package Generators;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Random;

public class ThermoGenerator implements Runnable {
  private static final String DB_URL = "jdbc:postgresql://snuffleupagus.db.elephantsql.com:5432/gnthefri";
  private static final String DB_USERNAME = "gnthefri";
  private static final String DB_PASSWORD = "qUk7llvLAcedh5ggsCeKZ8xJyqGC4sYn";

  private static final double MIN_COLLECTION_TIME_PERIOD = 10.0;
  private static final double MAX_COLLECTION_TIME_PERIOD = 30.0;
  private static final double MIN_COLLECTED_WATER_MASS = 5.0;
  private static final double MAX_COLLECTED_WATER_MASS = 15.0;
  private static final double MIN_OUTDOOR_TEMPERATURE = -10.0;
  private static final double MAX_OUTDOOR_TEMPERATURE = 30.0;
  private static final double MIN_HOT_WATER_TEMPERATURE = 40.0;
  private static final double MAX_HOT_WATER_TEMPERATURE = 80.0;
  private static final double MIN_COLD_WATER_TEMPERATURE = 10.0;
  private static final double MAX_COLD_WATER_TEMPERATURE = 30.0;
  private static final double MIN_SOLAR_FLUX = 500.0;
  private static final double MAX_SOLAR_FLUX = 1000.0;
  private static final double MIN_WATER_FLOW = 5.0;
  private static final double MAX_WATER_FLOW = 20.0;
  private static final double MIN_POUT = 200.0;
  private static final double MAX_POUT = 500.0;
  private static final double MIN_PIN = 200.0;
  private static final double MAX_PIN = 600.0;
  private static final double MIN_EFFICIENCY = 80.0;
  private static final double MAX_EFFICIENCY = 95.0;

  @Override
  public void run() {
    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
      while (true) {
        generateLectures(connection);
        Thread.sleep(60000); // Sleep for 1 minute
      }
    } catch (InterruptedException | SQLException e) {
      e.printStackTrace();
    }
  }

  private void generateLectures(Connection connection) throws SQLException {
    // Retrieve the series IDs from thermo_series table
    String query = "SELECT thermo_series_id FROM ejby_company.thermo_series";
    try (Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {
      while (resultSet.next()) {
        int seriesId = resultSet.getInt("thermo_series_id");
        generateLecture(connection, seriesId);
      }
    }
  }

  private void generateLecture(Connection connection, int seriesId) throws SQLException {
    double collectionTimePeriod = generateRandomValue(MIN_COLLECTION_TIME_PERIOD, MAX_COLLECTION_TIME_PERIOD);
    double collectedWaterMass = generateRandomValue(MIN_COLLECTED_WATER_MASS, MAX_COLLECTED_WATER_MASS);
    double outdoorTemperature = generateRandomValue(MIN_OUTDOOR_TEMPERATURE, MAX_OUTDOOR_TEMPERATURE);
    double hotWaterTemperature = generateRandomValue(MIN_HOT_WATER_TEMPERATURE, MAX_HOT_WATER_TEMPERATURE);
    double coldWaterTemperature = generateRandomValue(MIN_COLD_WATER_TEMPERATURE, MAX_COLD_WATER_TEMPERATURE);
    double solarFlux = generateRandomValue(MIN_SOLAR_FLUX, MAX_SOLAR_FLUX);
    double waterFlow = generateRandomValue(MIN_WATER_FLOW, MAX_WATER_FLOW);
    double pOut = generateRandomValue(MIN_POUT, MAX_POUT);
    double pIn = generateRandomValue(MIN_PIN, MAX_PIN);
    double efficiency = generateRandomValue(MIN_EFFICIENCY, MAX_EFFICIENCY);

    String insertQuery = "INSERT INTO ejby_company.thermo_measurements " +
        "(series_id, collection_time_period, collected_water_mass, outdoor_temperature, " +
        "hot_water_temperature, cold_water_temperature, solar_flux, water_flow, Pout, Pin, Efficiency) " +
        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
      preparedStatement.setInt(1, seriesId);
      preparedStatement.setBigDecimal(2, BigDecimal.valueOf(collectionTimePeriod));
      preparedStatement.setBigDecimal(3, BigDecimal.valueOf(collectedWaterMass));
      preparedStatement.setBigDecimal(4, BigDecimal.valueOf(outdoorTemperature));
      preparedStatement.setBigDecimal(5, BigDecimal.valueOf(hotWaterTemperature));
      preparedStatement.setBigDecimal(6, BigDecimal.valueOf(coldWaterTemperature));
      preparedStatement.setBigDecimal(7, BigDecimal.valueOf(solarFlux));
      preparedStatement.setBigDecimal(8, BigDecimal.valueOf(waterFlow));
      preparedStatement.setBigDecimal(9, BigDecimal.valueOf(pOut));
      preparedStatement.setBigDecimal(10, BigDecimal.valueOf(pIn));
      preparedStatement.setBigDecimal(11, BigDecimal.valueOf(efficiency));

      preparedStatement.executeUpdate();
      System.out.println("Generated lecture for series ID: " + seriesId);
    }
  }

  private double generateRandomValue(double minValue, double maxValue) {
    Random random = new Random();
    return minValue + (maxValue - minValue) * random.nextDouble();
  }

  public static void main(String[] args) {
    ThermoGenerator thermoGenerator = new ThermoGenerator();
    Thread thread = new Thread(thermoGenerator);
    thread.start();
  }
}


