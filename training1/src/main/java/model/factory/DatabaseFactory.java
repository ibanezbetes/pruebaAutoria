package model.factory;

import model.motorsql.*;

public class DatabaseFactory {
    public static final String POSTGRE = "POSTGRE";

    public static MotorSQL getDatabase(String tipo) {
        if (POSTGRE.equalsIgnoreCase(tipo)) {
            return new MotorPostgre();
        }
        throw new IllegalArgumentException("Tipo de base no soportado: " + tipo);
    }
}
