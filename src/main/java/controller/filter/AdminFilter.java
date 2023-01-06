package controller.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.entity.User;

import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*")
public class AdminFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (req.getSession().getAttribute("User")!=null) {
            User user = (User) req.getSession().getAttribute("User");
            if (user.getStatus() == 0) {
                chain.doFilter(req, resp);
            } else {
                resp.sendRedirect(req.getContextPath() + "/welcome");
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/welcome");
        }
    }
}
