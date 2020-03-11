package Auxiliares;

import BaseDeDatos.Cliente;
import BaseDeDatos.Factura;

public class Creador {

	static public Cliente nuevoCliente(boolean esParticular, boolean esRandom) {
		Cliente cliente;
		if (esParticular){
			if (esRandom) cliente = Cliente.darDeAlta(true, true);
			else cliente = Cliente.darDeAlta(true, false);
		} else {
			if (esRandom) cliente = Cliente.darDeAlta(false, true);
			else cliente = Cliente.darDeAlta(false, false);
		}
		return cliente;
	}

	static public Llamada nuevaLlamada(boolean random) {
		if (random) {
			return Llamada.generateRandomLlamada();
		} else {
			return Llamada.darDeAlta();
		}
	}

	static public Factura nuevaFactura(Cliente cliente, boolean random) {
		Factura factura;
		if (random) {
			factura = Factura.darDeAltaRandom(cliente);
		} else {
			factura = Factura.darDeAlta(cliente);
		}
		return factura;
	}

	static public Tarifa nuevaTarifa() {
		return IO.in.askNewTarifa();
	}
}
