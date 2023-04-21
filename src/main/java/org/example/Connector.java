package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private String url;
    private String user;
    private String password;

    public Connector(){
        this.url = "jdbc:postgresql://dpg-ch0m7fjh4hsukp3vd9a0-a.frankfurt-postgres.render.com:5432/reactorsbd";
        this.user = "kkolbassa";
        this.password = "RxLS1HZOi09baytXk1r0dFQ9Y4yG0nq2";
    }

    public Connector(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
