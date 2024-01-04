ALTER TABLE usuarios
ADD COLUMN
usuario VARCHAR(30);

UPDATE usuarios
SET usuario = CONCAT('usuario', id);

ALTER TABLE usuarios
MODIFY COLUMN usuario VARCHAR(30) UNIQUE NOT NULL;