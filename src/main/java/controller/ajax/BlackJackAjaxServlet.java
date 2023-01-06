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
import java.util.LinkedList;
import java.util.Map;

@WebServlet (urlPatterns = "/technical/blackjack")
public class BlackJackAjaxServlet extends HttpServlet {

    private static final long serialVersionUID = 1l;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlackJackGame game = BlackJackService.getGame(req.getParameter("message"));
        User user = (User) req.getSession().getAttribute("User");
        if (!BlackJackService.isPlayer(user.getId(), game.getId())){
            BlackJackRepository.addPlayer(game.getId(),user.getId());
        }
        LinkedList<BlackJackPlayer> list = new LinkedList<>();
        boolean check = true;
        for(Map.Entry<Long, BlackJackPlayer> entry : game.getPlayers().entrySet()) {
            list.add(entry.getValue());
            if (entry.getValue().is_stopped()){
                check = false;
            }
        }
        if (check){
            BlackJackRepository.getWinner(game.getId());
        }
        req.getSession().setAttribute("players_list", list);
        resp.setContentType("text/html");
        req.getRequestDispatcher("/WEB-INF/ajax-view/Game.jsp").forward(req,resp);
    }
}
