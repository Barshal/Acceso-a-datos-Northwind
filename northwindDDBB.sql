-- Borrar la base de datos si ya existe
DROP DATABASE IF EXISTS northwind;

-- Crear la base de datos
CREATE DATABASE northwind;
USE northwind;

-- Crear tabla productos
CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(254),
    descripcion VARCHAR(254),
    cantidad INT,
    precio DOUBLE
);

-- Crear tabla productos_fav
CREATE TABLE productos_fav (
    id_producto_fav INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);

-- Crear tabla empleados
CREATE TABLE empleados (
    id_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(254)
);

-- Crear tabla pedidos
CREATE TABLE pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,
    id_producto INT,
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto)
);