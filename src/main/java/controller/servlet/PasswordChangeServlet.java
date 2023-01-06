package controller.servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.User;
import model.service.UserService;

import java.io.IOException;

@WebServlet (urlPatterns = "/main/password/change")
public class PasswordChangeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/PasswordChangeJSP.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String toUpdate = UserService.updateUserPassword(((User)req
                .getSession().getAttribute("User")).getId(),req.getParameter("userPassword"));
        if (toUpdate.equals("Successfully")
        ) {
            req.getSession().removeAttribute("UpdatePasswordException");
            resp.sendRedirect(req.getContextPath() + "/main/user");
        } else {
            req.getSession().setAttribute("UpdatePasswordException", toUpdate);
            resp.sendRedirect(req.getContextPath() + "/main/password/change");
        }
    }
}
