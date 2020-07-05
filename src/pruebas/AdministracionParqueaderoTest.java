package pruebas;

import static org.junit.Assert.*;

import org.junit.Test;

import administracion.AdministracionParqueadero;
import administracion.RegistroVehiculo;
import administracion.impl.AdministracionParqueaderoImpl;
import administracion.impl.RegistroVehiculoImpl;
import tipos.RegistroCobro;
import tipos.Vehiculo;
import utilidades.Constantes.EstadoVehiculo;
import utilidades.Constantes.TipoVehiculo;

public class AdministracionParqueaderoTest {	
	
	@Test
	public void testRegistrarEntrada() {
		
		Vehiculo vehiculo = new Vehiculo();
		RegistroVehiculo registroVehiculo  = new RegistroVehiculoImpl();	
		
		AdministracionParqueadero administracion  = new AdministracionParqueaderoImpl(registroVehiculo);	
		administracion.registrarEntrada("ABC123");		
		vehiculo = registroVehiculo.buscarVehiculo("ABC123");		
		assertEquals(true, vehiculo.isVehiculoEstacionado());		
		
		Vehiculo vehiculoResidente = new Vehiculo();
		vehiculoResidente.setNroPlaca("ABC122");
		vehiculoResidente.setTipoVehiculo(TipoVehiculo.PARTICULAR);
		vehiculoResidente.setEstadoVehiculo(EstadoVehiculo.RESIDENTE);		
		
		RegistroVehiculo registroVehiculoResidente  = new RegistroVehiculoImpl();	
		
		AdministracionParqueadero administracionResidente  = new AdministracionParqueaderoImpl(registroVehiculoResidente);		
		
		
		registroVehiculoResidente.ingresarVehiculo(vehiculoResidente);
		
		administracionResidente.registrarEntrada("ABC122");
		vehiculo = registroVehiculoResidente.buscarVehiculo("ABC122");		
		assertEquals(true, vehiculo.isVehiculoEstacionado());	
		
		
		Vehiculo vehiculoOficial = new Vehiculo();
		vehiculoOficial.setNroPlaca("ABC133");
		vehiculoOficial.setTipoVehiculo(TipoVehiculo.OFICIAL);
		vehiculoOficial.setEstadoVehiculo(EstadoVehiculo.NO_RESIDENTE);
		
		RegistroVehiculo registroVehiculoOficial = new RegistroVehiculoImpl();	
		
		registroVehiculoOficial.ingresarVehiculo(vehiculoOficial);
		
		AdministracionParqueadero administracionOficial = new AdministracionParqueaderoImpl(registroVehiculoOficial);
		
		administracionOficial.registrarEntrada("ABC133");
		vehiculo = registroVehiculoOficial.buscarVehiculo("ABC133");		
		assertEquals(true, vehiculo.isVehiculoEstacionado());	
				
	
	}	
	

	@Test
	public void testRegistrarSalida() {
	
		RegistroVehiculo registroVehiculo  = new RegistroVehiculoImpl();	
		
		AdministracionParqueadero administracion  = new AdministracionParqueaderoImpl(registroVehiculo);	
		administracion.registrarEntrada("ABC123");		
		Vehiculo vehiculo = registroVehiculo.buscarVehiculo("ABC123");		
		RegistroCobro registroCobro = administracion.registrarSalida(vehiculo);		
		assertEquals(1, registroCobro.getTiempoEstacionado());			
		
	}

	
	@Test
	public void testComenzarMes() {
	
		Vehiculo vehiculoResidente = new Vehiculo();
		vehiculoResidente.setNroPlaca("ABC122");
		vehiculoResidente.setTipoVehiculo(TipoVehiculo.PARTICULAR);
		vehiculoResidente.setEstadoVehiculo(EstadoVehiculo.RESIDENTE);		
		
		RegistroVehiculo registroVehiculoResidente  = new RegistroVehiculoImpl();	
		
		AdministracionParqueadero administracionResidente  = new AdministracionParqueaderoImpl(registroVehiculoResidente);
		registroVehiculoResidente.ingresarVehiculo(vehiculoResidente);
		
		administracionResidente.registrarEntrada("ABC122");
		
		administracionResidente.comenzarMes();		
		
	}
	

	@Test
	public void testGuardarFicheroResidentes() {
		
		Vehiculo vehiculoResidente = new Vehiculo();
		vehiculoResidente.setNroPlaca("ABC122");
		vehiculoResidente.setTipoVehiculo(TipoVehiculo.PARTICULAR);
		vehiculoResidente.setEstadoVehiculo(EstadoVehiculo.RESIDENTE);		
		
		RegistroVehiculo registroVehiculoResidente  = new RegistroVehiculoImpl();	
		
		AdministracionParqueadero administracionResidente  = new AdministracionParqueaderoImpl(registroVehiculoResidente);
		registroVehiculoResidente.ingresarVehiculo(vehiculoResidente);
		
		administracionResidente.registrarEntrada("ABC122");
		
		administracionResidente.registrarSalida(vehiculoResidente);		
		administracionResidente.guardarFicheroResidentes("ResidentePrueba");		
		
	}
	
	

}
