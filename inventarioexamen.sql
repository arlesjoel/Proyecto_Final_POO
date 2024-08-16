-- Crear base de datos
CREATE DATABASE InventarioExamen;
GO
USE InventarioExamen;
GO
-- Tabla de Categorías
CREATE TABLE Categorias (
    id_categoria INT PRIMARY KEY IDENTITY,
    nombre VARCHAR(50) NOT NULL
);

-- Tabla de Subcategorías
CREATE TABLE Subcategorias (
    id_subcategoria INT PRIMARY KEY IDENTITY,
    nombre VARCHAR(50) NOT NULL,
    id_categoria INT,
    FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria)
);

-- Tabla de Proveedores
CREATE TABLE Proveedores (
    id_proveedor INT PRIMARY KEY IDENTITY,
    nombre VARCHAR(100) NOT NULL,
    contacto VARCHAR(100)
);

-- Tabla de Productos
CREATE TABLE Productos (
    id_producto INT PRIMARY KEY IDENTITY,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    id_categoria INT,
    id_subcategoria INT,
    FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria),
    FOREIGN KEY (id_subcategoria) REFERENCES Subcategorias(id_subcategoria)
);

-- Tabla de Usuarios
CREATE TABLE Usuarios (
    id_usuario INT PRIMARY KEY IDENTITY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    rol varchar (25) not null
);

-- Tabla de Stock
CREATE TABLE Stock (
    id_transaccion INT PRIMARY KEY IDENTITY,
    id_producto INT,
    cantidad INT NOT NULL,
    tipo varchar(25) not null,
    id_usuario INT,
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto),
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

INSERT INTO Productos (codigo, nombre, id_categoria, id_subcategoria) VALUES
('0001', 'LONA CRUDA NATURAL', '3', '7'),
('0002', 'LONA ROJA', '3', '8'),
('0003', 'LONA NEGRA', '3', '8'),
('0004', 'LONA AZUL', '3', '8'),
('0005', 'LONA AMARILLA', '3', '8'),
('0006', 'MANTA NATURAL', '3', '9'),
('0007', 'MANTA DE ROJA', '3', '10'),
('0008', 'MANTA NEGRA', '3', '10'),
('0009', 'MANTA AZUL', '3', '10'),
('0010', 'MANTA AMARILLA', '3', '10'),
('0011', 'DACRON CHINO', '3', '11'),
('0012', 'DACRON AMERICANO', '3', '11'),
('0013', 'IMPERMEABLES', '3', '12'),
('0014', 'TELA DE GALLETA', '3', '13'),
('0015', 'ZIPER ROJO','10', '23'),
('0016', 'ZIPER AZUL','10', '23'),
('0017', 'ZIPER NEGRO','10', '23'),
('0018', 'ZIPER METALICO GRUESO','10', '24'),
('0019', 'CUERO CAFÉ', '8', '20'),
('0020', 'CUERO NEGRO', '8', '20'),
('0021', 'SEMICUERO CAFÉ', '8', '21'),
('0022', 'SEMICUERO NEGRO', '8', '21'),
('0023', 'HILO BEIGE 3 CABOS', '5', '15'),
('0024', 'HILO ROJO 3 CABOS', '5', '15'),
('0025', 'HILO AZUL 3 CABOS', '5', '15'),
('0026', 'HILO AMARILLO 3 CABOS', '5', '15'),
('0027', 'HILO BEIGE DELGADO', '5', '14'),
('0028', 'HILO ROJO DELGADO', '5', '14'),
('0029', 'HILO NEGRO DELGADO', '5', '14'),
('0030', 'HILO AZUL DELGADO', '5', '14'),
('0031', 'HILO AMARILLO DELGADO', '5', '14'),
('0032', 'AISLANTE TERMICO', '9', '0'),
('0033', 'CINTA NY BLANCO', '6', '16'),
('0034', 'CINTA NY NEGRO', '6', '16'),
('0035', 'CINTA AL BLANCO', '6', '17'),
('0036', 'CINTA AL NEGRO', '6', '17'),
('0037', 'MAQUINA PLANA', '7', '18'),
('0038', 'MAQUINA OVERLOCK', '7', '18'),
('0040', 'MAQUINA DE CORTE DE TELA', '7', '19');

/*

INSERT INTO Productos (codigo, nombre) VALUES
('0001', 'LONA CRUDA NATURAL'),
('0002', 'LONA ROJA'),
('0003', 'LONA NEGRA'),
('0004', 'LONA AZUL'),
('0005', 'LONA AMARILLA'),
('0006', 'MANTA NATURAL'),
('0007', 'MANTA DE ROJA'),
('0008', 'MANTA NEGRA'),
('0009', 'MANTA AZUL'),
('0010', 'MANTA AMARILLA'),
('0011', 'DACRON CHINO'),
('0012', 'DACRON AMERICANO'),
('0013', 'IMPERMEABLES'),
('0014', 'TELA DE GALLETA'),
('0015', 'ZIPER ROJO'),
('0016', 'ZIPER AZUL'),
('0017', 'ZIPER NEGRO'),
('0018', 'ZIPER METALICO GRUESO'),
('0019', 'CUERO CAFÉ'),
('0020', 'CUERO NEGRO'),
('0021', 'SEMICUERO CAFÉ'),
('0022', 'SEMICUERO NEGRO'),
('0023', 'HILO BEIGE 3 CABOS'),
('0024', 'HILO ROJO 3 CABOS'),
('0025', 'HILO AZUL 3 CABOS'),
('0026', 'HILO AMARILLO 3 CABOS'),
('0027', 'HILO BEIGE DELGADO'),
('0028', 'HILO ROJO DELGADO'),
('0029', 'HILO NEGRO DELGADO'),
('0030', 'HILO AZUL DELGADO'),
('0031', 'HILO AMARILLO DELGADO'),
('0032', 'AISLANTE TERMICO'),
('0033', 'CINTA NY BLANCO'),
('0034', 'CINTA NY NEGRO'),
('0035', 'CINTA AL BLANCO'),
('0036', 'CINTA AL NEGRO'),
('0037', 'MAQUINA PLANA'),
('0038', 'MAQUINA OVERLOCK'),
('0040', 'MAQUINA DE CORTE DE TELA');

*/