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
            displayProductData(data.data);
        })
        .catch(function (error) {
            console.error(error);
            // Xử lý lỗi tìm nạp hoặc hiển thị thông báo lỗi
            alert('Error fetching product data. Please try again.');
        });
}

function displayProductData() {
    const productContainer = document.getElementById('productContainer');
    const categoryFilter = document.getElementById('categoryFilter');
    const filterBtn = document.getElementById('filterBtn');

    // Remove existing product cards
    while (productContainer.firstChild) {
        productContainer.removeChild(productContainer.firstChild);
    }

    function filterProducts() {
        const selectedCategory = categoryFilter.value;

        fetch('https://localhost/WebS2023_war/api/products')
            .then(function(response) {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Error fetching product data');
                }
            })
            .then(function(data) {
                const filteredData = selectedCategory
                    ? data.filter(function(productData) {
                        return productData.category.id === parseInt(selectedCategory);
                    })
                    : data;

                filteredData.forEach(function(productData) {
                    // Create product card element
                    const productCard = document.createElement('div');
                    productCard.classList.add('product-card');

                    // Create elements for product information
                    const idElement = document.createElement('p');
                    idElement.textContent = `ID: ${productData.id}`;
                    const nameElement = document.createElement('h3');
                    nameElement.textContent = productData.name;
                    const priceElement = document.createElement('p');
                    priceElement.textContent = `Price: ${productData.price}đ`;
                    const categoryElement = document.createElement('p');
                    categoryElement.textContent = `Category: ${productData.category.name}`;
                    const descriptionElement = document.createElement('p');
                    descriptionElement.textContent = `Description: ${productData.description}`;

                    // Create button for viewing product details
                    const viewDetailsBtn = document.createElement('button');
                    viewDetailsBtn.classList.add('view-details');
                    viewDetailsBtn.textContent = 'View Details';
                    // Add event listener for view details button
                    viewDetailsBtn.addEventListener('click', function() {
                        // Redirect to product detail page
                        window.location.href = `product-details.html?id=${productData.id}`;
                    });

                    // Append elements to product card
                    productCard.appendChild(idElement);
                    productCard.appendChild(nameElement);
                    productCard.appendChild(priceElement);
                    productCard.appendChild(categoryElement);
                    productCard.appendChild(descriptionElement);
                    productCard.appendChild(viewDetailsBtn);

                    // Append product card to product container
                    productContainer.appendChild(productCard);
                });
            })
            .catch(function(error) {
                console.error(error);
                alert('Error fetching product data. Please try again.');
            });
    }

}


// Hàm lọc sản phẩm
function applyFilter() {
    const selectedCategory = document.getElementById('categoryFilterSelect').value;

    // Gửi yêu cầu GET tới API để lấy danh sách sản phẩm theo danh mục lọc
    fetch(`https://localhost/WebS2023_war/api/products?category=${selectedCategory}`, {
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
            displayProductData(data.data);
        })
        .catch(function (error) {
            console.error(error);
            alert('Error fetching product data. Please try again.');
        });
}


