<%@ page import="java.sql.*, javax.servlet.*, javax.servlet.http.*" %>
<%
    // Check if the member is logged in
    String memberNic = (String) session.getAttribute("memberNic");
    if (memberNic == null) {
        response.sendRedirect("member_login.jsp?error=Please+login+first");
        return;
    }

    String memberName = "";
    int memberId = -1;

    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/elibrary", "root", "");
        PreparedStatement ps = con.prepareStatement("SELECT id, name FROM members WHERE nic = ?");
        ps.setString(1, memberNic);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            memberId = rs.getInt("id");
            memberName = rs.getString("name");
        }
        rs.close(); ps.close(); con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    if (memberId == -1) {
        response.sendRedirect("member_login.jsp?error=Member+not+found");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Member Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= memberName %></h2>

    <ul>
        
        <li><a href="update_member.jsp">Update Profile</a></li>
        <li><a href="index.jsp">Logout</a></li>
    </ul>

    <h3 id="borrow">Available Books</h3>

    <%
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/elibrary", "root", "");
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM books");
    %>

    <table border="1" cellpadding="8">
        <tr><th>Title</th><th>Author</th><th>Available Copies</th><th>Action</th></tr>
        <%
            while (rs.next()) {
                String bookId = rs.getString("id");
                int available = rs.getInt("copiesAvailable");
        %>
            <tr>
                <td><%= rs.getString("title") %></td>
                <td><%= rs.getString("author") %></td>
                <td><%= available %></td>
                <td>
                    <% if (available > 0) { %>
                        <form method="post" action="BorrowBookServlet">
                            <input type="hidden" name="memberId" value="<%= memberId %>" />
                            <input type="hidden" name="bookId" value="<%= bookId %>" />
                            <input type="submit" value="Borrow" />
                        </form>
                    <% } else { %>
                        Not Available
                    <% } %>
                </td>
            </tr>
        <%
            }
        %>
    </table>

    <%
        } catch (Exception e) {
            e.printStackTrace();
    %>
        <p style="color:red;">Error loading books.</p>
    <%
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception ignored) {}
            try { if (st != null) st.close(); } catch (Exception ignored) {}
            try { if (con != null) con.close(); } catch (Exception ignored) {}
        }
    %>

</body>
</html>
