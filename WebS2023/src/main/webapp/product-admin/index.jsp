<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <script src="https://kit.fontawesome.com/62d833ae64.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="../layout/header.jsp" %>
<h1>Product Management</h1>

<div class="filter-container">
    <div>
        <select id="statusFilterSelect">
            <option value="">Tất cả</option>
            <option value="1">category 1</option>
            <option value="2">category 2</option>
            <option value="3">category 3</option>
            <option value="4">category 4</option>
        </select>
        <button id="filterButton" class="filter">
            <i class="fa-solid fa-filter filter-icon"></i>
        </button>
    </div>
</div>

<button onclick="openAddProductModal()">Add Product</button>

<table id="productTable">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Category</th>
        <th>Description</th>
    </tr>
</table>

<!-- Modal for adding product -->
<div id="addProductModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="closeAddProductModal()">&times;</span>
        <h2>Add Product</h2>
        <form>
            <label for="productName">Name:</label>
            <input type="text" id="productName" required><br>
            <label for="productPrice">Price:</label>
            <input type="number" id="productPrice" required><br>
            <label for="productCategory">Category:</label>
            <input type="text" id="productCategory" required><br>
            <label for="productDescription">Description:</label>
            <textarea id="productDescription" required></textarea><br>
            <label for="productImage">Image:</label>
            <input type="file" id="productImage"><br>
            <button type="button" onclick="addProduct()">Add</button>
        </form>
    </div>
</div>


<%@ include file="../layout/footer.jsp" %>
<script src="script.js"></script>
</body>

</html>

