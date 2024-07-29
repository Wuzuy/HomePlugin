package br.com.wuzuy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataBaseManager {

    private Connection connection;
    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    public DataBaseManager(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public void connect() throws SQLException {
        String url = String.format("jdbc:mysql://%s:%d/%s", host, port, database);
        connection = DriverManager.getConnection(url, username, password);
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String getHomeLocation(String playerName) throws SQLException {
        String query = "SELECT home_location FROM homes WHERE player_name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, playerName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("home_location");
            } else {
                return null; // Nenhuma home encontrada
            }
        }
    }

    public void setHomeLocation(String playerName, String location) throws SQLException {
        String query = "INSERT INTO homes (player_name, home_location) VALUES (?, ?) " +
                "ON DUPLICATE KEY UPDATE home_location = VALUES(home_location)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, playerName);
            statement.setString(2, location);
            statement.executeUpdate();
        }
    }
}
