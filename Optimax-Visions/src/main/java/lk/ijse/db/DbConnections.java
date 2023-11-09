package lk.ijse.db;

import java.sql.*;

public class DbConnections {
    private static DbConnections dbConnection;
    private static Connection connection;

    public DbConnections() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/visioncare",
                "root",
                "Kavindu@1125"
        );
    }

    public static DbConnections getInstance() throws SQLException {
        return (null == dbConnection) ? dbConnection = new DbConnections() : dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }

    public static boolean setDetails(String sql) throws SQLException {
        connection = getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        boolean isSaved = pstm.executeUpdate() > 0;
        return isSaved;
    }

    public static String[][] getDetails(String name, int columnCount) throws SQLException {
        connection = getInstance().getConnection();
        int rowsCount = checkRowsCount(name);
        String[][] details = new String[rowsCount][columnCount];

        String sql = "select * from "+name;

        Statement statement =  connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        int i = 0;
        while (resultSet.next()){
            for (int j = 0; j < columnCount; j++) {
                details[i][j] = resultSet.getString(j+1);
            }
            i++;
        }
        return details;
    }

    public static String[][] getDetails(String name, int column, String sql, String date) throws SQLException {
        connection = getInstance().getConnection();

        int rowsCount = checkRowsCount(name, "date", date);

        String[][] details = new String[rowsCount][column];

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();

        int i = 0;
        while (resultSet.next()) {
            for (int j = 0; j < column; j++) {
                details[i][j] = resultSet.getString(j + 1);
            }
            i++;
        }
        return details;
    }

    public static int checkRowsCount(String tableName, String columnName, String value) throws SQLException {
        connection = getInstance().getConnection();

        int rows = 0;

        String sql = "SELECT COUNT(*) FROM " + tableName + " WHERE " + columnName + " = '"+ value +"'";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            rows = resultSet.getInt(1);
        }
        return rows;
    }

    public static int checkRowsCount(String name) throws SQLException {
        connection = getInstance().getConnection();

        int rows = 0;

        String sql = "SELECT COUNT(*) FROM "+name;

        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) rows = resultSet.getInt(1);
        return rows;
    }
}