package com.example.demo.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionManager {
    public static void closeAll(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void close(Connection connection, PreparedStatement statement) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
