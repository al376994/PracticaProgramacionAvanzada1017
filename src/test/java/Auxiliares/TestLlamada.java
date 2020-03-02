package Auxiliares;

import Auxiliares.Llamada;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class TestLlamada {

	static private DateTimeFormatter dmy;
	static private DateTimeFormatter hms;
	static private Llamada l1;
	static private LocalDate d1;
	static private LocalTime t1;
	static private Llamada l2;
	static private LocalDate d2;
	static private LocalTime t2;
	static private Llamada l3;
	static private LocalDate d3;
	static private LocalTime t3;


	@BeforeAll
	static void createLlamadas() {
		dmy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		hms = DateTimeFormatter.ofPattern("hh:mm:ss");
		d1 = LocalDate.now();
		d2 = LocalDate.now();
		d3 = LocalDate.now();
		t1 = LocalTime.now();
		t2 = LocalTime.now();
		t3 = LocalTime.now();
		l1 = new Llamada("123456789", d1, t1, Duration.ofSeconds(11));
		l2 = new Llamada("234567891", d2, t2, Duration.ofSeconds(22));
		l3 = new Llamada("345678912", d3, t3, Duration.ofSeconds(33));
	}

	@AfterAll
	static void clean() {
		dmy = null;
		hms = null;
		d1 = null;
		d2 = null;
		d3 = null;
		t1 = null;
		t2 = null;
		t3 = null;
		l1 = null;
		l2 = null;
		l3 = null;
	}

	@Test
	void getFecha(){
		assertEquals(l1.getFecha().toString(), d1.toString());
		assertEquals(l2.getFecha(), d2);
		assertEquals(l3.getFecha(), d3);
	}

	@Test
	void toStringTest(){
		assertEquals(l1.toString(),
				"Llamada al número 123456789 el " + d1.format(dmy) + " a las " +
						t1.format(hms) + " con una duración de 11 segundos.");
		assertEquals(l2.toString(),
				"Llamada al número 234567891 el " + d2.format(dmy) + " a las " +
						t2.format(hms) + " con una duración de 22 segundos.");
		assertEquals(l3.toString(),
				"Llamada al número 345678912 el " + d3.format(dmy) + " a las " +
						t3.format(hms) + " con una duración de 33 segundos.");
		System.out.println(l1 + "\n" + l2 + "\n" + l3);
	}
}