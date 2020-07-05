package administracion;

import tipos.Vehiculo;

public interface RegistroVehiculo {
	
	public void ingresarVehiculo(Vehiculo vehiculo);
	public Vehiculo buscarVehiculo(String nroPlaca);
	public void borrarVehiculo(Vehiculo vehiculo);	
	public void setVehiculoEstacionado(String nroPlaca,boolean estacionado);

}
