<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Danh sách đơn hàng</title>
  <style>
    /* CSS cho bảng */
    table {
      width: 90%;
      margin: 20px auto;
      border-collapse: collapse;
    }

    th, td {
      padding: 10px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }

    th {
      background-color: #f2f2f2;
    }

    /* CSS cho tiêu đề */
    h1 {
      margin-bottom: 20px;
      text-align: center;
    }

    /* CSS cho modal pop-up */
    .modal {
      display: none;
      position: fixed;
      z-index: 1;
      left: 0;
      top: 0;
      width: 100%;
      height: 100%;
      overflow: auto;
      background-color: rgba(0, 0, 0, 0.4);
    }

    .modal-content {
      background-color: #fefefe;
      margin: 15% auto;
      padding: 20px;
      border: 1px solid #888;
      width: 80%;
    }

    .close {
      color: #aaa;
      float: right;
      font-size: 28px;
      font-weight: bold;
      cursor: pointer;
    }

    .close:hover,
    .close:focus {
      color: black;
      text-decoration: none;
      cursor: pointer;
    }

    /* CSS cho nút Order Details */
    .order-details-button {
      padding: 5px 10px;
      background-color: transparent;
      border: none;
      color: black;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 12px;
      margin: 2px;
      cursor: pointer;
    }


    select
    {
      margin-right: 5px;
      padding: 3px 0;
    }

    /* CSS cho nút Update Order */
    .update-order {

      padding: 3px 16px;
      background-color: #4CAF50;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      transition: background-color 0.3s;
    }

  </style>
</head>
<body>
<%@ include file="../layout/header.jsp" %>

<h1>Danh sách đơn hàng</h1>
<table id="orderTable">
  <tr>
    <th>ID</th>
    <th>ID người dùng</th>
    <th>Ngày đặt</th>
    <th>Tổng tiền</th>
    <th>Tình trạng đơn hàng</th>
    <th></th>
    <th></th>
  </tr>
</table>

<!-- Modal -->
<div id="modal" class="modal">
  <div class="modal-content">
    <span class="close">&times;</span>
    <table id="productTable">
      <h1>Danh sách sản phẩm</h1>
      <tr>
        <th>Product ID</th>
        <th>Product Name</th>
        <th>Price</th>
        <th>Quantity</th>
      </tr>
    </table>
  </div>
</div>
>

<%@ include file="../layout/footer.jsp" %>

</body>
<script src="script.js"></script>
</html>
