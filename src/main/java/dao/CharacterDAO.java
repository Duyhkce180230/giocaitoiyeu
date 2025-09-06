/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import model.CharacterHSR;
import model.ElementHSR;
import model.PathHSR;
import model.Rarity;
import util.DBContext;

/**
 *
 * @author huakh
 */
public class CharacterDAO extends DBContext {

    public CharacterDAO() {
        super();
    }

    public List<CharacterHSR> getAllCharacter() {
        List<CharacterHSR> list = new ArrayList<>();
        String sql = "SELECT c.*, e.ElementName, p.PathName, r.StarName \n"
                + "FROM [Character] c \n"
                + "JOIN Element e ON c.ElementId = e.ElementId \n"
                + "JOIN Path p ON c.PathId = p.PathId \n"
                + "JOIN Rarity r ON c.RarityId = r.RarityId \n"
                + "ORDER BY c.Name ASC;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String Name = rs.getString("Name");
                int ElementId = rs.getInt("ElementId");
                String ElementName = rs.getString("ElementName");
                int PathId = rs.getInt("PathId");
                String PathName = rs.getString("PathName");
                int RarityId = rs.getInt("RarityId");
                String StarName = rs.getString("StarName");
                String ImageIcon = rs.getString("ImageIcon");
                String ImageFull = rs.getString("ImageFull");
                String Description = rs.getString("Description");
                Timestamp LastUpdate = rs.getTimestamp("LastUpdate");
                int status = rs.getInt("status");
                float pointE0 = rs.getFloat("pointE0");
                float pointE1 = rs.getFloat("pointE1");
                float pointE2 = rs.getFloat("pointE2");
                float pointE3 = rs.getFloat("pointE3");
                float pointE4 = rs.getFloat("pointE4");
                float pointE5 = rs.getFloat("pointE5");
                float pointE6 = rs.getFloat("pointE6");
                ElementHSR element = new ElementHSR(ElementId, ElementName);
                PathHSR path = new PathHSR(PathId, PathName);
                Rarity rarity = new Rarity(RarityId, StarName);
                list.add(new CharacterHSR(Id, Name, element, path, rarity, ImageIcon, ImageFull,
                        Description, LastUpdate, status, pointE0, pointE1, pointE2, pointE3, pointE4,
                        pointE5, pointE6));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public List<CharacterHSR> getAllCharacterForBanPick() {
        List<CharacterHSR> list = new ArrayList<>();
        String sql = "SELECT c.*, e.ElementName, p.PathName, r.StarName \n"
                + "FROM [Character] c\n"
                + "JOIN Element e ON c.ElementId = e.ElementId \n"
                + "JOIN Path p ON c.PathId = p.PathId \n"
                + "JOIN Rarity r ON c.RarityId = r.RarityId \n"
                + "where status = 1\n"
                + "ORDER BY c.Name ASC;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String Name = rs.getString("Name");
                int RarityId = rs.getInt("RarityId");
                String StarName = rs.getString("StarName");
                String ImageIcon = rs.getString("ImageIcon");
                String ImageFull = rs.getString("ImageFull");
                float PointE0 = rs.getFloat("PointE0");
                float PointE1 = rs.getFloat("PointE1");
                float PointE2 = rs.getFloat("PointE2");
                float PointE3 = rs.getFloat("PointE3");
                float PointE4 = rs.getFloat("PointE4");
                float PointE5 = rs.getFloat("PointE5");
                float PointE6 = rs.getFloat("PointE6");

                Rarity rarity = new Rarity(RarityId, StarName);
                list.add(new CharacterHSR(Id, Name, rarity, ImageIcon, ImageFull, PointE0,
                        PointE1, PointE2, PointE3, PointE4, PointE5, PointE6));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public int newCharacter(String name, int elementId, int pathId, int rarityId,
            String ImageIcon, String imageFull, String description, int status, float pointE0, float pointE1, float pointE2,
            float pointE3, float pointE4, float pointE5, float pointE6) {
        String sql = "INSERT INTO Character(Name, ElementId,PathId,RarityId,"
                + "ImageIcon,ImageFull,Description,LastUpdate,Status,PointE0,PointE1,PointE2,PointE3,PointE4,PointE5,PointE6) \n"
                + "VALUES (?,?,?,?,?,?,?,GETDATE(),?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, elementId);
            ps.setInt(3, pathId);
            ps.setInt(4, rarityId);
            ps.setString(5, ImageIcon);
            ps.setString(6, imageFull);
            ps.setString(7, description);
            ps.setInt(8, status);
            ps.setFloat(9, pointE0);
            ps.setFloat(10, pointE1);
            ps.setFloat(11, pointE2);
            ps.setFloat(12, pointE3);
            ps.setFloat(13, pointE4);
            ps.setFloat(14, pointE5);
            ps.setFloat(15, pointE6);
            int row = ps.executeUpdate();
            if (row > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }

    public int editCharacter(String name, int elementId, int pathId, int rarityId,
            String ImageIcon, String ImageFull, String description, int status, float pointE0, float pointE1, float pointE2,
            float pointE3, float pointE4, float pointE5, float pointE6, int charId) {
        String sql = "UPDATE Character SET Name = ?, ElementId = ?, PathId = ?, RarityId = ?,"
                + " ImageIcon = ?, ImageFull=?, \n"
                + "Description= ?, Status=?, PointE0 = ?,PointE1 = ?, PointE2 = ?, "
                + "PointE3 = ?, \n"
                + "PointE4 = ?, PointE5 = ?, PointE6 = ?\n"
                + "WHERE Id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, elementId);
            ps.setInt(3, pathId);
            ps.setInt(4, rarityId);
            ps.setString(5, ImageIcon);
            ps.setString(6, ImageFull);
            ps.setString(7, description);
            ps.setInt(8, status);
            ps.setFloat(9, pointE0);
            ps.setFloat(10, pointE1);
            ps.setFloat(11, pointE2);
            ps.setFloat(12, pointE3);
            ps.setFloat(13, pointE4);
            ps.setFloat(14, pointE5);
            ps.setFloat(15, pointE6);
            ps.setInt(16, charId);
            int row = ps.executeUpdate();
            if (row > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            return 0;
        }
    }

    public int deleteCharacter(int id) {
        String sql = "DELETE FROM Character WHERE Id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            int num = ps.executeUpdate();
            if (num > 0) {
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public List<CharacterHSR> getAllCharacterAsCategory() {
        List<CharacterHSR> list = new ArrayList<>();
        String sql = "select Id,Name,Status from Character";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                int status = rs.getInt("Status");
                list.add(new CharacterHSR(id, name, status));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

//    public static void main(String[] args) {
//        CharacterDAO dao = new CharacterDAO();
//        List<CharacterHSR> characters = dao.getAllCharacterAsCategory();
//        if (characters.isEmpty()) {
//            System.out.println("Không có nhân vật nào trong cơ sở dữ liệu.");
//        } else {
//            System.out.println("Danh sách nhân vật:");
//            for (CharacterHSR c : characters) {
//                System.out.println(c); // in thông tin từ toString() của CharacterHSR
//            }
//        }
//    }
}
