package Auxiliares;

public class Tarifa {

	private double precio;

	public Tarifa() {
		this.precio = 0.15;
	}

	public Tarifa(double precio) {
		this.precio = precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}
}
