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
  </style>
</head>
<body>
<%@ include file="../layout/header.jsp" %>

<h1>Danh sách đơn hàng</h1>
<table id="orderTable">
  <tr>
    <th>ID</th>
    <th>User ID</th>
    <th>Ngày đặt hàng</th>
    <th>Tổng số tiền</th>
    <th>Trạng thái</th>
    <th>Cập nhật trạng thái</th>
  </tr>
</table>

<%@ include file="../layout/footer.jsp" %>

<!-- Modal pop-up -->
<div id="modal" class="modal">
  <div class="modal-content">
    <span class="close">&times;</span>
    <h2>Thông tin chi tiết sản phẩm</h2>
    <div id="productDetails"></div>
  </div>
</div>

</body>
<script src="script.js"></script>
</html>
