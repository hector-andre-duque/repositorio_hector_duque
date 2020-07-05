package aplicacion;

import java.text.SimpleDateFormat;
import java.util.Scanner;

import administracion.AdministracionParqueadero;
import administracion.RegistroVehiculo;
import administracion.impl.AdministracionParqueaderoImpl;
import administracion.impl.RegistroVehiculoImpl;
import tipos.Registro;
import tipos.RegistroCobro;
import tipos.Vehiculo;
import utilidades.Constantes;
import utilidades.Constantes.EstadoVehiculo;
import utilidades.Constantes.MenuPrincipal;
import utilidades.Constantes.TipoVehiculo;

public class MenuAplicacion {
	
	Scanner teclado ;	
	
	RegistroVehiculo registroVehiculo;
	AdministracionParqueadero administracion; 
	
	
	public MenuAplicacion() {		
		this.teclado = new Scanner(System.in); 
		registroVehiculo = new RegistroVehiculoImpl(); 
		administracion = new AdministracionParqueaderoImpl(registroVehiculo);
	}
	
	
	public void menuComenzarMes() {
		System.out.println("Se va generar el archivo de pago para los vehículos residentes...");
		menuPagoResidentes();		
		administracion.comenzarMes();		
	}
	
	public void menuPagoResidentes() {
		String nombreArchivo,mensaje;
		System.out.println("Ingrese el nombre del fichero sin extensión:");
		nombreArchivo = teclado.next();
		try {
			mensaje = administracion.guardarFicheroResidentes(nombreArchivo);
			System.out.println(mensaje);
			
		} catch (Exception e) {
			System.out.println("Error al guardar el archivo "+e);
		}		
	}
	

	public int menuInicial() {
		
		System.out.println("==========================================================================");
		System.out.println("Para ingresar un vehículo Oficial o Residente se debe primero Matricular");
		System.out.println("==========================================================================");	
		System.out.println(MenuPrincipal.DIGITAR_PLACA+". Digitar placa.");
		System.out.println(MenuPrincipal.MATRICULAR_VEHICULO+". Matricular Vehiculo");	
		System.out.println(MenuPrincipal.COMENZAR_MES+". Comenzar mes");	
		System.out.println(MenuPrincipal.GUARDAR_FICHERO+". Pagos Residentes");	
		return teclado.nextInt();
		 
	}
	
	public void menuDigitarPlaca() {
		String nroPlaca;
		int opcion;  
		Vehiculo vehiculo;
		
		System.out.println("==================================" );		
		System.out.println("placa:" );
		nroPlaca = teclado.next();	
		vehiculo = registroVehiculo.buscarVehiculo(nroPlaca);		
		if (vehiculo==null) {
			System.out.println(MenuPrincipal.REGISTRAR_INGRESO+" Ingresar Vehiculo parqueadero" );
			
		}else {
			if (vehiculo.isVehiculoEstacionado()) {
				System.out.println(MenuPrincipal.REGISTRAR_SALIDA+" Registrar Salida " );
			}else {
				System.out.println(MenuPrincipal.REGISTRAR_INGRESO+" Ingresar Vehiculo parqueadero" );
			}			
		}
		System.out.println(MenuPrincipal.SALIR+" Salir" );
		opcion  = teclado.nextInt();
	
		switch (opcion) {
		case MenuPrincipal.REGISTRAR_INGRESO:			
			administracion.registrarEntrada(nroPlaca);			
			break;
		case MenuPrincipal.REGISTRAR_SALIDA:
			imprimirCuentaCobro(administracion.registrarSalida(vehiculo));			
			break;
	
		default:
			inicializarMenuPrincipal();
			break;
		}	
		
		inicializarMenuPrincipal();		
		
	}	
	
	
	public void imprimirCuentaCobro(RegistroCobro registroCobro) {
		
		System.out.println("========REGISTRO DE SALIDA================");
		System.out.println("PLACA: "+registroCobro.getVehiculo().getNroPlaca());
		if (registroCobro.getVehiculo().getTipoVehiculo() == TipoVehiculo.PARTICULAR)
			System.out.println("TIPO VEHICULO: PARTICULAR");		
		else
			System.out.println("TIPO VEHICULO: OFICIAL");
		
		if (registroCobro.getVehiculo().getEstadoVehiculo() == EstadoVehiculo.RESIDENTE) 
			System.out.println("ESTADO VEHICULO: RESIDENTE");
		else	
			System.out.println("ESTADO VEHICULO: NO RESIDENTE");
		if (registroCobro.getVehiculo().getTipoVehiculo() != TipoVehiculo.OFICIAL )
			System.out.println("tiempo estadia total(min):"+registroCobro.getTiempoEstacionado());
		if (registroCobro.getVehiculo().getEstadoVehiculo() == EstadoVehiculo.NO_RESIDENTE) 			 
			System.out.println("Valor a cobrar:"+registroCobro.getValorCobro());
		if (registroCobro.getVehiculo().getTipoVehiculo() == TipoVehiculo.OFICIAL) {
			System.out.println("Historial de instancias");
			System.out.println("Fecha Ingreso          Fecha salida");
			
			SimpleDateFormat formatter = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
			String strDate;
			for (Registro registro : registroCobro.getStackEstancias()) {
				strDate = formatter.format(registro.getFechaIngreso());  
				System.out.print(strDate);
				System.out.print("      ");
				strDate = formatter.format(registro.getFechaSalida());  
				System.out.println(strDate);				
			}
		}	
		System.out.println("================================================");
		
	}
	
	public void menuMatricularVehiculo(){
		String nroPlaca,rematricular;		
		Vehiculo vehiculo;
		
		System.out.println("Placa:");
		nroPlaca = teclado.next();
		
		vehiculo = registroVehiculo.buscarVehiculo(nroPlaca);
		if(vehiculo==null) {
			vehiculo = new Vehiculo();	
			vehiculo.setNroPlaca(nroPlaca);
			System.out.println("=============Matricular Vehiculo: "+nroPlaca+"=============");		
			System.out.println("Digite el tipo >>> " +Constantes.TipoVehiculo.PARTICULAR+": Particular, " +Constantes.TipoVehiculo.OFICIAL+": Oficial ");			
			vehiculo.setTipoVehiculo(teclado.nextInt());		
			if (vehiculo.getTipoVehiculo() == Constantes.TipoVehiculo.PARTICULAR) {		
				System.out.println("Digite el estado >>> "+Constantes.EstadoVehiculo.RESIDENTE+": Vehículo residente, "+Constantes.EstadoVehiculo.NO_RESIDENTE +": Vehiculo no Residente ");
				vehiculo.setEstadoVehiculo(teclado.nextInt());	
			}else {			
				vehiculo.setEstadoVehiculo(Constantes.EstadoVehiculo.NO_RESIDENTE);		
			}	
			vehiculo.setVehiculoEstacionado(false);
			registroVehiculo.ingresarVehiculo(vehiculo);
			
		}else {		
			
			
			
			System.out.println("El vehiculo "+nroPlaca+" ya se encuentra matriculado");
			System.out.println("Desea Rematricularlo?S/N");
			rematricular= teclado.next().toUpperCase();		
			
			if (!vehiculo.isVehiculoEstacionado()){
			
				if (rematricular.equalsIgnoreCase("S")){				
					registroVehiculo.borrarVehiculo(vehiculo);
					menuMatricularVehiculo();
				}				
			  	
			}else {
				
				System.out.println("El vehiculo se encuentra parqueado, debe regitrar la salida para rematricular");
			}
		}
		
		inicializarMenuPrincipal();
		
	}	
	
	public void  inicializarMenuPrincipal(){
		
		switch (menuInicial()) {		
		case  MenuPrincipal.DIGITAR_PLACA:			
			  menuDigitarPlaca();				
			break;	
		case MenuPrincipal.MATRICULAR_VEHICULO:			
			menuMatricularVehiculo(); 
			break;
		case MenuPrincipal.COMENZAR_MES:			
			menuComenzarMes();		
			break;	
			
		case MenuPrincipal.GUARDAR_FICHERO:
			menuPagoResidentes();
			break;
		
		case MenuPrincipal.SALIR:
			System.exit(0);
			break;
		}
	
		inicializarMenuPrincipal();
		
	}		
}
