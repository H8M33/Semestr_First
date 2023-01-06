package controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.service.MailService;
import model.service.UserService;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@WebServlet(urlPatterns = "/welcome/register")
@MultipartConfig
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/RegisterJSP.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Part filePart = req.getPart("file");
        InputStream fileContent = filePart.getInputStream();
        String toReg = UserService.signUp(
                req.getParameter("userName"),
                req.getParameter("userPassword"),
                req.getParameter("userEmail"), fileContent);
        if (toReg.equals("Successfully")) {
            UserService.signIn(
                    req.getParameter("userEmail"),
                    req.getParameter("userPassword"),
                    req);
            req.getSession().removeAttribute("RegisterException");
            req.getSession().setAttribute("id", UserService.getIDByUsername(req.getParameter("userName")));
            String code = UUID.randomUUID().toString();
            req.getSession().setAttribute("code",code);
            MailService.sendMessage(req.getParameter("userEmail"),"Confirm you registration",
                    "That's you code. Type it in the special box: "+code);
            resp.sendRedirect(req.getContextPath() + "/email/confirm");
        } else {
            req.getSession().setAttribute("RegisterException", toReg);
            resp.sendRedirect(req.getContextPath() + "/welcome/register");
        }
    }
}
