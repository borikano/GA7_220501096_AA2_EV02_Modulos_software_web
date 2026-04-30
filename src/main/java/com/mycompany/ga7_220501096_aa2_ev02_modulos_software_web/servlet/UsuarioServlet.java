/**
    Proyecto: GA7-220501096-AA2-EV02
    Autor: Luis H. Echeverry O
    ADSO: 3118315
    Modulo: Login web con JSP, Servlets, JDBC y MySQL
**/

package com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.servlet;

import com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.dao.UsuarioDAO;
import com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.modelo.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UsuarioServlet", urlPatterns = {"/usuarios"})
public class UsuarioServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!sesionActiva(request)) {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
            return;
        }

        String accion = request.getParameter("accion");

        if (accion == null || accion.trim().isEmpty()) {
            accion = "listar";
        }

        try {
            switch (accion) {
                case "nuevo":
                    mostrarFormularioNuevo(request, response);
                    break;

                case "editar":
                    mostrarFormularioEditar(request, response);
                    break;

                default:
                    listarUsuarios(request, response);
                    break;
            }

        } catch (SQLException e) {
            mostrarError(request, response, "Error de base de datos. Detalle tecnico: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            mostrarError(request, response, "Error tecnico: no se encontro el driver de conexion. Detalle: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (!sesionActiva(request)) {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
            return;
        }

        String accion = request.getParameter("accion");

        if (accion == null || accion.trim().isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/usuarios");
            return;
        }

        try {
            switch (accion) {
                case "guardar":
                    guardarUsuario(request, response);
                    break;

                case "actualizar":
                    actualizarUsuario(request, response);
                    break;

                case "desactivar":
                    desactivarUsuario(request, response);
                    break;

                default:
                    response.sendRedirect(request.getContextPath() + "/usuarios");
                    break;
            }

        } catch (SQLException e) {
            mostrarError(request, response, "Error de base de datos. Detalle tecnico: " + e.getMessage());

        } catch (ClassNotFoundException e) {
            mostrarError(request, response, "Error tecnico: no se encontro el driver de conexion. Detalle: " + e.getMessage());
        }
    }

    private boolean sesionActiva(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return session != null && session.getAttribute("usuarioSesion") != null;
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ClassNotFoundException, ServletException, IOException {

        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/views/usuarios.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("modoFormulario", "nuevo");
        request.getRequestDispatcher("/views/formularioUsuario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ClassNotFoundException, ServletException, IOException {

        int id = obtenerId(request);

        if (id <= 0) {
            request.setAttribute("mensajeError", "ID de usuario no valido.");
            listarUsuarios(request, response);
            return;
        }

        Usuario usuario = usuarioDAO.buscarUsuarioPorId(id);

        if (usuario == null) {
            request.setAttribute("mensajeError", "No se encontro el usuario solicitado.");
            listarUsuarios(request, response);
            return;
        }

        request.setAttribute("modoFormulario", "editar");
        request.setAttribute("usuarioEditar", usuario);
        request.getRequestDispatcher("/views/formularioUsuario.jsp").forward(request, response);
    }

    private void guardarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ClassNotFoundException, ServletException, IOException {

        String nombre = limpiarTexto(request.getParameter("nombre"));
        String correo = limpiarTexto(request.getParameter("correo"));
        String clave = request.getParameter("clave");

        if (nombre.isEmpty() || correo.isEmpty() || clave == null || clave.trim().isEmpty()) {
            request.setAttribute("mensajeError", "Todos los campos son obligatorios.");
            request.setAttribute("modoFormulario", "nuevo");
            request.getRequestDispatcher("/views/formularioUsuario.jsp").forward(request, response);
            return;
        }

        if (usuarioDAO.existeCorreo(correo)) {
            request.setAttribute("mensajeError", "El correo ya se encuentra registrado.");
            request.setAttribute("modoFormulario", "nuevo");
            request.getRequestDispatcher("/views/formularioUsuario.jsp").forward(request, response);
            return;
        }

        Usuario usuario = new Usuario(nombre, correo, clave);
        boolean registrado = usuarioDAO.registrarUsuario(usuario);

        if (registrado) {
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } else {
            request.setAttribute("mensajeError", "No fue posible registrar el usuario.");
            request.setAttribute("modoFormulario", "nuevo");
            request.getRequestDispatcher("/views/formularioUsuario.jsp").forward(request, response);
        }
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ClassNotFoundException, ServletException, IOException {

        int id = obtenerId(request);
        String nombre = limpiarTexto(request.getParameter("nombre"));
        String correo = limpiarTexto(request.getParameter("correo"));
        boolean estado = "1".equals(request.getParameter("estado"));

        if (id <= 0) {
            request.setAttribute("mensajeError", "ID de usuario no valido.");
            listarUsuarios(request, response);
            return;
        }

        if (nombre.isEmpty() || correo.isEmpty()) {
            Usuario usuario = usuarioDAO.buscarUsuarioPorId(id);
            request.setAttribute("mensajeError", "Nombre y correo son obligatorios.");
            request.setAttribute("modoFormulario", "editar");
            request.setAttribute("usuarioEditar", usuario);
            request.getRequestDispatcher("/views/formularioUsuario.jsp").forward(request, response);
            return;
        }

        if (usuarioDAO.existeCorreoEnOtroUsuario(correo, id)) {
            Usuario usuario = usuarioDAO.buscarUsuarioPorId(id);
            request.setAttribute("mensajeError", "El correo ya pertenece a otro usuario.");
            request.setAttribute("modoFormulario", "editar");
            request.setAttribute("usuarioEditar", usuario);
            request.getRequestDispatcher("/views/formularioUsuario.jsp").forward(request, response);
            return;
        }

        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre(nombre);
        usuario.setCorreo(correo);
        usuario.setEstado(estado);

        boolean actualizado = usuarioDAO.actualizarUsuario(usuario);

        if (actualizado) {
            response.sendRedirect(request.getContextPath() + "/usuarios");
        } else {
            request.setAttribute("mensajeError", "No fue posible actualizar el usuario.");
            listarUsuarios(request, response);
        }
    }

    private void desactivarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ClassNotFoundException, ServletException, IOException {

        int id = obtenerId(request);

        if (id <= 0) {
            request.setAttribute("mensajeError", "ID de usuario no valido.");
            listarUsuarios(request, response);
            return;
        }

        usuarioDAO.eliminarUsuario(id);
        response.sendRedirect(request.getContextPath() + "/usuarios");
    }

    private int obtenerId(HttpServletRequest request) {
        String idTexto = request.getParameter("id");

        if (idTexto == null || idTexto.trim().isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(idTexto);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private String limpiarTexto(String texto) {
        if (texto == null) {
            return "";
        }

        return texto.trim();
    }

    private void mostrarError(HttpServletRequest request, HttpServletResponse response, String mensaje)
            throws ServletException, IOException {

        request.setAttribute("mensajeError", mensaje);
        request.getRequestDispatcher("/views/error.jsp").forward(request, response);
    }
}