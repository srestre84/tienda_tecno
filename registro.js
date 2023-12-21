document.addEventListener('DOMContentLoaded', () => {
    var registroForm = document.getElementById('registroForm').querySelector('form');
    var loginForm = document.getElementById('loginForm').querySelector('form');
    var mensajeDiv = document.getElementById('mensaje');

    var backendUrl = "http://localhost:8080";  // Reemplaza con la URL de tu backend en producción

    function realizarAccion(formData, endpoint) {
        var apiUrl = `${backendUrl}/${endpoint}`;

        return fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(Object.fromEntries(formData)),
            credentials: 'include', // Agrega esto si estás utilizando credenciales
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error en la solicitud al servidor. Estado: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            console.log('Respuesta del servidor:', data);
            window.location.href = 'products.html';
        })
        .catch(error => {
            console.error('Error en la solicitud:', error);
            mensajeDiv.textContent = `Error en la conexión con el servidor. Detalles: ${error.message} (${endpoint}).`;
        });
    }

    if (registroForm && mensajeDiv) {
        registroForm.addEventListener('submit', event => {
            event.preventDefault();
            var formData = new FormData(registroForm);
            realizarAccion(formData, 'usuarios');
        });
    }

    if (loginForm && mensajeDiv) {
        loginForm.addEventListener('submit', event => {
            event.preventDefault();
            var formData = new FormData(loginForm);
            realizarAccion(formData, 'login');
        });
    }
});
