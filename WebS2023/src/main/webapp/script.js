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
            // console.log(data);
            displayCartData(data);
        });
    } else {
        // Handle error response

    }
});

function displayCartData(data) {
    data = data.data;
    const cartTable = document.getElementById("cartTable");
    const totalMoneyElement = document.getElementById("totalMoney");
    const buyBtn = document.getElementById("buyBtn");

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
        const actionCell = row.insertCell(5);

        noCell.textContent = index + 1;
        nameCell.textContent = item.product.name;
        quantityCell.innerHTML = `
            <div class="quantity-wrapper">
                <button class="quantity-btn" onclick="decreaseQuantity(${index})">-</button>
                <span>${item.quantity}</span>
                <button class="quantity-btn" onclick="increaseQuantity(${index})">+</button>
            </div>
        `;
        priceCell.textContent = item.product.price;
        totalMoneyCell.textContent = item.quantity * item.product.price;

        totalMoney += item.quantity * item.product.price;

        const deleteBtn = document.createElement('button');
        deleteBtn.classList.add('delete-btn');
        deleteBtn.textContent = 'Delete';
        deleteBtn.addEventListener('click', () => deleteProduct(item));

        actionCell.appendChild(deleteBtn);
    });

    // Display total money
    totalMoneyElement.textContent = "Total Money: " + totalMoney + " đ";
    // Show/hide Buy button

}

function decreaseQuantity(index) {
    const item = data.products[index];
    if (item.quantity > 1) {
        item.quantity -= 1;
        updateCartData();
    }
}

function increaseQuantity(index) {
    const item = data.products[index];
    item.quantity += 1;
    updateCartData();
}

function deleteProduct(item) {
    const index = data.products.indexOf(item);
    if (index > -1) {
        data.products.splice(index, 1);
        updateCartData();
    }
}

function updateCartData() {
    // Make API request to update cart data
    // ...

    // Re-display cart data
    displayCartData(data);
}
