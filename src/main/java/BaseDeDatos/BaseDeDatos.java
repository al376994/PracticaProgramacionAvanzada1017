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

	public static final DateTimeFormatter dmy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter hms = DateTimeFormatter.ofPattern("hh:mm:ss");

	public Hashtable<String, Cliente> clientes = new Hashtable<String, Cliente>();
	public Hashtable<String, Factura> facturas = new Hashtable<String, Factura>();

	private Empresa voidEmpresa = new Empresa();
	private Particular voidParticular = new Particular();

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

	public void printCliente() {
		Cliente cliente = askForCliente();
		IO.out.toTerminal(cliente);
		IO.waitIntro();
	}

	public void listarClientes() {
		for (Cliente cliente : clientes.values()) {
			IO.out.toTerminal(cliente + SEPARATOR);
		}
		IO.out.toTerminal(SEPARATOR);
	}

	public boolean nuevaLlamada(boolean random) {
		Cliente cliente = askForCliente();
		if (random) {
			cliente.darDeAltaLlamada();
			return true;
		} else {
			String telefono = IO.in.fromTerminalAskString("Número de telefono: ");
			LocalDate date = askDate("de la llamada");
			LocalTime time = askTime("de la llamada");
			Duration duracion = Duration.ofSeconds(IO.in.fromTerminalAskInt("Duracion de la llamada: "));
			cliente.darDeAltaLlamada(telefono, date, time, duracion);
			return true;
		}
	}

	public void listarLlamadas() {
		IO.out.toTerminal("\n");
		Cliente cliente = askForCliente();
		for (Llamada llamada : cliente.llamadas) {
			IO.out.toTerminal(llamada + SEPARATOR);
		}
		IO.out.toTerminal(SEPARATOR);
		IO.waitIntro();
	}

	public boolean nuevaFactura(boolean random) {
		Cliente cliente = askForCliente();
		if (random) {

		} else {

		}
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

	private LocalDate askDate(String extra) {
		int yyyy = IO.in.fromTerminalAskInt("Año " + extra + ": ");
		int mm = IO.in.fromTerminalAskInt("Mes " + extra + ": ");
		int dd = IO.in.fromTerminalAskInt("Día " + extra + ": ");
		return LocalDate.of(yyyy, mm, dd);

	}

	private LocalTime askTime(String extra) {
		int hh = IO.in.fromTerminalAskInt("Hora " + extra + ": ");
		int mm = IO.in.fromTerminalAskInt("Minuto " + extra + ": ");
		int ss = IO.in.fromTerminalAskInt("Segundo " + extra + ": ");
		return LocalTime.of(hh, mm, ss);
	}

}
