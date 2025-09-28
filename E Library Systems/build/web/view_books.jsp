<%@ page import="java.util.List" %>
<%@ page import="src.beans.Book" %>
<%@ page session="true" %>
<%
    List<Book> books = (List<Book>) request.getAttribute("bookList");
    String msg = request.getParameter("msg");
    String error = request.getParameter("error");
%>
<!DOCTYPE html>
<html>
<head>
    <title>View Books</title>
</head>
<body>

    <h2>All Books</h2>

    <% if (msg != null) { %>
        <p style="color:green;"><%= msg %></p>
    <% } %>
    <% if (error != null) { %>
        <p style="color:red;"><%= error %></p>
    <% } %>

    <table border="1" cellpadding="8">
        <tr>
            <th>Book ID</th>
            <th>Title</th>
            <th>Author</th>
            <th>Genre</th>
            <th>Copies</th>
            <th>Actions</th>
        </tr>
        <%
            if (books != null && !books.isEmpty()) {
                for (Book book : books) {
        %>
        <tr>
            <td><%= book.getId() %></td>
            <td><%= book.getTitle() %></td>
            <td><%= book.getAuthor() %></td>
            <td><%= book.getGenre() %></td>
            <td><%= book.getCopies() %></td>
            <td>
                <a href="edit_book.jsp?id=<%= book.getId() %>">Edit</a> |
                <a href="LibrarianBookServlet?action=delete&id=<%= book.getId() %>" onclick="return confirm('Delete this book?');">Delete</a>
            </td>
        </tr>
        <%
                }
            } else {
        %>
        <tr><td colspan="6" style="text-align:center;">No books found.</td></tr>
        <% } %>
    </table>

    <br/>
    <a href="add_book.jsp">Add New Book</a>

</body>
</html>
