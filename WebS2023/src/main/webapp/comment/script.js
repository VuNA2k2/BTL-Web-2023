// Lấy token từ cookie
function getTokenFromCookie() {
    const cookie = document.cookie.split(';');
    const token = cookie[0].substring("token=".length, cookie[0].length);
    return token;
}

// Lấy id product
function getProductIdFromURL() {
    const urlParams = new URLSearchParams(window.location.search);
    const productId = urlParams.get('productId');
    return productId;
}

// Lấy id product in order
function getProductInOrderIdFromURL() {
    const urlParams = new URLSearchParams(window.location.search);
    const productInOrderId = urlParams.get('productInOrder');
    return productInOrderId;
}

// Lấy đánh giá sản phẩm theo productId
function getProductReviews() {
    const productId = getProductIdFromURL();
    console.log(productId);
    const headers = {
        'Content-Type': 'application/json', 'Accept': 'application/json',
    };

    fetch(`https://localhost:443/WebS2023_war/api/rates/avg?productId=${productId}`, {
        headers: headers,
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === 'success') {
                console.log('Lấy thành công');
                const avgRates = data.data.avgRate;
                const countRates = data.data.countRate;
                displayProductInfo(avgRates, countRates);
            } else {
                console.log('Lỗi lấy đánh giá sản phẩm');
            }
        })
        .catch(error => {
            console.log('Error connect to API: ', error);
        });

    fetch(`https://localhost:443/WebS2023_war/api/rates?productId=${productId}`, {
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json', // 'Authorization': 'Bearer ' + getTokenFromCookie(),
        }
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === 'success') {
                const reviews = data.data;
                displayProductReviews(reviews)
            } else {
                console.log('Lỗi lấy đánh giá sản phẩm');
            }
        })
        .catch(error => {
            console.log('Error connect to API: ', error);
        });
}

// Hiển thị thông tin sản phẩm
function displayProductInfo(avgRates, countRates) {
    var avgRatesText = avgRates.toFixed(1) + "/5.0 ";
    document.getElementById('avg-rates').textContent = avgRatesText;
    document.getElementById('count-rates').textContent = countRates;
}

// Hiển thị danh sách đánh giá sản phẩm
function displayProductReviews(reviews) {
    const reviewList = document.getElementById('review-list');
    reviewList.innerHTML = '';

    reviews.forEach(review => {
        const reviewItem = document.createElement('li');
        reviewItem.className = 'review-item';
        reviewItem.innerHTML = `
            <p>Bình luận: ${review.comment}</p>
            <p>Đánh giá: ${review.star} <span class="star-fill"></span></p>
            <p style="font-size: 13px">${review.createdAt}</p>
        `;
        reviewList.appendChild(reviewItem);
    });
}

// Gửi đánh giá lên api
function submitReview() {
    const productInOrderId = getProductInOrderIdFromURL();

    const comment = document.getElementById('comment').value;
    const ratingInputs = document.querySelectorAll('.rating input[type="radio"]');
    let star = 0;

    ratingInputs.forEach(input => {
        if (input.checked) {
            star = parseInt(input.value);
        }
    });

    if (!comment || star === 0) {
        console.log('Vui lòng nhập đủ thông tin!');
        alert('Vui lòng nhập đủ thông tin!');
        return;
    }

    const token = getTokenFromCookie();
    const headers = {
        'Content-Type': 'application/json', 'Accept': 'application/json', 'Authorization': 'Bearer ' + token,
    };

    console.log(productInOrderId);
    const data = {
        comment: comment, star: star, productInOrderId: productInOrderId, images: [],
    };

    fetch(`https://localhost:443/WebS2023_war/api/rates`, {
        method: 'POST', headers: headers, body: JSON.stringify(data)
    })
        .then(response => {
            return response.json();
        })
        .then(data => {
            console.log(data);
            if (data.code === 'success') {
                console.log("Gửi thành công!");
                alert("Gửi thành công!");
            } else {
                console.log('Lỗi gửi đánh giá');
                alert("Lỗi gửi đánh giá!");
            }
        })
        .catch(error => {
            console.log('Lỗi kết nối API: ', error);
        });
}

document.getElementById("btnSubmit").onclick = submitReview;

// Khi tải xong trang, lấy danh sách đánh giá cho sản phẩm
window.onload = function () {
    getProductReviews();
}
