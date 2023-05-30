<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Shopping Cart</title>
  <link rel="stylesheet" href="style.css">
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
