package Auxiliares;

import BaseDeDatos.BaseDeDatos;
import BaseDeDatos.Cliente;
import BaseDeDatos.Factura;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.Scanner;

public class IOTerminal implements InOut{

	private static Scanner s = new Scanner(System.in);

	private String fromTerminalAskString(String text) {
		print(text, " ");
		return s.nextLine();
	}

	private int fromTerminalAskInt(String text) {
		while (true) {
			print(text, " ");
			try {
				int input = s.nextInt();
				s.nextLine();
				return input;
			} catch (InputMismatchException e) {
				print("Error, no se ha introducido un número.");
				s.nextLine();
			}
		}
	}

	private double fromTerminalAskDouble(String text) {
		while (true) {
			print(text, " ");
			try {
				double input = s.nextDouble();
				s.nextLine();
				return input;
			} catch (InputMismatchException e) {
				print("Error, no se ha introducido un número.");
				s.nextLine();
			}
		}
	}

	public void waitIntro() {
		print("Pulsa Intro para continuar.", " ");
		s.nextLine();
	}

	@Override
	public int getOption(String text) {
		return fromTerminalAskInt(text);
	}

	@Override
	public LocalDate askDate(String extra) {
		int yyyy = fromTerminalAskInt("Año " + extra + ": ");
		int mm = fromTerminalAskInt("Mes " + extra + ": ");
		int dd = fromTerminalAskInt("Día " + extra + ": ");
		return LocalDate.of(yyyy, mm, dd);
	}

	@Override
	public LocalTime askTime(String extra) {
		int hh = fromTerminalAskInt("Hora " + extra + ": ");
		int mm = fromTerminalAskInt("Minuto " + extra + ": ");
		int ss = fromTerminalAskInt("Segundo " + extra + ": ");
		return LocalTime.of(hh, mm, ss);
	}

	@Override
	public String[] askNewClienteData(boolean esParticular) {
		String[] data;
		if (esParticular) data = new String[5];
		else data = new String[4];

		data[0] = fromTerminalAskString("Nombre del cliente:");
		data[1] = fromTerminalAskString("NIF del cliente:");
		data[2] = fromTerminalAskString("Provincia del cliente:");
		data[3] = fromTerminalAskString("Poblacion del cliente:");

		if (esParticular) data[4] = fromTerminalAskString("Apellido del cliente:");

		return data;
	}

	@Override
	public String askNIF() {
		return fromTerminalAskString("\nEscribe el nif del cliente: ");
	}

	@Override
	public Llamada askNewLlamada() {
		String telefono = fromTerminalAskString("Número de telefono: ");
		LocalDate date = askDate("de la llamada");
		LocalTime time = askTime("de la llamada");
		Duration duracion = Duration.ofSeconds(fromTerminalAskInt("Duracion de la llamada: "));
		return new Llamada(telefono, date, time, duracion);
	}

	@Override
	public Cliente askForCliente(BaseDeDatos baseDeDatos) {
		Cliente cliente = null;
		while (cliente == null) {
			String nif = fromTerminalAskString("Introduce el NIF del cliente: ");
			cliente = baseDeDatos.getCliente(nif);
			if (cliente == null) print("El NIF introducido no es válido");
		}
		return cliente;
	}

	@Override
	public Tarifa askNewTarifa() {
		return new Tarifa(fromTerminalAskDouble("Introduce el precio de la nueva tarifa: "));
	}

	@Override
	public Factura askForFactura(BaseDeDatos baseDeDatos) {
		Factura factura = null;
		while (factura == null) {
			String codigo = fromTerminalAskString("Introduce el codigo de la factura: ");
			factura = baseDeDatos.getFacturas().get(codigo);
			if (factura == null) print("El codigo introducido no es válido");
		}
		return factura;
	}

	@Override
	public Factura askNewFactura(Cliente cliente) {
		String codigo = cliente.getNextFacturaCodigo();
		Tarifa tarifa = cliente.getTarifa();
		LocalDate date = askDate("de la factura");
		PeriodoFacturacion pf = askNewPeriodoFacturacion();
		return new Factura(codigo, tarifa, date, pf, cliente);
	}

	@Override
	public PeriodoFacturacion askNewPeriodoFacturacion() {
		LocalDate inicioFacturacion = askDate("del principio del periodo de facturacion");
		LocalDate finalFacturacion = askDate("del final del periodo de facturacion");
		return new PeriodoFacturacion(inicioFacturacion, finalFacturacion);
	}

	@Override
	public <T> void print(T output) {
		System.out.println(output);
	}

	@Override
	public <T> void print(T output, String eol) {
		System.out.print(output + eol);
	}

	@Override
	public <T> void listar(Collection<T> lista) {
		String SEPARADOR = "\n----------------------------------------------------------------------------------------------------";
		print(SEPARADOR, "");
		for (T elemento : lista) {
			print("\n" + elemento);
		}
		print(SEPARADOR);
		waitIntro();
	}
}
