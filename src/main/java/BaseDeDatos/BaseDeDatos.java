package BaseDeDatos;

import Auxiliares.*;
import es.uji.www.GeneradorDatosINE;

import java.io.*;
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

public class BaseDeDatos implements Serializable {

	private final String SEPARADOR = "\n----------------------------------------------------------------------------------------------------";

	public static final DateTimeFormatter DMY = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	public static final DateTimeFormatter HMS = DateTimeFormatter.ofPattern("hh:mm:ss");
	public static final boolean EN_TERMINAL = true;

	private Hashtable<String, Cliente> clientes = new Hashtable<String, Cliente>();
	private Hashtable<String, Factura> facturas = new Hashtable<String, Factura>();

	public Hashtable<String, Cliente> getClientes() {
		return clientes;
	}

	public Hashtable<String, Factura> getFacturas() {
		return facturas;
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

	public Cliente getCliente(String nif) {
		return clientes.get(nif);
	}

	public boolean borrarCliente(String nif) {
		if (clientes.containsKey(nif)) {
			clientes.remove(nif);
			return true;
		}
		return false;
	}	//TODO test

	public boolean cambiarTarifa(String nif, Tarifa tarifa) {
		Cliente cliente = getCliente(nif);
		if (cliente != null) {
			cliente.cambiarTarifa(tarifa);
			return true;
		}
		return false;
	}

	public boolean addLlamada(String nif, Llamada llamada) {
		Cliente cliente = getCliente(nif);
		int nLlamadas = cliente.getLlamadas().size();
		cliente.darDeAltaLlamada(llamada);
		return cliente.getLlamadas().size() == nLlamadas+1;
	}

	public boolean addFactura(Factura factura) {
		Cliente cliente = factura.getCliente();
		int nFacturasCliente = cliente.getFacturas().size();
		int nFacturas = facturas.size();
		cliente.addFactura(factura);
		facturas.put(factura.getCodigo(), factura);
		cliente.updateCodigoFacturaActual();
		return (cliente.getFacturas().size() > nFacturasCliente) && (facturas.size() > nFacturas);

	}

	public <T extends TieneFecha> Collection<T> elementosEntreFechas(ArrayList<T> lista, LocalDate desde, LocalDate hasta) {
		PeriodoFacturacion entreEstasFechas = new PeriodoFacturacion(desde, hasta);
		ArrayList<T> listaEntreFechas = new ArrayList<>();
		for (T elemento : lista) {
			if (entreEstasFechas.inPeriodoFacturacion(elemento.getFecha())) listaEntreFechas.add(elemento);
		}
		return listaEntreFechas;
	}

	public void saveData(){
		FileOutputStream archive = null;
		ObjectOutputStream object = null;
		try {
			archive = new FileOutputStream("data/clientes.bin");
			object = new ObjectOutputStream(archive);
			object.writeObject(clientes);
			archive = new FileOutputStream("data/facturas.bin");
			object = new ObjectOutputStream(archive);
			object.writeObject(facturas);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadData(){
		FileInputStream archive = null;
		ObjectInputStream object = null;
		try {
			archive = new FileInputStream("data/clientes.bin");
			object = new ObjectInputStream(archive);
			clientes = (Hashtable<String,Cliente>) object.readObject();
			archive = new FileInputStream("data/facturas.bin");
			object = new ObjectInputStream(archive);
			facturas = (Hashtable<String,Factura>) object.readObject();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void exitWithoutSave() {
		System.exit(0);
	}

}
