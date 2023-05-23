<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

        <%@include file="header.jsp" %>

        <h1>Shopping Cart Online</h1>
        <table>
            <tr>
                <th>No</th>
                <th>Name</th>
                <th>Quantity</th>
                <th>Price</th>
                <th>Total Money</th>
                <th>Action</th>
            </tr>
            <c:set var="items" value="${requestScope.items}" />
            <c:set var="counter" value="0" />
            <c:forEach items="${items}" var="item">
                <c:set var="counter" value="${counter + 1}" />
                <tr>
                    <td>${counter}</td>
                    <td>${item.pname}</td>
                    <td>
                        <c:if test="${item.quantity > 1}">
                            <button class="btn btn-circle"><a href="process?action=remove&id=${item.pid}">-</a></button>
                        </c:if>
                        ${item.quantity}

                        <button class="btn btn-circle"><a href="process?action=add&id=${item.pid}">+</a></button>

                    </td>
                    <td class="tr"><fmt:formatNumber pattern="##,###" value="${item.price}" /></td>
                    <td class="tr"><fmt:formatNumber pattern="##,###" value="${item.price * item.quantity}" /></td>
                    <td>
                        <form action="process" method="post">
                            <input type="hidden" name="id" value="${item.pid}" />
                            <button class="btn btn-circle"><a href="process?action=return&id=${item.pid}">Xóa</a></button>
                        </form>
                    </td>
                </tr>
            </c:forEach>

        </table>
        <div class="total">Total Money: <fmt:formatNumber pattern="##,###" value="${cart.totalMoney}" /> đ</div>

        <hr/>
        <c:if test="${not empty items}">
            <form action="checkout" method="post" class="center">
                <input type="submit" class="btn checkout-btn buy-btn" value="Buy" />
            </form>
        </c:if>
        <form action="logout" method="get">
            <input type="submit" class="btn logout-btn" value="Log Out" />
        </form>
        <hr/>
        <button class="continue-shopping-btn" onclick="window.location.href = 'shop'">Back</button>
        <a href="userhistory" class="user-history-link">Lịch sử mua hàng</a>

        <%@include file="footer.jsp" %>
    </body>
</html>
