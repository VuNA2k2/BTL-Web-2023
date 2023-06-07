function checkLogged() {
    let token;
    document.cookie.split(';').forEach(function (c) {
        if (c.includes('token')) {
            token = c.split('=')[1];
        }
    });
    if (token === undefined || token === null || token === '' || localStorage.getItem('user') === null || localStorage.getItem('user') === undefined) {
        window.location.href = "/WebS2023_war/login";
        return;
    }
    // const main = document.getElementById('main');
    // main.innerHTML = `Hello ${localStorage.getItem('user')}`;
}

checkLogged();
function getTokenFromCookie() {
    const cookie = document.cookie.split(';');
    const token = cookie[0].substring("token=".length, cookie[0].length);
    return token;
}
var user;
function getDataUser()
{
    fetch('https://localhost:443/WebS2023_war/api/users', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + getTokenFromCookie(),
        }
    }).then(function (response) {
        if(response.status === 200) {
            response.json().then(function (data) {
                console.log(data)
                localStorage.setItem('user', JSON.stringify(data.data));
                user = JSON.parse(localStorage.getItem('user'));
                displayUser(user);
            });
        }

    });

}
function saveUserData(user, id) {
    let api = 'https://localhost:443/WebS2023_war/api/users?id=' + id;
    fetch(api, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            'Authorization': 'Bearer ' + getTokenFromCookie(),
        },
        body: JSON.stringify(user),
    })
        .then(function (response) {
            if (response.status === 200) {
                return response.json();
            } else {
                throw new Error('Error putting user data');
            }
        })
        .then(function () {
            console.log('ok');
            getDataUser();
        })
        .catch(function (error) {
            console.error(error);
            alert('Error putting user data. Please try again.');
        });
}

function displayUser(userData) {
    const nameElement = document.getElementById("nameV");
    const emailElement = document.getElementById("emailV");
    const phoneNumberElement = document.getElementById("phoneNumberV");
    const addressElement = document.getElementById("addressV");

    nameElement.textContent = userData.fullName;
    emailElement.textContent = userData.email;
    phoneNumberElement.textContent = userData.phone;
    addressElement.textContent = userData.address;
}


function logOut()
{
    document.cookie = 'token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;';
    localStorage.removeItem('user');
    window.location.href='https://localhost/WebS2023_war/login/';
}
function showPopup() {

    const popup = document.getElementById('popup');
    popup.style.display = 'flex';
    const userForm = document.getElementById('user-form');

    document.getElementById('id').value = user.id;
    document.getElementById('username').value = user.username;
    document.getElementById('fullName').value = user.fullName;
    document.getElementById('email').value = user.email;
    document.getElementById('phone').value = user.phone;
    document.getElementById('address').value = user.address;
    document.getElementById('role').value = user.role;

    userForm.addEventListener('submit', function (e) {
        e.preventDefault();
        saveUser();
        popup.style.display = 'none';
    });


}
function saveUser() {
    const userForm = document.getElementById('user-form');
    const id = userForm.elements['id'].value;
    const username = userForm.elements['username'].value;
    const fullName = userForm.elements['fullName'].value;
    const email = userForm.elements['email'].value;
    const phone = userForm.elements['phone'].value;
    const address = userForm.elements['address'].value;
    const role = userForm.elements['role'].value;

    const userInput = {
        username: username,
        fullName: fullName,
        email: email,
        phone: phone,
        address: address,
        role: role
    };
    console.log(JSON.stringify(userInput))
    saveUserData(userInput,id);
}
window.addEventListener('click', function (event) {
    const popup = document.getElementById('popup');
    const popupContent = document.getElementById('popup-content');

    if (event.target == popup) {
        popup.style.display = 'none';
    }
});
getDataUser();