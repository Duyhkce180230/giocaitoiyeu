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
    


}
