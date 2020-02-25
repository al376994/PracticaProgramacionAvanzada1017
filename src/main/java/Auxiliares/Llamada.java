package Auxiliares;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
		DateTimeFormatter dmy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter hms = DateTimeFormatter.ofPattern("hh:mm:ss");
		return "Llamada al número " + telefono + " el " + fecha.format(dmy) +
				" a las " + hora.format(hms) + " con una duración de " + duracion.getSeconds() + " segundos.";
	}
}
