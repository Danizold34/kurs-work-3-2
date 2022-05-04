package com.nyha.webfinal.model.service;

import com.nyha.webfinal.entity.User;
import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * The interface for operations with the train
 *
 * @author Dovlet Jumaev
 */
public interface UserService {
    /**
     * Finds user by email
     *
     * @param email {@link String}
     * @return {@link Optional} of {@link User}
     * @throws ServiceException if {@link DaoException} occurs
     */
    Optional<User> findUserByEmail(String email) throws ServiceException;

    /**
     * Finds user by email and password
     *
     * @param email    {@link String}
     * @param password {@link String}
     * @return {@link Optional} of {@link User}
     * @throws ServiceException if {@link DaoException} occurs
     */
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;

    /**
     * Finds all users
     *
     * @return {@link List} of {@link User}
     * @throws ServiceException if {@link DaoException} occurs
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Adds user
     *
     * @param user     {@link User}
     * @param password {@link String}
     * @return {@link Optional} of {@link String}
     * @throws ServiceException if {@link DaoException} occurs
     */
    Optional<String> addUser(User user, String password) throws ServiceException;

    /**
     * Changes password
     *
     * @param user     {@link User}
     * @param password {@link String}
     * @return {@link Optional} of {@link String}
     * @throws ServiceException if {@link DaoException} occurs
     */
    Optional<String> changePassword(User user, String password) throws ServiceException;

    /**
     * Updates user
     *
     * @param user {@link User}
     * @return {@link Optional} of {@link String}
     * @throws ServiceException if {@link DaoException} occurs
     */
    Optional<String> updateUser(User user) throws ServiceException;
}
