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

	protected BaseDeDatos baseDeDatos;
	protected String nombre;
	protected String nif;
	protected Direccion direccion;
	protected String correoElectronico;
	protected LocalDate fechaDeAlta;
	protected Tarifa tarifa;
	protected List<Integer> facturas;
	protected List<Llamada> llamadas;


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

	public Cliente() {}

	public abstract <T extends Cliente> T darDeAlta(BaseDeDatos baseDeDatos);
	public abstract <T extends Cliente> T darDeAltaRandom(BaseDeDatos baseDeDatos);

	public String getNif() {
		return nif;
	}

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
