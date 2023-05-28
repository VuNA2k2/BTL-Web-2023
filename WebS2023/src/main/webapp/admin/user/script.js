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
// Make the API request and handle the response
function getUsers() {
    fetch('https://localhost:443/WebS2023_war/api/users', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
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
            displayUsers(data);
        })
        .catch(function (error) {
            console.error(error);
            // Handle fetch error or display error message
            alert('Error fetching user data. Please try again.');
        });
}

function displayUsers(data) {
    const usersContainer = document.getElementById('users-container');

    // Remove existing rows
    const rows = usersContainer.getElementsByTagName('tr');
    while (rows.length > 1) {
        usersContainer.deleteRow(1); // Start from index 1 to keep the table header
    }

    // Display user data
    data.forEach(function (userData) {
        const newRow = usersContainer.insertRow();

        // Create data cells
        const idCell = newRow.insertCell();
        const usernameCell = newRow.insertCell();
        const roleCell = newRow.insertCell();
        const detailCell = newRow.insertCell();

        // Set values for data cells
        idCell.textContent = userData.id;
        usernameCell.textContent = userData.username;
        roleCell.textContent = userData.role;

        // Create detail button and assign click event
        const detailButton = document.createElement('button');
        detailButton.textContent = 'Detail';
        detailButton.addEventListener('click', function () {
            openUserDetailsModal(userData);
        });

        detailCell.appendChild(detailButton);
    });
}

// Modal pop-up functions
const popup = document.getElementById('popup');
const popupContent = document.getElementById('popup-content');
const userForm = document.getElementById('user-form');
const deleteButton = document.getElementById('delete-button');

function openUserDetailsModal(userData) {
    // Populate form fields with user data
    document.getElementById('id').value = userData.id;
    document.getElementById('username').value = userData.username;
    document.getElementById('password').value = userData.password;
    document.getElementById('fullName').value = userData.fullName;
    document.getElementById('email').value = userData.email;
    document.getElementById('phone').value = userData.phone;
    document.getElementById('address').value = userData.address;
    document.getElementById('role').value = userData.role;

    // Show the modal
    popup.style.display = 'block';
}

function closeUserDetailsModal() {
    // Clear form fields
    userForm.reset();

    // Hide the modal
    popup.style.display = 'none';
}

userForm.addEventListener('submit', function (event) {
    event.preventDefault();

    const id = document.getElementById('id').value;
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const fullName = document.getElementById('fullName').value;
    const email = document.getElementById('email').value;
    const phone = document.getElementById('phone').value;
    const address = document.getElementById('address').value;
    const role = document.getElementById('role').value;

    const user = {
        id: id,
        username: username,
        password: password,
        fullName: fullName,
        email: email,
        phone: phone,
        address: address,
        role: role,
    };

    // Perform save user request
    saveUser(user);
});

deleteButton.addEventListener('click', function () {
    const id = document.getElementById('id').value;

    // Perform delete user request
    deleteUser(id);
});

function saveUser(user) {
    fetch('https://localhost/WebS2023_war/api/users', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
        body: JSON.stringify(user),
    })
        .then(function (response) {
            if (response.status === 200) {
                closeUserDetailsModal();
                getUsers();
            } else {
                throw new Error('Error saving user');
            }
        })
        .catch(function (error) {
            console.error(error);
            // Handle fetch error or display error message
            alert('Error saving user. Please try again.');
        });
}

function deleteUser(id) {
    fetch('https://localhost/WebS2023_war/api/users/' + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
        },
    })
        .then(function (response) {
            if (response.status === 200) {
                closeUserDetailsModal();
                getUsers();
            } else {
                throw new Error('Error deleting user');
            }
        })
        .catch(function (error) {
            console.error(error);
            // Handle fetch error or display error message
            alert('Error deleting user. Please try again.');
        });
}

// Call getUsers to get initial user data
getUsers();

function showAddUserForm() {
    openUserDetailsModal({
        id: '',
        username: '',
        password: '',
        fullName: '',
        email: '',
        phone: '',
        address: '',
        role: '',
    });
}


const popupOverlay = document.getElementById('popup');

// Function to close pop-up when clicked outside
function closePopUpOnClickOutside(event) {
    if (event.target === popupOverlay) {
        closeUserDetailsModal();
    }
}

// Event listener for click outside the pop-up
popupOverlay.addEventListener('click', closePopUpOnClickOutside);



