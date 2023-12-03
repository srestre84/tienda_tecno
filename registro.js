document.addEventListener('DOMContentLoaded', function () {
    var registroForm = document.getElementById('registroForm').querySelector('form');
    var loginForm = document.getElementById('loginForm').querySelector('form');
    var mensajeDiv = document.getElementById('mensaje');

    // Configuración de la URL del backend
    var backendUrl = "http://localhost:8080";  // Reemplaza con la URL de tu backend en producción

    // Función para realizar el registro o la autenticación
    function realizarAccion(formData, endpoint) {
        var apiUrl = `${backendUrl}/${endpoint}`;

        return fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(Object.fromEntries(formData)),
        })
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Error en la solicitud al servidor. Estado: ' + response.status);
            }
            return response.json();
        })
        .then(function (data) {
            console.log('Respuesta del servidor:', data);

            // Corrección: Corrige la URL de redirección
            window.location.href = 'products.html';
        })
        .catch(function (error) {
            console.error('Error en la solicitud:', error);
            mensajeDiv.textContent = `Error en la conexión con el servidor. Consulta la consola para más detalles (${endpoint}).`;
        });
    }

    // Manejar el evento de envío del formulario de registro
    if (registroForm && mensajeDiv) {
        registroForm.addEventListener('submit', function (event) {
            event.preventDefault();
            // Mejora: Usa FormData directamente sin convertir a un objeto
            var formData = new FormData(registroForm);
            realizarAccion(formData, 'usuarios');
        });
    }

    // Manejar el evento de envío del formulario de inicio de sesión
    if (loginForm && mensajeDiv) {
        loginForm.addEventListener('submit', function (event) {
            event.preventDefault();
            // Mejora: Usa FormData directamente sin convertir a un objeto
            var formData = new FormData(loginForm);
            realizarAccion(formData, 'login');
        });
    }
});

