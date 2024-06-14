package ar.unlam.Interfaz;

import java.util.HashSet;
import java.util.TreeSet;

public class ProbandoCosas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<String> queOnda = new HashSet<String>();
		String hola1 = "Nahuel";
		String hola2 = "Luchito";
		String hola3 = "Luchito";
		String hola4 = "Rodri";

		queOnda.add(hola1);
		queOnda.add(hola2);
		queOnda.add(hola3);
		queOnda.add(hola4);
		
		System.out.println(queOnda);
	}

}
