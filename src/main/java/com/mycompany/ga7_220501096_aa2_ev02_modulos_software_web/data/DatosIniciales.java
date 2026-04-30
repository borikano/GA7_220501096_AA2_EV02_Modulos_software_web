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
            new Usuario("Sofia Herrera", "sofia.herrera@email.com", "Sofia123*"),
            new Usuario("Daniel Castro", "daniel.castro@email.com", "Daniel123*"),
            new Usuario("Valentina Gomez", "valentina.gomez@email.com", "Valentina123*"),
            new Usuario("Mateo Ramirez", "mateo.ramirez@email.com", "Mateo123*"),
            new Usuario("Isabella Morales", "isabella.morales@email.com", "Isabella123*"),
            new Usuario("Sebastian Vargas", "sebastian.vargas@email.com", "Sebastian123*"),
            new Usuario("Natalia Pardo", "natalia.pardo@email.com", "Natalia123*"),
            new Usuario("Felipe Cardenas", "felipe.cardenas@email.com", "Felipe123*"),
            new Usuario("Gabriela Rios", "gabriela.rios@email.com", "Gabriela123*"),
            new Usuario("Tomas Navarro", "tomas.navarro@email.com", "Tomas123*")
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