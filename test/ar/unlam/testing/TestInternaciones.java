package ar.unlam.testing;

import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;
import ar.unlam.dominio.*;
import ar.unlam.dominio.Doctor.Cardiologo;
import ar.unlam.dominio.Doctor.Doctor;
import ar.unlam.dominio.Doctor.DoctorNoEncontradoException;
import ar.unlam.dominio.Doctor.Kinesiologo;
import ar.unlam.dominio.Doctor.Pediatra;
import ar.unlam.dominio.Habitacion.Habitacion;
import ar.unlam.dominio.Habitacion.HabitacionNoEncontradaException;
import ar.unlam.dominio.Internacion.Internacion;
import ar.unlam.dominio.Internacion.InternacionNoEncontradaException;
import ar.unlam.dominio.Paciente.Paciente;
import ar.unlam.dominio.Paciente.PacienteNoEncontradoException;

public class TestInternaciones {
	Sanatorio sanatorio;
	Habitacion habitacion;

	@Before
	public void setUp() throws HabitacionNoEncontradaException {
		// Creamos nuestro sanatorio que se encargara de administrar las internaciones
		// de los pacientes
		sanatorio = new Sanatorio("Dim", TipoDeHospitalBORRAR.PRIVADO);
		// Creamos y agregamos habitaciones a nuestro sanatorio
		sanatorio.agregarHabitacion(new Habitacion(11));
		sanatorio.agregarHabitacion(new Habitacion(21));
		sanatorio.agregarHabitacion(new Habitacion(31));
		sanatorio.agregarHabitacion(new Habitacion(41));
		sanatorio.agregarHabitacion(new Habitacion(51));

		// Creamos y agregamos pacientes a nuestro sanatorio
		sanatorio.agregarPacienteAlSistema(new Paciente("Juan", "Pérez", 30, "11111111", TipoDeCobertura.PLAN1500));
		sanatorio.agregarPacienteAlSistema(new Paciente("María", "González", 45, "22222222", TipoDeCobertura.PLAN3000));
		sanatorio.agregarPacienteAlSistema(new Paciente("Carlos", "Martínez", 50, "33333333", TipoDeCobertura.PLAN3000));
		sanatorio.agregarPacienteAlSistema(new Paciente("Ana", "Rodríguez", 60, "44444444", TipoDeCobertura.PLAN5000));
		sanatorio.agregarPacienteAlSistema(new Paciente("Lucía", "Fernández", 25, "55555555", TipoDeCobertura.SINPLAN));

		// Creamos y agregamos doctores con su especialidad al sanatorio
		sanatorio.agregarDoctor(new Cardiologo("Diego", "Martinez", 46, "26423212", "A1"));
		sanatorio.agregarDoctor(new Kinesiologo("Jorge", "Almiron", 50, "23423212", "A2"));
		sanatorio.agregarDoctor(new Cardiologo("Rodolfo", "Arruabarrena", 52, "22896233", "A3"));
		sanatorio.agregarDoctor(new Pediatra("Carlos", "Bianchi", 76, "16423212", "A4"));
		sanatorio.agregarDoctor(new Cardiologo("Hugo", "Ibarra", 39, "30123458", "A5"));
	}

	@Test
	public void queSePuedaIniciarUnaInternacion()
			throws PacienteNoEncontradoException, HabitacionNoEncontradaException, DoctorNoEncontradoException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate inicioFechaInternacion = LocalDate.parse("12/01/2024", formatter);
		// Para dar origen a la internacion vamos a realizar las busquedas en nuestras
		// listas por el valor que consideremos apropiado

		Paciente paciente = sanatorio.buscarPaciente("11111111");
		Habitacion habitacion = sanatorio.buscarHabitacionPorNumero(11);
		Doctor doctor = sanatorio.buscarDoctorPorMatricula("A1");
		// Una vez encontrado los parametros necesarios creamos la internacion
		Internacion internacion = new Internacion(paciente, doctor, inicioFechaInternacion, habitacion);
		// Verificamos que el paciente aun no se encuentre internado
		assertFalse(sanatorio.pacienteSeEncuentraInternado(paciente.getDni()));
		// Y aca agregamos y verificamos que la internacion haya tenido exito
		assertTrue(sanatorio.agregarInternacion(internacion));
		assertTrue(sanatorio.pacienteSeEncuentraInternado(paciente.getDni()));
	}

	@Test
	public void queSePuedaFinalizarLaInternacion() throws PacienteNoEncontradoException,
			InternacionNoEncontradaException, HabitacionNoEncontradaException, DoctorNoEncontradoException {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate inicioFechaInternacion = LocalDate.parse("12/01/2024", formatter);
		LocalDate fechaActual = LocalDate.parse("20/01/2024", formatter);
		// Para dar origen a la internacion vamos a realizar las busquedas en nuestras
		// listas por el valor que consideremos apropiado

		Paciente paciente = sanatorio.buscarPaciente("11111111");
		Habitacion habitacion = sanatorio.buscarHabitacionPorNumero(11);
		Doctor doctor = sanatorio.buscarDoctorPorMatricula("A1");
		// Una vez encontrado los parametros necesarios creamos la internacion
		Internacion internacion = new Internacion(paciente, doctor, inicioFechaInternacion, habitacion);
		sanatorio.agregarInternacion(internacion);

		// Damos por finalizada la internacion pasando la fecha actual
		assertTrue(sanatorio.finalizarInternacion("11111111", fechaActual));
		assertFalse(sanatorio.pacienteSeEncuentraInternado(paciente.getDni()));

	}

	@Test
	public void queSePuedaSaberElMontoQueDeberaPagarElPacienteAlFinalizarSuEstadia()
			throws PacienteNoEncontradoException, InternacionNoEncontradaException, HabitacionNoEncontradaException,
			DoctorNoEncontradoException {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate inicioFechaInternacion = LocalDate.parse("12/01/2024", formatter);
		LocalDate fechaFinInternacion = LocalDate.parse("20/01/2024", formatter);
		// Para dar origen a la internacion vamos a realizar las busquedas en nuestras
		// listas por el valor que consideremos apropiado

		Paciente paciente = sanatorio.buscarPaciente("11111111");
		Habitacion habitacion = sanatorio.buscarHabitacionPorNumero(11);
		Doctor doctor = sanatorio.buscarDoctorPorMatricula("A1");
		// Una vez encontrado los parametros necesarios creamos la internacion
		Internacion internacion = new Internacion(paciente, doctor, inicioFechaInternacion, habitacion);
		sanatorio.agregarInternacion(internacion);

		assertTrue(sanatorio.pacienteSeEncuentraInternado(paciente.getDni()));
		assertTrue(sanatorio.finalizarInternacion("11111111", fechaFinInternacion));
		assertFalse(sanatorio.pacienteSeEncuentraInternado(paciente.getDni()));
//		centroDeInternacion.agregarPaciente(paciente1); 

		sanatorio.finalizarInternacion("11111111", fechaFinInternacion);
		Double montoEsperado = 56000.00;
		assertEquals(sanatorio.obtenerMontoaPagarDeInternacion("11111111"), montoEsperado);
		assertTrue(sanatorio.habitacionDisponible(11));
	}

	@Test
	public void queSePuedaObtenerListadeInternacionesOrdenadasPorSuFechaDeInicio()
			throws HabitacionNoEncontradaException, PacienteNoEncontradoException, DoctorNoEncontradoException {
		// Para dar origen a la internacion vamos a realizar las busquedas en nuestras
		// listas por el valor que consideremos apropiado
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		LocalDate inicioFechaInternacion1 = LocalDate.parse("12/01/2024", formatter);
		Paciente paciente1 = sanatorio.buscarPaciente("11111111");
		Habitacion habitacion1 = sanatorio.buscarHabitacionPorNumero(11);
		Doctor doctor1 = sanatorio.buscarDoctorPorMatricula("A1");

		LocalDate inicioFechaInternacion2 = LocalDate.parse("15/01/2024", formatter);
		Paciente paciente2 = sanatorio.buscarPaciente("22222222");
		Habitacion habitacion2 = sanatorio.buscarHabitacionPorNumero(21);
		Doctor doctor2 = sanatorio.buscarDoctorPorMatricula("A2");

		LocalDate inicioFechaInternacion3 = LocalDate.parse("01/01/2024", formatter);
		Paciente paciente3 = sanatorio.buscarPaciente("33333333");
		Habitacion habitacion3 = sanatorio.buscarHabitacionPorNumero(31);
		Doctor doctor3 = sanatorio.buscarDoctorPorMatricula("A3");
		
		LocalDate inicioFechaInternacion4 = LocalDate.parse("20/01/2024", formatter);
		Paciente paciente4 = sanatorio.buscarPaciente("44444444");
		Habitacion habitacion4 = sanatorio.buscarHabitacionPorNumero(41);
		Doctor doctor4 = sanatorio.buscarDoctorPorMatricula("A4");
		
		LocalDate inicioFechaInternacion5 = LocalDate.parse("23/01/2024", formatter);
		Paciente paciente5 = sanatorio.buscarPaciente("55555555");
		Habitacion habitacion5 = sanatorio.buscarHabitacionPorNumero(51);
		Doctor doctor5 = sanatorio.buscarDoctorPorMatricula("A5");
		
		
		
		

		// Una vez encontrado los parametros necesarios creamos la internacion
		Internacion internacion1 = new Internacion(paciente1, doctor1, inicioFechaInternacion1, habitacion1);
		Internacion internacion2 = new Internacion(paciente2, doctor2, inicioFechaInternacion2, habitacion2);
		Internacion internacion3 = new Internacion(paciente3, doctor4, inicioFechaInternacion3, habitacion3);
		Internacion internacion4 = new Internacion(paciente4, doctor4, inicioFechaInternacion4, habitacion4);
		Internacion internacion5 = new Internacion(paciente5, doctor5, inicioFechaInternacion5, habitacion5);

		// agrego las internaciones y verifico que se hayan ordenado correctamente
		sanatorio.agregarInternacion(internacion1);
		sanatorio.agregarInternacion(internacion2);
		sanatorio.agregarInternacion(internacion3);
		sanatorio.agregarInternacion(internacion4);
		sanatorio.agregarInternacion(internacion5);
		// por defecto estan ordenadas por nombre las internaciones
		System.out.println(sanatorio.getInternaciones());

		assertEquals(sanatorio.getInternaciones().first(), internacion4);
		// luego de generar un nuevo TreeSet que ordena segun la fecha de inicio
		System.out.println(sanatorio.obtenerInternacionesOrdenadasPorFechaDeInicio());
	}
}
