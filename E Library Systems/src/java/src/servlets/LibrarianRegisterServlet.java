package src.servlets;

import src.beans.Librarian;
import src.beans.LibrarianDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LibrarianRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String employeeID = request.getParameter("employeeID");

        Librarian lib = new Librarian();
        lib.setUserID(userID);
        lib.setName(name);
        lib.setPassword(password);
        lib.setPhone(phone);
        lib.setEmployeeID(employeeID);

        LibrarianDAO dao = new LibrarianDAO();
        boolean success = dao.registerLibrarian(lib);

   if(success) {
    response.sendRedirect("librarian_login.jsp?msg=Registration successful, please login.");
} else {
    response.sendRedirect("librarian_register.jsp?error=Registration failed. Try again.");
}

    }
}
