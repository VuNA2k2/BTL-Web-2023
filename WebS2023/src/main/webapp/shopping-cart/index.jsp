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

<div id="gio-hang"></div>

<div id="tong-tien"></div>

<div>
  <button id="nut-mua" onclick="buyCart()">Mua</button>
</div>

<%@ include file="../layout/footer.jsp" %>

</body>
<script src="script.js"></script>
</html>