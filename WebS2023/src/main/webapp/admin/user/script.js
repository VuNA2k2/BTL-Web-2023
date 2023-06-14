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
    const usersContainer = document.getElementById('khung-nguoi-dung');

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
        detailCell.classList.add('nguoi-dung');
        detailCell.textContent = 'Chi tiết';

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
    const popup = document.getElementById('khung-chua');
    const userForm = document.getElementById('dien-thong-tin');
    const passwordLabel = document.getElementById("pass");
    const passwordInput = document.getElementById("password");
    const originalDisplayLabel = passwordLabel.style.display;
    const originalDisplayInput = passwordInput.style.display;
    passwordLabel.style.display = 'none';
    passwordInput.style.display = 'none';
    document.getElementById('id').value = user.id;
    document.getElementById('username').value = user.username;
    document.getElementById('password').value = '1';
    document.getElementById('fullName').value = user.fullName;
    document.getElementById('email').value = user.email;
    document.getElementById('phone').value = user.phone;
    document.getElementById('address').value = user.address;
    document.getElementById('quyen').value = user.role;
    const deleteButton = document.getElementById('nut-xoa');
    const closeButton = document.getElementById('nut-dong');
    deleteButton.addEventListener('click', function () {
        deleteUserData(user.id);
        closePopup();
    });

    userForm.addEventListener('submit', function (e) {
        e.preventDefault();
        closePopup();
        saveUser('save');

    });
    closeButton.addEventListener('click', function () {
        closePopup();
    });

    function closePopup() {
        popup.style.display = 'none';
        passwordLabel.style.display = originalDisplayLabel;
        passwordInput.style.display = originalDisplayInput;
    }

    popup.style.display = 'flex';
}

function showAddUserForm() {
    const userForm = document.getElementById('dien-thong-tin');
    userForm.reset();
    var passwordLabel = document.getElementById("pass");
    var passwordInput = document.getElementById("password");
    const popup = document.getElementById('khung-chua');
    const popupContent = document.getElementById('thong-tin');

    const deleteButton = document.getElementById('nut-xoa');
    deleteButton.style.display = 'none';

    userForm.addEventListener('submit', function (e) {
        e.preventDefault();
        saveUser('create');
        popup.style.display = 'none';
    });
    const closeButton = document.getElementById('nut-dong');
    closeButton.addEventListener('click', function () {
        popup.style.display = 'none';
    });

    popup.style.display = 'flex';
}

function saveUser(func) {
    const userForm = document.getElementById('dien-thong-tin');
    const id = userForm.elements['id'].value;
    const username = userForm.elements['username'].value;
    const password = userForm.elements['password'].value;
    const fullName = userForm.elements['fullName'].value;
    const email = userForm.elements['email'].value;
    const phone = userForm.elements['phone'].value;
    const address = userForm.elements['address'].value;
    const role = userForm.elements['quyen'].value;

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

const filterButton = document.getElementById('nut-loc');
filterButton.addEventListener('click', applyFilter);

const addUser = document.getElementById('them-nguoi-dung');
addUser.addEventListener('click', showAddUserForm);
function applyFilter() {
    const selectedRole = document.getElementById('loc-quyen').value;
    fetchDataUser(selectedRole);
}

applyFilter();