package View;

import Model.Manufacturer;
import Model.Model;
import Model.Manufacturer_log;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnector {
    private static DatabaseConnector instance;
    private Connection connection;
    private List<Manufacturer> manufacturers = new ArrayList<>();
    private List<Manufacturer_log> manufacturer_logs = new ArrayList<>();
    private Map<String, String> tableNames;
    private Manufacturer manufacturerInEdition;
    private Model modelInEdition;

    private List<Double> voltageData = new ArrayList<>();
    private LocalDate graphFromDate;
    private LocalDate graphToDate;

    private DatabaseConnector() {
        String url = "jdbc:postgresql://snuffleupagus.db.elephantsql.com:5432/gnthefri";
        String user = "gnthefri";
        String password = "qUk7llvLAcedh5ggsCeKZ8xJyqGC4sYn";

        try {
            connection = DriverManager.getConnection(url, user, password);
            initializeTableNames();
        } catch (SQLException e) {
            System.out.println("Connection failed. Error message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static synchronized DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
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

    private void initializeTableNames()
    {
        tableNames = new HashMap<>();
        tableNames.put("Thermo", "ejby_company.thermo_series");
        tableNames.put("Photovoltaic", "ejby_company.pv_series");
    }

    public boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM ejby_company.login_credentials WHERE username = ? AND password = ?";
        try ( PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next(); // Returns true if a matching user is found
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Authentication failed
    }

        private int getCityId(String city, String country) throws SQLException {
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
    public List<Manufacturer> getAllManufacturers() {
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


    public Manufacturer insertManufacturer(Manufacturer manufacturer) {
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

    public void deleteManufacturer(Manufacturer manufacturer) {
        try {
            int manufacturerId = manufacturer.getManufacturer_id();



            // Prepare the SQL statement for deleting the manufacturer
            String deleteQuery =  "DELETE FROM ejby_company.manufacturer WHERE manufacturer_id = ?";
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

            // Close the statements and database connection


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateManufacturer(Manufacturer manufacturer) {
        try {
            int manufacturer_id = manufacturer.getManufacturer_id();
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
                updateStatement.setInt(5, manufacturer_id);
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

    public Manufacturer getManufacturerInEdition()
    {
        return manufacturerInEdition;
    }

    public void setManufacturerInEdition(
        Manufacturer manufacturerInEdition)
    {
        this.manufacturerInEdition = manufacturerInEdition;
    }

    //Model Methods
    public List<Model> getAllModels() throws SQLException {
        List<Model> models = new ArrayList<>();

        try {
            String query = "SELECT m.model_id, m.name, m.price, m.dimensions,  m.solar_cell_area, m.type, ma.name AS manufacturer_name " +
                "FROM  ejby_company.model  m " +
                "INNER JOIN ejby_company.manufacturer ma ON m.manufacturer_id = ma.manufacturer_id";

            ResultSet rs = executeQuery(query);

            while (rs.next()) {
                int model_id = rs.getInt("model_id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                String dimensions = rs.getString("dimensions");
                String solarCellArea = rs.getString("solar_cell_area");
                String manufacturer_name = rs.getString("manufacturer_name");
                String panel_type = rs.getString("type");
                Model model = new Model(model_id, name, manufacturer_name,dimensions, panel_type, price,  solarCellArea);
                models.add(model);
            }

            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return models;
    }

    public void deleteModel(Model model) {
        try {
            int model_id = model.getModel_id();


            // Prepare the SQL statement for deleting the manufacturer
            String deleteQuery = "DELETE FROM ejby_company.model WHERE model_id = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, model_id);

            // Execute the delete statement
            int rowsDeleted = deleteStatement.executeUpdate();

            // Check if the deletion was successful
            if (rowsDeleted > 0) {
                System.out.println("Model deleted successfully from the database.");

                // Remove the manufacturer from the GUI table

            } else {
                System.out.println("Failed to delete modelfrom the database.");
            }




        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Model insertModel(Model model) {
        try {
            String name = model.getName();
            String manufacturer_name = model.getManufacturer_name();
            int price = model.getPrice();
            String dimensions = model.getDimensions();
            String solarCellArea = model.getSolar_cell_area();
            String panel_type = model.getPanel_type();

            String insertQuery = "INSERT INTO ejby_company.model (name, price, dimensions, solar_cell_area, type) " +
                "VALUES (?, ?, ?, ?, ?) RETURNING model_id";
            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

            insertStatement.setString(1, name);
            insertStatement.setInt(2, price);
            insertStatement.setString(3, dimensions);
            insertStatement.setString(4, solarCellArea);
            insertStatement.setString(5, panel_type);

            // Execute the SQL statement
            ResultSet generatedKeys = insertStatement.executeQuery();

            // Check if the insertion was successful
            if (generatedKeys.next()) {
                int model_id = generatedKeys.getInt("model_id");
                System.out.println("New model row inserted successfully. Model ID: " + model_id);
                Model newModel = new Model(model_id, name, manufacturer_name, dimensions, panel_type, price, solarCellArea);
                return newModel;
            } else {
                System.out.println("Failed to insert new model.");
            }


            // Close result sets and statements
            generatedKeys.close();
            insertStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Model getModelInEdition()
    {
        return modelInEdition;
    }

    public void setModelInEdition(Model modelInEdition)
    {
        this.modelInEdition = modelInEdition;
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

    public String getSeriesIdColumnName(String tableName) {
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
/*
    public List<Double> filterVoltageDataFromPhotovoltaic(LocalDate fromDate, LocalDate toDate) {
        List<Double> efficiencyData = new ArrayList<>();

        try {
            // Retrieve efficiency data from photovoltaic table grouped by day
            String pvQuery = "SELECT DATE(timestamp) AS day, AVG(efficiency) AS average_efficiency FROM ejby_company.pv_measurements WHERE DATE(timestamp) BETWEEN ? AND ? GROUP BY day";
            PreparedStatement pvStatement = connection.prepareStatement(pvQuery);
            pvStatement.setDate(1, Date.valueOf(fromDate));
            pvStatement.setDate(2, Date.valueOf(toDate));

            ResultSet pvResultSet = pvStatement.executeQuery();

            while (pvResultSet.next()) {
                double efficiency = pvResultSet.getDouble("average_efficiency");
                efficiencyData.add(efficiency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions
        }

        return efficiencyData;
    }

    public List<Double> filterVoltageDataFromThermo(LocalDate fromDate, LocalDate toDate) {
        List<Double> efficiencyData = new ArrayList<>();

        try {
            // Retrieve efficiency data from thermo table grouped by day
            String thermoQuery = "SELECT DATE(timestamp) AS day, AVG(efficiency) AS average_efficiency FROM ejby_company.thermo_measurements WHERE DATE(timestamp) BETWEEN ? AND ? GROUP BY day";
            PreparedStatement thermoStatement = connection.prepareStatement(thermoQuery);
            thermoStatement.setDate(1, Date.valueOf(fromDate));
            thermoStatement.setDate(2, Date.valueOf(toDate));

            ResultSet thermoResultSet = thermoStatement.executeQuery();

            while (thermoResultSet.next()) {
                double efficiency = thermoResultSet.getDouble("average_efficiency");
                efficiencyData.add(efficiency);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions
        }

        return efficiencyData;
    }


 */

    public List<Double> filterVoltageDataFromPhotovoltaic(LocalDate fromDate, LocalDate toDate) {
        List<Double> voltageData = new ArrayList<>();

        try {
            // Retrieve voltage data from photovoltaic table grouped by day
            String pvQuery = "SELECT DATE(timestamp) AS day, SUM(voltage) AS sum_voltage FROM ejby_company.pv_measurements WHERE DATE(timestamp) BETWEEN ? AND ? GROUP BY day";
            PreparedStatement pvStatement = connection.prepareStatement(pvQuery);
            pvStatement.setDate(1, Date.valueOf(fromDate));
            pvStatement.setDate(2, Date.valueOf(toDate));

            ResultSet pvResultSet = pvStatement.executeQuery();

            while (pvResultSet.next()) {
                double sumVoltage = pvResultSet.getDouble("sum_voltage");
                voltageData.add(sumVoltage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions
        }

        return voltageData;
    }

    public List<Double> filterVoltageDataFromThermo(LocalDate fromDate, LocalDate toDate) {
        List<Double> voltageData = new ArrayList<>();

        try {
            // Retrieve voltage data from thermo table grouped by day
            String thermoQuery = "SELECT DATE(timestamp) AS day, SUM(waterflow) AS sum_voltage FROM ejby_company.thermo_measurements WHERE DATE(timestamp) BETWEEN ? AND ? GROUP BY day";
            PreparedStatement thermoStatement = connection.prepareStatement(thermoQuery);
            thermoStatement.setDate(1, Date.valueOf(fromDate));
            thermoStatement.setDate(2, Date.valueOf(toDate));

            ResultSet thermoResultSet = thermoStatement.executeQuery();

            while (thermoResultSet.next()) {
                double sumVoltage = thermoResultSet.getDouble("sum_voltage");
                voltageData.add(sumVoltage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions
        }

        return voltageData;
    }



    public List<Double> getVoltageData() {
        return voltageData;
    }

    public void setVoltageData(List<Double> voltageData) {
        this.voltageData = voltageData;
    }


    public List<Manufacturer_log> getAllHistoricalManufacturer()  {

        try {
            String query = "SELECT m.timestamp, m.manufacturer_id, m.name, m.phone, m.email, c.city, m.info, m.action" +
                    "FROM  ejby_company.manufacturer_log m " + "JOIN ejby_company.city c ON m.city_id = c.city_id " ;

            ResultSet resultSet = executeQuery(query);
            System.out.println(resultSet);

            while (resultSet.next()) {
                Timestamp timestamp = resultSet.getTimestamp("timestamp");
                int manufacturer_id = resultSet.getInt("manufacturer_id");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String city_name = resultSet.getString("city_name");
                String info = resultSet.getString("info");
                String action = resultSet.getString("action");

                Manufacturer_log manufacturer_log = new Manufacturer_log(timestamp, manufacturer_id, name, phone, email, city_name,info, action );
                DatabaseConnector.getInstance().manufacturer_logs.add(manufacturer_log);

            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return manufacturer_logs;
    }

    public LocalDate getGraphFromDate() {
        return graphFromDate;
    }

    public void setGraphFromDate(LocalDate graphFromDate) {
        this.graphFromDate = graphFromDate;
    }

    public LocalDate getGraphToDate() {
        return graphToDate;
    }

    public void setGraphToDate(LocalDate graphToDate) {
        this.graphToDate = graphToDate;
    }

    public List<Manufacturer_log> getManufacturer_logs() {
        return manufacturer_logs;
    }

    public void setManufacturer_logs(List<Manufacturer_log> manufacturer_logs) {
        this.manufacturer_logs = manufacturer_logs;
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
