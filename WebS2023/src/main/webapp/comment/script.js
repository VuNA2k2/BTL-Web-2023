// Lấy token từ cookie
function getTokenFromCookie() {
    const cookie = document.cookie.split(';');
    const token = cookie[0].substring("token=".length, cookie[0].length);
    return token;
}

// Lấy đánh giá sản phẩm theo productId
function getProductReviews(productId) {
    const url = `https://localhost:443/WebS2023_war/api/rates/avg?productId=${productId}`;

    const token = getTokenFromCookie();
    const headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        // 'Authorization': `Bearer ${token}`,
    };

    fetch(url, {
        headers: headers,
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === 'success') {
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
            'Accept': 'application/json',
            // 'Authorization': 'Bearer ' + getTokenFromCookie(),
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
            <p>Thời gian: ${review.createdAt}</p>
        `;
        reviewList.appendChild(reviewItem);
    });
}

// Gửi đánh giá lên api
function submitReview() {
    const productId = parseInt(document.getElementById('product-id-hidden').value);
    const comment = document.getElementById('comment').value;
    const star = parseInt(document.getElementById('star').value);
    const url = 'https://localhost:443/WebS2023_war/api/rates?';

    const token = getTokenFromCookie();
    const headers = {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': 'Bearer ' + token,
    };

    const data = {
        comment: comment,
        star: parseInt(star),
        productInOrderId: parseInt(productId),
        images: [],
    };

    fetch(url, {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(data)
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === 'success') {
                // Gửi thành công, cập nhật lại danh sách đánh giá
                getProductReviews(productId);
                document.getElementById('comment').value = '';
                document.getElementById('star').value = '';
            } else {
                console.log('Lỗi gửi đánh giá');
            }
        })
        .catch(error => {
            console.log('Lỗi kết nối API: ', error);
        });
}

// Khi tải xong trang, lấy danh sách đánh giá cho sản phẩm
window.onload = function() {
    getProductReviews(2);
}
