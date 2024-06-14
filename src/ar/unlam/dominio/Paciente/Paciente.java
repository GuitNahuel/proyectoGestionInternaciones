package ar.unlam.dominio.Paciente;

import ar.unlam.dominio.Persona;
import ar.unlam.dominio.TipoDeCobertura;
import ar.unlam.dominio.Doctor.Doctor;

public class Paciente extends Persona implements IPaciente {

	private Boolean estaInternado;
	private TipoDeCobertura tipoDeCobertura;

	public Paciente(String nombre, String apellido, Integer edad, String dni, TipoDeCobertura tipoDeCobertura) {
		super(nombre, apellido, edad, dni);
		this.tipoDeCobertura=tipoDeCobertura;
		setInternacion(false);
	}

	public Boolean getEstaInternado() {
		return estaInternado;
	}

	public void setEstaInternado(Boolean estaInternado) {
		this.estaInternado = estaInternado;
	}

	public TipoDeCobertura getTipoDeCobertura() {
		return tipoDeCobertura;
	}

	public void setTipoDeCobertura(TipoDeCobertura tipoDeCobertura) {
		this.tipoDeCobertura = tipoDeCobertura;
	}

	@Override
	public Boolean asignarDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Doctor verDoctorAsignado() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean sacarTurno() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void alta() {
		// TODO Auto-generated method stub

	}

	public Boolean isInternacion() {
		return estaInternado;
	}

	public void setInternacion(Boolean internacion) {
		this.estaInternado = internacion;
	}
	 @Override
	    public String toString() {
	        return "\n"+"Paciente: " + this.nombre + " " + this.apellido;
	    }
	

}
