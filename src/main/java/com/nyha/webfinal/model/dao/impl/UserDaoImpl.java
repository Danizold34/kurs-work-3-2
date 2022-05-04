package com.nyha.webfinal.model.dao.impl;

import com.nyha.webfinal.model.dao.TrainDao;
import com.nyha.webfinal.model.dao.UserDao;
import com.nyha.webfinal.entity.User;
import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.pool.ConnectionPool;
import static com.nyha.webfinal.model.dao.ColumnName.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Works with database table users
 *
 * @author Dovlet Jumaev
 * @see UserDao
 */
public class UserDaoImpl implements UserDao {
    static Logger logger = LogManager.getLogger();

    private static final String FIND_USER_BY_EMAIL = "SELECT user_id, email, username, role FROM users WHERE email = ?";
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT user_id, email, username, role FROM users WHERE email = ? AND password = ?";
    private static final String FIND_ALL_USERS = "SELECT user_id, email, username, role FROM users";
    private static final String ADD_USER = "INSERT INTO `users` (`email`, `password`, `username`, `role`) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_PASSWORD = "UPDATE `users` SET password = ? WHERE user_id = ?";
    private static final String UPDATE_USER = "UPDATE `users` SET email = ?, username = ?, role = ? WHERE user_id = ?";


    @Override
    public List<User> findAll() throws DaoException {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(USER_ID));
                user.setEmail(resultSet.getString(USER_EMAIL));
                user.setUsername(resultSet.getString(USER_USERNAME));
                user.setRole(User.Role.valueOf(resultSet.getString(USER_ROLE).toUpperCase()));
                users.add(user);
            }
        } catch (SQLException e) {
            logger.error("search error", e);
            throw new DaoException("search error", e);
        }
        return users;
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException {
        Optional<User> userOptional;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(USER_ID));
                user.setEmail(resultSet.getString(USER_EMAIL));
                user.setUsername(resultSet.getString(USER_USERNAME));
                user.setRole(User.Role.valueOf(resultSet.getString(USER_ROLE).toUpperCase()));
            }
            userOptional = Optional.ofNullable(user);
        } catch (SQLException e) {
            logger.error("search error, email " + email, e);
            throw new DaoException("search error, email" + email, e);
        }
        return userOptional;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        Optional<User> userOptional;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong(USER_ID));
                user.setEmail(resultSet.getString(USER_EMAIL));
                user.setUsername(resultSet.getString(USER_USERNAME));
                user.setRole(User.Role.valueOf(resultSet.getString(USER_ROLE).toUpperCase()));
            }
            userOptional = Optional.ofNullable(user);
        } catch (SQLException e) {
            logger.error("search error, email " + email, e);
            throw new DaoException("search error, email " + email, e);
        }
        return userOptional;
    }

    @Override
    public boolean addUser(User user, String password) throws DaoException {
        boolean isAdd;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getRole().toString());
            isAdd = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("add error, " + user, e);
            throw new DaoException("add error, " + user, e);
        }
        return isAdd;
    }

    @Override
    public boolean changePassword(User user, String password) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PASSWORD)) {
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, user.getId());
            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("change password error, " + user, e);
            throw new DaoException("change password error, " + user, e);
        }
        return isUpdate;
    }

    @Override
    public boolean updateUser(User user) throws DaoException {
        boolean isUpdate;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getRole().toString());
            preparedStatement.setLong(4, user.getId());
            isUpdate = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.error("change password error, " + user, e);
            throw new DaoException("change password error, " + user, e);
        }
        return isUpdate;
    }
}
