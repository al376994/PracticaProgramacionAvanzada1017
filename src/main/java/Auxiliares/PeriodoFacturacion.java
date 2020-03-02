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

	static public PeriodoFacturacion askDates() {
		LocalDate start = IO.in.askDate("del principio del periodo de facturacion");
		LocalDate end = IO.in.askDate("del final del periodo de facturacion");
		return new PeriodoFacturacion(start, end);
	}

	public boolean inPeriodoFacturacion(LocalDate date) {
		if (date.isAfter(start) && date.isBefore(end)) return true;
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
