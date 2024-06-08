package daw.examen.persistence.zdao.impl.dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import daw.examen.common.tools.Color;

public class RawSQL {

    private static final Connection connection = DBConnection.getInstance().getConnection();

    // Ejecutar un SELECT
    public static ResultSet select(String sql, List<Object> params) {

        try (PreparedStatement preparedStatement = setParameters(sql, params)) {
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "RAW: Error executing SQL query: " + sql + Color.RESET);
        }
    }

    // Ejecutar un INSERT
    public static Object insert(String sql, List<Object> params, boolean returnGeneratedKeys) {

        try (PreparedStatement preparedStatement = setParameters(sql, params)) {

            preparedStatement.executeUpdate();

            if (!returnGeneratedKeys) {
                return null;
            }
            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                return resultSet.getObject(1);
            } else {
                throw new RuntimeException(Color.RED + "RAW: Unable to retrieve the last generated ID" + Color.RESET);
            }

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "RAW: Error executing SQL query:" + sql + Color.RESET);
        }
    }

    // Ejecutar un UPDATE
    public static int update(String sql, List<Object> params) {
        return RawSQL.statement(sql, params);
    }

    // Ejecutar un DELETE
    public static int delete(String sql, List<Object> params) {
        return RawSQL.statement(sql, params);
    }

    // Ejecutar un statement
    private static int statement(String sql, List<Object> params) {

        try (PreparedStatement preparedStatement = setParameters(sql, params)) {
            return preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "RAW: Error executing SQL query: " + sql + Color.RESET);
        }
    }

    // Establecer los parámetros
    private static PreparedStatement setParameters(String sql, List<Object> values) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            if (values != null) {
                for (int i = 0; i < values.size(); i++) {
                    Object value = values.get(i);
                    preparedStatement.setObject(i + 1, value);
                }
            }

            return preparedStatement;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    // Realizar un commit
    public static void commit() {

        try {
            connection.commit();

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "RAW: Error committing the transaction" + Color.RESET);
        }
    }

    // Realizar un rollback
    public static void rollback() {

        try {
            connection.rollback();

        } catch (SQLException e) {
            throw new RuntimeException(Color.RED + "RAW:  Error rolling back the transaction" + Color.RESET);
        }
    }
}