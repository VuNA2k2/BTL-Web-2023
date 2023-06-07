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

<div class="filter-container">
    <div>
        <select id="categoryFilterSelect" onchange="applyFilter()">
            <option value="">All</option>
            <option value="1">Category 1</option>
            <option value="2">Category 2</option>
            <option value="3">Category 3</option>
            <option value="4">Category 4</option>
        </select>
        <button id="filterButton" class="filter">
            <i class="fa-solid fa-filter filter-icon"></i>
        </button>
    </div>
</div>


<div id="productItems"></div>


<%@ include file="../layout/footer.jsp" %>
</body>
<script src="script.js"></script>
</html>
