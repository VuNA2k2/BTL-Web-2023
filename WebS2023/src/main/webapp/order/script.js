// Make the API request and handle the response
function getTokenFromCookie() {
    const cookie = document.cookie.split(';');
    const token = cookie[0].substring("token=".length, cookie[0].length);
    return token;
}

function fetchData() {
    fetch('https://localhost:443/WebS2023_war/api/orders', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + getTokenFromCookie(),
        },
    })
        .then(function (response) {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error('Error fetching order data');
            }
        })
        .then(function (data) {
            console.log(data);
            displayOrderData(data.data);
        })
        .catch(function (error) {
            console.error(error);
            // Handle fetch error or display error message
            alert('Error fetching order data. Please try again.');
        });
}

function displayOrderData(data) {
    const orderTable = document.getElementById('orderTable');

    // Remove existing rows
    const rows = orderTable.getElementsByTagName('tr');
    while (rows.length > 1) {
        orderTable.deleteRow(1); // Start from index 1 to keep the table header
    }

    // Display order data
    data.forEach(function (orderData) {
        const newRow = orderTable.insertRow();

        // Create data cells
        const idCell = newRow.insertCell();
        const userIdCell = newRow.insertCell();
        const orderDateCell = newRow.insertCell();
        const totalMoneyCell = newRow.insertCell();
        const statusCell = newRow.insertCell();
        const updateStatusCell = newRow.insertCell();
        const orderDetailsCell = newRow.insertCell(); // New cell for order details

        // Set values for data cells
        idCell.textContent = orderData.id;
        userIdCell.textContent = orderData.userId;
        orderDateCell.textContent = orderData.orderDate;
        totalMoneyCell.textContent = orderData.totalMoney;
        statusCell.textContent = orderData.status;

        // Create dropdown select for status
        const statusSelect = document.createElement('select');
        statusSelect.id = 'statusSelect_' + orderData.id;

        // Create status options
        const statuses = ['PENDING', 'IN SHIPPING', 'DONE', 'CANCEL'];
        statuses.forEach(function (status) {
            const option = document.createElement('option');
            option.value = status;
            option.textContent = status;
            statusSelect.appendChild(option);
        });

        // Set initial status for select
        statusSelect.value = orderData.status;

        // Create update status button and assign click event
        const updateStatusBtn = document.createElement('button');
        updateStatusBtn.classList.add('update-order');
        updateStatusBtn.textContent = 'Cập nhật';
        updateStatusBtn.addEventListener('click', function () {
            const selectedStatus = statusSelect.value;

            fetch('https://localhost/WebS2023_war/api/orders?orderId=' + orderData.id, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json',
                    'Authorization': 'Bearer ' + getTokenFromCookie(),
                },
                body: JSON.stringify({ status: selectedStatus }),
            })
                .then(function (response) {
                    if (response.status === 200) {
                        return response.json();
                    } else {
                        throw new Error('Error updating order status');
                    }
                })
                .then(function () {
                    fetchData();
                })
                .catch(function (error) {
                    console.error(error);
                    // Handle fetch error or display error message
                    alert('Error updating order status. Please try again.');
                });
        });

        // Create order details button and assign click event
        const orderDetailsBtn = document.createElement('button');
        orderDetailsBtn.classList.add('order-details-button');
        orderDetailsBtn.innerHTML = '<i class="fa-solid fa-arrow-right"></i>';

        orderDetailsBtn.addEventListener('click', function () {
            openOrderDetailsModal(orderData.id, data);
        });


        // Append select and buttons to respective cells
        updateStatusCell.appendChild(statusSelect);
        updateStatusCell.appendChild(updateStatusBtn);
        orderDetailsCell.appendChild(orderDetailsBtn);

        newRow.appendChild(orderDetailsCell);
    });
}

// Modal pop-up functions
const modal = document.getElementById('modal');
const closeBtn = document.getElementsByClassName('close')[0];

function openOrderDetailsModal(orderId, data) {
    const order = data.find(function (order) {
        return order.id === orderId;
    });

    if (!order) {
        throw new Error('Order not found');
    }

    const products = order.products;
    const productTable = document.getElementById('productTable');
    productTable.innerHTML = ''; // Clear previous content

    // Create table header row
    const headerRow = productTable.insertRow();
    const productIdHeader = headerRow.insertCell();
    productIdHeader.textContent = 'Product ID';
    const productNameHeader = headerRow.insertCell();
    productNameHeader.textContent = 'Product Name';
    const priceHeader = headerRow.insertCell();
    priceHeader.textContent = 'Price';
    const quantityHeader = headerRow.insertCell();
    quantityHeader.textContent = 'Quantity';

    // Create table rows for each product
    products.forEach(function (product) {
        const newRow = productTable.insertRow();
        const productIdCell = newRow.insertCell();
        productIdCell.textContent = product.productId;
        const productNameCell = newRow.insertCell();
        productNameCell.textContent = product.productName;
        const priceCell = newRow.insertCell();
        priceCell.textContent = product.productPrice;
        const quantityCell = newRow.insertCell();
        quantityCell.textContent = product.productQuantity;
    });

    modal.style.display = 'block';
}


closeBtn.onclick = function () {
    closeModal();
};

function closeModal() {
    modal.style.display = 'none';
}

window.onclick = function (event) {
    if (event.target === modal) {
        closeModal();
    }
};

// Call fetchData to get initial order data
fetchData();
