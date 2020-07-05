package transacciones.impl;

import java.util.ArrayList;

import tipos.RegistroNoResidente;
import tipos.RegistroOficial;
import tipos.RegistroResidente;
import tipos.Vehiculo;
import transacciones.RegistroParqueoDAO;

public class RegistroParqueoDaoImpl implements RegistroParqueoDAO {
	
	private ArrayList<RegistroNoResidente> listRegistroNoResidente;
	private ArrayList<RegistroResidente> listRegistroResidente;
	private ArrayList<RegistroOficial> listRegistroOficial;
	
	
	public RegistroParqueoDaoImpl() {		
		this.listRegistroNoResidente = new ArrayList<RegistroNoResidente>();
		this.listRegistroResidente = new  ArrayList<RegistroResidente>();
		this.listRegistroOficial = new  ArrayList<RegistroOficial>();
		
	}	
	
	public void registrarIngresoNoResidente(RegistroNoResidente registroNoResidente) {		
		listRegistroNoResidente.add(registroNoResidente);		
	}
	
	public RegistroNoResidente buscarRegistroNoResidente(String nroPlaca) {
		
		for (RegistroNoResidente registroNoResidente : listRegistroNoResidente) {
			if (registroNoResidente.getNroPlaca().equals(nroPlaca)) {			
				return registroNoResidente;				
			}			
		}		
		return null;		
	}
	
	
	public void borrarRegistroNoResidente(RegistroNoResidente registro) {				
		listRegistroNoResidente.remove(registro);		

	}
	
	
	public void registrarIngresoResidente(RegistroResidente registroResidente) {
		listRegistroResidente.add(registroResidente);
		
	}	
	
	public RegistroResidente buscarRegistroResidente(String nroPlaca) {
		
		for (RegistroResidente registroResidente : listRegistroResidente) {
			if (registroResidente.getNroPlaca().equals(nroPlaca)) {			
				return registroResidente;				
			}			
		}		
		return null;		
	}
	
	
	public ArrayList<RegistroResidente> buscarRegistroResidente() {
		
		return listRegistroResidente;
		
	}
	
	
	public void borrarRegistroResidente(RegistroResidente registro) {
		listRegistroResidente.remove(registro);			
	}
	
	
	public void registrarIngresoOficial(RegistroOficial registroOficial) {
		listRegistroOficial.add(registroOficial);		
	}
	
	public RegistroOficial buscarRegistroOficial(String nroPlaca) {
		
		for (RegistroOficial registroOficial : listRegistroOficial) {
			if (registroOficial.getNroPlaca().equals(nroPlaca)) {			
				return registroOficial;				
			}			
		}		
		return null;			
	}
	
	public void borrarRegistroOficial(RegistroOficial registro) {		
		listRegistroOficial.remove(registro);
	}
	
	
	public void borrarEstadiaTotalResidentes () {
		for (RegistroResidente registroResidente : listRegistroResidente) {
			registroResidente.setTiempoEstacionado(0);	
		}	
		
	}
	
	public void borrarEstanciasOficiales() {
		for (RegistroOficial registroOficial : listRegistroOficial) {				
			registroOficial.getEstancias().clear();				
		}			
	
	}
	
	

}
