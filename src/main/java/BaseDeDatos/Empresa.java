package BaseDeDatos;

import Auxiliares.Direccion;
import Auxiliares.IO;
import Auxiliares.Llamada;
import Auxiliares.Tarifa;
import es.uji.www.GeneradorDatosINE;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

// Una subclase (hijo) de la clase Cliente, contiene las funciones a las que llama cliente para crear una nueva
// Empresa, ya sea una aleatoria o una creada por el usuario. El toString es personalizado.

public class Empresa extends Cliente implements Serializable {

	private Empresa(BaseDeDatos baseDeDatos, String nombre, String nif, Direccion direccion, String correoElectronico, LocalDate fechaDeAlta, Tarifa tarifa, Hashtable<String, Factura> facturas, List<Llamada> llamadas) {
		super(baseDeDatos, nombre, nif, direccion, correoElectronico, fechaDeAlta, tarifa, facturas, llamadas);
	}

	static Empresa darDeAltaRandom(BaseDeDatos baseDeDatos) {
		GeneradorDatosINE g = new GeneradorDatosINE();

		String nombreEmpresa = g.getNombre();
		String nifEmpresa = g.getNIF();
		String provinciaEmpresa = g.getProvincia();
		Direccion direccionEmpresa = new Direccion(provinciaEmpresa, g.getPoblacion(provinciaEmpresa));
		String correoElectronicoEmpresa = nombreEmpresa + "@correo.com";
		Hashtable<String, Factura> facturasEmpresa = new Hashtable<String, Factura>();
		List<Llamada> llamadasEmpresa = new ArrayList<>();

		return new Empresa(baseDeDatos, nombreEmpresa, nifEmpresa, direccionEmpresa, correoElectronicoEmpresa,
				LocalDate.now(), new Tarifa(), facturasEmpresa, llamadasEmpresa);
	}

	static Empresa darDeAlta(BaseDeDatos baseDeDatos) {
		String[] data = IO.in.askNewClienteData(false);
		String nombreEmpresa = data[0];
		String nifEmpresa = data[1];
		String provinciaEmpresa = data[2];
		Direccion direccionEmpresa = new Direccion(provinciaEmpresa, data[3]);
		String correoElectronicoEmpresa = nombreEmpresa + "@correo.com";
		Hashtable<String, Factura> facturasEmpresa = new Hashtable<String, Factura>();
		List<Llamada> llamadasEmpresa = new ArrayList<>();

		return new Empresa(baseDeDatos, nombreEmpresa, nifEmpresa, direccionEmpresa, correoElectronicoEmpresa,
				LocalDate.now(), new Tarifa(), facturasEmpresa, llamadasEmpresa);
	}

	@Override
	public String toString() {
		return 	"Empresa" + "\nNombre: " + getNombre() + super.toString();
	}

}
