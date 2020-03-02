package BaseDeDatos;

import Auxiliares.Llamada;
import Auxiliares.PeriodoFacturacion;
import Auxiliares.Tarifa;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FacturaTest {

	static Cliente c1;
	static Cliente c2;
	static Factura f1;
	static Factura f2;
	static Factura f3;
	static Llamada l1;
	static Llamada l2;
	static Llamada l3;
	static Llamada l4;
	static Llamada l5;
	static Llamada l6;

	@BeforeAll
	static void generateClientesFacturasYLlamadas() {
		BaseDeDatos b1 = new BaseDeDatos();
		l1 = new Llamada("1", LocalDate.now(), LocalTime.now(), Duration.ofSeconds(10));
		l2 = new Llamada("1", LocalDate.now().plusDays(15), LocalTime.now(), Duration.ofSeconds(10));
		l3 = new Llamada("1", LocalDate.now().plusMonths(3), LocalTime.now(), Duration.ofSeconds(10));
		c1 = new Particular().darDeAltaRandom(b1);
		c1.darDeAltaLlamada(l1);
		c1.darDeAltaLlamada(l2);
		c1.darDeAltaLlamada(l3);
		f1 = new Factura(b1, c1.getNif() + "1", new Tarifa(), LocalDate.now(), new PeriodoFacturacion(LocalDate.now(), LocalDate.now().plusMonths(1)), c1);
		f2 = new Factura(b1, c1.getNif() + "2", new Tarifa(), LocalDate.now(), new PeriodoFacturacion(LocalDate.now().plusDays(20), LocalDate.now().plusMonths(5)), c1);
		l4 = new Llamada("1", LocalDate.now(), LocalTime.now(), Duration.ofSeconds(5));
		l5 = new Llamada("1", LocalDate.now().plusDays(15), LocalTime.now(), Duration.ofSeconds(5));
		l6 = new Llamada("1", LocalDate.now().plusMonths(3), LocalTime.now(), Duration.ofSeconds(5));
		c2 = new Particular().darDeAltaRandom(b1);
		c2.darDeAltaLlamada(l4);
		c2.darDeAltaLlamada(l5);
		c2.darDeAltaLlamada(l6);
		f3 = new Factura(b1, c2.getNif() + "1", new Tarifa(), LocalDate.now(), new PeriodoFacturacion(LocalDate.now(), LocalDate.now().plusMonths(6)), c2);

		b1.addFactura(f1);
	}

	@Test
	void getImporte() {
		assertEquals(0.15*2*10, f1.getImporte());
		assertEquals(0.15*1*10, f2.getImporte());
		assertEquals(0.15*3*5, f3.getImporte());
	}
}