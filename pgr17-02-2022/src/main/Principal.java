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
import java.util.Collections;

import clases.Animal;
import clases.AuxColor;
import clases.AuxHabitat;
import clases.Cuidador;
import clases.Planta;
import clases.SerVivo;
import util.MyObjectOutputStream;
import util.Util;

public class Principal {

	public static void main(String[] args) throws IOException {
		File fichVivos = new File("seresVivos.dat");
		File fichCuidadores = new File("cuidadores.dat");

		int opc;

		do {
			System.out.println("\n1.-CRUD cuidadores." + "\n2.-CRUD seres vivos."
					+ "\n3.-Listar seres vivos por tamaño (de menor a mayor)." + "\n4.-Listar animales por alimento."
					+ "\n5.-Sacar los datos de las plantas que tengan flores."
					+ "\n6.-Listar de que seres vivos se encarga cada cuidador."
					+ "\n7.-Introduce formación y listar los cuidadores que la tengan."
					+ "\n8.-Listado de cuantos cuidadores hay por cada formación. (Listado especial)"
					+ "\n9.-Listado de hábitats y cuantos seres vivos se encuentran en el."
					+ "\n10.-Listado de los trabajadores por años trabajados." + "\n11.-Listar las plantas por color."
					+ "\n12.-Salir.");
			System.out.println("Elige una opción: ");
			opc = Util.leerInt(1, 12);
			switch (opc) {
			case 1:
				crudCuidadores(fichCuidadores, fichVivos);
				break;
			case 2:
				crudSeresVivos(fichVivos, fichCuidadores);
				break;
			case 3:
				listadoSeresVivosPorTamanno(fichVivos);
				break;
			case 4:
				listadoAnimalesPorAlimento(fichVivos);
				break;
			case 5:
				listadoDePlantasConFlor(fichVivos);
				break;
			case 6:
				listadoNumSeis(fichVivos, fichCuidadores);
				break;
			case 7:
				listarCuidadoresPorFormacion(fichCuidadores);
				break;
			case 8:
				listadoEspecialCuidadoresPorFormacion(fichCuidadores);
				break;
			case 9:
				listadoSerVivoEnHabitat(fichVivos);
				break;
			case 10:
				listadoCuidadoresPorAnnosTrabajados(fichCuidadores);
				break;
			case 11:
				listadoPorColor(fichVivos);
				break;
			case 12:
				break;

			}
		} while (opc != 12);

	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE CRUD

	private static void crudCuidadores(File fichCuidadores, File fichVivos) {

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
				bajaCuidadores(fichCuidadores, fichVivos);
			} else {
				System.out.println("Error, antes debes introducir algun cuidador");
			}
			break;
		case 5:
			break;

		}

	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private static void crudSeresVivos(File fichVivos, File fichCuidadores) {
		if (fichCuidadores.exists()) {
			System.out.println("1. Alta de un ser vivo\n" + "2. Listar todos los seres vivos \n"
					+ "3. Modificar seres vivos (modificar ejemplares en el caso de que se avisten más o mueran, modificar vacunas) \n"
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
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// METODOS DE ALTA

	private static void altaCuidadores(File fichCuidadores) {
		int mas;
		int cuantos;
		if (fichCuidadores.exists()) {
			System.out.println("El fichero existe, los datos se almacenaran al final");
			FileOutputStream fos = null;
			MyObjectOutputStream moos = null;
			cuantos = Util.calculoFichero(fichCuidadores);
			try {

				fos = new FileOutputStream(fichCuidadores, true);
				moos = new MyObjectOutputStream(fos);
				do {
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
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

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
						SerVivo serVivo = null;
						FileOutputStream fos = new FileOutputStream(fichVivos, true);
						MyObjectOutputStream moos = new MyObjectOutputStream(fos);
						System.out.println("Es un animal (1) o una planta (2)?");
						int opcion = Util.leerInt(1, 2);
						if (opcion == 1) {
							serVivo = new Animal();
						} else {
							serVivo = new Planta();
						}
						serVivo.setDatos(fichCuidadores, wCod);
						moos.writeObject(serVivo);
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
						SerVivo serVivo = null;
						System.out.println("¿Es un animal (1) o una planta (2)?");
						int opcion = Util.leerInt(1, 2);
						if (opcion == 1) {
							serVivo = new Animal();
						} else {
							serVivo = new Planta();
						}
						serVivo.setDatos(wCod);
						oos.writeObject(serVivo);
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
			System.out.println("¿Quieres introducir otro ser vivo? (S/N)");
			mas = Util.leerChar('S', 'N');
		} while (mas == 'S');
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// METODOS LISTADO

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
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

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
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// METODOS MODIFICAR

	private static void modificarCuidadores(File fichCuidadores) {
		System.out.println("Introduce el codigo del trabajador que quieres modificar");
		int codUsuario = Util.leerInt();
		boolean modificado = false;

		// Volcado a arraylist
		ArrayList<Cuidador> cuidadores = volcarCuidador(fichCuidadores);

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
			volcarListaCuidadorAFichero(fichCuidadores, cuidadores);
			System.out.println("Modificacion realizada con exito");
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private static void modificarSeresVivos(File fichVivos, File fichCuidadores) {
		System.out.println("Introduce el nombre cientifico del animal que quieres modificar");
		String serUsuario = Util.introducirCadena();
		boolean modificado = false;

		// Volcado a arraylist
		ArrayList<SerVivo> seresVivos = volcarSerVivo(fichVivos);

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
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// METODOS ELIMINAR

	private static void bajaCuidadores(File fichCuidadores, File fichVivos) {
		System.out.println("Introduce el codigo del trabajador que quieres modificar");
		int codUsuario = Util.leerInt();
		boolean modificado = false;

		// Volcado a arraylist
		ArrayList<Cuidador> cuidadores = volcarCuidador(fichCuidadores);

		// Modificacion del boolean Activo
		for (int i = 0; i < cuidadores.size(); i++) {
			if (cuidadores.get(i).getCodCuidador() == codUsuario) {
				System.out.println("Los datos de este cuidador son:");
				cuidadores.get(i).getDatos();
				System.out.println("Deseas eliminarlo?(S/N)");
				char opc = Util.leerChar('S', 'N');
				if (opc == 'S') {
					ArrayList<SerVivo> seresVivos = volcarSerVivo(fichVivos);
					for (SerVivo ser : seresVivos) {
						/*
						 * for (int cu : ser.getCodCuidador()) { if (cu == codUsuario) {
						 * ser.borrarCuidador(codUsuario);
						 * 
						 * } }
						 */
						ser.borrarCuidador(codUsuario);
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
			volcarListaCuidadorAFichero(fichCuidadores, cuidadores);
			System.out.println("Modificacion realizada con exito");
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private static void eliminarSeresVivos(File fichVivos) {
		System.out.println("Introduce el nombre cientifico del ser vivo que quieres eliminar");
		String serUsuario = Util.introducirCadena();
		ArrayList<SerVivo> seresVivos = new ArrayList<SerVivo>();
		seresVivos = volcarSerVivo(fichVivos);
		for (int i = 0; i < seresVivos.size(); i++) {
			if (serUsuario.equalsIgnoreCase(seresVivos.get(i).getNombreCientifico())) {
				System.out.println("Estos son los datos del ser vivo, �seguro que quieres eliminarlo? (1=si/2=no)");
				seresVivos.get(i).getDatos();
				int opc = Util.leerInt(1, 2);
				if (opc == 1) {
					seresVivos.remove(i);
					System.out.println("Aniquilado");
					volcarListaSerVivoAFichero(fichVivos, seresVivos);
				} else {
					System.out.println("Entendible, pase buen dia");
				}
			}
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// METODOS PARA VOLCAR FICHEROS A ARRAYLIST

	private static ArrayList<Cuidador> volcarCuidador(File prueba) {
		int cont = Util.calculoFichero(prueba);
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
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private static ArrayList<SerVivo> volcarSerVivo(File prueba) {
		int cont = Util.calculoFichero(prueba);
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
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// METODOS PARA VOLCAR ARRAYLIST A FICHEROS

	private static void volcarListaCuidadorAFichero(File fichCuidadores, ArrayList<Cuidador> cuidadores) {
		try {
			int cont = Util.calculoFichero(fichCuidadores);
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
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

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
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// METODO PARA BUSQUEDA DE UN CUIDADOR EN CONCRETO USANDO SU CODIGO

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

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------
	// METODO PARA MODIFICAR LOS SERES VIVOS(AUXILIAR)

	private static SerVivo inModificarSerVivo(SerVivo serVivoModificar, File fichCuidadores) {
		boolean salir = false;
		do {
			System.out.println("�Que desea modificar? \n" + " 1) Ejemplares \n" + " 2) Cuidadores a su cargo \n");
			if (serVivoModificar instanceof Animal) {
				System.out.print("3) Desplazamiento");
			} else {
				System.out.print("3) Color");
			}
			System.out.println("4) Salir");
			int opcion = Util.leerInt(1, 4);

			switch (opcion) {

			case 1:
				System.out.println("�Cuantos ejemplares hay?");
				serVivoModificar.setEjemplares(Util.leerInt());
				break;

			case 2:
				System.out.println("Estos son los cuidadores a su cargo");
				ArrayList<Integer> codigos = (ArrayList) serVivoModificar.getCodCuidador();
				for (int e = 0; e < codigos.size(); e++) {
					System.out.println(codigos.get(e));
				}
				System.out.println("�Quieres eliminar (1) o añadir (2) un codigo?");
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
					System.out.println("Introduce el codigo que deseas añadir");
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
					System.out.println("�Que tipo de desplzamiento tiene?");
					((Animal) serVivoModificar).setDesplazamiento(Util.introducirCadena());
					System.out.println("El desplazamiento se ha modificado correctamente");
				} else {
					System.out.println("�Que color tiene?");
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
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private static void listadoDePlantasConFlor(File fichVivos) throws IOException {
		ObjectInputStream ois = null;
		if (fichVivos.exists()) {
			try {
				ois = new ObjectInputStream(new FileInputStream(fichVivos));
				int cuantos = Util.calculoFichero(fichVivos);
				System.out.println("El nombre vulgar de las plantas con flores es la siguiente");
				for (int i = 0; i < cuantos; i++) {
					Object aux = ois.readObject();
					if (aux instanceof Planta) {
						if (((Planta) aux).isFlores() == true) {
							String nombre = ((Planta) aux).getNombreVulgar();
							System.out.println(nombre);
						}
					}
				}

			} catch (EOFException e1) {
				System.out.println("el fichero ha acabado");
				ois.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else {
			System.out.println("No existen datos en el fichero.");
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private static void listadoAnimalesPorAlimento(File fichVivos) throws IOException {
		ObjectInputStream ois = null;
		if (fichVivos.exists()) {
			try {
				ois = new ObjectInputStream(new FileInputStream(fichVivos));
				int cuantos = Util.calculoFichero(fichVivos);
				for (int i = 0; i < cuantos; i++) {
					Object aux = ois.readObject();
					if (aux instanceof Animal) {
						if (((Animal) aux).getAlimento().equalsIgnoreCase("Carnivoro")) {
							String nombre = ((Animal) aux).getNombreVulgar();
							System.out.println("Animal Carnivoro:");
							System.out.println(nombre);
						}
						if (((Animal) aux).getAlimento().equalsIgnoreCase("Herbivoro")) {
							String nombre = ((Animal) aux).getNombreVulgar();
							System.out.println("Animal Herbivoro:");
							System.out.println(nombre);
						}
						if (((Animal) aux).getAlimento().equalsIgnoreCase("Omnivoro")) {
							String nombre = ((Animal) aux).getNombreVulgar();
							System.out.println("Animal Omnivoro:");
							System.out.println(nombre);
						}
					}
				}
			} catch (EOFException e1) {
				System.out.println("el fichero ha acabado");
				ois.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else {
			System.out.println("No existen datos en el fichero.");
		}
	}
	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private static void listadoSeresVivosPorTamanno(File fichVivos) {
		ArrayList<SerVivo> serVivos = volcarSerVivo(fichVivos);
		serVivos.sort((o1, o2) -> Float.compare(o1.getTamannoMedio(), o2.getTamannoMedio()));
		for (int i = 0; i < serVivos.size(); i++) {
			serVivos.get(i).getDatos();
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private static void listadoNumSeis(File fichVivos, File fichCuidadores) {
		int contCuid = 1, contSer = 1;
		ArrayList<SerVivo> seresVivos = volcarSerVivo(fichVivos);
		ArrayList<Cuidador> cuidadores = volcarCuidador(fichCuidadores);

		for (Cuidador c : cuidadores) {
			System.out.println("--------------*Cuidador " + contCuid + "*----------------");
			contCuid++;
			c.getDatos();
			for (SerVivo s : seresVivos) {
				boolean encontrado = s.encontrarCuidador(c.getCodCuidador());
				if (encontrado) {
					System.out.println("*SerVivo " + contSer + "*");
					contSer++;
					s.getDatos();
				}
			}
			System.out.println("-----------------------------------------");
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------
	private static void listarCuidadoresPorFormacion(File fichCuidadores) {
		System.out.println("Introduce la formacion");
		ArrayList<Cuidador> cuidadores = volcarCuidador(fichCuidadores);
		String formUsuario = Util.introducirCadena();
		for (int i = 0; i < cuidadores.size(); i++) {
			if (cuidadores.get(i).getFormacion().contains(formUsuario)) {
				cuidadores.get(i).getDatos();
			}
		}

	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private static void listadoEspecialCuidadoresPorFormacion(File fichCuidadores) {
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private static void listadoSerVivoEnHabitat(File fichVivos) {
		ArrayList <SerVivo> serVivos = volcarSerVivo(fichVivos);
		ArrayList <AuxHabitat> auxHabitat = new ArrayList<AuxHabitat>();
		boolean existe;
		
		for (int i=0; i<serVivos.size();i++) {
			existe = false;
			for (int j=0; j<auxHabitat.size();j++) {
				if (serVivos.get(i).getHabitat().equalsIgnoreCase(auxHabitat.get(j).getHabitat())) {
					auxHabitat.get(j).setCuantos(auxHabitat.get(j).getCuantos()+1);
					j=auxHabitat.size();
					existe = true;
				}
			}
			if (!existe) {
				AuxHabitat a = new AuxHabitat(serVivos.get(i).getHabitat(), 1);
				auxHabitat.add(a);
			}
		}
		Collections.sort(auxHabitat);
		System.out.println(" HABITAT | CUANTOS ");
		for (AuxHabitat e:auxHabitat){
			System.out.println(e.getHabitat()+" | "+e.getCuantos());
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private static void listadoCuidadoresPorAnnosTrabajados(File fichCuidadores) {
		ArrayList<Cuidador> cuidadores = volcarCuidador(fichCuidadores);
		for (int i = 0; i < cuidadores.size(); i++) {
			System.out.println(cuidadores.get(i).getNombre() + " " + cuidadores.get(i).getApellidos() + " tiene "
					+ cuidadores.get(i).getCalculoAnnosTrabajados() + " años trabajados");
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private static void listadoPorColor(File fichVivos) {
		ArrayList<SerVivo> seresVivos = volcarSerVivo(fichVivos);
		ArrayList<AuxColor> auxColor = new ArrayList<AuxColor>();
		boolean esta;

		for (int i = 0; i < seresVivos.size(); i++) {
			esta = false;
			if (seresVivos.get(i) instanceof Planta) {
				for (int j = 0; j < auxColor.size(); j++) {
					if (auxColor.get(j).getColor().equalsIgnoreCase(((Planta) seresVivos.get(i)).getColor())) {
						esta = true;
						auxColor.get(j).setVeces(auxColor.get(j).getVeces() + 1);
						j = auxColor.size();
					}
				}
				if (!esta) {
					AuxColor auxiliarColor = new AuxColor(((Planta) seresVivos.get(i)).getColor(), 1);
					auxColor.add(auxiliarColor);
				}
			}
		}
		Collections.sort(auxColor);
		System.out.println("Color | Veces");
		for (AuxColor a : auxColor) {
			System.out.println(a.getColor() + " | " + a.getVeces());
		}
	}

	// FIN DE LOS METODOS
}