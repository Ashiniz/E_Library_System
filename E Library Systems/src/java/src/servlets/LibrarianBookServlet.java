package src.servlets;

import src.beans.Book;
import src.beans.BookDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LibrarianBookServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        BookDAO dao = new BookDAO();

        if ("add".equals(action)) {
            try {
                Book book = new Book();
                book.setTitle(request.getParameter("title"));
                book.setAuthor(request.getParameter("author"));
                book.setGenre(request.getParameter("genre"));
                book.setCopies(Integer.parseInt(request.getParameter("copies")));

                if (dao.addBook(book)) {
                    response.sendRedirect("add_book.jsp?msg=Book added successfully.");
                } else {
                    response.sendRedirect("add_book.jsp?error=Failed to add book.");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("add_book.jsp?error=Invalid number for copies.");
            }

        } else if ("update".equals(action)) {
            try {
                Book book = new Book();
                book.setId(Integer.parseInt(request.getParameter("id")));
                book.setTitle(request.getParameter("title"));
                book.setAuthor(request.getParameter("author"));
                book.setGenre(request.getParameter("genre"));
                book.setCopies(Integer.parseInt(request.getParameter("copies")));

                if (dao.updateBook(book)) {
                    response.sendRedirect("ViewBooksServlet?msg=Book updated successfully.");
                } else {
                    response.sendRedirect("edit_book.jsp?id=" + book.getId() + "&error=Failed to update book.");
                }

            } catch (NumberFormatException e) {
                response.sendRedirect("edit_book.jsp?id=" + request.getParameter("id") + "&error=Invalid input.");
            }

        } else if ("delete".equals(action)) {
            try {
                int bookId = Integer.parseInt(request.getParameter("id"));
                if (dao.deleteBook(bookId)) {
                    response.sendRedirect("ViewBooksServlet?msg=Book deleted successfully.");
                } else {
                    response.sendRedirect("ViewBooksServlet?error=Failed to delete book.");
                }
            } catch (NumberFormatException e) {
                response.sendRedirect("ViewBooksServlet?error=Invalid book ID.");
            }

        } else {
            response.sendRedirect("ViewBooksServlet");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
