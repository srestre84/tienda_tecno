<script>
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
        .then(data => {
            console.log('Respuesta del servidor:', data);

            if (data && data.token) {
                // Guardar el token JWT en el almacenamiento local (localStorage)
                localStorage.setItem('jwtToken', data.token);
            }
       
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
            realizarAccion(formData, 'login');
        });
    }
});
</script>

</body>
</html>
