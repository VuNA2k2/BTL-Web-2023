<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Danh sách sản phẩm</title>
    <script src="https://kit.fontawesome.com/62d833ae64.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="../layout/header.jsp" %>
    <div id="container">
        <h1>Danh sách sản phẩm</h1>

        <div class="filter-container">
            <div>
                <select id="statusFilterSelect">
                    <option value="">Tất cả</option>
                </select>
                <button id="filterButton" class="filter">
                    <i class="fa-solid fa-filter filter-icon"></i>
                </button>
            </div>
        </div>


        <table id="productTable">
            <tr>
                <th>ID</th>
                <th>Tên</th>
                <th>Giá</th>
                <th>Loại</th>
                <th>Dung lượng</th>
                <th></th>
            </tr>
        </table>
    </div>

<!-- Modal Thêm sản phẩm -->
<button class="add-product-button" onclick="openAddProductModal()">Thêm sản phẩm</button>
<div id="addProductModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeAddProductModal()">&times;</span>
        <h2>Add Product</h2>
        <form>
            <label for="productName">Tên sản phẩm:</label>
            <input type="text" id="productName" required><br>
            <label for="productPrice">Giá:</label>
            <input type="number" id="productPrice" required><br>
            <label for="productCategory">Loại:</label>
            <input type="text" id="productCategory" required><br>
            <label for="productDescription">Dung lượng:</label>
            <textarea id="productDescription" required></textarea><br>
            <label for="productImage">Ảnh:</label>
            <input type="text" id="productImage"><br>
            <button type="button" onclick="addProduct()">Thêm</button>
        </form>
    </div>
</div>


<!-- Modal sửa sản phẩm -->
<div id="editProductModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeEditProductModal()">&times;</span>
        <!-- Các trường thông tin sản phẩm -->
        <label for="editProductName">Tên sản phẩm:</label>
        <input type="text" id="editProductName" >
        <label for="editProductDescription">Mô tả:</label>
        <input type="text" id="editProductDescription" >
        <label for="editProductPrice">Giá:</label>
        <input type="text" id="editProductPrice" >
        <label for="editProductCategory">Loại:</label>
        <input type="text" id="editProductCategory" >
        <label for="editProductImage">Ảnh:</label>
        <input type="text" id="editProductImage" >

        <!-- Nút lưu chỉnh sửa -->
        <button onclick="saveEditedProduct()">Lưu</button>
    </div>
</div>





<%@ include file="../../layout/footer.jsp" %>
<script src="script.js"></script>
</body>

</html>

