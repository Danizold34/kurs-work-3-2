package com.nyha.webfinal.model.dao.impl;

import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.model.dao.BankDao;
import com.nyha.webfinal.pool.ConnectionPool;

import static com.nyha.webfinal.model.dao.ColumnName.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Works with database table bank_accounts
 *
 * @author Dovlet Jumaev
 * @see BankDao
 */
public class BankDaoImpl implements BankDao {
    static Logger logger = LogManager.getLogger();
    private static final String FIND_ACCOUNT = "SELECT bank_account_id, money_amount FROM bank_accounts WHERE bank_account_id = ?";
    private static final String UPDATE_ACCOUNT = "UPDATE `bank_accounts` SET money_amount = ? WHERE bank_account_id = ?";

    @Override
    public boolean debitAccount(String accountNumber, double price) throws DaoException {
        boolean isUpdate = false;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement preparedStatementFind = connection.prepareStatement(FIND_ACCOUNT);
             PreparedStatement preparedStatementUpdate = connection.prepareStatement(UPDATE_ACCOUNT)
        ) {
            preparedStatementFind.setLong(1, Long.parseLong(accountNumber));
            ResultSet resultSet = preparedStatementFind.executeQuery();
            double currentMoney = 0;
            boolean flag = false;
            while (resultSet.next()) {
                currentMoney = resultSet.getDouble(MONEY_AMOUNT);
                flag = true;
            }
            if (flag) {
                if (currentMoney < price) {
                    return false;
                }
                currentMoney -= price;
                preparedStatementUpdate.setDouble(1, currentMoney);
                preparedStatementUpdate.setLong(2, Long.parseLong(accountNumber));
                isUpdate = preparedStatementUpdate.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logger.error("debitTheAccount error, " + accountNumber, e);
            throw new DaoException("debitTheAccount error, " + accountNumber, e);
        }
        return isUpdate;
    }

    @Override
    public List findAll() throws DaoException {
        throw new UnsupportedOperationException();
    }
}
