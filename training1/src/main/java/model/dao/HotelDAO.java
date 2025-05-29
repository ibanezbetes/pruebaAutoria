package model.dao;

import model.entities.Hotel;
import model.factory.DatabaseFactory;
import model.motorsql.MotorSQL;

public class HotelDAO {
    private final MotorSQL motor = DatabaseFactory.getDatabase(DatabaseFactory.POSTGRE);

    public int add(Hotel h) {
        String sql = "INSERT INTO hoteles(nombre,direccion) VALUES('" +
                h.getNombre() + "','" + h.getDireccion() + "')";
        motor.connect();
        int filas = motor.execute(sql);
        motor.disconnect();
        return filas;
    }
}
