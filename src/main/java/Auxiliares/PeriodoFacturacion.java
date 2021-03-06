package Auxiliares;

import BaseDeDatos.BaseDeDatos;

import java.io.Serializable;
import java.time.LocalDate;

// La clase PeriodoFacturacion representa el periodo entre 2 fechas, y se utiliza para indicar el inicio y el final de
// una facturacion. Se puede crear un periodo directamente con el contructor o mediante la funcion createFromDates() que
// utilizara la clase Input para que el usuario introduzca los datos de un nuevo periodo, lo cree, y lo devuelva.
// También tiene la funcion inPeriodoFacturacion que recive una fecha y devuelve true si esta esta dentro de periodo y
// false en el caso contrario. El toString es personalizado.

public class PeriodoFacturacion implements Serializable {
	private LocalDate start;
	private LocalDate end;

	public PeriodoFacturacion(LocalDate start, LocalDate end) {
		if (end.isAfter(start)) {
			this.start = start;
			this.end = end;
		} else {
			this.start = end;
			this.end = start;
		}
	}

	public boolean inPeriodoFacturacion(LocalDate date) {
		if (date.isAfter(start.minusDays(1)) && date.isBefore(end)) return true;
		else return false;
	}

	public LocalDate getStart() {
		return start;
	}

	public LocalDate getEnd() {
		return end;
	}

	@Override
	public String toString() {
		return "Desde " + start.format(BaseDeDatos.DMY) + " asta " + end.format(BaseDeDatos.DMY);
	}
}
