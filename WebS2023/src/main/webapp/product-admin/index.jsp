<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="../layout/header.jsp" %>
<h1>Product Management</h1>



<h2>Add Product</h2>
<form>
    <label for="productName">Name:</label>
    <input type="text" id="productName" required>
    <label for="productPrice">Price:</label>
    <input type="number" id="productPrice" required>
    <label for="productCategory">Category:</label>
    <input type="text" id="productCategory" required>
    <label for="productDescription">Description:</label>
    <input type="text" id="productDescription" required>
    <label for="productImage">Image URL:</label>
    <input type="text" id="productImage" required>
    <button type="button" onclick="addProduct()">Thêm sản phẩm</button>
</form>


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

<table id="productTable">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Category</th>
        <th>Description</th>
    </tr>
    </thead>
</table>

<%@ include file="../layout/footer.jsp" %>
<script src="script.js"></script>
</body>

</html>

