package BaseDeDatos;

import Auxiliares.Direccion;
import Auxiliares.Llamada;
import Auxiliares.Tarifa;
import Auxiliares.TieneFecha;
import es.uji.www.GeneradorDatosINE;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Hashtable;
import java.util.List;

// La clase Cliente representa a un cliente, se puede crear de 2 formas, o bien con el constructor, o bien con la
// funcion darDeAlta(), esta funcion creara un cliente que puede ser particular o empresa y puede pedir información al
// usuario o crear un cliente aleatorio. En esta clase también se maneja la creación de llamadas ya que estas siempre
// estan vinculadas a un cliente y no tienen clave de identificacion como las facturas. Además de guardar llamadas y
// facturas propias un cliente tiene un dato interno que es codigoFacturaActual, este se actualiza cada vez que se añade
// una factura a ese cliente en concreto.
// El toString es personalizado.

public abstract class Cliente implements TieneFecha, Serializable {

	//private BaseDeDatos baseDeDatos;
	private String nombre;
	private String nif;
	private Direccion direccion;
	private String correoElectronico;
	private LocalDate fechaDeAlta;
	private Tarifa tarifa;
	private Hashtable<String, Factura> facturas;
	private List<Llamada> llamadas;
	private int codigoFacturaActual = 1;

	Cliente(/*BaseDeDatos baseDeDatos, */String nombre, String nif, Direccion direccion, String correoElectronico, LocalDate fechaDeAlta, Tarifa tarifa, Hashtable<String, Factura> facturas, List<Llamada> llamadas) {
		//this.baseDeDatos = baseDeDatos;
		this.nombre = nombre;
		this.nif = nif;
		this.direccion = direccion;
		this.correoElectronico = correoElectronico;
		this.fechaDeAlta = fechaDeAlta;
		this.tarifa = tarifa;
		this.facturas = facturas;
		this.llamadas = llamadas;
	}

	public static Cliente darDeAlta(/*BaseDeDatos baseDeDatos, */boolean esParticular, boolean esRandom) {
		if (esParticular) {
			Particular particular;
			if (!esRandom) particular = Particular.darDeAlta();
			else particular = Particular.darDeAltaRandom();
			return particular;
		} else {
			Empresa empresa;
			if (!esRandom) empresa = Empresa.darDeAlta();
			else empresa = Empresa.darDeAltaRandom();
			return empresa;
		}

	}

	public String getNombre() {
		return nombre;
	}

	public String getNif() {
		return nif;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void cambiarTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public void darDeAltaLlamadaRandom() {
		llamadas.add(Llamada.generateRandomLlamada());
		updateImporteFacturas();
	}

	public void darDeAltaLlamada(Llamada llamada) {
		llamadas.add(llamada);
		updateImporteFacturas();
	}

	public void addFactura(Factura factura) {
		facturas.put(factura.getCodigo(), factura);
	}

	public void updateCodigoFacturaActual() {
		codigoFacturaActual++;
	}

	public String getNextFacturaCodigo() {
		int cod = codigoFacturaActual;
		return nif + cod;
	}

	private void updateImporteFacturas() {
		for (Factura factura : facturas.values()) {
			factura.updateImporte();
		}
	}

	@Override
	public LocalDate getFecha() {
		return fechaDeAlta;
	}

	@Override
	public String toString() {
		return "\t\tNIF: " + nif + "\nProvincia: " + direccion.getProvincia() +
				"\t\tPoblación: " + direccion.getPoblacion() + "\t\tCP: " + direccion.getCp() +
				"\nCorreo electonico: " + correoElectronico + "\t\tPrecio Tarifa: " + tarifa.getPrecio() +
				"\nFecha de alta: " + fechaDeAlta.format(BaseDeDatos.DMY);
	}

	public List<Llamada> getLlamadas() {
		return llamadas;
	};

	public Hashtable<String, Factura> getFacturas() {
		return facturas;
	};
}
