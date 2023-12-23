document.addEventListener('DOMContentLoaded', function () {
    // Obtener referencia al contenedor de productos
    var productsContainer = document.getElementById('productsContainer');

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
    var registrarProductoForm = document.getElementById('registrarProductoForm').querySelector('form');
    if (registrarProductoForm) {
        registrarProductoForm.addEventListener('submit', function (event) {
            event.preventDefault();
            var formData = new FormData(registrarProductoForm);
            agregarProducto(formData);
        });
    }

    // Función para agregar un nuevo producto
    function agregarProducto(formData) {
        // Realizar una solicitud al backend para agregar un nuevo producto
        fetch('http://localhost:8080/productos', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
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
    }

    // Mostrar la lista de productos al cargar la página
    mostrarProductos();
