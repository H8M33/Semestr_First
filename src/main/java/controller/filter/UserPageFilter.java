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

@WebFilter(urlPatterns = "/main/user/*")
public class UserPageFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req.getSession().getAttribute("User") != null) {
            User user = (User) req.getSession().getAttribute("User");
            if (!UserService.existUser(req.getParameter("id"))) {
                resp.sendRedirect("/main/user?id=" + user.getId());
            } else {
                chain.doFilter(req, resp);
            }
        } else {
            resp.sendRedirect("/welcome");
        }
    }
}
