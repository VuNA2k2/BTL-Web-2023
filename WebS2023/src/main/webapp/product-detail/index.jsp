<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Detail</title>
    <script src="https://kit.fontawesome.com/62d833ae64.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="../layout/header.jsp" %>
<div class="product-container">
    <div id="slideshow-container">
        <img id="slideshow-image" src="" alt="Slideshow Image">
    </div>
    <div class="product-data">
        <h1>Chi tiết sản phẩm</h1>
        <div id="productDetail">
        </div>
        <div class="product-details">
            <div class="product-description">
                <p><i class="fa-solid fa-box"></i> Bộ sản phẩm gồm: Hộp, Sách hướng dẫn, Cây lấy sim, Cáp
                    Lightning - Type C</p>
                <p><i class="fa-solid fa-rotate"></i> Hư gì đổi nấy 12 tháng tại 3485 siêu thị trên toàn quốc
                </p>
                <p><i class="fa-solid fa-shield"></i> Bảo hành chính hãng 1 năm</p>
                <p><i class="fa-solid fa-truck"></i> Giao hàng nhanh toàn quốc</p>
                <p><i class="fa-solid fa-phone"></i> Tổng đài: 1900.9696.42 (9h00 - 21h00 mỗi ngày)</p>
            </div>
        </div>
    </div>
</div>
<%@ include file="../list-comment/index.jsp" %>
<%@ include file="../layout/footer.jsp" %>
<script src="script.js"></script>
</body>
</html>
