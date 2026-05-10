# User Login Website

A clean, responsive user login and registration website built with HTML, CSS, and JavaScript.

## Features

- **User Registration** - Create new accounts with name, email, and password
- **User Login** - Authenticate with email and password
- **Password Visibility Toggle** - Show/hide password fields
- **Form Validation** - Client-side validation for all inputs
- **Dashboard** - Simple dashboard shown after successful login
- **Persistent Sessions** - Stay logged in using localStorage
- **Responsive Design** - Works on desktop, tablet, and mobile
- **Modern UI** - Clean gradient design with smooth animations

## Getting Started

Simply open `index.html` in a web browser, or serve the files with any static file server:

```bash
# Using Python
python3 -m http.server 8000

# Using Node.js (npx)
npx serve .
```

Then open `http://localhost:8000` in your browser.

## Project Structure

```
user-login-website/
├── index.html    # Main HTML file
├── styles.css    # Stylesheet with responsive design
├── app.js        # JavaScript logic for auth
└── README.md     # This file
```

## How It Works

- User data is stored in the browser's `localStorage` for demonstration purposes
- Passwords are stored as plain text in localStorage (for demo only - use proper hashing in production)
- Session persistence allows users to stay logged in across browser refreshes

## Security Note

This is a frontend-only demo. For production use, implement:
- Server-side authentication
- Password hashing (bcrypt, argon2)
- HTTPS
- CSRF protection
- Rate limiting
