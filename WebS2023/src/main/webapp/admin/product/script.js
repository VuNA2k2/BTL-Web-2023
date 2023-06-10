function getTokenFromCookie() {
    const cookie = document.cookie.split(';');
    const token = cookie[0].substring("token=".length, cookie[0].length);
    return token;
}

function fetchData(categoryId) {
    let url='https://localhost:443/WebS2023_war/api/products';
    if (categoryId) {
        url += '?categoryId=' +categoryId;
    }
    fetch(url, {
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
                throw new Error('Lỗi khi tìm nạp dữ liệu sản phẩm');
            }
        })
        .then(function (data) {
            console.log(data);
            displayProductData(data.data);
        })
        .catch(function (error) {
            console.error(error);
            // Xử lý lỗi tìm nạp hoặc hiển thị thông báo lỗi
            alert('Lỗi khi tìm nạp dữ liệu sản phẩm. Vui lòng thử lại.');
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
        const actionsCell = newRow.insertCell(); // Thêm ô cho các nút sửa và xóa

        idCell.textContent = productData.id;
        nameCell.textContent = productData.name;
        priceCell.textContent = productData.price + 'đ';
        categoryCell.textContent = productData.category.name;
        discriptionCell.textContent = productData.description;
        statusCell.textContent = productData.status;

        const statusSelect = document.createElement('select');
        statusSelect.id = 'statusSelect_' + productData.id;

        // khi xóa category đi rồi tạo mới 1 cái khác => categoryId bị nhảy => cần sửa lại filter
        // lúc thêm/sửa sản phẩm cần chú ý categoryId
        const statuses = ['5', '6', '7', '8'];
        statuses.forEach(function (status) {
            const option = document.createElement('option');
            option.value = status;
            option.textContent = status;
            statusSelect.appendChild(option);
        });
        statusSelect.value = productData.status;

        // Tạo nút sửa sản phẩm
        const editBtn = document.createElement('button');
        editBtn.classList.add('edit-product');
        editBtn.textContent = 'Edit';
        editBtn.addEventListener('click', () => {
            // Gọi hàm để sửa sản phẩm
            editProduct(productData);
        });
        actionsCell.appendChild(editBtn); // Thêm nút sửa vào ô action

        // Tạo nút xóa sản phẩm
        const deleteBtn = document.createElement('button');
        deleteBtn.classList.add('delete-product');
        deleteBtn.textContent = 'Delete';
        deleteBtn.addEventListener('click', () => {
            // Gọi hàm để xóa sản phẩm
            deleteProduct(productData.id); // Truyền ID sản phẩm cho hàm deleteProduct
        });
        actionsCell.appendChild(deleteBtn); // Thêm nút xóa vào ô action

    });
}

function openAddProductModal() {
    var modal = document.getElementById("addProductModal");
    modal.style.display = "block";
}

// Đóng modal thêm sản phẩm
function closeAddProductModal() {
    var modal = document.getElementById("addProductModal");
    modal.style.display = "none";
}

function addProduct() {
    // Lấy thông tin sản phẩm từ người dùng
    const name = document.getElementById('productName').value;
    const description = document.getElementById('productDescription').value;
    const price = document.getElementById('productPrice').value;
    const category = document.getElementById('productCategory').value;
    const image = document.getElementById('productImage').value;


    // Tạo đối tượng updatedProduct với các giá trị mới
    const updatedProduct = {
        name: name,
        description: description,
        price: price,
        categoryId: category,
        images: splitString(image),
    };
    console.log(updatedProduct);

    // Gửi yêu cầu POST tới API để thêm sản phẩm
    fetch('https://localhost:443/WebS2023_war/api/products', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + getTokenFromCookie(),
        },
        body: JSON.stringify(updatedProduct),
    })
        .then(function (response) {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error('Error adding product');
            }
        })
        .then(function () {
            // Sau khi thêm sản phẩm thành công, gọi hàm fetchData để cập nhật danh sách sản phẩm
            applyFilter();
            closeAddProductModal(); // Đóng modal thêm sản phẩm
        })
        .catch(function (error) {
            console.error(error);
            alert('Error adding product. Please try again.');
        });
}
function splitString(str) {
    var list = str.split(";");
    return list;
}


// Hàm mở modal sửa sản phẩm
function openEditProductModal(productId) {
    var modal = document.getElementById("editProductModal");
    modal.style.display = "block";

    // Lưu ID sản phẩm cần sửa trong thuộc tính data-product-id của modal
    modal.setAttribute('data-product-id', productId);
}


// Hàm đóng modal sửa sản phẩm
function closeEditProductModal() {
    var modal = document.getElementById("editProductModal");
    modal.style.display = "none";
}
function editProduct(productData) {
    // Hiển thị thông tin sản phẩm trong modal sửa sản phẩm
    // Đặt giá trị của các trường thông tin sản phẩm trong modal sửa sản phẩm
    document.getElementById('editProductName').value = productData.name;
    document.getElementById('editProductDescription').value = productData.description;
    document.getElementById('editProductPrice').value = productData.price;
    document.getElementById('editProductCategory').value = productData.category.id;

    let imageString = "";

    for (let i = 0; i < productData.images.length; i++) {
        imageString += productData.images[i].link;
        if(i<productData.images.length-1)
        {
            imageString += ";";
        }
    }
    document.getElementById('editProductImage').value = imageString;

    // Hiển thị modal sửa sản phẩm
    openEditProductModal(productData.id);
}

// Hàm lưu chỉnh sửa sản phẩm
function saveEditedProduct() {
    // Lấy thông tin sản phẩm từ người dùng
    const productId = document.getElementById('editProductModal').getAttribute('data-product-id');
    const name = document.getElementById('editProductName').value;
    const description = document.getElementById('editProductDescription').value;
    const price = document.getElementById('editProductPrice').value;
    const category = document.getElementById('editProductCategory').value;
    const image = document.getElementById('editProductImage').value;

    // Tạo đối tượng updatedProduct với các giá trị mới
    const updatedProduct = {
        name: name,
        description: description,
        price: price,
        categoryId: category,
        images: splitString(image),
    };

    // Gửi yêu cầu PUT tới API để cập nhật sản phẩm
    fetch(`https://localhost:443/WebS2023_war/api/products?id=${productId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + getTokenFromCookie(),
        },
        body: JSON.stringify(updatedProduct),
    })
        .then(function (response) {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error('Lỗi khi cập nhật sản phẩm');
            }
        })
        .then(function () {
            // Sau khi cập nhật sản phẩm thành công, gọi hàm fetchData để cập nhật danh sách sản phẩm
            applyFilter();
            closeEditProductModal(); // Đóng modal sửa sản phẩm
        })
        .catch(function (error) {
            console.error(error);
            alert('Lỗi khi cập nhật sản phẩm. Vui lòng th lại.');
        });
}



// Hàm xóa sản phẩm
function deleteProduct(productId) {
    // Xác nhận từ người dùng trước khi xóa sản phẩm
    const confirmDelete = confirm('Bạn chắc chắn muốn xóa sản phẩm này?');

    if (confirmDelete) {
        // Gửi yêu cầu DELETE tới API để xóa sản phẩm
        fetch(`https://localhost:443/WebS2023_war/api/products?id=${productId}`, {
            method: 'DELETE',
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
                    throw new Error('Lỗi khi xóa sản phẩm');
                }
            })
            .then(function () {
                // Sau khi xóa sản phẩm thành công, gọi hàm fetchData để cập nhật danh sách sản phẩm
                applyFilter();
            })
            .catch(function (error) {
                console.error(error);
                alert('Lỗi khi xóa sản phẩm. Vui lòng thử lại.');
            });
    }
}


// Hàm lọc sản phẩm
const filterButton = document.getElementById('filterButton');
filterButton.addEventListener('click', applyFilter);

function applyFilter() {
    const selectedStatus = document.getElementById('statusFilterSelect').value;
    fetchData(selectedStatus);
}
applyFilter();

