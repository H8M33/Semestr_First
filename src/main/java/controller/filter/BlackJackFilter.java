package controller.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.User;
import model.service.BlackJackService;

import java.io.IOException;

@WebFilter(urlPatterns = "/main/games/blackjack/game")
public class BlackJackFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("User");
        if (user == null || "".equals(req.getParameter("id"))) {
            res.sendRedirect(req.getContextPath() + "/welcome");
        } else if (!BlackJackService.isGameStarted(req.getParameter("id")) ||
                (BlackJackService.isPlayer(user.getId(), req.getParameter("id")) && !BlackJackService.isEnded(req.getParameter("id")))) {
            chain.doFilter(req, res);
        } else {
            res.sendRedirect(req.getContextPath() + "/main/games/blackjack/lobby");
        }
    }
}
