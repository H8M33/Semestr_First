package controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.User;
import model.service.UserService;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet (urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {
    List<User> userList;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        userList = UserService.getUserList();
        req.getSession().setAttribute("UserList", userList);
        req.getRequestDispatcher("/WEB-INF/view/AdminJSP.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LinkedList<User> list = new LinkedList<>();
        for (User user: userList
             ) {
            if (req.getParameter("user"+user.getId())!=null){
                list.add(user);
            }
        }
        UserService.deleteUsers(list);
        resp.sendRedirect(req.getContextPath()+"/admin");
    }
}
