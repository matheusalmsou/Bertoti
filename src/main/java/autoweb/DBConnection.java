package autoweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;
    private static final String url = "jdbc:mysql://127.0.0.1:3306/autoweb";
    private static final String user = "root";
    private static final String pass = "13MySql!";

    public static Connection getConnection() {
        if(connection == null) {
            try {

                Class.forName("com.mysql.cj.jdbc.Driver");

                connection = DriverManager.getConnection(url, user, pass);

            } catch (ClassNotFoundException | SQLException ex) {
                ex.printStackTrace();
            }
        }

            return connection;
    }
}
