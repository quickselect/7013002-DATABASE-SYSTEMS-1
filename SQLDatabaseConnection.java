import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDatabaseConnection {
  // Connect to your database.
  // Replace server name, username, and password with your credentials
  public static void main(String[] args) {
    String connectionUrl =
        "jdbc:sqlserver://localhost:1434;"
        + "DatabaseName=MusteriSiparis;"
        + "encrypt=false;"
        + "integratedSecurity=true;";
    // Using windows authentication: integratedSecurity=true;
    // if you don't write that; it will use sql auth instead

    try {
      // Load the JDBC driver
      Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

      ResultSet resultSet = null;

      // Establish the connection
      try (Connection connection = DriverManager.getConnection(connectionUrl);
           Statement statement = connection.createStatement();) {

        // Create and execute a SELECT SQL statement.
        String selectSql = "select top 10 * from Musteri";
        resultSet = statement.executeQuery(selectSql);

        // Print results from select statement
        while (resultSet.next()) {
          System.out.println(resultSet.getString(1) + " -> " +
              resultSet.getString(2) + " " +
              resultSet.getString(3) + " -> " +
              resultSet.getString(4));
        }
      }
    }
    // Handle any errors that may have occurred.
    catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}
