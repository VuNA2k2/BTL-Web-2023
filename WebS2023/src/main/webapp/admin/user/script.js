import {checkLogged} from '../../routing.js';

checkLogged('ADMIN');

function getTokenFromCookie() {
    const cookie = document.cookie.split(';');
    return cookie[0].substring("token=".length, cookie[0].length);
}

function fetchDataUser(role) {
    let api='https://localhost:443/WebS2023_war/api/users?role='+role;
    fetch(api, {
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
                throw new Error('Error fetching user data');
            }
        })
        .then(function (data) {
            console.log(data);
            displayUsers(data.data);
        })
        .catch(function (error) {
            console.error(error);
            alert('Error fetching user data. Please try again.');
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
            applyFilter();
        })
        .catch(function (error) {
            console.error(error);
            alert('Error putting user data. Please try again.');
        });
}

function createUserData(user) {
    let api = 'https://localhost:443/WebS2023_war/api/users';
    fetch(api, {
        method: 'POST',
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
                throw new Error('Error posting user data');
            }
        })
        .then(function () {
            console.log('ok');
            applyFilter();
        })
        .catch(function (error) {
            console.error(error);
            alert('Error posting user data. Please try again.');
        });
}

function deleteUserData(id) {
    let api = 'https://localhost:443/WebS2023_war/api/users?id=' + id;
    fetch(api, {
        method: 'DELETE',
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
                throw new Error('Error putting user data');
            }
        })
        .then(function () {
            applyFilter();
        })
        .catch(function (error) {
            console.error(error);
            alert('Error putting user data. Please try again.');
        });
}
function displayUsers(data) {
    const usersContainer = document.getElementById('users-container');

    // Remove existing rows
    const rows = usersContainer.getElementsByTagName('tr');
    while (rows.length > 1) {
        usersContainer.deleteRow(1);
    }

    data.forEach(function (user) {
        const row = document.createElement('tr');

        const idCell = document.createElement('td');
        idCell.textContent = user.id;

        const usernameCell = document.createElement('td');
        usernameCell.textContent = user.fullName;

        const roleCell = document.createElement('td');
        roleCell.textContent = user.role;

        const detailCell = document.createElement('button');
        detailCell.classList.add('user-card');
        detailCell.textContent = 'View Details';

        detailCell.addEventListener('click', function () {
            openUserDetailsModal(user);
        });

        row.appendChild(idCell);
        row.appendChild(usernameCell);
        row.appendChild(roleCell);
        row.appendChild(detailCell);

        usersContainer.appendChild(row);
    });
}

function openUserDetailsModal(user) {
    const popup = document.getElementById('popup');
    const userForm = document.getElementById('user-form');

    document.getElementById('id').value = user.id;
    document.getElementById('username').value = user.username;
    document.getElementById('password').value = '########';
    document.getElementById('fullName').value = user.fullName;
    document.getElementById('email').value = user.email;
    document.getElementById('phone').value = user.phone;
    document.getElementById('address').value = user.address;
    document.getElementById('role').value = user.role;
    const deleteButton = document.getElementById('delete-button');

    deleteButton.addEventListener('click', function () {
        deleteUserData(user.id);
        popup.style.display = 'none';
    });

    userForm.addEventListener('submit', function (e) {
        e.preventDefault();
        saveUser('save');
        popup.style.display = 'none';
    });

    popup.style.display = 'flex';
}

function showAddUserForm() {
    const userForm = document.getElementById('user-form');
    userForm.reset();

    const popup = document.getElementById('popup');
    const popupContent = document.getElementById('popup-content');

    const deleteButton = document.getElementById('delete-button');
    deleteButton.style.display = 'none';

    userForm.addEventListener('submit', function (e) {
        e.preventDefault();
        saveUser('create');
        popup.style.display = 'none';
    });

    popup.style.display = 'flex';
}

function saveUser(func) {
    const userForm = document.getElementById('user-form');
    const id = userForm.elements['id'].value;
    const username = userForm.elements['username'].value;
    const password = userForm.elements['password'].value;
    const fullName = userForm.elements['fullName'].value;
    const email = userForm.elements['email'].value;
    const phone = userForm.elements['phone'].value;
    const address = userForm.elements['address'].value;
    const role = userForm.elements['role'].value;

    // Perform save logic here
    console.log("Saving user...");
    console.log("ID:", id);
    console.log("Username:", username);
    console.log("Password:", password);
    console.log("Full Name:", fullName);
    console.log("Email:", email);
    console.log("Phone:", phone);
    console.log("Address:", address);
    console.log("Role:", role);
    if(func==='save'){
        const user = {
            username: username,
            fullName: fullName,
            email: email,
            phone: phone,
            address: address,
            role: role
        };
        console.log(func);
        saveUserData(user,id);
    }

    if(func==='create'){
        const user = {
            username: username,
            password: password,
            fullName: fullName,
            email: email,
            phone: phone,
            address: address,
            role: role
        };
        console.log(func);
        createUserData(user);
    }

}


window.addEventListener('click', function (event) {
    const popup = document.getElementById('popup');
    const popupContent = document.getElementById('popup-content');

    if (event.target == popup) {
        popup.style.display = 'none';
    }
});
const filterButton = document.getElementById('filterButton');
filterButton.addEventListener('click', applyFilter);

const addUser = document.getElementById('add-user');
addUser.addEventListener('click', showAddUserForm);
function applyFilter() {
    const selectedRole = document.getElementById('userFilterSelect').value;
    fetchDataUser(selectedRole);
}

applyFilter();


