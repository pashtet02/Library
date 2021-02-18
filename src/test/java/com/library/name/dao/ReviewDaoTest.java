/*
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RewievDaoTest {

    Account acc = new Account("login", "password", "email");

    @Mock
    Connection mockConn;
    @Mock
    PreparedStatement mockPreparedStmt;
    @Mock
    ResultSet mockResultSet;

    @Before
    public void setUp() throws SQLException {
        when(mockConn.prepareStatement(anyString())).thenReturn(mockPreparedStmt);
        when(mockConn.prepareStatement(anyString(), anyInt())).thenReturn(mockPreparedStmt);
        doNothing().when(mockPreparedStmt).setInt(anyInt(), anyInt());
        doNothing().when(mockPreparedStmt).setString(anyInt(), anyString());
        when(mockPreparedStmt.executeUpdate()).thenReturn(1);
        when(mockPreparedStmt.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(Boolean.FALSE);

        acc.setName("name");
        acc.setSurname("surname");
        acc.setPhoneNumber("8798709");
        acc.setId(0);
    }

    @Test
    public void testFindAccountById() throws SQLException {
        AccountDao dao = new MySqlAccountDao();
        dao.findAccountById(mockConn, 3);

        verify(mockConn, times(1)).prepareStatement(anyString());
        verify(mockPreparedStmt, times(1)).setInt(anyInt(), anyInt());
        verify(mockPreparedStmt, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
        verify(mockResultSet, times(1)).close();
        verify(mockPreparedStmt, times(1)).close();
    }*/
