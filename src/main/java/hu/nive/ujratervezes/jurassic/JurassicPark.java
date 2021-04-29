package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        String name = null;
        List<String> result = new ArrayList<>();
        String query = "SELECT breed FROM dinosaur WHERE actual > expected ORDER BY breed";
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                name = resultSet.getString("breed");
                result.add(name);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

    private Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
        } catch (SQLException e) {
            System.err.println("Database unable to connect.");
        }
        return connection;
    }

}
