package src.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import src.beans.MemberDAO;
import src.beans.Member;

@WebServlet("/MemberLoginServlet")
public class MemberLoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nic = request.getParameter("nic");
        String password = request.getParameter("password");

        MemberDAO memberDAO = new MemberDAO();
        Member member = memberDAO.authenticate(nic, password);

        if (member != null) {
            HttpSession session = request.getSession();

            // ✅ Store essential session attributes
            session.setAttribute("memberName", member.getName());
            session.setAttribute("userid", member.getNic());
            session.setAttribute("role", "member");
            session.setAttribute("member", member);
            session.setAttribute("memberNic", member.getNic()); // ✅ Added line

            response.sendRedirect("member_dashboard.jsp");
        } else {
            response.sendRedirect("member_login.jsp?error=1");
        }
    }
}
