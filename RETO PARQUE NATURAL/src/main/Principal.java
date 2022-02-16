package main;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.OrientationRequested;

import clases.Animal;
import clases.Cuidador;
import clases.Planta;
import clases.SerVivo;
import util.MyObjectOutputStream;
import util.Util;

public class Principal {

	public static void main(String[] args) {
		File fichVivos = new File("seresVivos.dat");
		File fichCuidadores = new File("cuidadores.dat");

		int opc;

		do {
			System.out.println("\n1.-CRUD cuidadores." + "\n2.-CRUD seres vivos."
					+ "\n3.-Listar seres vivos por tamaÃ±o." + "\n4.-Listar animales por alimento."
					+ "\n5.-Sacar los datos de las plantas que tengan flores."
					+ "\n6.-Listar de que seres vivos se encarga cada cuidador."
					+ "\n7.-Introduce formaciÃ³n y listar los cuidadores que la tengan."
					+ "\n8.-Listado de cuantos cuidadores hay por cada formaciÃ³n."
					+ "\n9.-Listado de hÃ¡bitats y cuantos seres vivos se encuentran en el."
					+ "\n10.-Listado de los trabajadores por aÃ±os trabajados." + "\n11.-Listar las plantas por color."
					+ "\n12.-Salir.");
			System.out.println("Elige una opciÃ³n: ");
			opc = Util.leerInt(1, 12);
			switch (opc) {
			case 1:
				crudCuidadores(fichCuidadores);
				break;
			case 2:
				crudSeresVivos(fichVivos, fichCuidadores);
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

	private static void crudCuidadores(File fichCuidadores) {

		System.out.println("1. Alta de un cuidadores\n" + "2. Listar todos los cuidadores \n"
				+ "3. Modificar cuidadores \n" + "4. Eliminar cuidadores\n" + "5. Salir");
		int opc = Util.leerInt(1, 5);
		switch (opc) {
		case 1:
			altaCuidadores(fichCuidadores);
			break;
		case 2:
			if (fichCuidadores.exists()) {
				listarCuidadores(fichCuidadores);
			} else {
				System.out.println("Error, antes debes introducir algun cuidador");
			}
			break;
		case 3:
			if (fichCuidadores.exists()) {
				modificarCuidadores(fichCuidadores);
			} else {
				System.out.println("Error, antes debes introducir algun cuidador");
			}
			break;
		case 4:
			if (fichCuidadores.exists()) {
				bajaCuidadores(fichCuidadores);
			} else {
				System.out.println("Error, antes debes introducir algun cuidador");
			}
			break;
		case 5:
			break;

		}

	}
	
	private static void listadoNumSeis(File fichVivos, File fichCuidadores) {
		int cuantos=Util.calculoFichero(fichCuidadores);
		int cont=Util.calculoFichero(fichVivos);
		ArrayList<SerVivo> seresVivos = volcarSerVivo(fichVivos, cont);
		ArrayList<Cuidador> cuidadores = volcarCuidador(fichCuidadores, cuantos);
		
		for(Cuidador c : cuidadores) {
			System.out.println("Los datos del Cuidador");
			c.getDatos();
			for(SerVivo s : seresVivos) {
				boolean encontrado = s.encontrarCuidador(c.getCodCuidador());
				if(encontrado) {
					s.getDatos();
				}
			}
			System.out.println("---------------------");
		}
	}

	private static Cuidador busquedaCuidador(File fichCuidadores, int wCod) {
		boolean encontrado = false;
		Cuidador cuidador = null;
		try {
			FileInputStream fis = new FileInputStream(fichCuidadores);
			ObjectInputStream ois = new ObjectInputStream(fis);
			int cuantos = Util.calculoFichero(fichCuidadores);

			for (int i = 0; i < cuantos; i++) {
				cuidador = (Cuidador) ois.readObject();
				if (cuidador.getCodCuidador() == wCod) {
					i = cuantos;
					encontrado = true;
				}
			}
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (!encontrado) {
			cuidador = null;
		}
		return cuidador;
	}

	private static void bajaCuidadores(File fichCuidadores, File fichVivos) {
		System.out.println("Introduce el codigo del trabajador que quieres modificar");
		int codUsuario = Util.leerInt();
		int cont = Util.calculoFichero(fichCuidadores);
		boolean modificado = false;

		// Volcado a arraylist
		ArrayList<Cuidador> cuidadores = volcarCuidador(fichCuidadores, cont);

		// Modificacion del boolean Activo
		for (int i = 0; i < cuidadores.size(); i++) {
			if (cuidadores.get(i).getCodCuidador() == codUsuario) {
				System.out.println("Los datos de este cuidador son:");
				cuidadores.get(i).getDatos();
				System.out.println("Deseas eliminarlo?(S/N)");
				char opc = Util.leerChar();
				if (opc == 'S') {
					ArrayList <SerVivo> seresVivos = volcarSerVivo(fichVivos, cont);
					for (SerVivo ser: seresVivos) {
						for (int cu:ser.getCodCuidador()) {
							if (cu==codUsuario) {
								ser.borrarCuidador(codUsuario);
							}
						}
					
					}
					cuidadores.remove(i);
					modificado = true;
				} else {
					i = cuidadores.size();
				}
			} else {
				System.out.println("Error, no se han encontrado cuidadores con ese codigo");
			}
		}

		// Se han realizado cambios, se vuelcan los datos del arraylist al fichero
		if (modificado) {
			volcarListaCuidadorAFichero(fichCuidadores, cont, cuidadores);
			System.out.println("Modificacion realizada con exito");
		}
	}

	private static void modificarCuidadores(File fichCuidadores) {
		System.out.println("Introduce el codigo del trabajador que quieres modificar");
		int codUsuario = Util.leerInt();
		int cont = Util.calculoFichero(fichCuidadores);
		boolean modificado = false;

		// Volcado a arraylist
		ArrayList<Cuidador> cuidadores = volcarCuidador(fichCuidadores, cont);

		// Modificacion del cuidador
		for (int i = 0; i < cuidadores.size(); i++) {
			if (cuidadores.get(i).getCodCuidador() == codUsuario) {
				System.out.println("Los datos de este cuidador son:");
				cuidadores.get(i).getDatos();
				System.out.println("Desea modificarlos?(S/N)");
				char opc = Util.leerChar('S', 'N');
				if (opc == 'S') {
					modificado = true;
					boolean salir = false;
					do {
						System.out.println("Que desea modificar:");
						System.out.println("1) Nombre\n" + "2) Apellido\n" + "3) Fecha de Nacimiento\n"
								+ "4) Fecha de Alta\n" + "5) Formacion\n" + "6) Salir\n");
						int opcion = Util.leerInt();
						switch (opcion) {
						case 1:
							cuidadores.get(i).setNombre(Util.introducirCadena());
							break;
						case 2:
							cuidadores.get(i).setApellidos(Util.introducirCadena());
							break;
						case 3:
							cuidadores.get(i).setFechaNac(Util.leerFechaDMA());
							break;
						case 4:
							cuidadores.get(i).setFechaAlta(Util.leerFechaDMA());
							break;
						case 5:
							cuidadores.get(i).setFormacion(Util.introducirCadena());
							break;
						case 6:
							salir = true;
							break;
						}
					} while (!salir);
				} else {
					i = cuidadores.size();
				}
			} else {
				System.out.println("Error, no se han encontrado cuidadores con ese codigo");
			}
		}

		// Se han realizado cambios, se vuelcan los datos del arraylist al fichero
		if (modificado) {
			volcarListaCuidadorAFichero(fichCuidadores, cont, cuidadores);
			System.out.println("Modificacion realizada con exito");
		}
	}

	private static void listarCuidadores(File fichCuidadores) {
		try {
			FileInputStream fis = new FileInputStream(fichCuidadores);
			ObjectInputStream ois = new ObjectInputStream(fis);
			int cuantos = Util.calculoFichero(fichCuidadores);
			for (int i = 0; i < cuantos; i++) {
				Cuidador aux = (Cuidador) ois.readObject();
				aux.getDatos();
			}
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found, fatal error, no encontrado el fichero");
		} catch (EOFException e) {
			System.out.println("FIN DE FICHERO");
		} catch (IOException e2) {
			System.out.println("-------");
		} catch (ClassNotFoundException e) {
			System.out.println("Error en la lectura de datos");
		}
	}

	private static void altaCuidadores(File fichCuidadores) {
		int mas;
		int cuantos;
		if (fichCuidadores.exists()) {
			System.out.println("El fichero existe, los datos se almacenaran al final");
			FileOutputStream fos = null;
			MyObjectOutputStream moos = null;
			try {
				fos = new FileOutputStream(fichCuidadores, true);
				moos = new MyObjectOutputStream(fos);
				cuantos = Util.calculoFichero(fichCuidadores);
				do {
					cuantos = Util.calculoFichero(fichCuidadores);
					cuantos++;
					Cuidador cuidador = new Cuidador();
					cuidador.setDatos(cuantos);
					moos.writeObject(cuidador);
					System.out.println("Quieres introducir mas Cuidadores?(1=Si/2=No)");
					mas = Util.leerInt(1, 2);
				} while (mas == 1);
				moos.close();
				fos.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Creando nuevo fichero");
			FileOutputStream fos = null;
			ObjectOutputStream oos = null;
			try {
				fos = new FileOutputStream(fichCuidadores);
				oos = new ObjectOutputStream(fos);
				cuantos = 0;
				do {
					cuantos++;
					Cuidador cuidador = new Cuidador();
					cuidador.setDatos(cuantos);
					oos.writeObject(cuidador);
					System.out.println("Quieres introducir mas Cuidadores?(1=Si/2=No)");
					mas = Util.leerInt(1, 2);
				} while (mas == 1);
				oos.close();
				fos.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private static void crudSeresVivos(File fichVivos, File fichCuidadores) {
		if (fichCuidadores.exists()) {
			System.out.println("1. Alta de un ser vivo\n" + "2. Listar todos los seres vivos \n"
					+ "3. Modificar seres vivos (modificar ejemplares en el caso de que se avisten mÃ¡s o mueran, modificar vacunas) \n"
					+ "4. Eliminar seres vivos\n" + "5. Salir");
			int opc = Util.leerInt(1, 5);
			switch (opc) {
			case 1:
				altaSerVivo(fichVivos, fichCuidadores);
				break;
			case 2:
				if (fichVivos.exists()) {
					listarSeresVivos(fichVivos);
				} else {
					System.out.println("Primero tienes que introducir seres vivos.");
				}
				break;
			case 3:
				if (fichVivos.exists()) {
					modificarSeresVivos(fichVivos, fichCuidadores);
				} else {
					System.out.println("Primero tienes que introducir seres vivos.");
				}
				break;
			case 4:
				if (fichVivos.exists()) {
					eliminarSeresVivos(fichVivos);
				} else {
					System.out.println("Primero tienes que introducir seres vivos.");
				}
				break;
			case 5:
				break;

			}
		} else {
			System.out.println("Error, primero debes introducir cuidadores");
		}
	}

	private static void eliminarSeresVivos(File fichVivos) {
		System.out.println("Introduce el nombre cientifico del ser vivo que quieres eliminar");
		String serUsuario = Util.introducirCadena();
		ArrayList<SerVivo> seresVivos = new ArrayList<SerVivo>();
		int cuantos = Util.calculoFichero(fichVivos);
		seresVivos = volcarSerVivo(fichVivos, cuantos);
		for (int i=0; i<seresVivos.size();i++) {
			if (serUsuario.equalsIgnoreCase(seresVivos.get(i).getNombreCientifico())) {
				System.out.println("Estos son los datos del ser vivo, ¿seguro que quieres eliminarlo? (1=si/2=no)");
				seresVivos.get(i).getDatos();
				int opc = Util.leerInt(1, 2);
				if (opc==1) {
					seresVivos.remove(i);
					System.out.println("Aniquilado");
					volcarListaSerVivoAFichero(fichVivos, seresVivos);
				}else {
					System.out.println("Entendible, pase buen dia");
				}
			}
		}
	}

	private static ArrayList<SerVivo> volcarSerVivo(File prueba, int cont) {
		ArrayList<SerVivo> lista = new ArrayList<SerVivo>(cont);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(prueba);
			ois = new ObjectInputStream(fis);
			for (int i = 0; i < cont; i++) {
				lista.add(i, (SerVivo) ois.readObject());
			}
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println(" ");
		} catch (ClassNotFoundException e) {
			System.out.println("Error en la lectura de datos.");
		}
		return lista;
	}

	private static ArrayList<Cuidador> volcarCuidador(File prueba, int cont) {
		ArrayList<Cuidador> lista = new ArrayList<Cuidador>(cont);
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(prueba);
			ois = new ObjectInputStream(fis);
			for (int i = 0; i < cont; i++) {
				lista.add(i, (Cuidador) ois.readObject());
			}
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println(" ");
		} catch (ClassNotFoundException e) {
			System.out.println("Error en la lectura de datos.");
		}
		return lista;
	}

	// Metodo para Modificar los seres vivos(General)
	private static SerVivo inModificarSerVivo(SerVivo serVivoModificar, File fichCuidadores) {
		boolean salir = false;
		do {
			System.out.println("¿Que desea modificar? \n" + " 1) Ejemplares \n" + " 2) Cuidadores a su cargo \n");
			if (serVivoModificar instanceof Animal) {
				System.out.print("3) Desplazamiento");
			} else {
				System.out.print("3) Color");
			}
			System.out.println("4) Salir");
			int opcion = Util.leerInt(1, 4);

			switch (opcion) {

			case 1:
				System.out.println("¿Cuantos ejemplares hay?");
				serVivoModificar.setEjemplares(Util.leerInt());
				break;

			case 2:
				System.out.println("Estos son los cuidadores a su cargo");
				ArrayList<Integer> codigos = (ArrayList) serVivoModificar.getCodCuidador();
				for (int e = 0; e < codigos.size(); e++) {
					System.out.println(codigos.get(e));
				}
				System.out.println("¿Quieres eliminar (1) o aÃ±adir (2) un codigo?");
				int respuesta = Util.leerInt();
				int codUsuario;

				if (respuesta == 1) {
					System.out.println("Introduce el codigo que deseas eliminar");
					codUsuario = Util.leerInt();
					Cuidador cuidador = busquedaCuidador(fichCuidadores, codUsuario);
					if (cuidador != null) {
						for (int e = 0; e < codigos.size(); e++) {
							if (codigos.get(e).equals(codUsuario))
								codigos.remove(e);
						}
						System.out.println("El codigo se ha eliminado con exito");
					} else
						System.out.println("Error, ese codigo no existe");
				} else {
					System.out.println("Introduce el codigo que deseas aÃ±adir");
					codUsuario = Util.leerInt();
					Cuidador cuidador = busquedaCuidador(fichCuidadores, codUsuario);
					if (cuidador != null) {
						codigos.add(codUsuario);
						serVivoModificar.setCodCuidador(codigos);
					} else {
						System.out.println("Ese cuidador no existe");
					}
				}
				break;

			case 3:
				if (serVivoModificar instanceof Animal) {
					System.out.println("¿Que tipo de desplzamiento tiene?");
					((Animal) serVivoModificar).setDesplazamiento(Util.introducirCadena());
					System.out.println("El desplazamiento se ha modificado correctamente");
				} else {
					System.out.println("¿Que color tiene?");
					((Planta) serVivoModificar).setColor(Util.introducirCadena());
					System.out.println("El color se ha modificado correctamente");
				}
				break;

			case 4:
				salir = true;
			}
		} while (!salir);
		return serVivoModificar;
	}

	private static void modificarSeresVivos(File fichVivos, File fichCuidadores) {
		System.out.println("Introduce el nombre cientifico del animal que quieres modificar");
		String serUsuario = Util.introducirCadena();
		int cont = Util.calculoFichero(fichVivos);
		boolean modificado = false;

		// Volcado a arraylist
		ArrayList<SerVivo> seresVivos = volcarSerVivo(fichVivos, cont);

		// Modificacion del ser vivo
		for (int i = 0; i < seresVivos.size(); i++) {
			if (seresVivos.get(i).getNombreCientifico().equalsIgnoreCase(serUsuario)) {
				System.out.println("Los datos de este ser vivo son:");
				// Modificacion si es un animal
				seresVivos.get(i).getDatos();
				System.out.println("Desea modificar los datos? (S/N)");
				char opc = Util.leerChar('S', 'N');
				if (opc == 'S') {

					SerVivo serVivoModificar = seresVivos.get(i);
					serVivoModificar = inModificarSerVivo(serVivoModificar, fichCuidadores);
					seresVivos.remove(i);
					seresVivos.add(serVivoModificar);
					volcarListaSerVivoAFichero(fichVivos, seresVivos);
					System.out.println("Has modificado los datos");
					modificado = true;
				} else {
					System.out.println("Regresando al menu");
				}
				i = seresVivos.size();
			}
		}
		if (modificado) {
			volcarListaSerVivoAFichero(fichVivos, seresVivos);
		}

	}

	private static void volcarListaSerVivoAFichero(File fichVivos, ArrayList<SerVivo> seresVivos) {
		try {
			FileOutputStream fos = new FileOutputStream(fichVivos);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (int i = 0; i < seresVivos.size(); i++) {
				oos.writeObject(seresVivos.get(i));
			}
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println(" ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void volcarListaCuidadorAFichero(File fichCuidadores, int cont, ArrayList<Cuidador> cuidadores) {
		try {
			FileOutputStream fos = new FileOutputStream(fichCuidadores);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (int i = 0; i < cont - 1; i++) {
				oos.writeObject(cuidadores.get(i));
			}
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
		} catch (IOException e) {
			System.out.println(" ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void listarSeresVivos(File fichVivos) {

		SerVivo serVivo;
		try {
			FileInputStream fis = new FileInputStream(fichVivos);
			ObjectInputStream ois = new ObjectInputStream(fis);
			int cuantos = Util.calculoFichero(fichVivos);
			for (int i = 0; i < cuantos; i++) {
				// Compara si es un animal o si es una planta
				serVivo = (SerVivo) ois.readObject();
				serVivo.getDatos();
				/*
				 * if (serVivo instanceof Animal) {
				 * 
				 * ((Animal)serVivo).getDatos(); } else {
				 * 
				 * ((Planta)serVivo).getDatos(); }
				 */
			}
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found, fatal error, no encontrado el fichero");
		} catch (EOFException e) {
			System.out.println("FIN DE FICHERO");
		} catch (IOException e2) {
			System.out.println("-------");
		} catch (ClassNotFoundException e) {
			System.out.println("Error en la lectura de datos");
		}
	}

	private static void altaSerVivo(File fichVivos, File fichCuidadores) {
		char mas;
		do {
			System.out.println("Introduce el codigo de cuidador");
			int wCod = Util.leerInt();
			Cuidador cuidador = busquedaCuidador(fichCuidadores, wCod);

			if (cuidador != null) {
				// Comprobacion de que existe un fichero de Seres Vivos
				if (fichVivos.exists()) {
					try {
						FileOutputStream fos = new FileOutputStream(fichVivos, true);
						MyObjectOutputStream moos = new MyObjectOutputStream(fos);
						System.out.println("Â¿Es un animal (1) o una planta (2)?");
						int opcion = Util.leerInt(1, 2);
						if (opcion == 1) {
							Animal animal = new Animal();
							animal.setDatos(wCod);
							moos.writeObject(animal);
						} else {
							Planta planta = new Planta();
							planta.setDatos(wCod);
							moos.writeObject(planta);
						}
						moos.close();
						fos.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}

				} else {
					try {
						FileOutputStream fos = new FileOutputStream(fichVivos);
						ObjectOutputStream oos = new ObjectOutputStream(fos);
						System.out.println("Â¿Es un animal (1) o una planta (2)?");
						int opcion = Util.leerInt(1, 2);
						if (opcion == 1) {
							Animal animal = new Animal();
							animal.setDatos(wCod);
							oos.writeObject(animal);
						} else {
							Planta planta = new Planta();
							planta.setDatos(wCod);
							oos.writeObject(planta);
						}
						oos.close();
						fos.close();
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else {
				System.out.println("El codigo del cuidador no se ha encontrado.");
			}
			System.out.println("Â¿Quieres introducir otro ser vivo? (S/N)");
			mas = Util.leerChar('S', 'N');
		} while (mas == 'S');
	}

}
