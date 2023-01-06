package controller.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.BlackJack.BlackJackGame;
import model.entity.BlackJack.BlackJackPlayer;
import model.entity.User;
import model.repository.blackjack.BlackJackRepository;
import model.service.BlackJackService;

import java.io.IOException;

@WebServlet (urlPatterns = "/technical/blackjack/stop")
public class BlackJackStopAjaxServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlackJackGame game = BlackJackService.getGame(req.getParameter("game_id"));
        User user = (User) req.getSession().getAttribute("User");
        BlackJackPlayer player = game.getPlayers().get(user.getId());
        BlackJackRepository.stopPlayer(player.getGame_id(), player.getPlayer_id());
    }
}
