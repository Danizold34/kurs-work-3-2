package test.nyha.webfinal.model.service.impl;

import com.nyha.webfinal.entity.User;
import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.model.dao.BankDao;
import com.nyha.webfinal.model.service.impl.BankServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class BankServiceImplTest {
    @Mock
    private BankDao bankDao;
    @InjectMocks
    private BankServiceImpl bankService;
    private AutoCloseable autoCloseable;

    @BeforeMethod
    public void init() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterMethod
    public void tierDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void debitAccountPositiveTest() throws DaoException, ServiceException {
        Optional expected = Optional.empty();
        when(bankDao.debitAccount(anyString(), anyDouble())).thenReturn(true);
        Optional<String> actual = bankService.debitAccount("1", 12);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void debitAccountNegativeTest() throws DaoException, ServiceException {
        Optional expected = Optional.empty();
        when(bankDao.debitAccount(anyString(), anyDouble())).thenReturn(false);
        Optional<String> actual = bankService.debitAccount("1", 12);
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void debitAccountServiceExceptionTest() throws ServiceException, DaoException {
        when(bankDao.debitAccount(anyString(), anyDouble())).thenThrow(DaoException.class);
        bankService.debitAccount("1", 12);
    }
}
