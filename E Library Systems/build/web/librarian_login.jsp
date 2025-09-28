<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Librarian Login</title>
</head>
<body>
    <h2>Librarian Login</h2>
    <form action="loginLibrarian" method="post">
        User ID: <input type="text" name="userID" required/><br/>
        Password: <input type="password" name="password" required/><br/>
        <input type="submit" value="Login"/>
    </form>

    <% 
        String error = (String) request.getAttribute("error");
        if (error != null) { 
    %>
        <p style="color: red;"><%= error %></p>
    <% 
        } 
    %>
</body>
</html>
