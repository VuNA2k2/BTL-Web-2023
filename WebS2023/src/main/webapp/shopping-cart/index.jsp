<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Shopping Cart</title>
  <style>
    .cart-item {
      display: flex;
      justify-content: center;
      margin-bottom: 20px;
      border: 1px solid #f2f2f2;
      padding: 20px;
      width: 40%;
      margin: 0 auto;
    }

    .cart-item-image {
      width: 200px;
      margin-right: 20px;

    }
    .delete-btn {
      border: none;
      background-color: transparent;
      color: red;
      font-size: 14px;
      cursor: pointer;
      margin-top: 5px;
    }


    .cart-item-info {
      display: flex;
      flex-direction: column;
      justify-content: space-between;
    }

    .cart-item-name {
      font-weight: bold;
      font-size: 18px;
      margin-bottom: 10px;
    }

    .cart-item-price {
      font-size: 16px;
    }

    .cart-item-quantity {
      display: flex;
      align-items: center;
      margin-top: 10px;
    }

    .cart-item-quantity input {
      width: 30px;
      text-align: center;
      margin-left: 10px;
    }

    .cart-item-total {
      font-weight: bold;
      font-size: 18px;
      margin-top: 10px;
    }

    .buy-btn {
      border: none;
      width: 40%;
      margin: 20px auto;
      padding: 8px 16px;
      background-color: springgreen;
      color: white;
      cursor: pointer;
      font-size: 14px;
      font-weight: bold;
      text-align: center;
      text-decoration: none;
      display: block;
    }

    .user-history, .total {
      text-align: right;
      margin: 20px 10px 0 0;
    }
    h1
    {
      text-align: center;
    }
  </style>
</head>
<body>
<%@ include file="../layout/header.jsp" %>

<h1>Giỏ hàng</h1>

<div class="user-history">
  <a href="https://localhost/WebS2023_war/order/" class="user-history-link">Lịch sử</a>
</div>

<div id="cartItems" class="center"></div>

<div id="cartTotal" class="total"></div>

<div class="center">
  <button id="buyBtn" class="buy-btn" onclick="buyCart()">Mua</button>
</div>

<%@ include file="../layout/footer.jsp" %>

</body>
<script src="script.js"></script>
</html>
