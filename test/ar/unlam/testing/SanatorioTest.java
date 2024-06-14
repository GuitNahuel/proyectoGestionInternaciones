package ar.unlam.testing;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.unlam.dominio.Sanatorio;
import ar.unlam.dominio.TipoDeHospitalBORRAR;

public class SanatorioTest {

	@Test
	public void queSePuedaCrearUnHospital() {
		// Creamos un hospital con el constructo por default y tambien uno que incluya
		// si es tipo
		// priva o publico
		String nombre = "Favaloro";
		Sanatorio hospital2 = new Sanatorio(nombre, TipoDeHospitalBORRAR.PRIVADO);
		// Corroboramos que hayan sido creados exitosamente
		assertNotNull(hospital2);
	}

	@Test
	public void queSePuedaAgregarEspecialidadesDentroDelHospital() {
		Sanatorio hospital1 = new Sanatorio("Clinica Modelo de Moron", TipoDeHospitalBORRAR.PRIVADO);
		// Creamos especialidades y las agregamos al hospital
		String especialidad1 = "Cardiologia";
		String especialidad2 = "Odontologia";
		hospital1.agregarEspecialidad(especialidad1);
		hospital1.agregarEspecialidad(especialidad2);
		// Comprobamos que esas especialidades hayan sido agregadas al hospital
		assertEquals(especialidad1, hospital1.getListaDeEspecialidades().get(0));
		assertEquals(especialidad2, hospital1.getListaDeEspecialidades().get(1));
	}


}
