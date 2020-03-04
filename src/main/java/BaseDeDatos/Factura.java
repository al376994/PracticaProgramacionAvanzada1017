package BaseDeDatos;

import Auxiliares.*;

import java.time.LocalDate;

public class Factura implements TieneFecha {

	private BaseDeDatos baseDeDatos;
	private String codigo;
	private Tarifa tarifa;
	private LocalDate fechaEmision;
	private PeriodoFacturacion periodoFacturacion;
	private Cliente cliente;
	private double importe;

	public Factura(BaseDeDatos baseDeDatos, String codigo, Tarifa tarifa, LocalDate fechaEmision, PeriodoFacturacion periodoFacturacion, Cliente cliente) {
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

	public Cliente getCliente() {
		return cliente;
	}

	public double getImporte() {
		return importe;
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
		return IO.in.askNewFactura(baseDeDatos, cliente);
	}

	@Override
	public String toString(){
		return "Factura: " + codigo + " emitida el " + fechaEmision.format(BaseDeDatos.DMY) +
				"\nPeriodo de facturacion" + periodoFacturacion.getStart().format(BaseDeDatos.HMS) + " - " + periodoFacturacion.getEnd() +
				"\nImporte: " + importe;
	}

	@Override
	public LocalDate getFecha() {
		return fechaEmision;
	}
}
