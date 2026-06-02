/**
    Proyecto: GA7-220501096-AA2-EV02
    Autor: Luis H. Echeverry O
    ADSO: 3118315
    Modulo: Login web con JSP, Servlets, JDBC y MySQL
**/


package com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.data;

import com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.dao.UsuarioDAO;
import com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.modelo.Usuario;
import java.sql.SQLException;

public class DatosIniciales {

    public static void main(String[] args) {

        UsuarioDAO usuarioDAO = new UsuarioDAO();

        Usuario[] usuarios = {
            new Usuario("Sofia Herrera", "usuario.web.01@example.com", "Sofia123*"),
            new Usuario("Daniel Castro", "usuario.web.03@example.com", "Daniel123*"),
            new Usuario("Valentina Gomez", "usuario.web.04@example.com", "Valentina123*"),
            new Usuario("Mateo Ramirez", "usuario.web.05@example.com", "Mateo123*"),
            new Usuario("Isabella Morales", "usuario.web.06@example.com", "Isabella123*"),
            new Usuario("Sebastian Vargas", "usuario.web.07@example.com", "Sebastian123*"),
            new Usuario("Natalia Pardo", "usuario.web.08@example.com", "Natalia123*"),
            new Usuario("Felipe Cardenas", "usuario.web.09@example.com", "Felipe123*"),
            new Usuario("Gabriela Rios", "usuario.web.10@example.com", "Gabriela123*"),
            new Usuario("Tomas Navarro", "usuario.web.11@example.com", "Tomas123*")
        };

        System.out.println("===== CARGA DE DATOS INICIALES =====");

        for (Usuario usuario : usuarios) {
            try {
                if (usuarioDAO.existeCorreo(usuario.getCorreo())) {
                    System.out.println("Ya existe: " + usuario.getCorreo());
                } else {
                    boolean registrado = usuarioDAO.registrarUsuario(usuario);

                    if (registrado) {
                        System.out.println("Registrado: " + usuario.getNombre() + " - " + usuario.getCorreo());
                    } else {
                        System.out.println("No se pudo registrar: " + usuario.getCorreo());
                    }
                }
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Error al registrar usuario: " + usuario.getCorreo());
                System.out.println("Detalle: " + e.getMessage());
            }
        }

        System.out.println("===== CARGA FINALIZADA =====");
    }
}
