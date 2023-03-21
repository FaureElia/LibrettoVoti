package it.polito.tdp.libretto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConnect {
	public static Connection getConnection() throws SQLException {
		String jdbcURL="jdbc:mariadb://localhost/libretto?user=root&password=eliaMaria23";
		Connection conn=DriverManager.getConnection(jdbcURL);
		return conn;
	}

}
