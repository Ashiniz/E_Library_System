<%@ page import="javax.servlet.http.*, javax.servlet.*" %>
<%
   
    if (session == null || !"librarian".equals(session.getAttribute("role"))) {
        response.sendRedirect("librarian_login.jsp");
        return;
    }
    String librarianName = (String) session.getAttribute("name");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Librarian Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= librarianName %></h2>
    <ul>
        <li><a href="add_book.jsp">Add Book</a></li>
        <li><a href="ViewBooksServlet">View Books</a></li>
        <li><a href="librarian_profile.jsp">Update Profile</a></li>
        <li><a href="index.jsp">Logout</a></li>
    </ul>
</body>
</html>
