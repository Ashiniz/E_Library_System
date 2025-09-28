<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Librarian Registration</title>
</head>
<body>
    <h2>Librarian Registration</h2>
    <form action="registerLibrarian" method="post">
        User ID: <input type="text" name="userID" required/><br/>
        Name: <input type="text" name="name" required/><br/>
        Password: <input type="password" name="password" required/><br/>
        Phone: <input type="text" name="phone" required/><br/>
        Employee ID: <input type="text" name="employeeID" required/><br/>
        <input type="submit" value="Register"/>
    </form>
</body>
</html>
