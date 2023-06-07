<%--
  Created by IntelliJ IDEA.
  User: VuNAP
  Date: 6/7/2023
  Time: 11:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <title>Đăng ký</title>
    <meta charset="UTF-8">
    <title>Đăng kí</title>
    <script src="https://kit.fontawesome.com/62d833ae64.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./style.css">
</head>

<body>

<div id="register">

    <div class="tieu_de">
        <p>Đăng ký</p>
    </div>
    <div class="thong_tin">
            <div class="input_data">
                <i class="fa-solid fa-user"></i>
                <input type="text" id="username" placeholder="Tên đăng nhập">
            </div>
            <div class="input_data">
                <i class="fa-solid fa-lock"></i>
                <input type="password" id="password" placeholder="Mật khẩu">
            </div>
            <div class="input_data">
                <i class="fa-solid fa-lock"></i>
                <input type="password" id="confirmPassword" placeholder="Xác nhận mật khẩu">
            </div>
            <div class="input_data">
                <i class="fa-solid fa-user"></i>
                <input type="text" id="fullName" placeholder="Tên">
            </div>
            <div class="input_data">
                <i class="fa-solid fa-phone"></i>
                <input type="text" id="phone" placeholder="Số điện thoại">
            </div>
            <div class="input_data">
                <i class="fa-solid fa-location-dot"></i>
                <input type="text" id="address" placeholder="Địa chỉ">
            </div>
            <div class="input_data">
                <i class="fa-solid fa-envelope"></i>
                <input type="email" id="email" placeholder="Email">
            </div>
            <button id="btnRegister" class="btnRegister" onclick="register()">Đăng kí</button>
            <div class="link">
                <p>Bạn đã có tài khoản? <a href="../login">Đăng nhập</a></p>
    </div>
</div>


</body>
<script src="./script.js"></script>
</html>

