
function filterProducts() {
  const input = document.getElementById("searchInput").value.toLowerCase();
  const cards = document.querySelectorAll(".card");

  cards.forEach(card => {
    const name = card.querySelector("h3").textContent.toLowerCase();
    const desc = card.querySelector("p").textContent.toLowerCase();

    if (name.includes(input) || desc.includes(input)) {
      card.style.display = "block";
    } else {
      card.style.display = "none";
    }
  });
}


// Scroll-to-top button
const scrollBtn = document.getElementById("scrollToTopBtn");

window.onscroll = () => {
  scrollBtn.style.display = (window.scrollY > 100) ? "block" : "none";
};

scrollBtn.onclick = () => {
  window.scrollTo({ top: 0, behavior: "smooth" });
};
