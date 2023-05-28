<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Management</title>
    <style>
        .user-card {
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 10px;
            cursor: pointer;
        }

        #popup {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.5);
            align-items: center;
            justify-content: center;
        }

        #popup-content {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background-color: white;
            padding: 20px;
            border-radius: 5px;
            width: 400px;
        }

        #popup-content h2 {
            margin-top: 0;
        }

        #popup-content label {
            display: block;
            margin-bottom: 5px;
        }

        #popup-content input[type="text"],
        #popup-content input[type="email"],
        #popup-content input[type="password"] {
            width: 100%;
            padding: 5px;
            margin-bottom: 10px;
        }

        #popup-content button {
            margin-top: 10px;
        }
    </style>
</head>
<body>
<h1>User Management</h1>
<div id="users-container"></div>

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
            <button type="submit">Save</button>
            <button type="button" id="delete-button">Delete</button>
        </form>
    </div>
</div>

<button onclick="showAddUserForm()">Add User</button>
<script src="script.js"></script>
</body>
</html>
