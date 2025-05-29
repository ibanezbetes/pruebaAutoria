package model.dao;

import model.entities.Usuario;
import model.factory.DatabaseFactory;
import model.motorsql.MotorSQL;

import java.sql.ResultSet;

public class UsuarioDAO {
    private final MotorSQL motor = DatabaseFactory.getDatabase(DatabaseFactory.POSTGRE);

    public Usuario login(String email, String pass) {
        String sql = "SELECT id,email,nombre FROM usuarios WHERE email='" +
                email + "' AND password='" + pass + "'";
        try {
            motor.connect();
            ResultSet rs = motor.executeQuery(sql);
            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setNombre(rs.getString("nombre"));
                return u;
            }
        } catch (Exception e) { e.printStackTrace(); }
        finally { motor.disconnect(); }
        return null;
    }
}
