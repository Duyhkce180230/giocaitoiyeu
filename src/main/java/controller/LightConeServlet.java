/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CharacterDAO;
import dao.ElementDAO;
import dao.LightConeDAO;
import dao.PathDAO;
import dao.RarityDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.nio.file.Paths;
import java.util.List;
import model.CharacterHSR;
import model.ElementHSR;
import model.LightCone;
import model.PathHSR;
import model.Rarity;

/**
 *
 * @author Hua Khanh Duy - CE180230 - SE1814
 */
@MultipartConfig
@WebServlet(name = "LightConeServlet", urlPatterns = {"/lightcone"})
public class LightConeServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LightConeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LightConeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        LightConeDAO lightConeDAO = new LightConeDAO();
        RarityDAO rarityDAO = new RarityDAO();
        PathDAO pathDAO = new PathDAO();
        CharacterDAO characterDAO = new CharacterDAO();
        if (action == null) {
            action = "listLightCone";
        }
        if (action.equalsIgnoreCase("listLightCone")) {
            List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
            request.setAttribute("listCharacter", character);

            List<LightCone> LightCone = lightConeDAO.getAllLightCone();
            request.setAttribute("listLightCone", LightCone);

            List<Rarity> rarities = rarityDAO.getAllRarity();
            request.setAttribute("listRarity", rarities);

            List<PathHSR> path = pathDAO.getAllPath();
            request.setAttribute("listPath", path);

            request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        CharacterDAO characterDAO = new CharacterDAO();
        RarityDAO rarityDAO = new RarityDAO();
        PathDAO pathDAO = new PathDAO();
        ElementDAO elementDAO = new ElementDAO();
        LightConeDAO lightConeDAO = new LightConeDAO();
        if (action.equalsIgnoreCase("create")) {
            String lightConeName = request.getParameter("lightConeName");

            if (lightConeName != null) {
                lightConeName = lightConeName.trim();
            }

            if (lightConeName == null || lightConeName.isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Light Cone Name must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else if (lightConeName.matches(".*\\s{2,}.*")) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Light Cone Name must not contain two or more consecutive spaces.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            }

            String pathIdStr = request.getParameter("pathID");
            int pathId = -1;

            if (pathIdStr == null || pathIdStr.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Path ID must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    pathId = Integer.parseInt(pathIdStr.trim());

                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "Path ID must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            String rarityIdStr = request.getParameter("rarityID");
            int rarityId = -1;

            if (rarityIdStr == null || rarityIdStr.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Rarity ID must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    rarityId = Integer.parseInt(rarityIdStr.trim());

                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "Rarity ID must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            int characterId = Integer.parseInt(request.getParameter("characterSignatureID"));
            Part filePart = request.getPart("lightConeImage");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String imagePath = null;

            if (fileName == null || fileName.isEmpty()) {

                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "No file selected.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                String lowerFileName = fileName.toLowerCase();
                if (!(lowerFileName.endsWith(".jpg") || lowerFileName.endsWith(".jpeg") || lowerFileName.endsWith(".png"))) {

                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "Only JPG, JPEG, PNG files are allowed.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
                if (filePart.getSize() > 5 * 1024 * 1024) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "File size must be less than 5MB.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
                imagePath = "images/" + fileName;

            }

            String description = request.getParameter("description");

            if (description != null) {
                description = description.trim();
            }

            if (description == null || description.isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Description must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            }
            if (description.matches(".*\\s{2,}.*")) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Description must not contain two or more consecutive spaces.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            }

            String statusStr = request.getParameter("status");
            int status = -1;

            if (statusStr == null || statusStr.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Status must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    status = Integer.parseInt(statusStr.trim());

                    if (status != 0 && status != 1) {
                        List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                        request.setAttribute("listCharacter", character);

                        List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                        request.setAttribute("listLightCone", LightCone);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);

                        request.setAttribute("err", "Status must be either 0 or 1.");
                        request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                        return;

                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "Status must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;
                }
            }
            String pointS1Str = request.getParameter("pointS1");
            float pointS1;
            if (pointS1Str == null || pointS1Str.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "PointS1 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    pointS1 = Float.parseFloat(pointS1Str.trim());
                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "PointS1 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            String pointS2Str = request.getParameter("pointS2");
            float pointS2;
            if (pointS2Str == null || pointS2Str.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "PointS2 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    pointS2 = Float.parseFloat(pointS2Str.trim());
                    if (pointS2 < pointS1) {
                        List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                        request.setAttribute("listCharacter", character);

                        List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                        request.setAttribute("listLightCone", LightCone);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);

                        request.setAttribute("err", "PointS2 must be greater than PointS1.");
                        request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                        return;

                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "PointS2 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            String pointS3Str = request.getParameter("pointS3");
            float pointS3;
            if (pointS3Str == null || pointS3Str.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "PointS3 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    pointS3 = Float.parseFloat(pointS3Str.trim());
                    if (pointS3 < pointS2) {
                        List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                        request.setAttribute("listCharacter", character);

                        List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                        request.setAttribute("listLightCone", LightCone);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);

                        request.setAttribute("err", "PointS3 must be greater than PointS2.");
                        request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                        return;

                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "PointS3 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            String pointS4Str = request.getParameter("pointS4");
            float pointS4;
            if (pointS4Str == null || pointS4Str.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "PointS4 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    pointS4 = Float.parseFloat(pointS4Str.trim());
                    if (pointS4 < pointS3) {
                        List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                        request.setAttribute("listCharacter", character);

                        List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                        request.setAttribute("listLightCone", LightCone);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);

                        request.setAttribute("err", "PointS4 must be greater than PointS3.");
                        request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                        return;

                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "PointS4 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            String pointS5Str = request.getParameter("pointS5");
            float pointS5;
            if (pointS5Str == null || pointS5Str.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "PointS5 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    pointS5 = Float.parseFloat(pointS5Str.trim());
                    if (pointS5 < pointS4) {
                        List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                        request.setAttribute("listCharacter", character);

                        List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                        request.setAttribute("listLightCone", LightCone);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);

                        request.setAttribute("err", "PointS5 must be greater than PointS4.");
                        request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                        return;

                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "PointS5 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            try {
                int res = lightConeDAO.newLightCone(lightConeName, pathId, rarityId, characterId,
                        imagePath, description, status, pointS1, pointS2, pointS3, pointS4, pointS5);

                if (res == 1) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("success", "LightCone added successfully!!!");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);

                } else {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "Fail to add LightCone!!!");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    
                }
            } catch (ServletException | IOException e) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Fail to add LightCone Exception!");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);

            }
        } else if (action.equalsIgnoreCase("delete")) {
            String idRaw = request.getParameter("lightConeID");
            int id;
            try {
                id = Integer.parseInt(idRaw);
                if (lightConeDAO.deleteLightCone(id) == 1) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("success", "LightCone deleted successfully!!!");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);

                } else {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "Fail to delete LightCone!!!");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Fail to delete LightCone Exception!");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);

            }
        } else if (action.equalsIgnoreCase("edit")) {

            String lightConeIdStr = request.getParameter("lightConeID");
            int lightConeId = -1;

            if (lightConeIdStr == null || lightConeIdStr.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Light Cone ID must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    lightConeId = Integer.parseInt(lightConeIdStr.trim());

                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "Light Cone ID must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            String lightConeName = request.getParameter("lightConeName");

            if (lightConeName != null) {
                lightConeName = lightConeName.trim();
            }

            if (lightConeName == null || lightConeName.isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Light Cone Name must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else if (lightConeName.matches(".*\\s{2,}.*")) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Light Cone Name must not contain two or more consecutive spaces.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            }

            String pathIdStr = request.getParameter("pathID");
            int pathId = -1;

            if (pathIdStr == null || pathIdStr.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Path ID must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    pathId = Integer.parseInt(pathIdStr.trim());

                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "Path ID must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            String rarityIdStr = request.getParameter("rarityID");
            int rarityId = -1;

            if (rarityIdStr == null || rarityIdStr.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Rarity ID must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    rarityId = Integer.parseInt(rarityIdStr.trim());

                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "Rarity ID must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            int characterId = Integer.parseInt(request.getParameter("characterSignatureID"));

            Part filePart = request.getPart("lightConeImage");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            String imagePath;

            if (fileName == null || fileName.isEmpty()) {
                String oldImage = request.getParameter("oldImage");
                if (oldImage == null || oldImage.trim().isEmpty()) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "Old image must not be null or empty.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                } else {
                    imagePath = oldImage;
                }
            } else {
                imagePath = "images/" + fileName;
            }

            String description = request.getParameter("description");

            if (description != null) {
                description = description.trim();
            }

            if (description == null || description.isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Description must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            }
            if (description.matches(".*\\s{2,}.*")) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Description must not contain two or more consecutive spaces.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            }

            String statusStr = request.getParameter("status");
            int status = -1;

            if (statusStr == null || statusStr.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Status must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    status = Integer.parseInt(statusStr.trim());

                    if (status != 0 && status != 1) {
                        List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                        request.setAttribute("listCharacter", character);

                        List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                        request.setAttribute("listLightCone", LightCone);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);

                        request.setAttribute("err", "Status must be either 0 or 1.");
                        request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                        return;

                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "Status must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;
                }
            }
            String pointS1Str = request.getParameter("pointS1");
            float pointS1;
            if (pointS1Str == null || pointS1Str.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "PointS1 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    pointS1 = Float.parseFloat(pointS1Str.trim());
                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "PointS1 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            String pointS2Str = request.getParameter("pointS2");
            float pointS2;
            if (pointS2Str == null || pointS2Str.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "PointS2 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    pointS2 = Float.parseFloat(pointS2Str.trim());
                    if (pointS2 < pointS1) {
                        List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                        request.setAttribute("listCharacter", character);

                        List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                        request.setAttribute("listLightCone", LightCone);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);

                        request.setAttribute("err", "PointS2 must be greater than PointS1.");
                        request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                        return;

                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "PointS2 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            String pointS3Str = request.getParameter("pointS3");
            float pointS3;
            if (pointS3Str == null || pointS3Str.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "PointS3 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    pointS3 = Float.parseFloat(pointS3Str.trim());
                    if (pointS3 < pointS2) {
                        List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                        request.setAttribute("listCharacter", character);

                        List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                        request.setAttribute("listLightCone", LightCone);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);

                        request.setAttribute("err", "PointS3 must be greater than PointS2.");
                        request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                        return;

                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "PointS3 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            String pointS4Str = request.getParameter("pointS4");
            float pointS4;
            if (pointS4Str == null || pointS4Str.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "PointS4 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    pointS4 = Float.parseFloat(pointS4Str.trim());
                    if (pointS4 < pointS3) {
                        List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                        request.setAttribute("listCharacter", character);

                        List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                        request.setAttribute("listLightCone", LightCone);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);

                        request.setAttribute("err", "PointS4 must be greater than PointS3.");
                        request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                        return;

                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "PointS4 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            String pointS5Str = request.getParameter("pointS5");
            float pointS5;
            if (pointS5Str == null || pointS5Str.trim().isEmpty()) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "PointS5 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                return;

            } else {
                try {
                    pointS5 = Float.parseFloat(pointS5Str.trim());
                    if (pointS5 < pointS4) {
                        List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                        request.setAttribute("listCharacter", character);

                        List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                        request.setAttribute("listLightCone", LightCone);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);

                        request.setAttribute("err", "PointS5 must be greater than PointS4.");
                        request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                        return;

                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "PointS5 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                    return;

                }
            }

            try {
                int res = lightConeDAO.editLightCone(lightConeName, pathId, rarityId, characterId, imagePath,
                        description, status, pointS1, pointS2, pointS3, pointS4, pointS5, lightConeId);

                if (res == 1) {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("success", "LightCone edited successfully!!!");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);

                } else {
                    List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                    request.setAttribute("listCharacter", character);

                    List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                    request.setAttribute("listLightCone", LightCone);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);

                    request.setAttribute("err", "Fail to edit LightCone!!!");
                    request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);
                }
            } catch (ServletException | IOException e) {
                List<CharacterHSR> character = characterDAO.getAllCharacterAsCategory();
                request.setAttribute("listCharacter", character);

                List<LightCone> LightCone = lightConeDAO.getAllLightCone();
                request.setAttribute("listLightCone", LightCone);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);

                request.setAttribute("err", "Fail to edit LightCone Exception!");
                request.getRequestDispatcher("/WEB-INF/views/lightcone.jsp").forward(request, response);

            }
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
