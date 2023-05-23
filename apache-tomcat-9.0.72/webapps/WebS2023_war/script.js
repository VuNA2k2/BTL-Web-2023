fetch('https://localhost:443/WebS2023_war/api/users', {
    method: 'GET',
    headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json',
        'Authorization': 'Bearer ' + document.cookie.split('=')[1],
    }
}).then(function (response) {
    if(response.status === 200) {
        response.json().then(function (data) {
            console.log(data)
            localStorage.setItem('user', JSON.stringify(data.data));
            if(data.data.role === 'ADMIN') {
                window.location.href = "/WebS2023_war/admin/user";
            }
        });
    }
    window.location.href = "/WebS2023_war/home";
});