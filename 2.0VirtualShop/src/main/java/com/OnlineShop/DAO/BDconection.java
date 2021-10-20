package com.OnlineShop.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDconection {
	// Base de datos local
	/*
	 * String bd = "tienda_virtual"; String login = "root"; String password =
	 * "admin"; String url = "jdbc:mysql://localhost/" + bd;
	 */

	// Base de AWS

	String bd = "Grupo02NewYork";
	String login = "admin";
	String password = "MisionTIC2022GRUPO02";
	String url = "jdbc:mysql://misiontic2022grupo02.czo3ixoe3xoe.us-east-1.rds.amazonaws.com/" + bd;

	public Connection getBDconection() {

		Connection connection = null;

		try {
			// Driver para SQL
			Class.forName("org.mariadb.jdbc.Driver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, login, password);
			if (connection != null) {
				System.out.println("Conexion a base de datos " + bd + " correcta\n");
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return connection;

	}

}
