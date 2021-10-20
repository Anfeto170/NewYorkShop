package com.OnlineShop.DTO;

public class Product {

	private int idProduct;
	private String Name;
	private int nitSupplier;
	private double purchasePrice, IVA, salePrice;

	public Product(int idProduct, String name, int nitSupplier, double purchasePrice) {
		super();
		this.idProduct = idProduct;
		this.Name = name;
		this.nitSupplier = nitSupplier;
		this.purchasePrice = purchasePrice;
		this.IVA = purchasePrice * 0.19;
		this.salePrice = purchasePrice + (purchasePrice * 0.19);
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getNitSupplier() {
		return nitSupplier;
	}

	public void setNitSupplier(int nitSupplier) {
		this.nitSupplier = nitSupplier;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getIVA() {
		return IVA;
	}

	public void setIVA(double iVA) {
		IVA = iVA;
	}

	public double getSalePrice() {
		return salePrice;
	}

}
