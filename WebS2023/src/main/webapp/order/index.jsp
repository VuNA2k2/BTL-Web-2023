<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Danh sách đơn hàng</title>
  <script src="https://kit.fontawesome.com/62d833ae64.js" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="../layout/header.jsp" %>

<h1>Danh sách đơn hàng</h1>

<div class="filter-container">
  <div>
    <select id="statusFilterSelect">
      <option value="">Tất cả</option>
      <option value="PENDING">PENDING</option>
      <option value="IN SHIPPING">IN SHIPPING</option>
      <option value="DONE">DONE</option>
      <option value="CANCEL">CANCEL</option>
    </select>
    <button id="filterButton" class="filter">
      <i class="fa-solid fa-filter filter-icon"></i>
    </button>
  </div>
</div>


<table id="orderTable">
  <tr>
    <th>ID</th>
    <th>Ngày đặt</th>
    <th>Tổng tiền</th>
    <th>Tình trạng đơn hàng</th>
    <th></th>
    <th></th>
  </tr>
</table>

<div id="modal" class="modal">
  <div class="modal-content">
    <span class="close"><i class="fa-solid fa-x"></i></span>
    <table id="productTable">
      <h1>Danh sách sản phẩm</h1>
    </table>
  </div>
</div>

<%@ include file="../layout/footer.jsp" %>

</body>
<script src="script.js"></script>
</html>
