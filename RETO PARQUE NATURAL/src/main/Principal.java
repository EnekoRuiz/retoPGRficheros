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

import clases.Animal;
import clases.Cuidador;
import clases.Planta;
import util.MyObjectOutputStream;
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
				+ "3. Modificar cuidadores \n" + "4. Eliminar cuidadores");
		int opc = Util.leerInt(1, 4);
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
			break;

		}

	}

	private static Cuidador busquedaCuidador(File fichCuidadores, int wCod) {
		Cuidador cuidador = null;
		boolean pasarNull = true;
		try {
			FileInputStream fis = new FileInputStream(fichCuidadores);
			ObjectInputStream ois = new ObjectInputStream(fis);
			int cuantos = Util.calculoFichero(fichCuidadores);

			for (int i = 0; i < cuantos; i++) {
				cuidador = (Cuidador) ois.readObject();
				if (cuidador.getCodCuidador() == wCod) {
					i = cuantos;
					pasarNull = false;
				}
			}
			ois.close();
			fis.close();
			if (pasarNull) {
				cuidador = null;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cuidador;
	}

	private static void modificarCuidadores(File fichCuidadores) {
		System.out.println("Introduce el codigo del trabajador que quieres modificar");
		int codUsuario = Util.leerInt();
		int cont = Util.calculoFichero(fichCuidadores);
		Cuidador c = busquedaCuidador(fichCuidadores, codUsuario);

		if (c != null) {
			ArrayList<Cuidador> cuidadores = new ArrayList<Cuidador>(cont);
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try {
				fis = new FileInputStream(fichCuidadores);
				ois = new ObjectInputStream(fis);
				for (int i = 0; i < cont; i++) {
					cuidadores.add(i, (Cuidador) ois.readObject());
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
		}
		else {
			System.out.println("Error, no existe ningun cuidador con ese codigo");
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
			try {
				FileOutputStream fos = new FileOutputStream(fichCuidadores, true);
				MyObjectOutputStream moos = new MyObjectOutputStream(fos);
				cuantos = Util.calculoFichero(fichCuidadores);
				do {
					cuantos++;
					Cuidador cuidador = new Cuidador();
					cuidador.setDatos(cuantos);
					moos.writeObject(cuidador);
					System.out.println("Quieres introducir mas Cuidadores?(1=Si/2=No)");
					mas = Util.leerInt(1, 2);
				} while (mas == 1);
				fos.close();
				moos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("Creando nuevo fichero");
			try {
				FileOutputStream fos = new FileOutputStream(fichCuidadores);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				cuantos = 0;
				do {
					cuantos++;
					Cuidador cuidador = new Cuidador();
					cuidador.setDatos(cuantos);
					oos.writeObject(cuidador);
					System.out.println("Quieres introducir mas Cuidadores?(1=Si/2=No)");
					mas = Util.leerInt(1, 2);
				} while (mas == 1);
				fos.close();
				oos.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private static void crudSeresVivos(File fichVivos, File fichCuidadores) {
		if (fichCuidadores.exists()) {
			System.out.println("1. Alta de un ser vivo\n" + "2. Listar todos los seres vivos \n"
					+ "3. Modificar seres vivos (modificar ejemplares en el caso de que se avisten más o mueran, modificar vacunas) \n"
					+ "4. Eliminar seres vivos");
			int opc = Util.leerInt(1, 4);
			switch (opc) {
			case 1:
				altaSerVivo(fichVivos, fichCuidadores);
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;

			}
		} else {
			System.out.println("Error, primero debes introducir cuidadores");
		}
	}

	private static void altaSerVivo(File fichVivos, File fichCuidadores) {
		boolean existeCuidador = false;
		int cuantos = Util.calculoFichero(fichCuidadores);
		List<Cuidador> cuidadores = new ArrayList<Cuidador>(cuantos);
		char mas;

		do {
			System.out.println("Introduce el codigo de cuidador");
			int wCod = Util.leerInt();

			// Volcado del fichero a ArrayList
			try {
				FileInputStream fis = new FileInputStream(fichCuidadores);
				ObjectInputStream ois = new ObjectInputStream(fis);
				cuidadores.clear();
				for (int i = 0; i < cuantos; i++) {
					cuidadores.add((Cuidador) ois.readObject());
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

			// Comprobacion de que existe un cuidador con ese codigo
			for (int i = 0; i < cuidadores.size(); i++) {
				if (cuidadores.get(i).getCodCuidador() == wCod) {
					existeCuidador = true;
					i = cuidadores.size();
				}
			}
			if (existeCuidador) {
				// Comprobacion de que existe un fichero de Seres Vivos
				if (fichVivos.exists()) {
					try {
						FileOutputStream fos = new FileOutputStream(fichVivos, true);
						MyObjectOutputStream moos = new MyObjectOutputStream(fos);
						System.out.println("¿Es un animal (1) o una planta (2)?");
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
						System.out.println("¿Es un animal (1) o una planta (2)?");
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
				System.out.println("Error, el cuidador no existe");
			}
			System.out.println("¿Quieres introducir otro ser vivo? (S/N)");
			mas = Util.leerChar('S', 'N');
		} while (mas == 'S');
	}

}
