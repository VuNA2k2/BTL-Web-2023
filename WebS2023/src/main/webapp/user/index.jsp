<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Trang web của bạn</title>
    <link rel="stylesheet" href="style.css">
    <script src="https://kit.fontawesome.com/62d833ae64.js" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="../layout/header.jsp" %>
<div id="bao-chua">
    <div id="thanh-ben">
        <ul>
            <li>
                <a href="https://localhost/WebS2023_war/order/"><i class="fas fa-cart-shopping"></i></a>
                <a href="https://localhost/WebS2023_war/order/">Lịch sử mua hàng</a>
            </li>
            <li>
                <i class="fas fa-pen-to-square" onclick="showPopup()"></i>
                <div onclick="showPopup()">Sửa thông tin cá nhân</div>
            </li>
        </ul>
    </div>
    <div id="thong-tin">
        <div class="nhan">
            <div class="nhan-chinh">Thông tin người dùng</div>
            <div class="nhan-thong-tin">Tên</div>
            <div class="nhan-thong-tin">Email</div>
            <div class="nhan-thong-tin">Số điện thoại</div>
            <div class="nhan-thong-tin">Địa chỉ</div>
        </div>
        <div class="du-lieu">
            <div class="nhan-chinh trong-suot">.</div>
            <div class="du-lieu-nguoi-dung" id="ten"></div>
            <div class="du-lieu-nguoi-dung" id="hom-thu"></div>
            <div class="du-lieu-nguoi-dung" id="so-dien-thoai"></div>
            <div class="du-lieu-nguoi-dung" id="dia-chi"></div>
        </div>
    </div>
</div>
<div id="nut-thoat" onclick="logOut()">
    Đăng xuất
</div>

<%@ include file="../layout/footer.jsp" %>
<div id="khung-chua">
    <div id="khung-thong-tin">
        <h2>User Details</h2>
        <form id="dien-thong-tin">
            <input type="hidden" id="id">
            <label for="username">Username:</label>
            <input type="text" id="username" required>
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" required>
            <label for="email">Email:</label>
            <input type="email" id="email" required>
            <label for="phone">Phone:</label>
            <input type="text" id="phone" required>
            <label for="address">Address:</label>
            <input type="text" id="address" required>
            <input type="hidden" id="role" required>
            <button type="submit" id="nut-luu">Save</button>
        </form>
    </div>
</div>
<script src="script.js"></script>
</body>
</html>