package BaseDeDatos;

import Auxiliares.IO;
import Auxiliares.Llamada;
import java.time.Duration;

import java.time.format.DateTimeFormatter;
import java.util.Hashtable;

public class BaseDeDatos {

	private final String SEPARATOR = "\n----------------------------------------------------------------------------------------------------";

	public static final DateTimeFormatter DMY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter HMS = DateTimeFormatter.ofPattern("hh:mm:ss");
	public static final boolean EN_TERMINAL = true;

	private static Hashtable<String, Cliente> clientes = new Hashtable<String, Cliente>();
	private static Hashtable<String, Factura> facturas = new Hashtable<String, Factura>();

	public static Hashtable<String, Cliente> getClientes() {
		return clientes;
	}

	public static Hashtable<String, Factura> getFacturas() {
		return facturas;
	}

	public Cliente getCliente(String nif) {
		if (clientes.containsKey(nif)) return clientes.get(nif);
		else return null;
	}

	public static Duration randomDuration(int min, int max) {
		if (min > max) {
			int tmp = max;
			max = min;
			min = tmp;
		}
		if (min == max) return Duration.ofSeconds(min);
		return Duration.ofSeconds( (long)(Math.random() * ((max - min) + 1)) + min );
	}

	public boolean nuevoParticular(boolean random) {
		Cliente cliente;
		if (random) cliente = Cliente.darDeAlta(this, true, true);
		else cliente = Cliente.darDeAlta(this, true, false);
		return addClient(cliente);
	}

	public boolean nuevaEmpresa(boolean random) {
		Cliente cliente;
		if (random) cliente = Cliente.darDeAlta(this, false, true);
		else cliente = Cliente.darDeAlta(this, false, false);
		return addClient(cliente);
	}

	public boolean addClient(Cliente cliente) {
		String key = cliente.getNif();
		if (!clientes.containsKey(key)) {
			clientes.put(key, cliente);
			return true;
		}
		return false;
	}

	public boolean askBorrarCliente() {
		String nif;
		nif = IO.in.askNIF();
		return borrarCliente(nif);
	}

	public boolean borrarCliente(String nif) {
		if (clientes.containsKey(nif)) {
			clientes.remove(nif);
			return true;
		}
		return false;
	}		//TODO test

	public void cambiarTarifa() {
		Cliente cliente = IO.in.askForCliente(this);
		cliente.cambiarTarifa(IO.in.askForTarifa());
	}

	public void printCliente() {
		Cliente cliente = IO.in.askForCliente(this);
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
		Cliente cliente = IO.in.askForCliente(this);
		if (random) {
			cliente.darDeAltaLlamadaRandom();
			return true;
		} else {
			cliente.darDeAltaLlamada(Llamada.darDeAlta());
			return true;
		}
	}

	public void listarLlamadas() {
		IO.out.toTerminal("\n");
		Cliente cliente = IO.in.askForCliente(this);
		for (Llamada llamada : cliente.getLlamadas()) {
			IO.out.toTerminal(llamada + SEPARATOR);
		}
		if (EN_TERMINAL) IO.out.toTerminal(SEPARATOR);
		if (EN_TERMINAL) IO.waitIntro();
	}

	public boolean nuevaFactura(boolean random) {
		Cliente cliente = IO.in.askForCliente(this);
		Factura factura;
		if (random) {
			factura = Factura.darDeAltaRandom(this, cliente);
		} else {
			factura = Factura.darDeAlta(this, cliente);
		}
		cliente.addFactura(factura);
		addFactura(factura);
		return true;
	}

	public void addFactura(Factura factura) {
		facturas.put(factura.getCodigo(), factura);
		factura.getCliente().updateCodigoFacturaActual();
	}

	public void printFactura() {
		Factura factura = IO.in.askForFactura();
		IO.out.toTerminal(factura);
		if (EN_TERMINAL) IO.waitIntro();
	}

	public void listarFacturas() {
		IO.out.toTerminal("\n");
		Cliente cliente = IO.in.askForCliente(this);
		for (Factura factura : cliente.getFacturas().values()) {
			IO.out.toTerminal(factura + SEPARATOR);
		}
		if (EN_TERMINAL) IO.out.toTerminal(SEPARATOR);
		if (EN_TERMINAL) IO.waitIntro();
	}

	public void exitWithoutSave() {
		System.exit(0);
	}

}
