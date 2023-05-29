// Make the API request and handle the response
function getTokenFromCookie() {
    const cookie = document.cookie.split(';');
    const token = cookie[0].substring("token=".length, cookie[0].length);
    return token;
}

function fetchCartData() {
    const token = getTokenFromCookie();

    fetch('https://localhost:443/WebS2023_war/api/carts', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + token,
        }
    })
        .then(function (response) {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error('Error fetching cart data');
            }
        })
        .then(function (data) {
            console.log(data);
            displayCartData(data.data);
        })
        .catch(function (error) {
            console.error(error);
            // Handle fetch error or display error message
            alert('Error fetching cart data. Please try again.');
        });
}

function displayCartData(data) {
    const cartTable = document.getElementById("cartTable");
    const totalMoneyElement = document.getElementById("totalMoney");
    const buyBtn = document.getElementById("buyBtn");


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
                <button class="quantity-btn" onclick="updateQuantity(${item.product.id}, ${item.quantity - 1})">-</button>
                <span>${item.quantity}</span>
                <button class="quantity-btn" onclick="updateQuantity(${item.product.id}, ${item.quantity + 1})">+</button>
            </div>
        `;
        priceCell.textContent = item.product.price;
        totalMoneyCell.textContent = item.quantity * item.product.price;
        totalMoney += item.quantity * item.product.price;

        const deleteBtn = document.createElement('button');
        deleteBtn.classList.add('delete-btn');
        deleteBtn.textContent = 'Delete';
        deleteBtn.addEventListener('click', () => updateQuantity(item.product.id, 0));

        actionCell.appendChild(deleteBtn);
        productIdCell.style.display = "none";
        productIdCell.textContent = item.product.id;
    });

    // Display total money
    totalMoneyElement.textContent = "Total Money: " + totalMoney + " đ";
    // Show/hide Buy button
}

function updateQuantity(productId, quantity) {
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
            fetchCartData();
        })
        .catch(function (error) {
            console.error(error);
            // Handle fetch error or display error message
            alert('Error updating cart data. Please try again.');
        });
}

// Fetch cart data when the page loads
fetchCartData();
