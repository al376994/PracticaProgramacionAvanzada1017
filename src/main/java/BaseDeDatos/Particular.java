package BaseDeDatos;

import Auxiliares.Direccion;
import Auxiliares.IO;
import Auxiliares.Llamada;
import Auxiliares.Tarifa;
import es.uji.www.GeneradorDatosINE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Particular extends Cliente {

	private String apellido;

	private Particular(BaseDeDatos baseDeDatos, String nombre, String nif, Direccion direccion, String correoElectronico, LocalDate fechaDeAlta, Tarifa tarifa, List<Integer> facturas, List<Llamada> llamadas, String apellido) {
		super(baseDeDatos, nombre, nif, direccion, correoElectronico, fechaDeAlta, tarifa, facturas, llamadas);
		this.apellido = apellido;
	}

	public Particular() {}

	@Override
	public Particular darDeAltaRandom(BaseDeDatos baseDeDatos) {
		GeneradorDatosINE g = new GeneradorDatosINE();

		String nombreParticular = g.getNombre();
		String nifParticular = g.getNIF();
		String provinciaParticular = g.getProvincia();
		Direccion direccionParticular = new Direccion(provinciaParticular, g.getPoblacion(provinciaParticular));
		String correoElectronicoParticular = nombreParticular + "@correo.com";
		List<Integer> facturasParticular = new ArrayList<>();
		List<Llamada> llamadasParticular = new ArrayList<>();
		String apellidoParticular = g.getApellido();

		return new Particular(baseDeDatos, nombreParticular, nifParticular, direccionParticular, correoElectronicoParticular,
				LocalDate.now(), new Tarifa(), facturasParticular, llamadasParticular, apellidoParticular);
	}

	@Override
	public Particular darDeAlta(BaseDeDatos baseDeDatos) {
		GeneradorDatosINE g = new GeneradorDatosINE();

		String nombreParticular = IO.in.fromTerminalAskString("Nombre del cliente:");
		String nifParticular = IO.in.fromTerminalAskString("NIF del cliente:");
		String provinciaParticular = IO.in.fromTerminalAskString("Provincia del cliente:");
		Direccion direccionParticular = new Direccion(provinciaParticular, IO.in.fromTerminalAskString("Poblacion del cliente:"));
		String correoElectronicoParticular = nombreParticular + "@correo.com";
		List<Integer> facturasParticular = new ArrayList<>();
		List<Llamada> llamadasParticular = new ArrayList<>();
		String apellidoParticular = IO.in.fromTerminalAskString("Apellido del cliente:");

		return new Particular(baseDeDatos, nombreParticular, nifParticular, direccionParticular, correoElectronicoParticular,
				LocalDate.now(), new Tarifa(), facturasParticular, llamadasParticular, apellidoParticular);
	}

	@Override
	public String toString() {
		return 	"Particular" + "\nNombre: " + nombre + " " + apellido + "\t\tNIF: " + nif + "\nProvincia: " + direccion.getProvincia() +
				"\t\tPoblación: " + direccion.getPoblacion() + "\t\tCP: " + direccion.getCp() +
				"\nCorreo electonico: " + correoElectronico + "\t\tPrecio Tarifa: " + tarifa.getPrecio() +
				"\nFecha de alta: " + fechaDeAlta.format(BaseDeDatos.dmy);
	}

}
