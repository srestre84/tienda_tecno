document.addEventListener('DOMContentLoaded', function () {
    
    //Se definen las variables  del DOM, La razón por la que se están utilizando ambas formas juntas 
    //es que getElementById se utiliza para obtener una referencia al elemento principal
    // con el ID 'registroForm', y luego querySelector se utiliza para buscar un elemento form
    // específico dentro del respectivo formulario
    let registroForm = document.getElementById('registroForm').querySelector('form');
    let loginForm = document.getElementById('loginForm').querySelector('form');
    let mensajeDiv = document.getElementById('mensaje');

    // Configuración de la URL del backend
    let backendUrl = "http://localhost:8080";  // Reemplaza con la URL de tu backend en producción

    // Función para realizar el registro o la autenticación
    function realizarAccion(formData, endpoint) {
        let apiUrl = `${backendUrl}/${endpoint}`;

    //En conjunto, esta solicitud POST está siendo configurada para enviar datos al servidor en formato JSON, y la promesa resultante de fetch es la que se está devolviendo. 
    //La cadena JSON con los datos del formulario se enviará como parte del cuerpo de la solicitud.   
        return fetch(apiUrl, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(Object.fromEntries(formData)),

     //Luego utilizo las promesas para manejo de errores y analizar el json que envia el servidor       
        })
        .then(function (response) {
            if (!response.ok) {
                throw new Error('Error en la solicitud al servidor. Estado: ' + response.status);
            }
            return response.json();
        })
        .then(function (data) {
            console.log('Respuesta del servidor:', data);

           // despues de realizar el registro me voy para la url products.html
            window.location.href = 'products.html';
        })
        .catch(function (error) {
            console.error('Error en la solicitud:', error);
            mensajeDiv.textContent = `Error en la conexión con el servidor. Consulta la consola para más detalles (${endpoint}).`;
        });
    }

    // Manejar el evento de envío del formulario de registro de usuarios creando un nuevo objeto 
    // con los datos registrados 
    if (registroForm && mensajeDiv) {
        registroForm.addEventListener('submit', function (event) {
            event.preventDefault();
           
            let formData = new FormData(registroForm);
            realizarAccion(formData, 'usuarios');
        });
    }

    // Manejar el evento de envío del formulario de inicio de sesión
    // con los datos registrados 
    if (loginForm && mensajeDiv) {
        loginForm.addEventListener('submit', function (event) {
            event.preventDefault();
           
            let formData = new FormData(loginForm);
            realizarAccion(formData, 'login');
        });
    }
});


