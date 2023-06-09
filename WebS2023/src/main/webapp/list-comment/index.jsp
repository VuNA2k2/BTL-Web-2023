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
<h1>Đánh giá sản phẩm</h1>
<div id="product-info">
    <p>Trung bình: <span id="avg-rates"></span><span class="star-fill"></span></p>
    <p>Số lượt đánh giá: <span id="count-rates"></span></p>
</div>
<ul id="review-list"></ul>
<%--<%@ include file="../layout/footer.jsp" %>--%>

</body>
<script src="../list-comment/script.js"></script>
</html>
