package src.servlets;

import src.beans.Librarian;
import src.beans.LibrarianDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LibrarianLoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        LibrarianDAO dao = new LibrarianDAO();

        if (dao.authenticate(userID, password)) {
            Librarian librarian = dao.getLibrarianById(userID);

            HttpSession session = request.getSession();
            session.setAttribute("userID", librarian.getUserID());
            session.setAttribute("role", "librarian");
            session.setAttribute("name", librarian.getName());
            session.setAttribute("employeeID", librarian.getEmployeeID());

            response.sendRedirect("librarian_dashboard.jsp");
        } else {
            request.setAttribute("error", "Invalid credentials.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("librarian_login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
