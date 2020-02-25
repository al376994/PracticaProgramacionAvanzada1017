package BaseDeDatos;

import Auxiliares.Llamada;
import es.uji.www.GeneradorDatosINE;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class BaseDeDatos {

	public void nuevoCliente() {
		
	}

	public Llamada generateRandomLlamada() {
		GeneradorDatosINE g = new GeneradorDatosINE();
		String tel =  "9" + g.getNIF().substring(0, 7);
		Duration dur = randomDuration(10, 60);
		return new Llamada(tel, LocalDate.now(), LocalTime.now(), dur);
	}

	public Duration randomDuration(int min, int max) {
		if (min > max) {
			int tmp = max;
			max = min;
			min = tmp;
		}
		if (min == max) return Duration.ofSeconds(min);
		return Duration.ofSeconds( (long)(Math.random() * ((max - min) + 1)) + min );
	}

}
