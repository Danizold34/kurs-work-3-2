package com.nyha.webfinal.controller.command.impl;

import com.nyha.webfinal.controller.command.Command;
import com.nyha.webfinal.controller.command.PagePath;
import com.nyha.webfinal.controller.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The command is responsible for logout
 *
 * @author Dovlet Jumaev
 * @see Command
 */
public class LogoutCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        router.setPage(PagePath.INDEX);
        router.setRedirect();
        HttpSession session = request.getSession();
        session.invalidate();
        return router;
    }
}
