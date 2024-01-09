package com.test.Significant_Section;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class Export_Status {

    private static final String URL = "jdbc:mysql://private.colab.humanbrain.in:3306/HBA_V2";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Health#123";

    @Test
    public void testDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");

            try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
                System.out.println("MySQL database connected");

                exportStatus(connection, 1, "*****Export status-1 , process started , orange*****");
                exportStatus(connection, 2, "*****Export status-2 , process on going , yellow*****");
                exportStatus(connection, 3, "*****Export status-3 , Zoomify conversion , Blue*****");
                exportStatus(connection, 4, "*****Export status-4 , process completed , Green*****");
                exportStatus(connection, 5, "*****Export status-5 , unsignificant process ,Black*****");
                
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void exportStatus(Connection connection, int status, String statusMessage) {
        System.out.println(statusMessage);
        String query = "SELECT * FROM `section` WHERE export_status=" + status;

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String positionIndex = resultSet.getString("positionindex");
                String name = resultSet.getString("name");
                System.out.println("name: " + name + " ---------- positionindex: " + positionIndex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
