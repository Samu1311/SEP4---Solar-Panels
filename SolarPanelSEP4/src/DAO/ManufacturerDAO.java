package DAO;

import Model.Manufacturer;
import View.DatabaseConnector;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ManufacturerDAO {
    private Connection connection;

    public ManufacturerDAO(Connection connection) {
        this.connection = DatabaseConnector.getConnection();
    }

    public List<Model.Manufacturer> getAllManufacturers() {
        List<Model.Manufacturer> manufacturers = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ejby_company.manufacturer");
            while (resultSet.next()) {
                Manufacturer manufacturer = new Manufacturer();
                manufacturer.setManufacturerId(resultSet.getInt("manufacturer_id"));
                manufacturer.setName(resultSet.getString("name"));
                manufacturer.setPhone(resultSet.getString("phone"));
                manufacturer.setEmail(resultSet.getString("email"));
                manufacturer.setCityId(resultSet.getInt("city_id"));

                manufacturers.add(manufacturer);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return manufacturers;
    }
    public void insertManufacturer(Manufacturer manufacturer) {
        try {
            String insertQuery = "INSERT INTO ejby_company.manufacturer (name, phone, email, city_id) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setString(1, manufacturer.getName());
            insertStatement.setString(2, manufacturer.getPhone());
            insertStatement.setString(3, manufacturer.getEmail());
            insertStatement.setInt(4, manufacturer.getCityId());

            int affectedRows = insertStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("New manufacturer inserted successfully.");
            } else {
                System.out.println("Failed to insert new manufacturer.");
            }

            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updateManufacturer(Manufacturer manufacturer) {
        try {
            String updateQuery = "UPDATE ejby_company.manufacturer SET name = ?, phone = ?, email = ?, city_id = ? WHERE manufacturer_id = ?";
            PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
            updateStatement.setString(1, manufacturer.getName());
            updateStatement.setString(2, manufacturer.getPhone());
            updateStatement.setString(3, manufacturer.getEmail());
            updateStatement.setInt(4, manufacturer.getCityId());
            updateStatement.setInt(5, manufacturer.getManufacturerId());

            int affectedRows = updateStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Manufacturer updated successfully.");
            } else {
                System.out.println("Failed to update manufacturer.");
            }
            updateStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteManufacturer(int manufacturerId) {
        try {
            String deleteQuery = "DELETE FROM ejby_company.manufacturer WHERE manufacturer_id = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, manufacturerId);

            int affectedRows = deleteStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("Manufacturer deleted successfully.");
            } else {
                System.out.println("Failed to delete manufacturer.");
            }

            deleteStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
}
}
}


