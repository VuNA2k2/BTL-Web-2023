<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <script src="https://kit.fontawesome.com/62d833ae64.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<%@ include file="../layout/header.jsp" %>
<h1>Danh sách sản phẩm</h1>

<label for="categoryFilterSelect">Loại:</label>
<select id="categoryFilterSelect">
    <option value="">Tất cả</option>
    <option value="11">Iphone 11</option>
    <option value="12">Iphone 12</option>
    <option value="13">Iphone 13</option>
    <option value="14">Iphone 14</option>
</select>


<%@ include file="../layout/footer.jsp" %>
</body>
<script src="script.js"></script>
</html>
