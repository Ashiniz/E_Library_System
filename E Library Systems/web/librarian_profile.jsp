<%@ page import="src.beans.LibrarianDAO, src.beans.Librarian" %>
<%
    String userid = (String) session.getAttribute("userID");
    if(userid == null || !"librarian".equals(session.getAttribute("role"))) {
        response.sendRedirect("index.jsp");
        return;
    }
    LibrarianDAO dao = new LibrarianDAO();
    Librarian librarian = dao.getLibrarianById(userid);
%>
<!DOCTYPE html>
<html>
<head><title>Librarian Profile</title></head>
<body>
<h2>Update Librarian Profile</h2>
<form action="LibrarianProfileServlet" method="post">

    Phone: <input type="text" name="phone" value="<%= librarian.getPhone() %>" required/><br/>
    Employee ID: <input type="text" name="employeeID" value="<%= librarian.getEmployeeID() %>" required/><br/>
    Password: <input type="password" name="password" value="<%= librarian.getPassword() %>" required/><br/>
    <input type="submit" value="Update Profile"/>
</form>
<a href="librarian_dashboard.jsp">Back to Dashboard</a>
</body>
</html>
