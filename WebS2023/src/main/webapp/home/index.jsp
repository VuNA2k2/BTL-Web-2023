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
<div id="container">
    <h1>Danh sách sản phẩm</h1>

    <div class="filter-container">
        <div>
            <select id="statusFilterSelect">
                <option value="">Tất cả</option>
                <option value="1">iphone 11</option>
                <option value="2">iphone 12</option>
                <option value="3">iphone 13</option>
                <option value="4">iphone 14</option>
            </select>
            <button id="filterButton" class="filter">
                <i class="fa-solid fa-filter filter-icon"></i>
            </button>
        </div>
    </div>
</div>

<div id="productItems"></div>


<%@ include file="../layout/footer.jsp" %>
</body>
<script src="script.js"></script>
</html>