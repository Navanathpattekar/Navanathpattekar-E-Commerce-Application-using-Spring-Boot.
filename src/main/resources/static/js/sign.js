document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('signup-form');
  const un = document.getElementById('username');
  const pass = document.getElementById('password');
  const cpass = document.getElementById('Cpassword');
  const num = document.getElementById('num');
  const mail = document.getElementById('mail');
  const genderRadios = document.getElementsByName('gender');

  const unError = document.getElementById('usernameError');
  const passError = document.getElementById('passwordError');
  const cpassError = document.getElementById('CpasswordError');
  const mailError = document.getElementById('mailError');
  const numError = document.getElementById('numError');
  const genError = document.getElementById('genderError');

  form.addEventListener('submit', (e) => {
    // Clear previous errors
    unError.textContent = "";
    passError.textContent = "";
    cpassError.textContent = "";
    mailError.textContent = "";
    numError.textContent = "";
    genError.textContent = "";

    let isvalid = true;

    // Username validation
    const usernamevalue = un.value.trim();
    if (!usernamevalue) {
      unError.textContent = "Username cannot be empty";
      isvalid = false;
    } else if (usernamevalue.length < 3) {
      unError.textContent = "Username must be at least 3 characters";
      isvalid = false;
    } else if (usernamevalue.length > 15) {
      unError.textContent = "Username cannot be more than 15 characters";
      isvalid = false;
    }

    // Password validation
    const passvalue = pass.value;
    if (passvalue.length < 6) {
      passError.textContent = "Password must be at least 6 characters";
      isvalid = false;
    }

    // Confirm password validation
    if (cpass.value !== passvalue) {
      cpassError.textContent = "Passwords do not match";
      isvalid = false;
    }

    // Email validation
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(mail.value)) {
      mailError.textContent = "Please enter a valid email address";
      isvalid = false;
    }

    // Phone number validation
    const phoneValue = num.value.trim();
    if (phoneValue.length < 10 || isNaN(phoneValue)) {
      numError.textContent = "Enter a valid phone number";
      isvalid = false;
    }

    // Gender validation
    let genderSelected = false;
    for (const radio of genderRadios) {
      if (radio.checked) {
        genderSelected = true;
        break;
      }
    }
    if (!genderSelected) {
      genError.textContent = "Please select your gender";
      isvalid = false;
    }

    // Final check
    if (!isvalid) {
      e.preventDefault(); // ❗ Stop form submission if invalid
    }
    // ✅ Otherwise, let form submit naturally to /signup (Spring will handle saving)
  });
});


document.addEventListener("DOMContentLoaded", () => {
  const successMsg = document.querySelector("div[th\\:if='${success}']");
  const errorMsg = document.querySelector("div[th\\:if='${error}']");

  // If success or error message is rendered, show popup
  const successText = successMsg?.textContent.trim();
  const errorText = errorMsg?.textContent.trim();

  if (successText) {
    alert(successText);
  }

  if (errorText) {
    alert(errorText);
  }
});
