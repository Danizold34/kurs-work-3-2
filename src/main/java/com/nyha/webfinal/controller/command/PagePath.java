package com.nyha.webfinal.controller.command;

/**
 * Describes all page paths
 *
 * @author Dovlet Jumaev
 */
public class PagePath {
    public static final String INDEX = "/index.jsp";
    public static final String MAIN = "/pages/main.jsp";
    public static final String LOGIN = "/pages/login.jsp";
    public static final String USERS = "pages/admin/users.jsp";
    public static final String TRAINS = "/pages/trains.jsp";
    public static final String ALL_TRAINS = "/pages/admin/all_trains.jsp";
    public static final String TRAIN = "/pages/train.jsp";
    public static final String ERROR_500 = "/pages/errors/error500.jsp";
    public static final String ERROR_404 = "/pages/errors/error404.jsp";
    public static final String REGISTRATION = "/pages/registration.jsp";
    public static final String PROFILE = "/pages/user/profile.jsp";
    public static final String TICKETS = "/pages/user/tickets.jsp";
    public static final String TICKET = "/pages/user/ticket.jsp";
    public static final String MESSAGE = "/pages/message.jsp";
    public static final String ADMIN = "/pages/admin/admin.jsp";
    public static final String ADD_TRAIN = "/pages/admin/add_train.jsp";

    public static final String TO_USERS = "/controller?command=to_users_page";
    public static final String TO_TRAINS = "/controller?command=show_all_trains";
    public static final String TO_MY_TICKETS = "/controller?command=to_my_tickets_page";

    private PagePath() {
    }
}
