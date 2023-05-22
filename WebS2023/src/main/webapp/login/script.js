


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
                window.location.href = "/WebS2023_war/";
            });
        } else {
            response.json().then(function (data) {
                alert(data.message);
            });
        }
    });
}