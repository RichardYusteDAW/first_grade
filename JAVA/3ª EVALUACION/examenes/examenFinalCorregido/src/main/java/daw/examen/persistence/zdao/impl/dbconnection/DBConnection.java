package daw.examen.persistence.zdao.impl.dbconnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import daw.examen.common.PropertiesReader;
import daw.examen.common.tools.Color;
import org.apache.ibatis.jdbc.ScriptRunner;

public class DBConnection {

    private static DBConnection instance;
    private final Connection connection;

    private final String URL = PropertiesReader.getInstance().getProperty("spring.datasource.url");
    private final String USER = PropertiesReader.getInstance().getProperty("spring.datasource.username");
    private final String PASSWORD = PropertiesReader.getInstance()
            .getProperty("spring.datasource.password");

    private DBConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "Error connecting to the database" + e.getMessage() + Color.RESET);
        }
    }

    // Singleton
    public static DBConnection getInstance() {
        if (instance == null) {
            synchronized (DBConnection.class) {
                if (instance == null) {
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    // Script for creating the database
    public void executeScript(String scriptPath) {
        try (InputStream scriptStream = getClass().getClassLoader().getResourceAsStream(scriptPath)) {

            if (scriptStream == null)
                throw new RuntimeException("Script not found: " + scriptPath);

            ScriptRunner scriptRunner = new ScriptRunner(connection);
            scriptRunner.runScript(new InputStreamReader(scriptStream));
            System.out.println("Script executed successfully");
        } catch (IOException e) {
            System.out.println(String.format("Error executing script %s:\n    %s\n", scriptPath, e.getMessage()));
            throw new RuntimeException(e);
        }
    }
}