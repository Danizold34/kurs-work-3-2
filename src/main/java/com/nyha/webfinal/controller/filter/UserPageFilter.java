package com.nyha.webfinal.controller.filter;

import com.nyha.webfinal.controller.SessionAttribute;
import com.nyha.webfinal.controller.command.PagePath;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Filter to control access to user pages
 *
 * @author Dovlet Jumaev
 * @see Filter
 */
@WebFilter(urlPatterns = {"/pages/user/*"})
public class UserPageFilter implements Filter {
    public static final String ERROR_ACCESS = "errorAccess";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(true);
        if (session.getAttribute(SessionAttribute.USER) == null) {
            httpResponse.sendRedirect(PagePath.ERROR_500);
            session.setAttribute(SessionAttribute.EXCEPTION, ERROR_ACCESS);
            return;
        }
        chain.doFilter(request, response);
    }
}
