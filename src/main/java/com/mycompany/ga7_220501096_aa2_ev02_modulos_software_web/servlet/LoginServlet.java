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

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/views/login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String correo = request.getParameter("correo");
        String clave = request.getParameter("clave");

        if (correo == null || correo.trim().isEmpty()
                || clave == null || clave.trim().isEmpty()) {

            request.setAttribute("mensajeError", "Debe ingresar correo y clave.");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            return;
        }

        try {
            Usuario usuario = usuarioDAO.validarLogin(correo.trim(), clave);

            if (usuario != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuarioSesion", usuario);
                session.setMaxInactiveInterval(30 * 60);

                response.sendRedirect(request.getContextPath() + "/views/dashboard.jsp");
            } else {
                request.setAttribute("mensajeError", "Correo o clave incorrectos, o usuario inactivo.");
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            request.setAttribute(
                    "mensajeError",
                    "Error de base de datos. Detalle tecnico: " + e.getMessage()
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