package administracion;

import tipos.RegistroCobro;
import tipos.Vehiculo;

public interface AdministracionParqueadero {
	
	
	public void registrarEntrada(String placa);
	
	public RegistroCobro registrarSalida(Vehiculo vehiculo); 
	
	public String guardarFicheroResidentes(String nombreArchivo);	

	public void comenzarMes();
}
