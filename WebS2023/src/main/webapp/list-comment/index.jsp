<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đánh giá sản phẩm</title>
    <link rel="stylesheet" type="text/css" href="../list-comment/style.css">
</head>
<body>
<%--<%@ include file="../layout/header.jsp" %>--%>
<div id="review-text" style="font-size: 30px; font-weight: bold;">Đánh giá sản phẩm</div>
<div id="product-info">
    <p>Trung bình: <span id="avg-rates"></span><span class="star-fill"></span></p>
    <p>Số lượt đánh giá: <span id="count-rates"></span></p>
</div>
<div id="filter">
    <label class="selected">
        <input type="radio" id="filterStarAll" class="filter" name="filterStar" value="" checked>
        Tất cả
    </label>
    <label>
        <input type="radio" id="filterStar5" class="filter" name="filterStar" value="5">
        5 sao
    </label>
    <label>
        <input type="radio" id="filterStar4" class="filter" name="filterStar" value="4">
        4 sao
    </label>
    <label>
        <input type="radio" id="filterStar3" class="filter" name="filterStar" value="3">
        3 sao
    </label>
    <label>
        <input type="radio" id="filterStar2" class="filter" name="filterStar" value="2">
        2 sao
    </label>
    <label>
        <input type="radio" id="filterStar1" class="filter" name="filterStar" value="1">
        1 sao
    </label>
    <label>
        <input type="radio" id="filterHasImage" class="filter" name="filterStar" value="has-image">
        Có hình ảnh
    </label>
</div>
<div id="sort">
    <select id="sortSelect" style="width: 200px; height: 40px">
        <option value="ratingDesc">Số sao giảm dần</option>
        <option value="ratingAsc">Số sao tăng dần</option>
        <option value="dateDesc">Gần đây nhất</option>
        <option value="dateAsc">Cũ nhất</option>
    </select>
</div>
<ul id="review-list"></ul>
<%--<%@ include file="../layout/footer.jsp" %>--%>

</body>
<script src="../list-comment/script.js"></script>
</html>
