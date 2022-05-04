package com.nyha.webfinal.controller;

import com.nyha.webfinal.controller.command.Command;
import com.nyha.webfinal.controller.command.CommandProvider;
import com.nyha.webfinal.controller.command.PagePath;
import com.nyha.webfinal.controller.command.Router;
import com.nyha.webfinal.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * Controller receive request from client (get or post)
 *
 * @author Dovlet Jumaev
 * @see HttpServlet
 */
@WebServlet(name = "controller", urlPatterns = {"/controller"})
public class Controller extends HttpServlet {
    static Logger logger = LogManager.getLogger();
    public static final String UNKNOWN_COMMAND = "unknown command";

    @Override
    public void init() {
        ConnectionPool.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes get and post requests
     *
     * @param request  {@link HttpServletRequest}
     * @param response {@link HttpServletResponse}
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String commandStr = request.getParameter(RequestParameter.COMMAND);
        logger.info(commandStr);
        Router router;
        Optional<Command> commandOptional = CommandProvider.defineCommand(commandStr);
        if (commandOptional.isPresent()) {
            Command command = commandOptional.get();
            router = command.execute(request);
        } else {
            router = new Router();
            logger.error("unknown command exception");
            router.setPage(PagePath.ERROR_500);
            request.setAttribute(RequestAttribute.EXCEPTION, UNKNOWN_COMMAND);
        }
        HttpSession session = request.getSession();
        session.setAttribute(SessionAttribute.CURRENT_PAGE, router.getPage());
        String page = router.getPage();
        logger.info(page);
        if (router.isRedirect()) {
            response.sendRedirect(page);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getInstance().destroyPool();
    }
}

