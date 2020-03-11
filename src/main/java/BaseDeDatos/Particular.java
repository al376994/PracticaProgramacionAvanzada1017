package BaseDeDatos;

import Auxiliares.*;
import es.uji.www.GeneradorDatosINE;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

// Una subclase (hijo) de la clase Cliente, contiene las funciones a las que llama cliente para crear un nuevo
// Particual, ya sea una aleatoria o una creada por el usuario. Además Particualr añade el atributo appelido.
// El toString es personalizado.

public class Particular extends Cliente implements Serializable {

	private String apellido;

	private Particular(String nombre, String nif, Direccion direccion, String correoElectronico, LocalDate fechaDeAlta, Tarifa tarifa, Hashtable<String, Factura> facturas, List<Llamada> llamadas, String apellido) {
		super(nombre, nif, direccion, correoElectronico, fechaDeAlta, tarifa, facturas, llamadas);
		this.apellido = apellido;
	}

	static Particular darDeAltaRandom() {
		GeneradorDatosINE g = new GeneradorDatosINE();

		String nombreParticular = g.getNombre();
		String nifParticular = g.getNIF();
		String provinciaParticular = g.getProvincia();
		Direccion direccionParticular = new Direccion(provinciaParticular, g.getPoblacion(provinciaParticular));
		String correoElectronicoParticular = nombreParticular.replace(' ', '_') + "@correo.com";
		Hashtable<String, Factura> facturasParticular = new Hashtable<String, Factura>();
		List<Llamada> llamadasParticular = new ArrayList<>();
		String apellidoParticular = g.getApellido();

		return new Particular(nombreParticular, nifParticular, direccionParticular, correoElectronicoParticular,
				LocalDate.now(), new Tarifa(), facturasParticular, llamadasParticular, apellidoParticular);
	}

	static Particular darDeAlta(InOut entradaSalida) {
		String[] data = entradaSalida.askNewClienteData(true);

		String nombreParticular = data[0];
		String nifParticular = data[1];
		String provinciaParticular = data[2];
		Direccion direccionParticular = new Direccion(provinciaParticular, data[3]);
		String correoElectronicoParticular = nombreParticular.replace(' ', '_') + "@correo.com";
		Hashtable<String, Factura> facturasParticular = new Hashtable<String, Factura>();
		List<Llamada> llamadasParticular = new ArrayList<>();
		String apellidoParticular = data[4];

		return new Particular(nombreParticular, nifParticular, direccionParticular, correoElectronicoParticular,
				LocalDate.now(), new Tarifa(), facturasParticular, llamadasParticular, apellidoParticular);
	}

	@Override
	public String toString() {
		return 	"Particular" + "\nNombre: " + getNombre() + " " + apellido + super.toString();
	}

}
