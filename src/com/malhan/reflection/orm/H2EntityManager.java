package com.malhan.reflection.orm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2EntityManager<T> extends AbstractEntityManager<T> {

    public Connection buildConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:h2:~/dev/courses/fm-pluralsight/back-end/intermediate/reflection-api/db-files/db-ps",
                "malhan", "malhan");
        return connection;
    }
}
