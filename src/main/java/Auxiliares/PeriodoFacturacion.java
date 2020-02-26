package Auxiliares;

import BaseDeDatos.BaseDeDatos;

import java.time.LocalDate;

public class PeriodoFacturacion {
	private LocalDate start;
	private LocalDate end;

	public PeriodoFacturacion(LocalDate start, LocalDate end) {
		this.start = start;
		this.end = end;
	}

	public LocalDate getStart() {
		return start;
	}

	public LocalDate getEnd() {
		return end;
	}

	@Override
	public String toString() {
		return "Desde " + start.format(BaseDeDatos.dmy) + " asta " + end.format(BaseDeDatos.dmy);
	}
}
