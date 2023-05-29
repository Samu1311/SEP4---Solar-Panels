package Generators;

import java.math.BigDecimal;
import java.sql.*;
import java.util.Random;

public class PvGenerator implements Runnable {
  private static final String DB_URL = "jdbc:postgresql://snuffleupagus.db.elephantsql.com:5432/gnthefri";
  private static final String DB_USERNAME = "gnthefri";
  private static final String DB_PASSWORD = "qUk7llvLAcedh5ggsCeKZ8xJyqGC4sYn";

  private static final double MIN_SOLAR_FLUX = 500.0;
  private static final double MAX_SOLAR_FLUX = 1000.0;
  private static final double MIN_RESISTANCE = 10.0;
  private static final double MAX_RESISTANCE = 50.0;
  private static final double MIN_CURRENT = 5.0;
  private static final double MAX_CURRENT = 20.0;
  private static final double MIN_VOLTAGE = 200.0;
  private static final double MAX_VOLTAGE = 400.0;
  private static final double MIN_OVERPRODUCED_ENERGY = 0.0;
  private static final double MAX_OVERPRODUCED_ENERGY = 100.0;

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
    // Retrieve the series IDs from pv_series table
    String query = "SELECT pv_series_id FROM ejby_company.pv_series";
    try (Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {
      while (resultSet.next()) {
        int seriesId = resultSet.getInt("pv_series_id");
        generateLecture(connection, seriesId);
      }
    }
  }

  private void generateLecture(Connection connection, int seriesId) throws SQLException {
    double solarFlux = generateRandomValue(MIN_SOLAR_FLUX, MAX_SOLAR_FLUX);
    double resistance = generateRandomValue(MIN_RESISTANCE, MAX_RESISTANCE);
    double current = generateRandomValue(MIN_CURRENT, MAX_CURRENT);
    double voltage = generateRandomValue(MIN_VOLTAGE, MAX_VOLTAGE);
    double overproducedEnergy = generateRandomValue(MIN_OVERPRODUCED_ENERGY, MAX_OVERPRODUCED_ENERGY);
    double pOut = voltage * current;
    double pIn = pOut + overproducedEnergy;
    double efficiency = (pOut / pIn) * 100.0;

    String insertQuery = "INSERT INTO ejby_company.pv_measurements (timestamp, series_id, resistance, solar_flux, " +
        "current, voltage, overproduced_energy, Pout, Pin, efficiency) VALUES (current_timestamp, ?, ?, ?, ?, ?, " +
        "?, ?, ?, ?)";

    try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
      preparedStatement.setInt(1, seriesId);
      preparedStatement.setBigDecimal(2, BigDecimal.valueOf(resistance));
      preparedStatement.setBigDecimal(3, BigDecimal.valueOf(solarFlux));
      preparedStatement.setBigDecimal(4, BigDecimal.valueOf(current));
      preparedStatement.setBigDecimal(5, BigDecimal.valueOf(voltage));
      preparedStatement.setBigDecimal(6, BigDecimal.valueOf(overproducedEnergy));
      preparedStatement.setBigDecimal(7, BigDecimal.valueOf(pOut));
      preparedStatement.setBigDecimal(8, BigDecimal.valueOf(pIn));
      preparedStatement.setBigDecimal(9, BigDecimal.valueOf(efficiency));

      preparedStatement.executeUpdate();
      System.out.println("Generated lecture for series ID: " + seriesId);
    }
  }

  private double generateRandomValue(double minValue, double maxValue) {
    Random random = new Random();
    return minValue + (maxValue - minValue) * random.nextDouble();
  }

  public static void main(String[] args) {
    PvGenerator pvGenerator = new PvGenerator();
    Thread thread = new Thread(pvGenerator);
    thread.start();
  }
}
