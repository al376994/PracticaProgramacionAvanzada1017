package BaseDeDatos;

import Auxiliares.IO;
import Auxiliares.Llamada;
import Auxiliares.PeriodoFacturacion;
import Auxiliares.Tarifa;
import org.javatuples.Quartet;

import java.time.LocalDate;

public class Factura {

	private static int codigoFacturaActual = 1;

	private BaseDeDatos baseDeDatos;
	private String codigo;
	private Tarifa tarifa;
	private LocalDate fechaEmision;
	private PeriodoFacturacion periodoFacturacion;
	private Cliente cliente;
	private double importe;

	private Factura(BaseDeDatos baseDeDatos, String codigo, Tarifa tarifa, LocalDate fechaEmision, PeriodoFacturacion periodoFacturacion, Cliente cliente) {
		this.baseDeDatos = baseDeDatos;
		this.codigo = codigo;
		this.tarifa = tarifa;
		this.fechaEmision = fechaEmision;
		this.periodoFacturacion = periodoFacturacion;
		this.cliente = cliente;
		importe = calculaImporte(tarifa, periodoFacturacion, cliente);
	}

	public String getCodigo() {
		return codigo;
	}

	private double calculaImporte(Tarifa tarifa, PeriodoFacturacion periodoFacturacion, Cliente cliente) {
		double importe = 0;
		for (Llamada llamada : cliente.getLlamadas()) {
			if (periodoFacturacion.inPeriodoFacturacion(llamada.getFecha())) {
				importe += llamada.getDuracion().getSeconds() * tarifa.getPrecio();
			}
		}
		return importe;
	}

	static public Factura darDeAltaRandom(BaseDeDatos baseDeDatos, Cliente cliente) {
		String codigo = cliente.getNextFacturaCodigo();
		Tarifa tarifa = new Tarifa();
		LocalDate date = LocalDate.now();
		PeriodoFacturacion pf = new PeriodoFacturacion(date, date.plusMonths(1));
		return new Factura(baseDeDatos, codigo, tarifa, date, pf, cliente);
	}

	static public Factura darDeAlta(BaseDeDatos baseDeDatos, Cliente cliente) {
		Quartet data = IO.in.askForFactura(cliente);
		String codigo = (String) data.getValue(0);
		Tarifa tarifa = (Tarifa) data.getValue(1);
		LocalDate date = (LocalDate) data.getValue(2);
		PeriodoFacturacion pf = (PeriodoFacturacion) data.getValue(3);
		return new Factura(baseDeDatos, codigo, tarifa, date, pf, cliente);
	}

	@Override
	public String toString(){
		return "Factura: " + codigo + " emitida el " + fechaEmision.format(BaseDeDatos.DMY) +
				"\nPeriodo de facturacion" + periodoFacturacion.getStart().format(BaseDeDatos.HMS) + " - " + periodoFacturacion.getEnd() +
				"\nImporte: " + importe;
	}

}
