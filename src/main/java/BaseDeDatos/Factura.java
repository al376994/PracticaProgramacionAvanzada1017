package BaseDeDatos;

import Auxiliares.IO;
import Auxiliares.PeriodoFacturacion;
import Auxiliares.Tarifa;

import java.time.LocalDate;

public class Factura {

	private static int codigoFacturaActual = 1;

	BaseDeDatos baseDeDatos;
	String codigo;
	Tarifa tarifa;
	LocalDate fechaEmision;
	PeriodoFacturacion periodoFacturacion;
	Cliente cliente;

	public Factura() {}

	private Factura(BaseDeDatos baseDeDatos, String codigo, Tarifa tarifa, LocalDate fechaEmision, PeriodoFacturacion periodoFacturacion, Cliente cliente) {
		this.baseDeDatos = baseDeDatos;
		this.codigo = codigo;
		this.tarifa = tarifa;
		this.fechaEmision = fechaEmision;
		this.periodoFacturacion = periodoFacturacion;
		this.cliente = cliente;
	}

	static public Factura darDeAltaRandom(BaseDeDatos baseDeDatos, Cliente cliente) {
		String codigo = cliente.getNextFacturaCodigo();
		Tarifa tarifa = new Tarifa();
		LocalDate date = LocalDate.now();
		PeriodoFacturacion pf = new PeriodoFacturacion(date, date.plusMonths(1));
		return new Factura(baseDeDatos, codigo, tarifa, date, pf, cliente);
	}

	static public Factura darDeAlta(BaseDeDatos baseDeDatos, Cliente cliente) {
		String codigo = cliente.getNextFacturaCodigo();
		Tarifa tarifa = new Tarifa(IO.in.fromTerminalAskDouble("Precio de la tarifa: "));
		LocalDate date = BaseDeDatos.askDate("de la factura");
		PeriodoFacturacion pf = PeriodoFacturacion.askDates();
		return new Factura(baseDeDatos, codigo, tarifa, date, pf, cliente);
	}
}
