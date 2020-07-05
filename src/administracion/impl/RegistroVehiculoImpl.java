package administracion.impl;

import administracion.RegistroVehiculo;
import tipos.Vehiculo;
import transacciones.RegistroVehiculoDAO;
import transacciones.impl.RegistroVehiculoDaoImpl;

public class RegistroVehiculoImpl implements RegistroVehiculo{
	
	private RegistroVehiculoDAO registroVehiculoDAO;	
	
	public RegistroVehiculoImpl() {
		super();
		this.registroVehiculoDAO = new RegistroVehiculoDaoImpl();
	}

	public void ingresarVehiculo(Vehiculo vehiculo) {		
		registroVehiculoDAO.ingresarVehiculo(vehiculo);		
	}
	
	public Vehiculo buscarVehiculo(String nroPlaca) {
		
	   return registroVehiculoDAO.buscarVehiculo(nroPlaca);	
		
	}
	
	public void borrarVehiculo(Vehiculo vehiculo) {
		registroVehiculoDAO.borrarVehiculo(vehiculo);		
	}
	
	public void setVehiculoEstacionado(String nroPlaca,boolean estacionado) {
		registroVehiculoDAO.setVehiculoEstacionado(nroPlaca,estacionado);
		
	}
	

}
