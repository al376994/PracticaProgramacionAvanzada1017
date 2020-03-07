package Auxiliares;

import BaseDeDatos.BaseDeDatos;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

// La clase Llamada representa una llamada de un cliente. Se puede crear una llamada directamente con el contructor
// o mediante la funcion darDeAlta() que utilizara la clase Input para que el usuario introduzca los datos de una
// nueva llamada, la cree, y la devuelva. El toString es personalizado.

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

	static public Llamada darDeAlta() {
		return IO.in.askNewLlamada();
	}

	public Duration getDuracion() {
		return duracion;
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
