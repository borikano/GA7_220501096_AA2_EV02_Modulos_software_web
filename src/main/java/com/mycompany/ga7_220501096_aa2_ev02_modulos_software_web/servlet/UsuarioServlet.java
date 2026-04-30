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

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("usuarioSesion") == null) {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
            return;
        }

        try {
            List<Usuario> usuarios = usuarioDAO.listarUsuarios();
            request.setAttribute("usuarios", usuarios);
            request.getRequestDispatcher("/views/usuarios.jsp").forward(request, response);

        } catch (SQLException e) {
            request.setAttribute(
                    "mensajeError",
                    "Error al consultar usuarios en la base de datos. Detalle tecnico: " + e.getMessage()
            );
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);

        } catch (ClassNotFoundException e) {
            request.setAttribute(
                    "mensajeError",
                    "Error tecnico: no se encontro el driver de conexion. Detalle: " + e.getMessage()
            );
            request.getRequestDispatcher("/views/error.jsp").forward(request, response);
        }
    }
}