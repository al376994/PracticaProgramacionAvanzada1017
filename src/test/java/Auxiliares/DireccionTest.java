package Auxiliares;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DireccionTest {

	static Direccion d1;
	static Direccion d1_2;
	static Direccion d2;
	static Direccion d2_2;
	static Direccion d3;

	@BeforeAll
	static void generateDirecciones() {
		d1 = new Direccion("1", "1");
		d1_2 = new Direccion("1", "1");
		d2 = new Direccion("2", "1");
		d3 = new Direccion("2", "2");
		d2_2 = new Direccion("2", "1");
	}

	@AfterAll
	static void deleteDirecciones() {
		d1 = null;
		d1_2 = null;
		d2 = null;
		d2_2 = null;
		d3 = null;
	}

	@Test
	void correctlyAssignedCp() {
		assertEquals(1, d1.getCp());
		assertEquals(1, d1_2.getCp());
		assertEquals(2, d2.getCp());
		assertEquals(2, d2_2.getCp());
		assertEquals(3, d3.getCp());
	}
}