package ar.unlam.dominio.Paciente;

import ar.unlam.dominio.Doctor.Doctor;

public interface IPaciente {

public Boolean asignarDoctor(Doctor doctor);

public Doctor verDoctorAsignado();

public Boolean sacarTurno();

public void alta();

}