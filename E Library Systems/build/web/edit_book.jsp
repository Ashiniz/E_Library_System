<%@ page import="src.beans.Book, src.beans.BookDAO" %>
<%
    String idStr = request.getParameter("id");
    if(idStr == null) {
        response.sendRedirect("view_books.jsp?error=No book ID provided");
        return;
    }
    int id = Integer.parseInt(idStr);
    BookDAO dao = new BookDAO();
    Book book = dao.getBookById(id);
    if(book == null) {
        response.sendRedirect("view_books.jsp?error=Book not found");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head><title>Edit Book</title></head>
<body>
<h2>Edit Book</h2>
<form action="LibrarianBookServlet" method="post">
    <input type="hidden" name="action" value="update"/>
    <input type="hidden" name="id" value="<%= book.getId() %>"/>
    Title: <input type="text" name="title" value="<%= book.getTitle() %>" required/><br/>
    Author: <input type="text" name="author" value="<%= book.getAuthor() %>" required/><br/>
    Genre: <input type="text" name="genre" value="<%= book.getGenre() %>" required/><br/>
    Copies: <input type="number" name="copies" value="<%= book.getCopies() %>" required/><br/>
    <input type="submit" value="Update Book"/>
</form>
<a href="view_books.jsp">Back to Book List</a>
</body>
</html>
