package com.nyha.webfinal.model.service.impl;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.model.dao.BankDao;
import com.nyha.webfinal.model.dao.impl.BankDaoImpl;
import com.nyha.webfinal.model.service.BankService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

/**
 * The service is responsible for bank account operations
 *
 * @author Dovlet Jumaev
 * @see BankService
 */
public class BankServiceImpl implements BankService {
    static Logger logger = LogManager.getLogger();
    public static final String OPERATION_FAILED = "operationFailed";

    private BankDao bankDao = new BankDaoImpl();

    @Override
    public Optional<String> debitAccount(String accountNumber, double price) throws ServiceException {
        try {
            if (!bankDao.debitAccount(accountNumber, price)) {
               return Optional.of(OPERATION_FAILED);
            }
        } catch (DaoException e) {
            logger.error("debitTheAccount error, " + accountNumber, e);
            throw new ServiceException("debitTheAccount error, " + accountNumber, e);
        }
        return Optional.empty();
    }
}
