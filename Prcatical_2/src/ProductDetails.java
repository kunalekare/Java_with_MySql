import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class ProductDetails {

    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    public static void main(String[] args) {
        Connection connection = null;
        Statement statement = null;

        try {
            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish a connection to the database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Create a statement object to execute queries
            statement = connection.createStatement();

            // SQL query to create the Product_Details table
            String createTableSQL = "CREATE TABLE IF NOT EXISTS Product_Details ("
                    + "PRODUCT_ID VARCHAR(8) PRIMARY KEY,"
                    + "PRODUCT_NAME VARCHAR(30),"
                    + "CATEGORY VARCHAR(30),"
                    + "COST_PRICE DECIMAL(6,2),"
                    + "SELL_PRICE DECIMAL(6,2),"
                    + "WEIGHT DECIMAL(6,2),"
                    + "SHIPPING_CHARGE DECIMAL(4,2),"
                    + "MANUFACTURER VARCHAR(20),"
                    + "SUPPLIER VARCHAR(10)"
                    + ");";

            // Execute the query to create the table
            statement.executeUpdate(createTableSQL);
            System.out.println("Table Product_Details created successfully.");

            // SQL query to insert data into the Product_Details table
            String insertDataSQL = "INSERT INTO Product_Details (PRODUCT_ID, PRODUCT_NAME, CATEGORY, COST_PRICE, SELL_PRICE, WEIGHT, SHIPPING_CHARGE, MANUFACTURER, SUPPLIER)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

            PreparedStatement preparedStatement = connection.prepareStatement(insertDataSQL);

            // Add data to the table
            preparedStatement.setString(1, "P1234567");
            preparedStatement.setString(2, "Laptop");
            preparedStatement.setString(3, "Electronics");
            preparedStatement.setDouble(4, 50000.00);
            preparedStatement.setDouble(5, 55000.00);
            preparedStatement.setDouble(6, 1.50);
            preparedStatement.setDouble(7, 300.00);
            preparedStatement.setString(8, "Dell");
            preparedStatement.setString(9, "ABC Corp");

            preparedStatement.executeUpdate();
            System.out.println("Data inserted successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}