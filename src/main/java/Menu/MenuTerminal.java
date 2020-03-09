package Menu;

import Auxiliares.IO;
import Auxiliares.Llamada;
import BaseDeDatos.BaseDeDatos;
import BaseDeDatos.Cliente;
import BaseDeDatos.Factura;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

// El menu cuando se ejecuta para la terminal.

public class MenuTerminal {	// TODO: Profesor(El menú no debería tener la base de datos) preguntar porque y que hacer al respecto
							// (esto estaba antes en MenuOpciones, pero como ahora el menu esta aqui pues tiene sentido que este aquí)

	private BaseDeDatos baseDeDatos;	// TODO: Profesor(El parámetro no se usa, eliminadlo) ahora el parámetro si se usa porque se han redistribuido las funciones

	public void run(BaseDeDatos baseDeDatos) {
		this.baseDeDatos = baseDeDatos;
		IO.out.print("Menu de Terminal de la aplicación de Facturación");
		chooseOptionSet(MenuOpciones.OPCIONES_PRINCIPALES);
	}

	private String getOpciones(String[] opciones) {
		return String.join("\n", opciones);
	}	//Añadido de MenuOpciones

	private boolean chooseOptionSet(int set) {
		int option;
		String inputText = "\nEscribe el número de la opción que quieres elegir:";
		switch (set) {
			case 1:																	//Menu principal
				IO.out.print("\n" + getOpciones(MenuOpciones.listaOpcionesPrincipales));
				option = IO.in.getOption(inputText);
				chooseOptionPrincipales(option);
				break;
			case 2:																	//Seleccinar tipo de cliente
				IO.out.print("\n" + getOpciones(MenuOpciones.listaOpcionesNuevoCliente));
				option = IO.in.getOption(inputText);
				boolean satisfactory = chooseOptionNuevoCliente(option);
				printIsSatisfactory(satisfactory);
				return satisfactory;
			case 3:																	//Crear llamada
				IO.out.print("\n" + getOpciones(MenuOpciones.listaOpcionesNuevaLlamada));
				option = IO.in.getOption(inputText);
				chooseOptionNuevaLlamada(option);
				printIsSatisfactory(true);
				return true;
			case 4:																	//Crear factura
				IO.out.print("\n" + getOpciones(MenuOpciones.listaOpcionesNuevaFactura));
				option = IO.in.getOption(inputText);
				chooseOptionNuevaFactura(option);
				printIsSatisfactory(true);
				return true;
			case -1:																//Opciones de salida
				IO.out.print("\n" + getOpciones(MenuOpciones.listaOpcionesSalida));
				option = IO.in.getOption(inputText);
				chooseOptionSalida(option);
			default:
				IO.out.print("Error choosing a Menu Set(" + set + "), returning to Main Menu");
				option = IO.in.getOption(inputText);
				chooseOptionPrincipales(option);
				break;
		}
		return chooseOptionSet(MenuOpciones.OPCIONES_PRINCIPALES);
	}

	// Todos los choose a loq eu llama chooseOptionSet a partir de aqui
	// En los switch solo van sentencias simples, si es necesario algo mas complejo crea una funcion en la zona de abajo

	private void chooseOptionPrincipales(int option) {
		switch (option) {
			case 1:											// CREAR UN CLIENTE NUEVO
				chooseOptionSet(MenuOpciones.OPCIONES_NUEVO_CLIENTE);
				break;
			case 2:											// BORRAR UN CLIENTE EXISTENTE
				printIsSatisfactory(baseDeDatos.askBorrarCliente());
				break;
			case 3:											// CAMBIAR LA TARIFA DE UN CLIENTE
				baseDeDatos.cambiarTarifa();
				break;
			case 4:											// MOSTRAR LOS DATOS DE UN CLIENTE
				baseDeDatos.printCliente();
				break;
			case 5:											// MOSTRAR LOS DATOS DE TODOS LOS CLIENTES
				baseDeDatos.listarClientes();
				IO.waitIntro();
				break;
			case 6:											// CREAR UNA LLAMADA PARA UN CLIENTE
				chooseOptionSet(MenuOpciones.OPCIONES_NUEVA_LLAMADA);
				break;
			case 7:											// MOSTRAR LLAMADAS DE UN CLIENTE
				baseDeDatos.listarLlamadas();
				break;
			case 8:											//CREAR UNA FACTURA PARA UN CLIENTE
				chooseOptionSet(MenuOpciones.OPCIONES_NUEVA_FACTURA);
				break;
			case 9:											//RECUPERAR FACTURA A PARTIR DE CODIGO
				baseDeDatos.printFactura();
				break;
			case 10:										//RECUPERAR FACTURAS DE UN CLIENTE
				baseDeDatos.listarFacturas();
				break;
			case 11:										//LISTAR CLIENTES ENTRE FECHAS
				IO.out.listar(listarClientesEntreFechas());
				break;
			case 12:										//LISTAR LLAMADAS ENTRE FECHAS
				IO.out.listar(listarLlamadassEntreFechas());
				break;
			case 13:										//LISTAR FACTURAS ENTRE FECHAS
				IO.out.listar(listarFacturasEntreFechas());
				break;
			case 14:										//LISTAR FACTURAS ENTRE FECHAS
				baseDeDatos.saveData();
				break;
			case 15:										//LISTAR FACTURAS ENTRE FECHAS
				baseDeDatos.loadData();
				break;
			case 16:										// CERRAR EL PROGRAMA
				chooseOptionSet(MenuOpciones.OPCIONES_SALIDA);
				break;
			default:
				wrongOptionWriten(MenuOpciones.OPCIONES_PRINCIPALES);
				break;
		}
	}

	private boolean chooseOptionNuevoCliente(int option){
		switch (option) {
			case 1:
				return baseDeDatos.nuevoParticular(false);
			case 2:
				return baseDeDatos.nuevoParticular(true);
			case 3:
				return baseDeDatos.nuevaEmpresa(false);
			case 4:
				return baseDeDatos.nuevaEmpresa(true);
			case 5:
				return true;
			default:
				wrongOptionWriten(MenuOpciones.OPCIONES_NUEVO_CLIENTE);
				break;
		}
		return false;
	}

	private boolean chooseOptionNuevaLlamada(int option){
		switch (option) {
			case 1:
				return baseDeDatos.nuevaLlamada(false);
			case 2:
				return baseDeDatos.nuevaLlamada(true);
			case 3:
				return true;
			default:
				wrongOptionWriten(MenuOpciones.OPCIONES_NUEVA_LLAMADA);
				break;
		}
		return false;
	}

	private boolean chooseOptionNuevaFactura(int option) {
		switch (option) {
			case 1:
				return baseDeDatos.nuevaFactura(false);
			case 2:
				return baseDeDatos.nuevaFactura(true);
			case 3:
				return true;
			default:
				wrongOptionWriten(MenuOpciones.OPCIONES_NUEVA_FACTURA);
				break;
		}
		return false;
	}

	private void chooseOptionSalida(int option) {
		switch (option) {
			case 1:
				baseDeDatos.exitWithoutSave();
				break;
			case 2:
				baseDeDatos.saveData();
				System.exit(0);
				break;
			case 3:
				break;
			default:
				wrongOptionWriten(MenuOpciones.OPCIONES_SALIDA);
				break;
		}
	}

	// Todas las funciones a las que llamen los choose van a partir de aquí a partir de aqui

	private Collection<Cliente> listarClientesEntreFechas() {
		ArrayList<Cliente> clientes = new ArrayList<>(baseDeDatos.getClientes().values());
		LocalDate desde = IO.in.askDate("");
		LocalDate hasta = IO.in.askDate("");
		return baseDeDatos.elementosEntreFechas(clientes, desde, hasta);
	}

	private Collection<Llamada> listarLlamadassEntreFechas() {
		Cliente cliente = IO.in.askForCliente(baseDeDatos);
		ArrayList<Llamada> llamadas = new ArrayList<>(cliente.getLlamadas());
		LocalDate desde = IO.in.askDate("");
		LocalDate hasta = IO.in.askDate("");
		return baseDeDatos.elementosEntreFechas(llamadas, desde, hasta);
	}

	private Collection<Factura> listarFacturasEntreFechas() {
		Cliente cliente = IO.in.askForCliente(baseDeDatos);
		ArrayList<Factura> facturas = new ArrayList<>(cliente.getFacturas().values());
		LocalDate desde = IO.in.askDate("");
		LocalDate hasta = IO.in.askDate("");
		return baseDeDatos.elementosEntreFechas(facturas, desde, hasta);
	}

	private void printIsSatisfactory(boolean s) {
		if (s) IO.out.print("Operacion completada con exito");
		else IO.out.print("La operacion no se ha podido realizar");
		IO.waitIntro();
	}

	private boolean wrongOptionWriten(int set) {
		IO.out.print("Write one of the options.");
		IO.waitIntro();
		return chooseOptionSet(set);
	}
}
