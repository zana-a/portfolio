fetch("js/blogs.json")
  .then((respond) => {
    respond.json().then((blogs) => {
      const template = document.getElementById("blogs");
      blogs.map((x) => {
        template.innerHTML += `
        <a href="#">
          <article>
            <img src="https://picsum.photos/12${Math.floor(
              Math.random() * 10
            )}" alt="${x.title}">
            <div>
              <h3>${x.title}</h3>
              <p>
                ${x.body}
              </p>
            </div>
          </article>
        </a>
      `;
      });
    });
  })
  .catch((err) => {
    console.log(err);
  });
