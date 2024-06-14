package ar.unlam.dominio;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import ar.unlam.dominio.Doctor.Doctor;
import ar.unlam.dominio.Doctor.DoctorNoEncontradoException;
import ar.unlam.dominio.Habitacion.Habitacion;
import ar.unlam.dominio.Habitacion.HabitacionNoEncontradaException;
import ar.unlam.dominio.Internacion.Internacion;
import ar.unlam.dominio.Internacion.InternacionNoEncontradaException;
import ar.unlam.dominio.Paciente.Paciente;
import ar.unlam.dominio.Paciente.PacienteNoEncontradoException;

public class Sanatorio {
	private static final long COSTO_DIA = 10000;
	private String nombre;
	private TipoDeHospitalBORRAR tipoDeHospital;
	private ArrayList<String> listaDeEspecialidades;
	private ArrayList<Doctor> listaDeDoctores;
	private ArrayList<Paciente> listaDePacientes;
	private TreeSet<Internacion> internaciones;
	private ArrayList<Habitacion> listaDeHabitaciones;

	public Sanatorio(String nombre, TipoDeHospitalBORRAR tipoDeHospital) {
		this.nombre = nombre;
		this.tipoDeHospital = tipoDeHospital;
		listaDeEspecialidades = new ArrayList<String>();
		listaDeDoctores = new ArrayList<Doctor>();
		listaDePacientes = new ArrayList<Paciente>();
		internaciones = new TreeSet<Internacion>();
		listaDeHabitaciones = new ArrayList<Habitacion>();
	}

	public String getNombre() {
		return nombre;
	}

	public ArrayList<Habitacion> getListaDeHabitaciones() {
		return listaDeHabitaciones;
	}

	public static long getCostoDia() {
		return COSTO_DIA;
	}

	public TreeSet<Internacion> getInternaciones() {
		return internaciones;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoDeHospitalBORRAR getTipoDeHospital() {
		return tipoDeHospital;
	}

	public void agregarEspecialidad(String especialidad) {
		listaDeEspecialidades.add(especialidad);

	}

	public boolean agregarDoctor(Doctor doctor) {
		return listaDeDoctores.add(doctor);

	}

	public Boolean agregarHabitacion(Habitacion habitacion) throws HabitacionNoEncontradaException {
		if (habitacion == null) {
			throw new HabitacionNoEncontradaException("No existe la habitacion");
		}
		for (Habitacion h : listaDeHabitaciones) {

			if (h.numeroHabitacion.equals(habitacion.getNumeroHabitacion())) {
				return false;
			}
		}
		return listaDeHabitaciones.add(habitacion);

	}

	public Boolean habitacionDisponible(Integer numeroHabitacionABuscar) {
		for (Habitacion habitacion : listaDeHabitaciones) {
			if (habitacion.getNumeroHabitacion().equals(numeroHabitacionABuscar) && habitacion.getPaciente() == null) {
				return true;
			}
		}
		return false;

	}

	public boolean agregarPacienteAlSistema(Paciente paciente) {
		for (Paciente p : listaDePacientes) {
			if (p.getDni().equals(paciente.getDni())) {
				return false;

			}

		}
		return listaDePacientes.add(paciente);

	}

	public ArrayList<String> getListaDeEspecialidades() {
		return listaDeEspecialidades;
	}

	public ArrayList<Doctor> getListaDeDoctores() {
		return listaDeDoctores;
	}

	public ArrayList<Paciente> getListaDePacientes() {
		return listaDePacientes;
	}

	public void ordenarPacientesIngresadosEnElSistemaPorNombre() {
		;
	}

	public String obtenerListaDePacientesIngresadosEnElSistemaPorNombre() {
		return listaDePacientes.toString();
	}

	public TreeSet<Internacion> obtenerInternacionesOrdenadasPorFechaDeInicio() {
		TreeSet<Internacion> internacionesOrdenadasPorFecha = new TreeSet<Internacion>(new Comparator<Internacion>() {

			@Override
			public int compare(Internacion o1, Internacion o2) {
				LocalDate fechaInicioInternacion1 = o1.getFechaDeInicioDeInternacion();
				LocalDate fechaInicioInternacion2 = o2.getFechaDeInicioDeInternacion();

				return fechaInicioInternacion1.compareTo(fechaInicioInternacion2);
			}
		});
		internacionesOrdenadasPorFecha.addAll(internaciones);
		return internacionesOrdenadasPorFecha;
	}
//	if (fechaInicioInternacion1==null&&fechaInicioInternacion2==null) {
//	throw new FechaNoEncontradaException("No existe la fecha");
//	}
	public String obtenerListaDeInternacionesPorFechaDeInicio() {
		return internaciones.toString();
	}

	public Habitacion buscarHabitacionPorNumero(Integer numeroHabitacionBuscada)
			throws HabitacionNoEncontradaException {
		// TODO Auto-generated method stub
		Habitacion habitacionBuscada = null;
		for (Habitacion h : listaDeHabitaciones) {
			if (h.getNumeroHabitacion().equals(numeroHabitacionBuscada)) {
				habitacionBuscada = h;
				break;
			}
		}
		if (habitacionBuscada == null) {
			throw new HabitacionNoEncontradaException("No existe la habitacion");
		}
		return habitacionBuscada;
	}

	public Doctor buscarDoctorPorMatricula(String matricula) throws DoctorNoEncontradoException {
		Doctor doctorBuscado = null;
		for (Doctor d : listaDeDoctores) {
			if (d.getMatricula().equals(matricula)) {
				doctorBuscado = d;
				break;
			}
		}
		if (doctorBuscado == null) {
			throw new DoctorNoEncontradoException("No existe la matricula");
		}
		return doctorBuscado;
	}

	public Paciente buscarPaciente(String dni) throws PacienteNoEncontradoException {
		for (Paciente paciente : listaDePacientes) {
			if (paciente.getDni().equals(dni)) {
				return paciente;
			}
		}
		throw new PacienteNoEncontradoException("No existe el dni");
	}

	public boolean pacienteSeEncuentraInternado(String dni) throws PacienteNoEncontradoException {
		Persona paciente = buscarPaciente(dni);
		for (Internacion internacion : internaciones) {
			if (internacion.getPaciente().equals(paciente)) {
				return paciente.isInternacion();
			}
		}
		return false;
	}

	public Boolean agregarInternacion(Internacion internacion) throws PacienteNoEncontradoException {
		// Verificar si el paciente ya está internado
		for (Internacion in : internaciones) {
			if (in.getPaciente().equals(internacion.getPaciente()) && in.estadoInternacionDeUnPaciente()
					&& in.getInternacionVigente()) {
				return false; // Ya está internado, no se puede agregar otra internación
			}
		}

		// Si no está internado, agregar la nueva internación
		internacion.isActiva(true);
		internaciones.add(internacion);
		return true;
	}

	public Boolean finalizarInternacion(String dni, LocalDate fechaFinDeInternacion)
			throws PacienteNoEncontradoException, InternacionNoEncontradaException {
		Internacion internacion = buscarInternacionPorDniDelPaciente(dni);
		if (internacion != null) {
			internacion.setFechaDeFinDeInternacion(fechaFinDeInternacion);
			internacion.isActiva(false);
			internacion.habitacionNuevamenteDisponible();
			return true;
		}
		return false;

	}

	public Internacion buscarInternacionPorDniDelPaciente(String dni)
			throws PacienteNoEncontradoException, InternacionNoEncontradaException {
		Internacion internacionBuscada = null;
		for (Internacion in : internaciones) {
			if (in.getPaciente().getDni().equals(dni)) {
				internacionBuscada = in;
				break;
			}
		}
		if (internacionBuscada == null) {
			throw new InternacionNoEncontradaException("No existe la internacion");
		}
		return internacionBuscada;
	}

	public Double obtenerMontoaPagarDeInternacion(String dni)
			throws PacienteNoEncontradoException, InternacionNoEncontradaException {
		Internacion internacion = buscarInternacionPorDniDelPaciente(dni);
		if (internacion.getFechaDeFinDeInternacion() != null && internacion.getFechaDeInicioDeInternacion() != null) {
			LocalDate ingresoInternacion = internacion.getFechaDeInicioDeInternacion();
			LocalDate egresoInternacion = internacion.getFechaDeFinDeInternacion();
			long diasInternado = ChronoUnit.DAYS.between(ingresoInternacion, egresoInternacion);
			double costoTotal = diasInternado * COSTO_DIA;
			double porcentajeCobertura = 0.0;

			switch (internacion.getPaciente().getTipoDeCobertura()) {
			case PLAN5000:
				porcentajeCobertura = 0.70;
				break;
			case PLAN3000:
				porcentajeCobertura = 0.50;
				break;
			case PLAN1500:
				porcentajeCobertura = 0.30;
				break;
			case SINPLAN:
				if (diasInternado <= 10) {
					porcentajeCobertura = 0.10;
				} else {
					porcentajeCobertura = 0.0;
				}
				break;
			}

			double costoCubierto = costoTotal * porcentajeCobertura;
			double costoPaciente = costoTotal - costoCubierto;
			return costoPaciente;
		}

		return 0.0;

	}

}
