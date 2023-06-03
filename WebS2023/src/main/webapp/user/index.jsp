<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Trang web của bạn</title>
    <style>
        body {
            margin: 0;
            padding: 0;
        }

        #wrapper {
            display: flex;
        }

        #sidebar {
            width: 20%;
            background-color: #f1f1f1;
            height: 70vh;
            padding: 40px 20px 0 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        #sidebar ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        #sidebar li {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }

        #sidebar i {
            margin-right: 10px;
            color: black;
            cursor: pointer;
        }

        #sidebar a {
            text-decoration: none;
            color: black;
        }

        #content {
            flex-grow: 1;
            background-color: #ffffff;
            padding: 40px 0 0 20px;
            position: relative;
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }

        .info-label {
            font-weight: bold;
            margin-bottom: 5px;
        }

        .main-label {
            font-weight: bold;
            font-size: xx-large;
            margin-bottom: 10px;
        }

        .info-value {
            margin-bottom: 10px;
        }

        .info-container {
            margin-bottom: 20px;
        }

        #logout-btn {
            position: absolute;
            bottom: 20px;
            right: 20px;
            border: none;
            background-color: red;
            color: white;
            padding: 10px;
        }

        .white {
            color: transparent;
        }

        #popup {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            align-items: center;
            justify-content: center;
        }

        #popup-content {
            position: relative;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            width: 400px;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
        }

        #popup-content h2 {
            margin-top: 0;
            margin-bottom: 20px;
            color: #333;
        }

        #popup-content label {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }

        #popup-content input[type="text"],
        #popup-content input[type="email"],
        #popup-content input[type="password"] {
            width: 100%;
            padding: 5px;
            margin-bottom: 10px;
            border: 1px solid #ddd;
            border-radius: 3px;
        }

        #popup-content button {
            margin-top: 10px;
            padding: 10px 20px;
            font-size: 16px;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        #save-button {
            background-color:green;
            display: flex;
            float: right;

        }


    </style>
    <script src="https://kit.fontawesome.com/62d833ae64.js" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="../layout/header.jsp" %>
<div id="wrapper">
    <div id="sidebar">
        <ul>
            <li>
                <i class="fas fa-cart-shopping"></i>
                <a href="https://localhost/WebS2023_war/order/">Lịch sử mua hàng</a>
            </li>
            <li>
                <i class="fas fa-pen-to-square" onclick="showPopup()"></i>
                <div >Sửa thông tin cá nhân</div>
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
