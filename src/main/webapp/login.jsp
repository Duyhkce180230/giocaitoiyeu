<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>

    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/img/favicon_io/favicon.ico">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" />

    <style>
        body {
            background: url("${pageContext.request.contextPath}/images/BackgroundGioCai.jpg") no-repeat center center fixed;
            background-size: cover;
            font-family: "Rajdhani", sans-serif;
        }

        .login-container {
            background: rgba(56, 30, 75, 0.9);
            padding: 30px;
            border-radius: 16px;
            width: 100%;
            max-width: 400px;
            box-shadow: 0 8px 20px rgba(0, 0, 0, 0.4);
            color: white;
            animation: fadeIn 0.6s ease-in-out;
        }

        .login-container h2 {
            font-weight: bold;
            margin-bottom: 20px;
            color: #fff;
        }

        .form-control {
            border-radius: 8px;
            border: 1px solid #7a4bc7;
        }

        .btn-primary {
            background-color: #7a4bc7;
            border: none;
            border-radius: 8px;
            transition: background 0.3s ease, transform 0.2s;
        }

        .btn-primary:hover {
            background-color: #9c6ffb;
            transform: translateY(-2px);
        }

        .link-forgot {
            color: #bca9ff;
            cursor: pointer;
            text-decoration: none;
        }

        .link-forgot:hover {
            text-decoration: underline;
        }

        @keyframes fadeIn {
            from {opacity: 0; transform: translateY(-10px);}
            to {opacity: 1; transform: translateY(0);}
        }
    </style>
</head>
<body>

<div class="container d-flex justify-content-center align-items-center" style="min-height: 100vh;">
    <div class="login-container text-center">
        <h2>Welcome Back</h2>

        <form method="POST" action="login">
            <!-- Username -->
            <div class="mb-3 text-start">
                <label for="username" class="form-label fw-bold">Username or Email</label>
                <input type="text" class="form-control" id="username" name="username" 
                       value="${usernameCookieSaved}" placeholder="Enter Username or Email" required />
            </div>

            <!-- Password -->
            <div class="mb-3 text-start position-relative">
                <label for="password" class="form-label fw-bold">Password</label>
                <div class="input-group">
                    <input type="password" class="form-control pe-5" id="password" name="password" placeholder="Enter Password" required />
                    <span class="input-group-text bg-white border-start-0" style="cursor: pointer;">
                        <i class="bi bi-eye-slash toggle-password" data-target="password"></i>
                    </span>
                </div>
            </div>

            <!-- Remember Me -->
            <div class="mb-3 form-check text-start">
                <input type="checkbox" class="form-check-input" name="rememberMe" id="rememberMe" />
                <label class="form-check-label" for="rememberMe">Remember me</label>
            </div>

            <!-- Captcha -->
            <div class="mb-3">
                <div class="cf-turnstile" data-sitekey="0x4AAAAAABgts3i36HFv5My1"></div>
            </div>

            <!-- Submit -->
            <button type="submit" class="btn btn-primary w-100">Login</button>

        </form>
    </div>
</div>



<!-- Toast -->
<div style="z-index: 3000;" id="customErrorToast" class="toast align-items-center text-white bg-danger border-0 position-fixed bottom-0 end-0 m-3" role="alert">
    <div class="d-flex">
        <div class="toast-body" id="customErrorMessage"></div>
        <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast"></button>
    </div>
</div>

<jsp:include page="/layout/toast.jsp" />

<!-- Scripts -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://challenges.cloudflare.com/turnstile/v0/api.js" async defer></script>

<script>
    // Toggle Password
    document.querySelectorAll('.toggle-password').forEach(icon => {
        icon.addEventListener('click', () => {
            const targetInput = document.getElementById(icon.getAttribute('data-target'));
            const isPassword = targetInput.type === 'password';
            targetInput.type = isPassword ? 'text' : 'password';
            icon.classList.toggle('bi-eye');
            icon.classList.toggle('bi-eye-slash');
        });
    });

    // Show info in forgot password modal
    document.getElementById("forgotPasswordModal").addEventListener('show.bs.modal', function () {
        document.getElementById("forgotInfo").classList.remove("d-none");
    });

    // Email validation
    document.addEventListener('DOMContentLoaded', function () {
        const forgotForm = document.querySelector('#forgotPasswordModal form');
        const emailInput = document.getElementById('forgotEmail');
        const toastEl = document.getElementById('customErrorToast');
        const toastMsg = document.getElementById('customErrorMessage');

        if (forgotForm) {
            forgotForm.addEventListener('submit', function (e) {
                const email = emailInput.value.trim();
                const domain = email.split('@')[1]?.toLowerCase();
                const acceptedDomains = ['gmail.com', 'email.com'];
                const acceptedTLDs = ['.vn', '.io', '.me'];

                let isValid = true;
                const emailRegex = /^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$/;

                if (!emailRegex.test(email)) {
                    isValid = false;
                    toastMsg.textContent = 'Invalid email.';
                } else if (!acceptedDomains.includes(domain) && !acceptedTLDs.some(tld => domain.endsWith(tld))) {
                    isValid = false;
                    toastMsg.textContent = 'Only accept gmail.com, email.com, or .vn, .io, .me domains';
                } else if ((domain.startsWith('gmail.') && domain !== 'gmail.com') ||
                           (domain.startsWith('email.') && domain !== 'email.com')) {
                    isValid = false;
                    toastMsg.textContent = 'gmail/email is not valid. Only gmail.com or email.com are accepted.';
                }

                if (!isValid) {
                    e.preventDefault();
                    const toast = new bootstrap.Toast(toastEl);
                    toast.show();
                }
            });
        }
    });
</script>

</body>
</html>
