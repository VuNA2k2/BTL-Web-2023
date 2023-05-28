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

// function displayOrderData(data) {
//     const orderTable = document.getElementById('orderTable');
// // Remove existing rows
//     const rows = orderTable.getElementsByTagName('tr');
//     while (rows.length > 1) {
//         orderTable.deleteRow(1); // Start from index 1 to keep the table header
//     }
//     // Display order data
//     data.forEach(function (orderData) {
//         const newRow = orderTable.insertRow();
//
//         // Create data cells
//         const idCell = newRow.insertCell();
//         const userIdCell = newRow.insertCell();
//         const orderDateCell = newRow.insertCell();
//         const totalMoneyCell = newRow.insertCell();
//         const statusCell = newRow.insertCell();
//         const updateStatusCell = newRow.insertCell();
//
//         // Set values for data cells
//         idCell.textContent = orderData.id;
//         userIdCell.textContent = orderData.userId;
//         orderDateCell.textContent = orderData.orderDate;
//         totalMoneyCell.textContent = orderData.totalMoney;
//         statusCell.textContent = orderData.status;
//
//         // Create dropdown select for status
//         const statusSelect = document.createElement('select');
//         statusSelect.id = 'statusSelect_' + orderData.id;
//
//         // Create status options
//         const statuses = ['PENDING', 'IN SHIPPING', 'DONE', 'CANCEL'];
//         statuses.forEach(function (status) {
//             const option = document.createElement('option');
//             option.value = status;
//             option.textContent = status;
//             statusSelect.appendChild(option);
//         });
//
//         // Set initial status for select
//         statusSelect.value = orderData.status;
//
//         // Create update status button and assign click event
//         const updateStatusBtn = document.createElement('button');
//         updateStatusBtn.textContent = 'Cập nhật';
//         updateStatusBtn.addEventListener('click', function () {
//             const selectedStatus = statusSelect.value;
//
//             fetch('https://localhost/WebS2023_war/api/orders?orderId=' + orderData.id, {
//                 method: 'PUT',
//                 headers: {
//                     'Content-Type': 'application/json',
//                     'Accept': 'application/json',
//                     'Authorization': 'Bearer ' + getTokenFromCookie(),
//                 },
//                 body: JSON.stringify({ status: selectedStatus }),
//             })
//                 .then(function (response) {
//                     if (response.status === 200) {
//                         return response.json();
//                     } else {
//                         throw new Error('Error updating order status');
//                     }
//                 })
//                 .then(function (data) {
//                     fetchData();
//                 })
//                 .catch(function (error) {
//                     console.error(error);
//                     // Handle fetch error or display error message
//                     alert('Error updating order status. Please try again.');
//                 });
//         });
//
//         // Append select and button to update status cell
//         updateStatusCell.appendChild(statusSelect);
//         updateStatusCell.appendChild(updateStatusBtn);
//     });
// }
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

        // Set values for data cells
        idCell.textContent = orderData.id;
        userIdCell.textContent = orderData.userId;
        orderDateCell.textContent = orderData.orderDate;
        totalMoneyCell.textContent = orderData.totalMoney;
        statusCell.textContent = orderData.status;

        // Assign click event to ID cell
        idCell.addEventListener('click', function () {
            openModal(orderData.id);
        });

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
                .then(function (data) {
                    fetchData();
                })
                .catch(function (error) {
                    console.error(error);
                    // Handle fetch error or display error message
                    alert('Error updating order status. Please try again.');
                });
        });

        // Append select and button to update status cell
        updateStatusCell.appendChild(statusSelect);
        updateStatusCell.appendChild(updateStatusBtn);
    });
}
// Modal pop-up functions
const modal = document.getElementById('modal');
const closeBtn = document.getElementsByClassName('close')[0];

function openModal(orderId) {
    // Find the order data with the specified orderId
    const orderData = data.data.find(order => order.id === orderId);
    const products = orderData.products;

    // Populate product details in the modal
    const productDetails = document.getElementById('productDetails');
    productDetails.innerHTML = ''; // Clear previous content

    products.forEach(function (product) {
        const productName = document.createElement('p');
        productName.textContent = 'Product Name: ' + product.productName;

        const productPrice = document.createElement('p');
        productPrice.textContent = 'Price: ' + product.productPrice;

        const productQuantity = document.createElement('p');
        productQuantity.textContent = 'Quantity: ' + product.productQuantity;

        productDetails.appendChild(productName);
        productDetails.appendChild(productPrice);
        productDetails.appendChild(productQuantity);
    });

    // Display the modal
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