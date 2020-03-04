package BaseDeDatos;

import Auxiliares.Direccion;
import Auxiliares.Llamada;
import Auxiliares.Tarifa;
import Auxiliares.TieneFecha;
import es.uji.www.GeneradorDatosINE;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
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

	Cliente(BaseDeDatos baseDeDatos, String nombre, String nif, Direccion direccion, String correoElectronico, LocalDate fechaDeAlta, Tarifa tarifa, Hashtable<String, Factura> facturas, List<Llamada> llamadas) {
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

	static Cliente darDeAlta(BaseDeDatos baseDeDatos, boolean esParticular, boolean esRandom) {
		if (esParticular) {
			Particular particular;
			if (esRandom) particular = Particular.darDeAlta(baseDeDatos);
			else particular = Particular.darDeAltaRandom(baseDeDatos);
			return particular;
		} else {
			Empresa empresa;
			if (esRandom) empresa = Empresa.darDeAlta(baseDeDatos);
			else empresa = Empresa.darDeAltaRandom(baseDeDatos);
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

	private Llamada generateRandomLlamada() {
		GeneradorDatosINE g = new GeneradorDatosINE();
		String tel =  "9" + g.getNIF().substring(0, 7);
		Duration dur = BaseDeDatos.randomDuration(10, 60);
		return new Llamada(tel, LocalDate.now(), LocalTime.now(), dur);
	}

	public void darDeAltaLlamadaRandom() {
		llamadas.add(generateRandomLlamada());
	}

	public void darDeAltaLlamada(Llamada llamada) {
		llamadas.add(llamada);
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
