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
import model.ElementHSR;
import util.DBContext;

/**
 *
 * @author huakh
 */
public class ElementDAO extends DBContext {

    public ElementDAO() {
        super();
    }

    public List<ElementHSR> getAllElement() {
        List<ElementHSR> list = new ArrayList<>();
        String sql = "select * from Element";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int elementId = rs.getInt("ElementId");
                String elementName = rs.getString("ElementName");
                list.add(new ElementHSR(elementId, elementName));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
//
//    public static void main(String[] args) {
//        ElementDAO dao = new ElementDAO();
//        List<ElementHSR> ElementHSR = dao.getAllElement();
//        if (ElementHSR.isEmpty()) {
//            System.out.println("Không có ElementHSR nào trong cơ sở dữ liệu.");
//        } else {
//            System.out.println("Danh sách ElementHSR:");
//            for (ElementHSR c : ElementHSR) {
//                System.out.println(c); // in thông tin từ toString() của CharacterHSR
//            }
//        }
//    }
}
