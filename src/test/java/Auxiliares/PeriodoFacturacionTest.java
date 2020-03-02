package Auxiliares;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PeriodoFacturacionTest {

	static PeriodoFacturacion pf1;
	static PeriodoFacturacion pf2;
	static PeriodoFacturacion pf3;
	static LocalDate d1;
	static LocalDate d2;
	static LocalDate d3;
	static LocalDate d4;
	static LocalDate d5;
	static LocalDate d6;

	@BeforeAll
	static void generatePeriodosYFechas() {
		d1 = LocalDate.of(2000, 1, 1);
		d2 = LocalDate.of(2000, 2, 1);
		pf1 = new PeriodoFacturacion(d1, d2);
		d3 = LocalDate.of(2000, 6, 1);
		d4 = LocalDate.of(2001, 1, 1);
		pf2 = new PeriodoFacturacion(d3, d4);
		d5 = LocalDate.of(2000, 1, 2);
		d6 = LocalDate.of(2000, 1, 15);
		pf3 = new PeriodoFacturacion(d5, d6);
	}

	@AfterAll
	static void deletePeriodosYFechas() {
		d1 = null;
		d2 = null;
		pf1 = null;
		d3 = null;
		d4 = null;
		pf2 = null;
		d5 = null;
		d6 = null;
		pf3 = null;
	}

	@Test
	void inPeriodoFacturacion() {
		assertTrue( pf1.inPeriodoFacturacion(d1));
		assertFalse(pf1.inPeriodoFacturacion(d2));
		assertFalse(pf1.inPeriodoFacturacion(d3));
		assertFalse(pf1.inPeriodoFacturacion(d4));
		assertTrue( pf1.inPeriodoFacturacion(d5));
		assertTrue( pf1.inPeriodoFacturacion(d6));

		assertFalse(pf2.inPeriodoFacturacion(d1));
		assertFalse(pf2.inPeriodoFacturacion(d2));
		assertTrue( pf2.inPeriodoFacturacion(d3));
		assertFalse(pf2.inPeriodoFacturacion(d4));
		assertFalse(pf2.inPeriodoFacturacion(d5));
		assertFalse(pf2.inPeriodoFacturacion(d6));

		assertFalse(pf3.inPeriodoFacturacion(d1));
		assertFalse(pf3.inPeriodoFacturacion(d2));
		assertFalse(pf3.inPeriodoFacturacion(d3));
		assertFalse(pf3.inPeriodoFacturacion(d4));
		assertTrue( pf3.inPeriodoFacturacion(d5));
		assertFalse(pf3.inPeriodoFacturacion(d6));
	}
}