package BaseDeDatos;

import Auxiliares.IOTerminal;
import Auxiliares.InOut;
import Auxiliares.Llamada;
import Auxiliares.Tarifa;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class BaseDeDatosTest {

	BaseDeDatos b1;
	Cliente c1;
	Cliente c2;
	Llamada l1;
	Llamada l2;
	Factura f1;
	Factura f2;

	@BeforeEach
	void generateAll() {
		b1 = new BaseDeDatos();
		generateCliente();
		generateLlamada();
		generateFacturas();
	}

	@AfterEach
	void destroyAll() {
		b1 = null;
		c1 = null;
		c2 = null;
		l1 = null;
		l2 = null;
		f1 = null;
		f2 = null;
	}

	void generateCliente() {
		InOut entradaSalida = new IOTerminal();
		c1 = Cliente.darDeAlta(entradaSalida, true, true);
		c2 = Cliente.darDeAlta(entradaSalida, false, true);
	}

	void generateLlamada() {
		l1 = Llamada.generateRandomLlamada();
		l2 = Llamada.generateRandomLlamada();
	}

	void generateFacturas() {
		f1 = Factura.darDeAltaRandom(c1);
		f2 = Factura.darDeAltaRandom(c2);
	}

	@Test
	void randomDuration() {
		Duration d;
		int segundos;
		for (int i = 0; i < 100; i++) {
			d = BaseDeDatos.randomDuration(10, 60);
			segundos = (int) d.getSeconds();
			assertTrue(segundos >= 10 && segundos <= 60);
		}
	}

	@Test
	void añadirAndGetCliente() {
		b1.addClient(c1);
		b1.addClient(c2);
		assertEquals(c1.getNif(), b1.getCliente(c1.getNif()).getNif());
		assertEquals(c2.getNif(), b1.getCliente(c2.getNif()).getNif());
	}

	@Test
	void añadirAndBorrarCliente() {
		assertFalse(b1.borrarCliente(c1.getNif()));
		b1.addClient(c1);
		assertTrue(b1.borrarCliente(c1.getNif()));
		assertFalse(b1.borrarCliente(c1.getNif()));

		assertFalse(b1.borrarCliente(c2.getNif()));
		b1.addClient(c2);
		assertTrue(b1.borrarCliente(c2.getNif()));
		assertFalse(b1.borrarCliente(c2.getNif()));

		assertFalse(b1.borrarCliente("nifNoExistente"));
	}

	@Test
	void añadirClienteAndCambiarTarifa() {
		c1.cambiarTarifa(new Tarifa(0.2));
		c2.cambiarTarifa(new Tarifa(0.1));
		assertEquals(0.2, c1.getTarifa().getPrecio());
		assertEquals(0.1, c2.getTarifa().getPrecio());
	}

	@Test
	void añadirLlamada() {
		b1.addClient(c1);
		b1.addClient(c2);
		assertEquals(0, c1.getLlamadas().size());
		assertEquals(0, c2.getLlamadas().size());
		assertTrue(b1.addLlamada(c1.getNif(), l1));
		assertTrue(b1.addLlamada(c1.getNif(), l2));
		assertTrue(b1.addLlamada(c2.getNif(), l1));
		assertEquals(2, c1.getLlamadas().size());
		assertEquals(1, c2.getLlamadas().size());
	}

	@Test
	void añadirFactura() {
		b1.addClient(c1);
		b1.addClient(c2);
		assertEquals(0, c1.getFacturas().size());
		assertEquals(0, c2.getFacturas().size());
		assertTrue(b1.addFactura(f1));
		assertTrue(b1.addFactura(f2));
		assertEquals(1, c1.getFacturas().size());
		assertEquals(1, c2.getFacturas().size());
	}
}