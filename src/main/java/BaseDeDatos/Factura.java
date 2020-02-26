package BaseDeDatos;

import Auxiliares.PeriodoFacturacion;
import Auxiliares.Tarifa;

import java.time.LocalDate;

public class Factura {

	private static int codigoFacturaActual = 1;

	BaseDeDatos baseDeDatos;
	int codigo;
	Tarifa tarifa;
	LocalDate fechaEmision;
	PeriodoFacturacion periodoFacturacion;

	private Factura(BaseDeDatos baseDeDatos, int codigo, Tarifa tarifa, LocalDate fechaEmision, PeriodoFacturacion periodoFacturacion) {
		this.baseDeDatos = baseDeDatos;
		this.codigo = codigo;
		this.tarifa = tarifa;
		this.fechaEmision = fechaEmision;
		this.periodoFacturacion = periodoFacturacion;
	}

	public Factura facturaRandom(BaseDeDatos baseDeDatos) {
		int cod = getNextCodigo();

		return new Factura(baseDeDatos, )
	}

	private int getNextCodigo() {
		int cod = codigoFacturaActual;
		codigoFacturaActual++;
		return cod;
	}
}
