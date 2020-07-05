package transacciones;

import java.util.ArrayList;

import tipos.RegistroNoResidente;
import tipos.RegistroOficial;
import tipos.RegistroResidente;

public interface RegistroParqueoDAO {
	
	public void registrarIngresoNoResidente(RegistroNoResidente registroNoResidente);
	public RegistroNoResidente buscarRegistroNoResidente(String nroPlaca);
	public void borrarRegistroNoResidente(RegistroNoResidente registro);
	
	public void registrarIngresoResidente(RegistroResidente registroResidente);	
	public RegistroResidente buscarRegistroResidente(String nroPlaca);
	public void borrarRegistroResidente(RegistroResidente registro);	
	public ArrayList<RegistroResidente> buscarRegistroResidente();
	
	public void registrarIngresoOficial(RegistroOficial rigstroOficial);		
	public RegistroOficial buscarRegistroOficial(String nroPlaca);
	public void borrarRegistroOficial(RegistroOficial registro);	
	
	public void borrarEstadiaTotalResidentes();	
	public void borrarEstanciasOficiales(); 
		
	
}
