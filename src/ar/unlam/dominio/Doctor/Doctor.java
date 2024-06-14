package ar.unlam.dominio.Doctor;

import ar.unlam.dominio.Persona;

public abstract class Doctor extends Persona {
	private String matricula;

	public Doctor(String nombre, String apellido, Integer edad, String dni, String matricula) {
		super(nombre, apellido, edad, dni);
		this.matricula = matricula;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

}
