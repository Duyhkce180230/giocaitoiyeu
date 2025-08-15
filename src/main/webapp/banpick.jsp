<!DOCTYPE html>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta charset="UTF-8">
        <title>Separated Draft UI</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">

        <style>
            * {
                box-sizing: border-box;
            }


            html, body {
                margin: 0;
                padding: 0;
                font-family: 'Segoe UI', sans-serif;
                font-size: 16px;
                line-height: 1.5;
                color: white;
                background-color: #1f112b;
            }

            /* SETTINGS SECTION */
            .settings-section {
                background-color: #381e4b;
                padding: 30px 20px;
                display: flex;
                flex-direction: column;
                align-items: center;
                gap: 16px;
            }

            .settings-section h2 {
                color: white;
                margin-bottom: 10px;
            }

            .settings-row {
                display: flex;
                gap: 20px;
                flex-wrap: wrap;
                justify-content: center;
            }

            .settings-section input[type="text"],
            .settings-section select {
                padding: 8px 10px;
                border-radius: 5px;
                border: none;
                font-size: 14px;
            }

            .settings-section button {
                padding: 10px 16px;
                font-size: 14px;
                background-color: #4b2e60;
                color: white;
                border: 1px solid #9e80c0;
                border-radius: 8px;
                cursor: pointer;
            }

            .settings-section label {
                margin-left: 10px;
                color: white;
            }

            .settings-section .dropdown {
                background-color: #7757a3;
                color: white;
            }

            /* DRAFT SECTION */
            .draft-section {
                padding-left: 0;
                padding-right: 0;
                margin-left: 0;
                display: flex;
                background-color: #2d1b40;
                color: white;
                padding: 30px 0;
            }

            .container {
                display: flex;
                width: 100%;
            }

            .team-section {
                flex: 1;
                background-color: #2d1b40;
                padding: 6px;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .team-header {
                width: 100%;
                padding: 10px 15px 10px 15px;
                text-align: center;
                color: white;
                margin-bottom: 10px;
                display: flex;
                justify-content: space-between;
                align-items: center;
                font-size: 20px;
                border-radius: 6px;

            }

            .blue {
                background-color: #5f93f3;
            }

            .red {
                background-color: #dc5d5d;
            }

            .slot {
                display: flex;
                align-items: center;
                justify-content: center;
                width: 500px;
                height: 80px;
                background-color: #6f5b81;
                border-radius: 8px;
                margin-bottom: 5px;
                font-size: 14px;
                font-weight: 500;
                overflow: hidden;
            }
            .slot img {
                width: 280px;
                height: 230px;
                object-fit: contain;
                border-radius: 0.3em;
                margin-top: 50px;
                filter: grayscale(100%);
                background-color: grey;
            }


            .slot-row {
                text-align: center;
                display: flex;
                gap: 6px;
                width: 100%;
                margin-bottom:6px;
                min-height: 10px;
            }

            .slot-half {
                display: flex;
                align-items: center;
                justify-content: center;
                width: 247px;
                height: 80px;
                background-color: #4b2e60;
                border-radius: 6px;
                overflow: hidden;
            }
            .slot-half img{
                width: 400px;
                height: 300px;
                object-fit: contain;
                border-radius: 0.3em;
                margin-top: 120px;
            }

            .center {
                flex: 1;
                background-color: #2d1b40;
                padding: 5px;
                display: flex;
                flex-direction: column;
                align-items: center;
            }


            .search-bar {
                width: 100%;
                padding: 10px;
                font-size: 14px;
                margin-bottom: 20px;
                border-radius: 6px;
                border: none;
            }

            .grid {
                display: grid;
                grid-template-columns: repeat(6, 1fr);
                gap: 10px;
            }

            .grid div {
                transition: transform 0.2s ease, box-shadow 0.2s ease;
            }

            .grid div:hover {
                transform: scale(1.1);
                box-shadow: 0 4px 15px rgba(0, 0, 0, 0.4);
                z-index: 5;
            }


            .cell {
                width: 75px;
                height: 75px;
                background-color: #b287e0;
                border-radius: 6px;
            }

            .tip {
                margin-top: 20px;
                font-size: 12px;
                color: #ccc;
            }


            .character-img {
                width: 100%;
                height: auto;
                border-radius: 6px;
            }
            .char-controls {
                background-color: #2d1b40;
                color: white;
                display: flex;
                gap: 5px;
                margin-top: 5px;
            }

            .char-controls select,
            .char-controls input {
                max-width: 145px;
                padding: 4px;
                background-color: #6a4e7b;
                color: white;
                border-radius: 4px;

            }

            .char-controls input::placeholder {
                color: white;
                opacity: 0.5;
            }
            .char-controls input {

            }

            .timing-container {
                display: flex;
                justify-content: center;
                align-items: center;
                gap: 12px;
                font-family: Arial, sans-serif;
                margin-bottom: 10px;
            }

            .time-box {
                background-color: #3a2356;
                padding: 6px 14px;
                border-radius: 8px;
                min-width: 85px;
                text-align: center;
                box-shadow: 0 0 8px rgba(0,0,0,0.4);
            }

            .time-box .label {
                font-size: 12px;
                color: #d8b6ff;
                margin-bottom: 2px;
            }

            .time-box .value {
                font-size: 20px;
                font-weight: bold;
                color: #ffd369;
            }

            .time-box .time-general {
                font-size: 24px;
                color: #ffffff;
            }
            .modal {
                display: none;
                position: fixed;
                z-index: 9999;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5);
                font-family: "Rajdhani", sans-serif;
            }


            .modal-content {
                background-color: #2a2a2a;
                margin: 15% auto;
                padding: 20px;
                border-radius: 8px;
                width: 320px;
                box-shadow: 0 0 10px #000;
                color: #fff;
            }


            .modal-content h3 {
                margin: 0 0 15px 0;
                font-size: 18px;
                font-weight: 500;
            }


            .custom-select {
                border: 1px solid #66ccff;
                border-radius: 4px;
                background: #1f1f1f;
                color: #fff;
                cursor: pointer;
                position: relative;
                user-select: none;
            }


            .custom-select .options::-webkit-scrollbar {
                width: 8px;
            }

            .custom-select .options::-webkit-scrollbar-track {
                background: transparent;
            }

            .custom-select .options::-webkit-scrollbar-thumb {
                background-color: #2196f3;
                border-radius: 4px;
            }

            .custom-select .options::-webkit-scrollbar-thumb:hover {
                background-color: #1976d2;
            }

            /* Firefox */
            .custom-select .options {
                scrollbar-width: thin;
                scrollbar-color: #7f8c8d transparent;
            }


            .custom-select .selected {
                padding: 8px;
                color: #999;
            }

            .custom-select .options {
                display: none;
                position: absolute;
                top: 100%;
                left: 0;
                width: 100%;
                background: #1f1f1f;
                border: 1px solid #66ccff;
                max-height: 200px;
                overflow-y: auto;
                z-index: 10000;
            }

            .custom-select .option {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 8px;
                cursor: pointer;
            }

            .custom-select .option:hover {
                background: #333;
            }

            .custom-select .option-text .title {
                font-size: 14px;
            }

            .custom-select .option-text .sub {
                font-size: 12px;
                color: #aaa;
            }

            .custom-select img {
                width: 40px;
                height: auto;
                border-radius: 4px;
            }


            .modal-actions {
                display: flex;
                justify-content: flex-end;
                gap: 10px;
                margin-top: 15px;
            }

            .modal-actions button {
                background: none;
                border: none;
                color: #66ccff;
                font-size: 14px;
                cursor: pointer;
                padding: 0;
            }

            .modal-actions button:hover {
                text-decoration: underline;
            }

            .custom-btn {
                background-color: #3b2e60;
                color: #e2d9f3;
                border: none;
                border-radius: 6px 0 6px 0;
                padding: 8px 16px;
                font-weight: 500;
                cursor: pointer;
                transition: background-color 0.3s ease;
                font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
                font-size: 14px;
            }

            .custom-btn:hover {
                background-color: #5a4989;
            }

            .button-group {
                width: 100%;
            }

            #lightconeSearch {
                width: 100%;
                padding: 10px 36px 10px 12px;
                border: 1px solid rgba(255, 255, 255, 0.2);
                border-radius: 4px;
                background-color: transparent;
                color: #fff;
                font-size: 14px;
                font-family: "Rajdhani", sans-serif;
                outline: none;
                appearance: none;
                transition: border-color 0.2s ease;
            }

            #lightconeSearch::placeholder {
                color: rgba(255, 255, 255, 0.4);
            }

            #lightconeSearch:focus {
                border-color: #66ccff;
            }

            .search-wrapper {
                position: relative;
            }

            .search-wrapper::after {
                content: "";
                position: absolute;
                right: 12px;
                top: 50%;
                width: 8px;
                height: 8px;
                border-right: 2px solid rgba(255, 255, 255, 0.6);
                border-bottom: 2px solid rgba(255, 255, 255, 0.6);
                transform: translateY(-50%) rotate(45deg);
                pointer-events: none;
            }


            .scorecalc-container {
                background-color: #3b1a5a;
                border-radius: 10px;
                padding: 20px;
                width: 480px;
                margin: 50px auto;
                color: white;
                text-align: center;
                font-family: Arial, sans-serif;
            }
            .scorecalc-teams {
                display: flex;
                justify-content: space-between;
                margin-bottom: 15px;
            }
            .scorecalc-team {
                display: flex;
                flex-direction: column;
                gap: 10px;
                width: 45%;
            }
            .scorecalc-team h3 {
                margin-bottom: 8px;
                text-align: center;
            }
            .scorecalc-input {
                background-color: #7a4d94;
                border: none;
                border-radius: 5px;
                padding: 6px;
                color: white;
                text-align: center;
                font-size: 14px;
            }
            .scorecalc-input::placeholder {
                color: #d2bde5;
            }
            .scorecalc-diff {
                color: #fff;
                margin: 10px 0;
                font-size: 16px;
            }
            .scorecalc-btn {
                background-color: #5fcf80;
                border: none;
                padding: 8px 15px;
                border-radius: 5px;
                font-size: 16px;
                color: white;
                cursor: pointer;
            }
            .scorecalc-btn:hover {
                background-color: #4bb36a;
            }
            .back-btn {

                display: inline-block;
                background-color: #5b2e8c;
                color: white;
                padding: 8px 18px;
                border-radius: 6px;
                text-decoration: none;
                font-size: 15px;
                font-weight: 500;
                transition: background-color 0.25s ease, box-shadow 0.25s ease;
            }

            .back-btn:hover {
                background-color: #7a4bc7;
                box-shadow: 0 2px 6px rgba(0,0,0,0.3);
            }
            .back-btn-container {
                position: absolute;
                top: 20px;
                left: 20px;
            }

            .roll-box {
                display: flex;
                flex-direction: column;
                align-items: center;
                background-color: #4b2e60;
                border: 1px solid #9e80c0;
                border-radius: 10px;
                padding: 15px;
                width: 120px;
            }

            .roll-value {
                font-size: 22px;
                font-weight: bold;
                color: #fff;
                margin-bottom: 10px;
            }




        </style>
    </head>
    <body>

        <!-- SETTINGS SECTION -->
        <div class="settings-section">
            <div class="back-btn-container">
                <a href="${pageContext.request.contextPath}/Home" class="back-btn">Back</a>
            </div>
            <h2>Settings</h2>
            <div class="settings-row">
                <input type="text" id="team1-input" placeholder="Team 1 Name">
                <input type="text" id="team2-input" placeholder="Team 2 Name">
            </div>
            <div class="settings-row">
                <!-- Team 1 Roll -->
                <div class="roll-box">
                    <div class="roll-value" id="team1-roll-value">0</div>
                    <button onclick="rollForTeam(1)">Roll for Team 1</button>
                </div>
                <!-- Team 2 Roll -->
                <div class="roll-box">
                    <div class="roll-value" id="team2-roll-value">0</div>
                    <button onclick="rollForTeam(2)">Roll for Team 2</button>
                </div>
            </div>
        </div>
        <!-- DRAFT SECTION -->
        <div class="draft-section container-fluid" >
            <div class="container">
                <!-- Team 1 -->
                <div class="team-section">
                    <div class="team-header blue">
                        <div id="team1-name">Team 1</div>
                        <div id="team1-score">0/30</div>
                    </div>
                    <div class="slot" id="slot-1">Banning... (1)</div>
                    <div class="slot-row">
                        <div class="slot-half pick-slot" id="slot-3">Picking... (3)</div>  
                        <div class="slot-half pick-slot" id="slot-6">Picking... (6)</div>
                    </div>
                    <div class="slot" id="slot-8">Banning... (8)</div>
                    <div class="slot-row">
                        <div class="slot-half pick-slot" id="slot-10">Picking... (10)</div>
                        <div class="slot-half pick-slot" id="slot-11">Picking... (11)</div>
                    </div>
                    <div class="slot-row">
                        <div class="slot-half pick-slot" id="slot-14">Picking... (14)</div>
                        <div class="slot-half pick-slot" id="slot-15">Picking... (15)</div>
                    </div>
                    <div class="slot-row">
                        <div class="slot-half pick-slot" id="slot-18">Picking... (18)</div>
                        <div class="slot-half pick-slot" id="slot-19">Picking... (19)</div>
                    </div>
                </div>

                <!-- Center Grid -->
                <div class="center">
                    <div class="timing-container">
                        <div class="time-box">
                            <div class="label">Reserve Time</div>
                            <div class="value" id="team1-reserve">9:30</div>
                        </div>
                        <div class="time-box">
                            <div class="label">Time Penalty</div>
                            <div class="value" id="team1-penalty">0:00</div>
                        </div>
                        <div class="time-box">
                            <div class="label">Time General</div>
                            <div class="value time-general">30</div>
                        </div>
                        <div class="time-box">
                            <div class="label">Time Penalty</div>
                            <div class="value" id="team2-penalty">0:00</div>
                        </div>
                        <div class="time-box">
                            <div class="label">Reserve Time</div>
                            <div class="value" id="team2-reserve">9:30</div>
                        </div>
                    </div>
                    <div class="button-group" style="display: flex; justify-content: space-between; margin: 10px 0;">
                        <button id="undoBtn" class="custom-btn">Undo</button>
                        <button id="resetBtn" class="custom-btn">Reset</button>
                    </div>

                    <input type="text" class="search-bar" id="searchInput" placeholder="Search characters">
                    <div class="grid">
                        <c:forEach var="character" items="${listCharacterBanPick}">
                            <c:choose>
                                <c:when test="${character.rarityID.starName == '5-Star'}">

                                    <div style="width: 70px; height: 70px;
                                         border-radius: 0.3em; align-items: center; justify-content: center;
                                         background-color: rgb(230, 183, 65); filter: none; display: flex; cursor: pointer;">
                                        <img src="${pageContext.request.contextPath}/${character.imageIcon}" 
                                             alt="${character.characterName}" 
                                             data-character-id="${character.characterID}"
                                             data-point-e0="${character.pointE0}"
                                             data-point-e1="${character.pointE1}"
                                             data-point-e2="${character.pointE2}"
                                             data-point-e3="${character.pointE3}"
                                             data-point-e4="${character.pointE4}"
                                             data-point-e5="${character.pointE5}"
                                             data-point-e6="${character.pointE6}"
                                             data-full-image="${pageContext.request.contextPath}/${character.imageFull}"

                                             style="width: 100%; height: 100%; object-fit: cover; border-radius: 0.3em;" />
                                    </div>
                                </c:when>
                                <c:when test="${character.rarityID.starName == '4-Star'}">

                                    <div style="width: 70px; height: 70px;
                                         border-radius: 0.3em; align-items: center; justify-content: center;
                                         background-color: #9b59b6; filter: none; display: flex; cursor: pointer;">
                                        <img src="${pageContext.request.contextPath}/${character.imageIcon}" 
                                             alt="${character.characterName}" 
                                             data-character-id="${character.characterID}"
                                             data-point-e0="${character.pointE0}"
                                             data-point-e1="${character.pointE1}"
                                             data-point-e2="${character.pointE2}"
                                             data-point-e3="${character.pointE3}"
                                             data-point-e4="${character.pointE4}"
                                             data-point-e5="${character.pointE5}"
                                             data-point-e6="${character.pointE6}"
                                             data-full-image="${pageContext.request.contextPath}/${character.imageFull}"
                                             style="width: 100%; height: 100%; object-fit: cover; border-radius: 0.3em;" />
                                    </div>
                                </c:when>
                                <c:otherwise>

                                    <div style="width: 70px; height: 70px;
                                         border-radius: 0.3em; align-items: center; justify-content: center;
                                         background-color: #7f8c8d; filter: none; display: flex; cursor: pointer;">
                                        <img src="${pageContext.request.contextPath}/${character.imageIcon}" 
                                             alt="${character.characterName}" 
                                             data-character-id="${character.characterID}"
                                             data-point-e0="${character.pointE0}"
                                             data-point-e1="${character.pointE1}"
                                             data-point-e2="${character.pointE2}"
                                             data-point-e3="${character.pointE3}"
                                             data-point-e4="${character.pointE4}"
                                             data-point-e5="${character.pointE5}"
                                             data-point-e6="${character.pointE6}"
                                             data-full-image="${pageContext.request.contextPath}/${character.imageFull}"
                                             style="width: 100%; height: 100%; object-fit: cover; border-radius: 0.3em;" />
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </div>
                    <div class="scorecalc-container" style="display: none;" >
                        <div class="scorecalc-teams">
                            <div class="scorecalc-team scorecalc-blue">
                                <h3>Blue Team</h3>
                                <input type="number" id="blue_p1" class="scorecalc-input" min="1" step="1" placeholder="Blue First-Half Cycles">
                                <input type="number" id="blue_p2" class="scorecalc-input" min="1" step="1"placeholder="Blue Second-Half Cycles">
                                <input type="number" id="blue_deaths" class="scorecalc-input" min="1" step="1" placeholder="Blue Team Deaths">
                                <div id="blue_point">Point: 0</div>
                            </div>

                            <div class="scorecalc-team scorecalc-red">
                                <h3>Red Team</h3>
                                <input type="number" id="red_p1" class="scorecalc-input" min="1" step="1" placeholder="Red First-Half Cycles">
                                <input type="number" id="red_p2" class="scorecalc-input" min="1" step="1" placeholder="Red Second-Half Cycles">
                                <input type="number" id="red_deaths" class="scorecalc-input" min="1" step="1" placeholder="Red Team Deaths">
                                <div id="red_point">Point: 0</div>
                            </div>
                        </div>

                        <div class="scorecalc-diff">
                            Cycle difference: <span id="scorecalc-difference">0</span>
                        </div>
                        <div id="scorecalc-result"></div>
                        <div id="scorecalc-winner"></div>
                    </div>

                </div>

                <!-- Team 2 -->
                <div class="team-section">
                    <div class="team-header red">
                        <div id="team2-name">Team 2</div>
                        <div id="team2-score">0/30</div>
                    </div>
                    <div class="slot" id="slot-2">Banning... (2)</div>
                    <div class="slot-row">
                        <div class="slot-half pick-slot" id="slot-4">Picking... (4)</div>
                        <div class="slot-half pick-slot" id="slot-5">Picking... (5)</div>
                    </div>
                    <div class="slot" id="slot-7">Banning... (7)</div>
                    <div class="slot-row">
                        <div class="slot-half pick-slot" id="slot-9">Picking... (9)</div>
                        <div class="slot-half pick-slot" id="slot-12">Picking... (12)</div>
                    </div>
                    <div class="slot-row">
                        <div class="slot-half pick-slot" id="slot-13">Picking... (13)</div>
                        <div class="slot-half pick-slot" id="slot-16">Picking... (16)</div>
                    </div>
                    <div class="slot-row">
                        <div class="slot-half pick-slot" id="slot-17">Picking... (17)</div>
                        <div class="slot-half pick-slot" id="slot-20">Picking... (20)</div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function rollForTeam(team) {
                var roll = Math.floor(Math.random() * 100) + 1;
                document.getElementById("team" + team + "-roll-value").textContent = roll;
            }

        </script>
        <script>
            const team1Input = document.getElementById('team1-input');
            const team2Input = document.getElementById('team2-input');
            const team1NameDisplay = document.getElementById('team1-name');
            const team2NameDisplay = document.getElementById('team2-name');

            team1Input.addEventListener('input', function () {
                team1NameDisplay.textContent = team1Input.value || 'Team 1';
            });

            team2Input.addEventListener('input', function () {
                team2NameDisplay.textContent = team2Input.value || 'Team 2';
            });
            const team1Score = document.getElementById('team1-score');
            const team2Score = document.getElementById('team2-score');
            const bluePoint = document.getElementById('blue_point');
            const redPoint = document.getElementById('red_point');
            const diffElement = document.getElementById('scorecalc-difference');
            const winnerElement = document.getElementById('scorecalc-winner');

            const blue_p1 = document.getElementById('blue_p1');
            const blue_p2 = document.getElementById('blue_p2');
            const blue_deaths = document.getElementById('blue_deaths');

            const red_p1 = document.getElementById('red_p1');
            const red_p2 = document.getElementById('red_p2');
            const red_deaths = document.getElementById('red_deaths');

            function getBaseScore(scoreElement) {
                return parseFloat(scoreElement.textContent.split('/')[0].trim()) || 0;
            }

            function calculateAdditionalScore(p1, p2, deaths) {
                const val1 = parseFloat(p1.value) || 0;
                const val2 = parseFloat(p2.value) || 0;
                const val3 = parseFloat(deaths.value) || 0;
                return (val1 + val2 + val3) * 5;
            }

            function calculateFinalPoint(score) {
                let adjusted = score - 30;
                return adjusted > 0 ? adjusted / 5 : adjusted / 6;
            }

            function updateScores() {
                let blueBaseScore = getBaseScore(team1Score);
                let blueAdditional = calculateAdditionalScore(blue_p1, blue_p2, blue_deaths);
                let blueTotalScore = blueBaseScore + blueAdditional;
                let bluePointValue = calculateFinalPoint(blueTotalScore);
                if (bluePoint)
                    bluePoint.textContent = 'Point: ' + bluePointValue.toFixed(4);

                let redBaseScore = getBaseScore(team2Score);
                let redAdditional = calculateAdditionalScore(red_p1, red_p2, red_deaths);
                let redTotalScore = redBaseScore + redAdditional;
                let redPointValue = calculateFinalPoint(redTotalScore);
                if (redPoint)
                    redPoint.textContent = 'Point: ' + redPointValue.toFixed(4);

                if (team1Score && team2Score && diffElement) {
                    let t1 = blueBaseScore;
                    let t2 = redBaseScore;
                    let t1Cycle = t1 / 5;
                    let t2Cycle = t2 / 5;
                    let diff = Math.abs(t1Cycle - t2Cycle).toFixed(4);
                    diffElement.textContent = diff;

                    if (t1Cycle < t2Cycle) {
                        diffElement.style.color = '#6d86f9';
                    } else if (t2Cycle < t1Cycle) {
                        diffElement.style.color = '#ca515f';
                    } else {
                        diffElement.style.color = '';
                    }
                }

                if (winnerElement) {
                    if (bluePointValue < redPointValue) {
                        winnerElement.textContent = "Blue Team wins!";
                        winnerElement.style.color = '#6d86f9';
                    } else if (redPointValue < bluePointValue) {
                        winnerElement.textContent = "Red Team wins!";
                        winnerElement.style.color = '#ca515f';
                    } else {
                        winnerElement.textContent = "It's a tie!";
                        winnerElement.style.color = '';
                    }
                }
            }

            [blue_p1, blue_p2, blue_deaths, red_p1, red_p2, red_deaths].forEach(input => {
                input.addEventListener('input', updateScores);
            });

            const observerConfig = {childList: true, characterData: true, subtree: true};
            const team1Observer = new MutationObserver(updateScores);
            const team2Observer = new MutationObserver(updateScores);
            if (team1Score)
                team1Observer.observe(team1Score, observerConfig);
            if (team2Score)
                team2Observer.observe(team2Score, observerConfig);

            updateScores();


        </script>
        <script>

            document.addEventListener("DOMContentLoaded", function () {


                const team1ScoreEl = document.querySelector("#team1-score");
                const team2ScoreEl = document.querySelector("#team2-score");
                const modal = document.getElementById("lightconeModal");
                const cancelBtn = document.getElementById("cancelBtn");
                const confirmBtn = document.getElementById("confirmBtn");
                const searchInput = document.getElementById("lightconeSearch");
                const customSelect = document.getElementById("lightconeSelectCustom");
                        const selectedDiv = customSelect?.querySelector(".selected");
                        const optionsContainer = customSelect?.querySelector(".options");

                if (!team1ScoreEl || !team2ScoreEl || !modal || !cancelBtn || !confirmBtn || !searchInput || !customSelect) {
                    console.error("Không tìm th?y các ph?n t? DOM c?n thi?t");
                    return;
                }


                const team1Slots = [1, 3, 6, 7, 10, 11, 14, 15, 18, 19]
                        .map(num => document.getElementById("slot-" + num))
                        .filter(Boolean);
                const team2Slots = [2, 4, 5, 8, 9, 12, 13, 16, 17, 20]
                        .map(num => document.getElementById("slot-" + num))
                        .filter(Boolean);
                const pickOrder = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
                        .map(num => document.getElementById("slot-" + num))
                        .filter(Boolean);


                let historyStack = [];
                const selectedCharacters = new Set();
                const MAX_SCORE = 30;
                let timers = {team1: {reserve: 14 * 60 + 59, penalty: 0}, team2: {reserve: 14 * 60 + 59, penalty: 0}};
                let generalTime = 30;
                let timerInterval = null;
                let currentPickIndex = 0;
                let activeInputLightCone = null;
                let activeLightConeImg = null;

                function formatTime(totalSeconds) {
                    let min = Math.floor(totalSeconds / 60);
                    let sec = totalSeconds % 60;
                    return min + ":" + (sec < 10 ? "0" + sec : sec);
                }

                function updateTimeDisplay(team) {
                    let reserveEl = document.getElementById(team + "-reserve");
                    reserveEl.textContent = formatTime(Math.max(timers[team].reserve, 0));
                    reserveEl.style.color = timers[team].reserve <= 0 ? "#ff0033" : "";
                    reserveEl.style.fontWeight = timers[team].reserve <= 0 ? "bold" : "";
                    document.getElementById(team + "-penalty").textContent = formatTime(timers[team].penalty);
                    document.querySelector(".time-general").textContent = generalTime;
                }

                function startTurn() {
                    if (timerInterval)
                        clearInterval(timerInterval);
                    generalTime = 30;

                    if (currentPickIndex >= pickOrder.length)
                        return;

                    let currentSlot = pickOrder[currentPickIndex];
                    let currentTeam = team1Slots.includes(currentSlot) ? "team1" : "team2";

                    timerInterval = setInterval(function () {
                        if (generalTime > 0) {
                            generalTime--;
                        } else {
                            if (timers[currentTeam].reserve > 0) {
                                timers[currentTeam].reserve--;
                            } else {
                                timers[currentTeam].penalty++;
                            }
                        }
                        updateTimeDisplay(currentTeam);
                    }, 1000);
                }

                function nextTurn() {
                    currentPickIndex++;
                    if (currentPickIndex >= 2) {
                        startTurn();
                    }
                }

                function updateTeamScore(teamSlots, scoreEl) {
                    let total = 0;
                    teamSlots.forEach(slot => {
                        let overlay = slot.querySelector(".char-point-overlay");
                        if (overlay) {
                            let val = parseFloat(overlay.textContent.replace("+", "")) || 0;
                            total += val;
                        }
                    });
                    scoreEl.textContent = total + "/" + MAX_SCORE;
                }


                document.querySelector(".grid").addEventListener("click", function (e) {
                    const img = e.target.closest("img[data-character-id]");
                    if (!img || selectedCharacters.has(img.getAttribute("data-character-id")))
                        return;

                    const emptySlot = pickOrder.find(s => !s.classList.contains("filled"));
                    if (!emptySlot)
                        return;

                    historyStack.push({
                        slot: emptySlot,
                        prevHTML: emptySlot.innerHTML,
                        prevClasses: [...emptySlot.classList],
                        prevStyle: emptySlot.getAttribute("style"),
                        characterId: img.getAttribute("data-character-id"),
                        parentDiv: img.parentElement,
                        parentPrevFilter: img.parentElement.style.filter,
                        parentPrevCursor: img.parentElement.style.cursor
                    });

                    const parentDiv = img.parentElement;
                    let bgColor = parentDiv.style.backgroundColor || "#7f8c8d";
                    if (emptySlot.classList.contains("slot")) {
                        bgColor = "grey";
                    }
                    emptySlot.style.backgroundColor = bgColor;
                    emptySlot.innerHTML = "";
                    emptySlot.style.display = "flex";
                    emptySlot.style.flexDirection = "column";
                    emptySlot.classList.add("filled");

                    const imgWrapper = document.createElement("div");
                    imgWrapper.style.display = "flex";
                    imgWrapper.style.alignItems = "center";
                    imgWrapper.style.justifyContent = "center";

                    const fullImgPath = img.getAttribute("data-full-image");
                    const newImg = document.createElement("img");
                    newImg.src = fullImgPath;
                    newImg.alt = img.alt;
                    newImg.style.maxWidth = "100%";
                    newImg.style.borderRadius = "4px";

                    if (emptySlot.classList.contains("slot-ban")) {
                        newImg.classList.add("ban-img");
                        emptySlot.classList.add("slot-ban");
                        emptySlot.style.border = "1px solid #28a745";
                        emptySlot.style.backgroundColor = "#28a745";
                    }

                    imgWrapper.appendChild(newImg);
                    emptySlot.appendChild(imgWrapper);

                    if (emptySlot.classList.contains("pick-slot") && !emptySlot.classList.contains("slot-ban")) {
                        const wrapperDiv = document.createElement("div");
                        emptySlot.parentNode.replaceChild(wrapperDiv, emptySlot);
                        wrapperDiv.appendChild(emptySlot);

                        const overlay = document.createElement("div");
                        overlay.classList.add("char-point-overlay");
                        overlay.textContent = "+" + img.getAttribute("data-point-e0");
                        overlay.style.position = "absolute";
                        overlay.style.top = "5px";
                        overlay.style.right = "5px";
                        overlay.style.backgroundColor = "rgba(0, 0, 0, 0.6)";
                        overlay.style.color = "#fff";
                        overlay.style.padding = "2px 5px";
                        overlay.style.borderRadius = "4px";
                        overlay.style.fontWeight = "bold";
                        overlay.style.fontSize = "14px";
                        emptySlot.style.position = "relative";
                        emptySlot.appendChild(overlay);

                        const controlsDiv = document.createElement("div");
                        controlsDiv.classList.add("char-controls");

                        const selectE = document.createElement("select");
                        ["E0", "E1", "E2", "E3", "E4", "E5", "E6"].forEach(optText => {
                            const option = document.createElement("option");
                            option.textContent = optText;
                            selectE.appendChild(option);
                        });

                        const updateOverlay = function () {
                            const selectedE = selectE.value.toLowerCase();
                            const pointE = parseFloat(img.getAttribute("data-point-" + selectedE)) || 0;
                            const selectedS = selectS.value.toUpperCase();
                            const lightConeContainer = controlsDiv.querySelector("div");
                            const pointSInput = lightConeContainer.querySelector('input[name="point' + selectedS + '"]');
                            const pointS = pointSInput ? parseFloat(pointSInput.value) || 0 : 0;
                            overlay.textContent = "+" + (pointE + pointS);
                            updateTeamScore(team1Slots.includes(emptySlot) ? team1Slots : team2Slots, team1Slots.includes(emptySlot) ? team1ScoreEl : team2ScoreEl);
                        };

                        selectE.addEventListener("change", updateOverlay);

                        const lightConeContainer = document.createElement("div");
                        lightConeContainer.style.display = "flex";
                        lightConeContainer.style.alignItems = "center";
                        lightConeContainer.style.gap = "8px";

                        const inputLightCone = document.createElement("input");
                        inputLightCone.type = "text";
                        inputLightCone.placeholder = "Light Cone...";
                        inputLightCone.readOnly = false;

                        const lightConeImg = document.createElement("img");
                        lightConeImg.style.maxHeight = "50px";
                        lightConeImg.style.display = "none";

                        lightConeContainer.appendChild(inputLightCone);
                        lightConeContainer.appendChild(lightConeImg);
                        controlsDiv.appendChild(lightConeContainer);

                        inputLightCone.addEventListener("click", () => {
                            modal.style.display = "block";
                            searchInput.value = "";
                            activeInputLightCone = inputLightCone;
                            activeLightConeImg = lightConeImg;
                        });

                        const selectS = document.createElement("select");
                        ["S1", "S2", "S3", "S4", "S5"].forEach(optText => {
                            const option = document.createElement("option");
                            option.textContent = optText;
                            selectS.appendChild(option);
                        });

                        selectS.addEventListener("change", updateOverlay);

                        controlsDiv.appendChild(selectE);
                        controlsDiv.appendChild(lightConeContainer);
                        controlsDiv.appendChild(selectS);
                        wrapperDiv.appendChild(controlsDiv);
                    }

                    parentDiv.style.filter = "grayscale(100%)";
                    parentDiv.style.cursor = "not-allowed";
                    selectedCharacters.add(img.getAttribute("data-character-id"));
                    updateTeamScore(team1Slots.includes(emptySlot) ? team1Slots : team2Slots, team1Slots.includes(emptySlot) ? team1ScoreEl : team2ScoreEl);
                    nextTurn();

                    const allSlotsFilled = pickOrder.every(slot => slot.classList.contains("filled"));
                    if (allSlotsFilled) {

                        document.querySelector(".grid").style.display = "none";
                        document.querySelector(".search-bar").style.display = "none";
                        document.querySelector(".scorecalc-container").style.display = "block";
                    }
                });

                document.getElementById('searchInput').addEventListener('keyup', function () {
                    const input = this.value.toLowerCase();
                    document.querySelectorAll('.grid > div').forEach(div => {
                        const img = div.querySelector('img');
                        const name = img ? img.alt.toLowerCase() : '';
                        div.style.display = name.includes(input) ? 'flex' : 'none';
                    });
                });

                selectedDiv.addEventListener("click", () => {
                    optionsContainer.style.display = optionsContainer.style.display === "block" ? "none" : "block";
                });

                searchInput.addEventListener("focus", () => {
                    optionsContainer.style.display = "block";
                });

                searchInput.addEventListener("input", function () {
                    const filter = this.value.toLowerCase();
                    document.querySelectorAll("#lightconeSelectCustom .option").forEach(option => {
                        const text = (option.querySelector(".title").textContent + option.querySelector(".sub").textContent).toLowerCase();
                        option.style.display = text.includes(filter) ? "flex" : "none";
                    });
                });

                optionsContainer.addEventListener("click", function (e) {
                    const option = e.target.closest(".option");
                    if (!option)
                        return;

                    const value = option.dataset.value;
                    const imgSrc = option.dataset.img;
                    const title = option.querySelector(".title").textContent;

                    selectedDiv.textContent = title;
                    selectedDiv.dataset.value = value;
                    selectedDiv.dataset.img = imgSrc;
                    searchInput.value = value;
                    optionsContainer.style.display = "none";
                });

                document.addEventListener("click", e => {
                    if (!customSelect.contains(e.target)) {
                        optionsContainer.style.display = "none";
                    }
                });
                cancelBtn.addEventListener("click", () => {
                    modal.style.display = "none";
                    searchInput.value = "";
                    activeInputLightCone = null;
                    activeLightConeImg = null;
                });

                var selectedDivv = null;

                document.querySelectorAll('#lightconeSelectCustom .option').forEach(function (option) {
                    option.addEventListener('click', function () {
                        selectedDivv = this;

                        var selectedEl = document.querySelector('#lightconeSelectCustom .selected');
                        selectedEl.style.display = 'none';
                        selectedEl.textContent = this.dataset.value;
                        selectedEl.dataset.value = this.dataset.value;
                        selectedEl.dataset.img = this.dataset.img;

                        selectedEl.dataset.pointS1 = this.dataset.pointS1;
                        selectedEl.dataset.pointS2 = this.dataset.pointS2;
                        selectedEl.dataset.pointS3 = this.dataset.pointS3;
                        selectedEl.dataset.pointS4 = this.dataset.pointS4;
                        selectedEl.dataset.pointS5 = this.dataset.pointS5;
                    });
                });

                document.getElementById("undoBtn").addEventListener("click", function () {
                    if (historyStack.length === 0)
                        return;

                    const lastAction = historyStack.pop();
                    const {slot, prevHTML, prevClasses, prevStyle, characterId, parentDiv, parentPrevFilter, parentPrevCursor} = lastAction;

                    slot.innerHTML = prevHTML;
                    slot.className = "";
                    prevClasses.forEach(cls => slot.classList.add(cls));
                    if (prevStyle) {
                        slot.setAttribute("style", prevStyle);
                    } else {
                        slot.removeAttribute("style");
                    }

                    const controlsDiv = slot.parentElement.querySelector(".char-controls");
                    if (controlsDiv) {
                        controlsDiv.remove();
                    }
                    parentDiv.style.filter = parentPrevFilter;
                    parentDiv.style.cursor = parentPrevCursor;

                    selectedCharacters.delete(characterId);

                    updateTeamScore(team1Slots, team1ScoreEl);
                    updateTeamScore(team2Slots, team2ScoreEl);

                    currentPickIndex = Math.max(0, currentPickIndex - 1);
                    startTurn();

                    const allSlotsFilled = pickOrder.every(slot => slot.classList.contains("filled"));
                    if (!allSlotsFilled) {
                        document.querySelector(".grid").style.display = "grid";
                        document.querySelector(".search-bar").style.display = "block";
                        document.querySelector(".scorecalc-container").style.display = "none";
                    }
                });

                const defaultSlotData = {};
                pickOrder.forEach(slot => {
                    defaultSlotData[slot.id] = {
                        html: slot.innerHTML,
                        className: slot.className
                    };
                });

                document.getElementById("resetBtn").addEventListener("click", function () {
                    pickOrder.forEach(slot => {
                        slot.innerHTML = defaultSlotData[slot.id].html;
                        slot.className = defaultSlotData[slot.id].className;
                        slot.style = "";

                        const controlsDiv = slot.parentElement.querySelector(".char-controls");
                        if (controlsDiv)
                            controlsDiv.remove();
                    });

                    document.querySelectorAll('.grid > div').forEach(div => {
                        div.style.filter = "";
                        div.style.cursor = "pointer";
                    });

                    selectedCharacters.clear();
                    historyStack = [];
                    currentPickIndex = 0;

                    team1ScoreEl.textContent = "0/" + MAX_SCORE;
                    team2ScoreEl.textContent = "0/" + MAX_SCORE;

                    timers = {
                        team1: {reserve: 14 * 60 + 59, penalty: 0},
                        team2: {reserve: 14 * 60 + 59, penalty: 0}
                    };
                    generalTime = 30;

                    updateTimeDisplay("team1");
                    updateTimeDisplay("team2");
                    if (timerInterval)
                        clearInterval(timerInterval);
                    
                });


                confirmBtn.addEventListener('click', function () {
                    var selectedEl = document.querySelector('#lightconeSelectCustom .selected');
                    var searchInput = document.querySelector('#lightconeSearch');

                    if (!searchInput.value.trim()) {
                        if (activeInputLightCone) {
                            activeInputLightCone.value = '';

                            if (activeLightConeImg) {
                                activeLightConeImg.style.display = 'none';
                            }

                            var container = activeInputLightCone.parentElement;
                            ['pointS1', 'pointS2', 'pointS3', 'pointS4', 'pointS5'].forEach(function (name) {
                                var input = container.querySelector('input[name="' + name + '"]');
                                if (input) {
                                    input.remove();
                                }
                            });
                            var activeSlot = activeInputLightCone.closest('.pick-slot');
                            if (activeSlot) {
                                var selectE = activeSlot.closest('div').querySelector('select');
                                var updateOverlayFn = selectE.onchange;
                                if (updateOverlayFn) {
                                    updateOverlayFn();
                                }
                                updateTeamScore(
                                        team1Slots.indexOf(activeSlot) !== -1 ? team1Slots : team2Slots,
                                        team1Slots.indexOf(activeSlot) !== -1 ? team1ScoreEl : team2ScoreEl
                                        );
                            }

                            activeInputLightCone = null;
                            activeLightConeImg = null;

                            modal.style.display = 'none';
                            searchInput.value = '';
                            return;
                        }
                    }

                    var value = selectedEl.dataset.value || '';
                    var imgSrc = selectedEl.dataset.img || '';
                    var pointS1 = selectedEl.dataset.pointS1 || '0';
                    var pointS2 = selectedEl.dataset.pointS2 || '0';
                    var pointS3 = selectedEl.dataset.pointS3 || '0';
                    var pointS4 = selectedEl.dataset.pointS4 || '0';
                    var pointS5 = selectedEl.dataset.pointS5 || '0';

                    if (activeInputLightCone && activeLightConeImg) {
                        activeInputLightCone.value = value;

                        if (imgSrc) {
                            // activeLightConeImg.src = imgSrc;
                            // activeLightConeImg.style.display = 'inline-block';
                        } else {
                            activeLightConeImg.style.display = 'none';
                        }

                        var container = activeInputLightCone.parentElement;

                        function setOrCreateHiddenInput(name, val) {
                            var input = container.querySelector('input[name="' + name + '"]');
                            if (!input) {
                                input = document.createElement('input');
                                input.type = 'hidden';
                                input.name = name;
                                container.appendChild(input);
                            }
                            input.value = val;
                        }

                        setOrCreateHiddenInput('pointS1', pointS1);
                        setOrCreateHiddenInput('pointS2', pointS2);
                        setOrCreateHiddenInput('pointS3', pointS3);
                        setOrCreateHiddenInput('pointS4', pointS4);
                        setOrCreateHiddenInput('pointS5', pointS5);
                    }

                    modal.style.display = 'none';
                    searchInput.value = '';

                    var activeSlot = activeInputLightCone ? activeInputLightCone.closest('.pick-slot') : null;
                    if (activeSlot) {
                        var selectE = activeSlot.closest('div').querySelector('select');
                        var updateOverlayFn = selectE.onchange;
                        if (updateOverlayFn) {
                            updateOverlayFn();
                        }
                        updateTeamScore(
                                team1Slots.indexOf(activeSlot) !== -1 ? team1Slots : team2Slots,
                                team1Slots.indexOf(activeSlot) !== -1 ? team1ScoreEl : team2ScoreEl
                                );
                    }

                    activeInputLightCone = null;
                    activeLightConeImg = null;
                });

                updateTimeDisplay("team1");
                updateTimeDisplay("team2");
                
            });
        </script>


        <!-- Modal (unchanged) -->
        <div id="lightconeModal" class="modal" style="display: none;">
            <div class="modal-content">
                <h3>Select Lightcone</h3>
                <div id="lightconeSelectCustom" class="custom-select">
                    <div class="search-wrapper">
                        <input type="text" id="lightconeSearch" placeholder="Search lightcone...">
                    </div>
                    <div class="selected" style="display: none;" ></div> 
                    <div class="options">
                        <c:forEach var="lc" items="${listLightconeBanPick}">
                            <div class="option" data-value="${lc.lightConeName}" data-img="${lc.lightConeImage}"
                                 data-point-s1="${lc.pointS1}" 
                                 data-point-s2="${lc.pointS2}" 
                                 data-point-s3="${lc.pointS3}" 
                                 data-point-s4="${lc.pointS4}" 
                                 data-point-s5="${lc.pointS5}">
                                <div class="option-text">
                                    <div class="title">${lc.lightConeName}</div>
                                    <div class="sub">${lc.characterSignatureID.characterName}</div>
                                </div>
                                <img src="${lc.lightConeImage}" alt="${lc.lightConeName}">
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!-- Actions -->
                <div class="modal-actions">
                    <button type="button" id="cancelBtn">CANCEL</button>
                    <button type="submit" id="confirmBtn">CONFIRM</button>
                </div>
            </div>
        </div>
