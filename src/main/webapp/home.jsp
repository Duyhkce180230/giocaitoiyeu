<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Le Back's Star Rail PVP Draft Tool</title>
        <meta charset="UTF-8">
        <link rel="icon" type="image/png" href="images/LogoGC.png">
        
        <style>
            body {
                background: url("${pageContext.request.contextPath}/images/BackgroundGioCai.jpg") no-repeat center center fixed;
                background-size: cover;
                color: white;
                font-family: "Rajdhani", Arial, sans-serif;
                margin: 0;
                padding: 0;
            }

            /* Thanh header */
            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: #3a2573;
                padding: 15px 30px;
                box-shadow: 0 2px 10px rgba(0, 0, 0, 0.5);
                position: sticky;
                top: 0;
                z-index: 1000;
            }

            .header h1 {
                margin: 0;
                font-size: 28px;
                font-weight: bold;
            }

            .login-btn {
                background-color: #5b2e8c;
                color: white;
                padding: 10px 25px;
                border: none;
                border-radius: 8px;
                text-decoration: none;
                font-size: 16px;
                transition: background 0.3s ease;
            }

            .login-btn:hover {
                background-color: #7a4bc7;
            }

            /* Khu vực chính */
            .main-container {
                display: flex;
                flex-direction: column;
                align-items: center;
                margin-top: 60px;
                gap: 20px;
            }

            .button {
                background-color: #5b2e8c;
                color: white;
                padding: 15px 30px;
                border: none;
                border-radius: 10px;
                cursor: pointer;
                width: 300px;
                text-align: center;
                text-decoration: none;
                font-size: 18px;
                transition: background 0.3s ease, transform 0.2s ease;
            }

            .button:hover {
                background-color: #7a4bc7;
                transform: scale(1.05);
            }

            /* Khu vực draft */
            #draftArea {
                text-align: center;
                margin-top: 40px;
            }
        </style>
    </head>

    <body>

        <!-- Header với nút Login/Logout -->
        <div class="header">
            <h1>Le Bach's Star Rail PVP Draft Tool</h1>
            <c:choose>
                <c:when test="${not empty sessionScope.user}">
                    <a class="login-btn" href="${pageContext.request.contextPath}/logout">Logout</a>
                </c:when>
                <c:otherwise>
                    <a class="login-btn" href="${pageContext.request.contextPath}/login">Login</a>
                </c:otherwise>
            </c:choose>
        </div>


        <!-- Các nút chính -->
        <div class="main-container">
            <a class="button" href="${pageContext.request.contextPath}/banpick">Ban pick</a>
            <a class="button" href="${pageContext.request.contextPath}/character">Character</a>
            <a class="button" href="${pageContext.request.contextPath}/lightcone">Light Cone</a>
            <a class="button" href="${pageContext.request.contextPath}/teamtest">Calculator</a>
        </div>

        <div id="draftArea"></div>

    </body>
</html>
