-- ============================================================
-- Script de base de datos
-- Proyecto: GA7-220501096-AA2-EV02
-- Autor: Luis H. Echeverry O
-- Ficha: ADSO 3118315
-- Base de datos: bbdd_ga7_ev02_web
-- ============================================================

CREATE DATABASE IF NOT EXISTS bbdd_ga7_ev02_web
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

USE bbdd_ga7_ev02_web;

CREATE TABLE IF NOT EXISTS usuario (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    clave VARCHAR(255) NOT NULL,
    estado TINYINT(1) NOT NULL DEFAULT 1,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- ============================================================
-- Nota importante sobre usuarios iniciales
-- ============================================================
-- Este proyecto usa BCrypt para proteger contraseñas.
-- Por esa razón, no se recomienda insertar usuarios con claves
-- en texto plano desde SQL.
--
-- Para cargar usuarios de prueba de forma segura, ejecutar:
--
-- src/main/java/com/mycompany/ga7_220501096_aa2_ev02_modulos_software_web/data/DatosIniciales.java
--
-- Esa clase registra usuarios usando UsuarioDAO y convierte las
-- claves en hashes BCrypt antes de guardarlas en la base de datos.
-- ============================================================

-- Consulta de verificación:
SELECT id, nombre, correo, estado, fecha_creacion
FROM usuario
ORDER BY id DESC;
