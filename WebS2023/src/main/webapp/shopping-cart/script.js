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
            alert('Error fetching cart data. Please try again.');
        });
}

function displayCartData(data) {
    const cartItems = document.getElementById('cartItems');
    const cartTotal = document.getElementById('cartTotal');
    const buyBtn = document.getElementById('buyBtn');

    cartItems.innerHTML = '';

    let totalMoney = 0;
    data.products.forEach((item, index) => {
        const cartItem = document.createElement('div');
        cartItem.classList.add('cart-item');

        const cartItemImage = document.createElement('img');
        cartItemImage.classList.add('cart-item-image');
        cartItemImage.src = item.product.images[0].link;
        cartItem.appendChild(cartItemImage);

        const cartItemInfo = document.createElement('div');
        cartItemInfo.classList.add('cart-item-info');

        const cartItemName = document.createElement('div');
        cartItemName.classList.add('cart-item-name');
        cartItemName.textContent = item.product.name;
        cartItemInfo.appendChild(cartItemName);

        const cartItemPrice = document.createElement('div');
        cartItemPrice.classList.add('cart-item-price');
        cartItemPrice.textContent = 'Giá: ' + item.product.price + ' đ';
        cartItemInfo.appendChild(cartItemPrice);

        const cartItemQuantity = document.createElement('div');
        cartItemQuantity.classList.add('cart-item-quantity');

        const quantityInput = document.createElement('input');
        quantityInput.type = 'number';
        quantityInput.min = 0;
        quantityInput.value = item.quantity;
        quantityInput.addEventListener('change', (event) => {
            updateQuantity(item.product.id, parseInt(event.target.value));
        });

        cartItemQuantity.appendChild(quantityInput);
        cartItemInfo.appendChild(cartItemQuantity);

        const cartItemTotal = document.createElement('div');
        cartItemTotal.classList.add('cart-item-total');
        cartItemTotal.textContent = 'Tổng: ' + item.quantity * item.product.price + ' đ';
        cartItemInfo.appendChild(cartItemTotal);

        cartItem.appendChild(cartItemInfo);
        cartItems.appendChild(cartItem);

        const deleteBtn = document.createElement('button');
        deleteBtn.classList.add('delete-btn');
        deleteBtn.textContent = 'Xóa';
        deleteBtn.addEventListener('click', () => {
            updateQuantity(item.product.id, 0);
        });
        cartItemInfo .appendChild(deleteBtn);

        totalMoney += item.quantity * item.product.price;
    });

    cartTotal.textContent = 'Tổng số tiền: ' + totalMoney + ' đ';

    const buyButton = document.getElementById('buyBtn');
    buyButton.style.display = totalMoney === 0 ? 'none' : 'block';

}

function updateQuantity(productId, quantity) {
    const token = getTokenFromCookie();
    const updateData = {
        productId: productId,
        quantity: quantity
    };
    fetch('https://localhost:443/WebS2023_war/api/carts', {
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
            alert('Error updating cart data. Please try again.');
        });
}

function buyCart() {
    const token = getTokenFromCookie();

    fetch('https://localhost:443/WebS2023_war/api/orders', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + token,
        },
    })
        .then(function (response) {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error('Error buying cart');
            }
        })
        .then(function (data) {
            console.log(data);
            fetchCartData();
        })
        .catch(function (error) {
            console.error(error);
            alert('Error buying cart. Please try again.');
        });
}

fetchCartData();
