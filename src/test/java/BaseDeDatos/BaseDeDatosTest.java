package BaseDeDatos;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BaseDeDatosTest {

	BaseDeDatos b1;
	Cliente c1;
	Cliente c2;

	@BeforeEach
	void generateCliente() {
		b1 = new BaseDeDatos();
		c1 = Cliente.darDeAlta(true, true);
		c2 = Cliente.darDeAlta(false, true);
	}

	@Test
	void añadirYGetCliente() {
		b1.addClient(c1);
		b1.addClient(c2);
		assertEquals(c1.getNif(), b1.getCliente(c1.getNif()).getNif());
		assertEquals(c2.getNif(), b1.getCliente(c2.getNif()).getNif());
	}

	@Test
	void añadirYBorrarCliente() {
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
}