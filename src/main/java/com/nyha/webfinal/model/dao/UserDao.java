package com.nyha.webfinal.model.dao;

import com.nyha.webfinal.entity.User;
import com.nyha.webfinal.exception.DaoException;

import java.sql.SQLException;
import java.util.Optional;

/**
 * The interface for working with database table users
 *
 * @author Dovlet Jumaev
 * @see BaseDao
 */
public interface UserDao extends BaseDao<User> {
    /**
     * Finds users by email and password
     *
     * @param email    {@link String}
     * @param password {@link String}
     * @return {@link Optional} of {@link User}
     * @throws DaoException if {@link SQLException} occur
     */
    Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException;

    /**
     * Finds users by email
     *
     * @param email {@link String}
     * @return {@link Optional} of {@link User}
     * @throws DaoException if {@link SQLException} occur
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Adds user
     *
     * @param user     {@link User}
     * @param password {@link String}
     * @return boolean true if the user added successfully, else false
     * @throws DaoException if {@link SQLException} occur
     */
    boolean addUser(User user, String password) throws DaoException;

    /**
     * Changes user password
     *
     * @param user     {@link User}
     * @param password {@link String}
     * @return boolean true if the password changed successfully, else false
     * @throws DaoException if {@link SQLException} occur
     */
    boolean changePassword(User user, String password) throws DaoException;

    /**
     * Updates user password
     *
     * @param user     {@link User}
     * @return boolean true if the user updated successfully, else false
     * @throws DaoException if {@link SQLException} occur
     */
    boolean updateUser(User user) throws DaoException;
}