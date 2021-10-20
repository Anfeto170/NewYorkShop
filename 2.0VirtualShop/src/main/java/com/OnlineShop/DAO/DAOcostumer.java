package com.OnlineShop.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.OnlineShop.DTO.Costumer;

public class DAOcostumer {

	BDconection Conect;

	public void createCostumer(Costumer createdCostumer) {
		Conect = new BDconection();
		try {
			Statement stmt = Conect.getBDconection().createStatement();
			stmt.executeUpdate("INSERT INTO costumers(idcard, name, address, phone, email) VALUES (" + "'"
					+ createdCostumer.getIDcard() + "'" + "," + "'" + createdCostumer.getName() + "'" + "," + "'"
					+ createdCostumer.getAddress() + "'" + "," + "'" + createdCostumer.getPhone() + "'" + "," + "'"
					+ createdCostumer.getEmail() + "'" + ")");
			System.out.println("El cliente ingresado es: " + createdCostumer.getName() + " junto a su ID: "
					+ createdCostumer.getIDcard());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> costumersList(String card) {
		ArrayList<String> registros = new ArrayList<String>();

		BDconection conex = new BDconection();

		String sql = "";
		sql = "select name from costumers where idcard= " + card + ";";

		try {
			Statement consulta = conex.getBDconection().createStatement();
			ResultSet res = consulta.executeQuery(sql);

			while (res.next()) {
				registros.add(res.getString("name"));

			}

			res.close();
			consulta.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No existe un usuario con esta cédula\n" + e);
		}
		return registros;
	}

	public ArrayList<Costumer> searchCostumers(String sCostumer) {
		ArrayList<Costumer> costumers = new ArrayList<Costumer>();
		BDconection conex = new BDconection();

		String sql = "SELECT * FROM costumers ";
		if (!sCostumer.trim().equals("null")) {
			sql = sql + "WHERE idcard = '" + sCostumer + "'";
		}

		try {
			Statement consulta = conex.getBDconection().createStatement();
			ResultSet res = consulta.executeQuery(sql);

			while (res.next()) {
				Costumer cmr = new Costumer(res.getString("idcard"), res.getString("name"), res.getString("address"),
						res.getString("phone"), res.getString("email"));
				costumers.add(cmr);
			}
			res.close();
			consulta.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar al cliente\n" + e);
		}
		return costumers;
	}

	public String modifyCostumer(Costumer upCostumer) {
		Conect = new BDconection();
		try {
			Statement stmt = Conect.getBDconection().createStatement();
			String modificar = "name='" + upCostumer.getName() + "',address='" + upCostumer.getAddress() + "',phone='"
					+ upCostumer.getPhone() + "',email='" + upCostumer.getEmail() + "'";
			stmt.executeUpdate(
					"UPDATE costumers" + " SET " + modificar + " WHERE idCard='" + upCostumer.getIDcard() + "'");
			String response = "Se modifico al cliente " + upCostumer.getIDcard() + " el nombre (" + upCostumer.getName()
					+ "), la direccion(" + upCostumer.getAddress() + "), el telefono (" + upCostumer.getPhone()
					+ ") y el E-mail (" + upCostumer.getEmail() + ").";
			return response;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void deleteCostumer(Costumer co) {
		String bd = "Grupo02NewYork.costumers";
		String condicion = "idcard='" + co.getIDcard() + "'";
		Conect = new BDconection();

		try {
			Statement stmt = Conect.getBDconection().createStatement();
			stmt.executeUpdate("DELETE FROM " + bd + " WHERE " + condicion + ";");
			System.out.println("El cliente fue eliminado ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
