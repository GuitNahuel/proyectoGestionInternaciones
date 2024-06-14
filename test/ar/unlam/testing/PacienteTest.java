package ar.unlam.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.unlam.dominio.Sanatorio;
import ar.unlam.dominio.TipoDeCobertura;
import ar.unlam.dominio.TipoDeHospitalBORRAR;
import ar.unlam.dominio.Paciente.Paciente;
import ar.unlam.dominio.Paciente.PacienteNoEncontradoException;

public class PacienteTest {


	@Test
	public void queSePuedaCargarUnPaciente() {
		Sanatorio hospital1 = new Sanatorio("Clinica Modelo de Moron", TipoDeHospitalBORRAR.PRIVADO);
		String nombre = "Ignacio";
		String apellido = "Gutierrez";
		Integer edad = 20;
		String dni = "45123200";
		TipoDeCobertura tipoDeCobertura=TipoDeCobertura.PLAN5000;
		Paciente paciente1 = new Paciente(nombre, apellido, edad, dni, tipoDeCobertura);

		assertTrue(hospital1.agregarPacienteAlSistema(paciente1));
	}
	@Test
	public void queSePuedaAgregarUnPaciente() {
		Sanatorio sanatorio = new Sanatorio("Clinica Modelo de Moron", TipoDeHospitalBORRAR.PRIVADO);
		String nombre = "Ignacio";
		String apellido = "Gutierrez";
		Integer edad = 20;
		String dni = "45123200";
		Paciente paciente = new Paciente(nombre,apellido,edad,dni, TipoDeCobertura.PLAN5000);
		sanatorio.agregarPacienteAlSistema(paciente);
		assertNotNull(sanatorio.getListaDePacientes().get(0));
	}

	@Test
	public void queSePuedaBuscarUnPaciente() throws PacienteNoEncontradoException {
		Sanatorio hospital1 = new Sanatorio("Clinica Modelo de Moron", TipoDeHospitalBORRAR.PRIVADO);
		String nombre = "Ignacio";
		String apellido = "Gutierrez";
		Integer edad = 20;
		String dni = "45123200";
		String nombre1 = "Juan";
		String apellido1 = "Spinetta";
		Integer edad1 = 40;
		String dni1 = "25123200";
		TipoDeCobertura tipoDeCobertura=TipoDeCobertura.PLAN5000;
		Paciente paciente = new Paciente(nombre, apellido, edad, dni, tipoDeCobertura);
		Paciente paciente1 = new Paciente(nombre1, apellido1, edad1, dni1, tipoDeCobertura);
		hospital1.agregarPacienteAlSistema(paciente);
		hospital1.agregarPacienteAlSistema(paciente1);
		assertEquals(hospital1.buscarPaciente("25123200").getDni(), paciente1.getDni());

	}

	@Test(expected = PacienteNoEncontradoException.class)
	public void queSeNoPuedaBuscarUnPaciente() throws PacienteNoEncontradoException {
		Sanatorio sanatorio = new Sanatorio("Clinica Modelo de Moron", TipoDeHospitalBORRAR.PRIVADO);
		String nombre = "Ignacio";
		String apellido = "Gutierrez";
		Integer edad = 20;
		String dni = "45123200";
		String nombre1 = "Juan";
		String apellido1 = "Spinetta";
		Integer edad1 = 40;
		String dni1 = "25123200";
		TipoDeCobertura tipoDeCobertura=TipoDeCobertura.PLAN5000;

		Paciente paciente = new Paciente(nombre, apellido, edad, dni, tipoDeCobertura);
		Paciente paciente1 = new Paciente(nombre1, apellido1, edad1, dni1, tipoDeCobertura);
		sanatorio.agregarPacienteAlSistema(paciente);
		sanatorio.agregarPacienteAlSistema(paciente1);
		sanatorio.buscarPaciente("gaasdasd");
	}
	@Test
	public void queSePuedaOrdenarLaListaDePacientesCargadosEnElSistemaPorElNombre() {
		Sanatorio sanatorio = new Sanatorio("DIM", TipoDeHospitalBORRAR.PRIVADO);
		sanatorio.agregarPacienteAlSistema(new Paciente("Juan", "Pérez", 30, "12345678", TipoDeCobertura.PLAN1500));
		sanatorio.agregarPacienteAlSistema(new Paciente("María", "González", 45, "87654321", TipoDeCobertura.PLAN3000));
		sanatorio.agregarPacienteAlSistema(new Paciente("Carlos", "Martínez", 50, "11223344", TipoDeCobertura.PLAN3000));
		sanatorio.agregarPacienteAlSistema(new Paciente("Ana", "Rodríguez", 60, "44332211", TipoDeCobertura.PLAN5000));
		sanatorio.agregarPacienteAlSistema(new Paciente("Lucía", "Fernández", 25, "55667788", TipoDeCobertura.SINPLAN));
		sanatorio.ordenarPacientesIngresadosEnElSistemaPorNombre();
		Paciente pacienteParaComparar= new Paciente("Ana", "Rodríguez", 60, "44332211", TipoDeCobertura.PLAN5000);
		assertEquals(sanatorio.getListaDePacientes().get(0), pacienteParaComparar);
	}

}
