package controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.service.EmailConfirmService;
import model.service.UserService;

import java.io.IOException;

@WebServlet (urlPatterns = "/email/confirm")
public class EmailConfirmServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/ConfirmJSP.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String code = (String) req.getSession().getAttribute("code");
        long id = (long) req.getSession().getAttribute("id");
        String toConfirm = EmailConfirmService.confirm(code, req.getParameter("code"), id);
        if (toConfirm.equals("Successfully")){
            req.getSession().removeAttribute("id");
            req.getSession().removeAttribute("ConfirmException");
            if (req.getSession().getAttribute("toRestore")!=null){
                UserService.signInByID(id,req);
            }
            resp.sendRedirect("/main/user");
        }
        else {
            req.getSession().setAttribute("ConfirmException", toConfirm);
            resp.sendRedirect("/email/confirm");
        }

    }
}
