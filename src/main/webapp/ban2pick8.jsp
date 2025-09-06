<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <title>Gio Cai Le Bach</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">
        <link rel="icon" type="image/png" href="images/LogoGC.png">
        <style>
            *{
                box-sizing:border-box
            }
            html,body{
                margin:0;
                padding:0;
                font-family:'Segoe UI',sans-serif;
                font-size:16px;
                line-height:1.5;
                color:#fff;
                background:#252f2c;
                overflow-x: hidden;
            }

            
            .settings-section{
                padding:12px 16px;
                border-bottom:1px solid #1f1f1f;
                background:#101010
            }
            .settings-row{
                display:flex;
                gap:10px;
                flex-wrap:wrap;
                margin-top:8px
            }
            .settings-section input{
                background:#151515;
                border:1px solid #2a2a2a;
                color:#fff;
                border-radius:6px;
                padding:6px 10px
            }
            .roll-box{
                background:#151515;
                border:1px solid #2a2a2a;
                border-radius:8px;
                padding:8px 12px;
                text-align:center
            }
            .roll-value{
                font-weight:700;
                font-size:18px;
                margin-bottom:4px
            }
            .custom-btn{
                background:#5a3caf;
                border:none;
                color:#fff;
                border-radius:8px;
                padding:6px 12px;
                cursor:pointer
            }
            .back-btn-container{
                margin-bottom:6px
            }


            .team-col{
                flex:1 1 50%;
                min-width:0px;
                background:#001a10;
                border-radius:10px;
                padding:14px 12px;
                border:1px solid #1f1f1f;
            }
            .team-header{
                width: 48%;
                display:flex;
                justify-content:space-between;
                align-items:center;
                padding:8px 10px;
                border-radius:8px;
                margin-bottom:10px;
                font-weight:700
            }
            .team-header.blue{
                background:#001066;
                border:1px solid #2b477f
            }
            .team-header.red{
                background:#8f241c;
                border:1px solid #7a3535
            }

            
            .slot{
                width:80px;
                height:200px;
                background:#0f3433;
                border:1px solid #2b2b2b;
                border-radius:8px;
                display:flex;
                align-items:center;
                justify-content:center;

                overflow:hidden;
                flex-shrink:0;
            }


            .slot-row{
                display:flex;
                flex-wrap:wrap;
                gap:8px;

            }
            .slot-half{
                width:100px;
                height:200px;
                background:#0f3433;
                border:1px solid #2b2b2b;
                border-radius:8px;
                display:flex;
                align-items:center;
                justify-content:center;
                overflow:hidden;
                flex-shrink:0;
            }
            .pick-slot{
                outline:1px dashed #313131
            }
            .picked-img {
                width:100%;
                height:100%;
                object-fit:cover;
                border-radius:6px;
            }

            
            .controls-bar{
                margin:0 16px 10px 16px;
                background:#121212;
                border:1px solid #1f1f1f;
                border-radius:10px;
                padding:10px 12px;
            }

            .button-group{
                display:flex;
                gap:10px;
                justify-content:flex-end;
                margin-top:10px
            }
            .button-group .btn-undo{
                background:#1e3a8a
            }
            .button-group .btn-reset{
                background:#7a2626
            }


            .grid-wrap {
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                margin-top: 20px;
                width: 100%;
            }


            .search-container {
                display: flex;
                align-items: center;
                justify-content: center;
                gap: 12px;
                margin-bottom: 8px;
                width: 115%;
            }


            .grid {
                display: grid;
                grid-template-columns: repeat(auto-fill, 60px);
                gap: 6px;
                justify-content: center;
                width: 74%;
                margin-bottom: 20px;
            }

            .search-bar {
                width: 49%;
                background: #004643;
                border: 1px solid #2a2a2a;
                color: #fff;
                border-radius: 8px;
                padding: 8px 10px;
            }
            .search-bar::placeholder {
                color: #e8e4e6;
                opacity: 0.7;
            }
            .button-group {
                display: flex;
                gap: 6px;
            }
            .custom-btn {
                padding: 6px 12px;
                border: none;
                border-radius: 6px;
                font-weight: bold;
                cursor: pointer;
            }
            .btn-undo {
                background: #1e3a8a;
                color: white;
            }
            .btn-reset {
                background: #991b1b;
                color: white;
            }


            .grid .tile{
                width:60px;
                height:60px;
                border-radius:6px;
                display:flex;
                align-items:center;
                justify-content:center;
                cursor:pointer
            }
            .grid img{
                width:100%;
                height:100%;
                object-fit:cover;
                border-radius:6px;
                border:1px solid #3a3a3a;
                transition:transform .15s
            }
            .grid img:hover{
                transform:scale(1.05);
                border-color:#5a3caf
            }

            .draft-container {
                display: flex;
                justify-content: space-between;
                align-items: flex-start;
                gap: 20px;
                margin: 20px;
            }

           
            .team-info {
                width: 190px;
                background: #101820;
                padding: 15px;
                border-radius: 10px;
                font-size: 14px;
                line-height: 1.5;
            }
            .team-info h3 {
                margin-bottom: 12px;
                text-align: center;
                font-size: 16px;
                color: #00d4ff;
            }

            
            .team-slots-center {
                flex: 1;
                display: flex;
                flex-direction: column;
                align-items: center;
                gap: 10px;
            }

            .tile.disabled {
                opacity: 1;
                pointer-events: none;
                filter: grayscale(100%);
            }

            .gray-filter {
                filter: grayscale(100%);
            }

            .team-section {
                display: flex;
                flex-direction: column; 
                align-items: flex-start; 
                gap: 10px; 
            }
            .roll-box {
                display: flex;
                flex-direction: column;
                align-items: center;
                background-color: #004643;
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

            .roll-box button {
                padding: 10px 16px;
                font-size: 14px;
                background-color: #abd1c6;
                color: black;
                border: 1px solid #9e80c0;
                border-radius: 8px;
                cursor: pointer;
            }

            .timing-container {
                display: flex;
                justify-content: center;
                align-items: center;
                gap: 12px;
                font-family: Arial, sans-serif;
            }

            .time-box {

                background-color: #004643;
                padding: 6px 14px;
                border-radius: 8px;
                min-width: 85px;
                text-align: center;
                box-shadow: 0 0 8px rgba(0,0,0,0.4);
            }

            
            .overlay .value {
                min-width: 50px;
                text-align: center;
                font-weight: 600;
            }

            
            .overlay .e-toggle {
                position: relative;
                display: inline-block;
            }

            
            .overlay .e-toggle .e-button {
                position: relative;
                background: rgba(255,255,255,0.06);
                border: 1px solid rgba(255,255,255,0.12);
                color: #fff;
                left: -10px;
                width: 130%;
                font-size: 12px;
                border-radius: 4px;
                cursor: pointer;
                white-space: nowrap;
            }

            .overlay .e-toggle .ec-selector {
                width: 180%;
                display: none;
                position: absolute;
                
                left: -16px;
                margin-top: 5px;
                padding: 7px;
                background: rgba(0,0,0,0.8);
                border-radius: 6px;
                box-shadow: 0 6px 18px rgba(0,0,0,0.4);
                z-index: 10;
                display: flex;
                gap: 6px;
                flex-wrap: wrap;
            }

           
            .overlay .e-toggle .ec-selector button {
                background: transparent;
                border: 1px solid rgba(255,255,255,0.14);
                width: 130%;
                color: #fff;
                padding: 4px 6px;
                font-size: 12px;
                border-radius: 4px;
                cursor: pointer;
                line-height: 1;
            }

            
            .overlay .e-toggle .ec-selector button.active {
                background: rgba(255,255,255,0.18);
                border-color: rgba(255,255,255,0.28);
            }

            
            .slot .ec-selector {
                display: none;
                flex-direction: column;
                gap: 4px;
                margin-left: 4px;
            }

            
            .slot .overlay:hover .ec-selector {
                display: flex;
            }



            .slot.clickable {
                cursor: pointer;
            }


            .slot, .slot-half {
                position: relative;
                overflow: visible;
            }


            .overlay {
                position: absolute;
                top: -12px;  
                left: 50%;  
                transform: translateX(-50%); 
                padding: 4px 6px;
                background: rgba(0,0,0,0.55);
                color: #fff;
                font-size: 12px;
                border-radius: 6px;
                display: flex;
                align-items: center;
                gap: 6px;
                z-index: 5;
            }

            
            .lc-overlay {
                position: absolute;
                bottom: 6px;
                left: 6px;
                padding: 4px 4px;
                background: rgba(0,0,0,0.55);
                color: #fff;
                font-size: 12px;
                flex-direction: column;
                border-radius: 6px;
                display: flex;
                align-items: center;
                gap: 4px;
                z-index: 5;
            }

            
            .active-lightcone-img {
                z-index: 1; 
            }


            .modal-content {
                background-color: #2a2a2a;
                margin: 15% auto;
                padding: 20px;
                border-radius: 8px;
                width: 640px;
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
                max-height: 350px;
                overflow-y: auto;
                z-index: 10000;
                flex-direction: column;
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
                width: 60px;
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

            
            .lc-overlay .s-toggle {
                position: relative;
                display: inline-block;
            }

            
            .lc-overlay .s-toggle .s-button {
                background: rgba(255,255,255,0.06);
                border: 1px solid rgba(255,255,255,0.12);
                color: #fff;
                font-size: 12px;
                border-radius: 4px;
                cursor: pointer;
                white-space: nowrap;
            }

            
            .lc-overlay .s-toggle .s-selector {
                display: none;
                position: absolute;
                bottom: 110%;
                left: -5px;
                margin-bottom: 5px; 
                padding: 5px;
                background: rgba(0,0,0,0.8);
                border-radius: 6px;
                box-shadow: 0 6px 18px rgba(0,0,0,0.4);
                z-index: 10;
                display: flex;
                gap: 6px;
                flex-wrap: wrap;
            }

            
            .lc-overlay .s-toggle .s-selector button {
                background: transparent;
                border: 1px solid rgba(255,255,255,0.14);
                color: #fff;
                padding: 4px 6px;
                font-size: 12px;
                border-radius: 4px;
                cursor: pointer;
                line-height: 1;
            }

            
            .lc-overlay .s-toggle .s-selector button.active {
                background: rgba(255,255,255,0.18);
                border-color: rgba(255,255,255,0.28);
            }

            .selected {
                filter: grayscale(100%) brightness(0.6);
                pointer-events: none; 
            }

            
            @media (max-width: 992px){
                .top-teams{
                    flex-direction:column
                }
            }

            .blinking {
                animation: blink 1s infinite;
            }

            @keyframes blink {
                0%   {
                    box-shadow: 0 0 10px 3px #fff;
                }
                50%  {
                    box-shadow: 0 0 10px 8px #FFD93D;
                }
                100% {
                    box-shadow: 0 0 10px 3px #fff;
                }
            }

            .team-top-row {
                display: flex;
                align-items: center;
                justify-content: space-between;  
                margin-bottom: 12px;
            }

            .team-header {
                background: #1f3366;
                color: white;
                font-weight: bold;
                padding: 8px 16px;
                border-radius: 6px;
            }

            .team-times {
                display: flex;
                gap: 16px;
            }

            .time-box {
                background: #064b46;
                padding: 6px 14px;
                border-radius: 6px;
                color: #fff;
                text-align: center;
            }
            .time-box .label {
                font-size: 13px;
                font-weight: 600;
                opacity: 0.8;
            }
            .time-box .value {
                font-size: 15px;
                font-weight: bold;
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

            .score-panel {
                background: #1e1b1b; 
                color: #fff;
                font-family: 'Orbitron', sans-serif; 
                font-size: 14px;
                padding: 20px;
                border-radius: 6px;
                width: 180px;
            }


            .score-panel label {
                display: block;
                margin: 8px 0 4px;
            }

            .score-panel input {
                width: 100%;
                padding: 6px;
                background: transparent;
                border: 1px solid #666;
                border-radius: 4px;
                color: #fff;
                font-size: 14px;
                text-align: center;
            }

            .score-panel span {
                display: block;
                font-size: 18px;
                font-weight: bold;
                margin-top: 4px;
            }

            .score-panel p {
                margin: 12px 0;
                font-size: 14px;
                text-align: center;
            }

            .score-panel h2 {
                margin: 12px 0;
                text-align: center;
            }

           
            #t1-cost,
            #t1-total-point {
                font-size: 24px;   
                font-weight: bold;
                color: #fff;
                text-align: center;
                display: block;
                margin-top: 4px;
                text-shadow: 0 0 6px #00ffff; 
            }
            #t2-cost,
            #t2-total-point {
                font-size: 24px; 
                font-weight: bold;
                color: #fff;
                text-align: center;
                display: block;
                margin-top: 4px;
                text-shadow: 0 0 6px #00ffff;
            }

            .custom-btn {
                padding: 6px 12px;
                border: none;
                border-radius: 6px;
                font-weight: bold;
                cursor: pointer;
                transition: background 0.2s, transform 0.2s;
            }

            
            .btn-undo {
                background: #1e3a8a;
                color: white;
            }
            .btn-undo:hover {
                background: #2b4db3;
                transform: scale(1.05);
            }

            
            .btn-reset {
                background: #991b1b;
                color: white;
            }
            .btn-reset:hover {
                background: #bb2525;
                transform: scale(1.05);
            }

            
            .btn-setting {
                background: #444;
                color: #fff;
                border: 1px solid #888;
            }
            .btn-setting:hover {
                background: #666;
                transform: scale(1.05);
            }


            .modal-contents h3 {
                margin-bottom: 15px;
                font-size: 18px;
                color: #00d4ff;
            }

            .modal-actions {
                margin-top: 15px;
            }
            .modal {
                display: none;
                position: fixed;
                z-index: 9999;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.7);
                font-family: 'Rajdhani', sans-serif;
            }

            .modal-contents {
                background-color: #2a2a2a;
                margin: 10% auto;
                padding: 25px;
                border-radius: 12px;
                width: 90%;
                max-width: 450px;
                box-shadow: 0 0 15px rgba(0, 0, 0, 0.5);
                color: #fff;
                text-align: center;
            }

            .roll-box button:hover {
                background: #6b4ec1;
            }

            .modal-actions button:hover {
                background: #666;
                transform: scale(1.05);
            }

            .winner-container {
                text-align: center;
            }
            .winner .label {
                font-size: 24px;
                color: #00d4ff;
                padding: 10px;
                background: #101820;
                border-radius: 8px;
            }
        </style>
    </head>
    <body>

        <div class="draft-container">

            
            <div class="team-section">
                <div class="score-panel">
                    <h2 id="t1-name">Team 1<br><span></span></h2>
                    <p>Total cost:<br><span id="t1-cost">0</span></p>

                    <label for="first-half">First Half Cycle:</label>
                    <input type="number" id="t1-first-half">

                    <label for="second-half">Second Half Cycle:</label>
                    <input type="number" id="t1-second-half">

                    <label for="deaths">Deaths:</label>
                    <input type="number" id="t1-deaths">

                    <label for="penaltys">Time penaltys:</label>
                    <input type="number" id="t1-penaltys">

                    <p>Total Point:<br><span id="t1-total-point">-6.0000</span></p>
                </div>
            </div>



            <div class="team-slots-center">
                <div class="team-col">
                    
                    <div class="team-top-row">
                        <div class="team-header blue"> 
                            <div id="team1-name">Team 1</div> 
                        </div>

                        <div class="team-times">
                            <div class="time-box">
                                <div class="label">General</div>
                                <div class="value time-general1">00:30</div>
                            </div>
                            <div class="time-box">
                                <div class="label">Reserve</div>
                                <div class="value" id="team1-reserve">5:00</div>
                            </div>
                            <div class="time-box">
                                <div class="label">Penalty</div>
                                <div class="value" id="team1-penalty">0:00</div>
                            </div>
                        </div>
                    </div>
                    <div class="team-slots">
                        <div class="slot-row">
                            <div class="slot slot-1"></div>
                            <div class="slot-half pick-slot-3"></div>
                            <div class="slot-half pick-slot-6"></div>
                            <div class="slot slot-8"></div>
                            <div class="slot-half pick-slot-10"></div>
                            <div class="slot-half pick-slot-11"></div>
                            <div class="slot-half pick-slot-14"></div>
                            <div class="slot-half pick-slot-15"></div>
                            <div class="slot-half pick-slot-18"></div>
                            <div class="slot-half pick-slot-19"></div>
                        </div>
                    </div>
                </div>
                <div class="winner-container">
                    <div class="winner">
                        <div class="label"></div>
                    </div>
                </div>
                <div class="team-col">
                    <div class="team-top-row">
                        <div class="team-header red"> 
                            <div id="team2-name">Team 2</div> 
                        </div>

                        <div class="team-times">
                            <div class="time-box">
                                <div class="label">General</div>
                                <div class="value time-general2">00:30</div>
                            </div>
                            <div class="time-box">
                                <div class="label">Reserve</div>
                                <div class="value" id="team2-reserve">5:00</div>
                            </div>
                            <div class="time-box">
                                <div class="label">Penalty</div>
                                <div class="value" id="team2-penalty">0:00</div>
                            </div>
                        </div>
                    </div>

                    <div class="team-slots">
                        <div class="slot-row">
                            <div class="slot slot-2"></div>
                            <div class="slot-half pick-slot-4"></div>
                            <div class="slot-half pick-slot-5"></div>
                            <div class="slot slot-7"></div>
                            <div class="slot-half pick-slot-9"></div>
                            <div class="slot-half pick-slot-12"></div>
                            <div class="slot-half pick-slot-13"></div>
                            <div class="slot-half pick-slot-16"></div>
                            <div class="slot-half pick-slot-17"></div>
                            <div class="slot-half pick-slot-20"></div>
                        </div>
                    </div>
                </div>
            </div>

            
            <div class="team-section">


                <div class="score-panel">
                    <h2 id="t2-name">Team 2<br><span></span></h2>
                    <p>Total cost:<br><span id="t2-cost">0</span></p>

                    <label for="first-half">First Half Cycle:</label>
                    <input type="number" id="t2-first-half">

                    <label for="second-half">Second Half Cycle:</label>
                    <input type="number" id="t2-second-half">

                    <label for="deaths">Deaths:</label>
                    <input type="number" id="t2-deaths">

                    <label for="penaltys">Time penaltys:</label>
                    <input type="number" id="t2-penaltys">

                    <p>Total Point:<br><span id="t2-total-point">-6.0000</span></p>
                </div>
            </div>

        </div>

        <div class="grid-wrap">
            <div class="search-container">
                <input type="text" class="search-bar" id="searchInput" placeholder="Search characters">
                <div class="button-group">
                    <button id="undoBtn" class="custom-btn btn-undo">Undo</button>
                    <button id="resetBtn" class="custom-btn btn-reset">Reset</button>
                    <button id="settingBtn" class="custom-btn btn-setting">⚙ Setting</button>
                </div>
            </div>

            <div id="settingsModal" class="modal">
                <div class="modal-contents">
                    <h3 style="color: #00d4ff; font-size: 20px; margin-bottom: 20px; text-align: center;">⚙ Settings</h3>

                    <div class="settings-row" style="display: flex; flex-direction: column; gap: 15px; align-items: center;">
                        <div style="width: 100%; max-width: 300px;">
                            <label for="team1-input" style="display: block; margin-bottom: 5px; font-size: 14px; color: #e8e4e6;">Team 1 Name</label>
                            <input type="text" id="team1-input" placeholder="Enter Team 1 Name" 
                                   style="width: 100%; padding: 10px; border-radius: 6px; border: 1px solid #66ccff; background: #1f1f1f; color: #fff; font-size: 14px;">
                        </div>
                        <div style="width: 100%; max-width: 300px;">
                            <label for="team2-input" style="display: block; margin-bottom: 5px; font-size: 14px; color: #e8e4e6;">Team 2 Name</label>
                            <input type="text" id="team2-input" placeholder="Enter Team 2 Name" 
                                   style="width: 100%; padding: 10px; border-radius: 6px; border: 1px solid #66ccff; background: #1f1f1f; color: #fff; font-size: 14px;">
                        </div>
                    </div>

                    <div class="settings-row" style="display: flex; justify-content: center; gap: 20px; margin-top: 20px;">
                        <div class="roll-box" style="background: #004643; border: 1px solid #9e80c0; border-radius: 10px; padding: 15px; text-align: center; width: 140px;">
                            <div class="roll-value" id="team1-roll-value" style="font-size: 24px; font-weight: bold; color: #fff; margin-bottom: 10px;">0</div>
                            <button onclick="rollForTeam(1)" 
                                    style="padding: 10px 16px; font-size: 14px; background: #5a3caf; color: #fff; border: none; border-radius: 8px; cursor: pointer; transition: background 0.2s;">
                                Roll for Team 1
                            </button>
                        </div>
                        <div class="roll-box" style="background: #004643; border: 1px solid #9e80c0; border-radius: 10px; padding: 15px; text-align: center; width: 140px;">
                            <div class="roll-value" id="team2-roll-value" style="font-size: 24px; font-weight: bold; color: #fff; margin-bottom: 10px;">0</div>
                            <button onclick="rollForTeam(2)" 
                                    style="padding: 10px 16px; font-size: 14px; background: #5a3caf; color: #fff; border: none; border-radius: 8px; cursor: pointer; transition: background 0.2s;">
                                Roll for Team 2
                            </button>
                        </div>
                    </div>

                    <div class="modal-actions" style="margin-top: 20px; display: flex; justify-content: center;">
                        <button id="closeSettings" 
                                style="background: #444; border: none; color: #fff; padding: 8px 20px; border-radius: 6px; cursor: pointer; font-size: 14px; transition: background 0.2s;">
                            Close
                        </button>
                    </div>
                </div>
            </div>

            <div class="grid">
                <c:forEach var="character" items="${listCharacterBanPick}">
                    <c:choose>
                        <c:when test="${character.rarityID.starName == '5-Star'}">
                            <div class="tile" 
                                 style="background:#e6b741" 
                                 data-full="${pageContext.request.contextPath}/${character.imageFull}">
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
                                     data-rarity="5" alt="Character 5"
                                     style="width: 100%; height: 100%; object-fit: cover; border-radius: 0.3em;" />
                            </div>
                        </c:when>
                        <c:when test="${character.rarityID.starName == '4-Star'}">
                            <div class="tile" 
                                 style="background:#9b59b6" 
                                 data-full="${pageContext.request.contextPath}/${character.imageFull}">
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
                                     data-rarity="4" alt="Character 4"
                                     style="width: 100%; height: 100%; object-fit: cover; border-radius: 0.3em;" />
                            </div>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </div>
        </div>

        
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
                
                <div class="modal-actions">
                    <button type="button" id="cancelBtn">CANCEL</button>
                    <button type="submit" id="confirmBtn">CONFIRM</button>
                </div>
            </div>
        </div>
    </body>
    <script>

        const settingsModal = document.getElementById("settingsModal");
        const settingBtn = document.getElementById("settingBtn");
        const closeSettings = document.getElementById("closeSettings");

        
        settingBtn.onclick = function () {
            settingsModal.style.display = "block";
        };

        
        closeSettings.onclick = function () {
            settingsModal.style.display = "none";
        };

        
        window.onclick = function (event) {
            if (event.target === settingsModal) {
                settingsModal.style.display = "none";
            }
        };


        const team1Input = document.getElementById("team1-input");
        const team2Input = document.getElementById("team2-input");


        team1Input.addEventListener("input", function () {
            const value = this.value || "Team 1"; // nếu rỗng thì để mặc định
            document.getElementById("team1-name").textContent = value;
            document.getElementById("t1-name").textContent = value;
        });


        team2Input.addEventListener("input", function () {
            const value = this.value || "Team 2";
            document.getElementById("team2-name").textContent = value;
            document.getElementById("t2-name").textContent = value;
        });

        function calculateTotalPoint(teamId) {
            var costElementId = 't' + teamId + '-cost';
            var pointElementId = 't' + teamId + '-total-point';
            var firstHalfId = 't' + teamId + '-first-half';
            var secondHalfId = 't' + teamId + '-second-half';
            var deathsId = 't' + teamId + '-deaths';
            var penaltiesId = 't' + teamId + '-penaltys';

            var costElement = document.getElementById(costElementId);
            var pointElement = document.getElementById(pointElementId);
            var firstHalfElement = document.getElementById(firstHalfId);
            var secondHalfElement = document.getElementById(secondHalfId);
            var deathsElement = document.getElementById(deathsId);
            var penaltiesElement = document.getElementById(penaltiesId);

            
            var totalCost = parseFloat(costElement.textContent) || 0;
            var firstHalf = parseFloat(firstHalfElement.value) || 0;
            var secondHalf = parseFloat(secondHalfElement.value) || 0;
            var deaths = parseFloat(deathsElement.value) || 0;
            var penalties = parseFloat(penaltiesElement.value) || 0;

            
            var totalPoint = (totalCost - 30) / 5 + firstHalf + secondHalf + deaths + penalties;

            
            pointElement.textContent = totalPoint.toFixed(4);
        }

        function checkAllSlotsFilled() {
            var slotClasses = [
                'slot-1', 'slot-2', 'slot-7', 'slot-8',
                'pick-slot-3', 'pick-slot-4', 'pick-slot-5', 'pick-slot-6',
                'pick-slot-9', 'pick-slot-10', 'pick-slot-11', 'pick-slot-12',
                'pick-slot-13', 'pick-slot-14', 'pick-slot-15', 'pick-slot-16',
                'pick-slot-17', 'pick-slot-18', 'pick-slot-19', 'pick-slot-20'
            ];
            for (var i = 0; i < slotClasses.length; i++) {
                var slot = document.querySelector('.' + slotClasses[i]);
                if (!slot || !slot.querySelector('.picked-img')) {
                    return false;
                }
            }
            return true;
        }

        function updateWinner() {
            var winnerLabel = document.querySelector(".winner-container .winner .label");
            if (!winnerLabel)
                return;

            if (!checkAllSlotsFilled()) {
                winnerLabel.textContent = "...";
                winnerLabel.style.color = "#00d4ff";
                return;
            }

            var t1PointElement = document.getElementById("t1-total-point");
            var t2PointElement = document.getElementById("t2-total-point");

            if (!t1PointElement || !t2PointElement)
                return;

            var t1Points = parseFloat(t1PointElement.textContent) || 0;
            var t2Points = parseFloat(t2PointElement.textContent) || 0;

            var team1NameElement = document.getElementById("team1-name");
            var team2NameElement = document.getElementById("team2-name");
            var team1Name = team1NameElement && team1NameElement.textContent ? team1NameElement.textContent : "Team 1";
            var team2Name = team2NameElement && team2NameElement.textContent ? team2NameElement.textContent : "Team 2";

            if (t1Points < t2Points) {
                winnerLabel.textContent = team1Name + " Wins!";
                winnerLabel.style.color = "#00d4ff";
            } else if (t2Points < t1Points) {
                winnerLabel.textContent = team2Name + " Wins!";
                winnerLabel.style.color = "#E43636";
            } else {
                winnerLabel.textContent = "Draw!";
                winnerLabel.style.color = "#00d4ff";
            }
        }

        function updateTeamPoints() {
            calculateTotalPoint(1);
            calculateTotalPoint(2); 
            updateWinner();
        }

        document.addEventListener('DOMContentLoaded', function () {
            updateTeamPoints();

            var costIds = ['t1-cost', 't2-cost'];
            for (var i = 0; i < costIds.length; i++) {
                var costElement = document.getElementById(costIds[i]);
                var observer = new MutationObserver(updateTeamPoints);
                observer.observe(costElement, {childList: true, characterData: true, subtree: true});
            }

            var inputIds = ['t1-first-half', 't1-second-half', 't1-deaths', 't1-penaltys', 't2-first-half', 't2-second-half', 't2-deaths', 't2-penaltys'];
            for (var j = 0; j < inputIds.length; j++) {
                var inputElement = document.getElementById(inputIds[j]);
                if (inputElement) {
                    inputElement.addEventListener('input', updateTeamPoints);
                }
            }

            var slotClasses = [
                'slot-1', 'slot-2', 'slot-7', 'slot-8',
                'pick-slot-3', 'pick-slot-4', 'pick-slot-5', 'pick-slot-6',
                'pick-slot-9', 'pick-slot-10', 'pick-slot-11', 'pick-slot-12',
                'pick-slot-13', 'pick-slot-14', 'pick-slot-15', 'pick-slot-16',
                'pick-slot-17', 'pick-slot-18', 'pick-slot-19', 'pick-slot-20'
            ];
            for (var k = 0; k < slotClasses.length; k++) {
                var slot = document.querySelector('.' + slotClasses[k]);
                if (slot) {
                    slot.addEventListener('DOMSubtreeModified', updateTeamPoints);
                }
            }
        });
    </script>

    <script>
        function rollForTeam(team) {
            var roll = Math.floor(Math.random() * 100) + 1;
            document.getElementById("team" + team + "-roll-value").textContent = roll;
        }

        document.addEventListener("DOMContentLoaded", function () {
            const grid = document.querySelector(".grid");
            const slots = [];
            const defaultBg = "#0f3433";
            let currentIndex = 0;
            const searchInput = document.getElementById("searchInput");
            const slotElements = document.querySelectorAll(".slot, .slot-half");
            for (let i = 1; i <= 20; i++) {
                let el = document.querySelector(".slot-" + i) || document.querySelector(".pick-slot-" + i);
                if (el) {
                    slots.push(el);
                }
            }

            const team1Indices = [0, 2, 5, 7, 9, 10, 13, 14, 17, 18];
            const team2Indices = [1, 3, 4, 6, 8, 11, 12, 15, 16, 19];
            let generalTime1 = 30;
            let reserveTime1 = 300;
            let penaltyTime1 = 0;

            let generalTime2 = 30;
            let reserveTime2 = 300;
            let penaltyTime2 = 0;

            let timer = null;
            let currentTeam = null;

            function getTeamBySlot(slotIndex) {
                if (team1Indices.includes(slotIndex))
                    return 1;
                if (team2Indices.includes(slotIndex))
                    return 2;
                return null;
            }

            function startTurn(index) {
                if (index >= slots.length)
                    return;

                currentTeam = getTeamBySlot(index);
                if (!currentTeam)
                    return;

                if (index >= 2) {
                    if (currentTeam === 1)
                        generalTime1 = 30;
                    if (currentTeam === 2)
                        generalTime2 = 30;

                    if (timer)
                        clearInterval(timer);
                    timer = setInterval(function () {
                        if (currentTeam === 1) {
                            if (generalTime1 > 0)
                                generalTime1--;
                            else if (reserveTime1 > 0)
                                reserveTime1--;
                            else
                                penaltyTime1++;
                        } else {
                            if (generalTime2 > 0)
                                generalTime2--;
                            else if (reserveTime2 > 0)
                                reserveTime2--;
                            else
                                penaltyTime2++;
                        }
                        updateDisplay();
                    }, 1000);
                }

                updateDisplay();
            }

            function stopTurnAndNext() {
                if (timer)
                    clearInterval(timer);
                currentIndex++;
                if (currentIndex < slots.length) {
                    startTurn(currentIndex);
                }
            }

            function updateDisplay() {
                function fmt(sec) {
                    const m = Math.floor(sec / 60);
                    const s = sec % 60;
                    return m + ":" + (s < 10 ? "0" : "") + s;
                }

                document.querySelector(".time-general1").textContent =
                        "00:" + (generalTime1 < 10 ? "0" : "") + generalTime1;
                document.querySelector(".time-general2").textContent =
                        "00:" + (generalTime2 < 10 ? "0" : "") + generalTime2;

                const team1PenaltyElement = document.getElementById("team1-penalty");
                const team2PenaltyElement = document.getElementById("team2-penalty");

                team1PenaltyElement.textContent = fmt(penaltyTime1);
                team1PenaltyElement.style.color = penaltyTime1 > 0 ? "#ff0000" : "#ffffff";

                team2PenaltyElement.textContent = fmt(penaltyTime2);
                team2PenaltyElement.style.color = penaltyTime2 > 0 ? "#ff0000" : "#ffffff";

                document.getElementById("team1-reserve").textContent = fmt(reserveTime1);
                document.getElementById("team2-reserve").textContent = fmt(reserveTime2);
            }

            if (slotElements.length > 0) {
                startTurn(0);
            }

            function resetSlot(slot) {
                slot.innerHTML = "";
                slot.classList.remove("clickable");
                slot.classList.remove("blinking");
                slot.style.background = defaultBg;
                for (var i = 0; i <= 6; i++) {
                    delete slot.dataset["pointE" + i];
                }
                for (var j = 1; j <= 5; j++) {
                    delete slot.dataset["pointS" + j];
                }
                delete slot.dataset.rarity;
            }

            function computeCost() {
                let total1 = 0;
                let total2 = 0;

                slots.forEach(function (slot, index) {
                    let add = 0;
                    if (slot.classList.contains("slot-half")) {
                        const overlay = slot.querySelector(".overlay .value");
                        if (overlay) {
                            add += parseFloat(overlay.textContent) || 0;
                        }
                        const lcVal = slot.querySelector(".lc-overlay .lc-value");
                        if (lcVal) {
                            add += parseFloat(lcVal.textContent) || 0;
                        }
                        if (team1Indices.includes(index)) {
                            total1 += add;
                        } else {
                            total2 += add;
                        }
                    }
                });

                document.getElementById("t1-cost").textContent = total1.toFixed(1);
                document.getElementById("t2-cost").textContent = total2.toFixed(1);
            }

            function createOverlayOnSlot(slot, defaultIndex) {
                var old = slot.querySelector(".overlay");
                if (old)
                    old.remove();
                if (!slot.classList.contains("slot-half"))
                    return;
                if (!slot.querySelector("img.picked-img"))
                    return;

                var overlay = document.createElement("div");
                overlay.className = "overlay";

                var valueSpan = document.createElement("span");
                valueSpan.className = "value";

                function getPoint(i) {
                    var raw = slot.dataset["pointE" + i];
                    if (raw === undefined)
                        return "0.0";
                    var n = parseFloat(raw);
                    if (isNaN(n))
                        return "0.0";
                    return n.toFixed(1);
                }

                var currentIndex = defaultIndex || 0;
                valueSpan.textContent = getPoint(currentIndex);

                var eToggle = document.createElement("div");
                eToggle.className = "e-toggle";

                var eButton = document.createElement("button");
                eButton.className = "e-button";
                eButton.type = "button";
                eButton.textContent = "E" + currentIndex;

                var selector = document.createElement("div");
                selector.className = "ec-selector";

                for (var i = 0; i <= 6; i++) {
                    (function (idx) {
                        var btn = document.createElement("button");
                        btn.type = "button";
                        btn.textContent = "E" + idx;
                        if (idx === currentIndex)
                            btn.classList.add("active");
                        btn.addEventListener("click", function (ev) {
                            ev.stopPropagation();
                            currentIndex = idx;
                            valueSpan.textContent = getPoint(currentIndex);
                            eButton.textContent = "E" + currentIndex;

                            selector.querySelectorAll("button").forEach(function (b) {
                                b.classList.remove("active");
                            });
                            btn.classList.add("active");

                            selector.style.display = "none";
                            computeCost();
                        });
                        selector.appendChild(btn);
                    })(i);
                }

                eButton.addEventListener("click", function (ev) {
                    ev.stopPropagation();
                    var isOpen = selector.style.display === "flex";
                    document.querySelectorAll(".ec-selector").forEach(function (sel) {
                        sel.style.display = "none";
                    });
                    selector.style.display = isOpen ? "none" : "flex";
                });

                eToggle.appendChild(eButton);
                eToggle.appendChild(selector);

                overlay.appendChild(valueSpan);
                overlay.appendChild(eToggle);
                slot.appendChild(overlay);

                computeCost();
            }

            function createLightconeOverlay(slot, defaultIndex) {
                var old = slot.querySelector(".lc-overlay");
                if (old)
                    old.remove();

                if (!slot.querySelector("img.picked-img"))
                    return;

                var lcOverlay = document.createElement("div");
                lcOverlay.className = "lc-overlay";

                var valueSpan = document.createElement("span");
                valueSpan.className = "lc-value";

                function getPoint(i) {
                    var raw = slot.dataset["pointS" + i];
                    if (raw === undefined)
                        return "0.0";
                    var n = parseFloat(raw);
                    if (isNaN(n))
                        return "0.0";
                    return n.toFixed(1);
                }

                var currentIndex = defaultIndex || 1;
                valueSpan.textContent = getPoint(currentIndex);

                var sToggle = document.createElement("div");
                sToggle.className = "s-toggle";

                var sButton = document.createElement("button");
                sButton.className = "s-button";
                sButton.type = "button";
                sButton.textContent = "S" + currentIndex;

                var selector = document.createElement("div");
                selector.className = "s-selector";

                for (var i = 1; i <= 5; i++) {
                    (function (idx) {
                        var btn = document.createElement("button");
                        btn.type = "button";
                        btn.textContent = "S" + idx;
                        btn.style.background = "transparent";
                        btn.style.border = "1px solid rgba(255,255,255,0.14)";
                        btn.style.color = "#fff";
                        btn.style.padding = "4px 6px";
                        btn.style.fontSize = "12px";
                        btn.style.borderRadius = "4px";
                        btn.style.cursor = "pointer";

                        if (idx === currentIndex)
                            btn.classList.add("active");

                        btn.addEventListener("click", function (ev) {
                            ev.stopPropagation();
                            currentIndex = idx;
                            valueSpan.textContent = getPoint(currentIndex);
                            sButton.textContent = "S" + currentIndex;

                            selector.querySelectorAll("button").forEach(function (b) {
                                b.classList.remove("active");
                            });
                            btn.classList.add("active");

                            selector.style.display = "none";
                            computeCost();
                        });
                        selector.appendChild(btn);
                    })(i);
                }

                sButton.addEventListener("click", function (ev) {
                    ev.stopPropagation();
                    var isOpen = selector.style.display === "flex";
                    document.querySelectorAll(".s-selector").forEach(function (sel) {
                        sel.style.display = "none";
                    });
                    selector.style.display = isOpen ? "none" : "flex";
                });

                sToggle.appendChild(sButton);
                sToggle.appendChild(selector);

                lcOverlay.appendChild(sToggle);
                lcOverlay.appendChild(valueSpan);
                slot.appendChild(lcOverlay);

                computeCost();
            }

            grid.addEventListener("click", function (e) {
                const tile = e.target.closest(".tile");
                if (!tile || tile.classList.contains("disabled"))
                    return;

                const img = tile.querySelector("img");
                if (!img)
                    return;

                const fullImageUrl = img.getAttribute("data-full-image");
                const rarity = img.getAttribute("data-rarity");

                if (currentIndex < slots.length) {
                    const slot = slots[currentIndex];

                    const fullImg = document.createElement("img");
                    fullImg.src = fullImageUrl;
                    fullImg.alt = img.alt;
                    fullImg.className = "picked-img";
                    fullImg.style.width = "100%";
                    fullImg.style.height = "100%";
                    fullImg.style.objectFit = "cover";
                    fullImg.style.borderRadius = "6px";
                    fullImg.draggable = false;

                    slot.classList.remove("blinking");

                    if (slot.classList.contains("slot")) {
                        fullImg.classList.add("gray-filter");
                        slot.style.background = "grey";
                        var oldOverlay = slot.querySelector(".overlay");
                        if (oldOverlay)
                            oldOverlay.remove();
                    } else {
                        if (rarity === "4") {
                            slot.style.background = "rgba(138, 95, 204, 0.5)";
                        } else if (rarity === "5") {
                            slot.style.background = "rgba(201, 163, 106, 0.5)";
                        } else {
                            slot.style.background = "#333";
                        }
                    }

                    for (let j = 0; j <= 6; j++) {
                        const key = "pointE" + j;
                        const val = img.dataset[key] || "";
                        slot.dataset[key] = val;
                    }

                    slot.classList.add("clickable");
                    slot.dataset.rarity = rarity;
                    slot.appendChild(fullImg);
                    if (slot.classList.contains("slot-half")) {
                        createOverlayOnSlot(slot, 0);
                    }

                    tile.classList.add("disabled");
                    tile.style.pointerEvents = "none";
                    tile.style.opacity = "1";

                    stopTurnAndNext();

                    if (currentIndex < slots.length) {
                        slots[currentIndex].classList.add("blinking");
                    }

                    searchInput.value = "";
                    document.querySelectorAll(".grid > div").forEach(div => {
                        div.style.display = "flex";
                    });

                    computeCost();
                }
            });

            document.getElementById("resetBtn").addEventListener("click", function () {
                slots.forEach(function (s) {
                    resetSlot(s);
                });
                currentIndex = 0;

                if (timer) {
                    clearInterval(timer);
                }
                generalTime1 = 30;
                generalTime2 = 30;
                reserveTime1 = 300;
                reserveTime2 = 300;
                penaltyTime1 = 0;
                penaltyTime2 = 0;

                if (slots.length > 0) {
                    slots[0].classList.add("blinking");
                }

                var grid = document.querySelector(".grid");
                grid.querySelectorAll(".tile.disabled").forEach(function (t) {
                    t.classList.remove("disabled");
                    t.style.pointerEvents = "auto";
                    t.style.opacity = "1";
                });

                var team1Input = document.getElementById("team1-input");
                var team2Input = document.getElementById("team2-input");
                var team1RollValue = document.getElementById("team1-roll-value");
                var team2RollValue = document.getElementById("team2-roll-value");
                if (team1Input)
                    team1Input.value = "";
                if (team2Input)
                    team2Input.value = "";
                if (team1RollValue)
                    team1RollValue.textContent = "0";
                if (team2RollValue)
                    team2RollValue.textContent = "0";

                var team1Name = document.getElementById("team1-name");
                var t1Name = document.getElementById("t1-name");
                var team2Name = document.getElementById("team2-name");
                var t2Name = document.getElementById("t2-name");
                if (team1Name)
                    team1Name.textContent = "Team 1";
                if (t1Name)
                    t1Name.innerHTML = "Team 1<br><span></span>";
                if (team2Name)
                    team2Name.textContent = "Team 2";
                if (t2Name)
                    t2Name.innerHTML = "Team 2<br><span></span>";

                var inputs = [
                    "t1-first-half", "t1-second-half", "t1-deaths", "t1-penaltys",
                    "t2-first-half", "t2-second-half", "t2-deaths", "t2-penaltys"
                ];
                for (var i = 0; i < inputs.length; i++) {
                    var inputElement = document.getElementById(inputs[i]);
                    if (inputElement)
                        inputElement.value = "";
                }

                var t1Cost = document.getElementById("t1-cost");
                var t2Cost = document.getElementById("t2-cost");
                if (t1Cost)
                    t1Cost.textContent = "0";
                if (t2Cost)
                    t2Cost.textContent = "0";

                updateDisplay();
                startTurn(0);
                computeCost();
                updateTeamPoints();
            });


            document.addEventListener('DOMContentLoaded', function () {
                updateTeamPoints();

                var costIds = ['t1-cost', 't2-cost'];
                for (var i = 0; i < costIds.length; i++) {
                    var costElement = document.getElementById(costIds[i]);
                    var observer = new MutationObserver(updateTeamPoints);
                    observer.observe(costElement, {childList: true, characterData: true, subtree: true});
                }

                var inputIds = ['t1-first-half', 't1-second-half', 't1-deaths', 't1-penaltys', 't2-first-half', 't2-second-half', 't2-deaths', 't2-penaltys'];
                for (var j = 0; j < inputIds.length; j++) {
                    var inputElement = document.getElementById(inputIds[j]);
                    if (inputElement) {
                        inputElement.addEventListener('input', updateTeamPoints);
                    }
                }
            });

            document.getElementById("undoBtn").addEventListener("click", function () {
                if (currentIndex > 0) {
                    if (timer)
                        clearInterval(timer);

                    currentIndex--;
                    const slot = slots[currentIndex];
                    if (slot) {
                        resetSlot(slot);
                    }

                    const lastTile = [...grid.querySelectorAll(".tile.disabled")].pop();
                    if (lastTile) {
                        lastTile.classList.remove("disabled");
                        lastTile.style.pointerEvents = "auto";
                        lastTile.style.opacity = "1";
                    }

                    if (currentIndex < slots.length - 1) {
                        slots[currentIndex + 1].classList.remove("blinking");
                    }
                    if (currentIndex < slots.length) {
                        slots[currentIndex].classList.add("blinking");
                    }

                    generalTime1 = 30;
                    generalTime2 = 30;

                    startTurn(currentIndex);
                    computeCost();
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

            document.addEventListener("click", function (ev) {
                if (!ev.target.closest(".e-toggle")) {
                    document.querySelectorAll(".ec-selector").forEach(function (sel) {
                        sel.style.display = "none";
                    });
                }
                if (!ev.target.closest(".s-toggle")) {
                    document.querySelectorAll(".s-selector").forEach(function (sel) {
                        sel.style.display = "none";
                    });
                }
            });

            var modal = document.getElementById("lightconeModal");
            var cancelBtn = document.getElementById("cancelBtn");
            var confirmBtn = document.getElementById("confirmBtn");
            var searchInputModal = document.getElementById("lightconeSearch");
            var customSelect = document.getElementById("lightconeSelectCustom");
            var optionsDiv = customSelect.querySelector(".options");
            var selectedLightcone = null;
            var currentSlot = null;

            function openModal(slot) {
                if (!slot.querySelector("img.picked-img"))
                    return;
                if (slot.classList.contains("slot"))
                    return;
                modal.style.display = "block";
                selectedLightcone = null;
                currentSlot = slot;
                searchInputModal.value = "";
                optionsDiv.style.display = "flex";
            }

            function closeModal() {
                modal.style.display = "none";
                selectedLightcone = null;
                currentSlot = null;
                searchInputModal.value = "";
                optionsDiv.style.display = "none";
            }

            searchInputModal.addEventListener("input", function () {
                var term = this.value.toLowerCase();
                optionsDiv.querySelectorAll(".option").forEach(function (opt) {
                    var text = opt.querySelector(".title").textContent.toLowerCase();
                    var sub = opt.querySelector(".sub").textContent.toLowerCase();
                    opt.style.display = (text.includes(term) || sub.includes(term)) ? "flex" : "none";
                });
            });

            optionsDiv.addEventListener("click", function (e) {
                var opt = e.target.closest(".option");
                if (!opt)
                    return;

                optionsDiv.querySelectorAll(".option").forEach(function (o) {
                    o.classList.remove("active");
                });
                opt.classList.add("active");

                selectedLightcone = {
                    name: opt.dataset.value,
                    img: opt.dataset.img,
                    pointS1: opt.dataset.pointS1,
                    pointS2: opt.dataset.pointS2,
                    pointS3: opt.dataset.pointS3,
                    pointS4: opt.dataset.pointS4,
                    pointS5: opt.dataset.pointS5
                };

                searchInputModal.value = opt.dataset.value;
                optionsDiv.style.display = "none";
                e.stopPropagation();
            });

            customSelect.addEventListener("click", function (e) {
                if (e.target.closest("#lightconeSearch"))
                    return;
                optionsDiv.style.display = optionsDiv.style.display === "flex" ? "none" : "flex";
                e.stopPropagation();
            });

            searchInputModal.addEventListener("focus", function () {
                optionsDiv.style.display = "flex";
            });

            document.addEventListener("click", function (e) {
                if (!e.target.closest(".custom-select") && !e.target.closest(".modal-content")) {
                    optionsDiv.style.display = "none";
                }
            });

            cancelBtn.addEventListener("click", closeModal);
            window.addEventListener("click", function (e) {
                if (e.target === modal)
                    closeModal();
            });

            confirmBtn.addEventListener("click", function () {
                if (!currentSlot)
                    return;

                if (searchInputModal.value.trim() === "") {
                    var lcOverlay = currentSlot.querySelector(".lc-overlay");
                    if (lcOverlay)
                        lcOverlay.remove();
                    var activeLightConeImg = currentSlot.querySelector(".active-lightcone-img");
                    if (activeLightConeImg)
                        activeLightConeImg.remove();
                    for (var i = 1; i <= 5; i++) {
                        delete currentSlot.dataset["pointS" + i];
                    }
                } else if (selectedLightcone) {
                    for (var i = 1; i <= 5; i++) {
                        currentSlot.dataset["pointS" + i] = selectedLightcone["pointS" + i] || "0";
                    }

                    var imgSrc = selectedLightcone.img;
                    if (imgSrc) {
                        var activeLightConeImg = currentSlot.querySelector(".active-lightcone-img");
                        if (!activeLightConeImg) {
                            activeLightConeImg = document.createElement("img");
                            activeLightConeImg.className = "active-lightcone-img";
                            activeLightConeImg.style.position = "absolute";
                            activeLightConeImg.style.bottom = "4px";
                            activeLightConeImg.style.right = "4px";
                            activeLightConeImg.style.width = "50px";
                            activeLightConeImg.style.height = "70px";
                            activeLightConeImg.style.borderRadius = "4px";
                            activeLightConeImg.style.border = "1px solid #666";
                            currentSlot.appendChild(activeLightConeImg);
                        }
                        activeLightConeImg.src = imgSrc;
                        activeLightConeImg.style.display = "inline-block";
                    }

                    createLightconeOverlay(currentSlot, 1);
                }

                closeModal();
                computeCost();
            });

            slots.forEach(function (slot) {
                slot.addEventListener("click", function (e) {
                    if (slot.hasChildNodes() && !e.target.closest(".e-toggle") && !e.target.closest(".s-toggle")) {
                        openModal(slot);
                    }
                });
            });

            if (slots.length > 0) {
                slots[0].classList.add("blinking");
            }

            computeCost();
        });
    </script>
</html>