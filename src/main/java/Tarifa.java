public class Tarifa {

	private double precio;

	Tarifa() {
		this.precio = 0.15;
	}

	Tarifa(double precio) {
		this.precio = precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public double getPrecio() {
		return precio;
	}
}
