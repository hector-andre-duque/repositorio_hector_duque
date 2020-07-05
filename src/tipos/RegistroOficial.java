package tipos;

import java.util.Stack;

public class RegistroOficial extends Registro  {
	
	private String nroPlaca;
	private Stack<Registro> estancias;
	
	
	public String getNroPlaca() {
		return nroPlaca;
	}
	public void setNroPlaca(String nroPlaca) {
		this.nroPlaca = nroPlaca;
	}
	public Stack<Registro> getEstancias() {
		return estancias;
	}
	public void setEstancias(Stack<Registro> estancias) {
		this.estancias = estancias;
	}
	
	
	
	

}
