package controller.ajax;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.User;
import model.repository.MessageRepository;

@WebServlet (urlPatterns = "/technical/message/send")
public class MessageSendAjaxServlet extends HttpServlet {

    private static final long serialVersionUID = 1l;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("User");
        String name = (String) req.getSession().getAttribute("name");
        String message = req.getParameter("message");
        MessageRepository.createMessage(user.getUsername(),name,message);
    }
}
