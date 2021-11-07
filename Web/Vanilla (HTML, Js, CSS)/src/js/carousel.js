window.onload = () => {
  const slider = document.querySelector(".carousel");
  const sliderItem = slider.querySelectorAll(".carousel .slide");
  const dots = document.querySelector(".carousel .dots");
  const dotsChild = document.querySelector(".dots").getElementsByTagName("li");

  for (const i in Array(sliderItem.length).fill(0)) {
    console.log(sliderItem.length);
    dots.appendChild(document.createElement("li"));
    dotsChild[i].setAttribute("id", i);
    dotsChild[0].classList.add("active");
    dotsChild[i].addEventListener("click", runSlider);
  }

  function runSlider() {
    for (const i in Array(sliderItem.length).fill(0)) {
      sliderItem[i].classList.remove("active");
      sliderItem[this.getAttribute("id")].classList.add("active");
      dotsChild[i].classList.remove("active");
      dotsChild[this.getAttribute("id")].classList.add("active");
    }
  }
};
