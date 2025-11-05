package util;

import java.sql.*;
import java.util.logging.*;

public class DBContext {

    public Connection conn = null;

    public DBContext() {
        try {
            // Nạp driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Lấy biến môi trường
            String dbURL = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String pass = System.getenv("DB_PASS");

            if (dbURL == null) {
                throw new RuntimeException("⚠️ Thiếu biến môi trường DB_URL");
            }

            System.out.println("⏳ Đang kết nối tới SQL Server...");

            // Nếu user/pass tồn tại thì dùng, còn không thì chỉ dbURL
            if (user != null && pass != null) {
                conn = DriverManager.getConnection(dbURL, user, pass);
            } else {
                conn = DriverManager.getConnection(dbURL);
            }

            if (conn != null) {
                DatabaseMetaData dm = conn.getMetaData();
                System.out.println("✅ Kết nối thành công!");
                System.out.println("Driver: " + dm.getDriverName() + " " + dm.getDriverVersion());
                System.out.println("Database: " + dm.getDatabaseProductName() + " " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "❌ Lỗi kết nối SQL Server", ex);
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "❌ Không tìm thấy JDBC driver", ex);
        }
    }

    public void close() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("🔒 Đã đóng kết nối.");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "❌ Lỗi khi đóng kết nối DB", ex);
        }
    }
    
//    public DBContext() {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//            String dbURL = "jdbc:sqlserver://72.60.107.117:1433;"
//                    + "databaseName=GioCai;"
//                    + "user=sa;"
//                    + "password=HKDuy@972422;"
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
//
//    public void close() {
//        try {
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "Error: Close DB", ex);
//        }
//    }

}
