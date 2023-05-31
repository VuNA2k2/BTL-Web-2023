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

<div id="productContainer"></div>


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
    <button type="button" onclick="addProduct()">Add Product</button>
</form>


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

<h2>Filter Products</h2>
<select id="categoryFilterSelect">
    <option value="">All Categories</option>
    <option value="1">Category 1</option>
    <option value="2">Category 2</option>
    <option value="3">Category 3</option>
</select>
<button type="button" onclick="applyFilter()">Apply Filter</button>

<%@ include file="../layout/footer.jsp" %>
<script src="script.js"></script>
</body>

</html>

