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
<div id="container">
    <h1>User Management</h1>
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
            <th>Name</th>
            <th>Role</th>
            <th>Detail</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
    <button class="add-user-button" onclick="showAddUserForm()">Add User</button>
</div>
<div id="popup">
    <div id="popup-content">
        <h2>User Details</h2>
        <form id="user-form">
            <input type="hidden" id="id">
            <label for="username">Username:</label>
            <input type="text" id="username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" required>
            <label for="fullName">Full Name:</label>
            <input type="text" id="fullName" required>
            <label for="email">Email:</label>
            <input type="email" id="email" required>
            <label for="phone">Phone:</label>
            <input type="text" id="phone" required>
            <label for="address">Address:</label>
            <input type="text" id="address" required>
            <label for="role">Role:</label>
            <input type="text" id="role" required>
            <button type="submit" id="save-button">Save</button>
            <button type="button" id="delete-button">Delete</button>
        </form>
    </div>
</div>
<%@include file="../../layout/footer.jsp" %>
<script src="script.js" type="module">
</script>
</body>
</html>