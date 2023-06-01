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

        idCell.textContent = productData.id;
        nameCell.textContent = productData.name;
        priceCell.textContent = productData.price + 'đ';
        categoryCell.textContent = productData.category.description;
        discriptionCell.textContent = productData.description;
    });
}

// Mở modal thêm sản phẩm
function openAddProductModal() {
    var modal = document.getElementById("addProductModal");
    modal.style.display = "block";
}

// Đóng modal thêm sản phẩm
function closeAddProductModal() {
    var modal = document.getElementById("addProductModal");
    modal.style.display = "none";
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


// Hàm cập nhật bảng sản phẩm (thêm sản phẩm mới vào bảng)
function updateProductTable(name, price, category, description, image) {
    var table = document.getElementById("productTable");
    var newRow = table.insertRow(-1);
    var cell1 = newRow.insertCell(0);
    var cell2 = newRow.insertCell(1);
    var cell3 = newRow.insertCell(2);
    var cell4 = newRow.insertCell(3);
    var cell5 = newRow.insertCell(4);
    cell1.innerHTML = ""; // Thay bằng ID sản phẩm
    cell2.innerHTML = name;
    cell3.innerHTML = price;
    cell4.innerHTML = category;
    cell5.innerHTML = description;
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
