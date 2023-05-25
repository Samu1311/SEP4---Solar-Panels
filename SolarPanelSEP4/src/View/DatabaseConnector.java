package View;

import DAO.ManufacturerDAO;
import DAO.ModelDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static Connection connection;
    private ManufacturerDAO manufacturerDAO;
    private ModelDAO modelDAO;

    public DatabaseConnector() {
        String url = "jdbc:postgresql://snuffleupagus.db.elephantsql.com:5432/gnthefri";
        String user = "gnthefri";
        String password = "qUk7llvLAcedh5ggsCeKZ8xJyqGC4sYn";

        try {
            connection = DriverManager.getConnection(url, user, password);
            manufacturerDAO = new ManufacturerDAO(connection);
            modelDAO = new ModelDAO(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        return connection;
    }

    public ManufacturerDAO getManufacturerDAO() {
        return manufacturerDAO;
    }

    public ModelDAO getModelDAO() {
        return modelDAO;
    }

    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
       }
    }
}


