package src.servlets;

import java.io.IOException;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;
import src.beans.Book;
import src.beans.BookDAO;

public class MemberBookServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BookDAO dao = new BookDAO();
        List<Book> books = dao.getAllBooks();

        request.setAttribute("bookList", books); // ✅ matches JSP usage
        RequestDispatcher dispatcher = request.getRequestDispatcher("view_books_member.jsp");
        dispatcher.forward(request, response);
    }
}
