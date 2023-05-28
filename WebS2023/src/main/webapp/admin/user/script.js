function checkLogged(role) {
    let token;
    document.cookie.split(';').forEach(function (c) {
        if (c.includes('token')) {
            token = c.split('=')[1];
        }
    });
    if (token === undefined || token === null || token === '' || localStorage.getItem('user') === null || localStorage.getItem('user') === undefined || JSON.parse(localStorage.getItem('user')).role !== role) {
        window.location.href = "/WebS2023_war/login";
        // return;
    }
    // const main = document.getElementById('main');
    // main.innerHTML = `Hello ${localStorage.getItem('user')}`;
}

checkLogged('ADMIN');

const users = [
    {
        "id": 1,
        "username": "john_doe",
        "password": "123456",
        "full_name": "John Doe",
        "email": "john.doe@example.com",
        "phone": "123456789",
        "address": "123 Main St",
        "role": "USER"
    },
    {
        "id": 2,
        "username": "jane_smith",
        "password": "abcdef",
        "full_name": "Jane Smith",
        "email": "jane.smith@example.com",
        "phone": "987654321",
        "address": "456 Elm St",
        "role": "ADMIN"
    }
];

document.addEventListener('DOMContentLoaded', getUsers);

function getUsers() {
    const usersContainer = document.getElementById('users-container');
    usersContainer.innerHTML = '';

    users.forEach(user => {
        const userCard = document.createElement('div');
        userCard.classList.add('user-card');
        userCard.setAttribute('data-userid', user.id);
        userCard.innerHTML = `
            <p><strong>ID:</strong> ${user.id}  <strong>Username:</strong> ${user.username}  <strong>Role:</strong> ${user.role}</p>
        `;
        userCard.addEventListener('click', () => showUserDetails(user.id));
        usersContainer.appendChild(userCard);
    });
}

function showUserDetails(userId) {
    const user = users.find(user => user.id === userId);

    const popup = document.getElementById('popup');
    const form = document.getElementById('user-form');
    const idInput = document.getElementById('id');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const fullNameInput = document.getElementById('fullName');
    const emailInput = document.getElementById('email');
    const phoneInput = document.getElementById('phone');
    const addressInput = document.getElementById('address');
    const roleInput = document.getElementById('role');

    idInput.value = user.id;
    usernameInput.value = user.username;
    passwordInput.value = user.password;
    fullNameInput.value = user.full_name;
    emailInput.value = user.email;
    phoneInput.value = user.phone;
    addressInput.value = user.address;
    roleInput.value = user.role;

    form.addEventListener('submit', e => {
        e.preventDefault();

        const updateUser = {
            id: idInput.value,
            username: usernameInput.value,
            password: passwordInput.value,
            full_name: fullNameInput.value,
            email: emailInput.value,
            phone: phoneInput.value,
            address: addressInput.value,
            role: roleInput.value
        };

        const index = users.findIndex(user => user.id === userId);
        users[index] = updateUser;

        popup.style.display = 'none';
        getUsers();
    });

    const deleteButton = document.getElementById('delete-button');
    deleteButton.addEventListener('click', () => {
        const index = users.findIndex(user => user.id === userId);
        users.splice(index, 1);

        popup.style.display = 'none';
        getUsers();
    });

    popup.style.display = 'block';
}

function showAddUserForm() {
    const popup = document.getElementById('popup');
    const form = document.getElementById('user-form');
    const idInput = document.getElementById('id');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const fullNameInput = document.getElementById('fullName');
    const emailInput = document.getElementById('email');
    const phoneInput = document.getElementById('phone');
    const addressInput = document.getElementById('address');
    const roleInput = document.getElementById('role');

    idInput.value = '';
    usernameInput.value = '';
    passwordInput.value = '';
    fullNameInput.value = '';
    emailInput.value = '';
    phoneInput.value = '';
    addressInput.value = '';
    roleInput.value = '';

    form.addEventListener('submit', e => {
        e.preventDefault();

        const newUser = {
            id: users.length + 1,
            username: usernameInput.value,
            password: passwordInput.value,
            full_name: fullNameInput.value,
            email: emailInput.value,
            phone: phoneInput.value,
            address: addressInput.value,
            role: roleInput.value
        };

        users.push(newUser);

        popup.style.display = 'none';
        getUsers();
    });

    popup.style.display = 'block';
}
