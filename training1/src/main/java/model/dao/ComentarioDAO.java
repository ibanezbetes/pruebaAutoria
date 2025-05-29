package model.dao;

import model.entities.Comentario;
import model.factory.DatabaseFactory;
import model.motorsql.MotorSQL;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComentarioDAO {
    private final MotorSQL motor = DatabaseFactory.getDatabase(DatabaseFactory.POSTGRE);

    public int add(Comentario c) {
        String sql = "INSERT INTO comentarios(id_pelicula,texto,autor,fecha) VALUES(" +
                c.getIdPelicula() + ",'" + c.getTexto() + "','" +
                c.getAutor() + "', CURRENT_DATE)";
        motor.connect();
        int filas = motor.execute(sql);
        motor.disconnect();
        return filas;
    }

    public List<Comentario> listByPelicula(int idPel) {
        List<Comentario> lista = new ArrayList<>();
        String sql = "SELECT * FROM comentarios WHERE id_pelicula = " + idPel;
        try {
            motor.connect();
            ResultSet rs = motor.executeQuery(sql);
            while (rs.next()) {
                Comentario c = new Comentario();
                c.setId(rs.getInt("id"));
                c.setIdPelicula(rs.getInt("id_pelicula"));
                c.setTexto(rs.getString("texto"));
                c.setAutor(rs.getString("autor"));
                c.setFecha(rs.getString("fecha"));
                lista.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally { motor.disconnect(); }
        return lista;
    }
}
