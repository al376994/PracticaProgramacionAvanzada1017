package BaseDeDatos;

import Auxiliares.IO;
import Auxiliares.Llamada;
import Auxiliares.PeriodoFacturacion;
import Auxiliares.TieneFecha;

import java.time.Duration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

public class BaseDeDatos {

	private final String SEPARADOR = "\n----------------------------------------------------------------------------------------------------";

	public static final DateTimeFormatter DMY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter HMS = DateTimeFormatter.ofPattern("hh:mm:ss");
	public static final boolean EN_TERMINAL = true;

	private static Hashtable<String, Cliente> clientes = new Hashtable<String, Cliente>();
	private static Hashtable<String, Factura> facturas = new Hashtable<String, Factura>();

	public Hashtable<String, Cliente> getClientes() {
		return clientes;
	}

	public Hashtable<String, Factura> getFacturas() {
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
	}	//TODO mover a MenuOpciones

	public boolean nuevaEmpresa(boolean random) {
		Cliente cliente;
		if (random) cliente = Cliente.darDeAlta(this, false, true);
		else cliente = Cliente.darDeAlta(this, false, false);
		return addClient(cliente);
	}		//TODO mover a MenuOpciones

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
	}					//TODO mover a MenuOpciones

	public boolean borrarCliente(String nif) {
		if (clientes.containsKey(nif)) {
			clientes.remove(nif);
			return true;
		}
		return false;
	}		//TODO test

	public void cambiarTarifa() {
		Cliente cliente = IO.in.askForCliente(this);
		cliente.cambiarTarifa(IO.in.askNewTarifa());
	}

	public void printCliente() {
		Cliente cliente = IO.in.askForCliente(this);
		IO.out.print(cliente);
		if (EN_TERMINAL) IO.waitIntro();
	}						//TODO mover a menuOpciones o Output

	public void listarClientes() {
		if (EN_TERMINAL) IO.out.print("\n");
		IO.out.listar(clientes.values());
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
		IO.out.print("\n");
		Cliente cliente = IO.in.askForCliente(this);
		IO.out.listar(cliente.getLlamadas());
	}						//TODO mover a MenuOpciones

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
		Factura factura = IO.in.askForFactura(this);
		IO.out.print(factura);
		if (EN_TERMINAL) IO.waitIntro();
	}						//TODO mover a MenuOpciones o Output

	public void listarFacturas() {
		IO.out.print("\n");
		Cliente cliente = IO.in.askForCliente(this);
		IO.out.listar(cliente.getFacturas().values());
	}						//TODO mover a MenuOpciones

	public <T extends TieneFecha> Collection<T> elementosEntreFechas(ArrayList<T> lista, LocalDate desde, LocalDate hasta) {
		PeriodoFacturacion entreEstasFechas = new PeriodoFacturacion(desde, hasta);
		ArrayList<T> listaEntreFechas = new ArrayList<>();
		for (T elemento : lista) {
			if (entreEstasFechas.inPeriodoFacturacion(elemento.getFecha())) listaEntreFechas.add(elemento);
		}
		return listaEntreFechas;
	}

	public void exitWithoutSave() {
		System.exit(0);
	}

}
