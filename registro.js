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
            throw error; 
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
    console.log('Tipo de ID del usuario:', typeof data);

    if (typeof data.id === 'number') {
        localStorage.setItem('userId', data.id);
    }

    mensajeDiv.textContent = 'Solicitud exitosa. Redirigiendo...';
    setTimeout(() => {
        window.location.href = 'products.html';
    }, 2000);
})

                .catch(error => {
                    console.error('Error en el registro:', error);
                   
                });
        });
    }

    if (loginForm && mensajeDiv) {
        loginForm.addEventListener('submit', event => {
            event.preventDefault();
            var formData = new FormData(loginForm);

            realizarLogin(formData)
                .then(data => {
                console.log('Tipo de ID del usuario:', typeof data);

               if (typeof data.id === 'number') {
            localStorage.setItem('userId', data.id);
                }

                mensajeDiv.textContent = 'Solicitud exitosa. Redirigiendo...';
           setTimeout(() => {
        window.location.href = 'products.html';
    }, 2000);
})

                .catch(error => {
                    console.error('Error en el inicio de sesión:', error);
          
                });
        });
    }
});


