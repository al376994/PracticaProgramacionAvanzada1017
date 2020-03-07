package Auxiliares;

// La clase Tarifa representa la tarifa que tiene un cliente o una factura. Por el momento solo guarda un double con
// el precio de la tarifa y lo puede devolver. Se puede crear una tarifa por defecto con un precio de 0.15 o darle un
// precio en concreto.

public class Tarifa {

	private double precio;

	public Tarifa() {
		this.precio = 0.15;
	}

	public Tarifa(double precio) {
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}
}
