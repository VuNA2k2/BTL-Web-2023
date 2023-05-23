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