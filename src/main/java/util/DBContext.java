package util;

import java.sql.*;
import java.util.logging.*;

public class DBContext {

    public Connection conn = null;

//    public DBContext() {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//
//            // Lấy thông tin từ biến môi trường
//            String dbUrl = System.getenv("DB_URL");
//            String dbUser = System.getenv("DB_USER");
//            String dbPassword = System.getenv("DB_PASSWORD");
//
//            if (dbUrl == null || dbUser == null || dbPassword == null) {
//                throw new RuntimeException("⚠️ Thiếu biến môi trường DB_URL / DB_USER / DB_PASSWORD");
//            }
//
//            // Xây dựng URL kết nối
//            String connectionString = dbUrl
//                    + "user=" + dbUser + ";"
//                    + "password=" + dbPassword + ";"
//                    + "encrypt=true;"
//                    + "trustServerCertificate=false;"
//                    + "hostNameInCertificate=*.database.windows.net;"
//                    + "loginTimeout=30;";
//
//            System.out.println("⏳ Đang kết nối tới Azure SQL...");
//            conn = DriverManager.getConnection(connectionString);
//
//            if (conn != null) {
//                System.out.println("✅ Kết nối thành công tới database Azure SQL!");
//            } else {
//                System.err.println("❌ Không thể kết nối tới DB (conn == null)");
//            }
//
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "❌ Không tìm thấy JDBC driver", ex);
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "❌ Lỗi khi kết nối tới database", ex);
//        }
//    }
//
//    public void close() {
//        try {
//            if (conn != null && !conn.isClosed()) {
//                conn.close();
//                System.out.println("🔒 Đã đóng kết nối.");
//            }
//        } catch (SQLException ex) {
//            Logger.getLogThis Love, Foreverger(DBContext.class.getName()).log(Level.SEVERE, "Lỗi khi đóng DB", ex);
//        }
//    }
//    public DBContext() {
//        try {
//            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//            String dbURL = "jdbc:sqlserver://localhost:1433;"
//                    + "databaseName=GioCai;"
//                    + "user=sa;"
//                    + "password=123456;"
//                    + "encrypt=false;trustServerCertificate=true;loginTimeout=30;";
//            conn = DriverManager.getConnection(dbURL);
//
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
            // Nạp driver SQL Server
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // ✅ Cập nhật thông tin kết nối
            String dbURL = "jdbc:sqlserver://72.60.107.117:1433;"
                    + "databaseName=GioCai;"
                    + "user=sa;"
                    + "password=HKDuy@972422;"
                    + "encrypt=false;"
                    + "trustServerCertificate=true;"
                    + "loginTimeout=30;";
            // Timeout 30s

            // Kết nối database
            System.out.println("⏳ Đang kết nối tới SQL Server 72.60.107.117...");
            conn = DriverManager.getConnection(dbURL);

            if (conn != null) {
                DatabaseMetaData dm = conn.getMetaData();
                System.out.println("✅ Kết nối thành công!");
                System.out.println("Driver: " + dm.getDriverName() + " " + dm.getDriverVersion());
                System.out.println("Database: " + dm.getDatabaseProductName() + " " + dm.getDatabaseProductVersion());
            }

        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, "❌ Lỗi kết nối SQL Server", ex);
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

}
