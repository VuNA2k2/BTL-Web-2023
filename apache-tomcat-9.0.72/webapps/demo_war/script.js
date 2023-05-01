

function callApi() {
    fetch('http://localhost:8080/demo_war/api/v1/products', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        }
    }).then(response => response.json()).then(data => {
        console.log(data);
        let output = '';
        output += JSON.stringify(data, null, 2) + "<br/>";
        document.getElementsByClassName("product-grid")[0].innerHTML = output;
    });
}

callApi();