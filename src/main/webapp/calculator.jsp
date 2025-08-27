<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Mirror Cup Draft</title>
        <link href="https://fonts.googleapis.com/css2?family=Roboto&display=swap" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
        <style>
            html, body {

                margin: 0;
                padding: 0;
                height: 100%;
                width: 100%;
                background-color: #2d1b40;
                color: #fff;
                font-family: 'Inter', 'Segoe UI', sans-serif;
                overflow: hidden;
            }

            .main-container {
                display: flex;
                height: 100vh;
                width: 100vw;
            }

            .middle-panel {
                flex: 0 0 50%;
                padding: 16px;
                text-align: center;
                display: flex;
                flex-direction: column;
                align-items: center;
            }

            .efficiency-box {
                background-color: #2b2b2b;
                border: 1px solid #555;
                border-radius: 8px;
                padding: 12px;
                text-align: left;
                color: white;
                width: 400px;
                margin: auto;
            }
            .efficiency-box input {
                background-color: #1c1c1c;
                color: white;
                border: 1px solid #444;
            }
            .efficiency-box input:focus {
                outline: none;
                border-color: #00bfff;
                box-shadow: 0 0 5px #00bfff;
            }


            .slot-container {
                display: flex;
                justify-content: center;
                gap: 15px;
            }

            .slot-wrapper {
                display: flex;
                flex-direction: column;
                align-items: center;
                gap: 10px;
            }


            .slot {
                width: 140px;
                height: 280px;
                background-color: #6f5b81;
                border: 2px dashed #444;
                border-radius: 6px;
                margin-top: 100px;
                position: relative;
                overflow: hidden;
            }
            .slot .overlay {
                position: absolute;
                top: 6px;
                left: 6px;
                display: flex;
                align-items: center;
                gap: 8px;
                padding: 6px;
                background: rgba(0,0,0,0.55);
                color: #fff;
                font-size: 12px;
                border-radius: 6px;
                z-index: 5;
                pointer-events: auto;
            }

            /* Value hiển thị điểm */
            .slot .overlay .value {
                min-width: 75px;
                text-align: center;
                font-weight: 600;
            }

            /* Toggle nhỏ chứa nút E hiện tại và popup selector */
            .slot .overlay .e-toggle {
                position: relative;
                display: inline-block;
            }

            /* nút E hiện tại (kích thước nhỏ) */
            .slot .overlay .e-toggle .e-button {
                background: rgba(255,255,255,0.06);
                border: 1px solid rgba(255,255,255,0.12);
                color: #fff;
                padding: 4px 8px;
                font-size: 12px;
                border-radius: 4px;
                cursor: pointer;
                white-space: nowrap;
            }

            .slot .overlay .e-toggle .ec-selector {
                display: none;
                position: absolute;
                top: 110%;
                left: 0;
                margin-top: 6px;
                padding: 6px;
                background: rgba(0,0,0,0.8);
                border-radius: 6px;
                box-shadow: 0 6px 18px rgba(0,0,0,0.4);
                z-index: 10;
                display: flex;
                gap: 6px;
                flex-wrap: wrap;
            }

            /* nút trong ec-selector */
            .slot .overlay .e-toggle .ec-selector button {
                background: transparent;
                border: 1px solid rgba(255,255,255,0.14);
                color: #fff;
                padding: 4px 6px;
                font-size: 12px;
                border-radius: 4px;
                cursor: pointer;
                line-height: 1;
            }

            /* active */
            .slot .overlay .e-toggle .ec-selector button.active {
                background: rgba(255,255,255,0.18);
                border-color: rgba(255,255,255,0.28);
            }

            /* Ẩn thanh chọn mặc định */
            .slot .ec-selector {
                display: none;
                flex-direction: column;
                gap: 4px;
                margin-left: 4px;
            }

            /* Khi hover vào overlay hoặc chính selector thì hiển thị */
            .slot .overlay:hover .ec-selector {
                display: flex;
            }


            .trash-btn {
                background-color: #e74c3c;
                border: none;
                color: white;
                padding: 6px 14px;
                border-radius: 6px;
                cursor: pointer;
                font-size: 16px;
                transition: background 0.2s;
            }

            .trash-btn:hover {
                background-color: #c0392b;
            }



            .btn-danger, .btn-success, .btn-primary {
                border-radius: 6px;
                font-weight: 500;
            }

            .btn-danger {
                background-color: #e74c3c;
                border: none;
            }
            .btn-success {
                background-color: #27ae60;
                border: none;
            }
            .btn-primary {
                background-color: #3498db;
                border: none;
            }

            /* ========== RIGHT PANEL (Character grid) ========== */
            .right-panel {
                flex: 0 0 50%;
                padding: 16px;
                overflow-y: auto;
            }

            .search-bar {
                width: 100%;
                padding: 8px 12px;
                border-radius: 6px;
                border: none;
                outline: none;
                margin-bottom: 12px;
                background-color: #1c1c1c;
                color: #fff;
            }

            .grid {
                display: grid;
                grid-template-columns: repeat(7, 1fr);
                gap: 15px;
                padding: 10px;   /* thêm khoảng cách bên trong container */
            }

            .grid div {
                transition: transform 0.2s ease, box-shadow 0.2s ease;
            }

            .grid div:hover {
                transform: scale(1.1);
                box-shadow: 0 4px 15px rgba(0, 0, 0, 0.4);
                z-index: 5;
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
            .btnclear {
                margin-top: 10px;
                width: 100%; /* ăn theo chiều rộng slot-container */
                max-width: calc(4 * 100px + 3 * 20px); /* 4 ô mỗi ô 100px + 3 khoảng gap */
            }


            .slot.clickable {
                cursor: pointer;
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

            /* ================== Lightcone S Selector (giống E) ================== */
            .slot .lc-overlay .s-toggle {
                position: relative;
                display: inline-block;
            }

            /* nút S hiện tại */
            .slot .lc-overlay .s-toggle .s-button {
                background: rgba(255,255,255,0.06);
                border: 1px solid rgba(255,255,255,0.12);
                color: #fff;
                padding: 4px 8px;
                font-size: 12px;
                border-radius: 4px;
                cursor: pointer;
                white-space: nowrap;
            }

            /* danh sách S1–S5 xổ xuống */
            .slot .lc-overlay .s-toggle .s-selector {
                display: none;
                position: absolute;
                bottom: 110%;
                left: 0;
                margin-bottom: 6px; /* thay margin-top */
                padding: 6px;
                background: rgba(0,0,0,0.8);
                border-radius: 6px;
                box-shadow: 0 6px 18px rgba(0,0,0,0.4);
                z-index: 10;
                display: flex;
                gap: 6px;
                flex-wrap: wrap;
            }

            /* nút trong selector */
            .slot .lc-overlay .s-toggle .s-selector button {
                background: transparent;
                border: 1px solid rgba(255,255,255,0.14);
                color: #fff;
                padding: 4px 6px;
                font-size: 12px;
                border-radius: 4px;
                cursor: pointer;
                line-height: 1;
            }

            /* active button */
            .slot .lc-overlay .s-toggle .s-selector button.active {
                background: rgba(255,255,255,0.18);
                border-color: rgba(255,255,255,0.28);
            }

            .selected {
                filter: grayscale(100%) brightness(0.6);
                pointer-events: none; /* Không cho click lại */
            }
        </style>
    </head>
    <body>
        <div class="main-container">
            <!-- MIDDLE PANEL -->
            <div class="back-btn-container">
                <a href="${pageContext.request.contextPath}/Home" class="back-btn">Back</a>
            </div>
            <div class="middle-panel">
                <div class="slot-container">
                    <div class="slot-wrapper">
                        <div class="slot"></div>
                        <button class="trash-btn"><i class="fas fa-trash">Delete</i></button>
                    </div>
                    <div class="slot-wrapper">
                        <div class="slot"></div>
                        <button class="trash-btn"><i class="fas fa-trash">Delete</i></button>
                    </div>
                    <div class="slot-wrapper">
                        <div class="slot"></div>
                        <button class="trash-btn"><i class="fas fa-trash">Delete</i></button>
                    </div>
                    <div class="slot-wrapper">
                        <div class="slot"></div>
                        <button class="trash-btn"><i class="fas fa-trash">Delete</i></button>
                    </div>
                </div>


                <div class="d-flex justify-content-center gap-2 mb-3">
                    <button class="btn btn-danger btnclear">CLEAR</button>
                </div>




                <div class="efficiency-box d-flex gap-3">
                    <div>
                        <label for="cycles" class="form-label text-white">Cycles Taken:</label>
                        <input type="number" id="cycles" class="form-control" value="0" min="0">
                    </div>
                    <div>
                        <label for="cost" class="form-label text-white">Cost:</label>
                        <input type="number" id="cost" class="form-control" value="0" min="0" readonly>
                    </div>
                </div>


            </div>


            <!-- RIGHT PANEL -->
            <div class="right-panel">
                <input type="text" class="search-bar" id="searchInput" placeholder="Search characters">
                <div class="grid">
                    <c:forEach var="character" items="${listCharacterBanPick}">
                        <c:choose>
                            <c:when test="${character.rarityID.starName == '5-Star'}">

                                <div style="width: 80px; height: 80px;
                                     border-radius: 0.3em; align-items: center; justify-content: center;
                                     background-color: rgba(201, 163, 106, 0.5); filter: none; display: flex; cursor: pointer; border: 3px solid rgb(57, 105, 178);">
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

                                <div style="width: 80px; height: 80px;
                                     border-radius: 0.3em; align-items: center; justify-content: center;
                                     background-color: rgba(138, 95, 204, 0.5); filter: none; display: flex; cursor: pointer; border: 3px solid rgb(57, 105, 178);">
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
                            <c:otherwise>

                                <div style="width: 80px; height: 80px;
                                     border-radius: 0.3em; align-items: center; justify-content: center;
                                     background-color: #7f8c8d; filter: none; display: flex; cursor: pointer; border: 3px solid rgb(57, 105, 178);">
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
                                         data-rarity="3" alt="Character 3"
                                         style="width: 100%; height: 100%; object-fit: cover; border-radius: 0.3em;" />
                                </div>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            // ===== Khai báo dùng chung =====
            var slots = document.querySelectorAll(".slot");
            var grid = document.querySelector(".grid");
            var clearBtn = document.querySelector(".btn-danger");
            var trashBtns = document.querySelectorAll(".trash-btn");
            var cyclesInput = document.getElementById("cycles");
            var costInput = document.getElementById("cost");

            // ===== Reset Slot =====
            function resetSlot(slot) {
                slot.innerHTML = "";
                slot.classList.remove("clickable");
                slot.style.background = "#333";
                for (var i = 0; i <= 6; i++) {
                    delete slot.dataset["pointE" + i];
                }
                for (var j = 1; j <= 5; j++) {
                    delete slot.dataset["pointS" + j];
                }
                delete slot.dataset.rarity;
            }

            // ===== Compute Cost =====
            function computeCost() {
                var totalOverlay = 0;

                slots.forEach(function (slot) {
                    var overlay = slot.querySelector(".overlay .value");
                    if (overlay) {
                        var n = parseFloat(overlay.textContent);
                        if (!isNaN(n))
                            totalOverlay += n;
                    }

                    var lcVal = slot.querySelector(".lc-overlay .lc-value");
                    if (lcVal) {
                        var m = parseFloat(lcVal.textContent);
                        if (!isNaN(m))
                            totalOverlay += m;
                    }
                });

                var cycles = parseFloat(cyclesInput.value) || 0;
                var cost = totalOverlay + cycles * 5;
                costInput.value = parseFloat(cost.toFixed(1));
            }

            // ===== Character Overlay =====
            function createOverlayOnSlot(slot, defaultIndex) {
                var old = slot.querySelector(".overlay");
                if (old)
                    old.remove();
                if (!slot.firstChild)
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

            // ===== Lightcone Overlay =====
            function createLightconeOverlay(slot, defaultIndex) {
                var old = slot.querySelector(".lc-overlay");
                if (old)
                    old.remove();

                var lcOverlay = document.createElement("div");
                lcOverlay.className = "lc-overlay";
                lcOverlay.style.position = "absolute";
                lcOverlay.style.bottom = "6px";
                lcOverlay.style.left = "6px";
                lcOverlay.style.padding = "4px 6px";
                lcOverlay.style.background = "rgba(0,0,0,0.55)";
                lcOverlay.style.borderRadius = "6px";
                lcOverlay.style.fontSize = "12px";
                lcOverlay.style.color = "#fff";
                lcOverlay.style.display = "flex";
                lcOverlay.style.alignItems = "center";
                lcOverlay.style.gap = "6px";

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
                selector.style.display = "none";
                selector.style.position = "absolute";
                selector.style.bottom = "110%";
                selector.style.left = "0";
                selector.style.padding = "6px";
                selector.style.background = "rgba(0,0,0,0.8)";
                selector.style.borderRadius = "6px";
                selector.style.boxShadow = "0 6px 18px rgba(0,0,0,0.4)";
                selector.style.zIndex = "10";
                selector.style.display = "flex";
                selector.style.gap = "6px";

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

                sToggle.style.position = "relative";
                sToggle.appendChild(sButton);
                sToggle.appendChild(selector);

                lcOverlay.appendChild(valueSpan);
                lcOverlay.appendChild(sToggle);
                slot.appendChild(lcOverlay);

                computeCost();
            }

            // ===== Grid chọn nhân vật =====
            grid.addEventListener("click", function (e) {
                var img = e.target.closest("img");
                if (!img)
                    return;

                var fullImageUrl = img.getAttribute("data-full-image");
                var rarity = img.getAttribute("data-rarity");

                for (var i = 0; i < slots.length; i++) {
                    var slot = slots[i];
                    if (!slot.hasChildNodes()) {
                        var fullImg = document.createElement("img");
                        fullImg.src = fullImageUrl;
                        fullImg.alt = img.alt;
                        fullImg.style.width = "100%";
                        fullImg.style.height = "100%";
                        fullImg.style.objectFit = "cover";
                        fullImg.style.borderRadius = "6px";
                        fullImg.draggable = false;

                        for (var j = 0; j <= 6; j++) {
                            var key = "pointE" + j;
                            var val = img.dataset[key] || img.getAttribute("data-" + key) || "";
                            slot.dataset[key] = val;
                        }

                        slot.classList.add("clickable");
                        slot.dataset.rarity = rarity;

                        if (rarity === "4")
                            slot.style.background = "rgba(138, 95, 204, 0.5)";
                        else if (rarity === "5")
                            slot.style.background = "rgba(201, 163, 106, 0.5)";
                        else
                            slot.style.background = "#333";

                        slot.appendChild(fullImg);
                        createOverlayOnSlot(slot, 0);
                        break;
                    }
                }
            });

            // Clear button
            if (clearBtn) {
                clearBtn.addEventListener("click", function () {
                    slots.forEach(resetSlot);
                    computeCost();
                });
            }

            // Trash buttons
            trashBtns.forEach(function (btn, index) {
                btn.addEventListener("click", function () {
                    resetSlot(slots[index]);
                    computeCost();
                });
            });

            // Đổi cycles thì tính lại
            if (cyclesInput) {
                cyclesInput.addEventListener("input", computeCost);
            }

            // Click ra ngoài thì ẩn popup
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

            // ===== Modal Lightcone =====
            var modal = document.getElementById("lightconeModal");
            var cancelBtn = document.getElementById("cancelBtn");
            var confirmBtn = document.getElementById("confirmBtn");
            var searchInput = document.getElementById("lightconeSearch");
            var customSelect = document.getElementById("lightconeSelectCustom");
            var optionsDiv = customSelect.querySelector(".options");
            var selectedLightcone = null;
            var currentSlot = null;

            function openModal(slot) {
                modal.style.display = "block";
                selectedLightcone = null;
                currentSlot = slot;
                searchInput.value = "";
                optionsDiv.style.display = "flex"; // Show options when modal opens
            }

            function closeModal() {
                modal.style.display = "none";
                selectedLightcone = null;
                currentSlot = null;
                searchInput.value = "";
                optionsDiv.style.display = "none"; // Ensure options are hidden when modal closes
            }

            // Handle search input
            searchInput.addEventListener("input", function () {
                var term = this.value.toLowerCase();
                optionsDiv.querySelectorAll(".option").forEach(function (opt) {
                    var text = opt.querySelector(".title").textContent.toLowerCase();
                    var sub = opt.querySelector(".sub").textContent.toLowerCase();
                    opt.style.display = (text.includes(term) || sub.includes(term)) ? "flex" : "none";
                });
            });

            // Handle clicking an option
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

                searchInput.value = opt.dataset.value;
                optionsDiv.style.display = "none"; // Hide options after selection
                e.stopPropagation();
            });

            // Toggle options visibility when clicking custom-select (but not search input)
            customSelect.addEventListener("click", function (e) {
                if (e.target.closest("#lightconeSearch"))
                    return; // Ignore clicks on search input
                optionsDiv.style.display = optionsDiv.style.display === "flex" ? "none" : "flex";
                e.stopPropagation();
            });

            // Show options when focusing on search input
            searchInput.addEventListener("focus", function () {
                optionsDiv.style.display = "flex";
            });

            // Hide options when clicking outside custom-select
            document.addEventListener("click", function (e) {
                if (!e.target.closest(".custom-select") && !e.target.closest(".modal-content")) {
                    optionsDiv.style.display = "none";
                }
            });

            // Close modal on cancel or clicking outside
            cancelBtn.addEventListener("click", closeModal);
            window.addEventListener("click", function (e) {
                if (e.target === modal)
                    closeModal();
            });

            // Confirm lightcone selection
            confirmBtn.addEventListener("click", function () {
                if (currentSlot) {
                    if (searchInput.value.trim() === "") {
                        // If search input is empty, remove lightcone overlay and image
                        var lcOverlay = currentSlot.querySelector(".lc-overlay");
                        if (lcOverlay) {
                            lcOverlay.remove();
                        }
                        var activeLightConeImg = currentSlot.querySelector(".active-lightcone-img");
                        if (activeLightConeImg) {
                            activeLightConeImg.remove();
                        }
                        // Clear lightcone dataset values
                        for (var i = 1; i <= 5; i++) {
                            delete currentSlot.dataset["pointS" + i];
                        }
                    } else if (selectedLightcone) {
                        // Apply selected lightcone
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
                }
            });

            // Open modal on single click
            document.querySelectorAll(".slot").forEach(function (slot) {
                slot.addEventListener("click", function (e) {
                    if (slot.hasChildNodes() && !e.target.closest(".e-toggle") && !e.target.closest(".s-toggle")) {
                        openModal(slot);
                    }
                });
            });

            computeCost();
        });

        document.getElementById('searchInput').addEventListener('keyup', function () {
            const input = this.value.toLowerCase();
            document.querySelectorAll('.grid > div').forEach(div => {
                const img = div.querySelector('img');
                const name = img ? img.alt.toLowerCase() : '';
                div.style.display = name.includes(input) ? 'flex' : 'none';
            });
        });
    </script>



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

</html>
