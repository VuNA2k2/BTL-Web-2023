<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<div id="data"></div>
<a href="hello-servlet">Hello Servlet</a>
</body>
<script>
    function callApi() {
        fetch('http://localhost:8080/WebS2023_war/products', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
            }
        }).then(response => response.json()).then(data => {
            console.log(data);
            let output = '';
            for (let i = 0; i < data.data.length; i++)
                output += JSON.stringify(data.data[i], null, 2) + "<br/>";
            document.getElementById("data").innerHTML = output;
        });
    }

    callApi();
</script>
</html>