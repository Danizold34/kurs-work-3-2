package com.nyha.webfinal.controller.filter;

import com.nyha.webfinal.controller.SessionAttribute;
import com.nyha.webfinal.controller.command.PagePath;
import com.nyha.webfinal.entity.User;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter to control access to admin pages
 *
 * @author Dovlet Jumaev
 * @see Filter
 */
@WebFilter(urlPatterns = {"/pages/admin/*"})
public class AdminPageFilter implements Filter {
    public static final String ERROR_ACCESS = "errorAccess";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(true);
        User user = (User) session.getAttribute(SessionAttribute.USER);
        if (session.getAttribute(SessionAttribute.USER) == null) {
            httpResponse.sendRedirect(PagePath.ERROR_500);
            session.setAttribute(SessionAttribute.EXCEPTION, ERROR_ACCESS);
            return;
        }
        if (!user.getRole().equals(User.Role.ADMIN)) {
            httpResponse.sendRedirect(PagePath.MESSAGE);
            session.setAttribute(SessionAttribute.ERROR_MESSAGE, ERROR_ACCESS);
            return;
        }
        chain.doFilter(request, response);
    }
}
