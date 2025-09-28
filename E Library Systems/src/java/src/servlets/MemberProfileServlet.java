package src.servlets;

import src.beans.Member;
import src.beans.MemberDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class MemberProfileServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || !"member".equals(session.getAttribute("role"))) {
            response.sendRedirect("member_login.jsp");
            return;
        }

        String nic = (String) session.getAttribute("userid");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        Member member = new Member();
        member.setNic(nic); // ✅ FIXED here
        member.setName(name);
        member.setPhone(phone);
        member.setPassword(password);

        MemberDAO dao = new MemberDAO();
        if (dao.updateMember(member)) {
            response.sendRedirect("member_profile.jsp?msg=Profile updated.");
        } else {
            response.sendRedirect("member_profile.jsp?error=Update failed.");
        }
    }
}