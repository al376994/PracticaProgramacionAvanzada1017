package BaseDeDatos;

import Auxiliares.IO;
import Auxiliares.Llamada;
import Auxiliares.PeriodoFacturacion;
import Auxiliares.TieneFecha;
import es.uji.www.GeneradorDatosINE;

import java.time.Duration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;

// La clase BaseDeDatos como su nombre indica guarda una base de datos, concreteamente de los clientes y las facturas.
// Tanto clientes como facturas se guardan en HashTables para poder recuperar rapidamente u cliente o una factura a
// partir de su clave de identificación. En esta clase SOLO se interactua con la base de datos en si, es decir, solo
// hay funciones que interactuen directamente con las tablas clientes y facturas. También contiene unas variables
// que se utilizan en todoo el programa por lo que son static y como siempre son iguales son final también, estas son
// DMY que guarda el formato en el que se muestra una fecha, HMS que guarda el formato en el que se muestra una hora,
// y EN_TERMINAL que indica al programa que se esta ejecutando el programa en terminal, este se cambiará a false cuando
// se empiezen las practicas con interfaz grafica.

public class BaseDeDatos {

	private final String SEPARADOR = "\n----------------------------------------------------------------------------------------------------";

	public static final DateTimeFormatter DMY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter HMS = DateTimeFormatter.ofPattern("hh:mm:ss");
	public static final boolean EN_TERMINAL = true;
	public final GeneradorDatosINE generadorDatosINE = new GeneradorDatosINE();

	private Hashtable<String, Cliente> clientes = new Hashtable<String, Cliente>();
	private Hashtable<String, Factura> facturas = new Hashtable<String, Factura>();

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
		} else {
			IO.out.print("----------------YA EXISTE UN CLIENTE CON NIF----------------");
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

	public boolean existsNif(String nif) {
		if (getCliente(nif) == null) return false;
		return true;
	}

	public void exitWithoutSave() {
		System.exit(0);
	}

}
