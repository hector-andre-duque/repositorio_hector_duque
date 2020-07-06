package administracion.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

import administracion.AdministracionParqueadero;
import administracion.RegistroVehiculo;
import tipos.Registro;
import tipos.RegistroCobro;
import tipos.RegistroNoResidente;
import tipos.RegistroOficial;
import tipos.RegistroResidente;
import tipos.Vehiculo;
import transacciones.RegistroParqueoDAO;
import transacciones.impl.RegistroParqueoDaoImpl;
import utilidades.Constantes.Archivo;
import utilidades.Constantes.CostoTarifa;
import utilidades.Constantes.EstadoVehiculo;
import utilidades.Constantes.TipoVehiculo;

/**
 * AdministracionParqueadero: permite el control y gestion de ingreso y salida
 * parqueadero.
 * 
 * @author hector.duque
 *
 */
public class AdministracionParqueaderoImpl implements AdministracionParqueadero {

	RegistroVehiculo registroVehiculo;
	RegistroParqueoDAO registroParqueDAO;

	public AdministracionParqueaderoImpl(RegistroVehiculo registroVehiculo) {
		this.registroVehiculo = registroVehiculo;
		registroParqueDAO = new RegistroParqueoDaoImpl();

	}

	public AdministracionParqueaderoImpl() {

		registroParqueDAO = new RegistroParqueoDaoImpl();

	}

	/**
	 * registrarEntrada: permite registar la entrada al parqueadero de un vehiculo
	 * dependendiendo de su clasificacion.
	 * 
	 */

	public void registrarEntrada(String nroPlaca) {

		Vehiculo vehiculo = registroVehiculo.buscarVehiculo(nroPlaca);

		if (vehiculo == null) {
			vehiculo = new Vehiculo();
			vehiculo.setNroPlaca(nroPlaca);
			vehiculo.setVehiculoEstacionado(true);
			vehiculo.setEstadoVehiculo(EstadoVehiculo.NO_RESIDENTE);
			vehiculo.setTipoVehiculo(TipoVehiculo.PARTICULAR);
			registroVehiculo.ingresarVehiculo(vehiculo);
		}

		if (vehiculo.getEstadoVehiculo() == EstadoVehiculo.NO_RESIDENTE
				&& vehiculo.getTipoVehiculo() == TipoVehiculo.PARTICULAR) {

			registroParqueDAO.registrarIngresoNoResidente(registrarNoResidente(nroPlaca));

		} else if (vehiculo.getEstadoVehiculo() == EstadoVehiculo.RESIDENTE) {

			registroParqueDAO.registrarIngresoResidente(registrarResidente(nroPlaca));

		} else {

			registroParqueDAO.registrarIngresoOficial(registrarOficial(nroPlaca));

		}

		registroVehiculo.setVehiculoEstacionado(nroPlaca, true);

	}

	/**
	 * registrarSalida: permite registrar la salida del vehiculo del parqueadero
	 * return RegistroCobro : especifica segun caracteristicas del vehiculo la
	 * cuenta de cobro.
	 * 
	 */

	public RegistroCobro registrarSalida(Vehiculo vehiculo) {

		Date fechaActual = new Date();
		int minutos = 0;
		double valorPagar = 0;

		RegistroCobro registroCobro = new RegistroCobro();

		registroCobro.setVehiculo(vehiculo);

		if (vehiculo.getEstadoVehiculo() == EstadoVehiculo.NO_RESIDENTE
				&& vehiculo.getTipoVehiculo() == TipoVehiculo.PARTICULAR) {

			RegistroNoResidente registroNoResidente = registroParqueDAO
					.buscarRegistroNoResidente(vehiculo.getNroPlaca());

			minutos = getDiferenciaMinutosDate(registroNoResidente.getFechaIngreso(), fechaActual);
			if (minutos == 0) {
				minutos = 1;
			}

			valorPagar = minutos * CostoTarifa.COSTO_MINUTO_NO_RESIDENTE;

			registroParqueDAO.borrarRegistroNoResidente(registroNoResidente);

		} else if (vehiculo.getEstadoVehiculo() == EstadoVehiculo.RESIDENTE) {
			RegistroResidente registroResidente = registroParqueDAO.buscarRegistroResidente(vehiculo.getNroPlaca());

			minutos = registroResidente.getTiempoEstacionado()
					+ getDiferenciaMinutosDate(registroResidente.getFechaIngreso(), fechaActual);
			if (minutos == 0) {
				minutos = 1;
			}

			registroParqueDAO.borrarRegistroResidente(registroResidente);
			registroResidente.setFechaSalida(fechaActual);
			registroResidente.setTiempoEstacionado(minutos);
			valorPagar = minutos * CostoTarifa.COSTO_MINUTO_RESIDENTE;
			registroParqueDAO.registrarIngresoResidente(registroResidente);
		} else {

			RegistroOficial registroOficial = registroParqueDAO.buscarRegistroOficial(vehiculo.getNroPlaca());
			registroParqueDAO.borrarRegistroOficial(registroOficial);
			registroOficial.setEstancias(registrarEstanciasOficial(registroOficial.getFechaIngreso(), fechaActual,
					registroOficial.getEstancias()));
			registroParqueDAO.registrarIngresoOficial(registroOficial);
			registroCobro.setStackEstancias(registroOficial.getEstancias());

		}

		registroCobro.setTiempoEstacionado(minutos);
		registroCobro.setValorCobro(valorPagar);

		registroVehiculo.setVehiculoEstacionado(vehiculo.getNroPlaca(), false);

		return registroCobro;

	}

	/**
	 * comenzarMes:Reinicia el total acumulado de los vehiculos residentes y las
	 * instancias de los vehiculos oficiales
	 */
	public void comenzarMes() {

		registroParqueDAO.borrarEstadiaTotalResidentes();
		registroParqueDAO.borrarEstanciasOficiales();

	}

	/**
	 * guardarFicheroResidentes: Permite generar archivo de cobro de los vehiculos
	 * residentes.
	 */

	public String guardarFicheroResidentes(String nombreArchivo) {
		String mensaje;
		String ruta = Archivo.RUTA_ARCHIVO_RESIDENTE + nombreArchivo + Archivo.EXTENSION;
		FileWriter flwriter = null;
		BufferedWriter bfwriter = null;
		try {
			flwriter = new FileWriter(ruta);
			bfwriter = new BufferedWriter(flwriter);
			bfwriter.write("PLANILLA PAGOS VEHÍCULOS RESIDENTES\n");
			bfwriter.newLine();
			bfwriter.write("Matrícula	Tiempo estacionado(mins)	Cantidad a pagar");

			ArrayList<RegistroResidente> listResidentes = registroParqueDAO.buscarRegistroResidente();

			for (RegistroResidente registroResidente : listResidentes) {
				bfwriter.newLine();
				bfwriter.write(registroResidente.getNroPlaca() + "				"
						+ registroResidente.getTiempoEstacionado() + "						"
						+ registroResidente.getTiempoEstacionado() * CostoTarifa.COSTO_MINUTO_RESIDENTE);
			}

			bfwriter.close();
			flwriter.close();
		} catch (Exception e) {
			mensaje = "Error al escribir en el archivo";
		}

		mensaje = "Archivo generado: " + ruta;

		return mensaje;

	}

	private RegistroNoResidente registrarNoResidente(String nroPlaca) {
		RegistroNoResidente registroNoResidente = new RegistroNoResidente();
		Date fechaActual = new Date();
		registroNoResidente.setNroPlaca(nroPlaca);
		registroNoResidente.setFechaIngreso(fechaActual);
		return registroNoResidente;

	}

	private RegistroOficial registrarOficial(String nroPlaca) {
		Date fechaActual = new Date();
		RegistroOficial registroOficial = registroParqueDAO.buscarRegistroOficial(nroPlaca);
		if (registroOficial == null) {
			registroOficial = new RegistroOficial();
			registroOficial.setNroPlaca(nroPlaca);
			registroOficial.setFechaIngreso(fechaActual);

		} else {
			registroParqueDAO.borrarRegistroOficial(registroOficial);
			registroOficial.setFechaSalida(null);
			registroOficial.setFechaIngreso(fechaActual);
		}

		registroOficial.setEstancias(registrarEstanciasOficial(fechaActual, null, registroOficial.getEstancias()));

		return registroOficial;
	}

	private RegistroResidente registrarResidente(String nroPlaca) {
		Date fechaActual = new Date();
		RegistroResidente registroResidente = registroParqueDAO.buscarRegistroResidente(nroPlaca);

		if (registroResidente == null) {
			registroResidente = new RegistroResidente();
			registroResidente.setNroPlaca(nroPlaca);
			registroResidente.setFechaIngreso(fechaActual);
			registroResidente.setTiempoEstacionado(0);
		} else {

			registroParqueDAO.borrarRegistroResidente(registroResidente);
			registroResidente.setFechaSalida(null);
			registroResidente.setFechaIngreso(fechaActual);
		}

		return registroResidente;

	}

	private Stack<Registro> registrarEstanciasOficial(Date fechaIngreso, Date fechaSalida, Stack<Registro> estancias) {
		Stack<Registro> stackEstancias;
		Registro registro = new Registro();

		stackEstancias = estancias;

		registro.setFechaIngreso(fechaIngreso);
		registro.setFechaSalida(fechaSalida);

		if (stackEstancias == null) {
			stackEstancias = new Stack<Registro>();
		}

		if (fechaSalida == null)
			stackEstancias.add(0, registro);
		else {
			stackEstancias.remove(0);
			stackEstancias.add(0, registro);
		}
		return stackEstancias;
	}

	private int getDiferenciaMinutosDate(Date dateInicio, Date dateFinal) {
		long segundos = dateFinal.getTime() - dateInicio.getTime();
		int minutos = (int) ((segundos / (1000 * 60)) % 60);
		return minutos;
	}

}
