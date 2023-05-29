package View;

import Model.Manufacturer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.sql.DriverManager.getConnection;

public class DatabaseConnector {
    private static Connection connection;
    private int transactionItemId;
    private Map<String, String> tableNames;

    public DatabaseConnector() {
        String url = "jdbc:postgresql://snuffleupagus.db.elephantsql.com:5432/gnthefri";
        String user = "gnthefri";
        String password = "qUk7llvLAcedh5ggsCeKZ8xJyqGC4sYn";

        try {
            connection = getConnection(url, user, password);
            initializeTableNames();
        } catch (SQLException e) {
            System.out.println("Connection failed. Error message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initializeTableNames() {
        tableNames = new HashMap<>();
        tableNames.put("Thermo", "ejby_company.thermo_series");
        tableNames.put("Photovoltaic", "ejby_company.pv_series");
    }

    public void testConnection() {
        if (connection != null) {
            System.out.println("Connection to the database established successfully.");
        } else {
            System.out.println("Failed to establish connection to the database.");
        }
    }

    public static ResultSet executeQuery(String sql) {
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

    public void setTransactionItemId(int itemId) {
        this.transactionItemId = itemId;
    }

    public int getTransactionItemId() {
        return transactionItemId;
}

    private static int getCityId(String city, String country) throws SQLException {
        String query = "SELECT city_id FROM ejby_company.city WHERE city = ? AND country_id = (SELECT country_id FROM ejby_company.country WHERE country = ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, city);
        statement.setString(2, country);
        ResultSet resultSet = statement.executeQuery();

        int cityId = -1;
        if (resultSet.next()) {
            cityId = resultSet.getInt("city_id");
        }

        resultSet.close();
        statement.close();

        return cityId;
    }
// Manufacturer Methods
    public static List<Manufacturer> getAllManufacturers() {
        List<Manufacturer> manufacturers = new ArrayList<>();


        try {
            // Execute the query to retrieve the manufacturers data
            String query = "SELECT m.manufacturer_id, m.name, m.phone, m.email, c.city, c.postal_code, cn.country " +
                    "FROM ejby_company.manufacturer m " +
                    "JOIN ejby_company.city c ON m.city_id = c.city_id " +
                    "JOIN ejby_company.country cn ON c.country_id = cn.country_id";
            ResultSet resultSet = executeQuery(query);

            // Iterate through the result set and create Manufacturer objects
            while (resultSet.next()) {
                int manufacturerId = resultSet.getInt("manufacturer_id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String city = resultSet.getString("city");
                int postalCode = resultSet.getInt("postal_code");
                String country = resultSet.getString("country");

                Manufacturer manufacturer = new Manufacturer(manufacturerId, name, phone, email, city, postalCode, country);
                manufacturers.add(manufacturer);
            }

            // Close the result set
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return manufacturers;
    }

    public static Manufacturer insertManufacturer(Manufacturer manufacturer) {
        try {
            String name = manufacturer.getName();
            String phone = manufacturer.getPhone();
            String email = manufacturer.getEmail();
            String country = manufacturer.getCountry_name();
            String city = manufacturer.getCity_name();

            // Check if the country exists
            String countryQuery = "SELECT country_id FROM ejby_company.country WHERE country = ?";
            PreparedStatement countryStatement = connection.prepareStatement(countryQuery);
            countryStatement.setString(1, country);
            ResultSet countryResultSet = countryStatement.executeQuery();

            int countryId;
            if (countryResultSet.next()) {
                countryId = countryResultSet.getInt("country_id");
            } else {
                System.out.println("Country not found: " + country);
                System.out.println("Please select an existing country.");
                return null;
            }

            // Check if the city exists for the given country
            String cityQuery = "SELECT city_id FROM ejby_company.city WHERE city = ? AND country_id = ?";
            PreparedStatement cityStatement = connection.prepareStatement(cityQuery);
            cityStatement.setString(1, city);
            cityStatement.setInt(2, countryId);
            ResultSet cityResultSet = cityStatement.executeQuery();

            int cityId;
            if (cityResultSet.next()) {
                cityId = cityResultSet.getInt("city_id");
            } else {
                System.out.println("City not found: " + city);
                System.out.println("Please select an existing city for the country: " + country);
                return null;
            }

                    // Prepare the SQL statement for inserting a new manufacturer
                    String insertQuery = "INSERT INTO ejby_company.manufacturer (name, phone, email, city_id) " +
                            "SELECT ?, ?, ?, city.city_id " +
                            "FROM ejby_company.city " +
                            "INNER JOIN ejby_company.country ON city.country_id = country.country_id " +
                            "WHERE city.city = ? AND country.country = ? " +
                            "ON CONFLICT DO NOTHING " +
                            "RETURNING manufacturer_id";
                    PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
                    insertStatement.setString(1, name);
                    insertStatement.setString(2, phone);
                    insertStatement.setString(3, email);
                    insertStatement.setString(4, city);
                    insertStatement.setString(5, country);

                    // Execute the SQL statement
                    ResultSet generatedKeys = insertStatement.executeQuery();

                    // Check if the insertion was successful
                    if (generatedKeys.next()) {
                        int manufacturerId = generatedKeys.getInt("manufacturer_id");
                        System.out.println("New manufacturer row inserted successfully. Manufacturer ID: " + manufacturerId);
                        Manufacturer newManufacturer = new Manufacturer(manufacturerId, name, phone, email, city, country);
                        return newManufacturer;
                    } else {
                        System.out.println("Failed to insert new manufacturer.");
                    }

                    // Close result sets and statements
                    generatedKeys.close();
                    insertStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
  return null;
            }

    public static void deleteManufacturer(Manufacturer manufacturer) {
        try {
            int manufacturerId = manufacturer.getManufacturer_id();

            // Prepare the SQL statement for deleting the manufacturer
            String deleteQuery = "DELETE FROM ejby_company.manufacturer WHERE manufacturer_id = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, manufacturerId);

            // Execute the delete statement
            int rowsDeleted = deleteStatement.executeUpdate();

            // Check if the deletion was successful
            if (rowsDeleted > 0) {
                System.out.println("Manufacturer deleted successfully from the database.");

                // Remove the manufacturer from the GUI table

            } else {
                System.out.println("Failed to delete manufacturer from the database.");
            }

            // Close the delete statement and database connection
            deleteStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
}
    }

    public static boolean updateManufacturer(Manufacturer manufacturer) {
        try {
            int manufacturerId = manufacturer.getManufacturer_id();
            String name = manufacturer.getName();
            String phone = manufacturer.getPhone();
            String email = manufacturer.getEmail();
            String country = manufacturer.getCountry_name();
            String city = manufacturer.getCity_name();

            // Fetch the city_id based on the updated city name
            int cityId = getCityId(city, country);

            if (cityId != -1) {
                // Manufacturer exists, perform update
                String updateQuery = "UPDATE ejby_company.manufacturer SET name = ?, phone = ?, email = ?, city_id = ? WHERE manufacturer_id = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                updateStatement.setString(1, name);
                updateStatement.setString(2, phone);
                updateStatement.setString(3, email);
                updateStatement.setInt(4, cityId);
                updateStatement.setInt(5, manufacturerId);
                updateStatement.executeUpdate();
                System.out.println("Manufacturer updated successfully.");
                updateStatement.close();
            } else {
                System.out.println("City not found: " + city + " in country: " + country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
}
        return true;
    }

    //Series Methods
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

    private String getSeriesIdColumnName(String tableName) {
        if (tableName.equals("Thermo")) {
            return "thermo_series_id";
        } else if (tableName.equals("Photovoltaic")) {
            return "pv_series_id";
        } else {
            throw new IllegalArgumentException("Invalid table name: " + tableName);
        }
    }
    public int getMaxSeriesId(String tableName) {
        String seriesIdColumnName = getSeriesIdColumnName(tableName);
        String query = "SELECT MAX(" + seriesIdColumnName + ") " + tableName;

        try (
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getMinSeriesId(String tableName) {
        String seriesIdColumnName = getSeriesIdColumnName(tableName);
        String query = "SELECT MIN(" + seriesIdColumnName + ") " + tableName;
        try (
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }


    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
                System.out.println("Connection to the database closed successfully.");
            }
        } catch (SQLException e) {
            System.out.println("Error while closing the connection. Error message: " + e.getMessage());
            e.printStackTrace();
}
}
}
