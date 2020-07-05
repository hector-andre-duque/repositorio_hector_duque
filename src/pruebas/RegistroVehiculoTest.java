package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import administracion.RegistroVehiculo;
import administracion.impl.RegistroVehiculoImpl;
import tipos.Vehiculo;
import utilidades.Constantes.EstadoVehiculo;
import utilidades.Constantes.TipoVehiculo;

public class RegistroVehiculoTest {
	
	
	RegistroVehiculo registroVehiculo  = new RegistroVehiculoImpl();
		

	@Test
	public void testRegistroVehiculoImpl() {	
		
		
		Vehiculo  vehiculo = new Vehiculo();
		
		vehiculo.setNroPlaca("ABC123");
		vehiculo.setEstadoVehiculo(EstadoVehiculo.RESIDENTE);
		vehiculo.setTipoVehiculo(TipoVehiculo.PARTICULAR);
		vehiculo.setVehiculoEstacionado(false);		
		registroVehiculo.ingresarVehiculo(vehiculo);		
		
	}

	@Test
	public void testBuscarVehiculo() {			
		
		Vehiculo vehiculoEsperado=null;		
		
		vehiculoEsperado = new Vehiculo();
		vehiculoEsperado.setNroPlaca("ABC123");
		vehiculoEsperado.setEstadoVehiculo(EstadoVehiculo.RESIDENTE);
		vehiculoEsperado.setTipoVehiculo(TipoVehiculo.PARTICULAR);
		vehiculoEsperado.setVehiculoEstacionado(false);
		
		registroVehiculo.ingresarVehiculo(vehiculoEsperado); 
		
		Vehiculo vehiculo =  registroVehiculo.buscarVehiculo("ABC123");		
		
		assertEquals(vehiculoEsperado, vehiculo);		
		
		Vehiculo vehiculoEsperadoNUll=null;			
		
		registroVehiculo.ingresarVehiculo(vehiculoEsperado); 
		
		Vehiculo vehiculoNull =  registroVehiculo.buscarVehiculo("ABC120");		
		
		assertEquals(vehiculoEsperadoNUll, vehiculoNull);	
	
	}	
	

	@Test
	public void testBorrarVehiculo() {
		
		Vehiculo vehiculo= null; 
		
		vehiculo = new Vehiculo();
		vehiculo.setNroPlaca("ABC123");
		vehiculo.setEstadoVehiculo(EstadoVehiculo.RESIDENTE);
		vehiculo.setTipoVehiculo(TipoVehiculo.PARTICULAR);
		vehiculo.setVehiculoEstacionado(false);		
		
		registroVehiculo.ingresarVehiculo(vehiculo);
		
		registroVehiculo.borrarVehiculo(vehiculo);
		Vehiculo vehiculoNull =  registroVehiculo.buscarVehiculo("ABC123");			
		assertEquals(null, vehiculoNull);	
	}

	@Test
	public void testSetVehiculoEstacionado() {
		
		Vehiculo vehiculo= null; 
		
		vehiculo = new Vehiculo();
		vehiculo.setNroPlaca("ABC123");
		vehiculo.setEstadoVehiculo(EstadoVehiculo.RESIDENTE);
		vehiculo.setTipoVehiculo(TipoVehiculo.PARTICULAR);
		vehiculo.setVehiculoEstacionado(false);
		
		registroVehiculo.ingresarVehiculo(vehiculo);
		
		registroVehiculo.setVehiculoEstacionado("ABC123",true);
		
		Vehiculo vehiculoEstacionado =  registroVehiculo.buscarVehiculo("ABC123");	
		
		assertEquals(true, vehiculoEstacionado.isVehiculoEstacionado());	
		
		
	}

}
