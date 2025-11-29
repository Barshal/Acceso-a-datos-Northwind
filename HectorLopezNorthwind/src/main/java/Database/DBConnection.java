package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() {
        System.out.println("getConnection");
        if (connection == null) {
            createConnection();}
        return connection;
    }

    private static void createConnection() {
        String user = "root";
        String pass = "";
        String url = "jdbc:mysql://localhost:3306/northwind";
        try {
            System.out.println("createConnection");
            connection = DriverManager.getConnection(url,user,pass);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.print("Fallo en la conexion");
            System.err.println(e.getMessage());
        }
    }

}
