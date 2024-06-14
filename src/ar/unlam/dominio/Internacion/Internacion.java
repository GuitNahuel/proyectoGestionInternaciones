package ar.unlam.dominio.Internacion;

import java.time.LocalDate;
import java.util.Comparator;

import ar.unlam.dominio.Doctor.Doctor;
import ar.unlam.dominio.Habitacion.Habitacion;
import ar.unlam.dominio.Paciente.Paciente;

public class Internacion implements Comparable<Internacion> {
	private Paciente paciente;
	private Doctor doctor;
	private LocalDate fechaDeInicioDeInternacion;
	private LocalDate fechaDeFinDeInternacion;
	private Boolean internacionVigente;
	private Habitacion habitacion;

	public Internacion(Paciente paciente, Doctor doctor, LocalDate inicioFechaInternacion, Habitacion habitacion) {
		paciente.setInternacion(true);
		this.paciente = paciente;
		this.doctor = doctor;
		this.fechaDeInicioDeInternacion = inicioFechaInternacion;
		this.habitacion = habitacion;
		;
		habitacion.asignarPaciente(paciente);
	}

	public Boolean estadoInternacionDeUnPaciente() {
		return paciente.isInternacion();

	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDate getFechaDeInicioDeInternacion() {
		return fechaDeInicioDeInternacion;
	}

	public void setFechaDeInicioDeInternacion(LocalDate fechaDeInicioDeInternacion) {
		this.fechaDeInicioDeInternacion = fechaDeInicioDeInternacion;
	}

	public LocalDate getFechaDeFinDeInternacion() {
		return fechaDeFinDeInternacion;
	}

	public void setFechaDeFinDeInternacion(LocalDate fechaDeFinDeInternacion) {
		this.fechaDeFinDeInternacion = fechaDeFinDeInternacion;
	}

	public Boolean getInternacionVigente() {
		return internacionVigente;
	}

	public void isActiva(Boolean internacionActiva) {
		this.internacionVigente = internacionActiva;
		paciente.setInternacion(internacionActiva);
	}

	public void habitacionNuevamenteDisponible() {
		// TODO Auto-generated method stub
		habitacion.quitarPacienteDeHabitacion();
	}

	@Override
	public String toString() {
		return "\n" + "Nombre: " + this.paciente.getNombre() + " " + this.paciente.getApellido()
				+ " Inicio de Internacion= " + this.getFechaDeInicioDeInternacion();
	}

	@Override
	public int compareTo(Internacion o) {
		// TODO Auto-generated method stub

		return this.paciente.getNombre().compareTo(o.getPaciente().getNombre());
	}

}
