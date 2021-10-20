package com.OnlineShop.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.OnlineShop.DTO.*;

public class VentasDAO {

	public boolean insertVentas(Ventas ven) {
		BDconection conex = new BDconection();
		String confirmation = "No se pudo generar la factura";
		try {
			Statement estatuto = conex.getBDconection().createStatement();
			estatuto.executeUpdate(
					"INSERT INTO ventas_tbl(NIT_cliente, codigo_producto, cantidad, valor_total, fecha) VALUES ('"
							+ ven.getNIT_cliente() + "', '" + ven.getCodigo_producto() + "', " + ven.getCantidad()
							+ ", " + ven.getTotal() + ", '" + ven.getFecha() + "')");
			estatuto.close();
			confirmation = "Factura creada con �xito";
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	public ArrayList<Ventas> searchSales(String fecha) {
		ArrayList<Ventas> sales = new ArrayList<Ventas>();
		BDconection conex = new BDconection();

		String sql = "SELECT * FROM ventas_tbl";
		if (!fecha.trim().equals("null")) {
			sql = sql + " WHERE fecha = '" + fecha + "'";
		}

		try {
			Statement consulta = conex.getBDconection().createStatement();
			ResultSet res = consulta.executeQuery(sql);

			while (res.next()) {
				Ventas sale = new Ventas(res.getString("NIT_cliente"), res.getString("codigo_producto"),
						res.getInt("cantidad"), res.getString("valor_total"), res.getString("fecha"));
				sales.add(sale);
			}
			res.close();
			consulta.close();
			System.out.println("Se entregó el resultado" + sales);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No hay facturas por consultar\n" + e);
		}
		return sales;
	}

	public ArrayList<Object> verifyClientAndProduct(int CodProduct, String CosName) {
		ArrayList<Object> clientes = new ArrayList<Object>();
		BDconection conex = new BDconection();

		String sqlp = "SELECT * FROM products";
		if (!CosName.trim().equals("null")) {
			sqlp = sqlp + "WHERE name = '" + CosName + "'";
		}

		try {
			Statement consulta = conex.getBDconection().createStatement();
			ResultSet res = consulta.executeQuery(sqlp);

			while (res.next()) {
				Costumer sale = new Costumer(res.getString("idcard"), res.getString("name"), res.getString("address"),
						res.getString("phone"), res.getString("email"));
				clientes.add(sale);
			}
			res.close();
			consulta.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No hay facturas por consultar\n" + e);
		}

		String sqlc = "SELECT * FROM costumers";
		if (CodProduct != 0) {
			sqlc = sqlc + "WHERE idProduct = '" + CodProduct + "'";
		}

		try {
			Statement consulta = conex.getBDconection().createStatement();
			ResultSet res = consulta.executeQuery(sqlc);

			while (res.next()) {
				Product sale = new Product(res.getInt("idProduct"), res.getString("name"), res.getInt("nitSupplier"),
						res.getDouble("purchasePrice"));
				clientes.add(sale);
			}
			res.close();
			consulta.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No hay facturas por consultar\n" + e);
		}
		return clientes;
	}

	public ArrayList<String> consultarConsolidado(String tipo) {
		ArrayList<String> registros = new ArrayList<String>();
		BDconection conex = new BDconection();

		String sql = "";
		if (tipo.trim().equals("producto")) {
			sql = "SELECT codigo_producto AS Item, SUM(cantidad) AS Unidades\r\n" + "FROM ventas_tbl\r\n"
					+ "GROUP BY codigo_producto\r\n" + "ORDER BY codigo_producto;";

		} else if (tipo.trim().equals("cliente")) {
			sql = "SELECT NIT_cliente AS Item, SUM(cantidad) AS Unidades\r\n" + "FROM ventas_tbl\r\n"
					+ "GROUP BY NIT_cliente\r\n" + "ORDER BY NIT_cliente;";
		}

		try {
			Statement consulta = conex.getBDconection().createStatement();
			ResultSet res = consulta.executeQuery(sql);

			while (res.next()) {
				registros.add(res.getString("Item") + ";" + res.getInt("unidades"));

			}
			res.close();
			consulta.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No se pudieron consultar las ventas\n" + e);
		}
		return registros;
	}
}
