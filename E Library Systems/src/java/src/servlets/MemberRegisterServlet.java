package src.servlets;

import src.beans.Member;
import src.beans.MemberDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class MemberRegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nic = request.getParameter("nic");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        Member member = new Member();
        member.setNic(nic); // ✅ FIXED here
        member.setName(name);
        member.setPhone(phone);
        member.setPassword(password);

        MemberDAO dao = new MemberDAO();
        if (dao.registerMember(member)) {
            response.sendRedirect("member_login.jsp?msg=Registered successfully.");
        } else {
            response.sendRedirect("MemberRegister.jsp?error=Registration failed.");
        }
    }
}