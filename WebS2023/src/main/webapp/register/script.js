
function register() {
    const username = document.getElementById('username');
    const password = document.getElementById('password');
    const fullName = document.getElementById('fullName');
    const confirmPassword = document.getElementById('confirmPassword');
    const email = document.getElementById('email');
    const phone = document.getElementById('phone');
    const address = document.getElementById('address');
    const btnRegister = document.getElementById('btnRegister');

    if (username.value === '' || password.value === '' || confirmPassword.value === '' || email.value === '' || phone.value === '' || address.value === '' || fullName.value === '') {
        alert('Please fill all fields');
        return;
    }
    if (password.value !== confirmPassword.value) {
        alert('Password and Confirm Password must be the same');
        return;
    }
    const user = {
        username: username.value,
        password: password.value,
        email: email.value,
        phone: phone.value,
        address: address.value,
        fullName: fullName.value,
        role: 'USER'
    };
    fetch('https://localhost:443/WebS2023_war/api/auth/register', {
        method: 'POST', body: JSON.stringify(user), headers: {
            'Content-Type': 'application/json', 'Accept': 'application/json',
        }
    }).then(function (response) {
        if (response.status === 200) {
            response.json().then(function (data) {
                alert(JSON.stringify(data));
                // if (data.code === 'success') {
                //     alert('Register successfully');
                //     window.location.href = '/WebS2023_war/login';
                // }
            });
        } else {
            // response.json().then(function (data) {
            //     alert(data.message);
            // });
        }
    });
}