package View;

public class TestConnections {
  public static void main(String[] args) {
    DatabaseConnector connector = new DatabaseConnector();
    connector.testConnection();

    // Print the Manufacturers table
    connector.printManufacturersTable();

    connector.closeConnection();
  }
}
