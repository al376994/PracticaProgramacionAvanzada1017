package BaseDeDatos;

import Auxiliares.Direccion;
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

	@Override
	public Empresa darDeAlta(BaseDeDatos baseDeDatos) {
		GeneradorDatosINE g = new GeneradorDatosINE();

		String nombreEmpresa = g.getNombre();
		String nifEmpresa = g.getNIF();
		String provinciaEmpresa = g.getProvincia();
		Direccion direccionEmpresa = new Direccion(provinciaEmpresa, g.getPoblacion(provinciaEmpresa));
		String correoElectronicoEmpresa = nifEmpresa + "@correo.com";
		List<Integer> facturasEmpresa = new ArrayList<>();
		List<Llamada> llamadasEmpresa = new ArrayList<>();

		return new Empresa(baseDeDatos, nombreEmpresa, nifEmpresa, direccionEmpresa, correoElectronicoEmpresa,
				LocalDate.now(), new Tarifa(), facturasEmpresa, llamadasEmpresa);
	}

}
