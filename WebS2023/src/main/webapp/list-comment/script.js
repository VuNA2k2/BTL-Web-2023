// Lấy productId
const urlParamsProduct = new URLSearchParams(window.location.search);
const productId = parseInt(urlParamsProduct.get('productId'));
console.log(productId);

// Lấy đánh giá sản phẩm theo productId
function getListProductReviews() {
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
                displayListProductInfo(avgRates, countRates);
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
                displayListProductReviews(reviews);
            } else {
                console.log('Lỗi lấy đánh giá sản phẩm');
            }
        })
        .catch(error => {
            console.log('Error connect to API: ', error);
        });
}

// Hiển thị đánh giá chung về sản phẩm
function displayListProductInfo(avgRates, countRates) {
    document.getElementById('avg-rates').textContent = avgRates.toFixed(1) + "/5.0 ";
    document.getElementById('count-rates').textContent = countRates;
}

// Hiển thị danh sách đánh giá sản phẩm
function displayListProductReviews(reviews) {
    const reviewListProduct = document.getElementById('review-list');
    reviewListProduct.innerHTML = '';
    reviews.forEach(review => {
        console.log(review);
        const reviewItems = document.createElement('li');
        reviewItems.className = 'review-item';
        let imagesHtml = '';
        if (review.images.length > 0) {
            imagesHtml = '<div class="review-images">';
            review.images.forEach(image => {
                const aspectRatio = 150 / image.height;
                const newWidth = Math.round(image.width * aspectRatio);
                imagesHtml += `<img src="${image.link}" alt="Review Image" style="max-height: 150px; width: ${newWidth}px;">`;
            });
            imagesHtml += '</div>';
        }
        reviewItems.innerHTML = `
            <p>Đánh giá: ${review.star} <span class="star-fill"></span></p>
            <p>Bình luận: ${review.comment}</p>
            ${imagesHtml}
            <p style="font-size: 13px">${review.createdAt}</p>
        `;
        reviewListProduct.appendChild(reviewItems);
    });
}

getListProductReviews();
