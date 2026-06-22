document.getElementById('login-form')?.addEventListener('submit', (e) => {
  const email = document.getElementById('email').value.trim();
  const password = document.getElementById('password').value.trim();

  if (!email || !password) {
    e.preventDefault();
    alert('Email and Password required');
  }
});



  function togglePassword() {
    const passwordInput = document.getElementById("password");

    if (passwordInput.type === "password") {
      passwordInput.type = "text";
    } else {
      passwordInput.type = "password";
    }
  }

