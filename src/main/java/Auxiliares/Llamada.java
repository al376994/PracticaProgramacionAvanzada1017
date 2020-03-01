package Auxiliares;

import BaseDeDatos.BaseDeDatos;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class Llamada implements TieneFecha {

	private String telefono;
	private LocalDate fecha;
	private LocalTime hora;
	private Duration duracion;

	public Llamada(String telefono, LocalDate fecha, LocalTime hora, Duration duracion) {
		this.telefono = telefono;
		this.fecha = fecha;
		this.hora = hora;
		this.duracion = duracion;
	}

	@Override
	public LocalDate getFecha() {
		return fecha;
	}

	@Override
	public String toString(){
		return "Llamada al número " + telefono + " el " + fecha.format(BaseDeDatos.DMY) +
				" a las " + hora.format(BaseDeDatos.HMS) + " con una duración de " + duracion.getSeconds() + " segundos.";
	}
}
