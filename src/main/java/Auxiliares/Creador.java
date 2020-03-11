package Auxiliares;

import BaseDeDatos.Cliente;
import BaseDeDatos.Factura;

public class Creador {

	InOut entradaSalida;

	public Creador(InOut entradaSalida) {
		this.entradaSalida = entradaSalida;
	}

	public Cliente nuevoCliente(boolean esParticular, boolean esRandom) {
		Cliente cliente;
		if (esParticular){
			if (esRandom) cliente = Cliente.darDeAlta(entradaSalida, true, true);
			else cliente = Cliente.darDeAlta(entradaSalida, true, false);
		} else {
			if (esRandom) cliente = Cliente.darDeAlta(entradaSalida, false, true);
			else cliente = Cliente.darDeAlta(entradaSalida, false, false);
		}
		return cliente;
	}

	public Llamada nuevaLlamada(boolean random) {
		if (random) {
			return Llamada.generateRandomLlamada();
		} else {
			return Llamada.darDeAlta(entradaSalida);
		}
	}

	public Factura nuevaFactura(Cliente cliente, boolean random) {
		Factura factura;
		if (random) {
			factura = Factura.darDeAltaRandom(cliente);
		} else {
			factura = Factura.darDeAlta(entradaSalida, cliente);
		}
		return factura;
	}

	public Tarifa nuevaTarifa() {
		return entradaSalida.askNewTarifa();
	}
}
