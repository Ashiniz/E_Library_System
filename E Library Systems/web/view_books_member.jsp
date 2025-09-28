<%@ page import="java.sql.*, javax.servlet.http.*, javax.servlet.*" %>
<%
    // Check login
    String nic = (String) session.getAttribute("memberNic");
    if (nic == null) {
%>
        <p style="color:red;">You must <a href="member_login.jsp">login</a> to borrow books.</p>
        <%
        return; // Stop processing if not logged in
    }

    String error = request.getParameter("error");
    String msg = request.getParameter("msg");

    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/elibrary", "root", "");
    Statement st = con.createStatement();
    ResultSet rs = st.executeQuery("SELECT * FROM books");
%>

<!DOCTYPE html>
<html>
<head>
    <title>View Books - Member</title>
</head>
<body>

<h2>Available Books</h2>

<% if (error != null) { %>
    <p style="color:red;"><%= error %></p>
<% } %>
<% if (msg != null) { %>
    <p style="color:green;"><%= msg %></p>
<% } %>

<table border="1" cellpadding="8">
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>Genre</th>
        <th>Copies Available</th>
        <th>Action</th>
    </tr>

    <%
        while (rs.next()) {
            int available = rs.getInt("copiesAvailable");
    %>
    <tr>
        <td><%= rs.getString("title") %></td>
        <td><%= rs.getString("author") %></td>
        <td><%= rs.getString("genre") %></td>
        <td><%= available %></td>
        <td>
            <% if (available > 0) { %>
                <form method="post" action="BorrowBookServlet">
                    <input type="hidden" name="bookId" value="<%= rs.getString("id") %>" />
                    <input type="submit" value="Borrow" />
                </form>
            <% } else { %>
                Not Available
            <% } %>
        </td>
    </tr>
    <%
        }
        rs.close();
        st.close();
        con.close();
    %>
</table>

</body>
</html>
