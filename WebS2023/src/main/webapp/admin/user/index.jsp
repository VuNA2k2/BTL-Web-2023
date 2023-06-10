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
<%@include file="../layout/header.jsp" %>
<div id="container">
    <h1>Quản lý người dùng</h1>
    <div class="filter-container">
        <div>
            <select id="userFilterSelect">
                <option value="ALL">Tất cả</option>
                <option value="USER">User</option>
                <option value="ADMIN">Admin</option>
            </select>
            <button id="filterButton" class="filter" onclick="applyFilter()">
                <i class="fa-solid fa-filter filter-icon"></i>
            </button>
        </div>
    </div>
    <table id="users-container">
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
    <button class="add-user-button" id="add-user">Thêm</button>
</div>
<div id="popup">
    <div id="popup-content">
        <h2>Tài khoản</h2>
        <form id="user-form">
            <input type="hidden" id="id">
            <label for="username">Username:</label>
            <input type="text" id="username" required>

            <label for="fullName">Tên:</label>
            <input type="text" id="fullName" required>
            <label for="email">Email:</label>
            <input type="email" id="email" required>
            <label for="phone">Số điện thoại:</label>
            <input type="text" id="phone" required>
            <label for="address">Địa chỉ:</label>
            <input type="text" id="address" required>
            <label for="role">Vai trò:</label>
            <input type="text" id="role" required>
            <button type="submit" id="save-button">Lưu</button>
            <button type="button" id="delete-button">Xóa</button>
        </form>
    </div>
</div>
<%@include file="../../layout/footer.jsp" %>
<script src="script.js" type="module"></script>
</body>
</html>