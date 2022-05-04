package com.nyha.webfinal.model.service.impl;

import com.nyha.webfinal.util.MailSender;
import com.nyha.webfinal.util.PasswordEncryption;
import com.nyha.webfinal.model.dao.UserDao;
import com.nyha.webfinal.model.dao.impl.UserDaoImpl;
import com.nyha.webfinal.entity.User;
import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.model.service.UserService;
import com.nyha.webfinal.validator.UserValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * The service is responsible for user operations
 *
 * @author Dovlet Jumaev
 * @see UserService
 */
public class UserServiceImpl implements UserService {
    static Logger logger = LogManager.getLogger();
    public static final String INCORRECT_EMAIL = "incorrectEmail";
    public static final String EMAIL_ALREADY_EXISTS = "emailAlreadyExists";
    public static final String INCORRECT_USERNAME = "incorrectUsername";
    public static final String INCORRECT_PASSWORD = "incorrectPassword";
    public static final String PASSWORD_CHANGED = "passwordChanged";
    public static final String USER_UPDATED = "userUpdated";
    public static final String MAIL_SUBJECT = "Registration";
    public static final String MAIL_TEXT = "Thank you for registering on our website.";
    private UserDao userDao = new UserDaoImpl();

    @Override
    public Optional<User> findUserByEmail(String email) throws ServiceException {
        Optional<User> user;
        if (UserValidator.isValidEmail(email)) {
            try {
                user = userDao.findUserByEmail(email);
            } catch (DaoException e) {
                logger.error("search error, email: " + email, e);
                throw new ServiceException("search error,  email:" + email, e);
            }
        } else {
            user = Optional.empty();
        }
        return user;
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException {
        Optional<User> user;
        if (UserValidator.isValidEmail(email) && UserValidator.isValidPassword(password)) {
            try {
                String encodedPassword = PasswordEncryption.encrypt(password);
                user = userDao.findUserByEmailAndPassword(email, encodedPassword);
            } catch (DaoException e) {
                logger.error("search error, email: " + email, e);
                throw new ServiceException("search error,  email: " + email, e);
            }
        } else {
            user = Optional.empty();
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() throws ServiceException {
        List<User> users;
        try {
            users = userDao.findAll();
        } catch (DaoException e) {
            logger.error("search error", e);
            throw new ServiceException("search error", e);
        }
        return users;
    }

    @Override
    public Optional<String> addUser(User user, String password) throws ServiceException {
        if (!UserValidator.isValidEmail(user.getEmail())) {
            return Optional.of(INCORRECT_EMAIL);
        }
        if (findUserByEmail(user.getEmail()).isPresent()) {
            return Optional.of(EMAIL_ALREADY_EXISTS);
        }
        if (!UserValidator.isValidUsername(user.getUsername())) {
            return Optional.of(INCORRECT_USERNAME);
        }
        if (!UserValidator.isValidPassword(password)) {
            return Optional.of(INCORRECT_PASSWORD);
        }
        try {
            String encodedPassword = PasswordEncryption.encrypt(password);
            userDao.addUser(user, encodedPassword);
            MailSender.sendEmail(user.getEmail(), MAIL_SUBJECT, MAIL_TEXT);
        } catch (DaoException e) {
            logger.error("add user error, " + user, e);
            throw new ServiceException("add user error, " + user, e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<String> changePassword(User user, String password) throws ServiceException {
        try {
            String encodedPassword = PasswordEncryption.encrypt(password);
            userDao.changePassword(user, encodedPassword);
        } catch (DaoException e) {
            logger.error("change password error, " + user, e);
            throw new ServiceException("change password error, " + user, e);
        }
        return Optional.of(PASSWORD_CHANGED);
    }

    @Override
    public Optional<String> updateUser(User user) throws ServiceException {
        try {
            userDao.updateUser(user);
        } catch (DaoException e) {
            logger.error("updateUser error, " + user, e);
            throw new ServiceException("updateUser error, " + user, e);
        }
        return Optional.of(USER_UPDATED);
    }
}
