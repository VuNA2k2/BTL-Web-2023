<%--
  Created by IntelliJ IDEA.
  User: VuNAP
  Date: 5/21/2023
  Time: 10:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <script src="https://kit.fontawesome.com/62d833ae64.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./style.css">
</head>
<body>
<div id="login">
    <div class="title">
        <p>Đăng nhập</p>
    </div>
        <div class="input_data">
            <i class="fa-solid fa-user"></i>
            <input type="text" id="username" placeholder="Tên đăng nhập">
        </div>
        <div class="input_data">
            <i class="fa-solid fa-lock"></i>
            <input type="password" id="password" placeholder="Mật khẩu">
        </div>
        <button class="login_btn" type="submit" onclick="login()">Đăng nhập</button>
</div>
</body>
<script src="./script.js"></script>
</html>
