package model.motorsql;

import java.sql.ResultSet;

public interface MotorSQL {
    void connect();
    int execute(String sql);
    ResultSet executeQuery(String sql);
    void disconnect();
}
