


function login() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var data = {
        username: username,
        password: password
    };
    fetch(
        "https://localhost:443/WebS2023_war/api/auth/login",
        {
            method: "POST",
            body: JSON.stringify(data),
            headers: {
                "Content-Type": "application/json",
                'Accept': 'application/json',
            }
        }
    ).then(function (response) {
        if (response.status === 200) {
            response.json().then( (data) => {
                console.log(data)
                document.cookie = "token=" + data.data.token + ";path=/";
                // checkLogged();
                window.location.href = "/WebS2023_war";

            });
        } else {
            response.json().then(function (data) {
                alert(data.message);
            });
        }
    });
}

// Thêm phần điều hướng đăng nhập
// function checkLogged() {
//     let token;
//     document.cookie.split(';').forEach(function (c) {
//         if (c.includes('token')) {
//             token = c.split('=')[1];
//         }
//     });
//     if (token === undefined || token === null || token === '' || localStorage.getItem('user') === null || localStorage.getItem('user') === undefined) {
//         window.location.href = "/WebS2023_war/login";
//         return;
//     }
//
//     if(JSON.parse(localStorage.getItem('user')).role ==='ADMIN')
//     {
//         window.location.href = "/WebS2023_war/admin/user";
//         return;
//     }
//     if(JSON.parse(localStorage.getItem('user')).role ==='USER')
//     {
//         window.location.href = "/WebS2023_war/home";
//         return;
//     }
// }