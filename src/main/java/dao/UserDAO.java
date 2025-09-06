/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://github/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.security.MessageDigest;
import java.security.SecureRandom;

import model.*;
import util.DBContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

import model.UserGoogle;

/**
 *
 * @author Duy
 * @author Ngo Phuoc Thinh - CE170008 - SE1815
 */
public class UserDAO extends DBContext {

    public boolean updateUser(User user) throws SQLException {
        String sql = "UPDATE Users SET DisplayName = ?, Email = ?, Role = ?, BanStatus = ?, DateOfBirth = ?, info = ?, PhoneNumber = ? WHERE UserName = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            int i = 1;
            ps.setString(i++, user.getDisplayName());
            ps.setString(i++, user.getEmail());

            if (user.getRole() != null) {
                ps.setInt(i++, user.getRole().ordinal());
            }
            if (user.getBan() != null) {
                ps.setInt(i++, user.getBan().ordinal());
            }
            ps.setTimestamp(i++, user.getDateOfBirth());
            ps.setString(i++, user.getInfo());
            ps.setString(i++, user.getPhone());
            ps.setString(i++, user.getUserName());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public User getByUserIDWithAvatar(int userID) {
        String sql = "SELECT *, AvatarGoogle FROM Users WHERE UserID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String userName = rs.getString("UserName");
                String displayName = rs.getString("DisplayName");
                String email = rs.getString("Email");
                String password = rs.getString("Password");
                int roleInt = rs.getInt("Role");
                Role role = null;
                switch (roleInt) {
                    case 0:
                        role = Role.STAFF;
                        break;
                    case 1:
                        role = Role.ADMIN;
                        break;
                    default:
                        System.err.println("Invalid role value from DB: " + roleInt);
                }
                int gender = rs.getInt("Gender");
                Timestamp birthOfDate = rs.getTimestamp("DateOfBirth");
                Timestamp timeCreate = rs.getTimestamp("UserCreateDate");
                byte[] avatar = rs.getBytes("Avatar");
                String info = rs.getString("info");
                int banInt = rs.getInt("BanStatus");
                Ban Ban = null;
                switch (banInt) {
                    case 0:
                        Ban = Ban.NORMAL;
                        break;
                    case 1:
                        Ban = Ban.BANNED;
                        break;
                    default:
                        System.err.println("Invalid ban value from DB: " + banInt);
                }
                String phoneNumber = rs.getString("PhoneNumber");
                boolean isVerified = rs.getBoolean("IsVerified");
                String GoogleID = rs.getString("GoogleID");
                String avatarGoogleUrl = rs.getString("AvatarGoogle");

                User acc = new User(userID, userName, displayName, email, password, role, gender, birthOfDate, timeCreate, avatar, info, Ban, phoneNumber, isVerified, GoogleID, avatarGoogleUrl);
                return acc;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User findByGoogleIDWithAvatar(String googleID) {
        String sql = "SELECT *, AvatarGoogle FROM Users WHERE GoogleID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, googleID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int UserID = rs.getInt("UserID");
                String UserName = rs.getString("UserName");
                String DisplayName = rs.getString("DisplayName");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                int roleInt = rs.getInt("Role");
                Role role = null;
                switch (roleInt) {
                    case 0:
                        role = Role.STAFF;
                        break;
                    case 1:
                        role = Role.ADMIN;
                        break;
                    default:
                        System.err.println("Invalid role value from DB: " + roleInt);
                }
                int gender = rs.getInt("Gender");
                Timestamp BirthOfDay = rs.getTimestamp("DateOfBirth");
                Timestamp TimeCreate = rs.getTimestamp("UserCreateDate");
                byte[] Avatar = rs.getBytes("Avatar");
                String info = rs.getString("info");
                int banInt = rs.getInt("BanStatus");
                Ban Ban = null;
                switch (banInt) {
                    case 0:
                        Ban = Ban.NORMAL;
                        break;
                    case 1:
                        Ban = Ban.BANNED;
                        break;
                    default:
                        System.err.println("Invalid ban value from DB: " + banInt);
                }
                String PhoneNumber = rs.getString("PhoneNumber");
                boolean isVerified = rs.getBoolean("IsVerified");
                String avatarGoogleUrl = rs.getString("AvatarGoogle");

                User acc = new User(UserID, UserName, DisplayName, Email, Password, role, gender, BirthOfDay, TimeCreate, Avatar, info, Ban, PhoneNumber, isVerified, googleID, avatarGoogleUrl);
                return acc;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean checkPassword(int userId, String oldPassword) throws Exception {
        String sql = "SELECT COUNT(*) FROM Users WHERE userId = ? AND password = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ps.setString(2, hashMD5(oldPassword));
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    public void updatePassword(int userId, String newPassword) throws Exception {
        String sql = "UPDATE Users SET password = ? WHERE userId = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, hashMD5(newPassword));
            ps.setInt(2, userId);
            ps.executeUpdate();
        }
    }


    public String hashMD5(String pass) {
        try {
            MessageDigest mes = MessageDigest.getInstance("MD5");
            byte[] mesMD5 = mes.digest(pass.getBytes());
            StringBuilder str = new StringBuilder();
            for (byte b : mesMD5) {
                String ch = String.format("%02x", b);
                str.append(ch);
            }
            return str.toString();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    public boolean banAccount(String userName) throws SQLException {
        String sql = "UPDATE Users SET BanStatus = CASE WHEN BanStatus = 0 THEN 1 WHEN BanStatus = 1 THEN 0 ELSE BanStatus END WHERE UserName = ?";
        try ( PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userName);
            int ii = ps.executeUpdate();
            return ii > 0;
        }
    }

    public User verifyMD5(String input, String Password) {
        String sql = "SELECT * FROM Users WHERE (UserName = ? OR Email = ?) AND Password = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, input);
            ps.setString(2, input);
            ps.setString(3, hashMD5(Password));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int UserID = rs.getInt("UserID");
                String UserName = rs.getString("UserName");
                String DisplayName = rs.getString("DisplayName");
                String Email = rs.getString("Email");
                Password = rs.getString("Password");
                int roleInt = rs.getInt("Role");
                Role role = null;
                switch (roleInt) {
                    case 0:
                        role = Role.STAFF;
                        break;
                    case 1:
                        role = Role.ADMIN;
                        break;
                    default:
                        System.err.println("Invalid role value from DB: " + roleInt);
                }
                int gender = rs.getInt("Gender");
                Timestamp BirthOfDay = rs.getTimestamp("DateOfBirth");
                Timestamp TimeCreate = rs.getTimestamp("UserCreateDate");
                byte[] Avatar = rs.getBytes("Avatar");
                String info = rs.getString("info");
                int banInt = rs.getInt("BanStatus");
                Ban Ban = null;
                switch (banInt) {
                    case 0:
                        Ban = Ban.NORMAL;
                        break;
                    case 1:
                        Ban = Ban.BANNED;
                        break;
                    default:
                        System.err.println("Invalid ban value from DB: " + banInt);
                }
                String PhoneNumber = rs.getString("PhoneNumber");
                boolean isVerified = rs.getBoolean("IsVerified");
                String GoogleID = rs.getString("GoogleID");
                String avatarGoogleUrl = rs.getString("AvatarGoogle");

                User acc = new User(UserID, UserName, DisplayName, Email, Password, role, gender, BirthOfDay, TimeCreate, Avatar, info, Ban, PhoneNumber, isVerified, GoogleID, avatarGoogleUrl);
                return acc;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int insertUser(User user) {
        String hashMD5 = hashMD5(user.getPassword());

        String sql = "INSERT INTO users (UserName, DisplayName, Email, Password, Role, PhoneNumber, IsVerified, BanStatus) VALUES (?, 'Newbie', ?, ?, 0, ?, 0, 0)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, hashMD5);
            ps.setString(4, user.getPhone());

            int result = ps.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }


    public User findByGoogleID(String googleID) {
        String sql = "SELECT * FROM Users WHERE GoogleID = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, googleID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int UserID = rs.getInt("UserID");
                String UserName = rs.getString("UserName");
                String DisplayName = rs.getString("DisplayName");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                int roleInt = rs.getInt("Role");
                Role role = null;
                switch (roleInt) {
                    case 0:
                        role = Role.STAFF;
                        break;
                    case 1:
                        role = Role.ADMIN;
                        break;
                    default:
                        System.err.println("Invalid role value from DB: " + roleInt);
                }
                int gender = rs.getInt("Gender");
                Timestamp BirthOfDay = rs.getTimestamp("DateOfBirth");
                Timestamp TimeCreate = rs.getTimestamp("UserCreateDate");
                byte[] Avatar = rs.getBytes("Avatar");
                String info = rs.getString("info");
                int banInt = rs.getInt("BanStatus");
                Ban Ban = null;
                switch (banInt) {
                    case 0:
                        Ban = Ban.NORMAL;
                        break;
                    case 1:
                        Ban = Ban.BANNED;
                        break;
                    default:
                        System.err.println("Invalid ban value from DB: " + banInt);
                }
                String PhoneNumber = rs.getString("PhoneNumber");
                boolean isVerified = rs.getBoolean("IsVerified");
                String avatarGoogleUrl = rs.getString("AvatarGoogle");

                User acc = new User(UserID, UserName, DisplayName, Email, Password, role, gender, BirthOfDay, TimeCreate, Avatar, info, Ban, PhoneNumber, isVerified, googleID, avatarGoogleUrl);
                return acc;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public User findByEmail(String Email) {
        String sql = "SELECT * FROM Users WHERE Email = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, Email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int UserID = rs.getInt("UserID");
                String UserName = rs.getString("UserName");
                String DisplayName = rs.getString("DisplayName");
                String Password = rs.getString("Password");
                int roleInt = rs.getInt("Role");
                Role role = null;
                switch (roleInt) {
                    case 0:
                        role = Role.STAFF;
                        break;
                    case 1:
                        role = Role.ADMIN;
                        break;
                    default:
                        System.err.println("Invalid role value from DB: " + roleInt);
                }
                int gender = rs.getInt("Gender");
                Timestamp BirthOfDay = rs.getTimestamp("DateOfBirth");
                Timestamp TimeCreate = rs.getTimestamp("UserCreateDate");
                byte[] Avatar = rs.getBytes("Avatar");
                String info = rs.getString("info");
                int banInt = rs.getInt("BanStatus");
                Ban Ban = null;
                switch (banInt) {
                    case 0:
                        Ban = Ban.NORMAL;
                        break;
                    case 1:
                        Ban = Ban.BANNED;
                        break;
                    default:
                        System.err.println("Invalid ban value from DB: " + banInt);
                }
                String PhoneNumber = rs.getString("PhoneNumber");
                boolean isVerified = rs.getBoolean("IsVerified");
                String GoogleID = rs.getString("GoogleID");
                String avatarGoogleUrl = rs.getString("AvatarGoogle");

                User acc = new User(UserID, UserName, DisplayName, Email, Password, role, gender, BirthOfDay, TimeCreate, Avatar, info, Ban, PhoneNumber, isVerified, GoogleID, avatarGoogleUrl);
                return acc;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int updateGoogleID(User user) {
        String sql = "UPDATE Users SET GoogleID = ?, IsVerified = ?, AvatarGoogle = ? WHERE UserID = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, user.getGoogleID());
            ps.setBoolean(2, true);
            ps.setString(3, user.getAvatarUrl());
            ps.setInt(4, user.getUserId());

            int result = ps.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int insertGoogle(UserGoogle user) {
        String sql = "INSERT INTO Users (UserName, DisplayName, Email, Password, Role, AvatarGoogle, GoogleID, IsVerified) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            String email = user.getEmail();
            String name = user.getName();
            String picture = user.getPicture();
            String googleID = user.getId();

            String username = email.split("@")[0];
            String password = generateRandomPassword(10);

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, password);
            ps.setInt(5, 0);
            ps.setString(6, picture);
            ps.setString(7, googleID);
            ps.setBoolean(8, true);

            int result = ps.executeUpdate();
            return result > 0 ? 1 : 0;

        } catch (Exception e) {
            System.out.println("insertGoogle error: " + e.getMessage());
        }
        return 0;
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }

    public void saveTokenVerifyEmail(int userId, String token, Timestamp createdAt, Timestamp expiresAt) {
        String sql = "INSERT INTO VerifyEmailTokens (UserID, token, created_at, expires_at) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, token);
            ps.setTimestamp(3, createdAt);
            ps.setTimestamp(4, expiresAt);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public VerifyEmailToken findValidTokenVerifyEmail(String token) {
        String sql = "SELECT * FROM VerifyEmailTokens WHERE token = ? AND expires_at > GETUTCDATE()";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, token);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                VerifyEmailToken vet = new VerifyEmailToken();
                vet.setId(rs.getInt("id"));
                vet.setUserId(rs.getInt("UserID"));
                vet.setToken(token);
                return vet;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteTokenVerifyEmail(int userId) {
        String sql = "DELETE FROM VerifyEmailTokens WHERE UserID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int updateIsVerified(int userId) {
        String sql = "UPDATE Users SET IsVerified = 1 WHERE UserID = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            int result = ps.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public void saveTokenForgotPassword(int userId, String token, Timestamp createdAt, Timestamp expiresAt) {
        String sql = "INSERT INTO PasswordResetTokens (UserID, token, created_at, expires_at) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, token);
            ps.setTimestamp(3, createdAt);
            ps.setTimestamp(4, expiresAt);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public PasswordResetToken findValidTokenForgotPassword(String token) {
        String sql = "SELECT * FROM PasswordResetTokens WHERE token = ? AND expires_at > GETUTCDATE()";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, token);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                PasswordResetToken prt = new PasswordResetToken();
                prt.setId(rs.getInt("id"));
                prt.setUserId(rs.getInt("UserID"));
                prt.setToken(token);
                return prt;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteTokenForgotPassword(int userId) {
        String sql = "DELETE FROM PasswordResetTokens WHERE UserID = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userId);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int updatePasswordAfterForgot(int userId, String newPassword) {
        String hashPass = hashMD5(newPassword);
        String sql = "UPDATE Users SET Password = ? WHERE UserID = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, hashPass);
            ps.setInt(2, userId);

            int result = ps.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int saveToken(int userID, String token, Timestamp expiryDate) {
        String sql = "INSERT INTO RememberTokens (user_id, token, expiry_date) VALUES (?, ?, ?)";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            ps.setString(2, token);
            ps.setTimestamp(3, expiryDate);
            int result = ps.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public User findByToken(String token) {
        String sql = "SELECT u.* FROM users u JOIN RememberTokens t ON u.UserID = t.user_id WHERE t.token = ? AND t.expiry_date > CURRENT_TIMESTAMP;";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, token);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int UserID = rs.getInt("UserID");
                String UserName = rs.getString("UserName");
                String DisplayName = rs.getString("DisplayName");
                String Email = rs.getString("Email");
                String Password = rs.getString("Password");
                int roleInt = rs.getInt("Role");
                Role role = null;
                switch (roleInt) {
                    case 0:
                        role = Role.STAFF;
                        break;
                    case 1:
                        role = Role.ADMIN;
                        break;
                    default:
                        System.err.println("Invalid role value from DB: " + roleInt);
                }
                int gender = rs.getInt("Gender");
                Timestamp BirthOfDay = rs.getTimestamp("DateOfBirth");
                Timestamp TimeCreate = rs.getTimestamp("UserCreateDate");
                byte[] Avatar = rs.getBytes("Avatar");
                String info = rs.getString("info");
                int banInt = rs.getInt("BanStatus");
                Ban Ban = null;
                switch (banInt) {
                    case 0:
                        Ban = Ban.NORMAL;
                        break;
                    case 1:
                        Ban = Ban.BANNED;
                        break;
                    default:
                        System.err.println("Invalid ban value from DB: " + banInt);
                }
                String PhoneNumber = rs.getString("PhoneNumber");
                boolean isVerified = rs.getBoolean("IsVerified");
                String GoogleID = rs.getString("GoogleID");
                String avatarGoogleUrl = rs.getString("AvatarGoogle");

                User acc = new User(UserID, UserName, DisplayName, Email, Password, role, gender, BirthOfDay, TimeCreate, Avatar, info, Ban, PhoneNumber, isVerified, GoogleID, avatarGoogleUrl);
                return acc;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public int deleteToken(String token) {
        String sql = "DELETE FROM RememberTokens WHERE token = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, token);
            int result = ps.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }

    public int deleteAllTokens(int userID) {
        String sql = "DELETE FROM RememberTokens WHERE user_id = ?";

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);
            int result = ps.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return 0;
    }


//    public static void main(String[] args) {
//        UserDAO dao = new UserDAO();
//        
//        User acc = dao.verifyMD5("Admin","123");
//        
//        System.out.println(acc);
//    }
}
