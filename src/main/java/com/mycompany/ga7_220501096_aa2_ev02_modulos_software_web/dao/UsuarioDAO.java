/**
    Proyecto: GA7-220501096-AA2-EV02
    Autor: Luis H. Echeverry O
    ADSO: 3118315
    Modulo: Login web con JSP, Servlets, JDBC y MySQL
**/

package com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.dao;

import com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.config.Conexion;
import com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.modelo.Usuario;
import com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.util.SeguridadClave;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public boolean registrarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO usuario (nombre, correo, clave, estado) VALUES (?, ?, ?, ?)";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            String claveHash = SeguridadClave.generarHash(usuario.getClave());

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getCorreo());
            statement.setString(3, claveHash);
            statement.setBoolean(4, usuario.isEstado());

            return statement.executeUpdate() > 0;
        }
    }

    public Usuario validarLogin(String correo, String clavePlano) throws SQLException, ClassNotFoundException {
        String sql = "SELECT id, nombre, correo, clave, estado, fecha_creacion "
                + "FROM usuario "
                + "WHERE correo = ? AND estado = 1";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, correo);

            try (ResultSet resultado = statement.executeQuery()) {
                if (resultado.next()) {
                    String claveHash = resultado.getString("clave");

                    if (SeguridadClave.validarClave(clavePlano, claveHash)) {
                        return mapearUsuario(resultado);
                    }
                }
            }
        }

        return null;
    }

    public List<Usuario> listarUsuarios() throws SQLException, ClassNotFoundException {
        List<Usuario> usuarios = new ArrayList<>();

        String sql = "SELECT id, nombre, correo, clave, estado, fecha_creacion "
                + "FROM usuario "
                + "ORDER BY id DESC";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultado = statement.executeQuery()) {

            while (resultado.next()) {
                usuarios.add(mapearUsuario(resultado));
            }
        }

        return usuarios;
    }

    public Usuario buscarUsuarioPorId(int id) throws SQLException, ClassNotFoundException {
        String sql = "SELECT id, nombre, correo, clave, estado, fecha_creacion "
                + "FROM usuario "
                + "WHERE id = ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultado = statement.executeQuery()) {
                if (resultado.next()) {
                    return mapearUsuario(resultado);
                }
            }
        }

        return null;
    }

    public boolean actualizarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE usuario "
                + "SET nombre = ?, correo = ?, estado = ? "
                + "WHERE id = ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, usuario.getNombre());
            statement.setString(2, usuario.getCorreo());
            statement.setBoolean(3, usuario.isEstado());
            statement.setInt(4, usuario.getId());

            return statement.executeUpdate() > 0;
        }
    }

    public boolean actualizarClave(int id, String nuevaClave) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE usuario SET clave = ? WHERE id = ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            String claveHash = SeguridadClave.generarHash(nuevaClave);

            statement.setString(1, claveHash);
            statement.setInt(2, id);

            return statement.executeUpdate() > 0;
        }
    }

    public boolean eliminarUsuario(int id) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE usuario SET estado = 0 WHERE id = ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        }
    }

    public boolean existeCorreo(String correo) throws SQLException, ClassNotFoundException {
        String sql = "SELECT id FROM usuario WHERE correo = ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, correo);

            try (ResultSet resultado = statement.executeQuery()) {
                return resultado.next();
            }
        }
    }

    public boolean existeCorreoEnOtroUsuario(String correo, int idUsuario) throws SQLException, ClassNotFoundException {
        String sql = "SELECT id FROM usuario WHERE correo = ? AND id <> ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement statement = conexion.prepareStatement(sql)) {

            statement.setString(1, correo);
            statement.setInt(2, idUsuario);

            try (ResultSet resultado = statement.executeQuery()) {
                return resultado.next();
            }
        }
    }

    private Usuario mapearUsuario(ResultSet resultado) throws SQLException {
        return new Usuario(
                resultado.getInt("id"),
                resultado.getString("nombre"),
                resultado.getString("correo"),
                resultado.getString("clave"),
                resultado.getBoolean("estado"),
                resultado.getTimestamp("fecha_creacion")
        );
    }
}