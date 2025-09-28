<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Book</title>
</head>
<body>
    <h2>Add Book</h2>
    <form action="LibrarianBookServlet" method="post">
        <input type="hidden" name="action" value="add" />
        Title: <input type="text" name="title" required/><br/>
        Author: <input type="text" name="author" required/><br/>
        Genre: <input type="text" name="genre" required/><br/>
        Copies: <input type="number" name="copies" required min="1"/><br/>
        <input type="submit" value="Add Book"/>
    </form>

    <% 
      String error = request.getParameter("error");
      String msg = request.getParameter("msg");
      if (error != null) { %>
          <p style="color:red;"><%= error %></p>
    <% } else if (msg != null) { %>
          <p style="color:green;"><%= msg %></p>
    <% } %>

    <a href="ViewBooksServlet">View All Books</a>
</body>
</html>
