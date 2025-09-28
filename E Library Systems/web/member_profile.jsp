<%@ page session="true" %>
<%@ page import="src.beans.Member" %>
<%
    Member member = (Member) session.getAttribute("member");
    if (member == null) {
        response.sendRedirect("member_login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Member Profile</title>
</head>
<body>
    <h2>Update Profile</h2>
    <form action="MemberProfileServlet" method="post">
        NIC: <%= member.getNic() %> (Uneditable)<br/>
        Name: <input type="text" name="name" value="<%= member.getName() %>" required/><br/>
        Phone: <input type="text" name="phone" value="<%= member.getPhone() %>" required/><br/>
        Password: <input type="password" name="password" value="<%= member.getPassword() %>" required/><br/>
        <input type="submit" value="Update"/>
    </form>
</body>
</html>
