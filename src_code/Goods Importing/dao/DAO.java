package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DAO {
    public static Connection con;

    public DAO() {
        if (con == null) {
            // Kết nối SQL Server
            String dbUrl = "jdbc:sqlserver://localhost:1433;databaseName=Mini_Football_Field_Management;encrypt=false";
            String dbClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection(dbUrl, "sa", "123456");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
