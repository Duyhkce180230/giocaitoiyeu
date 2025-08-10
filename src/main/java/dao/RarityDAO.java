/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Rarity;
import util.DBContext;

/**
 *
 * @author huakh
 */
public class RarityDAO extends DBContext {

    public RarityDAO() {
        super();
    }

    public List<Rarity> getAllRarity() {
        List<Rarity> list = new ArrayList<>();
        String sql = "select * from Rarity ORDER BY StarName Desc";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int rarityId = rs.getInt("RarityId");
                String starName = rs.getString("StarName");
                list.add(new Rarity(rarityId, starName));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

//    public static void main(String[] args) {
//        RarityDAO dao = new RarityDAO();
//        List<Rarity> Rarity = dao.getAllRarity();
//        if (Rarity.isEmpty()) {
//            System.out.println("Không có Rarity nào trong cơ sở dữ liệu.");
//        } else {
//            System.out.println("Danh sách Rarity:");
//            for (Rarity c : Rarity) {
//                System.out.println(c); // in thông tin từ toString() của CharacterHSR
//            }
//        }
//    }

}
