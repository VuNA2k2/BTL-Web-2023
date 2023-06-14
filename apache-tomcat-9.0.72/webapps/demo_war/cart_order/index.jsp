<%--
  Created by IntelliJ IDEA.
  User: VuNAP
  Date: 4/25/2023
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<h1>Cart</h1>
<h2 id="id">Bạn đã mua thành công sản phẩm có id</h2>
<div id="data">

</div>
</body>
<script>
    var urlParams = new URLSearchParams(window.location.search);
    var id = urlParams.get('id');
    document.getElementById("id").innerHTML = "Bạn đã mua thành công sản phẩm có id: " + id;
    fetch("http://localhost:8080/demo_war/api/v1/products?id=" + id, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
        },

    })
        .then(response => response.json())
        .then(data => {
            product = data;
            html = "";
            html += '<div style="text-align: center">';
            html += '<img src="' + product.linkImage + '" alt="' + product.name + '" width="200" height="200">';
            html += '<h3>' + product.name + '</h3>';
            html += '<p>' + product.description + '</p>';
            html += '</div>';
            document.getElementById("data").innerHTML = html;
        });
</script>
</html>
