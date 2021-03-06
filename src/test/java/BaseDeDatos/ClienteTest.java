package BaseDeDatos;

import Auxiliares.IOTerminal;
import Auxiliares.InOut;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

	static BaseDeDatos b1;
	static Cliente c1;
	static Cliente c2;
	static Cliente c3;
	static Cliente c4;

	@BeforeAll
	static void generateClientes() {
		b1 = new BaseDeDatos();
		InOut entradaSalida = new IOTerminal();
		c1 = Cliente.darDeAlta(entradaSalida, true, true);
		c2 = Cliente.darDeAlta(entradaSalida, true, true);
		c3 = Cliente.darDeAlta(entradaSalida, false, true);
		c4 = Cliente.darDeAlta(entradaSalida, false, true);
	}

	@Test
	void codigoFacturaActualSeActualiza() {
		assertEquals(c1.getNif() + "1", c1.getNextFacturaCodigo());
		b1.addFactura(Factura.darDeAltaRandom(c1));
		assertEquals(c1.getNif() + "2", c1.getNextFacturaCodigo());
		b1.addFactura(Factura.darDeAltaRandom(c1));
		assertEquals(c2.getNif() + "1", c2.getNextFacturaCodigo());
		assertEquals(c1.getNif() + "3", c1.getNextFacturaCodigo());
		b1.addFactura(Factura.darDeAltaRandom(c2));
		assertEquals(c2.getNif() + "2", c2.getNextFacturaCodigo());

		assertEquals(c3.getNif() + "1", c3.getNextFacturaCodigo());
		b1.addFactura(Factura.darDeAltaRandom(c3));
		assertEquals(c3.getNif() + "2", c3.getNextFacturaCodigo());
		b1.addFactura(Factura.darDeAltaRandom(c3));
		assertEquals(c4.getNif() + "1", c4.getNextFacturaCodigo());
		assertEquals(c3.getNif() + "3", c3.getNextFacturaCodigo());
		b1.addFactura(Factura.darDeAltaRandom(c4));
		assertEquals(c4.getNif() + "2", c4.getNextFacturaCodigo());
	}
}