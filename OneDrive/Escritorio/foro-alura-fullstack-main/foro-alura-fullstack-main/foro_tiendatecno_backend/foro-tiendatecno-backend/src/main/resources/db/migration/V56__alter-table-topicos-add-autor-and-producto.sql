ALTER TABLE topicos
CHANGE autor autor_id BIGINT;

ALTER TABLE topicos
CHANGE Producto Producto_id BIGINT;


ALTER TABLE topicos
ADD FOREIGN KEY(autor_id) REFERENCES usuarios(id);

ALTER TABLE topicos
ADD FOREIGN KEY(Producto_id) REFERENCES productos(id);