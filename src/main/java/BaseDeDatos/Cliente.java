package BaseDeDatos;

import Auxiliares.Direccion;
import Auxiliares.Llamada;
import Auxiliares.Tarifa;
import Auxiliares.TieneFecha;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Hashtable;
import java.util.List;

public abstract class Cliente implements TieneFecha {

	protected BaseDeDatos baseDeDatos;
	protected String nombre;
	protected String nif;
	protected Direccion direccion;
	protected String correoElectronico;
	protected LocalDate fechaDeAlta;
	protected Tarifa tarifa;
	protected Hashtable<String, Factura> facturas = new Hashtable<String, Factura>();
	protected List<Llamada> llamadas;
	protected int codigoFacturaActual = 1;


	public Cliente(BaseDeDatos baseDeDatos, String nombre, String nif, Direccion direccion, String correoElectronico, LocalDate fechaDeAlta, Tarifa tarifa, Hashtable<String, Factura> facturas, List<Llamada> llamadas) {
		this.baseDeDatos = baseDeDatos;
		this.nombre = nombre;
		this.nif = nif;
		this.direccion = direccion;
		this.correoElectronico = correoElectronico;
		this.fechaDeAlta = fechaDeAlta;
		this.tarifa = tarifa;
		this.facturas = facturas;
		this.llamadas = llamadas;
	}

	public Cliente() {}

	public abstract <T extends Cliente> T darDeAlta(BaseDeDatos baseDeDatos);
	public abstract <T extends Cliente> T darDeAltaRandom(BaseDeDatos baseDeDatos);

	public String getNif() {
		return nif;
	}

	public void cambiarTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public List<Llamada> listaLlamadas() {
		return llamadas;
	}

	public void darDeAltaLlamada() {
		llamadas.add(baseDeDatos.generateRandomLlamada());
	}

	public void darDeAltaLlamada(Llamada llamada) {
		llamadas.add(llamada);
	}

	public String getNextFacturaCodigo() {
		int cod = codigoFacturaActual;
		codigoFacturaActual++;
		return nif + cod;
	}

	@Override
	public LocalDate getFecha() {
		return fechaDeAlta;
	}

	@Override
	public String toString() {
		return "\t\tNIF: " + nif + "\nProvincia: " + direccion.getProvincia() +
				"\t\tPoblación: " + direccion.getPoblacion() + "\t\tCP: " + direccion.getCp() +
				"\nCorreo electonico: " + correoElectronico + "\t\tPrecio Tarifa" + tarifa.getPrecio() +
				"\nFecha de alta: " + fechaDeAlta.format(BaseDeDatos.DMY);
	}

}
