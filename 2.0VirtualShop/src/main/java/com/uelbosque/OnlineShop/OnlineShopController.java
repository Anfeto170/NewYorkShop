package com.uelbosque.OnlineShop;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.OnlineShop.DTO.*;
import com.OnlineShop.DAO.*;

@RestController
public class OnlineShopController {

	/***************
	 * User
	 ***************/
	/*
	 * @RequestMapping("/") public String home() { return " 22"; }
	 */

	@RequestMapping("/logInUser")
	public String logInUsers(String sUser, String pUser) {
		DAOuser DAO = new DAOuser();

		String confirmation = "";
		if (DAO.validateUser(sUser) == true) {
			if (DAO.validatePassword(sUser, pUser) == true) {
				confirmation = "Ingreso Exitoso";
			} else {
				confirmation = "Contrase�a incorrecta";
			}
		} else {
			confirmation = "El usuario ingresado no existe";
		}
		return confirmation;
		// return DAO.logIn(sUser, pUser);
	}

	@RequestMapping("/CreateUser")
	public String CreateUser(User cUser) {
		DAOuser DAO = new DAOuser();
		DAO.createUser(cUser);
		return "Usuario Registrado";
	}

	@RequestMapping("/SearchUser")
	public ArrayList<User> searchUsers(String sUser) {
		DAOuser DAO = new DAOuser();
		return DAO.searchUsers(sUser);
	}
	
	@RequestMapping("/ConsultaGUser")
	public ArrayList<User> ConsultaGUser(String sUser) {
		DAOuser DAO = new DAOuser();
		return DAO.searchUsers(sUser);
	}
	@RequestMapping("/ModifyUser")
	public String ModifyUser(User modUser) {
		DAOuser DAO = new DAOuser();
		return DAO.modifyUser(modUser);
	}

	@RequestMapping("/DeleteUser")
	public String DeleteUser(User dUser) {
		DAOuser DAO = new DAOuser();
		DAO.deleteUser(dUser);
		return "Usuario Eliminado";
	}

	/***************
	 * Costumer
	 ***************/
	// El nombre del @RequestMapping es el que expone el microservicio
	@RequestMapping("/CreateCostumer")
	public String CreateCostumer(Costumer cCostumer) {
		DAOcostumer DAO = new DAOcostumer();
		DAO.createCostumer(cCostumer);
		return "Cliente Registrado";
	}

	@RequestMapping("/SearchCostumer")
	public ArrayList<Costumer> searchCostumers(String sCostumer) {
		DAOcostumer DAO = new DAOcostumer();
		return DAO.searchCostumers(sCostumer);
	}
	
	@RequestMapping("/ConsultaGCostumer")
	public ArrayList<Costumer> ConsultaCostumers(String sCostumer) {
		DAOcostumer DAO = new DAOcostumer();
		return DAO.searchCostumers(sCostumer);
	}

	@RequestMapping("/ModifyCostumer")
	public String ModifyCostumer(Costumer modCost) {
		DAOcostumer DAO = new DAOcostumer();
		return DAO.modifyCostumer(modCost);
	}

	@RequestMapping("/DeleteCostumer")
	public String DeleteCostumer(Costumer dCostumer) {
		DAOcostumer DAO = new DAOcostumer();
		DAO.deleteCostumer(dCostumer);
		return "Cliente Eliminado";
	}

	@RequestMapping("/ListCostumer")
	public ArrayList<String> ListCostumer(String card) {
		DAOcostumer dao = new DAOcostumer();
		return dao.costumersList(card);
	}

	/***************
	 * Supplier
	 ***************/
	@RequestMapping("/CreateSupplier")
	public String registrarProveedor(Supplier p) {
		DAOsupplier dao = new DAOsupplier();
		dao.createSupplier(p);
		return "Proveedor Registrado";
	}

	@RequestMapping("/ModifySupplier")
	public String ModifySupplier(Supplier modSupp) {
		DAOsupplier DAO = new DAOsupplier();
		return DAO.modifySupplier(modSupp);
	}

	@RequestMapping("/SearchSupplier")
	public ArrayList<Supplier> consultarProveedores(String nit) {
		DAOsupplier dao = new DAOsupplier();
		return dao.searchSupplier(nit);
	}
	@RequestMapping("/DeleteSupplier")
	public String DeleteSupplier(Supplier dsup) {
		DAOsupplier DAO = new DAOsupplier();
		DAO.deleteSupplier(dsup);
		return "Proveedor Eliminado";
	}


	/***************
	 * Product
	 ***************/
	@RequestMapping("/CreateProduct")
	public String CreateProduct(Product p) {
		DAOproduct dao = new DAOproduct();
		dao.createProduct(p);
		return "Producto Registrado";
	}

	@PostMapping("/cargarArchivo")
	public ResponseEntity<?> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
		LocalDateTime now = LocalDateTime.now();
		try {
			String name = "C:\\ArchivosRecibidos\\" + dtf.format(now) + fileName;
			System.out.println(name);
			File fl = new File(name);
			file.transferTo(fl);
			DAOproduct dao = new DAOproduct();
			dao.FileUpload(fl);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok("Archivo cargado con exito.");
	}

	@RequestMapping("/SearchProduct")
	public ArrayList<Product> searchProducts(String sProduct) {
		DAOproduct DAO = new DAOproduct();
		return DAO.searchProducts(sProduct);
	}

	@RequestMapping("/ModifyProduct")
	public String modifyProduct(Product modProd) {
		DAOproduct DAO = new DAOproduct();
		return DAO.modifyProduct(modProd);
	}

	@RequestMapping("/DeleteProduct")
	public String DeleteProduct(Product dProduct) {
		DAOproduct DAO = new DAOproduct();
		DAO.deleteProduct(dProduct);
		return "Producto  Eliminado";
	}

	/***************
	 * Sales
	 ***************/

	@RequestMapping("/consultarVentas")
	public ArrayList<String> consultarVentas(String tipo) {
		VentasDAO dao = new VentasDAO();
		return dao.consultarConsolidado(tipo);
	}

	@RequestMapping("/CreateSale")
	public boolean CreateSale(Ventas factura) {
		VentasDAO dao = new VentasDAO();
		return dao.insertVentas(factura);
	}

	@RequestMapping("/SearchSale")
	public ArrayList<Ventas> SearchSale(String date) {
		VentasDAO dao = new VentasDAO();
		return dao.searchSales(date);
	}

	@RequestMapping("/ListSales")
	public ArrayList<String> ListSales() {
		DAOproduct dao = new DAOproduct();
		return dao.listadoProductos();
	}

}
