<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Light Cone List - Honkai Star Rail</title>
        <link rel="icon" type="image/png" href="images/LogoGC.png">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">

        <style>
            body {
                background-color: #1f1235;
                color: white;
            }
            .table {
                background-color: #3a2c59;
                color: #fff;
            }
            .search-box input::placeholder {
                color: #ccc;
            }
            .search-box input {

            }
            .search-box .input-group-text {
                background-color: #3a2c59;
                color: white;
                border: none;
            }
            .search-box input.form-control {
                background-color: #3a2c59;
                color: white;
                border: none;
            }
            .table th, .table td {
                vertical-align: middle;
            }
            .img-thumbnail {
                max-height: 80px;
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
            .container-fluid {
                width: 100%;
                padding-left: 0;
                padding-right: 0;
                margin-left: 0;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid mt-4">
            <div class="align-items-center mb-4">
                <div class="back-btn-container">
                    <a href="${pageContext.request.contextPath}/Home" class="back-btn">Back</a>
                </div>

                <div class="col text-center">
                    <h1 class="mb-0 text-white">List Light Cone Honkai Star Rail</h1>
                </div>

                <div class=" text-end">
                    <c:if test="${not empty sessionScope.user}">
                        <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#createModal">
                            + New
                        </button>
                    </c:if>
                </div>
            </div>


            <!-- Thanh tìm kiếm -->
            <div class="input-group mb-4 w-100 w-md-50">
                <span class="input-group-text bg-dark border-0"><i class="fas fa-search text-white"></i></span>
                <input type="text" id="searchInput" class="form-control" placeholder="Search character name...">
            </div>
            <table class="table table-bordered table-hover text-center">
                <thead class="table-dark">
                    <tr>
                        <th>Image</th>
                        <th>Signature Character</th>
                        <th>Name</th>
                        <th>S1</th>
                        <th>S2</th>
                        <th>S3</th>
                        <th>S4</th>
                        <th>S5</th>
                            <c:if test="${not empty sessionScope.user}">
                            <th>Actions</th>
                            </c:if>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cone" items="${listLightCone}">
                        <tr>
                            <td><img src="${pageContext.request.contextPath}/${cone.lightConeImage}" class="img-thumbnail" alt="Light Cone Image"></td>
                            <td>${cone.characterSignatureID.characterName}</td>
                            <td>${cone.lightConeName}</td>
                            <td><fmt:formatNumber value="${cone.pointS1}" type="number" minFractionDigits="0" /></td>
                            <td><fmt:formatNumber value="${cone.pointS2}" type="number" minFractionDigits="0" /></td>
                            <td><fmt:formatNumber value="${cone.pointS3}" type="number" minFractionDigits="0" /></td>
                            <td><fmt:formatNumber value="${cone.pointS4}" type="number" minFractionDigits="0" /></td>
                            <td><fmt:formatNumber value="${cone.pointS5}" type="number" minFractionDigits="0" /></td>
                            <c:if test="${not empty sessionScope.user}">
                                <td>
                                    <button class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#editModal${cone.lightConeID}">Edit</button>
                                    <button class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#deleteModal${cone.lightConeID}">Delete</button>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
    <!-- Modal Create Light Cone -->
    <div class="modal fade" id="createModal" tabindex="-1" aria-labelledby="createModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg">
            <div class="modal-content bg-dark text-white">
                <form action="${pageContext.request.contextPath}/lightcone?action=create" method="post" enctype="multipart/form-data">
                    <div class="modal-header">
                        <h5 class="modal-title">Add New Light Cone</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body row g-3">
                        <input type="hidden" name="action" value="create">
                        <div class="col-md-6">
                            <label>Name</label>
                            <input type="text" name="lightConeName" class="form-control" required>
                        </div>
                        <div class="col-md-6">
                            <label>Rarity</label>
                            <select name="rarityID" class="form-select" required>
                                <c:forEach var="rarity" items="${listRarity}">
                                    <option value="${rarity.rarityID}">${rarity.starName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label>Path</label>
                            <select name="pathID" class="form-select" required>
                                <c:forEach var="path" items="${listPath}">
                                    <option value="${path.pathID}">${path.pathName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-6">
                            <label>Signature Character</label>
                            <select name="characterSignatureID" class="form-select">
                                <c:forEach var="ch" items="${listCharacter}">
                                    <option value="${ch.characterID}">${ch.characterName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="col-12">
                            <label>Description</label>
                            <textarea name="description" class="form-control" rows="3" required>No Data</textarea>
                        </div>
                        <div class="col-md-6">
                            <label>Image</label>
                            <input type="file" name="lightConeImage" class="form-control" accept="image/*" required>
                        </div>
                        <div class="mb-2">
                            <label>Status</label>
                            <select name="status" class="form-select" required>
                                <option value="1">Active</option>
                                <option value="0">Inactive</option>
                            </select>
                        </div>
                        <div class="row">
                            <div class="col-md" style="flex: 0 0 20.83%; max-width: 20%;">
                                <label>Point 1</label>
                                <input type="number" name="pointS1" class="form-control" value="0" step="any" required>
                            </div>
                            <div class="col-md" style="flex: 0 0 20.83%; max-width: 20%;">
                                <label>Point S2</label>
                                <input type="number" name="pointS2" class="form-control" value="0" step="0.25" required>
                            </div>
                            <div class="col-md" style="flex: 0 0 20.83%; max-width: 20%;">
                                <label>Point S3</label>
                                <input type="number" name="pointS3" class="form-control" value="0" step="0.25" required>
                            </div>
                            <div class="col-md" style="flex: 0 0 20.83%; max-width: 20%;">
                                <label>Point S4</label>
                                <input type="number" name="pointS4" class="form-control" value="0" step="0.25" required>
                            </div>
                            <div class="col-md" style="flex: 0 0 20.83%; max-width: 20%;">
                                <label>Point S5</label>
                                <input type="number" name="pointS5" class="form-control" value="0" step="0.25" required>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-success">Save</button>
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Modal Edit Light Cone -->
    <c:forEach var="cone" items="${listLightCone}">
        <div class="modal fade" id="editModal${cone.lightConeID}" tabindex="-1">
            <div class="modal-dialog modal-lg">
                <div class="modal-content bg-dark text-white">
                    <form action="${pageContext.request.contextPath}/lightcone?action=edit" method="post" enctype="multipart/form-data">
                        <div class="modal-header">
                            <h5 class="modal-title">Edit Light Cone</h5>
                            <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body row g-3">
                            <input type="hidden" name="action" value="edit">
                            <input type="hidden" name="lightConeID" value="${cone.lightConeID}">
                            <div class="col-md-6">
                                <label>Name</label>
                                <input type="text" name="lightConeName" value="${cone.lightConeName}" class="form-control" required>
                            </div>
                            <div class="col-md-6">
                                <label>Rarity</label>
                                <select name="rarityID" class="form-select" required>
                                    <c:forEach var="rarity" items="${listRarity}">
                                        <option value="${rarity.rarityID}" ${rarity.rarityID == cone.rarityID.rarityID ? 'selected' : ''}>${rarity.starName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label>Path</label>
                                <select name="pathID" class="form-select" required>
                                    <c:forEach var="path" items="${listPath}">
                                        <option value="${path.pathID}" ${path.pathID == cone.pathID.pathID ? 'selected' : ''}>${path.pathName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-6">
                                <label>Signature Character</label>
                                <select name="characterSignatureID" class="form-select">
                                    <c:forEach var="ch" items="${listCharacter}">
                                        <option value="${ch.characterID}" ${ch.characterID == cone.characterSignatureID.characterID ? 'selected' : ''}>${ch.characterName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-12">
                                <label>Description</label>
                                <textarea name="description" class="form-control" rows="3" required>${cone.description}</textarea>
                            </div>
                            <div class="col-md-12">
                                <label>Image</label>
                                <input type="file" name="lightConeImage" class="form-control" accept=".png">

                                <!-- Input ẩn để lưu ảnh cũ -->
                                <input type="hidden" name="oldImage" value="${cone.lightConeImage}" required>

                                <!-- Hiển thị đường dẫn ảnh hiện tại -->
                                <small style="color: white;">Current: ${cone.lightConeImage}</small>

                            </div>
                            <div class="col-md-6">
                                <label>Status</label>
                                <select name="status" class="form-select" required>
                                    <option value="1" ${cone.status == 1 ? 'selected' : ''}>Active</option>
                                    <option value="0" ${cone.status == 0 ? 'selected' : ''}>Inactive</option>
                                </select>
                            </div>
                            
                            <div class="row">
                                <div class="col-md" style="flex: 0 0 20.83%; max-width: 20%;">
                                    <label>Point S1</label>
                                    <input type="number" name="pointS1" id="pointS1${cone.lightConeID}" value="${empty cone.pointS1 ? 0 : cone.pointS1}" class="form-control" min="0" step="0.25" required>
                                </div>
                                <div class="col-md" style="flex: 0 0 20.83%; max-width: 20%;">
                                    <label>Point S2</label>
                                    <input type="number" name="pointS2" id="pointS2${cone.lightConeID}" value="${empty cone.pointS2 ? 0 : cone.pointS2}" class="form-control" min="0" step="0.25" required>
                                </div>
                                <div class="col-md" style="flex: 0 0 20.83%; max-width: 20%;">
                                    <label>Point S3</label>
                                    <input type="number" name="pointS3" id="pointS3${cone.lightConeID}" value="${empty cone.pointS3 ? 0 : cone.pointS3}" class="form-control" min="0" step="0.25" required>
                                </div>

                                <div class="col-md" style="flex: 0 0 20.83%; max-width: 20%;">
                                    <label>Point S4</label>
                                    <input type="number" name="pointS4" id="pointS4${cone.lightConeID}" value="${empty cone.pointS4 ? 0 : cone.pointS4}" class="form-control" min="0" step="0.25" required>
                                </div>
                                <div class="col-md" style="flex: 0 0 20.83%; max-width: 20%;">
                                    <label>Point S5</label>
                                    <input type="number" name="pointS5" id="pointS5${cone.lightConeID}" value="${empty cone.pointS5 ? 0 : cone.pointS5}" class="form-control" min="0" step="0.25" required>
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-primary">Update</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </c:forEach>
    <!-- Modal Delete Light Cone -->
    <c:forEach var="cone" items="${listLightCone}">
        <div class="modal fade" id="deleteModal${cone.lightConeID}" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content bg-dark text-white">
                    <form action="${pageContext.request.contextPath}/lightcone" method="post">
                        <div class="modal-header">
                            <h5 class="modal-title">Delete Light Cone</h5>
                            <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                        </div>
                        <div class="modal-body">
                            <input type="hidden" name="action" value="delete">
                            <input type="hidden" name="lightConeID" value="${cone.lightConeID}">
                            Are you sure you want to delete <strong>${cone.lightConeName}</strong>?
                        </div>
                        <div class="modal-footer">
                            <button type="submit" class="btn btn-danger">Yes, Delete</button>
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </c:forEach>


    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

    <script>
        document.getElementById('searchInput').addEventListener('keyup', function () {
            let input = this.value.toLowerCase();
            let rows = document.querySelectorAll('table tbody tr');

            rows.forEach(function (row) {
                let nameCell = row.cells[1]; // cột Name
                let signatureCell = row.cells[2]; // cột Signature Character

                let name = nameCell ? nameCell.textContent.toLowerCase() : '';
                let signature = signatureCell ? signatureCell.textContent.toLowerCase() : '';

                row.style.display = (name.includes(input) || signature.includes(input)) ? '' : 'none';
            });
        });
    </script>

    <script>
        function formatNumber(num) {
            if (!num || isNaN(num))
                return "0";
            return parseFloat(num).toString();
        }

        $(document).ready(function () {
            // Bắt tất cả modal có id bắt đầu bằng editModal
            $("[id^='editModal']").on("show.bs.modal", function () {
                const modal = $(this);
                const id = modal.attr("id").replace("editModal", "");

                for (let i = 1; i <= 5; i++) {
                    const input = modal.find("#pointS" + i + id);
                    if (input.length > 0) {
                        const raw = input.val();
                        input.val(formatNumber(raw));
                    }
                }
            });
        });
    </script>

    <jsp:include page="/layout/toast.jsp"/>
</html>
