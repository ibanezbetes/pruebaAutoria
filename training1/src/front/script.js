/* Ajusta BASE_URL si cambias WAR o puerto */
const BASE_URL = "http://localhost:8080/miProyectoBackend/Controller";

/* ------------ util para mostrar JSON bonito ------------- */
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

/* ------------ LOGIN ------------------------------------- */
document.getElementById("btnLogin").onclick = () =>{
  const email = document.getElementById("loginMail").value;
  const pass  = document.getElementById("loginPass").value;
  fetch(`${BASE_URL}?ACTION=USUARIO.LOGIN&email=${email}&pass=${pass}`)
   .then(r=>r.json()).then(d=>show(loginOut,d));
};

/* ------------ ALTA HOTEL -------------------------------- */
document.getElementById("btnHotel").onclick = () =>{
  const n = hotelNombre.value, d = hotelDireccion.value;
  fetch(`${BASE_URL}?ACTION=HOTEL.ADD&nombre=${encodeURIComponent(n)}&direccion=${encodeURIComponent(d)}`)
   .then(r=>r.json()).then(d=>show(hotelOut,d));
};

/* ------------ PELIS POR GÉNERO -------------------------- */
document.getElementById("btnGenero").onclick = () =>{
  const g = selGenero.value;
  fetch(`${BASE_URL}?ACTION=PELICULA.FIND&genero=${encodeURIComponent(g)}`)
   .then(r=>r.json()).then(d=>show(pelisOut,d));
};

/* ------------ BUSCADOR TÍTULO --------------------------- */
document.getElementById("btnSearch").onclick = () =>{
  const q = searchTxt.value;
  fetch(`${BASE_URL}?ACTION=PELICULA.SEARCH&search=${encodeURIComponent(q)}`)
   .then(r=>r.json()).then(d=>show(searchOut,d));
};

/* ------------ COMENTARIOS ------------------------------- */
btnComAdd.onclick = () =>{
  const id = comPelId.value, txt = comTexto.value, aut = comAutor.value;
  fetch(`${BASE_URL}?ACTION=COMENTARIO.ADD&modo=ADD&idPel=${id}&texto=${encodeURIComponent(txt)}&autor=${encodeURIComponent(aut)}`)
   .then(r=>r.json()).then(d=>show(comOut,d));
};
btnComList.onclick = () =>{
  const id = comPelId.value;
  fetch(`${BASE_URL}?ACTION=COMENTARIO.LIST&modo=LIST&idPel=${id}`)
   .then(r=>r.json()).then(d=>show(comOut,d));
};
