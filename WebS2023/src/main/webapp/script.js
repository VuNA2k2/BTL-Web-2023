


function getTokenFromCookie() {
    const cookie = document.cookie.split(';');
    const token = cookie[0].substring("token=".length, cookie[0].length);
    return token;
}

fetch('https://localhost:443/WebS2023_war/api/products', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        // 'Authorization': 'Bearer ' + getTokenFromCookie(),
    }
}).then(function (response) {
    if(response.status === 200) {
        response.json().then(function (data) {
            console.log(data)
            // localStorage.setItem('user', JSON.stringify(data.data));
            // if(data.data.role === 'ADMIN') {
            //     window.location.href = "/WebS2023_war/admin/user";
            // }
        });
    }
    // window.location.href = "/WebS2023_war/home";
});