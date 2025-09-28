<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Member Registration</title>
</head>
<body>
    <h2>Member Registration</h2>
    <form action="registerMember" method="post">
        NIC: <input type="text" name="nic" required/><br/>
        Name: <input type="text" name="name" required/><br/>
        Phone: <input type="text" name="phone" required/><br/>
        Password: <input type="password" name="password" required/><br/>
        <input type="submit" value="Register"/>
    </form>
</body>
</html>
