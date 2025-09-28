package src.servlets;

import src.beans.Book;
import src.beans.BookDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class MemberViewBooksServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookDAO dao = new BookDAO();
        List<Book> books = dao.getAllBooks();
        request.setAttribute("bookList", books);
        RequestDispatcher dispatcher = request.getRequestDispatcher("view_books.jsp");
        dispatcher.forward(request, response);
    }
}
