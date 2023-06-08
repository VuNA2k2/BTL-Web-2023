// Lấy token từ cookie
function getTokenFromCookie() {
    const cookie = document.cookie.split(';');
    const token = cookie[0].substring("token=".length, cookie[0].length);
    return token;
}

// Lấy productId và productInOrderId
const urlParams = new URLSearchParams(window.location.search);
const productInOrderId = urlParams.get('productInOrder');
console.log(productInOrderId);

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

