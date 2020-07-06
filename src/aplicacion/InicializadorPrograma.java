package aplicacion;

import java.util.Scanner;

import administracion.RegistroVehiculo;
import administracion.impl.RegistroVehiculoImpl;
import tipos.Vehiculo;
import utilidades.Constantes;

/**
 * Método que inicializa la aplicación
 * @author ubeimar.vargas
 *
 */
public class InicializadorPrograma {
	
	static Scanner teclado;

	public static void main(String[] args) {
		
		MenuAplicacion menuAplicacion = new MenuAplicacion();
		
		menuAplicacion.inicializarMenuPrincipal();			
		
	}

}
