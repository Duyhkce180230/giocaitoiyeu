<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="model.User" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Character List - Honkai Star Rail</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
        <style>
            body {
                background-color: #2d1b47;
                color: #fff;
                font-family: 'Segoe UI', sans-serif;
            }

            h1 {
                color: #fff;
            }

            .table {
                background-color: #3a2c59;
                color: #fff;
            }

            .table th,
            .table td {
                vertical-align: middle;
            }

            .table thead {
                background-color: #1f1233;
            }

            .btn-success,
            .btn-primary,
            .btn-danger {
                border: none;
            }

            .btn-success {
                background-color: #6a4cc4;
            }

            .btn-primary {
                background-color: #5246c0;
            }

            .btn-danger {
                background-color: #c04c7a;
            }

            .modal-content {
                background-color: #2d1b47;
                color: #fff;
            }

            .form-control,
            .form-select {
                background-color: #3a2c59;
                color: #fff;
                border: 1px solid #5a4d80;
            }

            .form-control:focus,
            .form-select:focus {
                border-color: #866cf0;
                box-shadow: 0 0 0 0.25rem rgba(134, 108, 240, 0.25);
            }

            .btn-close {
                filter: invert(1);
            }

            .modal-header,
            .modal-footer {
                border: none;
            }

            img.character-img {
                width: 60px;
                height: auto;
                border-radius: 10px;
                border: 2px solid #866cf0;
            }
            .input-group {
                background-color: #3a2c59;
                border: none;
            }

            .input-group-text {
                background-color: #3a2c59;
                border: none;
            }
            #searchInput::placeholder {
                color: #ccc;
            }
            #searchInput {
                background-color: #3a2c59;
                border: none;
                color: #fff;
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
        </style>

    </head>
    <body class="p-5">

        <div class="d-flex justify-content-between align-items-center mb-4 flex-wrap">

            <div class="back-btn-container">
                <a href="${pageContext.request.contextPath}/Home" class="back-btn">Back</a>
            </div>


            <h1 class="mb-0 text-white text-center flex-grow-1">List Character Honkai Star Rail</h1>


            <c:if test="${not empty sessionScope.user}">
                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#createModal">
                    + Add New Character
                </button>
            </c:if>
        </div>



        <div class="input-group mb-4 w-100 w-md-50">
            <span class="input-group-text bg-dark border-0"><i class="fas fa-search text-white"></i></span>
            <input type="text" id="searchInput" class="form-control" placeholder="Search character name...">
        </div>


        <table class="table table-bordered table-hover text-center">
            <thead class="table-dark">
                <tr>
                    <th>Image Icon</th>
                    <th>Image Full</th>
                    <th>Name</th>
                    <th>E0</th>
                    <th>E1</th>
                    <th>E2</th>
                    <th>E3</th>
                    <th>E4</th>
                    <th>E5</th>
                    <th>E6</th>
                        <c:if test="${not empty sessionScope.user}">
                        <th>Actions</th>
                        </c:if>

                </tr>
            </thead>
            <tbody>
                <c:forEach var="character" items="${listCharacter}">
                    <tr>
                        <td><img class="character-img" src="${pageContext.request.contextPath}/${character.imageIcon}" alt="${character.characterName}"></td>
                        <td><img class="character-img" src="${pageContext.request.contextPath}/${character.imageFull}" alt="${character.characterName}"></td>
                        <td>${character.characterName}</td>
                        
                        <td><fmt:formatNumber value="${character.pointE0}" type="number" minFractionDigits="0" /></td>
                        <td><fmt:formatNumber value="${character.pointE1}" type="number" minFractionDigits="0" /></td>
                        <td><fmt:formatNumber value="${character.pointE2}" type="number" minFractionDigits="0" /></td>
                        <td><fmt:formatNumber value="${character.pointE3}" type="number" minFractionDigits="0" /></td>
                        <td><fmt:formatNumber value="${character.pointE4}" type="number" minFractionDigits="0" /></td>
                        <td><fmt:formatNumber value="${character.pointE5}" type="number" minFractionDigits="0" /></td>
                        <td><fmt:formatNumber value="${character.pointE6}" type="number" minFractionDigits="0" /></td>
                        <c:if test="${not empty sessionScope.user}">
                            <td>
                                <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editModal${character.characterID}">Edit</button>
                                <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal${character.characterID}">Delete</button>
                            </td>
                        </c:if>


                    </tr>

                </c:forEach>
            </tbody>
        </table>


        <div class="modal fade" id="createModal" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <form action="${pageContext.request.contextPath}/character?action=create" method="post" enctype="multipart/form-data">
                        <div class="modal-header">
                            <h5 class="modal-title">Create New Character</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" name="action" value="create">

                            <div class="mb-3">
                                <label>Name</label>
                                <input type="text" name="name" class="form-control" required>
                            </div>

                            <div class="row mb-3">
                                <div class="col-md-4">
                                    <label>Rarity</label>
                                    <select name="rarityId" class="form-select">
                                        <c:forEach var="r" items="${listRarity}">
                                            <option value="${r.rarityID}">${r.starName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-4">
                                    <label>Element</label>
                                    <select name="elementId" class="form-select">
                                        <c:forEach var="e" items="${listElement}">
                                            <option value="${e.elementID}">${e.elementName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-4">
                                    <label>Path</label>
                                    <select name="pathId" class="form-select">
                                        <c:forEach var="p" items="${listPath}">
                                            <option value="${p.pathID}">${p.pathName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label>Description</label>
                                <textarea name="description" class="form-control" rows="3">No Data</textarea>
                            </div>

                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label>Image Icon</label>
                                    <input type="file" name="imageIcon" class="form-control" accept="image/*" required>
                                </div>
                                <div class="col-md-6">
                                    <label>Image Full</label>
                                    <input type="file" name="imageFull" class="form-control" accept="image/*" required>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label>Status</label>
                                <select name="status" class="form-select">
                                    <option value="1">Active</option>
                                    <option value="0">Inactive</option>
                                </select>
                            </div>

                            <label>Points E0 - E6</label>
                            <div style="display: flex; gap: 8px;">
                                <input type="number" name="pointE0" class="form-control" value="0" step="any" placeholder="Point E0" style="flex:1;">
                                <input type="number" name="pointE1" class="form-control" value="0" step="any" placeholder="Point E1" style="flex:1;">
                                <input type="number" name="pointE2" class="form-control" value="0" step="0.25" placeholder="Point E2" style="flex:1;">
                                <input type="number" name="pointE3" class="form-control" value="0" step="0.25" placeholder="Point E3" style="flex:1;">
                                <input type="number" name="pointE4" class="form-control" value="0" step="0.25" placeholder="Point E4" style="flex:1;">
                                <input type="number" name="pointE5" class="form-control" value="0" step="0.25" placeholder="Point E5" style="flex:1;">
                                <input type="number" name="pointE6" class="form-control" value="0" step="0.25" placeholder="Point E6" style="flex:1;">
                            </div>


                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-success">Create</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

    </div>
    <c:forEach var="character" items="${listCharacter}">
        <div class="modal fade" id="editModal${character.characterID}" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <form method="POST" action="${pageContext.request.contextPath}/character?action=edit" enctype="multipart/form-data">
                        <div class="modal-header">
                            <h5 class="modal-title">Edit Character</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" name="action" value="update">
                            <input type="hidden" name="characterId" value="${character.characterID}">

                            <div class="mb-3">
                                <label>Name</label>
                                <input type="text" name="name" value="${character.characterName}" class="form-control" required>
                            </div>

                            <div class="row mb-3">
                                <div class="col-md-4">
                                    <label>Rarity</label>
                                    <select name="rarityId" class="form-select">
                                        <c:forEach var="r" items="${listRarity}">
                                            <option value="${r.rarityID}" ${r.rarityID == character.rarityID.rarityID ? 'selected' : ''}>${r.starName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-4">
                                    <label>Element</label>
                                    <select name="elementId" class="form-select">
                                        <c:forEach var="e" items="${listElement}">
                                            <option value="${e.elementID}" ${e.elementID == character.elementID.elementID ? 'selected' : ''}>${e.elementName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="col-md-4">
                                    <label>Path</label>
                                    <select name="pathId" class="form-select">
                                        <c:forEach var="p" items="${listPath}">
                                            <option value="${p.pathID}" ${p.pathID == character.pathID.pathID ? 'selected' : ''}>${p.pathName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label>Description</label>
                                <textarea name="description" class="form-control" rows="3">${character.description}</textarea>
                            </div>

                            <div class="row mb-3">
                                <div class="col-md-6">
                                    <label>Image Icon</label>
                                    <input type="file" name="imageIcon" class="form-control" accept=".png" >
                                    <input type="hidden" name="oldImageIcon" value="${character.imageIcon}" required>
                                    <small style="color: white;">Current: ${character.imageIcon}</small>
                                </div>
                                <div class="col-md-6">
                                    <label>Image Full</label>
                                    <input type="file" name="imageFull" class="form-control" accept=".png">
                                    <input type="hidden" name="oldImageFull" value="${character.imageFull}" required>
                                    <small style="color: white;">Current: ${character.imageFull}</small>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label>Status</label>
                                <select name="status" class="form-select">
                                    <option value="1" ${character.status == 1 ? 'selected' : ''}>Active</option>
                                    <option value="0" ${character.status == 0 ? 'selected' : ''}>Inactive</option>
                                </select>
                            </div>

                            <label>Points E0 - E6</label>
                            <div class="d-flex gap-2 mb-3">
                                <input type="number" name="pointE0" id="pointE0${character.characterID}" value="${empty character.pointE0 ? 0 : character.pointE0}" class="form-control" min="0" step="0.25" placeholder="Point E0" style="flex: 1;">
                                <input type="number" name="pointE1" id="pointE1${character.characterID}" value="${empty character.pointE1 ? 0 : character.pointE1}" class="form-control" min="0" step="0.25" placeholder="Point E1" style="flex: 1;">
                                <input type="number" name="pointE2" id="pointE2${character.characterID}" value="${empty character.pointE2 ? 0 : character.pointE2}" class="form-control" min="0" step="0.25" placeholder="Point E2" style="flex: 1;">
                                <input type="number" name="pointE3" id="pointE3${character.characterID}" value="${empty character.pointE3 ? 0 : character.pointE3}" class="form-control" min="0" step="0.25" placeholder="Point E3" style="flex: 1;">
                                <input type="number" name="pointE4" id="pointE4${character.characterID}" value="${empty character.pointE4 ? 0 : character.pointE4}" class="form-control" min="0" step="0.25" placeholder="Point E4" style="flex: 1;">
                                <input type="number" name="pointE5" id="pointE5${character.characterID}" value="${empty character.pointE5 ? 0 : character.pointE5}" class="form-control" min="0" step="0.25" placeholder="Point E5" style="flex: 1;">
                                <input type="number" name="pointE6" id="pointE6${character.characterID}" value="${empty character.pointE6 ? 0 : character.pointE6}" class="form-control" min="0" step="0.25" placeholder="Point E6" style="flex: 1;">
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save Changes</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <div class="modal fade" id="deleteModal${character.characterID}" tabindex="-1" aria-labelledby="deleteModalLabel${character.characterID}" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <form method="POST" action="${pageContext.request.contextPath}/character">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="id" value="${character.characterID}">

                        <div class="modal-header bg-danger text-white">
                            <h5 class="modal-title" id="deleteModalLabel${character.characterID}">Confirm Deletion</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>

                        <div class="modal-body">
                            Are you sure you want to delete character <strong>${character.characterName}</strong>?
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <button type="submit" class="btn btn-danger">Delete</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>


    </c:forEach>


</body>
<jsp:include page="/layout/toast.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script>

    function formatNumber(num) {
        if (!num || isNaN(num))
            return "0";

        const parsed = parseFloat(num);
        return parsed.toString();
    }

    $(document).ready(function () {

        $("[id^='editModal']").on("show.bs.modal", function () {
            const modal = $(this);
            const characterId = modal.attr("id").replace("editModal", "");

            for (let i = 0; i <= 6; i++) {
                const input = modal.find("#pointE" + i + characterId);
                const formattedValue = formatNumber(input.val());
                input.val(formattedValue);
            }
        });
    });
</script>


<script>
    document.getElementById('searchInput').addEventListener('keyup', function () {
        let input = this.value.toLowerCase();
        let rows = document.querySelectorAll('table tbody tr');

        rows.forEach(function (row) {
            let nameCell = row.cells[2];
            if (nameCell) {
                let name = nameCell.textContent.toLowerCase();
                row.style.display = name.includes(input) ? '' : 'none';
            }
        });
    });
</script>
</html>
