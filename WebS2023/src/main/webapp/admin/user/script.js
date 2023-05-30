function checkLogged(role) {
    let token;
    document.cookie.split(';').forEach(function (c) {
        if (c.includes('token')) {
            token = c.split('=')[1];
        }
    });
    if (token === undefined || token === null || token === '' || localStorage.getItem('user') === null || localStorage.getItem('user') === undefined || JSON.parse(localStorage.getItem('user')).role !== role) {
        window.location.href = "/WebS2023_war/login";
        return;
    }
    // const main = document.getElementById('main');
    // main.innerHTML = `Hello ${localStorage.getItem('user')}`;
}

checkLogged('ADMIN');


getUsers();
function getUsers() {
    const users = {
        "users": [
            {
                "id": 1,
                "username": "user1",
                "password": "password1",
                "full_name": "John Doe",
                "email": "john.doe@example.com",
                "phone": "1234567890",
                "address": "123 Main St, City",
                "role": "USER"
            },
            {
                "id": 2,
                "username": "user2",
                "password": "password2",
                "full_name": "Jane Smith",
                "email": "jane.smith@example.com",
                "phone": "9876543210",
                "address": "456 Elm St, Town",
                "role": "USER"
            },
            {
                "id": 3,
                "username": "admin",
                "password": "admin123",
                "full_name": "Admin User",
                "email": "admin@example.com",
                "phone": "5555555555",
                "address": "789 Oak St, City",
                "role": "ADMIN"
            }
        ]
    };

    displayUsers(users);
}

function displayUsers(users) {
    const usersContainer = document.getElementById('users-container');

    // Remove existing rows
    const rows = usersContainer.getElementsByTagName('tr');
    while (rows.length > 1) {
        usersContainer.deleteRow(1);
    }

    users.users.forEach(function (user) {
        const row = document.createElement('tr');

        const idCell = document.createElement('td');
        idCell.textContent = user.id;

        const usernameCell = document.createElement('td');
        usernameCell.textContent = user.username;

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
    const popupContent = document.getElementById('popup-content');
    const userForm = document.getElementById('user-form');

    document.getElementById('id').value = user.id;
    document.getElementById('username').value = user.username;
    document.getElementById('password').value = user.password;
    document.getElementById('fullName').value = user.full_name;
    document.getElementById('email').value = user.email;
    document.getElementById('phone').value = user.phone;
    document.getElementById('address').value = user.address;
    document.getElementById('role').value = user.role;

    const deleteButton = document.getElementById('delete-button');

    deleteButton.addEventListener('click', function () {
        deleteUser(user.id);
        popup.style.display = 'none';
    });

    userForm.addEventListener('submit', function (e) {
        e.preventDefault();
        saveUser();
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
        saveUser();
        popup.style.display = 'none';
    });

    popup.style.display = 'flex';
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

    // Refresh user list
    getUsers();
}

function deleteUser(id) {
    // Perform delete logic here
    console.log("Deleting user with ID:", id);

    // Refresh user list
    getUsers();
}

window.addEventListener('click', function (event) {
    const popup = document.getElementById('popup');
    const popupContent = document.getElementById('popup-content');

    if (event.target == popup) {
        popup.style.display = 'none';
    }
});
