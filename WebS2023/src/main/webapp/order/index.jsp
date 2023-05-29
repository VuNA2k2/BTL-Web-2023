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
</body>
<script src="script.js"></script>


</html>
