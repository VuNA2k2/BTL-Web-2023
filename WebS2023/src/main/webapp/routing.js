export function checkLogged(role) {
    if (role === 'BOTH') return;
    let token;
    document.cookie.split(';').forEach(function (c) {
        if (c.includes('token')) {
            token = c.split('=')[1];
        }
    });
    if (!token || localStorage.getItem('user') === null || !localStorage.getItem('user')|| JSON.parse(localStorage.getItem('user')).role !== role) {
        window.location.href = "/WebS2023_war/login";
        return false;
    }
    return true;
}