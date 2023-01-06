package controller.ajax;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.BlackJack.BlackJackGame;
import model.entity.BlackJack.BlackJackPlayer;
import model.entity.BlackJack.Card;
import model.entity.BlackJack.Deck;
import model.entity.User;
import model.repository.blackjack.BlackJackRepository;
import model.service.BlackJackService;
import model.service.DeckMakerService;

import java.io.IOException;
import java.util.TreeMap;

@WebServlet(urlPatterns = "/technical/blackjack/get")
public class BlackJackTakeAjaxServlet extends HttpServlet {

    private static final TreeMap<Long, Deck> deckMap = new TreeMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BlackJackGame game = BlackJackService.getGame(req.getParameter("game_id"));
        User user = (User) req.getSession().getAttribute("User");
        BlackJackPlayer player = game.getPlayers().get(user.getId());
        if (player.getValue() < 21) {
            Card card = deckMap.computeIfAbsent(game.getId(), value -> DeckMakerService.getDeck()).getCard();
            player.setCards(player.getCards() + " " + card.getName());
            player.setValue(player.getValue() + card.getValue());
            if (card.getValue() == 11) {
                if (player.getAce_counts() == -1 || player.getValue() > 22) {
                    player.setValue(player.getValue() - 10);
                } else {
                    if (player.getValue() > 22) {
                        player.setValue(player.getValue() - 10 * player.getAce_counts());
                        player.setAce_counts(-1);
                    } else {
                        player.setAce_counts(player.getAce_counts() + 1);
                    }
                }
            }
            BlackJackRepository.readyPlayers(game.getId());
            BlackJackRepository.updatePlayer(player);
        }
    }
}
