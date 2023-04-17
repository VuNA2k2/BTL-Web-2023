<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>API Call from JSP</title>
    <script>
        function callApi() {
            fetch('/WebS2023_war/users?id=1', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                }
            }).then(response => response.json()).then(data => {
                document.getElementById('apiResponse').innerHTML = JSON.stringify(data, null, 2);
            });
        }
    </script>
</head>
<body>
<h1>API Call from JSP</h1>
<button onclick="callApi()">Call API</button>
<pre id="apiResponse"></pre>
</body>
</html>
