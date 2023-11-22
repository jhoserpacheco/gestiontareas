DROP DATABASE gestiontareas;
CREATE DATABASE gestiontareas;

-- Crear tabla de usuarios
CREATE TABLE usuario (
    id_usuario INT AUTO_INCREMENT  NOT NULL,
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    PRIMARY KEY (id_usuario)
);

-- Crear tabla de tareas
CREATE TABLE tarea (
    id_tarea INT AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    descripcion VARCHAR(255) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE,
    PRIMARY KEY (id_tarea, id_usuario),
    FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);
