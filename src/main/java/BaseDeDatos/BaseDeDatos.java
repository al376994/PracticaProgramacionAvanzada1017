package BaseDeDatos;

import Auxiliares.IO;
import Auxiliares.Llamada;
import es.uji.www.GeneradorDatosINE;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

public class BaseDeDatos {

	private final String SEPARATOR = "\n----------------------------------------------------------------------------------------------------";

	public static final DateTimeFormatter DMY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter HMS = DateTimeFormatter.ofPattern("hh:mm:ss");
	public static final boolean EN_TERMINAL = true;

	public static Hashtable<String, Cliente> clientes = new Hashtable<String, Cliente>();
	public static Hashtable<String, Factura> facturas = new Hashtable<String, Factura>();

	private Empresa voidEmpresa = new Empresa();
	private Particular voidParticular = new Particular();

	public static Cliente getCliente(String nif) {
		if (clientes.containsKey(nif)) return clientes.get(nif);
		else return null;
	}

	public boolean nuevoParticular(boolean random) {
		Cliente cliente;
		if (random) cliente = voidParticular.darDeAltaRandom(this);
		else cliente = voidParticular.darDeAlta(this);
		return addClient(cliente);
	}

	public boolean nuevaEmpresa(boolean random) {
		Cliente cliente;
		if (random) cliente = voidEmpresa.darDeAltaRandom(this);
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

	public boolean borrarCliente() {
		String nif;
		nif = IO.in.fromTerminalAskString("\nEscribe el nif del cliente que quieres borrar: ");
		if (clientes.containsKey(nif)) {
			clientes.remove(nif);
			return true;
		}
		return false;
	}

	public void cambiarTarifa() {
		Cliente cliente = IO.in.askForCliente();
		cliente.cambiarTarifa(IO.in.askForTarifa());
	}

	public void printCliente() {
		Cliente cliente = IO.in.askForCliente();
		IO.out.toTerminal(cliente);
		if (EN_TERMINAL) IO.waitIntro();
	}

	public void listarClientes() {
		for (Cliente cliente : clientes.values()) {
			IO.out.toTerminal(cliente + SEPARATOR);
		}
		if (EN_TERMINAL) IO.out.toTerminal(SEPARATOR);
	}

	public boolean nuevaLlamada(boolean random) {
		Cliente cliente = IO.in.askForCliente();
		if (random) {
			cliente.darDeAltaLlamada();
			return true;
		} else {
			cliente.darDeAltaLlamada(IO.in.askNewLlamadaData());
			return true;
		}
	}

	public void listarLlamadas() {
		IO.out.toTerminal("\n");
		Cliente cliente = IO.in.askForCliente();
		for (Llamada llamada : cliente.llamadas) {
			IO.out.toTerminal(llamada + SEPARATOR);
		}
		if (EN_TERMINAL) IO.out.toTerminal(SEPARATOR);
		if (EN_TERMINAL) IO.waitIntro();
	}

	public boolean nuevaFactura(boolean random) {
		Cliente cliente = IO.in.askForCliente();
		if (random) {
			Factura.darDeAltaRandom(this, cliente);
		} else {
			Factura.darDeAlta(this, cliente);
		}
		return true;
	}

	public void exitWithoutSave() {
		System.exit(0);
	}

	Llamada generateRandomLlamada() {
		GeneradorDatosINE g = new GeneradorDatosINE();
		String tel =  "9" + g.getNIF().substring(0, 7);
		Duration dur = randomDuration(10, 60);
		return new Llamada(tel, LocalDate.now(), LocalTime.now(), dur);
	}

	private Duration randomDuration(int min, int max) {
		if (min > max) {
			int tmp = max;
			max = min;
			min = tmp;
		}
		if (min == max) return Duration.ofSeconds(min);
		return Duration.ofSeconds( (long)(Math.random() * ((max - min) + 1)) + min );
	}

}
