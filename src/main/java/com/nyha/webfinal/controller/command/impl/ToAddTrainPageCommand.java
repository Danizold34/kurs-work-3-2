package com.nyha.webfinal.controller.command.impl;

import com.nyha.webfinal.controller.SessionAttribute;
import com.nyha.webfinal.controller.command.Command;
import com.nyha.webfinal.controller.command.PagePath;
import com.nyha.webfinal.controller.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The command is responsible for going to the add train page
 *
 * @author Dovlet Jumaev
 * @see Command
 */
public class ToAddTrainPageCommand implements Command  {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router();
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionAttribute.CURRENT_COMMAND, PagePath.ADD_TRAIN);
        router.setPage(PagePath.ADD_TRAIN);
        return router;
    }
}
