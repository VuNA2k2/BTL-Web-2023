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

<%--     khi xóa category đi rồi tạo mới 1 cái khác => categoryId bị nhảy => cần sửa lại filter--%>
<%--     lúc thêm/sửa sản phẩm cần chú ý categoryId--%>
    <div class="filter-container">
        <div>
            <select id="statusFilterSelect">
                <option value="">Tất cả</option>
                <option value="5">iphone 11</option>
                <option value="6">iphone 12</option>
                <option value="7">iphone 13</option>
                <option value="8">iphone 14</option>
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