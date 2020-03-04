package BaseDeDatos;

import Auxiliares.Direccion;
import Auxiliares.IO;
import Auxiliares.Llamada;
import Auxiliares.Tarifa;
import es.uji.www.GeneradorDatosINE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Particular extends Cliente {

	private String apellido;

	private Particular(BaseDeDatos baseDeDatos, String nombre, String nif, Direccion direccion, String correoElectronico, LocalDate fechaDeAlta, Tarifa tarifa, Hashtable<String, Factura> facturas, List<Llamada> llamadas, String apellido) {
		super(baseDeDatos, nombre, nif, direccion, correoElectronico, fechaDeAlta, tarifa, facturas, llamadas);
		this.apellido = apellido;
	}

	public Particular() {}

	//@Override
	static Particular darDeAltaRandom(BaseDeDatos baseDeDatos) {
		GeneradorDatosINE g = new GeneradorDatosINE();

		String nombreParticular = g.getNombre();
		String nifParticular = g.getNIF();
		String provinciaParticular = g.getProvincia();
		Direccion direccionParticular = new Direccion(provinciaParticular, g.getPoblacion(provinciaParticular));
		String correoElectronicoParticular = nombreParticular + "@correo.com";
		Hashtable<String, Factura> facturasParticular = new Hashtable<String, Factura>();
		List<Llamada> llamadasParticular = new ArrayList<>();
		String apellidoParticular = g.getApellido();

		return new Particular(baseDeDatos, nombreParticular, nifParticular, direccionParticular, correoElectronicoParticular,
				LocalDate.now(), new Tarifa(), facturasParticular, llamadasParticular, apellidoParticular);
	}

	//@Override
	static Particular darDeAlta(BaseDeDatos baseDeDatos) {
		GeneradorDatosINE g = new GeneradorDatosINE();
		String[] data = IO.in.askNewClienteData(true);

		String nombreParticular = data[0];
		String nifParticular = data[1];
		String provinciaParticular = data[2];
		Direccion direccionParticular = new Direccion(provinciaParticular, data[3]);
		String correoElectronicoParticular = nombreParticular + "@correo.com";
		Hashtable<String, Factura> facturasParticular = new Hashtable<String, Factura>();
		List<Llamada> llamadasParticular = new ArrayList<>();
		String apellidoParticular = data[4];

		return new Particular(baseDeDatos, nombreParticular, nifParticular, direccionParticular, correoElectronicoParticular,
				LocalDate.now(), new Tarifa(), facturasParticular, llamadasParticular, apellidoParticular);
	}

	@Override
	public String toString() {
		return 	"Particular" + "\nNombre: " + getNombre() + " " + apellido + super.toString();
	}

}
