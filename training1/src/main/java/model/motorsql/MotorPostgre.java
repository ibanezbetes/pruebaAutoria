package model.motorsql;

import java.sql.*;

public class MotorPostgre implements MotorSQL {
    private Connection conn;
    private Statement st;
    private ResultSet rs;

    /* Ajusta URL, usuario y contraseña a tu Postgres */
    private static final String URL  = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASS = "1234";

    public void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
            st   = conn.createStatement();
            System.out.println("[DB] Conexión OK");
        } catch (Exception e) {
            e.printStackTrace();                 // ← mira el stack completo
            throw new RuntimeException("DB FAIL", e);
        }
    }



    @Override
    public int execute(String sql) {
        try { return st.executeUpdate(sql); }
        catch (SQLException e) { e.printStackTrace(); return 0; }
    }

    @Override
    public ResultSet executeQuery(String sql) {
        try { return st.executeQuery(sql); }
        catch (SQLException e) { e.printStackTrace(); return null; }
    }

    @Override
    public void disconnect() {
        try {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (conn != null) conn.close();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
