<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="model.Order" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Danh sách đơn hàng</title>
        <style>
            /* Styles for the table */
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

            /* Styles for the form */
            form {
                display: inline-block;
                margin-bottom: 10px;
            }

            label {
                font-weight: bold;
            }

            select {
                margin-right: 10px;
            }

            input[type="submit"] {
                padding: 8px 16px;
                background-color: #4CAF50;
                color: white;
                border: none;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }

            /* Styles for headings */
            h1 {
                margin-bottom: 20px;
                text-align: center;
            }

            /* Styles for links */
            td a {
                color: #007bff;
                text-decoration: none;
            }

            td a:hover {
                text-decoration: underline;
            }

        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>

        <h1>Danh sách đơn hàng</h1>
        <table>
            <tr>
                <th>ID</th>
                <th>Customer ID</th>
                <th>Total Money</th>
                <th>Status</th>


            </tr>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td><a href="orderDetail?id=${order.id}">${order.id}</a></td>
                    <td>${order.cusid}</td>
                    <td><fmt:formatNumber pattern="##,###" value="${order.totalmoney}" /></td>
                    <td>${order.status}</td>


                </tr>
            </c:forEach>
        </table>
        <%@include file="footer.jsp" %>
    </body>
    <script>
        function submitForm() {
            document.getElementById('orderForm').submit();
        }
    </script>
</html>
