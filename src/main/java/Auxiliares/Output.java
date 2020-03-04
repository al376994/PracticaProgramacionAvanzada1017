package Auxiliares;

import java.util.Collection;

import static BaseDeDatos.BaseDeDatos.EN_TERMINAL;

public class Output {

	private final String SEPARADOR = "\n----------------------------------------------------------------------------------------------------";

	public <T> void print(T output) {
		System.out.println(output);
	}

	public <T> void print(T output, String eol) {
		String print = output + eol;
		System.out.print(print);
	}

	public <T> void listar(Collection<T> lista) {
		if (EN_TERMINAL) {
			for (T elemento : lista) {
				IO.out.print(elemento);
			}
			IO.out.print(SEPARADOR);
			IO.waitIntro();
		}
	}

}
