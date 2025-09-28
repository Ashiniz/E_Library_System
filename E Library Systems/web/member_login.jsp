<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Member Login</title>
</head>
<body>
    <h2>Member Login</h2>
    <form action="loginMember" method="post">
        NIC: <input type="text" name="nic" required/><br/>
        Password: <input type="password" name="password" required/><br/>
        <input type="submit" value="Login"/>
    </form>
</body>
</html>
