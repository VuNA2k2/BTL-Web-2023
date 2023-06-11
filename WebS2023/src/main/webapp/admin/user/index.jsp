<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management</title>
    <link rel="stylesheet" href="style.css">
    <script src="https://kit.fontawesome.com/62d833ae64.js" crossorigin="anonymous"></script>

</head>
<body>
<%@include file="../../layout/header.jsp" %>
<div id="khung">
    <h1>Quản lý người dùng</h1>
    <div class="khung-loc">
        <div>
            <select id="loc-quyen">
                <option value="ALL">Tất cả</option>
                <option value="USER">User</option>
                <option value="ADMIN">Admin</option>
            </select>
            <button id="nut-loc" class="filter" onclick="applyFilter()">
                <i class="fa-solid fa-filter icon-loc"></i>
            </button>
        </div>
    </div>
    <table id="khung-nguoi-dung">
        <thead>
        <tr>
            <th>ID</th>
            <th>Tên</th>
            <th>Vai trò</th>
            <th></th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
    <button class="nut-them" id="them-nguoi-dung">Thêm</button>
</div>
<div id="khung-chua">
    <div id="thong-tin">
        <div id="nut-dong"><i class="fa-sharp fa-solid fa-xmark"></i></div>
        <h2>Tài khoản</h2>
        <form id="dien-thong-tin">
            <input type="hidden" id="id">
            <label for="username">Username:</label>
            <input type="text" id="username" required>
            <label for="password" id="pass">Password:</label>
            <input type="text" id="password" required>
            <label for="fullName">Tên:</label>
            <input type="text" id="fullName" required>
            <label for="email">Email:</label>
            <input type="email" id="email" required>
            <label for="phone">Số điện thoại:</label>
            <input type="text" id="phone" required>
            <label for="address">Địa chỉ:</label>
            <input type="text" id="address" required>
            <label for="quyen">Vai trò:</label>
            <select id="quyen" required>
                <option value="USER">USER</option>
                <option value="ADMIN">ADMIN</option>
            </select>

            <button type="submit" id="nut-luu">Lưu</button>
            <button type="button" id="nut-xoa">Xóa</button>
        </form>
    </div>
</div>
<%@include file="../../layout/footer.jsp" %>
<script src="script.js" type="module"></script>
</body>
</html>