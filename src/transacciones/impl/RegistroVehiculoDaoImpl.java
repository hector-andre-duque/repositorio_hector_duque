package transacciones.impl;

import java.util.ArrayList;
import java.util.List;

import tipos.RegistroNoResidente;
import tipos.Vehiculo;
import transacciones.RegistroVehiculoDAO;

public class RegistroVehiculoDaoImpl implements RegistroVehiculoDAO {	
	
	private ArrayList<Vehiculo> listVehiculo;
	
	
	public RegistroVehiculoDaoImpl() {
		super();
		this.listVehiculo = new ArrayList<Vehiculo>();		
	}

	public void ingresarVehiculo(Vehiculo vehiculo) {		
		listVehiculo.add(vehiculo);		
	}
	
	public Vehiculo buscarVehiculo(String nroPlaca) {
		
		for (Vehiculo vehiculo : listVehiculo) {
			if (vehiculo.getNroPlaca().equals(nroPlaca)) {			
				return vehiculo;				
			}			
		}		
		return null;		
	}
	
	public void borrarVehiculo(Vehiculo vehiculo) {				
				listVehiculo.remove(vehiculo);		
		
	}	
	
	public void setVehiculoEstacionado(String nroPlaca,boolean estacionado) {
		Vehiculo vehiculo = buscarVehiculo(nroPlaca);
		borrarVehiculo(vehiculo);
		vehiculo.setVehiculoEstacionado(estacionado);
		listVehiculo.add(vehiculo);
		
		
	}
		

}
