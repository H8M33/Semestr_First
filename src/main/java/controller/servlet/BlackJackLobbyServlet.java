package controller.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.User;
import model.repository.blackjack.BlackJackRepository;
import model.service.BlackJackService;

import java.io.IOException;

@WebServlet (urlPatterns = "/main/games/blackjack/lobby")
public class BlackJackLobbyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/view/BlackJackLobbyJSP.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long id = BlackJackService.getIdToCreate();
        User user = (User) req.getSession().getAttribute("User");
        BlackJackService.createGame(id);
        BlackJackRepository.addPlayer(id, user.getId());
        resp.sendRedirect(req.getContextPath()+"/main/games/blackjack/game?id="+id);
    }
}
