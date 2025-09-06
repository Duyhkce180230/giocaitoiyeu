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
import model.CharacterHSR;
import model.LightCone;
import model.Team;
import util.DBContext;

/**
 *
 * @author huakh
 */
public class TeamDAO extends DBContext {

    public TeamDAO() {
        super();
    }

    public List<Team> getTeam() {
        List<Team> list = new ArrayList<>();
        String sql = "SELECT t.TeamId,\n"
                + "c1.Id as IDChar1,\n"
                + "c1.ImageFull AS Char1Image,\n"
                + "t.EChar1,\n"
                + "lc1.Id as IDLc1,\n"
                + "lc1.ImageUrl AS LC1Image,\n"
                + "t.SLCChar1,\n"
                + "c2.Id as IDChar2,\n"
                + "c2.ImageFull AS Char2Image,\n"
                + "t.EChar2,\n"
                + "lc2.Id as IDLc2,\n"
                + "lc2.ImageUrl AS LC2Image,\n"
                + "t.SLCChar2,\n"
                + "c3.Id as IDChar3,\n"
                + "c3.ImageFull AS Char3Image,\n"
                + "t.EChar3,\n"
                + "lc3.Id as IDLc3,\n"
                + "lc3.ImageUrl AS LC3Image,\n"
                + "t.SLCChar3,\n"
                + "c4.Id as IDChar4,\n"
                + "c4.ImageFull AS Char4Image,\n"
                + "t.EChar4,\n"
                + "lc4.Id as IDLc4,\n"
                + "lc4.ImageUrl AS LC4Image,\n"
                + "t.SLCChar4,\n"
                + "t.TotalCycle,\n"
                + "t.Note,\n"
                + "t.LinkSetUp,\n"
                + "t.Half\n"
                + "FROM [GioCai].[dbo].[Team] t\n"
                + "LEFT JOIN [Character] c1 ON t.IDChar1 = c1.Id\n"
                + "LEFT JOIN [Character] c2 ON t.IDChar2 = c2.Id\n"
                + "LEFT JOIN [Character] c3 ON t.IDChar3 = c3.Id\n"
                + "LEFT JOIN [Character] c4 ON t.IDChar4 = c4.Id\n"
                + "LEFT JOIN [LightCone] lc1 ON t.IDLCChar1 = lc1.Id\n"
                + "LEFT JOIN [LightCone] lc2 ON t.IDLCChar2 = lc2.Id\n"
                + "LEFT JOIN [LightCone] lc3 ON t.IDLCChar3 = lc3.Id\n"
                + "LEFT JOIN [LightCone] lc4 ON t.IDLCChar4 = lc4.Id\n"
                + "ORDER BY t.TeamId ASC;";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int Id = rs.getInt("TeamId");
                //Char1
                int IDChar1 = rs.getInt("IDChar1");
                String Char1Image = rs.getString("Char1Image");
                String EChar1 = rs.getString("EChar1");
                int IDLc1 = rs.getInt("IDLc1");
                String LC1Image = rs.getString("LC1Image");
                String SLCChar1 = rs.getString("SLCChar1");
                CharacterHSR char1 = new CharacterHSR(IDChar1, Char1Image);
                LightCone lcChar1 = new LightCone(IDLc1, LC1Image);
                //Char2
                int IDChar2 = rs.getInt("IDChar2");
                String Char2Image = rs.getString("Char2Image");
                String EChar2 = rs.getString("EChar2");
                int IDLc2 = rs.getInt("IDLc2");
                String LC2Image = rs.getString("LC2Image");
                String SLCChar2 = rs.getString("SLCChar2");
                CharacterHSR char2 = new CharacterHSR(IDChar2, Char2Image);
                LightCone lcChar2 = new LightCone(IDLc2, LC2Image);
                //Char3
                int IDChar3 = rs.getInt("IDChar3");
                String Char3Image = rs.getString("Char3Image");
                String EChar3 = rs.getString("EChar3");
                int IDLc3 = rs.getInt("IDLc3");
                String LC3Image = rs.getString("LC3Image");
                String SLCChar3 = rs.getString("SLCChar3");
                CharacterHSR char3 = new CharacterHSR(IDChar3, Char3Image);
                LightCone lcChar3 = new LightCone(IDLc3, LC3Image);
                //Char4
                int IDChar4 = rs.getInt("IDChar4");
                String Char4Image = rs.getString("Char4Image");
                String EChar4 = rs.getString("EChar4");
                int IDLc4 = rs.getInt("IDLc4");
                String LC4Image = rs.getString("LC4Image");
                String SLCChar4 = rs.getString("SLCChar4");
                CharacterHSR char4 = new CharacterHSR(IDChar4, Char4Image);
                LightCone lcChar4 = new LightCone(IDLc4, LC4Image);

                int TotalCycle = rs.getInt("TotalCycle");
                String Note = rs.getString("Note");
                String LinkSetUp = rs.getString("LinkSetUp");
                String Half = rs.getString("Half");

                list.add(new Team(Id,
                        char1, EChar1, lcChar1, SLCChar1,
                        char2, EChar2, lcChar2, SLCChar2,
                        char3, EChar3, lcChar3, SLCChar3,
                        char4, EChar4, lcChar4, SLCChar4,
                        TotalCycle, Note, LinkSetUp, Half));
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

//    public static void main(String[] args) {
//        TeamDAO dao = new TeamDAO();
//        List<Team> Team = dao.getTeam();
//        if (Team.isEmpty()) {
//            System.out.println("Không có nhân vật nào trong cơ sở dữ liệu.");
//        } else {
//            System.out.println("Danh sách nhân vật:");
//            for (Team c : Team) {
//                System.out.println(c); // in thông tin từ toString() của CharacterHSR
//            }
//        }
//    }
}
