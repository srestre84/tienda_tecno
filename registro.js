document.addEventListener('DOMContentLoaded', () => {
    var registroForm = document.getElementById('registroForm')?.querySelector('form');
    var loginForm = document.getElementById('loginForm')?.querySelector('form');
    var mensajeDiv = document.getElementById('mensaje');

    var backendUrl = "http://localhost:8080";

    function realizarAccion(formData, endpoint) {
        var apiUrl = `${backendUrl}/${endpoint}`;

        return fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
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
        .catch(error => {
            console.error('Error en la solicitud:', error);
            mensajeDiv.textContent = `Error en la conexión con el servidor. Detalles: ${error.message} (${endpoint}).`;
            throw error; // Propaga el error para manejarlo en el nivel superior
        });
    }

    function realizarRegistro(formData) {
        return realizarAccion(formData, 'usuarios');
    }

    function realizarLogin(formData) {
        return realizarAccion(formData, 'login');
    }

    if (registroForm && mensajeDiv) {
        registroForm.addEventListener('submit', event => {
            event.preventDefault();
            var formData = new FormData(registroForm);

            realizarRegistro(formData)
                .then(data => {
                    console.log('Registro exitoso. ID del usuario:', data.id);

                    if (data.id) {
                        localStorage.setItem('userId', data.id);
                    }

                    mensajeDiv.textContent = 'Solicitud exitosa. Redirigiendo...';
                    setTimeout(() => {
                        window.location.href = 'products.html';
                    }, 2000);
                })
                .catch(error => {
                    console.error('Error en el registro:', error);
                    // Puedes manejar el error de registro aquí, si es necesario
                });
        });
    }

    if (loginForm && mensajeDiv) {
        loginForm.addEventListener('submit', event => {
            event.preventDefault();
            var formData = new FormData(loginForm);

            realizarLogin(formData)
                .then(data => {
                    console.log('Inicio de sesión exitoso. ID del usuario:', data.id);

                    if (data.id) {
                        localStorage.setItem('userId', data.id);
                    }

                    mensajeDiv.textContent = 'Solicitud exitosa. Redirigiendo...';
                    setTimeout(() => {
                        window.location.href = 'products.html';
                    }, 2000);
                })
                .catch(error => {
                    console.error('Error en el inicio de sesión:', error);
                    // Puedes manejar el error de inicio de sesión aquí, si es necesario
                });
        });
    }
});


