function displayCartData(data) {
    data=data.data;
    const cartTable = document.getElementById("cartTable");
    const totalMoneyElement = document.getElementById("totalMoney");

    // Clear existing table rows
    while (cartTable.rows.length > 1) {
        cartTable.deleteRow(1);
    }

    // Display cart items
    let totalMoney = 0;
    data.products.forEach((item, index) => {
        const row = cartTable.insertRow(index + 1);
        const noCell = row.insertCell(0);
        const nameCell = row.insertCell(1);
        const quantityCell = row.insertCell(2);
        const priceCell = row.insertCell(3);
        const totalMoneyCell = row.insertCell(4);

        noCell.textContent = index + 1;
        nameCell.textContent = item.product.name;
        quantityCell.textContent = item.quantity;
        priceCell.textContent = item.product.price;
        totalMoneyCell.textContent = item.quantity * item.product.price;

        totalMoney += item.quantity * item.product.price;
    });

    // Display total money
    totalMoneyElement.textContent = "Total Money: " + totalMoney + " đ";
}

// Make the API request and handle the response
function getTokenFromCookie() {
    const cookie = document.cookie.split(';');
    const token = cookie[0].substring("token=".length, cookie[0].length);
    return token;
}

fetch('https://localhost:443/WebS2023_war/api/carts', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': 'Bearer ' + getTokenFromCookie(),
    }
}).then(function (response) {
    if (response.status === 200) {
        response.json().then(function (data) {
            console.log(data);
            displayCartData(data);
        });
    } else {
        // Handle error response
    }
});
