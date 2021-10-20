package com.OnlineShop.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.OnlineShop.DTO.*;;

public class DAOsupplier {

	public void createSupplier(Supplier prov) {
		BDconection conex = new BDconection();
		try {
			Statement estatuto = conex.getBDconection().createStatement();
			estatuto.executeUpdate("INSERT INTO supplier(NIT, name, address, phone, email, website) VALUES ('"
					+ prov.getNIT() + "', '" + prov.getName() + "', '" + prov.getAddress() + "', '" + prov.getPhone()
					+ "', '" + prov.getEmail() + "', '" + prov.getWebsite() + "')");
			estatuto.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public ArrayList<Supplier> searchSupplier(String nit) {
		ArrayList<Supplier> proveedores = new ArrayList<Supplier>();
		BDconection conex = new BDconection();

		String sql = "SELECT * FROM supplier ";
		if (!nit.trim().equals("null")) {
			sql = sql + "WHERE NIT = '" + nit + "'";
		}

		try {
			Statement consulta = conex.getBDconection().createStatement();
			ResultSet res = consulta.executeQuery(sql);

			while (res.next()) {
				Supplier prov = new Supplier(res.getString("NIT"), res.getString("name"), res.getString("address"),
						res.getString("phone"), res.getString("email"), res.getString("website"));
				proveedores.add(prov);
			}
			res.close();
			consulta.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar la Persona\n" + e);
		}
		return proveedores;
	}
	public String modifySupplier(Supplier modSupp) {
		BDconection Conect = new BDconection();
		try {
			Statement stmt = Conect.getBDconection().createStatement();
			String modificar = "NIT='" + modSupp.getNIT() +"' ,address='" + modSupp.getAddress() + "', phone='" + modSupp.getPhone() + "' ,email='" + modSupp.getEmail() + "', website='" + modSupp.getWebsite() + "'";
			stmt.executeUpdate("UPDATE supplier " + " SET " + modificar + " WHERE NIT='" + modSupp.getNIT()+ "'");
			String response = "Se modifico al proveedor " + modSupp.getNIT() + " el nombre (" + modSupp.getName() + "), la direccion ("+ modSupp.getAddress() +"), el telefono ("+modSupp.getPhone()+"), el E-mail (" + modSupp.getEmail()+") y el sitio web ("+modSupp.getWebsite() + ").";
			return response;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void deleteSupplier(Supplier dsup) {
		String bd = "Grupo02NewYork.supplier";
		String condicion = "NIT='" + dsup.getNIT() + "'";
		BDconection Conect = new BDconection();

		try {
			Statement stmt = Conect.getBDconection().createStatement();
			stmt.executeUpdate("DELETE FROM " + bd + " WHERE " + condicion + ";");
			System.out.println("El Proveedor fue eliminado ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
