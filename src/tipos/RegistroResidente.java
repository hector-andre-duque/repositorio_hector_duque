package tipos;

public class RegistroResidente  extends Registro{
	String nroPlaca;
	private int tiempoEstacionado = 0;
	
	public String getNroPlaca() {
		return nroPlaca;
	}
	public void setNroPlaca(String nroPlaca) {
		this.nroPlaca = nroPlaca;
	}
	public int getTiempoEstacionado() {		
		return tiempoEstacionado;
	}
	public void setTiempoEstacionado(int tiempoEstacionado) {
		this.tiempoEstacionado = tiempoEstacionado;
	}
	
	

}
