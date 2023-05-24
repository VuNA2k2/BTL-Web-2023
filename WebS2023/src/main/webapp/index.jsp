<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        .loader {
            border: 16px solid #f3f3f3; /* Màu viền */
            border-top: 16px solid #3498db; /* Màu viền trên */
            border-radius: 50%; /* Hình tròn */
            width: 120px; /* Kích thước */
            height: 120px; /* Kích thước */
            animation: spin 2s linear infinite; /* Tạo hiệu ứng quay */
            margin: 150px auto; /* Canh giữa */
        }

        @keyframes spin {
            0% { transform: rotate(0deg); }
            100% { transform: rotate(360deg); }
        }
    </style>
</head>
<body>
<div class="loader"></div>
<script src="script.js"></script>
<%--<script>--%>
<%--    fetch('https://localhost/WebS2023_war/api/carts?userId=1', {--%>
<%--        method: 'GET',--%>
<%--        headers: {--%>
<%--            'Content-Type': 'application/json',--%>
<%--            'Accept': 'application/json',--%>
<%--            'Authorization': 'Bearer ' + document.cookie.split('=')[1],--%>
<%--        }--%>
<%--    }).then((response) => {--%>
<%--        response.json().then((data) => {--%>
<%--            console.log(data)--%>
<%--        })--%>
<%--    })--%>
<%--</script>--%>
</body>
</html>