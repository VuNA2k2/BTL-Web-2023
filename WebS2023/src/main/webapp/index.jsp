<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Shopping Cart</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            min-height: 100vh;
        }

        h1 {
            text-align: center;
            margin-top: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            text-align: center;
            padding: 8px;
        }

        th {
            background-color: #f2f2f2;
        }

        .tr {
            text-align: right;
        }

        a {
            text-decoration: none;
            color: chocolate;
            font-size: 16px;
            font-weight: bold;
        }

        .total {
            text-align: right;
            margin: 20px 10px 0 0;
            font-size: 20px;
            font-weight: bold;
        }

        .btn {
            padding: 2px 6px;
            background-color: #f2f2f2;
            border: none;
            cursor: pointer;
            font-size: 14px;
            font-weight: bold;
            line-height: 1;
        }



        .checkout-btn {
            background-color: #66cc66;
            color: white;
            border: none;
            padding: 8px 16px;
            cursor: pointer;
            font-size: 14px;
            font-weight: bold;
        }

        .logout-btn {
            background-color: #cccccc;
            border: none;
            padding: 8px 16px;
            cursor: pointer;
            font-size: 14px;
            font-weight: bold;
        }

        .center {
            text-align: center;
        }

        .buy-btn {
            width: 40%;
            border: none;
            padding: 8px 16px;
            background-color: #66cc66;
            color: white;
            cursor: pointer;
            font-size: 14px;
            font-weight: bold;
        }

        .continue-shopping-btn {
            position: fixed;
            top: 10px;
            left: 10px;
            padding: 10px;
            background-color: #f2f2f2;
            border: none;
            cursor: pointer;
        }

        .user-history-link {
            margin-left: auto;
            margin-right: 10px;
        }

    </style>
</head>
<body>
<!-- Header -->

<h1>Shopping Cart Online</h1>
<table id="cartTable">
    <tr>
        <th>No</th>
        <th>Name</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Total Money</th>
    </tr>
</table>
<div id="totalMoney" class="total"></div>

<!-- Footer -->

<script src="script.js"></script>
</body>
</html>
