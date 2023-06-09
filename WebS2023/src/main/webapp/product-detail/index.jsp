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

    <div class="product-details">
        <div class="product-promotion">
            <div class="list">
                <h3>Khuyến mãi</h3>
                <ul>
                    <li>Thu cũ Đổi mới: Giảm đến 2 triệu (Tùy model máy cũ, không kèm các hình thức thanh
                        toán online, mua kèm)</li>
                    <li>Mua kèm iPhone ưu đãi thêm (Tuỳ model và không kèm khuyến mãi khác của sản phẩm mua
                        kèm):
                        <ul>
                            <li>Phụ kiện Apple: Giảm đến 50%.</li>
                            <li>Apple Watch: Giảm đến 27%.</li>
                            <li>iPad: Giảm đến 50%.</li>
                        </ul>
                    </li>
                    <li>Nhập mã MM30THANG5 giảm tối đa 100.000đ khi thanh toán qua MOMO </li>
                    <li>Giảm 100,000đ Cho Đơn Hàng Từ 10 Triệu Khi Thanh Toán Quét Mã QR Bằng Ứng Dụng Ngân
                        Hàng </li>
                    <li>Giảm 15% gói Bảo hiểm rơi vỡ </li>
                </ul>
            </div>
        </div>

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
<%@ include file="../layout/footer.jsp" %>
<script src="script.js"></script>
</body>
</html>
