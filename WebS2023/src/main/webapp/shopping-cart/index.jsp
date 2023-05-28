<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>Shopping Cart</title>
  <style>
    body {
      margin: 0;
      padding: 0;
      display: flex;
      flex-direction: column;
      min-height: 100vh;
    }

    h1 {
      text-align: center;
      margin-top: 20px;
    }

    table {
      width: 100%;
      border-collapse: collapse;
    }

    th, td {
      text-align: center;
      padding: 8px;
    }

    th {
      background-color: #f2f2f2;
    }

    .tr {
      text-align: right;
    }

    a {
      text-decoration: none;
      color: chocolate;
      font-size: 16px;
      font-weight: bold;
    }

    .total {
      text-align: right;
      margin: 20px 10px 0 0;
      font-size: 20px;
      font-weight: bold;
    }

    .center {
      text-align: center;
    }

    .buy-btn {
      width: 40%;
      border: none;
      padding: 8px 16px;
      background-color: #66cc66;
      color: white;
      cursor: pointer;
      font-size: 14px;
      font-weight: bold;
    }

    .user-history-link {
      margin-left: auto;
      margin-right: 10px;
    }

    .quantity-wrapper {
      display: flex;
      align-items: center;
      justify-content: space-between;
    }

    .quantity-btn {
      padding: 2px 6px;
      background-color: #f2f2f2;
      border: none;
      cursor: pointer;
      font-size: 14px;
      font-weight: bold;
      line-height: 1;
    }

    .delete-btn {
      padding: 2px 6px;
      background-color: #f2f2f2;
      border: none;
      cursor: pointer;
      font-size: 14px;
      font-weight: bold;
      line-height: 1;
      color: red;
    }
    .user-history
    {
      float: right;
      margin-right: 10px;
    }

    .hidden
    {
      display: none;
    }
  </style>
</head>
<body>
<!-- Header -->
<%@include file="../layout/header.jsp" %>
<h1>
  Giỏ hàng
  <span class="user-history">
        <a href="https://localhost/WebS2023_war/order/" class="user-history-link">Lịch sử</a>
    </span>
</h1>

<!-- Cart Table -->
<table id="cartTable">
  <tr>
    <th>STT</th>
    <th>Tên</th>
    <th>Số lượng</th>
    <th>Giá</th>
    <th>Tổng tiền</th>
    <th></th>
    <th class="hidden" >ProductId</th>
  </tr>
</table>

<!-- Total Money -->
<div id="totalMoney" class="total"></div>

<!-- Buy Button -->
<div class="center">
  <button id="buyBtn" class="buy-btn" onclick="buyCart()">Mua</button>
</div>


<!-- Footer -->
<%@include file="../layout/footer.jsp" %>
<script src="script.js"></script>
</body>
</html>
