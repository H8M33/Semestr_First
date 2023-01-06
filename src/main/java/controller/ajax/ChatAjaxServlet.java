package controller.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.User;
import model.repository.MessageRepository;

import java.io.IOException;

@WebServlet (urlPatterns = "/technical/chat")
public class ChatAjaxServlet extends HttpServlet {

    private static final long serialVersionUID = 1l;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("User");
        String name = (String) req.getSession().getAttribute("name");
        req.getSession().setAttribute("allMessages", MessageRepository.getMessages(user.getUsername(), name));
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/ajax-view/Chat.jsp").forward(req,resp);
    }
}
