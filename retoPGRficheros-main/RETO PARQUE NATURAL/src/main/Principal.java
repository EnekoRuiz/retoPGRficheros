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

	// Esto hay que preguntarle a Carmen 12/02/22
	private static Cuidador busquedaCuidador(File fichCuidadores, int wCod, Cuidador cuidador) {
		try {
			FileInputStream fis = new FileInputStream(fichCuidadores);
			ObjectInputStream ois = new ObjectInputStream(fis);
			int cuantos = Util.calculoFichero(fichCuidadores);

			for (int i = 0; i < cuantos; i++) {
				cuidador = (Cuidador) ois.readObject();
				if (cuidador.getCodCuidador() == wCod) {
					i = cuantos;
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
		return cuidador;
	}

	private static void bajaCuidadores(File fichCuidadores) {
		System.out.println("Introduce el codigo del trabajador que quieres modificar");
		int codUsuario = Util.leerInt();
		int cont = Util.calculoFichero(fichCuidadores);
		boolean modificado = false;

		// Volcado a arraylist
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

		// Modificacion del boolean Activo
		for (int i = 0; i < cuidadores.size(); i++) {
			if (cuidadores.get(i).getCodCuidador() == codUsuario) {
				System.out.println("Los datos de este cuidador son:");
				cuidadores.get(i).getDatos();
				System.out.println("Desea darle de baja?(S/N)");
				char opc = Util.leerChar();
				if (opc == 'S') {
					cuidadores.get(i).setActivo(false);
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
			System.out.println("Modificacion realizada con exito");
		}
	}

	private static void modificarCuidadores(File fichCuidadores) {
		System.out.println("Introduce el codigo del trabajador que quieres modificar");
		int codUsuario = Util.leerInt();
		int cont = Util.calculoFichero(fichCuidadores);
		boolean modificado = false;

		// Volcado a arraylist
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

		// Modificacion del cuidador
		for (int i = 0; i < cuidadores.size(); i++) {
			if (cuidadores.get(i).getCodCuidador() == codUsuario) {
				System.out.println("Los datos de este cuidador son:");
				cuidadores.get(i).getDatos();
				System.out.println("Desea modificarlos?(S/N)");
				char opc = Util.leerChar();
				if (opc == 'S') {
					modificado = true;
					boolean salir = false;
					do {
						System.out.println("Que desea modificar:");
						System.out.println("1) Nombre\n"
								+ "2) Apellido\n"
								+ "3) Fecha de Nacimiento\n"
								+ "4) Fecha de Alta\n"
								+ "5) Formacion\n"
								+ "6) Salir\n");
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

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
						modificarSeresVivos(fichVivos);
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
	}

	private static void modificarSeresVivos(File fichVivos) {
			System.out.println("Introduce el nombre cientifico del animal que quieres modificar");
			String serUsuario = Util.introducirCadena();
			int cont = Util.calculoFichero(fichVivos);
			boolean modificado = false;
	
			// Volcado a arraylist
			ArrayList<SerVivo> seresVivos = new ArrayList<SerVivo>(cont);
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try {
				fis = new FileInputStream(fichVivos);
				ois = new ObjectInputStream(fis);
				for (int i = 0; i < cont; i++) {
					seresVivos.add(i, (SerVivo) ois.readObject());
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
	
			// Modificacion del ser vivo 
			for (int i = 0; i < seresVivos.size(); i++) {
				if (seresVivos.get(i).getNombreCientifico() == serUsuario) {
					System.out.println("Los datos de este ser vivo son:");
					if (seresVivos.get(i) instanceof Animal){
						seresVivos.get(i).getDatos();
					}
					//Modificacion si es un animal
					if (seresVivos.get(i) instanceof Animal){
						((Animal)seresVivos.get(i)).getDatos();
						System.out.println("Desea modificar los datos? (S/N)");
						char opc = Util.leerChar();
						if (opc == 'S'){
							modificado = true;
							boolean salir = false;
							do{
								System.out.println("¿Que desea modificar? \n"
								+" 1) Ejemplares \n"
								+" 2) Cuidadores a su cargo \n"
								+" 3) Desplazamiento");
								int opcion = Util.leerInt();
								switch (opcion){
									case 1:
									System.out.println("¿Cuantos ejemplares hay?");
									seresVivos.get(i).setEjemplares(Util.leerInt());
										break;

									case 2:
									System.out.println("Estos son los cuidadores a su cargo");
									ArrayList <Integer> codigos = (ArrayList)seresVivos.get(i).getCodCuidador();
									for (int e=0; e<codigos.size();e++){
										System.out.println(codigos.get(e));
									}  
									System.out.println("¿Quieres eliminar (1) o añadir (2) un codigo?");
									int respuesta = Util.leerInt();
									int codUsuario;
									Cuidador cuidador = new Cuidador();
									if (respuesta == 1){
										System.out.println("Introduce el codigo que deseas eliminar");
										codUsuario = Util.leerInt();
										busquedaCuidador(fichVivos, codUsuario, cuidador);
										if (cuidador != null) {
											for (int e = 0; e<codigos.size();e++){
												if (codigos.get(e).equals(codUsuario))
												codigos.remove(e);
											}
										}
										else
										 System.out.println("Error, ese codigo no existe");
									}
									else {
										System.out.println("Introduce el codigo que deseas añadir");
										codUsuario = Util.leerInt();
										codigos.add(codUsuario);
									}
										break;

									case 3:
									System.out.println("¿Que tipo de desplzamiento tiene?");
									((Animal)seresVivos.get(i)).setDesplazamiento(Util.introducirCadena());
										break;
								}
							} while (!salir);
						}
					}
					//Modificacion si es una planta
					else {
						((Planta)seresVivos.get(i)).getDatos();
						System.out.println("Desea modificar los datos? (S/N)");
						char opc = Util.leerChar();
						if (opc == 'S'){
							modificado = true;
							boolean salir = false;
							do{
								System.out.println("¿Que desea modificar? \n"
								+" 1) Ejemplares \n"
								+" 2) Cuidadores a su cargo \n"
								+" 3) Tipo de reproduccion");
								int opcion = Util.leerInt();
								switch (opcion){
									case 1:
									System.out.println("¿Cuantos ejemplares hay?");
									seresVivos.get(i).setEjemplares(Util.leerInt());
										break;

									case 2:
									System.out.println("Estos son los cuidadores a su cargo");
									ArrayList <Integer> codigos = (ArrayList)seresVivos.get(i).getCodCuidador();
									for (int e=0; e<codigos.size();e++){
										System.out.println(codigos.get(e));
									}  
									System.out.println("¿Quieres eliminar (1) o añadir (2) un codigo?");
									int respuesta = Util.leerInt();
									int codUsuario;
									Cuidador cuidador = new Cuidador();
									if (respuesta == 1){
										System.out.println("Introduce el codigo que deseas eliminar");
										codUsuario = Util.leerInt();
										busquedaCuidador(fichVivos, codUsuario, cuidador);
										if (cuidador != null) {
											for (int e = 0; e<codigos.size();e++){
												if (codigos.get(e).equals(codUsuario))
												codigos.remove(e);
											}
										}
										else
										 System.out.println("Error, ese codigo no existe");
									}
									else {
										System.out.println("Introduce el codigo que deseas añadir");
										codUsuario = Util.leerInt();
										codigos.add(codUsuario);
									}
										break;

									case 3:
									System.out.println("¿Que tipo de reproduccion tiene?");
									((Planta)seresVivos.get(i)).setTipoReproduccion(Util.introducirCadena());
										break;
								}
							} while (!salir);
						}
					}
					
				} else {
					System.out.println("Error, no se han encontrado cuidadores con ese codigo");
				}
			}
	
			// Se han realizado cambios, se vuelcan los datos del arraylist al fichero
			if (modificado) {
				try {
					FileOutputStream fos = new FileOutputStream(fichVivos);
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					for (int i = 0; i < cont - 1; i++) {
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
				System.out.println("Modificacion realizada con exito");
			}
		}
	

	private static void listarSeresVivos(File fichVivos) {
		Animal animal = new Animal();
		Planta planta = new Planta();
		try {
			FileInputStream fis = new FileInputStream(fichVivos);
			ObjectInputStream ois = new ObjectInputStream(fis);
			int cuantos = Util.calculoFichero(fichVivos);
			for (int i = 0; i < cuantos; i++) {
				// Compara si es un animal o si es una planta
				if (ois.readObject().getClass().equals(animal)) {
					animal = (Animal) ois.readObject();
					animal.getDatos();
				} else {
					planta = (Planta) ois.readObject();
					planta.getDatos();
				}
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
			Cuidador cuidador = new Cuidador();
			busquedaCuidador(fichCuidadores, wCod, cuidador);
			if (cuidador != null) {
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
				System.out.println("El codigo del cuidador no se ha encontrado.");
			}
			System.out.println("¿Quieres introducir otro ser vivo? (S/N)");
			mas = Util.leerChar('S', 'N');
		} while (mas == 'S');
	}

}
