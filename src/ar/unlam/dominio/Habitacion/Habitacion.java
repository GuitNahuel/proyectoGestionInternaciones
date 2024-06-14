package ar.unlam.dominio.Habitacion;

import ar.unlam.dominio.Paciente.Paciente;

public class Habitacion {

	public Integer numeroHabitacion;
	private Paciente paciente;

	public Habitacion(Integer numeroDeHabitacion) {
		this.numeroHabitacion = numeroDeHabitacion;
		this.paciente = null;
	}

	public Integer getNumeroHabitacion() {
		return numeroHabitacion;
	}

	public void setNumeroHabitacion(Integer numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public Boolean asignarPaciente(Paciente pacienteAIngresar) {
		if (this.paciente == null) {
			this.paciente = pacienteAIngresar;
			return true;
		} else {
			return false;
		}
	}

	public void quitarPacienteDeHabitacion() {
		paciente = null;
	}

	


}
