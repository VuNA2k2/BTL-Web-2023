// Lấy productId từ URL
const urlParamsProduct = new URLSearchParams(window.location.search);
const productId = parseInt(urlParamsProduct.get('productId'));
console.log(productId);

let reviews = [];

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
                console.log('Lấy thành công');
                reviews = data.data;
                refreshReviewList();
            } else {
                console.log('Lỗi lấy danh sách đánh giá');
            }
        })
        .catch(error => {
            console.log('Error connect to API: ', error);
        });
}

// Hiển thị thông tin đánh giá chung về sản phẩm
function displayListProductInfo(avgRates, countRates) {
    document.getElementById('avg-rates').textContent = avgRates.toFixed(1) + "/5.0 ";
    document.getElementById('count-rates').textContent = countRates;
}

// Hiển thị danh sách đánh giá
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

// Sắp xếp danh sách đánh giá
function sortReviews(sortValue, reviewsToSort) {
    if (!reviewsToSort || reviewsToSort.length === 0) {
        return;
    }
    let sortedReviews = [];
    if (sortValue === 'ratingDesc') {
        sortedReviews = reviewsToSort.sort((a, b) => b.star - a.star);
    } else if (sortValue === 'ratingAsc') {
        sortedReviews = reviewsToSort.sort((a, b) => a.star - b.star);
    } else if (sortValue === 'dateDesc') {
        sortedReviews = reviewsToSort.sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt));
    } else if (sortValue === 'dateAsc') {
        sortedReviews = reviewsToSort.sort((a, b) => new Date(a.createdAt) - new Date(b.createdAt));
    }
    displayListProductReviews(sortedReviews);
}

// Lọc danh sách đánh giá dựa trên bộ lọc
function filterReviews(filterValue) {
    let filteredReviews = [];
    if (filterValue === '') {
        filteredReviews = reviews;
    } else if (filterValue === 'has-image') {
        filteredReviews = reviews.filter(review => review.images.length > 0);
    } else {
        filteredReviews = reviews.filter(review => review.star === parseInt(filterValue));
    }
    return filteredReviews;
}

// Làm mới danh sách đánh giá
function refreshReviewList() {
    const filterValue = document.querySelector('input[name="filterStar"]:checked').value;
    const filteredReviews = filterReviews(filterValue);
    const sortValue = document.getElementById('sortSelect').value;
    sortReviews(sortValue, filteredReviews);
}

// Update bộ lọc và sắp xếp
function updateFilterAndSort() {
    refreshReviewList();
    const filterLabels = document.querySelectorAll('#filter label');
    filterLabels.forEach(label => {
        label.classList.remove('selected');
    });
    // Thêm lớp .selected cho nút lọc được chọn (nếu có)
    const selectedFilterInput = document.querySelector('input[name="filterStar"]:checked');
    if (selectedFilterInput) {
        const selectedFilterLabel = selectedFilterInput.parentElement;
        selectedFilterLabel.classList.add('selected');
    }
}

// Khi có sự thay đổi sắp xếp
function sortSelectChange() {
    updateFilterAndSort();
}

// Gắn sự kiện change cho select
const sortSelect = document.getElementById('sortSelect');
sortSelect.addEventListener('change', sortSelectChange);

// Gắn sự kiện change cho các radio button
const filterButtons = document.querySelectorAll('input[name="filterStar"]');
filterButtons.forEach(filterButton => {
    filterButton.addEventListener('change', updateFilterAndSort);
});

// Lấy danh sách đánh giá sản phẩm từ API
getListProductReviews();

// Sắp xếp danh sách đánh giá ban đầu giảm dần theo số sao
sortReviews('ratingDesc');
