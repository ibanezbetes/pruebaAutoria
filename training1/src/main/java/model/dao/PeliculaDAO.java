package model.dao;

import model.entities.Pelicula;
import model.factory.DatabaseFactory;
import model.motorsql.MotorSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PeliculaDAO {
    private final MotorSQL motor;

    public PeliculaDAO() {
        motor = DatabaseFactory.getDatabase(DatabaseFactory.POSTGRE);
    }


    public ArrayList<Pelicula> findByGenero(String genero) {
        ArrayList<Pelicula> lista = new ArrayList<>();

        String sql =
                (genero == null || genero.isBlank())
                        ? "SELECT * FROM peliculas"
                        : "SELECT p.* FROM peliculas p " +
                        "JOIN peliculas_categorias pc ON p.id = pc.id_pelicula " +
                        "JOIN categorias c ON c.id = pc.id_categoria " +
                        "WHERE c.nombre ILIKE '%" + genero + "%'";

        lista = ejecutarYMapear(sql);
        return lista;
    }

    /* --- Búsqueda por texto en título ------------------------------------- */
    public ArrayList<Pelicula> findByTituloLike(String texto) {
        String sql = "SELECT * FROM peliculas " +
                "WHERE LOWER(titulo) LIKE LOWER('%" + texto + "%')";
        return ejecutarYMapear(sql);
    }

    /* --- Utilidad privada para no repetir código -------------------------- */
    private ArrayList<Pelicula> ejecutarYMapear(String sql) {
        ArrayList<Pelicula> lista = new ArrayList<>();
        try {
            motor.connect();
            ResultSet rs = motor.executeQuery(sql);

            while (rs.next()) {
                Pelicula p = new Pelicula();
                p.setId(rs.getInt("id"));
                p.setTitulo(rs.getString("titulo"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setDuracion(rs.getInt("duracion"));
                p.setAno(rs.getString("ano"));
                lista.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            motor.disconnect();
        }
        return lista;
    }
}
