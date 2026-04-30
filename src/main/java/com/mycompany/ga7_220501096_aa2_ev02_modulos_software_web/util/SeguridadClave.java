package com.mycompany.ga7_220501096_aa2_ev02_modulos_software_web.util;

import org.mindrot.jbcrypt.BCrypt;

public class SeguridadClave {

    private SeguridadClave() {
    }

    public static String generarHash(String clavePlano) {
        if (clavePlano == null || clavePlano.trim().isEmpty()) {
            throw new IllegalArgumentException("La clave no puede estar vacia.");
        }

        return BCrypt.hashpw(clavePlano, BCrypt.gensalt());
    }

    public static boolean validarClave(String clavePlano, String claveHash) {
        if (clavePlano == null || clavePlano.trim().isEmpty()) {
            return false;
        }

        if (claveHash == null || claveHash.trim().isEmpty()) {
            return false;
        }

        try {
            return BCrypt.checkpw(clavePlano, claveHash);
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}