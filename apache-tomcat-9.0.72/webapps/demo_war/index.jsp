<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <style>
        .product-grid {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            grid-gap: 20px;
            justify-content: start;
        }

        .product-item {
            padding: 20px;
            grid-column: span 1;
            display: inline-block;
        }
    </style>
</head>
<body>
<h1><%= "HOME" %>
</h1>
<br/>
<div id="product-grid"></div>
</body>
<script>
    function callApi() {
        fetch('http://localhost:8080/demo_war/api/v1/products', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            }
        }).then(response => response.json()).then(data => {
            console.log(data);
            var products = data;

            // Tạo HTML cho grid sản phẩm
            var html = "";
            for (var i = 0; i < products.length; i++) {
                html += '<div class="product-item">';
                html += '<img src="' + products[i].linkImage + '" alt="' + products[i].name + '" width="200" height="200">';
                html += '<h3>' + products[i].name + '</h3>';
                html += '<p>' + products[i].description + '</p>';
                html += '<button onclick="navigateTo('+products[i].id+')">Add to Cart</button>';
                html += '</div>';
            }

            // Thiết lập HTML mới cho grid sản phẩm
            document.getElementById("product-grid").innerHTML = html;
        });
    }

    function navigateTo(id) {
        window.location.href = "http://localhost:8080/demo_war/cart_order/index.jsp?id=" + id;
    }

    callApi();
</script>
</html>