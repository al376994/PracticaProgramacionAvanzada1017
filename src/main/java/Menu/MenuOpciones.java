package Menu;

public class MenuOpciones {

	static final String[] listaOpcionesPrincipales = {
			"1. \tDar de alta a un nuevo cliente",
			"2. \tBorrar un cliente",
			"3. \tCambiar la tarifa de un cliente",
			"4. \tRecuperar los datos de un cliente a partir de su NIF",
			"5. \tRecuperar el listado de todos los clientes",
			"6. \tDar de alta una llamada",
			"7. \tListar todas las llamadas de un cliente",
			"8. \tEmitir una factura para un cliente, calculando el importe de la misma en función de las llamadas",
			"9. \tRecuperar los datos de una factura a partir de su código",
			"10.\tRecuperar todas las facturas de un cliente",
			"11.\tListar clientes dados de alta entre 2 fechas",
			"12.\tListar llamadas de un cliente entre 2 fechas",
			"13.\tListar facturas de un cliente entre 2 fechas",
			"14.\tSalir"
	};

	static final String[] listaOpcionesNuevoCliente = {
			"1. \tDar de alta un Particular",
			"2. \tDar de alta un Particular aleatorio",
			"3. \tDar de alta una Empresa",
			"4. \tDar de alta una Empresa aleatoria",
			"5. \tCancelar"
	};

	static final String[] listaOpcionesSalida = {
			"1. \tSalir sin guardar",
			"2. \tGuardar y salir",
			"3. \tCancelar"
	};

	static final String[] listaOpcionesNuevaLlamada = {
			"1. \tDar de alta una Llamada",
			"2. \tDar de alta una Llamada aleatoria",
			"3. \tCancelar"
	};

	static final String[] listaOpcionesNuevaFactura= {
			"1. \tDar de alta una Factura",
			"2. \tDar de alta una Factura aleatoria",
			"3. \tCancelar"
	};

	static final int OPCIONES_PRINCIPALES = 1;
	static final int OPCIONES_NUEVO_CLIENTE = 2;
	static final int OPCIONES_NUEVA_LLAMADA = 3;
	static final int OPCIONES_NUEVA_FACTURA = 4;
	static final int OPCIONES_SALIDA = -1;
/*
	public String getOpciones(String[] opciones) {
		return String.join("\n", opciones);
	}

	public boolean chooseOptionSet(int set) {
		int option;
		switch (set) {
			case 1:																	//Menu principal
				IO.out.print("\n" + getOpciones(listaOpcionesPrincipales));
				option = IO.in.getOption(inputText);
				chooseOptionPrincipales(option);
				break;
			case 2:																	//Seleccinar tipo de cliente
				IO.out.print("\n" + getOpciones(listaOpcionesNuevoCliente));
				option = IO.in.getOption(inputText);
				boolean satisfactory = chooseOptionNuevoCliente(option);
				printIsSatisfactory(satisfactory);
				return satisfactory;
			case 3:																	//Crear llamada
				IO.out.print("\n" + getOpciones(listaOpcionesNuevaLlamada));
				option = IO.in.getOption(inputText);
				chooseOptionNuevaLlamada(option);
				printIsSatisfactory(true);
				return true;
			case 4:																	//Crear factura
				IO.out.print("\n" + getOpciones(listaOpcionesNuevaFactura));
				option = IO.in.getOption(inputText);
				chooseOptionNuevaFactura(option);
				printIsSatisfactory(true);
			case -1:																//Opciones de salida
				IO.out.print("\n" + getOpciones(listaOpcionesSalida));
				option = IO.in.getOption(inputText);
				chooseOptionSalida(option);
			default:
				IO.out.print("Error choosing a Menu Set(" + set + "), returning to Main Menu");
				option = IO.in.getOption(inputText);
				chooseOptionPrincipales(option);
				break;
		}
		return chooseOptionSet(OPCIONES_PRINCIPALES);
	}

	private void chooseOptionPrincipales(int option) {
		switch (option) {
			case 1:											// CREAR UN CLIENTE NUEVO
				chooseOptionSet(OPCIONES_NUEVO_CLIENTE);
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
				chooseOptionSet(OPCIONES_NUEVA_LLAMADA);
				break;
			case 7:											// MOSTRAR LLAMADAS DE UN CLIENTE
				baseDeDatos.listarLlamadas();
				break;
			case 8:											//CREAR UNA FACTURA PARA UN CLIENTE
				chooseOptionSet(OPCIONES_NUEVA_FACTURA);
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
			case 14:										// CERRAR EL PROGRAMA
				chooseOptionSet(OPCIONES_SALIDA);
			default:
				wrongOptionWriten(OPCIONES_PRINCIPALES);
				break;
		}
	}

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
				wrongOptionWriten(OPCIONES_NUEVO_CLIENTE);
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
				wrongOptionWriten(OPCIONES_NUEVA_LLAMADA);
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
				return wrongOptionWriten(OPCIONES_NUEVA_FACTURA);
		}
	}

	private void chooseOptionSalida(int option) {
		switch (option) {
			case 1:
				baseDeDatos.exitWithoutSave();
				break;
			case 2:
				IO.out.print("Opcion no implementada.");
				break;
			case 3:
				break;
			default:
				wrongOptionWriten(OPCIONES_SALIDA);
				break;
		}
	}

	private void printIsSatisfactory(boolean s) {
		if (s) IO.out.print("Operacion completada con exito");
		else IO.out.print("La operacion no se ha podido realizar");
		IO.waitIntro();
	}

	private boolean wrongOptionWriten(int set) {
		IO.out.print(wrongOption);
		IO.waitIntro();
		return chooseOptionSet(set);
	}
*/
}
