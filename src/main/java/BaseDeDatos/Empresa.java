package BaseDeDatos;

import Auxiliares.Direccion;
import Auxiliares.IO;
import Auxiliares.Llamada;
import Auxiliares.Tarifa;
import es.uji.www.GeneradorDatosINE;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Empresa extends Cliente {

	private Empresa(BaseDeDatos baseDeDatos, String nombre, String nif, Direccion direccion, String correoElectronico, LocalDate fechaDeAlta, Tarifa tarifa, List<Integer> facturas, List<Llamada> llamadas) {
		super(baseDeDatos, nombre, nif, direccion, correoElectronico, fechaDeAlta, tarifa, facturas, llamadas);
	}

	public Empresa() {}

	@Override
	public Empresa darDeAltaRandom(BaseDeDatos baseDeDatos) {
		GeneradorDatosINE g = new GeneradorDatosINE();

		String nombreEmpresa = g.getNombre();
		String nifEmpresa = g.getNIF();
		String provinciaEmpresa = g.getProvincia();
		Direccion direccionEmpresa = new Direccion(provinciaEmpresa, g.getPoblacion(provinciaEmpresa));
		String correoElectronicoEmpresa = nombreEmpresa + "@correo.com";
		List<Integer> facturasEmpresa = new ArrayList<>();
		List<Llamada> llamadasEmpresa = new ArrayList<>();

		return new Empresa(baseDeDatos, nombreEmpresa, nifEmpresa, direccionEmpresa, correoElectronicoEmpresa,
				LocalDate.now(), new Tarifa(), facturasEmpresa, llamadasEmpresa);
	}

	@Override
	public Empresa darDeAlta(BaseDeDatos baseDeDatos) {
		GeneradorDatosINE g = new GeneradorDatosINE();

		String nombreEmpresa = IO.in.fromTerminalAskString("Nombre de la empresa:");
		String nifEmpresa = IO.in.fromTerminalAskString("NIF de la empresa:");
		String provinciaEmpresa = IO.in.fromTerminalAskString("Provincia de la empresa:");
		Direccion direccionEmpresa = new Direccion(provinciaEmpresa, IO.in.fromTerminalAskString("Población de la poblacion:"));
		String correoElectronicoEmpresa = nombreEmpresa + "@correo.com";
		List<Integer> facturasEmpresa = new ArrayList<>();
		List<Llamada> llamadasEmpresa = new ArrayList<>();

		return new Empresa(baseDeDatos, nombreEmpresa, nifEmpresa, direccionEmpresa, correoElectronicoEmpresa,
				LocalDate.now(), new Tarifa(), facturasEmpresa, llamadasEmpresa);
	}

	@Override
	public String toString() {
		return 	"Empresa" + "\nNombre: " + nombre + "\t\tNIF: " + nif + "\nProvincia: " + direccion.getProvincia() +
				"\t\tPoblación: " + direccion.getPoblacion() + "\t\tCP: " + direccion.getCp() +
				"\nCorreo electonico: " + correoElectronico + "\t\tPrecio Tarifa" + tarifa.getPrecio();
	}

}
