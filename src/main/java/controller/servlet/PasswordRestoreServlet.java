package controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.MailService;
import model.service.UserService;

import java.io.IOException;
import java.util.UUID;

@WebServlet (urlPatterns = "/welcome/restore")
public class PasswordRestoreServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/PasswordRestoreJSP.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("userEmail");
        long id = UserService.getIDByEmail(email);
        if (UserService.existUser(id)) {
            String code = UUID.randomUUID().toString();
            req.getSession().setAttribute("code",code);
            req.getSession().removeAttribute("PasswordRestoreException");
            req.getSession().setAttribute("toRestore","password");
            req.getSession().setAttribute("id", id);
            MailService.sendMessage(email, "Password restore",
                    "That's you code. If you asked for a password restore, type it in the special box: " + code);
            resp.sendRedirect("/email/confirm");
        }
        else {
            req.getSession().setAttribute("PasswordRestoreException","User with this email does not exist");
            resp.sendRedirect("/welcome/restore");
        }
    }
}
