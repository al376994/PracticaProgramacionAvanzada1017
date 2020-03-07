package Auxiliares;

import java.time.LocalDate;

// Esta interfaz es comun entre todas las clases que pueden devolver su fecha, se utiliza a la hora de recuperar
// todos los elementos de una lista que se encuentran entre 2 fechas.

public interface TieneFecha {

	LocalDate getFecha();

}
