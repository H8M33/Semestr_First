package controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.UserService;

import java.io.IOException;

@WebServlet(urlPatterns = "/welcome/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/LoginJSP.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String toLog = UserService
                .signIn(
                        req.getParameter("userEmail"),
                        req.getParameter("userPassword"),
                        req);
        if (toLog.equals("Successfully")
        ) {
            req.getSession().removeAttribute("LoginException");
            resp.sendRedirect(req.getContextPath() + "/main/user");
        } else {
            req.getSession().setAttribute("LoginException", toLog);
            resp.sendRedirect(req.getContextPath() + "/welcome/login");
        }
    }
}
