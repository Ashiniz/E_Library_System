package src.servlets;

import src.beans.Librarian;
import src.beans.LibrarianDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LibrarianProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session == null || !"librarian".equals(session.getAttribute("role"))) {
            response.sendRedirect("librarian_login.jsp");
            return;
        }
        String userID = (String) session.getAttribute("userID");
        String phone = request.getParameter("phone");
        String employeeID = request.getParameter("employeeID");
        String password = request.getParameter("password");

        Librarian lib = new Librarian();
        lib.setUserID(userID);
        lib.setPhone(phone);
        lib.setEmployeeID(employeeID);
        lib.setPassword(password);

        LibrarianDAO dao = new LibrarianDAO();
        if(dao.updateLibrarian(lib)) {
            response.sendRedirect("librarian_profile.jsp?msg=Profile updated successfully.");
        } else {
            response.sendRedirect("librarian_profile.jsp?error=Update failed.");
        }
    }
}
