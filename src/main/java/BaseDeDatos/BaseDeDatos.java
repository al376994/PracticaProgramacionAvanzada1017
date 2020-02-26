package BaseDeDatos;

import Auxiliares.IO;
import Auxiliares.Llamada;
import es.uji.www.GeneradorDatosINE;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Hashtable;

public class BaseDeDatos {

	public Hashtable<String, Cliente> clientes = new Hashtable<String, Cliente>();

	public Empresa voidEmpresa = new Empresa();
	public Particular voidParticular = new Particular();

	public boolean nuevoParticular(boolean notRandom) {
		Cliente cliente;
		if (notRandom) cliente = voidParticular.darDeAltaRandom(this);
		else cliente = voidParticular.darDeAlta(this);
		return addClient(cliente);
	}

	public boolean nuevaEmpresa(boolean notRandom) {
		Cliente cliente;
		if (notRandom) cliente = voidEmpresa.darDeAltaRandom(this);
		else cliente = voidEmpresa.darDeAlta(this);
		return addClient(cliente);
	}

	private boolean addClient(Cliente cliente) {
		String key = cliente.getNif();
		if (!clientes.containsKey(key)) {
			clientes.put(key, cliente);
			return true;
		}
		return false;
	}

	public boolean borrarCliente(String nif) {
		if (clientes.containsKey(nif)) {
			clientes.remove(nif);
			return true;
		}
		return false;
	}

	public void cambiarTarifa() {
		Cliente cliente = askForCliente();
		double tarifa = IO.in.fromTerminalAskDouble("Introduce el precio de la nueva tarifa: ");
		cliente.cambiarTarifa(tarifa);
	}

	private Cliente getCliente(String nif) {
		if (clientes.containsKey(nif)) return clientes.get(nif);
		else return null;
	}

	private Cliente askForCliente() {
		Cliente cliente = null;
		while (cliente == null) {
			String nif = IO.in.fromTerminalAskString("Introduce el NIF del cliente: ");
			cliente = getCliente(nif);
			if (cliente == null) IO.out.toTerminal("El NIF introducido no es válido");
		}
		return cliente;
	}

	public void listarClientes() {
		for (Cliente cliente : clientes.values()) {
			IO.out.toTerminal(cliente + "\n----------------------------------------------------------------------------------------------------");
		}
	}

	public Llamada generateRandomLlamada() {
		GeneradorDatosINE g = new GeneradorDatosINE();
		String tel =  "9" + g.getNIF().substring(0, 7);
		Duration dur = randomDuration(10, 60);
		return new Llamada(tel, LocalDate.now(), LocalTime.now(), dur);
	}

	public Duration randomDuration(int min, int max) {
		if (min > max) {
			int tmp = max;
			max = min;
			min = tmp;
		}
		if (min == max) return Duration.ofSeconds(min);
		return Duration.ofSeconds( (long)(Math.random() * ((max - min) + 1)) + min );
	}

}
