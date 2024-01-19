const formulario = document.getElementById("loginForm > form");

const usuarioLogin = document.getElementById("usuarioLogin");
const contrasenaLogin = document.getElementById("contrasenaLogin");


const backendUrl = "http://localhost:8080";

formulario.addEventListener("submit", login);

async function login (e) {
  e.preventDefault();
  
  // agregar los datos correctos (luego se deberia reemplazar por los del formulario)
  const usuario = {
    usuario: "usuario",
    contrasena: "contrasena"
  }


  const respuesta = await fetch(backendUrl + "/login", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(usuario),
            credentials: 'include',
        });


  const datos = await respuesta.json();
  console.log(datos)
}
