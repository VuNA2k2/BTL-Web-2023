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
<h1>Danh sách sản phẩm</h1>

<!-- Form for adding a new product -->
<form>
    <label for="productName">Name:</label>
    <input type="text" id="productName" required><br>

    <label for="productPrice">Price:</label>
    <input type="number" id="productPrice" required><br>

    <label for="productCategory">Category:</label>
    <input type="text" id="productCategory" required><br>

    <label for="productDescription">Description:</label>
    <textarea id="productDescription" required></textarea><br>

    <label for="productImage">Image URL:</label>
    <input type="text" id="productImage" required><br>

    <button type="button" onclick="addProduct()">Add Product</button>
</form>

<!-- Table for displaying product data -->
<table id="productTable">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Category</th>
        <th>Description</th>
        <th>Actions</th>
    </tr>
    </thead>
</table>

<!-- Select for filtering products by category -->
<label for="categoryFilterSelect">Loại:</label>
<select id="categoryFilterSelect">
    <option value="">Tất cả</option>
    <option value="11">Iphone 11</option>
    <option value="12">Iphone 12</option>
    <option value="13">Iphone 13</option>
    <option value="14">Iphone 14</option>
</select>

<!-- Button for applying the filter -->
<button id="filterButton">Apply Filter</button>
<%@ include file="../layout/footer.jsp" %>
</body>
<script src="script.js"></script>
</html>

