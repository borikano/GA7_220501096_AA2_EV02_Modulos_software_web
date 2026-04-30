package com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3307/bbdd_ga7_ev02_web";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private Conexion() {
    }

    public static Connection obtenerConexion() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static String probarConexion() {
        try (Connection conexion = obtenerConexion()) {

            if (conexion != null && !conexion.isClosed()) {
                return "Conexion exitosa a la base de datos bbdd_ga7_ev02_web.";
            }

        } catch (ClassNotFoundException e) {
            return "Error: No se encontro el driver de MySQL. Detalle: " + e.getMessage();

        } catch (SQLException e) {
            return "Error de conexion: " + e.getMessage();
        }

        return "No fue posible validar la conexion.";
    }
}