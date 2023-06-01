function getTokenFromCookie() {
    const cookie = document.cookie.split(';');
    const token = cookie[0].substring("token=".length, cookie[0].length);
    return token;
}

function fetchData() {
    fetch('https://localhost:443/WebS2023_war/api/products', {
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
                throw new Error('Error fetching product data');
            }
        })
        .then(function (data) {
            displayProductData(data.data);
        })
        .catch(function (error) {
            console.error(error);
            // Xử lý lỗi tìm nạp hoặc hiển thị thông báo lỗi
            alert('Error fetching product data. Please try again.');
        });
}

function displayProductData(data) {
    const productTable = document.getElementById('productTable');

    // Remove existing rows
    const rows = productTable.getElementsByTagName('tr');
    while (rows.length > 1) {
        productTable.deleteRow(1);
    }

    data.forEach(function (productData) {
        const newRow = productTable.insertRow();

        const idCell = newRow.insertCell();
        const nameCell = newRow.insertCell();
        const priceCell = newRow.insertCell();
        const categoryCell = newRow.insertCell();
        const discriptionCell = newRow.insertCell();
        const statusCell = newRow.insertCell();
        const updateStatusCell = newRow.insertCell();
        const deleteCell = newRow.insertCell();

        idCell.textContent = productData.id;
        nameCell.textContent = productData.name;
        priceCell.textContent = productData.price+'đ';
        categoryCell.textContent = productData.category;
        discriptionCell.textContent = productData.description;
        statusCell.textContent = productData.status;

        const statusSelect = document.createElement('select');
        statusSelect.id = 'statusSelect_' + productData.id;

        const statuses = ['1', '2', '3'];
        statuses.forEach(function (status) {
            const option = document.createElement('option');
            option.value = status;
            option.textContent = status;
            statusSelect.appendChild(option);
        });

        statusSelect.value = productData.status;

        const updateStatusBtn = document.createElement('button');
        updateStatusBtn.classList.add('update-order');
        updateStatusBtn.textContent = 'Cập nhật';
        updateStatusBtn.addEventListener('click', function () {
            const selectedStatus = statusSelect.value;
            fetch('https://localhost/WebS2023_war/api/products?id=' + productData.id, {
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

                    applyFilter();
                })
                .catch(function (error) {
                    console.error(error);
                    alert('Error updating order status. Please try again.');
                });
        });

        updateStatusCell.appendChild(statusSelect);
        updateStatusCell.appendChild(updateStatusBtn);

        // Tạo nút sửa sản phẩm
        const editBtn = document.createElement('button');
        editBtn.classList.add('edit-product');
        editBtn.textContent = 'Edit';
        editBtn.addEventListener('click', () => {
            // Gọi hàm để sửa sản phẩm
            editProduct(productData.id);
        });

        // Tạo nút xóa sản phẩm
        const deleteBtn = document.createElement('button');
        deleteBtn.classList.add('delete-product');
        deleteBtn.textContent = 'Delete';
        deleteBtn.addEventListener('click', () => {
            // Gọi hàm để xóa sản phẩm
            deleteProduct(productData.id);
        });


    });
}


// Hàm thêm sản phẩm
function addProduct() {
    // Lấy thông tin sản phẩm từ người dùng
    const name = document.getElementById('productName').value;
    const price = document.getElementById('productPrice').value;
    const category = document.getElementById('productCategory').value;
    const description = document.getElementById('productDescription').value;
    const image = document.getElementById('productImage').value;

    // Gửi yêu cầu POST tới API để thêm sản phẩm
    fetch('https://localhost/WebS2023_war/api/products', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + getTokenFromCookie(),
        },
        body: JSON.stringify({ name, price, category, description, image }),
    })
        .then(function (response) {
            if (response.status === 201) {
                return response.json();
            } else {
                throw new Error('Error adding product');
            }
        })
        .then(function () {
            // Sau khi thêm sản phẩm thành công, gọi hàm fetchData để cập nhật danh sách sản phẩm
            fetchData();
        })
        .catch(function (error) {
            console.error(error);
            alert('Error adding product. Please try again.');
        });
}

// Hàm sửa sản phẩm
function editProduct(productId) {
    // Lấy thông tin sản phẩm từ người dùng
    const newName = prompt('Enter the new name for the product:');
    const newPrice = prompt('Enter the new price for the product:');
    const newCategory = prompt('Enter the new category for the product:');
    const newDescription = prompt('Enter the new description for the product:');
    const newImage = prompt('Enter the new image URL for the product:');

    // Gửi yêu cầu PUT tới API để cập nhật sản phẩm
    fetch(`https://localhost/WebS2023_war/api/products?id=${productId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + getTokenFromCookie(),
        },
        body: JSON.stringify({ name: newName, price: newPrice, category: newCategory, description: newDescription, image: newImage }),
    })
        .then(function (response) {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error('Error updating product');
            }
        })
        .then(function () {
            // Sau khi cập nhật sản phẩm thành công, gọi hàm fetchData để cập nhật danh sách sản phẩm
            fetchData();
        })
        .catch(function (error) {
            console.error(error);
            alert('Error updating product. Please try again.');
        });
}

// Hàm xóa sản phẩm
function deleteProduct(productId) {
    // Xác nhận từ người dùng trước khi xóa sản phẩm
    const confirmDelete = confirm('Are you sure you want to delete this product?');

    if (confirmDelete) {
        // Gửi yêu cầu DELETE tới API để xóa sản phẩm
        fetch(`https://localhost/WebS2023_war/api/products?id=${productId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Accept': 'application/json',
                'Authorization': 'Bearer ' + getTokenFromCookie(),
            },
        })
            .then(function (response) {
                if (response.status === 204) {
                    return response.json();
                } else {
                    throw new Error('Error deleting product');
                }
            })
            .then(function () {
                // Sau khi xóa sản phẩm thành công, gọi hàm fetchData để cập nhật danh sách sản phẩm
                fetchData();
            })
            .catch(function (error) {
                console.error(error);
                alert('Error deleting product. Please try again.');
            });
    }
}

// Hàm lọc sản phẩm
function applyFilter() {
    const selectedCategory = document.getElementById('categoryFilterSelect').value;

    // Gửi yêu cầu GET tới API để lấy danh sách sản phẩm theo danh mục lọc
    fetch(`https://localhost/WebS2023_war/api/products?category=${selectedCategory}`, {
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
                throw new Error('Error fetching product data');
            }
        })
        .then(function (data) {
            displayProductData(data.data);
        })
        .catch(function (error) {
            console.error(error);
            alert('Error fetching product data. Please try again.');
        });
}

// Gọi hàm fetchData để hiển thị danh sách sản phẩm ban đầu
fetchData();
