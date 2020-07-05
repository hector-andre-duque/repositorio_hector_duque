package tipos;

public class Vehiculo {
	private 	String  nroPlaca;
	private 	int	    estadoVehiculo;
	private 	int 	tipoVehiculo;
	private 	boolean vehiculoEstacionado = false;
	
	public String getNroPlaca() {
		return nroPlaca;
	}
	public void setNroPlaca(String nroPlaca) {
		this.nroPlaca = nroPlaca;
	}
	public int getEstadoVehiculo() {
		return estadoVehiculo;
	}
	public void setEstadoVehiculo(int estadoVehiculo) {
		this.estadoVehiculo = estadoVehiculo;
	}
	public int getTipoVehiculo() {
		return tipoVehiculo;
	}
	public void setTipoVehiculo(int tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}
	
	public boolean isVehiculoEstacionado() {
		return vehiculoEstacionado;
	}
	public void setVehiculoEstacionado(boolean vehiculoEstacionado) {
		this.vehiculoEstacionado = vehiculoEstacionado;
	}

}
