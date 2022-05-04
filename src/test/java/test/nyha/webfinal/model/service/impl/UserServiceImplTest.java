package test.nyha.webfinal.model.service.impl;

import com.nyha.webfinal.entity.User;
import com.nyha.webfinal.exception.DaoException;
import com.nyha.webfinal.exception.ServiceException;
import com.nyha.webfinal.model.dao.UserDao;
import com.nyha.webfinal.model.service.impl.UserServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceImplTest {
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserServiceImpl userService;
    private AutoCloseable autoCloseable;
    private User user;
    private String password;

    @BeforeClass
    public void setUp() {
        user = new User();
        user.setEmail("gret.andreyyy@gmail.com");
        user.setUsername("Nyha");
        user.setRole(User.Role.ADMIN);
        user.setId(1L);
        password = "nDpdyjqlw4";
    }

    @BeforeMethod
    public void init() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }

    @AfterMethod
    public void tierDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    public void findUserByEmailAndPasswordPositiveTest() throws DaoException, ServiceException {
        Optional<User> expected = Optional.of(user);
        when(userDao.findUserByEmailAndPassword(anyString(), anyString())).thenReturn(expected);
        Optional<User> actual = userService.findUserByEmailAndPassword("gret.andrey@gmail.com", "nDpdyjqlw4");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findUserByEmailAndPasswordNegativeTest() throws DaoException, ServiceException {
        Optional<User> expected = Optional.of(user);
        when(userDao.findUserByEmailAndPassword(anyString(), anyString())).thenReturn(Optional.empty());
        Optional<User> actual = userService.findUserByEmailAndPassword("gret.andrey@gmail.com", "nDpdyjqlw4");
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findUserByEmailAndPasswordServiceExceptionTest() throws ServiceException, DaoException {
        when(userDao.findUserByEmailAndPassword(anyString(), anyString())).thenThrow(DaoException.class);
        userService.findUserByEmailAndPassword("gret.andrey@gmail.com", "nDpdyjqlw4");
    }

    @Test
    public void findUserByEmailPositiveTest() throws DaoException, ServiceException {
        Optional<User> expected = Optional.of(user);
        when(userDao.findUserByEmail(anyString())).thenReturn(expected);
        Optional<User> actual = userService.findUserByEmail("gret.andrey@gmail.com");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findUserByEmailNegativeTest() throws DaoException, ServiceException {
        Optional<User> expected = Optional.of(user);
        when(userDao.findUserByEmail(anyString())).thenReturn(Optional.empty());
        Optional<User> actual = userService.findUserByEmail("nDpdyjqlw4");
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findUserByEmailServiceExceptionTest() throws ServiceException, DaoException {
        when(userDao.findUserByEmail(anyString())).thenThrow(DaoException.class);
        userService.findUserByEmail("gret.andrey@gmail.com");
    }

    @Test
    public void findAllUsersPositiveTest() throws DaoException, ServiceException {
        List<User> expected = List.of(user);
        when(userDao.findAll()).thenReturn(expected);
        List<User> actual = userService.findAllUsers();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void findAllUsersNegativeTest() throws DaoException, ServiceException {
        List<User> expected = List.of(user);
        when(userDao.findAll()).thenReturn(Collections.emptyList());
        List<User> actual = userService.findAllUsers();
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void findAllUsersServiceExceptionTest() throws ServiceException, DaoException {
        when(userDao.findAll()).thenThrow(DaoException.class);
        userService.findAllUsers();
    }

    @Test
    public void addUserPositiveTest() throws DaoException, ServiceException {
        Optional expected = Optional.empty();
        when(userDao.addUser(any(User.class), anyString())).thenReturn(true);
        Optional<String> actual = userService.addUser(user, password);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void addUserNegativeTest() throws DaoException, ServiceException {
        Optional expected = Optional.empty();
        when(userDao.addUser(any(User.class), anyString())).thenReturn(true);
        Optional<String> actual = userService.addUser(user, "incorrect pass");
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void addUserServiceExceptionTest() throws ServiceException, DaoException {
        when(userDao.addUser(any(User.class), anyString())).thenThrow(DaoException.class);
        userService.addUser(user, password);
    }

    @Test
    public void changePasswordPositiveTest() throws DaoException, ServiceException {
        Optional<String> expected = Optional.of("passwordChanged");
        when(userDao.changePassword(any(User.class), anyString())).thenReturn(true);
        Optional<String> actual = userService.changePassword(user, password);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void changePasswordNegativeTest() throws DaoException, ServiceException {
        Optional<String> expected = Optional.empty();
        when(userDao.changePassword(any(User.class), anyString())).thenReturn(true);
        Optional<String> actual = userService.changePassword(user, "incorrect pass");
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void changePasswordServiceExceptionTest() throws ServiceException, DaoException {
        when(userDao.changePassword(any(User.class), anyString())).thenThrow(DaoException.class);
        userService.changePassword(user, password);
    }

    @Test
    public void updateUserPositiveTest() throws DaoException, ServiceException {
        Optional<String> expected = Optional.of("userUpdated");
        when(userDao.updateUser(any(User.class))).thenReturn(true);
        Optional<String> actual = userService.updateUser(user);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void updateUserNegativeTest() throws DaoException, ServiceException {
        Optional<String> expected = Optional.empty();
        when(userDao.updateUser(any(User.class))).thenReturn(true);
        Optional<String> actual = userService.updateUser(user);
        Assert.assertNotEquals(actual, expected);
    }

    @Test(expectedExceptions = ServiceException.class)
    public void updateUserServiceExceptionTest() throws ServiceException, DaoException {
        when(userDao.updateUser(any(User.class))).thenThrow(DaoException.class);
        userService.updateUser(user);
    }
}
