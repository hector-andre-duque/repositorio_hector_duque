package transacciones;

import tipos.RegistroNoResidente;
import tipos.Vehiculo;

public interface RegistroVehiculoDAO {	
	
	public void ingresarVehiculo(Vehiculo vehiculo);
	public Vehiculo buscarVehiculo(String nroPlaca);
	public void borrarVehiculo(Vehiculo vehiculo);
	public void setVehiculoEstacionado(String nroPlaca,boolean estacionado);
}
