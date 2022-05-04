package com.nyha.webfinal.model.service;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;

import java.util.Optional;

/**
 * The interface for operations with the bank account
 *
 * @author Dovlet Jumaev
 */
public interface BankService {
    /**
     * Debits the account
     *
     * @param accountNumber {@link String}
     * @param price         {@link double}
     * @return {@link Optional} of {@link String}
     * @throws ServiceException if {@link DaoException} occurs
     */
    Optional<String> debitAccount(String accountNumber, double price) throws ServiceException;
}
