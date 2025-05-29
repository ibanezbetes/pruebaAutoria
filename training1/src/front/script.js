/* ---------- CONFIG ---------- */
const BASE = "http://localhost:8080/miProyectoBackend/Controller";

/* ---------- DOM caché ---------- */
const $ = id => document.getElementById(id);

const loginMail   = $("loginMail");
const loginPass   = $("loginPass");
const loginOut    = $("loginOut");

const hotelNombre    = $("hotelNombre");
const hotelDireccion = $("hotelDireccion");
const hotelOut       = $("hotelOut");

const selGenero = $("selGenero");
const pelisOut  = $("pelisOut");

const searchTxt = $("searchTxt");
const searchOut = $("searchOut");

const comPelId = $("comPelId");
const comTexto = $("comTexto");
const comAutor = $("comAutor");
const comOut   = $("comOut");

/* ---------- util para mostrar JSON bonito ---------- */
function show(outEl, data){
  outEl.innerHTML = Array.isArray(data)
    ? data.map(toCard).join("")
    : `<pre>${JSON.stringify(data,null,2)}</pre>`;
}
function toCard(p){
  return `<div class="card">
            <strong>${p.titulo||p.nombre||"–"}</strong><br>
            ${p.descripcion||p.direccion||p.texto||""}
          </div>`;
}

/* ---------- LOGIN (POST) ----------------------------- */
$("btnLogin").onclick = () => {
  fetch(BASE, {
    method:'POST',
    headers:{'Content-Type':'application/x-www-form-urlencoded'},
    body:`ACTION=USUARIO.LOGIN&email=${encodeURIComponent(loginMail.value)}&pass=${encodeURIComponent(loginPass.value)}`
  })
  .then(r=>r.json()).then(d=>show(loginOut,d));
};

/* ---------- ALTA HOTEL (POST) ------------------------ */
$("btnHotel").onclick = () => {
  fetch(BASE, {
    method:'POST',
    headers:{'Content-Type':'application/x-www-form-urlencoded'},
    body:`ACTION=HOTEL.ADD&nombre=${encodeURIComponent(hotelNombre.value)}&direccion=${encodeURIComponent(hotelDireccion.value)}`
  })
  .then(r=>r.json()).then(d=>show(hotelOut,d));
};

/* ---------- PELIS POR GÉNERO (GET) ------------------- */
$("btnGenero").onclick = () => {
  fetch(`${BASE}?ACTION=PELICULA.FIND&genero=${encodeURIComponent(selGenero.value)}`)
    .then(r=>r.json()).then(d=>show(pelisOut,d));
};

/* ---------- BUSCADOR TÍTULO (GET) -------------------- */
$("btnSearch").onclick = () => {
  fetch(`${BASE}?ACTION=PELICULA.SEARCH&search=${encodeURIComponent(searchTxt.value)}`)
    .then(r=>r.json()).then(d=>show(searchOut,d));
};

/* ---------- COMENTARIOS ------------------------------ */
$("btnComAdd").onclick = () => {
  fetch(BASE, {
    method:'POST',
    headers:{'Content-Type':'application/x-www-form-urlencoded'},
    body:`ACTION=COMENTARIO.ADD&modo=ADD&idPel=${comPelId.value}&texto=${encodeURIComponent(comTexto.value)}&autor=${encodeURIComponent(comAutor.value)}`
  })
  .then(r=>r.json()).then(d=>show(comOut,d));
};

$("btnComList").onclick = () => {
  fetch(`${BASE}?ACTION=COMENTARIO.LIST&modo=LIST&idPel=${comPelId.value}`)
    .then(r=>r.json()).then(d=>show(comOut,d));
};
