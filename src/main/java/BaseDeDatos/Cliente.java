package BaseDeDatos;

import Auxiliares.Direccion;
import Auxiliares.Llamada;
import Auxiliares.Tarifa;
import Auxiliares.TieneFecha;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
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

	public abstract <T extends Cliente> T darDeAlta(BaseDeDatos baseDeDatos);

	public void cambiarTarifa(double precio) {
		tarifa = new Tarifa(precio);
	}

	public List<Llamada> listaLlamadas() {
		return llamadas;
	}

	public void darDeAltaLlamada() {
		llamadas.add(baseDeDatos.generateRandomLlamada());
	}

	public void darDeAltaLlamada(String telefono, LocalDate date, LocalTime time, Duration duration) {
		llamadas.add(new Llamada(telefono, date, time, duration));
	}

	@Override
	public LocalDate getFecha() {
		return fechaDeAlta;
	}

	@Override
	public String toString() {
		return "";
	}

}
