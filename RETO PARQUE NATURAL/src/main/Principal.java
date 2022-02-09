package main;

import java.io.File;

import util.Util;

public class Principal {

	public static void main(String[] args) {
		File fichVivos = new File("seresVivos.dat");
		File fichCuidadores = new File("cuidadores.dat");
		
		int opc;
		
		do {
			System.out.println("\n1.-CRUD cuidadores." + "\n2.-CRUD seres vivos."
					+ "\n3.-Listar seres vivos por tamaño." + "\n4.-Listar animales por alimento."
					+ "\n5.-Sacar los datos de las plantas que tengan flores."
					+ "\n6.-Listar de que seres vivos se encarga cada cuidador."
					+ "\n7.-Introduce formación y listar los cuidadores que la tengan."
					+ "\n8.-Listado de cuantos cuidadores hay por cada formación."
					+ "\n9.-Listado de hábitats y cuantos seres vivos se encuentran en el."
					+ "\n10.-Listado de los trabajadores por años trabajados." + "\n11.-Listar las plantas por color."
					+ "\n12.-Salir.");
			System.out.println("Elige una opción: ");
			opc = Util.leerInt(1, 12);
			switch(opc) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			case 11:
				break;
			case 12:
				break;
				
			}
		} while (opc != 12);

	}

}
