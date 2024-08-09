-- Crear base de datos
CREATE DATABASE InventarioDB;
USE InventarioDB;

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
    descripcion TEXT,
    id_categoria INT,
    id_subcategoria INT,
    nivel_minimo INT,
    nivel_maximo INT,
    FOREIGN KEY (id_categoria) REFERENCES Categorias(id_categoria),
    FOREIGN KEY (id_subcategoria) REFERENCES Subcategorias(id_subcategoria)
);

-- Tabla de Usuarios
CREATE TABLE Usuarios (
    id_usuario INT PRIMARY KEY IDENTITY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    -- rol ENUM('administrador', 'encargado') NOT NULL
    rol varcharv (25) not null
);

-- Tabla de Stock
CREATE TABLE Stock (
    id_transaccion INT PRIMARY KEY IDENTITY,
    id_producto INT,
    cantidad INT NOT NULL,
    -- tipo ENUM('entrada', 'salida') NOT NULL,
    tipo varchar(25) not null,
    id_usuario INT,
    fecha_hora DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_producto) REFERENCES Productos(id_producto),
    FOREIGN KEY (id_usuario) REFERENCES Usuarios(id_usuario)
);

