package controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.User;
import model.repository.user.UserRepository;
import model.service.UserService;

import java.io.IOException;
import java.io.InputStream;

@WebServlet(urlPatterns = "/main/user/update")
@MultipartConfig
public class UserUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("User");
        req.getSession().setAttribute("UserToUpdate", UserService.getUsername(Long.parseLong(req.getParameter("id"))));
        if (Long.parseLong(req.getParameter("id")) != user.getId() && user.getStatus() != 0) {
            resp.sendRedirect("/main/user?id=" + req.getParameter("id"));
        } else {
            req.getRequestDispatcher("/WEB-INF/view/UserUpdateJSP.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("User");
        if (req.getParameter("action").equals("UpdateImage")) {
            InputStream fileContent = req.getPart("file").getInputStream();
            UserRepository.updateImage(fileContent, user.getId());
        } else if (req.getParameter("action").equals("UpdateUsername")) {
            UserService.updateUserName(req.getParameter("username"), user.getId());
        }
        resp.sendRedirect(req.getContextPath() + "/main/user");
    }
}
