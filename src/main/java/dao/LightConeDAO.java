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
import model.LightCone;
import model.PathHSR;
import model.Rarity;
import util.DBContext;

/**
 *
 * @author huakh
 */
public class LightConeDAO extends DBContext {

    public LightConeDAO() {
        super();
    }

    public List<LightCone> getAllLightCone() {
        List<LightCone> list = new ArrayList<>();
        String sql = "SELECT l.*, r.StarName, p.PathName, c.Name as CharName FROM LightCone l\n"
                + "JOIN Path p ON l.PathId = p.PathId\n"
                + "JOIN Rarity r ON l.RarityId = r.RarityId\n"
                + "JOIN Character c ON l.CharacterSignature = c.Id\n"
                + "order by CharName";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String Name = rs.getString("Name");
                int PathId = rs.getInt("PathId");
                String PathName = rs.getString("PathName");
                int RarityId = rs.getInt("RarityId");
                String StarName = rs.getString("StarName");
                int CharacterSignature = rs.getInt("CharacterSignature");
                String CharName = rs.getString("CharName");
                String ImageUrl = rs.getString("ImageUrl");
                String Description = rs.getString("Description");
                Timestamp LastUpdate = rs.getTimestamp("LastUpdate");
                int status = rs.getInt("status");
                float pointS1 = rs.getFloat("PointS1");
                float pointS2 = rs.getFloat("PointS2");
                float pointS3 = rs.getFloat("PointS3");
                float pointS4 = rs.getFloat("PointS4");
                float pointS5 = rs.getFloat("PointS5");
                CharacterHSR CharSignatureId = new CharacterHSR(CharacterSignature, CharName);
                PathHSR path = new PathHSR(PathId, PathName);
                Rarity rarity = new Rarity(RarityId, StarName);
                list.add(new LightCone(Id, Name, path, rarity,
                        CharSignatureId, ImageUrl, Description, LastUpdate,
                        status, pointS1, pointS2, pointS3, pointS4, pointS5));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    public int newLightCone(String name, int pathId, int rarityId, int characterId,
            String image, String description, int status, float pointS1, float pointS2,
            float pointS3, float pointS4, float pointS5) {
        String sql = "INSERT INTO LightCone(Name,PathId,RarityId,CharacterSignature,ImageUrl,Description,LastUpdate,Status,PointS1,PointS2,\n"
                + "PointS3,PointS4,PointS5)\n"
                + "VALUES (?,?,?,?,?,?,GETDATE(),?,?,?,?,?,?);";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, pathId);
            ps.setInt(3, rarityId);
            ps.setInt(4, characterId);
            ps.setString(5, image);
            ps.setString(6, description);
            ps.setInt(7, status);
            ps.setFloat(8, pointS1);
            ps.setFloat(9, pointS2);
            ps.setFloat(10, pointS3);
            ps.setFloat(11, pointS4);
            ps.setFloat(12, pointS5);
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

    public int deleteLightCone(int id) {
        String sql = "  DELETE FROM LightCone WHERE Id = ?";
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

    public int editLightCone(String name, int pathId, int rarityId, int characterId,
            String image, String description, int status, float pointS1, float pointS2,
            float pointS3, float pointS4, float pointS5, int lightconeId) {
        String sql = "UPDATE LightCone SET Name = ?, PathId = ?, RarityId = ?,"
                + "CharacterSignature=?, ImageUrl = ?, \n"
                + "Description= ?, LastUpdate= GETDATE(), Status=?, PointS1 = ?, PointS2 = ?, "
                + "PointS3 = ?, \n"
                + "PointS4 = ?, PointS5 = ?\n"
                + "WHERE Id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, pathId);
            ps.setInt(3, rarityId);
            ps.setInt(4, characterId);
            ps.setString(5, image);
            ps.setString(6, description);
            ps.setInt(7, status);
            ps.setFloat(8, pointS1);
            ps.setFloat(9, pointS2);
            ps.setFloat(10, pointS3);
            ps.setFloat(11, pointS4);
            ps.setFloat(12, pointS5);
            ps.setInt(13, lightconeId);
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

    public List<LightCone> getAllLightConeForBanPick() {
        List<LightCone> list = new ArrayList<>();
        String sql = "SELECT l.Id,l.Name,l.ImageUrl,l.CharacterSignature, \n"
                + "l.PointS1,l.PointS2,l.PointS3,l.PointS4,l.PointS5,\n"
                + "r.RarityId, r.StarName,p.PathId, \n"
                + "p.PathName , c.Name as CharName FROM LightCone l\n"
                + "JOIN Path p ON l.PathId = p.PathId\n"
                + "JOIN Rarity r ON l.RarityId = r.RarityId\n"
                + "JOIN Character c ON l.CharacterSignature = c.Id\n"
                + "where c.Status = 1\n"
                + "order by CharName";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("Id");
                String Name = rs.getString("Name");
                int PathId = rs.getInt("PathId");
                String PathName = rs.getString("PathName");
                int RarityId = rs.getInt("RarityId");
                String StarName = rs.getString("StarName");
                int CharacterSignature = rs.getInt("CharacterSignature");
                String CharName = rs.getString("CharName");
                String ImageUrl = rs.getString("ImageUrl");
                float pointS1 = rs.getFloat("PointS1");
                float pointS2 = rs.getFloat("PointS2");
                float pointS3 = rs.getFloat("PointS3");
                float pointS4 = rs.getFloat("PointS4");
                float pointS5 = rs.getFloat("PointS5");
                CharacterHSR CharSignatureId = new CharacterHSR(CharacterSignature, CharName);
                PathHSR path = new PathHSR(PathId, PathName);
                Rarity rarity = new Rarity(RarityId, StarName);
                list.add(new LightCone(Id, Name, path, rarity,
                        CharSignatureId, ImageUrl, pointS1, pointS2, pointS3, pointS4, pointS5));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
//    public static void main(String[] args) {
//        LightConeDAO dao = new LightConeDAO();
//        List<LightCone> LightCone = dao.getAllLightCone();
//        if (LightCone.isEmpty()) {
//            System.out.println("Không có LightCone nào trong cơ sở dữ liệu.");
//        } else {
//            System.out.println("Danh sách LightCone:");
//            for (LightCone c : LightCone) {
//                System.out.println(c);
//            }
//        }
//    }
}
