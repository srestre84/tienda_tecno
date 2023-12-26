document.addEventListener('DOMContentLoaded', () => {
    const registroForm = document.querySelector('#registroForm form');
    const loginForm = document.querySelector('#loginForm form');
    const mensajeDiv = document.getElementById('mensaje');

    const backendUrl = "http://localhost:8080";

    function handleErrors(response) {
        if (!response.ok) {
            throw new Error(`Error en la solicitud al servidor. Estado: ${response.status}`);
        }
        const contentType = response.headers.get('content-type');
        return contentType && contentType.includes('application/json') ? response.json() : {};
    }

    function redirectToProductsPage() {
        mensajeDiv.textContent = 'Solicitud exitosa. Redirigiendo...';
        setTimeout(() => {
            window.location.href = 'products.html';
        }, 2000);
    }

    function realizarAccion(formData, endpoint) {
        const apiUrl = `${backendUrl}/${endpoint}`;

        fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(Object.fromEntries(formData)),
            credentials: 'include',
        })
        .then(handleErrors)
        .then(data => {
            console.log('Respuesta del servidor:', data);
            redirectToProductsPage();
        })
        .catch(error => {
            console.error('Error en la solicitud:', error);
            mensajeDiv.textContent = `Error en la conexión con el servidor. Detalles: ${error.message} (${endpoint}).`;
        });
    }

    if (registroForm) {
        registroForm.addEventListener('submit', event => {
            event.preventDefault();
            const formData = new FormData(registroForm);
            realizarAccion(formData, 'usuarios');
        });
    }

    if (loginForm) {
        loginForm.addEventListener('submit', event => {
            event.preventDefault();
            const formData = new FormData(loginForm);
            realizarAccion(formData, 'login');
        });
    }
});



