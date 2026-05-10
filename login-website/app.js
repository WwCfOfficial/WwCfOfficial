// DOM Elements
const loginToggle = document.getElementById('login-toggle');
const registerToggle = document.getElementById('register-toggle');
const loginForm = document.getElementById('login-form');
const registerForm = document.getElementById('register-form');
const loginMessage = document.getElementById('login-message');
const registerMessage = document.getElementById('register-message');
const dashboard = document.getElementById('dashboard');
const logoutBtn = document.getElementById('logout-btn');

// Toggle between login and register forms
loginToggle.addEventListener('click', () => {
    loginToggle.classList.add('active');
    registerToggle.classList.remove('active');
    loginForm.classList.add('active');
    registerForm.classList.remove('active');
    clearMessages();
});

registerToggle.addEventListener('click', () => {
    registerToggle.classList.add('active');
    loginToggle.classList.remove('active');
    registerForm.classList.add('active');
    loginForm.classList.remove('active');
    clearMessages();
});

// Toggle password visibility
document.querySelectorAll('.toggle-password').forEach(btn => {
    btn.addEventListener('click', () => {
        const targetId = btn.getAttribute('data-target');
        const input = document.getElementById(targetId);
        if (input.type === 'password') {
            input.type = 'text';
            btn.textContent = '🙈';
        } else {
            input.type = 'password';
            btn.textContent = '👁️';
        }
    });
});

// User storage (localStorage-based for demo)
function getUsers() {
    const users = localStorage.getItem('users');
    return users ? JSON.parse(users) : [];
}

function saveUsers(users) {
    localStorage.setItem('users', JSON.stringify(users));
}

function getCurrentUser() {
    const user = localStorage.getItem('currentUser');
    return user ? JSON.parse(user) : null;
}

function setCurrentUser(user) {
    localStorage.setItem('currentUser', JSON.stringify(user));
}

function removeCurrentUser() {
    localStorage.removeItem('currentUser');
}

// Show message
function showMessage(element, text, type) {
    element.textContent = text;
    element.className = `message ${type}`;
}

function clearMessages() {
    loginMessage.className = 'message';
    loginMessage.textContent = '';
    registerMessage.className = 'message';
    registerMessage.textContent = '';
}

// Register form submission
registerForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const name = document.getElementById('register-name').value.trim();
    const email = document.getElementById('register-email').value.trim();
    const password = document.getElementById('register-password').value;
    const confirm = document.getElementById('register-confirm').value;

    // Validation
    if (password !== confirm) {
        showMessage(registerMessage, 'Passwords do not match!', 'error');
        return;
    }

    if (password.length < 6) {
        showMessage(registerMessage, 'Password must be at least 6 characters!', 'error');
        return;
    }

    const users = getUsers();

    // Check if email already exists
    if (users.find(u => u.email === email)) {
        showMessage(registerMessage, 'An account with this email already exists!', 'error');
        return;
    }

    // Save new user
    const newUser = {
        name,
        email,
        password,
        createdAt: new Date().toLocaleDateString()
    };

    users.push(newUser);
    saveUsers(users);

    showMessage(registerMessage, 'Account created successfully! Please login.', 'success');
    registerForm.reset();

    // Switch to login form after a delay
    setTimeout(() => {
        loginToggle.click();
    }, 1500);
});

// Login form submission
loginForm.addEventListener('submit', (e) => {
    e.preventDefault();

    const email = document.getElementById('login-email').value.trim();
    const password = document.getElementById('login-password').value;

    const users = getUsers();
    const user = users.find(u => u.email === email && u.password === password);

    if (user) {
        showMessage(loginMessage, 'Login successful! Redirecting...', 'success');
        setCurrentUser(user);

        setTimeout(() => {
            showDashboard(user);
        }, 1000);
    } else {
        showMessage(loginMessage, 'Invalid email or password!', 'error');
    }
});

// Show dashboard
function showDashboard(user) {
    document.querySelector('.container').style.display = 'none';
    dashboard.style.display = 'flex';
    document.getElementById('user-name').textContent = user.name;
    document.getElementById('user-email').textContent = user.email;
    document.getElementById('user-date').textContent = user.createdAt;
}

// Logout
logoutBtn.addEventListener('click', () => {
    removeCurrentUser();
    dashboard.style.display = 'none';
    document.querySelector('.container').style.display = 'block';
    loginForm.reset();
    registerForm.reset();
    clearMessages();
});

// Check if user is already logged in
window.addEventListener('DOMContentLoaded', () => {
    const user = getCurrentUser();
    if (user) {
        showDashboard(user);
    }
});
