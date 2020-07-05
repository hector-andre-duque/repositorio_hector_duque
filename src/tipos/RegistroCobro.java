package tipos;

import java.util.Stack;

public class RegistroCobro {
	
	Vehiculo vehiculo;
	int tiempoEstacionado;
	double valorCobro;
	Stack<Registro> stackEstancias;
	
	public Vehiculo getVehiculo() {
		return vehiculo;
	}
	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}
	public int getTiempoEstacionado() {
		return tiempoEstacionado;
	}
	public void setTiempoEstacionado(int tiempoEstacionado) {
		this.tiempoEstacionado = tiempoEstacionado;
	}
	public double getValorCobro() {
		return valorCobro;
	}
	public void setValorCobro(double valorCobro) {
		this.valorCobro = valorCobro;
	}
	public Stack<Registro> getStackEstancias() {
		return stackEstancias;
	}
	public void setStackEstancias(Stack<Registro> stackEstancias) {
		this.stackEstancias = stackEstancias;
	}
	
	

}
