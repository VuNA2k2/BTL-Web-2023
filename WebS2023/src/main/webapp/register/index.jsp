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
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng kí</title>
    <script src="https://kit.fontawesome.com/62d833ae64.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="./style.css">

</head>

<body>

<div id="register">

    <div class="tieu_de">
        <p>Đăng kí</p>
    </div>
    <div class="thong_tin">
        <form>
            <div class="input_data">
                <i class="fa-solid fa-user"></i>
                <input type="text" id="name" name="username" placeholder="Tên">
            </div>
            <div class="input_data">
                <i class="fa-solid fa-phone"></i>
                <input type="text" id="phone" name="phone" placeholder="Số điện thoại">
            </div>
            <div class="input_data">
                <i class="fa-solid fa-location-dot"></i>
                <input type="text" id="address" name="address" placeholder="Địa chỉ">
            </div>
            <div class="input_data">
                <i class="fa-solid fa-envelope"></i>
                <input type="email" id="email" name="email" placeholder="Email">
            </div>
            <div class="input_data">
                <i class="fa-solid fa-lock"></i>
                <input type="password" id="pass" name="password" placeholder="Mật khẩu">
            </div>
            <div class="input_data">
                <i class="fa-solid fa-lock"></i>
                <input type="password" id="confirm_pass" name="confirm-password" placeholder="Xác nhận mật khẩu">
            </div>

            <button class="register_btn">Đăng kí</button>
        </form>
    </div>
</div>


</body>

</html>

