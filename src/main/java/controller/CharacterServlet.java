/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CharacterDAO;
import dao.ElementDAO;
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
import java.util.Optional;
import model.CharacterHSR;
import model.ElementHSR;
import model.PathHSR;
import model.Rarity;

/**
 *
 * @author Hua Khanh Duy - CE180230 - SE1814
 */
@MultipartConfig
@WebServlet(name = "CharacterServlet", urlPatterns = {"/character"})
public class CharacterServlet extends HttpServlet {

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
            out.println("<title>Servlet CharacterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CharacterServlet at " + request.getContextPath() + "</h1>");
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
        CharacterDAO characterDAO = new CharacterDAO();
        RarityDAO rarityDAO = new RarityDAO();
        PathDAO pathDAO = new PathDAO();
        ElementDAO elementDAO = new ElementDAO();
        if (action == null) {
            action = "listCharacter";
        }
        if (action.equalsIgnoreCase("listCharacter")) {
            List<CharacterHSR> characters = characterDAO.getAllCharacter();
            request.setAttribute("listCharacter", characters);

            List<Rarity> rarities = rarityDAO.getAllRarity();
            request.setAttribute("listRarity", rarities);

            List<ElementHSR> elements = elementDAO.getAllElement();
            request.setAttribute("listElement", elements);

            List<PathHSR> path = pathDAO.getAllPath();
            request.setAttribute("listPath", path);

            request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
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
        if (action.equalsIgnoreCase("create")) {
            String name = request.getParameter("name");
            if (name != null) {
                name = name.trim();
            }
            if (name == null || name.isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Name must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            }
            if (name.matches(".*\\s{2,}.*")) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Name must not contain two or more consecutive spaces.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            }

            String rarityIdStr = request.getParameter("rarityId");
            int rarityId = -1;

            if (rarityIdStr == null || rarityIdStr.trim().isEmpty()) {
                
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Rarity ID must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    rarityId = Integer.parseInt(rarityIdStr.trim());

                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Rarity ID must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }

            String elementIdStr = request.getParameter("elementId");
            int elementId = -1;

            if (elementIdStr == null || elementIdStr.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Element ID must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    elementId = Integer.parseInt(elementIdStr.trim());

                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Element ID must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }

            String pathIdStr = request.getParameter("pathId");
            int pathId = -1;

            if (pathIdStr == null || pathIdStr.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Path ID must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    pathId = Integer.parseInt(pathIdStr.trim());

                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Path ID must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }

            String description = request.getParameter("description");

            if (description != null) {
                description = description.trim();
            }

            if (description == null || description.isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Description must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else if (description.length() > 500) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Description must not exceed 500 characters.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else if (description.matches(".*\\s{2,}.*")) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Description must not contain two or more consecutive spaces.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            }

            Part filePartIcon = request.getPart("imageIcon");
            String fileNameIcon = Paths.get(filePartIcon.getSubmittedFileName()).getFileName().toString();
            String imagePathIcon;
            if (fileNameIcon == null || fileNameIcon.isEmpty()) {

                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "File Icon must not be empty");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;

            } else {

                String lowerFileName = fileNameIcon.toLowerCase();
                if (!(lowerFileName.endsWith(".jpg") || lowerFileName.endsWith(".jpeg") || lowerFileName.endsWith(".png"))) {

                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Only JPG, JPEG, PNG files are allowed.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
                if (filePartIcon.getSize() > 5 * 1024 * 1024) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "File size must be less than 5MB.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
                imagePathIcon = "images/" + fileNameIcon;
            }

            Part filePartFull = request.getPart("imageFull");
            String fileNameFull = Paths.get(filePartFull.getSubmittedFileName()).getFileName().toString();
            String imagePathFull;
            if (fileNameFull == null || fileNameFull.isEmpty()) {

                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "File Full must not be empty");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;

            

            } else {
                String lowerFileName = fileNameFull.toLowerCase();
                if (!(lowerFileName.endsWith(".jpg") || lowerFileName.endsWith(".jpeg") || lowerFileName.endsWith(".png"))) {

                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Only JPG, JPEG, PNG files are allowed for full image.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
                if (filePartFull.getSize() > 5 * 1024 * 1024) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Full image file size must be less than 5MB.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
                imagePathFull = "images/" + fileNameFull;
            }

            String statusStr = request.getParameter("status");
            int status = -1;

            if (statusStr == null || statusStr.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Status must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    status = Integer.parseInt(statusStr.trim());

                    if (status != 0 && status != 1) {
                        List<CharacterHSR> characters = characterDAO.getAllCharacter();
                        request.setAttribute("listCharacter", characters);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<ElementHSR> elements = elementDAO.getAllElement();
                        request.setAttribute("listElement", elements);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);
                        request.setAttribute("err", "Status must be either 0 or 1.");
                        request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                        return;
                    }

                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Status must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }

            String pointE0Str = request.getParameter("pointE0");
            float pointE0;
            if (pointE0Str == null || pointE0Str.trim().isEmpty()) {
                
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "PointE0 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    pointE0 = Float.parseFloat(pointE0Str.trim());
                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "PointE0 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }
            String pointE1Str = request.getParameter("pointE1");
            float pointE1;
            if (pointE1Str == null || pointE1Str.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "PointE1 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    pointE1 = Float.parseFloat(pointE1Str.trim());
                    if (pointE1 < pointE0) {
                        List<CharacterHSR> characters = characterDAO.getAllCharacter();
                        request.setAttribute("listCharacter", characters);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<ElementHSR> elements = elementDAO.getAllElement();
                        request.setAttribute("listElement", elements);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);
                        request.setAttribute("err", "PointE1 must be greater than PointE0.");
                        request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "PointE1 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }
            String pointE2Str = request.getParameter("pointE2");
            float pointE2;
            if (pointE2Str == null || pointE2Str.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "PointE2 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    pointE2 = Float.parseFloat(pointE2Str.trim());
                    if (pointE2 < pointE1) {
                        List<CharacterHSR> characters = characterDAO.getAllCharacter();
                        request.setAttribute("listCharacter", characters);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<ElementHSR> elements = elementDAO.getAllElement();
                        request.setAttribute("listElement", elements);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);
                        request.setAttribute("err", "PointE2 must be greater than PointE1.");
                        request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "PointE2 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }
            String pointE3Str = request.getParameter("pointE3");
            float pointE3;
            if (pointE3Str == null || pointE3Str.trim().isEmpty()) {

                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "PointE3 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    pointE3 = Float.parseFloat(pointE3Str.trim());
                    if (pointE3 < pointE2) {

                        List<CharacterHSR> characters = characterDAO.getAllCharacter();
                        request.setAttribute("listCharacter", characters);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<ElementHSR> elements = elementDAO.getAllElement();
                        request.setAttribute("listElement", elements);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);
                        request.setAttribute("err", "PointE3 must be greater than PointE2.");
                        request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {

                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "PointE3 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }
            String pointE4Str = request.getParameter("pointE4");
            float pointE4;
            if (pointE4Str == null || pointE4Str.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "PointE4 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    pointE4 = Float.parseFloat(pointE4Str.trim());
                    if (pointE4 < pointE3) {
                        
                        List<CharacterHSR> characters = characterDAO.getAllCharacter();
                        request.setAttribute("listCharacter", characters);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<ElementHSR> elements = elementDAO.getAllElement();
                        request.setAttribute("listElement", elements);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);
                        request.setAttribute("err", "PointE4 must be greater than PointE3.");
                        request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "PointE4 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }
            String pointE5Str = request.getParameter("pointE5");
            float pointE5;
            if (pointE5Str == null || pointE5Str.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "PointE5 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    pointE5 = Float.parseFloat(pointE5Str.trim());
                    if (pointE5 < pointE4) {
                        List<CharacterHSR> characters = characterDAO.getAllCharacter();
                        request.setAttribute("listCharacter", characters);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<ElementHSR> elements = elementDAO.getAllElement();
                        request.setAttribute("listElement", elements);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);
                        request.setAttribute("err", "PointE5 must be greater than PointE4.");
                        request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "PointE5 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }
            String pointE6Str = request.getParameter("pointE6");
            float pointE6;
            if (pointE6Str == null || pointE6Str.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "PointE6 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;

            } else {
                try {
                    pointE6 = Float.parseFloat(pointE6Str.trim());
                    if (pointE6 < pointE5) {
                        List<CharacterHSR> characters = characterDAO.getAllCharacter();
                        request.setAttribute("listCharacter", characters);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<ElementHSR> elements = elementDAO.getAllElement();
                        request.setAttribute("listElement", elements);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);
                        request.setAttribute("err", "PointE6 must be greater than PointE5.");
                        request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "PointE6 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }

            try {
                int res = characterDAO.newCharacter(name, elementId, pathId, rarityId, imagePathIcon, imagePathFull,
                        description, status, pointE0, pointE1, pointE2, pointE3, pointE4, pointE5, pointE6);

                if (res == 1) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("success", "Character created successfully!!!");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);

                } else {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Fail to create Character!!!");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                }
            } catch (ServletException | IOException e) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Fail to create Character Exception!");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);

            }
        } else if (action.equalsIgnoreCase("edit")) {
            int characterId = Integer.parseInt(request.getParameter("characterId"));
            String name = request.getParameter("name");
            if (name != null) {
                name = name.trim();
            }
            if (name == null || name.isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Name must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            }
            if (name.matches(".*\\s{2,}.*")) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Name must not contain two or more consecutive spaces.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            }

            String rarityIdStr = request.getParameter("rarityId");
            int rarityId = -1;

            if (rarityIdStr == null || rarityIdStr.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Rarity ID must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    rarityId = Integer.parseInt(rarityIdStr.trim());

                } catch (NumberFormatException e) {

                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Rarity ID must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }

            String elementIdStr = request.getParameter("elementId");
            int elementId = -1;

            if (elementIdStr == null || elementIdStr.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Element ID must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    elementId = Integer.parseInt(elementIdStr.trim());

                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Element ID must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }

            String pathIdStr = request.getParameter("pathId");
            int pathId = -1;

            if (pathIdStr == null || pathIdStr.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Path ID must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    pathId = Integer.parseInt(pathIdStr.trim());

                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Path ID must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }

            String description = request.getParameter("description");

            if (description != null) {
                description = description.trim();
            }

            if (description == null || description.isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Description must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else if (description.length() > 500) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Description must not exceed 500 characters.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else if (description.matches(".*\\s{2,}.*")) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Description must not contain two or more consecutive spaces.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            }

            Part filePartIcon = request.getPart("imageIcon");
            String fileNameIcon = Paths.get(filePartIcon.getSubmittedFileName()).getFileName().toString();
            String imagePathIcon;
            if (fileNameIcon == null || fileNameIcon.isEmpty()) {

                imagePathIcon = request.getParameter("oldImageIcon");
            } else {

                String lowerFileName = fileNameIcon.toLowerCase();
                if (!(lowerFileName.endsWith(".jpg") || lowerFileName.endsWith(".jpeg") || lowerFileName.endsWith(".png"))) {

                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Only JPG, JPEG, PNG files are allowed.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
                if (filePartIcon.getSize() > 5 * 1024 * 1024) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "File size must be less than 5MB.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
                imagePathIcon = "images/" + fileNameIcon;
            }

            Part filePartFull = request.getPart("imageFull");
            String fileNameFull = Paths.get(filePartFull.getSubmittedFileName()).getFileName().toString();
            String imagePathFull;
            if (fileNameFull == null || fileNameFull.isEmpty()) {

                imagePathFull = request.getParameter("oldImageFull");

            } else {
                String lowerFileName = fileNameFull.toLowerCase();
                if (!(lowerFileName.endsWith(".jpg") || lowerFileName.endsWith(".jpeg") || lowerFileName.endsWith(".png"))) {

                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Only JPG, JPEG, PNG files are allowed for full image.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
                if (filePartFull.getSize() > 5 * 1024 * 1024) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Full image file size must be less than 5MB.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
                imagePathFull = "images/" + fileNameFull;
            }

            String statusStr = request.getParameter("status");
            int status = -1;

            if (statusStr == null || statusStr.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Status must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    status = Integer.parseInt(statusStr.trim());

                    if (status != 0 && status != 1) {
                        List<CharacterHSR> characters = characterDAO.getAllCharacter();
                        request.setAttribute("listCharacter", characters);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<ElementHSR> elements = elementDAO.getAllElement();
                        request.setAttribute("listElement", elements);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);
                        request.setAttribute("err", "Status must be either 0 or 1.");
                        request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                        return;
                    }

                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Status must be a valid integer.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }

            String pointE0Str = request.getParameter("pointE0");
            float pointE0;
            if (pointE0Str == null || pointE0Str.trim().isEmpty()) {
                
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "PointE0 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    pointE0 = Float.parseFloat(pointE0Str.trim());
                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "PointE0 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }
            String pointE1Str = request.getParameter("pointE1");
            float pointE1;
            if (pointE1Str == null || pointE1Str.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "PointE1 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    pointE1 = Float.parseFloat(pointE1Str.trim());
                    if (pointE1 < pointE0) {
                        List<CharacterHSR> characters = characterDAO.getAllCharacter();
                        request.setAttribute("listCharacter", characters);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<ElementHSR> elements = elementDAO.getAllElement();
                        request.setAttribute("listElement", elements);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);
                        request.setAttribute("err", "PointE1 must be greater than PointE0.");
                        request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "PointE1 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }
            String pointE2Str = request.getParameter("pointE2");
            float pointE2;
            if (pointE2Str == null || pointE2Str.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "PointE2 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    pointE2 = Float.parseFloat(pointE2Str.trim());
                    if (pointE2 < pointE1) {
                        List<CharacterHSR> characters = characterDAO.getAllCharacter();
                        request.setAttribute("listCharacter", characters);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<ElementHSR> elements = elementDAO.getAllElement();
                        request.setAttribute("listElement", elements);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);
                        request.setAttribute("err", "PointE2 must be greater than PointE1.");
                        request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "PointE2 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }
            String pointE3Str = request.getParameter("pointE3");
            float pointE3;
            if (pointE3Str == null || pointE3Str.trim().isEmpty()) {

                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "PointE3 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    pointE3 = Float.parseFloat(pointE3Str.trim());
                    if (pointE3 < pointE2) {

                        List<CharacterHSR> characters = characterDAO.getAllCharacter();
                        request.setAttribute("listCharacter", characters);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<ElementHSR> elements = elementDAO.getAllElement();
                        request.setAttribute("listElement", elements);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);
                        request.setAttribute("err", "PointE3 must be greater than PointE2.");
                        request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {

                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "PointE3 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }
            String pointE4Str = request.getParameter("pointE4");
            float pointE4;
            if (pointE4Str == null || pointE4Str.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "PointE4 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    pointE4 = Float.parseFloat(pointE4Str.trim());
                    if (pointE4 < pointE3) {
                        
                        List<CharacterHSR> characters = characterDAO.getAllCharacter();
                        request.setAttribute("listCharacter", characters);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<ElementHSR> elements = elementDAO.getAllElement();
                        request.setAttribute("listElement", elements);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);
                        request.setAttribute("err", "PointE4 must be greater than PointE3.");
                        request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "PointE4 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }
            String pointE5Str = request.getParameter("pointE5");
            float pointE5;
            if (pointE5Str == null || pointE5Str.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "PointE5 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;
            } else {
                try {
                    pointE5 = Float.parseFloat(pointE5Str.trim());
                    if (pointE5 < pointE4) {
                        List<CharacterHSR> characters = characterDAO.getAllCharacter();
                        request.setAttribute("listCharacter", characters);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<ElementHSR> elements = elementDAO.getAllElement();
                        request.setAttribute("listElement", elements);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);
                        request.setAttribute("err", "PointE5 must be greater than PointE4.");
                        request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "PointE5 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }
            String pointE6Str = request.getParameter("pointE6");
            float pointE6;
            if (pointE6Str == null || pointE6Str.trim().isEmpty()) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "PointE6 must not be empty.");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                return;

            } else {
                try {
                    pointE6 = Float.parseFloat(pointE6Str.trim());
                    if (pointE6 < pointE5) {
                        List<CharacterHSR> characters = characterDAO.getAllCharacter();
                        request.setAttribute("listCharacter", characters);

                        List<Rarity> rarities = rarityDAO.getAllRarity();
                        request.setAttribute("listRarity", rarities);

                        List<ElementHSR> elements = elementDAO.getAllElement();
                        request.setAttribute("listElement", elements);

                        List<PathHSR> path = pathDAO.getAllPath();
                        request.setAttribute("listPath", path);
                        request.setAttribute("err", "PointE6 must be greater than PointE5.");
                        request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                        return;
                    }
                } catch (NumberFormatException e) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "PointE6 must be a valid float.");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                    return;
                }
            }

            try {
                int res = characterDAO.editCharacter(name, elementId, pathId, rarityId, imagePathIcon, imagePathFull,
                        description, status, pointE0, pointE1, pointE2, pointE3, pointE4, pointE5, pointE6, characterId);

                if (res == 1) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("success", "Character edited successfully!!!");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);

                } else {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Fail to edit Character!!!");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
                }
            } catch (ServletException | IOException e) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Fail to edit Character Exception!");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);
            }
        } else if (action.equalsIgnoreCase("delete")) {
            String idRaw = request.getParameter("id");
            int id;
            try {
                id = Integer.parseInt(idRaw);
                if (characterDAO.deleteCharacter(id) == 1) {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("success", "Character deleted successfully!!!");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);

                } else {
                    List<CharacterHSR> characters = characterDAO.getAllCharacter();
                    request.setAttribute("listCharacter", characters);

                    List<Rarity> rarities = rarityDAO.getAllRarity();
                    request.setAttribute("listRarity", rarities);

                    List<ElementHSR> elements = elementDAO.getAllElement();
                    request.setAttribute("listElement", elements);

                    List<PathHSR> path = pathDAO.getAllPath();
                    request.setAttribute("listPath", path);
                    request.setAttribute("err", "Fail to delete Character!");
                    request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);

                }
            } catch (NumberFormatException e) {
                List<CharacterHSR> characters = characterDAO.getAllCharacter();
                request.setAttribute("listCharacter", characters);

                List<Rarity> rarities = rarityDAO.getAllRarity();
                request.setAttribute("listRarity", rarities);

                List<ElementHSR> elements = elementDAO.getAllElement();
                request.setAttribute("listElement", elements);

                List<PathHSR> path = pathDAO.getAllPath();
                request.setAttribute("listPath", path);
                request.setAttribute("err", "Fail to delete Character Exception!");
                request.getRequestDispatcher("/WEB-INF/views/character.jsp").forward(request, response);

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
