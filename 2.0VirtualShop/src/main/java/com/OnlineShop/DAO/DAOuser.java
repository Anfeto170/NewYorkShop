package com.OnlineShop.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.OnlineShop.DTO.User;

public class DAOuser {

	BDconection Conect;

	public void createUser(User createdUser) {
		Conect = new BDconection();
		try {
			Statement stmt = Conect.getBDconection().createStatement();
			stmt.executeUpdate("INSERT INTO users(idCard, name, email, user, password) VALUES (" + "'"
					+ createdUser.getIDcard() + "'" + "," + "'" + createdUser.getName() + "'" + "," + "'"
					+ createdUser.getEmail() + "'" + "," + "'" + createdUser.getUser() + "'" + "," + "MD5('"
					+ createdUser.getPassword() + "')" + ")");
			System.out.println("El usuario ingresado es: " + createdUser.getUser() + " junto a su contraseña: "
					+ createdUser.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<User> searchUsers(String sUser) {
		ArrayList<User> users = new ArrayList<User>();
		BDconection conex = new BDconection();

		String sql = "SELECT * FROM users ";
		if (!sUser.trim().equals("null")) {
			sql = sql + "WHERE idCard = '" + sUser + "'";
		}

		try {
			Statement consulta = conex.getBDconection().createStatement();
			ResultSet res = consulta.executeQuery(sql);

			while (res.next()) {
				User usr = new User(res.getString("idCard"), res.getString("name"), res.getString("email"),
						res.getString("user"), res.getString("password"));
				users.add(usr);
			}
			res.close();
			consulta.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n" + e);
		}
		return users;
	}

	public Boolean validateUser(String sUser) {
		Boolean confirmation = false;
		BDconection conex = new BDconection();

		String sql = "SELECT * FROM users WHERE user = '" + sUser + "'";

		int counter = 0;
		Statement consulta;
		try {
			consulta = conex.getBDconection().createStatement();
			ResultSet oCome = consulta.executeQuery(sql);
			while (oCome.next()) {
				counter = +1;
			}
			if (counter == 1) {
				confirmation = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return confirmation;
	}

	public Boolean validatePassword(String sUser, String pUser) {
		Boolean confirmation = false;
		BDconection conex = new BDconection();

		String sql = "SELECT * FROM users WHERE user = '" + sUser + "' AND password = MD5('" + pUser + "')";
		int counter = 0;
		Statement consulta;
		try {
			consulta = conex.getBDconection().createStatement();
			ResultSet oCome2 = consulta.executeQuery(sql);
			while (oCome2.next()) {
				counter = +1;
			}

			if (counter == 1) {
				confirmation = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return confirmation;
	}

	/*
	 * public Boolean logIn(String sUser, String pUser) { Boolean confirmation =
	 * false; BDconection conex = new BDconection();
	 * 
	 * String sql = "SELECT COUNT(*) AS total FROM users WHERE user = '" + sUser +
	 * "' and password = '" + pUser + "';";
	 * 
	 * Statement consulta; int counter = 0; try { consulta =
	 * conex.getBDconection().createStatement(); ResultSet res =
	 * consulta.executeQuery(sql); counter = res.getInt("total"); if (counter == 1)
	 * { confirmation = true; }
	 * 
	 * } catch (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }
	 * 
	 * return confirmation; }
	 * 
	 * public Boolean logInUsers(String sUser, String pUser) {
	 * System.out.println(sUser + pUser); // ArrayList<User> users2 = new
	 * ArrayList<User>();
	 * 
	 * BDconection conex = new BDconection();
	 * 
	 * String sql = "SELECT * FROM users "; String sql1 = null; String sql2 = null;
	 * Boolean exist = false;
	 * 
	 * if (!sUser.trim().equals("null") && !pUser.trim().equals("null")) { sql1 =
	 * sql + "WHERE user = '" + sUser + "';";
	 * System.out.println("Aqu� llegamos 1"); }
	 * 
	 * try { Statement consulta = conex.getBDconection().createStatement();
	 * ResultSet res = consulta.executeQuery(sql1); int Counter = 0; while
	 * (res.next()) { Counter += 1; } System.out.println(res);
	 * System.out.println("Aqu� llegamos 2");
	 * 
	 * if (Counter != 0) { sql2 = sql + "WHERE user = '" + sUser + "'" +
	 * "and password = '" + pUser + "';"; ResultSet resf =
	 * consulta.executeQuery(sql2); System.out.println(resf);
	 * 
	 * System.out.println("Aqu� llegamos 3"); int Counter2 = 0; while
	 * (resf.next()) { Counter2 += 1; } System.out.println(Counter2);
	 * 
	 * if (Counter2 != 0) { System.out.println("Aqu� llegamos 3.5");
	 * 
	 * while (resf.next()) {
	 * 
	 * User usr2 = new User(resf.getString("idCard"), resf.getString("name"),
	 * resf.getString("email"), resf.getString("user"), resf.getString("password"));
	 * System.out.println(usr2);
	 * 
	 * System.out.println("Aqu� llegamos 3.5");
	 * 
	 * usr2.toString();
	 * 
	 * users2.add(usr2); }
	 * 
	 * exist = true; System.out.println("Aqu� llegamos 3.5");
	 * 
	 * System.out.println("Inicio de sesi�n exitoso");
	 * System.out.println("Aqu� llegamos 4");
	 * 
	 * } else { System.out.println("La contrase�a es incorrecta");
	 * 
	 * } resf.close(); } else {
	 * System.out.println("El usuario ingresado no existe");
	 * 
	 * }
	 * 
	 * consulta.close();
	 * 
	 * } catch (Exception e) { JOptionPane.showMessageDialog(null,
	 * "Hubo un error en al iniciar sesion\n" + e); } return exist; }
	 */
	public String modifyUser(User modUser) {
		Conect = new BDconection();
		try {
			Statement stmt = Conect.getBDconection().createStatement();
			String modificar = "email='" + modUser.getEmail() + "',password=MD5('" + modUser.getPassword() + "')";
			stmt.executeUpdate("UPDATE users" + " SET " + modificar + " WHERE idCard='" + modUser.getIDcard() + "'");
			String response = "Se modifico al usuario " + modUser.getUser() + " la contrase�a (" + modUser.getPassword()
					+ ") y el E-mail (" + modUser.getEmail() + ").";
			return response;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void deleteUser(User dUser) {

		String bd = "Grupo02NewYork.users";
		String condicion = "idCard='" + dUser.getIDcard() + "'";
		Conect = new BDconection();

		try {
			Statement stmt = Conect.getBDconection().createStatement();
			stmt.executeUpdate("DELETE FROM users WHERE " + condicion + ";");
			System.out.println("El usuario fue eliminado ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
