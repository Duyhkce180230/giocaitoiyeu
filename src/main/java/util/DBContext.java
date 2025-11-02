package util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author
 */
public class DBContext {

    public Connection conn = null;

//    public DBContext() {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String dbURL = "jdbc:sqlserver://localhost:1433;"
//                    + "databaseName=GioCai;"
//                    + "user=sa;"
//                    + "password=123456;"
//                    + "encrypt=true;trustServerCertificate=true;";
//            conn = DriverManager.getConnection(dbURL);
//            if (conn != null) {
//                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
//                System.out.println("Driver name: " + dm.getDriverName());
//                System.out.println("Driver version: " + dm.getDriverVersion());
//                System.out.println("Product name: "
//                        + dm.getDatabaseProductName());
//                System.out.println("Product version: "
//                        + dm.getDatabaseProductVersion());
//            }
//        } catch (SQLException ex) {
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    public DBContext() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Lấy thông tin từ biến môi trường (Render Environment Variables)
            String dbURL = System.getenv("DB_URL");
            String dbUser = System.getenv("DB_USER");
            String dbPassword = System.getenv("DB_PASSWORD");

            if (dbURL == null || dbUser == null || dbPassword == null) {
                System.err.println("❌ Missing environment variables for DB connection!");
            }

            conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);

            if (conn != null) {
                System.out.println("✅ Connected to Azure SQL successfully!");
            } else {
                System.err.println("❌ Connection failed (conn == null)");
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "❌ SQLServer JDBC Driver not found!", ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "❌ Database connection error!", ex);
        }
    }


//    public DBContext() {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//            String dbURL = "jdbc:sqlserver://88.222.241.39:1433;"
//                    + "databaseName=GioCai;"
//                    + "user=sa;"
//                    + "password=HKD_GiaiLB@GioCai2004;"
//                    + "encrypt=false;"
//                    + "trustServerCertificate=true;"
//                    + "loginTimeout=30;";
//
//            conn = DriverManager.getConnection(dbURL);
//
//            if (conn != null) {
//                DatabaseMetaData dm = conn.getMetaData();
//                System.out.println("Connected to DB");
//                System.out.println("Driver name: " + dm.getDriverName());
//                System.out.println("Driver version: " + dm.getDriverVersion());
//                System.out.println("Product name: " + dm.getDatabaseProductName());
//                System.out.println("Product version: " + dm.getDatabaseProductVersion());
//            } else {
//                System.err.println("conn == null");
//            }
//
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Not Found JDBC Driver", ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Error Connect DB", ex);
//        }
//    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Error: Close DB", ex);
        }
    }
}
