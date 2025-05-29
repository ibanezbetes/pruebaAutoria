/* ----- Config ----- */
const BASE = "http://localhost:8080/miProyectoBackend/peliculas";

/* ----- Carga inicial (todas) ----- */
document.addEventListener("DOMContentLoaded", () => pedirPeliculas(""));

/* ----- Botonera género ----- */
document.getElementById("botonera").addEventListener("click", e => {
  if (e.target.tagName === "BUTTON") {
    const genero = e.target.dataset.genero;
    pedirPeliculas(genero);            // ?genero=Drama   (o vacío para todas)
  }
});

/* ----- Buscador título ----- */
document.getElementById("formBuscar").addEventListener("submit", e => {
  e.preventDefault();
  const texto = document.getElementById("buscador").value.trim();
  if (texto.length === 0) return;
  buscarPorTitulo(texto);              // ?titulo=ti
});

function buscarPorTitulo(txt) {
  fetch(`${BASE}?titulo=${encodeURIComponent(txt)}`)
    .then(r => r.json())
    .then(pintar)
    .catch(console.error);
}

/* ----- Petición por género o todas ----- */
function pedirPeliculas(genero) {
  const url = genero ? `${BASE}?genero=${encodeURIComponent(genero)}` : BASE;
  fetch(url)
    .then(r => r.json())
    .then(pintar)
    .catch(console.error);
}

/* ----- Pintar tarjetas ----- */
function pintar(lista) {
  const cont = document.getElementById("pelis");
  cont.innerHTML = "";
  if (lista.length === 0) {
    cont.textContent = "No hay resultados";
    return;
  }
  lista.forEach(p => {
    const div = document.createElement("div");
    div.className = "card";
    div.innerHTML = `
      <h3>${p.titulo}</h3>
      <p><strong>Año:</strong> ${p.ano}</p>
      <p><strong>Duración:</strong> ${p.duracion} min</p>
      <p>${p.descripcion}</p>`;
    cont.appendChild(div);
  });
}
