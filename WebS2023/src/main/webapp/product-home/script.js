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

function displayProductData(data) {
    const productItems = document.getElementById('productItems');

    productItems.innerHTML = '';

    data.forEach((product, index) => {
        const productItem = document.createElement('div');
        productItem.classList.add('product-item');

        const productItemImage = document.createElement('img');
        productItemImage.classList.add('product-item-image');
        productItemImage.src = product.images[0].link;
        productItem.appendChild(productItemImage);
        productItemImage.addEventListener('click', function() {
            // Chuyển hướng đến trang chi tiết sản phẩm
            const productId = product.id;
            window.location.href = 'https://localhost/WebS2023_war/product-detail/?productId=' + productId;
        });


        const productItemInfo = document.createElement('div');
        productItemInfo.classList.add('product-item-info');


        const productName = document.createElement('div');
        productName.classList.add('product-name');
        productName.textContent = product.name;
        productItemInfo.appendChild(productName);

        const productDescription = document.createElement('div');
        productDescription.classList.add('product-description');
        productDescription.textContent =  product.description;
        productItemInfo.appendChild(productDescription);

        const productPrice = document.createElement('div');
        productPrice.classList.add('product-price');
        productPrice.textContent = product.price + ' đ';
        productItemInfo.appendChild(productPrice);

        const productCategory = document.createElement('div');
        productCategory.classList.add('product-category');
        productCategory.textContent = 'Category: ' + product.category.description;
        productItemInfo.appendChild(productCategory);


        productItem.appendChild(productItemInfo);
        productItems.appendChild(productItem);
    });
}


// Hàm lọc sản phẩm

function filterProductsByCategory(category) {
    fetch(`https://localhost:443/WebS2023_war/api/products?category=${category}`, {
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
            // Filtered products are fetched successfully, display them
            displayProductData(data.data);
        })
        .catch(function (error) {
            console.error(error);
            alert('Error fetching product data. Please try again.');
        });
}


function applyFilter() {
    const selectedCategory = document.getElementById('categoryFilterSelect').value;
    filterProductsByCategory(selectedCategory);
}

fetchData();
