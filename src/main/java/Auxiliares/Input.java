package Auxiliares;

import BaseDeDatos.BaseDeDatos;
import BaseDeDatos.Cliente;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Input {

	private static Scanner s = new Scanner(System.in);

	public int askInt(String text) {
		if (BaseDeDatos.EN_TERMINAL) fromTerminalAskInt(text);
	}

	public String fromTerminalAskString(String text) {
		IO.out.toTerminal(text, " ");
		return s.nextLine();
	}

	public int fromTerminalAskInt(String text) {
		while (true) {
			IO.out.toTerminal(text, " ");
			try {
				int input = s.nextInt();
				s.nextLine();
				return input;
			} catch (InputMismatchException e) {
				IO.out.toTerminal("Error, no se ha introducido un número.");
				s.nextLine();
			}
		}
	}

	public double fromTerminalAskDouble(String text) {
		while (true) {
			IO.out.toTerminal(text, " ");
			try {
				double input = s.nextDouble();
				s.nextLine();
				return input;
			} catch (InputMismatchException e) {
				IO.out.toTerminal("Error, no se ha introducido un número.");
				s.nextLine();
			}
		}
	}

	public String[] askNewClienteData(boolean esParticular) {
		String[] data;
		if (esParticular) data = new String[5];
		else data = new String[4];
		if (BaseDeDatos.EN_TERMINAL) {
			data[0] = IO.in.fromTerminalAskString("Nombre del cliente:");
			data[1] = IO.in.fromTerminalAskString("NIF del cliente:");
			data[2] = IO.in.fromTerminalAskString("Provincia del cliente:");
			data[3] = IO.in.fromTerminalAskString("Poblacion del cliente:");
		}
		else IO.out.toTerminal("No implementado");

		if (esParticular) data[4] = IO.in.fromTerminalAskString("Apellido del cliente:");

		return data;

	}

	public Llamada askNewLlamadaData() {
		String telefono;
		LocalDate date;
		LocalTime time;
		Duration duracion;
		if (BaseDeDatos.EN_TERMINAL) {
			telefono = IO.in.fromTerminalAskString("Número de telefono: ");
			date = askDate("de la llamada");
			time = askTime("de la llamada");
			duracion = Duration.ofSeconds(IO.in.fromTerminalAskInt("Duracion de la llamada: "));
		}
		else IO.out.toTerminal("No implementado");
		return new Llamada(telefono, date, time, duracion);
	}

	public static LocalDate askDate(String extra) {
		int yyyy;
		int mm;
		int dd;
		if (BaseDeDatos.EN_TERMINAL) {
			yyyy = IO.in.fromTerminalAskInt("Año " + extra + ": ");
			mm = IO.in.fromTerminalAskInt("Mes " + extra + ": ");
			dd = IO.in.fromTerminalAskInt("Día " + extra + ": ");
		}
		else IO.out.toTerminal("No implementado");
		return LocalDate.of(yyyy, mm, dd);
	}

	public static LocalTime askTime(String extra) {
		int hh;
		int mm;
		int ss;
		if (BaseDeDatos.EN_TERMINAL) {
			hh = IO.in.fromTerminalAskInt("Hora " + extra + ": ");
			mm = IO.in.fromTerminalAskInt("Minuto " + extra + ": ");
			ss = IO.in.fromTerminalAskInt("Segundo " + extra + ": ");
		}
		else IO.out.toTerminal("No implementado");
		return LocalTime.of(hh, mm, ss);
	}

	public Cliente askForCliente() {
		Cliente cliente = null;
		while (cliente == null) {
			String nif;
			if (BaseDeDatos.EN_TERMINAL) nif = IO.in.fromTerminalAskString("Introduce el NIF del cliente: ");
			else IO.out.toTerminal("No implementado");
			cliente = BaseDeDatos.getCliente(nif);
			if (cliente == null) IO.out.toTerminal("El NIF introducido no es válido");
		}
		return cliente;
	}

	public Tarifa askForTarifa() {
		Tarifa tarifa;
		if (BaseDeDatos.EN_TERMINAL) {
			tarifa = new Tarifa(IO.in.fromTerminalAskDouble("Introduce el precio de la nueva tarifa"));
		}
		else IO.out.toTerminal("No implementado");
		return tarifa;
	}

	public void waitIntro() {
		IO.out.toTerminal("Pulsa Intro para continuar.", " ");
		s.nextLine();
	}

}
