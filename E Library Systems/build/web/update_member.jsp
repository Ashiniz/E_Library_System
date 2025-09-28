<%@ page import="javax.servlet.http.HttpSession" %>
<%
    if (session == null || session.getAttribute("userid") == null) {
        response.sendRedirect("member_login.jsp");
        return;
    }

    String nic = (String) session.getAttribute("userid");
    String name = (String) session.getAttribute("memberName");
    String phone = (String) session.getAttribute("memberPhone");
    String password = (String) session.getAttribute("memberPassword");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Update Member Profile</title>
</head>
<body>
    <h2>Update Profile</h2>
    <form action="MemberProfileServlet" method="post">
        NIC: <strong><%= nic %></strong><br/>
        Name: <input type="text" name="name" value="<%= name %>" required/><br/>
        Phone: <input type="text" name="phone" value="<%= phone %>" required/><br/>
        Password: <input type="password" name="password" value="<%= password %>" required/><br/>
        <input type="submit" value="Update"/>
    </form>

    <%
        if (request.getParameter("msg") != null) {
            out.println("<p style='color:green'>" + request.getParameter("msg") + "</p>");
        }
        if (request.getParameter("error") != null) {
            out.println("<p style='color:red'>" + request.getParameter("error") + "</p>");
        }
    %>
</body>
</html>
