package Auxiliares;

import BaseDeDatos.BaseDeDatos;
import BaseDeDatos.Cliente;
import BaseDeDatos.Factura;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

public interface InOut {

	//Input
	int getOption(String text);
	LocalDate askDate(String extra);
	LocalTime askTime(String extra);
	String[] askNewClienteData(boolean esParticular);
	String askNIF();
	Llamada askNewLlamada();
	Cliente askForCliente(BaseDeDatos baseDeDatos);
	Tarifa askNewTarifa();
	Factura askForFactura(BaseDeDatos baseDeDatos);
	Factura askNewFactura(Cliente cliente);
	PeriodoFacturacion askNewPeriodoFacturacion();
	void waitIntro();

	//Output
	<T> void print(T output);
	<T> void print(T output, String eol);
	<T> void listar(Collection<T> lista);

}
