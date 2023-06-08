function getTokenFromCookie() {
    const cookie = document.cookie.split(';');
    const token = cookie[0].substring("token=".length, cookie[0].length);
    return token;
}
// Lấy ID sản phẩm từ URL
const urlParams = new URLSearchParams(window.location.search);
const productIdFromProduct = urlParams.get('productId');
function fetchData() {
    fetch(`https://localhost/WebS2023_war/api/products?id=${productIdFromProduct}`, {
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
            displayProductDetail(data.data);
        })
        .catch(function (error) {
            console.error(error);
            // Xử lý lỗi tìm nạp hoặc hiển thị thông báo lỗi
            alert('Error fetching product data. Please try again.');
        });
}



function displayProductDetail(product) {
    const productDetail = document.getElementById('productDetail');

    const productItem = document.createElement('div');
    productItem.classList.add('product-item');

    const imageList = product.images;

    var currentIndex = 0;
    var slideshowImage = document.getElementById("slideshow-image");

    function startSlideshow() {
        // Hiển thị hình ảnh đầu tiên
        slideshowImage.src = imageList[currentIndex];

        // Tăng chỉ số hiện tại và kiểm tra xem có đến cuối danh sách hay không
        currentIndex++;
        if (currentIndex >= imageList.length) {
            currentIndex = 0;
        }

        // Lặp lại quá trình sau mỗi 3 giây
        setTimeout(startSlideshow, 3000);
    }

    // Bắt đầu slideshow
        startSlideshow();




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
    buyButton.classList.add('buy-button');
    buyButton.addEventListener('click', function() {
        buyProduct(productIdFromProduct);
    });
    productItem.appendChild(buyButton);

    productDetail.appendChild(productItem);
}

function buyProduct(productId) {
    console.log(productId);
    const updateData = {
        productId: productId,
        quantity: 1
    };
    fetch('https://localhost:443/WebS2023_war/api/carts', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + getTokenFromCookie(),
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
        })
        .catch(function (error) {
            console.error(error);
            alert('Error updating cart data. Please try again.');
        });
}



fetchData();
