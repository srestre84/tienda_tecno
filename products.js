<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="products.css">
    <title>Mostrar y Agregar Productos</title>
    <style>
        /* Estilos adicionales */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
    </style>
</head>
<body>

<!-- Contenedor para agregar un nuevo producto -->
<div>
    <h2>Agregar Nuevo Producto</h2>
    <form id="formulario-producto">
        <label for="nombre">Nombre del Producto:</label>
        <input type="text" id="nombre" name="nombre" required>

        <label for="categoria">Categoría:</label>
        <input type="text" id="categoria" name="categoria" required>

        <button type="submit" disabled>Agregar Producto</button>
    </form>
</div>

<!-- Contenedor para mostrar productos existentes en una tabla -->
<div>
    <h2>Productos Existenes</h2>
    <p id="mensaje-categoria"></p>
    <div id="productos-container"></div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function () {
    // Obtener referencia al contenedor de productos
    var productsContainer = document.getElementById('productos-container');

    // Función para obtener y mostrar la lista de productos
    function mostrarProductos() {
        // Realizar una solicitud al backend para obtener la lista de productos
        fetch('http://localhost:8080/productos')
            .then(response => response.json())
            .then(data => {
                // Limpiar el contenedor antes de mostrar los nuevos productos
                productsContainer.innerHTML = '';

                // Mostrar cada producto en el contenedor
                data.forEach(producto => {
                    var productoDiv = document.createElement('div');
                    productoDiv.textContent = `Nombre: ${producto.nombre}, Categoría: ${producto.categoria}`;
                    productsContainer.appendChild(productoDiv);
                });
            })
            .catch(error => console.error('Error al obtener la lista de productos:', error));
    }

    // Manejar el evento de envío del formulario para agregar productos
    var formularioProducto = document.getElementById('formulario-producto');
    if (formularioProducto) {
        formularioProducto.addEventListener('submit', function (event) {
            event.preventDefault();
            var formData = new FormData(formularioProducto);
            agregarProducto(formData);
        });
    }

    // Función para agregar un nuevo producto
    function agregarProducto(formData) {
        // Obtener el token de alguna manera (dependiendo de cómo lo almacenes en tu aplicación)
        var token = obtenerToken(); // Implementa esta función según tu lógica

        // Verificar si el token está presente
        if (token) {
            // Realizar una solicitud al backend para agregar un nuevo producto incluyendo el token en el encabezado
            fetch('http://localhost:8080/productos', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`, // Agrega el token al encabezado de autorización
                },
                body: JSON.stringify(Object.fromEntries(formData)),
            })
            .then(response => response.json())
            .then(data => {
                console.log('Respuesta del servidor (Agregar Producto):', data);
                if (data.success) {
                    // Después de agregar un producto con éxito, actualizar la lista de productos
                    mostrarProductos();
                } else {
                    console.error('Error al agregar el producto. Detalles en la consola.');
                }
            })
            .catch(error => console.error('Error en la solicitud:', error));
        } else {
            console.error('Token no presente en la solicitud');
            // Manejar el caso en el que el token no está presente
        }
    }

    // Función de ejemplo para obtener el token (ajústala según tu implementación)
    function obtenerToken() {
        // Implementa la lógica para obtener el token de almacenamiento local, cookies u otro lugar
        // Retorna el token si está presente, de lo contrario, retorna null
        return localStorage.getItem('miToken'); // Ejemplo utilizando localStorage
    }

    // Mostrar la lista de productos al cargar la página
    mostrarProductos();
});
</script>

</body>
</html>
