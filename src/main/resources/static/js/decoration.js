/*function addToCart(title, description, price) {
  const cart = JSON.parse(localStorage.getItem('cart')) || [];
  const item = { title, description, price, quantity: 1 };
  cart.push(item);
  localStorage.setItem('cart', JSON.stringify(cart));
  alert(`${title} added to cart.`);
}*/

// Search filter
function filterProducts() {
  const input = document.getElementById("searchInput").value.toLowerCase();
  const cards = document.querySelectorAll(".card");
  cards.forEach(card => {
    const name = card.querySelector("h3").textContent.toLowerCase();
    const desc = card.querySelector("p").textContent.toLowerCase();
    card.style.display = name.includes(input) || desc.includes(input) ? "block" : "none";
  });
}

// Scroll-to-top button
const scrollBtn = document.getElementById("scrollToTopBtn");
window.onscroll = () => scrollBtn.style.display = (window.scrollY > 100) ? "block" : "none";
scrollBtn.onclick = () => window.scrollTo({ top: 0, behavior: "smooth" });
