package com.OnlineShop.DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.OnlineShop.DTO.Product;
import com.OnlineShop.DTO.Supplier;

public class DAOproduct {

	BDconection Conect;

	public void createProduct(Product createdProduct) {
		Conect = new BDconection();
		try {
			Statement stmt = Conect.getBDconection().createStatement();
			stmt.executeUpdate(
					"INSERT INTO products(idProduct, name, nitSupplier, purchasePrice, IVA, salePrice) VALUES (" + "'"
							+ createdProduct.getIdProduct() + "'" + "," + "'" + createdProduct.getName() + "'" + ","
							+ "'" + createdProduct.getNitSupplier() + "'" + "," + "'"
							+ createdProduct.getPurchasePrice() + "'" + "," + "'" + createdProduct.getIVA() + "'" + ","
							+ "'" + createdProduct.getSalePrice() + "'" + ")");
			System.out.println("El producto ingresado es: " + createdProduct.getName() + " junto a su codigo: "
					+ createdProduct.getIdProduct());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
		}
	}

	public void FileUpload(File archivo) {
		try {
			FileReader fr = new FileReader(archivo);
			BufferedReader br = new BufferedReader(fr);
			String linea = "";
			while ((linea = br.readLine()) != null) {
				String[] tokens = linea.split(";");
				Product p = new Product(Integer.parseInt(tokens[0]), tokens[1], Integer.parseInt(tokens[2]),
						Float.parseFloat(tokens[3]));
				createProduct(p);
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> listadoProductos() {
		ArrayList<String> registros = new ArrayList<String>();
		BDconection conex = new BDconection();

		String sql = "";
		sql = "SELECT idProduct, name, salePrice FROM products;";

		try {
			Statement consulta = conex.getBDconection().createStatement();
			ResultSet res = consulta.executeQuery(sql);

			while (res.next()) {
				registros.add(
						res.getString("idProduct") + ";" + res.getString("name") + ";" + res.getDouble("salePrice"));

			}
			res.close();
			consulta.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "No hay productos por consultar\n" + e);
		}
		return registros;
	}

	public ArrayList<Product> searchProducts(String sProduct) {
		ArrayList<Product> products = new ArrayList<Product>();
		BDconection conex = new BDconection();

		String sql = "SELECT * FROM products ";
		if (!sProduct.trim().equals("null")) {
			sql = sql + "WHERE idProduct = '" + sProduct + "'";
		}

		try {
			Statement consulta = conex.getBDconection().createStatement();
			ResultSet res = consulta.executeQuery(sql);

			while (res.next()) {
				Product pdt = new Product(res.getInt("idProduct"), res.getString("name"), res.getInt("nitSupplier"),
						res.getDouble("purchasePrice"));
				products.add(pdt);
			}
			res.close();
			consulta.close();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "no se pudo consultar al cliente\n" + e);
		}
		return products;
	}

	public String modifyProduct(Product upProduct) {
		String response = null;
		Conect = new BDconection();
		try {
			String ProductNit = String.valueOf(upProduct.getNitSupplier());
			DAOsupplier Searcher = new DAOsupplier();
			ArrayList<Supplier> Results = Searcher.searchSupplier(ProductNit);
			if (Results.size() != 0) {
				System.out.println("Aquí se llegó");
				Statement stmt = Conect.getBDconection().createStatement();
				String modificar = "nitSupplier='" + upProduct.getNitSupplier() + "',purchasePrice='"
						+ upProduct.getPurchasePrice() + "',IVA='" + upProduct.getIVA() + "',salePrice='"
						+ upProduct.getSalePrice() + "'";
				stmt.executeUpdate("UPDATE products" + " SET " + modificar + " WHERE idProduct='"
						+ upProduct.getIdProduct() + "'");
				response = "Se modifico al producto " + upProduct.getIdProduct() + " el nombre (" + upProduct.getName()
						+ "), el NIT del proveedor (" + upProduct.getNitSupplier() + "), el precio de compra ("
						+ upProduct.getPurchasePrice() + ") y el precio de venta (" + upProduct.getSalePrice() + ").";
			} else {
				response = "No existe un proveedor para ese producto";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public void deleteProduct(Product pro) {
		String bd = "Grupo02NewYork.products";
		String condicion = "idProduct='" + pro.getIdProduct() + "'";
		Conect = new BDconection();

		try {
			Statement stmt = Conect.getBDconection().createStatement();
			stmt.executeUpdate("DELETE FROM " + bd + " WHERE " + condicion + ";");
			System.out.println("El producto fue eliminado ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
