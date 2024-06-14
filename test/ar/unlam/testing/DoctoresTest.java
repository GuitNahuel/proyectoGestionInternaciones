package ar.unlam.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.unlam.dominio.Sanatorio;
import ar.unlam.dominio.TipoDeHospitalBORRAR;
import ar.unlam.dominio.Doctor.Cardiologo;
import ar.unlam.dominio.Doctor.DoctorNoEncontradoException;
import ar.unlam.dominio.Doctor.Kinesiologo;
import ar.unlam.dominio.Doctor.Pediatra;

public class DoctoresTest {

	@Test
	public void queSePuedaAgregarDoctoresAlHospital() {
		Sanatorio hospital1 = new Sanatorio("Clinica Modelo de Moron", TipoDeHospitalBORRAR.PRIVADO);
		// vamos a crear un doctor para agregarlo al hospital
		String nombre = "Raul";
		String apellido = "Gutierrez";
		Integer edad = 50;
		String dni = "18234567";
		Kinesiologo kinesiologo1 = new Kinesiologo(nombre, apellido, edad, dni, "MHJ208");
		Cardiologo cardiologo1 = new Cardiologo("Diego", "Martinez", 46, "26423212", "GHJ286");
		Pediatra pediatra1 = new Pediatra("Mariano", "Sandez", 30, "26923212", "OKI231");
		// lo agregamos a la lista de doctores
		hospital1.agregarDoctor(kinesiologo1);
		hospital1.agregarDoctor(cardiologo1);
		hospital1.agregarDoctor(pediatra1);
		// Testeamos de que exista y comparamos su nombre para chequear que coincida
		assertEquals(cardiologo1, hospital1.getListaDeDoctores().get(1));
		assertTrue(hospital1.getListaDeDoctores().get(1).equals(cardiologo1));
		assertTrue(hospital1.getListaDeDoctores().get(2).equals(pediatra1));

	}
	@Test
	public void queSePuedaBuscarUnDoctorPorMatricula() throws DoctorNoEncontradoException {
		Sanatorio hospital1 = new Sanatorio("Clinica Modelo de Moron", TipoDeHospitalBORRAR.PRIVADO);
		Cardiologo cardiologo1 = new Cardiologo("Diego", "Martinez", 46, "26423212", "GHJ286");
		Pediatra pediatra1 = new Pediatra("Mariano", "Sandez", 30, "26923212", "OKI231");
		hospital1.agregarDoctor(cardiologo1);
		hospital1.agregarDoctor(pediatra1);
		assertEquals(hospital1.buscarDoctorPorMatricula("OKI231"), pediatra1);
		
	}
	@Test(expected = DoctorNoEncontradoException.class)
	public void queSeNoPuedaBuscarUnDoctorPorMatricula() throws DoctorNoEncontradoException {
		Sanatorio hospital1 = new Sanatorio("Clinica Modelo de Moron", TipoDeHospitalBORRAR.PRIVADO);
		Cardiologo cardiologo1 = new Cardiologo("Diego", "Martinez", 46, "26423212", "GHJ286");
		Pediatra pediatra1 = new Pediatra("Mariano", "Sandez", 30, "26923212", "OKI231");
		hospital1.agregarDoctor(cardiologo1);
		hospital1.agregarDoctor(pediatra1);
		hospital1.buscarDoctorPorMatricula("KI231");
	}
}
