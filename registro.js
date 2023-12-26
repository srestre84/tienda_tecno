document.addEventListener('DOMContentLoaded', () => {
    var registroForm = document.getElementById('registroForm')?.querySelector('form');
    var loginForm = document.getElementById('loginForm')?.querySelector('form');
    var mensajeDiv = document.getElementById('mensaje');

    var backendUrl = "http://localhost:8080";
    
    function realizarAccion(formData, endpoint, includeToken = false) {
        var apiUrl = `${backendUrl}/${endpoint}`;

        var headers = {
            'Content-Type': 'application/json',
        };

        // Incluir el token en la solicitud si es necesario
        if (includeToken) {
            var token = localStorage.getItem("jwtToken");
            headers['Authorization'] = `Bearer ${token}`;
        }

        return fetch(apiUrl, {
            method: 'POST',
            headers: headers,
            body: JSON.stringify(Object.fromEntries(formData)),
            credentials: 'include',
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error en la solicitud al servidor. Estado: ${response.status}`);
            }

            const contentType = response.headers.get('content-type');
            if (contentType && contentType.includes('application/json')) {
                return response.json();
            } else {
                return {};
            }
        })
        .then(data => {
            console.log('Respuesta del servidor:', data);

            mensajeDiv.textContent = 'Solicitud exitosa. Redirigiendo...';
            setTimeout(() => {
                window.location.href = 'products.html';
            }, 2000);
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
            realizarAccion(formData, 'login').then(response => {
                // Almacenar el token en el almacenamiento local después del inicio de sesión
                localStorage.setItem("jwtToken", response.token);
            });
        });
    }

    // Función para agregar productos con el token incluido
    function agregarProducto(event) {
        event.preventDefault();

        const nombre = document.getElementById('nombre').value;
        const categoria = document.getElementById('categoria').value;

        realizarAccion({ nombre, categoria }, 'productos', true);
    }

    // Agregar un listener al formulario o botón de agregar productos
    var formularioProducto = document.getElementById('formulario-producto');
    if (formularioProducto) {
        formularioProducto.addEventListener('submit', agregarProducto);
    }
});



