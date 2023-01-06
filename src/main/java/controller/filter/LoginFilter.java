package controller.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.User;
import model.service.UserService;

import java.io.IOException;

@WebFilter(urlPatterns = {"/main/*", "/register/confirm"})
public class LoginFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req.getSession().getAttribute("User") == null ||
                UserService.getUserStatus(((User) req.getSession().getAttribute("User")).getId()) == -1) {
            resp.sendRedirect("/welcome");
        } else {
            chain.doFilter(req, resp);
        }
    }
}
