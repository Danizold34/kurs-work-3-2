package com.nyha.webfinal.model.dao;

import com.nyha.webfinal.exception.DaoException;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

/**
 * The interface for working with database table bank_accounts
 *
 * @author Dovlet Jumaev
 * @see BaseDao
 */
public interface BankDao extends BaseDao {
    /**
     * Debit the account
     *
     * @param accountNumber {@link String}
     * @param price         {@link double}
     * @return boolean true if the account was debited successful, else false
     * @throws DaoException if {@link SQLException} occur
     */
    boolean debitAccount(String accountNumber, double price) throws DaoException;
}
