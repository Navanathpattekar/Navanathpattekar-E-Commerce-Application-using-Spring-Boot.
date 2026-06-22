document.addEventListener("DOMContentLoaded", () => {

  // ----------------------------
  // --- Hero Carousel ---
  // ----------------------------
  const slides = document.querySelectorAll('.carousel-slide');
  const nextHero = document.querySelector('.next');
  const prevHero = document.querySelector('.prev');
  let currentSlide = 0;

  function showSlide(index) {
    slides.forEach(slide => slide.classList.remove('active'));
    slides[index].classList.add('active');
  }

  function nextSlide() {
    currentSlide = (currentSlide + 1) % slides.length;
    showSlide(currentSlide);
  }

  function prevSlide() {
    currentSlide = (currentSlide - 1 + slides.length) % slides.length;
    showSlide(currentSlide);
  }

  nextHero?.addEventListener('click', nextSlide);
  prevHero?.addEventListener('click', prevSlide);
  setInterval(nextSlide, 5000); // Auto-slide every 5 seconds

  // ----------------------------
  // --- Category Carousel (6 visible, infinite) ---
  // ----------------------------
  const track = document.getElementById('carouselTrack');
  const prevCat = document.querySelector('.carousel-btn.left');
  const nextCat = document.querySelector('.carousel-btn.right');
  const visibleItems = 6; // always show 6 items

  function moveNext() {
    // Animate left
    track.style.transition = 'transform 0.4s ease';
    track.style.transform = `translateX(-${100 / visibleItems}%)`;

    // After animation, move first element to the end
    setTimeout(() => {
      track.appendChild(track.firstElementChild);
      track.style.transition = 'none';
      track.style.transform = 'translateX(0)';
    }, 400);
  }

  function movePrev() {
    // Move last element to start instantly
    track.insertBefore(track.lastElementChild, track.firstElementChild);
    track.style.transition = 'none';
    track.style.transform = `translateX(-${100 / visibleItems}%)`;

    // Animate to normal position
    setTimeout(() => {
      track.style.transition = 'transform 0.4s ease';
      track.style.transform = 'translateX(0)';
    }, 20);
  }

  prevCat?.addEventListener('click', movePrev);
  nextCat?.addEventListener('click', moveNext);

  // ----------------------------
  // --- Shop Link Clicks ---
  // ----------------------------
  const shopLinks = document.querySelectorAll('.category a');
  shopLinks.forEach(link => {
    link.addEventListener('click', (e) => {
      e.preventDefault();
      const href = link.getAttribute('href');
      window.location.href = href;
    });
  });

  // ----------------------------
  // --- Optional: Welcome message ---
  // ----------------------------
  const welcomeMessage = document.getElementById("welcomeMessage");
  if (welcomeMessage) {
    const userData = JSON.parse(localStorage.getItem("signupData"));
    if (userData?.username) {
      welcomeMessage.textContent = `Welcome back, ${userData.username}!`;
    }
  }

  // ----------------------------
  // --- Responsive adjustment ---
  // ----------------------------
  window.addEventListener("resize", () => {
    // No special resize needed for infinite carousel
    // Hero carousel will resize automatically via CSS
  });
});
