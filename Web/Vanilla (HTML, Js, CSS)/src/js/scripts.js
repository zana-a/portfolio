// Menu button
const toggleBtn = document.querySelector("#mob_toggle");
const menu = document.querySelector("nav .mob");

toggleBtn.addEventListener("click", () => {
  menu.classList.toggle("open");
  toggleBtn.classList.toggle("c-white");
});

// Footer copyright year
const thisYear = document.querySelector(".this-year");
thisYear.innerHTML = new Date().getFullYear();
