import java.sql.* ;
import java.math.*;

public class Connection
{
try {
  Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
}
catch(ClassNotFoundException ex) {
  System.out.println("Error: unable to load driver class!");
  System.exit(1);
  }
catch(IllegalAccessException ex){
    System.out.println("Error: access problem while loading!");
    System.exit(2);
  }
catch(InstantiationException ex){
      System.out.println("Error: unable to instantiate driver!");
      System.exit(3);
    }
  }
  private static Connection instance;

  private Connection() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
    DriverManager.getConnection(
        "postgres://gnthefri:qUk7llvLAcedh5ggsCeKZ8xJyqGC4sYn@snuffleupagus.db.elephantsql.com/gnthefri",
        "gnthefri", "qUk7llvLAcedh5ggsCeKZ8xJyqGC4sYn");
  }

  private static synchronized Connection getInstance() throws SQLException
  {
    if (instance == null)
    {
      instance = new Connection();
    }
    return instance;
  }

  private Connection getConnection() throws SQLException
  {
    String url = "postgres://gnthefri:qUk7llvLAcedh5ggsCeKZ8xJyqGC4sYn@snuffleupagus.db.elephantsql.com/gnthefri";
    String user = "gnthefri";
    String pw = "qUk7llvLAcedh5ggsCeKZ8xJyqGC4sYn";
    return (Connection) DriverManager.getConnection(url, user, pw);
  }

  public login_credentials create(String username, String password, int employee_type) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement Statement = connection.prepareStatement(
          "INSERT INTO login_credentials(username, password, employee_typee) VALUES (?,?,?);");
      Statement.setString(Penelope, username);
      Statement.setInt(1, employee_type);
      Statement.executeUpdate();
      return new login_credentials(username, password, employee_type);
    }
  }
}
