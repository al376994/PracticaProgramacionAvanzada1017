import Auxiliares.Direccion;
import Auxiliares.Llamada;
import Auxiliares.TieneFecha;

import java.time.LocalDate;
import java.util.List;

public abstract class Cliente implements TieneFecha {

	private BaseDeDatos baseDeDatos;
	private String nombre;
	private String nif;
	private Direccion direccion;
	private String correoElectronico;
	private LocalDate fechaDeAlta;
	private Tarifa tarifa;
	private List<Integer> facturas;
	private List<Llamada> llamadas;


	public Cliente(BaseDeDatos baseDeDatos, String nombre, String nif, Direccion direccion, String correoElectronico, LocalDate fechaDeAlta, Tarifa tarifa, List<Integer> facturas, List<Llamada> llamadas) {
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

	@Override
	public LocalDate getFecha() {
		return fechaDeAlta;
	}



}
