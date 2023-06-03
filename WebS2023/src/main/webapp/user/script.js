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
function displayUser(userData) {
    const nameElement = document.getElementById("nameV");
    const emailElement = document.getElementById("emailV");
    const phoneNumberElement = document.getElementById("phoneNumberV");
    const addressElement = document.getElementById("addressV");

    nameElement.textContent = userData.name;
    emailElement.textContent = userData.email;
    phoneNumberElement.textContent = userData.phoneNumber;
    addressElement.textContent = userData.address;
}

const userData = {
        "name": "John Doe",
        "email": "johndoe@example.com",
        "phoneNumber": "1234567890",
        "address": "123 ABC Street, XYZ City"
};
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
    document.getElementById('password').value = '########';
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
    const password = userForm.elements['password'].value;
    const fullName = userForm.elements['fullName'].value;
    const email = userForm.elements['email'].value;
    const phone = userForm.elements['phone'].value;
    const address = userForm.elements['address'].value;
    const role = userForm.elements['role'].value;

    const user = {
        username: username,
        password: password,
        fullName: fullName,
        email: email,
        phone: phone,
        address: address,
        role: role
    };
    saveUserData(user,id);


}
window.addEventListener('click', function (event) {
    const popup = document.getElementById('popup');
    const popupContent = document.getElementById('popup-content');

    if (event.target == popup) {
        popup.style.display = 'none';
    }
});

displayUser(userData);
