package BaseDeDatos;

import Auxiliares.*;

import java.io.Serializable;
import java.time.LocalDate;

// La clase Factura representa una factura de un cliente, se puede crear o bien mediante el contructor o una de las
// funciones darDeAlta, la Random crea una factura aleatoria y la otra pregunta por datos al usuario, ambas devolveran
// la factura. La funcion calculaImporte tiene 2 formas, la publica que solo recive periodoDacturacion y cliente y la
// privada que recoje tarifa de la propia factura, esta separado así para que se pueda calcular el importe en el
// constructor. También hay una funcion updateImporte que como indica su nombre vuelve a calcular el importe y
// sobreescribe la variable importe con el nuevo valor.

public class Factura implements TieneFecha, Serializable {

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

	public PeriodoFacturacion getPeriodoFacturacion() {
		return periodoFacturacion;
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

	public double calculaImporte() {
		return calculaImporte(this.tarifa, periodoFacturacion, cliente);
	}

	public void updateImporte() {
		importe = calculaImporte();
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
				"\nPeriodo de facturacion" + periodoFacturacion.getStart().format(BaseDeDatos.DMY) + " - " + periodoFacturacion.getEnd().format(BaseDeDatos.DMY) +
				"\nImporte: " + importe;
	}

	@Override
	public LocalDate getFecha() {
		return fechaEmision;
	}
}
