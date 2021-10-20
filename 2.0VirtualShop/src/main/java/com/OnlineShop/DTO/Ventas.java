package com.OnlineShop.DTO;

public class Ventas {

	private String Codigo_producto;
	private int cantidad;
	private String Total;
	private String NIT_cliente, Fecha;

	public Ventas(String nIT_cliente, String codigo_producto, int cantidad, String total, String fecha) {
		super();
		this.Codigo_producto = codigo_producto;
		this.cantidad = cantidad;
		this.NIT_cliente = nIT_cliente;
		this.Total = total;
		this.Fecha = fecha;
	}
	
	public String getFecha() {
		return Fecha;
	}

	public void setFecha(String fecha) {
		this.Fecha = fecha;
	}

	public String getCodigo_producto() {
		return Codigo_producto;
	}

	public void setCodigo_producto(String codigo_producto) {
		this.Codigo_producto = codigo_producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getTotal() {
		return Total;
	}

	public void setTotal(String total) {
		Total = total;
	}

	public String getNIT_cliente() {
		return NIT_cliente;
	}

	public void setNIT_cliente(String nIT_cliente) {
		NIT_cliente = nIT_cliente;
	}



}
