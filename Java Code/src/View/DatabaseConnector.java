package View;

import java.sql.*;

public class DatabaseConnector {
    private Connection connection;

    public DatabaseConnector() {
        String url = "jdbc:postgresql://snuffleupagus.db.elephantsql.com:5432/gnthefri";
        String user = "gnthefri";
        String password = "qUk7llvLAcedh5ggsCeKZ8xJyqGC4sYn";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Connection failed. Error message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void testConnection() {
        if (connection != null) {
            System.out.println("Connection to the database established successfully.");
        } else {
            System.out.println("Failed to establish connection to the database.");
        }
    }

    public ResultSet executeQuery(String sql) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Query execution failed. Error message: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public int executeUpdate(String sql) {
        try {
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(sql);
            return rowsAffected;
        } catch (SQLException e) {
            System.out.println("Query execution failed. Error message: " + e.getMessage());
            e.printStackTrace();
            return -1;
        }
    }

    public void printManufacturersTable() {
        try {
            // Execute the query to retrieve the manufacturers data
            String query = "SELECT m.manufacturer_id, m.name, m.phone, m.email, c.city, c.postal_code, cn.country " +
                    "FROM ejby_company.manufacturer m " +
                    "JOIN ejby_company.city c ON m.city_id = c.city_id " +
                    "JOIN ejby_company.country cn ON c.country_id = cn.country_id";
            ResultSet resultSet = executeQuery(query);

            // Print the table headers
            System.out.println("Manufacturer ID\tName\t\tPhone\t\tEmail\t\t\tCity\t\tPostal Code\tCountry");
            System.out.println("------------------------------------------------------------------------------");

            // Iterate through the result set and print the data
            while (resultSet.next()) {
                int manufacturerId = resultSet.getInt("manufacturer_id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String city = resultSet.getString("city");
                int postalCode = resultSet.getInt("postal_code");
                String country = resultSet.getString("country");

                System.out.printf("%d\t\t%s\t\t%s\t\t%s\t\t%s\t\t%d\t\t%s\n", manufacturerId, name, phone, email, city, postalCode, country);
            }

            // Close the result set
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printPhotovoltaicSeriesTable() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "SELECT s.series_id, p.voltage, p.current, p.resistance, p.solar_flux, p.efficiency " +
                            "FROM ejby_company.series s " +
                            "JOIN ejby_company.pv_measurements p ON s.series_id = p.series_id " +
                            "JOIN ( " +
                            "   SELECT series_id, MAX(date || ' ' || time) AS max_datetime " +
                            "   FROM ejby_company.pv_measurements " +
                            "   GROUP BY series_id " +
                            ") m ON p.series_id = m.series_id AND (p.date || ' ' || p.time) = m.max_datetime"
            );

            System.out.println("Series ID\tVoltage\t\tCurrent\t\tResistance\tSolar Flux\tEfficiency");
            System.out.println("---------------------------------------------------------------");

            while (resultSet.next()) {
                int seriesId = resultSet.getInt("series_id");
                double voltage = resultSet.getDouble("voltage");
                double current = resultSet.getDouble("current");
                double resistance = resultSet.getDouble("resistance");
                double solarFlux = resultSet.getDouble("solar_flux");
                double efficiency = resultSet.getDouble("efficiency");

                System.out.printf("%d\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f\t\t%.2f\n",
                        seriesId, voltage, current, resistance, solarFlux, efficiency);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertManufacturer(String name, String phone, String email, String country, String city) {
        try {
            // Get the country_id based on the provided country name
            String countryQuery = "SELECT country_id FROM ejby_company.country WHERE country = ?";
            PreparedStatement countryStatement = connection.prepareStatement(countryQuery);
            countryStatement.setString(1, country);
            ResultSet countryResultSet = countryStatement.executeQuery();

            int countryId;
            if (countryResultSet.next()) {
                countryId = countryResultSet.getInt("country_id");
            } else {
                System.out.println("Country not found: " + country);
                return;
            }

            // Get the city_id and postal_code based on the provided city name
            String cityQuery = "SELECT city_id, postal_code FROM ejby_company.city WHERE city = ?";
            PreparedStatement cityStatement = connection.prepareStatement(cityQuery);
            cityStatement.setString(1, city);
            ResultSet cityResultSet = cityStatement.executeQuery();

            int cityId;
            int postalCode;
            if (cityResultSet.next()) {
                cityId = cityResultSet.getInt("city_id");
                postalCode = cityResultSet.getInt("postal_code");
            } else {
                System.out.println("City not found: " + city);
                return;
            }

            // Prepare the SQL statement for inserting a new manufacturer
            String insertQuery = "INSERT INTO ejby_company.manufacturer (name, phone, email, city_id) VALUES (?, ?, ?, ?)";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, name);
            insertStatement.setString(2, phone);
            insertStatement.setString(3, email);
            insertStatement.setInt(4, cityId);

            // Execute the SQL statement
            int affectedRows = insertStatement.executeUpdate();

            // Check if the insertion was successful
            if (affectedRows > 0) {
                // Retrieve the generated key
                ResultSet generatedKeys = insertStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int manufacturerId = generatedKeys.getInt(1);
                    System.out.println("New manufacturer row inserted successfully. Manufacturer ID: " + manufacturerId);
                    System.out.println("Postal Code: " + postalCode);
                } else {
                    System.out.println("Failed to retrieve generated manufacturer ID.");
                }
            } else {
                // Manufacturer with the same key already exists, retrieve the existing manufacturer_id
                String existingManufacturerIdQuery = "SELECT manufacturer_id FROM ejby_company.manufacturer WHERE name = ? AND phone = ? AND email = ? AND city_id = ?";
                PreparedStatement existingManufacturerIdStatement = connection.prepareStatement(existingManufacturerIdQuery);
                existingManufacturerIdStatement.setString(1, name);
                existingManufacturerIdStatement.setString(2, phone);
                existingManufacturerIdStatement.setString(3, email);
                existingManufacturerIdStatement.setInt(4, cityId);
                ResultSet existingManufacturerIdResultSet = existingManufacturerIdStatement.executeQuery();

                if (existingManufacturerIdResultSet.next()) {
                    int existingManufacturerId = existingManufacturerIdResultSet.getInt("manufacturer_id");
                    System.out.println("Manufacturer already exists. Manufacturer ID: " + existingManufacturerId);
                    System.out.println("Postal Code: " + postalCode);
                } else {
                    System.out.println("Failed to retrieve existing manufacturer ID.");
                }
            }

            // Close result sets, statements, and connections
            countryResultSet.close();
            countryStatement.close();
            cityResultSet.close();
            cityStatement.close();
            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
 }
}
}
