package Auxiliares;

import BaseDeDatos.BaseDeDatos;
import BaseDeDatos.Cliente;
import BaseDeDatos.Factura;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

// Todos los inputs se hacer aquí, siempre que se quiera que el usuario introduzca algo de informacion se utilizara
// esta clase.

public class Input {	// TODO: Profesor(deberíais usar la herencia para evitar tener que ir repetidamente) ¿como?
						// TODO: Profesor(comprobando si estáis EN_TERMINAL) ¿que quiere decir esto?

	private static Scanner s = new Scanner(System.in);

	public String fromTerminalAskString(String text) {
		IO.out.print(text, " ");
		return s.nextLine();
	}

	public int fromTerminalAskInt(String text) {
		while (true) {
			IO.out.print(text, " ");
			try {
				int input = s.nextInt();
				s.nextLine();
				return input;
			} catch (InputMismatchException e) {
				IO.out.print("Error, no se ha introducido un número.");
				s.nextLine();
			}
		}
	}

	public double fromTerminalAskDouble(String text) {
		while (true) {
			IO.out.print(text, " ");
			try {
				double input = s.nextDouble();
				s.nextLine();
				return input;
			} catch (InputMismatchException e) {
				IO.out.print("Error, no se ha introducido un número.");
				s.nextLine();
			}
		}
	}

	public int getOption(String text) {
		int option;
		if (BaseDeDatos.EN_TERMINAL) option = fromTerminalAskInt(text);
		else IO.out.print("No implementado");
		return option;
	}

	public LocalDate askDate(String extra) {
		int yyyy;
		int mm;
		int dd;
		if (BaseDeDatos.EN_TERMINAL) {
			yyyy = IO.in.fromTerminalAskInt("Año " + extra + ": ");
			mm = IO.in.fromTerminalAskInt("Mes " + extra + ": ");
			dd = IO.in.fromTerminalAskInt("Día " + extra + ": ");
		}
		else IO.out.print("No implementado");
		return LocalDate.of(yyyy, mm, dd);
	}

	public LocalTime askTime(String extra) {
		int hh;
		int mm;
		int ss;
		if (BaseDeDatos.EN_TERMINAL) {
			hh = IO.in.fromTerminalAskInt("Hora " + extra + ": ");
			mm = IO.in.fromTerminalAskInt("Minuto " + extra + ": ");
			ss = IO.in.fromTerminalAskInt("Segundo " + extra + ": ");
		}
		else IO.out.print("No implementado");
		return LocalTime.of(hh, mm, ss);
	}

	public void waitIntro() {
		IO.out.print("Pulsa Intro para continuar.", " ");
		s.nextLine();
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
		else IO.out.print("No implementado");

		if (esParticular) data[4] = IO.in.fromTerminalAskString("Apellido del cliente:");

		return data;
	}

	public String askNIF() {
		String nif;
		if (BaseDeDatos.EN_TERMINAL) nif = fromTerminalAskString("\nEscribe el nif del cliente que quieres borrar: ");
		else IO.out.print("No implementado");
		return nif;
	}

	public Llamada askNewLlamada() {
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
		else IO.out.print("No implementado");
		return new Llamada(telefono, date, time, duracion);
	}

	public Cliente askForCliente(BaseDeDatos baseDeDatos) {
		Cliente cliente = null;
		while (cliente == null) {
			String nif;
			if (BaseDeDatos.EN_TERMINAL) nif = IO.in.fromTerminalAskString("Introduce el NIF del cliente: ");
			else IO.out.print("No implementado");
			cliente = baseDeDatos.getCliente(nif);
			if (cliente == null) IO.out.print("El NIF introducido no es válido");
		}
		return cliente;
	}

	public Tarifa askNewTarifa() {
		Tarifa tarifa;
		if (BaseDeDatos.EN_TERMINAL) {
			tarifa = new Tarifa(IO.in.fromTerminalAskDouble("Introduce el precio de la nueva tarifa"));
		}
		else IO.out.print("No implementado");
		return tarifa;
	}

	public Factura askForFactura(BaseDeDatos baseDeDatos) {
		Factura factura = null;
		while (factura == null) {
			String codigo;
			if (BaseDeDatos.EN_TERMINAL) codigo = IO.in.fromTerminalAskString("Introduce el codigo de la factura: ");
			else IO.out.print("No implementado");
			factura = baseDeDatos.getFacturas().get(codigo);
			if (factura == null) IO.out.print("El codigo introducido no es válido");
		}
		return factura;
	}

	public Factura askNewFactura(BaseDeDatos baseDeDatos, Cliente cliente) {
		String codigo;
		Tarifa tarifa;
		LocalDate date;
		PeriodoFacturacion pf;
		if (BaseDeDatos.EN_TERMINAL) {
			codigo = cliente.getNextFacturaCodigo();
			tarifa = cliente.getTarifa();
			date = IO.in.askDate("de la factura");
			pf = PeriodoFacturacion.askDates();
		}
		return new Factura(baseDeDatos, codigo, tarifa, date, pf, cliente);
	}
}
