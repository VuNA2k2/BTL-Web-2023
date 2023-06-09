<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Detail</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="../layout/header.jsp" %>
<h1>Chi tiết sản phẩm</h1>
<div class="product-container">
    <div id="productDetail">
        <div id="slideshow-container">
            <img id="slideshow-image" src="" alt="Slideshow Image">
        </div>

    </div>

</div>
<%@ include file="../list-comment/index.jsp" %>
<%@ include file="../layout/footer.jsp" %>
<script src="script.js"></script>
</body>
</html>
