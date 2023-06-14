import {checkLogged} from "../routing.js";

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
    displaySlideshow(imageList);


    const productName = document.createElement('div');
    productName.classList.add('product-name');
    productName.textContent = product.name;
    productItem.appendChild(productName);

    const productDescription = document.createElement('div');
    productDescription.classList.add('product-description');
    productDescription.textContent = 'Dung lượng: ' + product.description;
    productItem.appendChild(productDescription);

    const productPrice = document.createElement('div');
    productPrice.classList.add('product-price');
    productPrice.textContent =  product.price + ' đ';
    productItem.appendChild(productPrice);

    const productCategory = document.createElement('div');
    productCategory.classList.add('product-category');
    productCategory.textContent = 'Loại: ' + product.category.name;
    productItem.appendChild(productCategory);

    const buyButton = document.createElement('button');
    buyButton.textContent = 'Thêm vào giỏ hàng';
    buyButton.classList.add('buy-button');
    buyButton.addEventListener('click', function() {
        if(checkLogged("USER")) {
            buyProduct(productIdFromProduct);
        }
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
                throw new Error('Lỗi khi thêm sản phẩm vào giỏ hàng');
            }
        })
        .then(function (data) {
            console.log(data);
            alert('Sản phẩm đã được thêm vào giỏ hàng thành công!');
        })

        .catch(function (error) {
            console.error(error);
            alert('Lỗi khi thêm sản phẩm vào giỏ hàng. Vui lòng thử lại.');
        });
}

function displaySlideshow(imageList) {
    const slideshowContainer = document.getElementById('slideshow-container');
    const slideshowImage = document.getElementById('slideshow-image');
    const linkList = imageList.map((image) => image.link);
    let currentIndex = 0;

    function changeSlide() {
        slideshowImage.src = linkList[currentIndex];
        currentIndex = (currentIndex + 1) % linkList.length;
    }

    // Bắt đầu slideshow
    changeSlide();
    setInterval(changeSlide, 2000); // Thay đổi hình ảnh sau mỗi 2 giây
}



fetchData();
