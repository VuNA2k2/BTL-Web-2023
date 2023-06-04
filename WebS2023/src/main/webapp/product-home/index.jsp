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
<h1>Danh sách sản phẩm</h1>

<div>
    <label for="categoryFilterSelect">Filter by Category:</label>
    <select id="categoryFilterSelect">
        <option value="">All</option>
        <option value="1">Category 1</option>
        <option value="2">Category 2</option>
        <option value="3">Category 3</option>
    </select>
    <button onclick="applyFilter()">Apply Filter</button>
</div>

<div id="productItems"></div>


<%@ include file="../layout/footer.jsp" %>
</body>
<script src="script.js"></script>
</html>
