package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            String url = "jdbc:derby://localhost:1527/dw";
            String username = "root";
            String password = "felipe10";


            try {
                Class.forName("com.mysql.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);

            } catch (SQLException e) {
                throw new IllegalStateException("Cannot connect the database!", e);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DbUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
            return connection;
        }

    }
}
