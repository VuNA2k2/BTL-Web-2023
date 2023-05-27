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
            displayCartData(data.data); // Truyền data.data vào hàm displayCartData
        });
    } else {
        // Handle error response
    }
});

function displayCartData(data) {
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
        const productIdCell = row.insertCell(6);
        noCell.textContent = index + 1;
        nameCell.textContent = item.product.name;
        quantityCell.innerHTML = `
    <div class="quantity-wrapper">
        <button class="quantity-btn" onclick="decreaseQuantity(${item.product.id}, ${item.quantity})">-</button>
        <span>${item.quantity}</span>
        <button class="quantity-btn" onclick="increaseQuantity(${item.product.id}, ${item.quantity})">+</button>
    </div>
`;

        priceCell.textContent = item.product.price;
        totalMoneyCell.textContent = item.quantity * item.product.price;

        totalMoney += item.quantity * item.product.price;

        const deleteBtn = document.createElement('button');
        deleteBtn.classList.add('delete-btn');
        deleteBtn.textContent = 'Delete';
        deleteBtn.addEventListener('click', () => deleteProduct(item.product.id, item.quantity));


        actionCell.appendChild(deleteBtn);
        productIdCell.style.display = "none"; // Ẩn cột "ProductId"
        productIdCell.textContent=item.product.id;
    });

    // Display total money
    totalMoneyElement.textContent = "Total Money: " + totalMoney + " đ";
    // Show/hide Buy button
}

function decreaseQuantity(productId, quantity) {

        quantity -= 1;
        updateCartData(productId, quantity);

}

function increaseQuantity(productId, quantity) {
    quantity += 1;
    updateCartData(productId, quantity);
}

function deleteProduct(productId, quantity) {
    updateCartData(productId, 0);
}


function updateCartData(productId, quantity) {
    const token = getTokenFromCookie();
    const updateData = {
        productId: productId,
        quantity: quantity
    };

    fetch(`https://localhost:443/WebS2023_war/api/carts`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + token,
        },
        body: JSON.stringify(updateData),
    })
        .then(function (response) {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error('Error updating cart data');
            }
        })
        .then(function (data) {
            console.log(data);
            displayCartData(data.data);
        })
        .catch(function (error) {
            // Handle fetch error or display error message
            console.log(error);
            // Display error message to the user
            alert('Error updating cart data. Please try again.');
        });
}

