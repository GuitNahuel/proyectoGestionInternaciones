package ar.unlam.dominio;

import java.util.Comparator;

import ar.unlam.dominio.Paciente.Paciente;

public class NombreComparator implements Comparator<Paciente>{

	@Override
	public int compare(Paciente pac1, Paciente pac2) {
		// TODO Auto-generated method stub
		
		return pac1.getNombre().compareTo(pac2.getNombre());
	}

	
}
