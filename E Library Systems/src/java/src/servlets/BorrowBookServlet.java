package src.servlets;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class BorrowBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String nic = (String) session.getAttribute("memberNic");
        String bookIdStr = request.getParameter("bookId");

        if (nic == null || bookIdStr == null || bookIdStr.isEmpty()) {
            response.sendRedirect("view_books_member.jsp?error=You+must+login+to+borrow+books");
            return;
        }

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/elibrary", "root", "");

            // Get member ID using NIC
            stmt = conn.prepareStatement("SELECT id FROM members WHERE nic = ?");
            stmt.setString(1, nic);
            rs = stmt.executeQuery();

            if (!rs.next()) {
                response.sendRedirect("view_books_member.jsp?error=Member+not+found");
                return;
            }

            int memberId = rs.getInt("id");
            rs.close();
            stmt.close();

            // Check how many books this member has borrowed and not returned
            stmt = conn.prepareStatement("SELECT COUNT(*) FROM borrowed_books WHERE member_id = ? AND returned = FALSE");
            stmt.setInt(1, memberId);
            rs = stmt.executeQuery();
            rs.next();
            int borrowedCount = rs.getInt(1);
            rs.close();
            stmt.close();

            if (borrowedCount >= 3) {
                response.sendRedirect("view_books_member.jsp?error=Borrow+limit+of+3+books+reached");
                return;
            }

            // Check availability
            stmt = conn.prepareStatement("SELECT copiesAvailable FROM books WHERE id = ?");
            stmt.setInt(1, Integer.parseInt(bookIdStr));
            rs = stmt.executeQuery();
            if (!rs.next()) {
                response.sendRedirect("view_books_member.jsp?error=Book+not+found");
                return;
            }

            int available = rs.getInt("copiesAvailable");
            rs.close();
            stmt.close();

            if (available <= 0) {
                response.sendRedirect("view_books_member.jsp?error=No+copies+available");
                return;
            }

            // Insert into borrowed_books
            stmt = conn.prepareStatement("INSERT INTO borrowed_books (member_id, book_id, borrow_date, returned) VALUES (?, ?, CURDATE(), FALSE)");
            stmt.setInt(1, memberId);
            stmt.setInt(2, Integer.parseInt(bookIdStr));
            stmt.executeUpdate();
            stmt.close();

            // Decrease copy count
            stmt = conn.prepareStatement("UPDATE books SET copiesAvailable = copiesAvailable - 1 WHERE id = ?");
            stmt.setInt(1, Integer.parseInt(bookIdStr));
            stmt.executeUpdate();

            response.sendRedirect("view_books_member.jsp?msg=Book+borrowed+successfully");

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("view_books_member.jsp?error=Server+error");
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (stmt != null) stmt.close(); } catch (Exception ignored) {}
            try { if (conn != null) conn.close(); } catch (Exception ignored) {}
        }
    }
}
