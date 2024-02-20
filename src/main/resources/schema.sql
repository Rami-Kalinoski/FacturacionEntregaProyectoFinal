DROP TABLE IF EXISTS detalles_ventas;
DROP TABLE IF EXISTS ventas;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS clientes;

CREATE TABLE clientes (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    dni INT,
    nombre VARCHAR (100),
    apellido VARCHAR (100),
    email VARCHAR (150)
);

CREATE TABLE productos (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR (120),
    color VARCHAR (50),
    stock INT,
    precio FLOAT
);

CREATE TABLE ventas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    fecha DATETIME,
    total FLOAT,
    id_cliente BIGINT,

    CONSTRAINT FK_clientes FOREIGN KEY (id_cliente) REFERENCES clientes(id)
);

CREATE TABLE detalles_ventas (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    venta_id BIGINT,
    producto_id BIGINT,
    nombre_producto VARCHAR (120),
    color_producto VARCHAR (50),
    precio_producto FLOAT,
    cantidad_producto INT,

    CONSTRAINT FK_ventas FOREIGN KEY (venta_id) REFERENCES ventas(id),
    CONSTRAINT FK_productos FOREIGN KEY (producto_id) REFERENCES productos(id)
);