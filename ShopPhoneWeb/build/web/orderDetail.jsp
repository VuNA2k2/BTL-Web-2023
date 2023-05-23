<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="model.OrderDetail" %>
<%@ page import="model.Product" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Order Details</title>

        <style>
            body {
                margin: 20px;
                
                line-height: 1.5;
            }

            h1 {
                color: #333;
                text-align: center;
            }

            .table-container {
                width: 90%;
                margin: 0 auto;
            }

            table {
                width: 100%;
                border-collapse: collapse;
            }

            th, td {
                padding: 10px;
                text-align: left;
                border-bottom: 1px solid #ddd;
            }

            th {
                background-color: #f5f5f5;
            }

            tr:hover {
                background-color: #f9f9f9;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
      
        <h1>Danh sách chi tiết đơn hàng</h1>
        <div class="table-container">
            <table>
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Price</th>
                </tr>
                <c:forEach var="orderDetail" items="${orderDetails}">
                    <tr>
                        <td>${orderDetail.pid}</td>
                        <td>
                            <c:forEach var="product" items="${productList}">
                                <c:if test="${product.id eq orderDetail.pid}">
                                    ${product.name}
                                </c:if>
                            </c:forEach>

                        </td>
                        <td>${orderDetail.quantity}</td>

                        <td><fmt:formatNumber pattern="##,###" value="${orderDetail.price}" /></td>

                    </tr>
                </c:forEach>
            </table>

        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>
