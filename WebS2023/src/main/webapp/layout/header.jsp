<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>JSP Page</title>
  <script src="https://kit.fontawesome.com/62d833ae64.js" crossorigin="anonymous"></script>
  <style>
    html, body {
      margin: 0;
      padding: 0;
    }

    body {
      padding-top: 70px; /* Khoảng cách giữa header và nội dung */
    }

    header {
      position: fixed;
      top: 0;
      left: 0;
      right: 0;
      background-color: black;
      height: 70px;
      display: flex;
      justify-content: center;
      align-items: center;
      z-index: 999;
    }

    .header-container {
      display: flex;
      position: relative;
      align-items: center;
      width: 100%;
      max-width: 1200px;
    }

    .header-menu {
      display: flex;
      justify-content: space-between;
      align-items: center;
    }

    .header-menu ul {
      display: inline-block;
      margin: 0;
      padding: 0;
      list-style: none;
    }

    .header-menu li {
      display: inline-block;
      padding: 23px;
      position: relative;
    }

    .header-menu li a {
      color: white;
      text-decoration: none;
      font-size: 14px;
      font-weight: 600;
      transition: color 0.3s;
    }

    .header-menu li:hover a {
      color: #FFD700; /* Change color on hover */
    }

    .header-menu li::before {
      content: "";
      position: absolute;
      bottom: 0;
      left: 50%;
      width: 0;
      height: 2px;
      background-color: #FFD700; /* Initial underline color */
      transition: width 0.3s ease-in-out;
      transform: translateX(-50%);
    }

    .header-menu li:hover::before {
      width: 100%; /* Expand underline on hover */
    }

    .search-box {
      position: absolute;
      right: 80px;
      display: flex;
      float: left;
      align-items: center;
      justify-content: center;
    }

    .search-box input {
      border: none;
      border-bottom: 2px solid white;
      padding: 5px;
      margin-right: 10px;
      font-size: 16px;
      width: 250px;
      color: black;
    }

    .search-box button {
      display: flex;
      background-color: transparent;
      border: none;
      cursor: pointer;
      outline: none;
    }

    .search-box button i {
      font-size: 20px;
    }

    .login {
      padding: 0px 0 5px;
      position: absolute;
      right: 0;
      display: block;
      float: left;
    }

    .login a {
      display: flex;
      align-items: center;
      font-size: 16px;
      text-decoration: none;
      transition: color 0.3s;
    }

    .login a i {
      font-size: 20px;
      margin-right: 5px;
      color: white;
    }

    .login a:hover {
      color: #FFD700; /* Change color on hover */
    }

    .cart {
      padding: 0px 0 5px;
      position: absolute;
      right: 35px;
      display: block;
      float: left;
    }

    .cart a {
      display: flex;
      align-items: center;
      font-size: 16px;
      text-decoration: none;
      transition: color 0.3s;
    }

    .cart a i {
      font-size: 20px;
      margin-right: 5px;
      color: white;
    }

    .cart a:hover {
      color: #FFD700; /* Change color on hover */
    }
  </style>
</head>
<body>
<header>
  <div class="header-container">
    <div class="header-menu">
      <ul>
        <li><a href="https://localhost/WebS2023_war/home">Trang chủ</a></li>
        <li><a href="#">Sản phẩm</a></li>
        <li><a href="#">iPhone</a></li>
        <li><a href="#">Liên hệ</a></li>
      </ul>
    </div>
    <div class="cart">
      <a href="https://localhost/WebS2023_war/shopping-cart"><i class="fa-solid fa-cart-shopping"></i></a>
    </div>
    <div class="login">
      <a href="https://localhost/WebS2023_war/user/"><i class="fa-solid fa-user"></i> </a>
    </div>
  </div>
</header>
</body>
</html>
