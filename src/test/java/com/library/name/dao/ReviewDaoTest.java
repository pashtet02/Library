/*
package com.library.name.dao;

import com.library.name.entity.Review;
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
public class ReviewDaoTest {

    Review review = new Review();

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

        review.setId(0);
    }

    @Test
    public void testGetById() throws SQLException {
        ReviewDao reviewDao = ReviewDao.getInstance();
        reviewDao.getById(mockConn, 13);

        verify(mockConn, times(1)).prepareStatement(anyString());
        //verify(mockPreparedStmt, times(1)).setInt(anyInt(), anyInt());
        verify(mockPreparedStmt, times(1)).executeQuery();
        verify(mockResultSet, times(1)).next();
        verify(mockResultSet, times(1)).close();
        verify(mockPreparedStmt, times(1)).close();
    }
}
*/
