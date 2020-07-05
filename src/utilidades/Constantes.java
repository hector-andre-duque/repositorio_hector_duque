package utilidades;

public class Constantes {
	private Constantes() {
	}
	public static final class EstadoVehiculo {
		public static final int RESIDENTE = 1;  
		public static final int NO_RESIDENTE = 2;		
	}
	
	public static final class CostoTarifa {
		public static final int COSTO_MINUTO_RESIDENTE = 25;  
		public static final int COSTO_MINUTO_NO_RESIDENTE =50;
		
	}
	
	public static final class TipoVehiculo {
		public static final int PARTICULAR = 1;  	
		public static final int OFICIAL =2;
			}
	
	public static final class MenuPrincipal {
		public static final int DIGITAR_PLACA = 1;
		public static final int MATRICULAR_VEHICULO = 2 ;		
		public static final int COMENZAR_MES= 3;  
		public static final int GUARDAR_FICHERO= 4;  
		public static final int SALIR= 5;
		public static final int REGISTRAR_INGRESO= 6;
		public static final int REGISTRAR_SALIDA= 7;		
		
	}
	
	public static final class Archivo {
		public static final String RUTA_ARCHIVO_RESIDENTE = "D:\\fichero_residentes\\";	
		public static final String Extension = ".txt";
		public static final String Extensiondoc = ".doc";
		
	}
	
	
	
	
	
}
