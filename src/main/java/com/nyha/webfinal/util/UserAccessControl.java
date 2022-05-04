package com.nyha.webfinal.util;

import com.nyha.webfinal.controller.SessionAttribute;
import com.nyha.webfinal.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * The utility is responsible for controlling the user
 *
 * @author Dovlet Jumaev
 */
public final class UserAccessControl {
    private UserAccessControl() {
    }

    /**
     * Checks if the role of the logged in user is appropriate
     *
     * @param request         {@link HttpServletRequest}
     * @param permissibleRole {@link User.Role} permissible user role
     * @return boolean true if the role of the logged in user is appropriate, else
     *         false
     */
    public static boolean isValidForRole(HttpServletRequest request, User.Role permissibleRole) {
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(SessionAttribute.USER);
        if (user.getRole() != permissibleRole) {
            return false;
        }
        return true;
    }
}
