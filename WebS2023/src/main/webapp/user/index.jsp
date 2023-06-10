<%--
  Created by IntelliJ IDEA.
  User: VuNAP
  Date: 6/7/2023
  Time: 10:50 AM
  To change this template use File | Settings | File Templates.
--%>
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
<div id="wrapper">
    <div id="sidebar">
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
    <div id="content">
        <div class="label">
            <div class="main-label">Thông tin người dùng</div>
            <div class="info-label">Tên</div>
            <div class="info-label">Email</div>
            <div class="info-label">Số điện thoại</div>
            <div class="info-label">Địa chỉ</div>
        </div>
        <div class="value">
            <div class="main-label white">.</div>
            <div class="info-value" id="nameV"></div>
            <div class="info-value" id="emailV"></div>
            <div class="info-value" id="phoneNumberV"></div>
            <div class="info-value" id="addressV"></div>
        </div>
    </div>
</div>
<div id="logout-button" onclick="logOut()">
    Đăng xuất
</div>

<%@ include file="../layout/footer.jsp" %>
<div id="popup">
    <div id="popup-content">
        <h2>User Details</h2>
        <form id="user-form">
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
            <button type="submit" id="save-button">Save</button>
        </form>
    </div>
</div>
<script src="script.js"></script>
</body>
</html>
