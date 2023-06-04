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
            console.log(data);
            displayProductData(data.data);
        })
        .catch(function (error) {
            console.error(error);
            // Xử lý lỗi tìm nạp hoặc hiển thị thông báo lỗi
            alert('Error fetching product data. Please try again.');
        });
}

// Lấy ID sản phẩm từ URL
const urlParams = new URLSearchParams(window.location.search);
const productIdFromProduct = urlParams.get('productId');
console.log(productIdFromProduct);

function displayProductDetail(product) {
    const productDetail = document.getElementById('productDetail');

    const productItem = document.createElement('div');
    productItem.classList.add('product-item');

    const productItemImage = document.createElement('img');
    productItemImage.classList.add('product-item-image');
    productItemImage.src = product.image;
    productItem.appendChild(productItemImage);

    const productName = document.createElement('div');
    productName.classList.add('product-name');
    productName.textContent = 'Name: ' + product.name;
    productItem.appendChild(productName);

    const productDescription = document.createElement('div');
    productDescription.classList.add('product-description');
    productDescription.textContent = 'Description: ' + product.description;
    productItem.appendChild(productDescription);

    const productPrice = document.createElement('div');
    productPrice.classList.add('product-price');
    productPrice.textContent = 'Price: ' + product.price + ' đ';
    productItem.appendChild(productPrice);

    const productCategory = document.createElement('div');
    productCategory.classList.add('product-category');
    productCategory.textContent = 'Category: ' + product.category.description;
    productItem.appendChild(productCategory);

    const buyButton = document.createElement('button');
    buyButton.textContent = 'Buy';
    buyButton.classList.add('buy-button'); // Thêm lớp CSS 'buy-button' vào nút
    buyButton.addEventListener('click', function() {
        buyProduct(product.productId);
    });
    productItem.appendChild(buyButton);

    productDetail.appendChild(productItem);
}

function buyProduct(productId) {
    //Gửi yêu cầu POST đến một API để thêm sản phẩm vào giỏ hàng
    fetch('https://localhost:443/WebS2023_war/api/carts', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + getTokenFromCookie(),
        },
        body: JSON.stringify({ productId: productId }),
    })
        .then(function (response) {
            if (response.status === 201) {
                alert('Product added to cart successfully!');
            } else {
                throw new Error('Error adding product to cart');
            }
        })
        .catch(function (error) {
            console.error(error);
            // Xử lý lỗi hoặc hiển thị thông báo lỗi
            alert('Error adding product to cart. Please try again.');
        });
}


fetchData();
