// Lấy token từ cookie
function getTokenFromCookie() {
    const cookie = document.cookie.split(';');
    const token = cookie[0].substring("token=".length, cookie[0].length);
    return token;
}

// Lấy productId và productInOrderId
const urlParams = new URLSearchParams(window.location.search);
const productId = urlParams.get('productId');
console.log(productId);
const productInOrderId = urlParams.get('productInOrder');
console.log(productInOrderId);

// Lấy đánh giá sản phẩm theo productId
function getProductReviews() {
    fetch(`https://localhost:443/WebS2023_war/api/rates/avg?productId=${productId}`, {
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        }
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
            'Accept': 'application/json',
        }
    })
        .then(response => response.json())
        .then(data => {
            if (data.code === 'success') {
                const reviews = data.data;
                displayProductReviews(reviews);
            } else {
                console.log('Lỗi lấy đánh giá sản phẩm');
            }
        })
        .catch(error => {
            console.log('Error connect to API: ', error);
        });
}

// Hiển thị đánh giá sản phẩm
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
        let imagesHtml = '';
        if (review.images.length > 0) {
            imagesHtml = '<div class="review-images">';
            review.images.forEach(image => {
                imagesHtml += `<img src="${image}" alt="Review Image">`;
            });
            imagesHtml += '</div>';
        }
        reviewItem.innerHTML = `
            <p>Bình luận: ${review.comment}</p>
            <p>Đánh giá: ${review.star} <span class="star-fill"></span></p>
            ${imagesHtml}
            <p style="font-size: 13px">${review.createdAt}</p>
        `;
        reviewList.appendChild(reviewItem);
    });
}

// Gửi đánh giá lên api
function submitReview() {
    const comment = document.getElementById('comment').value;
    const ratingInputs = document.querySelectorAll('.rating input[type="radio"]');
    // const files = document.getElementById('image').files;
    // const images = [];
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

    const data = {
        comment: comment,
        star: star,
        productInOrderId: productInOrderId,
        images: [],
    };

    fetch(`https://localhost:443/WebS2023_war/api/rates`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + getTokenFromCookie(),
        },
        body: JSON.stringify(data)
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
