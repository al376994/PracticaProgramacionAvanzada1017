package BaseDeDatos;

import Auxiliares.Direccion;
import Auxiliares.Llamada;
import Auxiliares.Tarifa;
import Auxiliares.TieneFecha;

import java.time.LocalDate;
import java.util.Hashtable;
import java.util.List;

public abstract class Cliente implements TieneFecha {

	private BaseDeDatos baseDeDatos;
	private String nombre;
	private String nif;
	private Direccion direccion;
	private String correoElectronico;
	private LocalDate fechaDeAlta;
	private Tarifa tarifa;
	private Hashtable<String, Factura> facturas = new Hashtable<String, Factura>();
	private List<Llamada> llamadas;
	private int codigoFacturaActual = 1;


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

	public List<Llamada> listaLlamadas() {
		return llamadas;
	}

	public void darDeAltaLlamadaRandom() {
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

	public List<Llamada> getLlamadas() {
		return llamadas;
	};
}
