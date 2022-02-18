package util;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Util {

	public static boolean leerBoolean() {
		String es;
		do {
			es = introducirCadena();
		} while (!es.equalsIgnoreCase("true") && !es.equalsIgnoreCase("false"));
		boolean buleano = Boolean.parseBoolean(es);
		return buleano;
	}

	public static String fechaToString(LocalDate fecha) {
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String wfecha;

		wfecha = fecha.format(formateador);

		return wfecha;
	}

	// Devuelve el n�mero de objetos de un fichero
	public static int calculoFichero(File fich) {
		int cont = 0;
		if (fich.exists()) {
			FileInputStream fis = null;
			ObjectInputStream ois = null;
			try {
				fis = new FileInputStream(fich);
				ois = new ObjectInputStream(fis);

				Object aux = ois.readObject();

				while (aux != null) {
					cont++;
					aux = ois.readObject();
				}

			} catch (EOFException e1) {
				

			} catch (Exception e2) {
				e2.printStackTrace();
			}

			try {
				ois.close();
				fis.close();
			} catch (IOException e) {
				System.out.println("Error al cerrar los flujos");

			}
		}
		return cont;
	}

	public static LocalDate leerFechaDMA() {
		boolean error;
		LocalDate date = null;
		String dateString;
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		do {
			error = false;
			dateString = introducirCadena();
			try {
				date = LocalDate.parse(dateString, formateador);
			} catch (DateTimeParseException e) {
				System.out.println("Error, introduce una fecha en formato dd/mm/aaaa ");
				error = true;
			}
		} while (error);
		return date;
	}

	public static LocalDate leerFechaAMD() {
		boolean error;
		LocalDate date = null;
		String dateString;
		DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		do {
			error = false;
			dateString = introducirCadena();
			try {
				date = LocalDate.parse(dateString, formateador);
			} catch (DateTimeParseException e) {
				System.out.println("Error, uuuu/hh/ee ");
				error = true;
			}
		} while (error);
		return date;
	}

	public static char leerChar(char opt1, char opt2) {
		char letra = ' ';
		String cadena;
		boolean error;
		do {
			error = false;
			cadena = introducirCadena();
			if (cadena.length() != 1) {
				System.out.println("Error, introduce un ï¿½nico caracter: ");
				error = true;
			} else {
				letra = cadena.charAt(0);
				letra = Character.toUpperCase(letra);
				if (letra != opt1 && letra != opt2) {
					System.out.println(
							"Error, la opciï¿½n introducida no es correcta, introduce " + opt1 + " o " + opt2);
					error = true;
				}
			}
		} while (error);

		return letra;
	}

	public static char leerChar() {
		char letra = ' ';
		String cadena;
		boolean error;
		do {
			error = false;
			cadena = introducirCadena();
			if (cadena.length() != 1) {
				System.out.println("Error, introduce un ï¿½nico caracter: ");
				error = true;
			}
		} while (error);
		letra = cadena.charAt(0);
		return letra;
	}

	public static float leerFLoat() {
		float flotante = 0;
		boolean error;
		do {
			error = false;
			try {
				flotante = Float.parseFloat(introducirCadena());
			} catch (NumberFormatException e) {
				System.err.println("Error en la entrada de datos");
				error = true;
			}
		} while (error);

		return flotante;
	}

	public static float leerFloat(float min, float max) {
		float flotante = 0;
		float aux;
		boolean error;
		if (min > max) {
			aux = min;
			min = max;
			max = aux;
		}
		do {
			error = false;
			try {
				flotante = Float.parseFloat(introducirCadena());
			} catch (NumberFormatException e) {
				System.err.println("Error en la entrada de datos");
				error = true;
			}
			if (!error && (flotante < min || flotante > max)) {
				System.err.println("Error, el valor no se encuentra entre " + min + " y " + max + ".");
				error = true;
			}
		} while (error);

		return flotante;
	}

	public static float leerFloat(String message, float min, float max) {
		float flotante = 0;
		float aux;
		boolean error;
		if (min > max) {
			aux = min;
			min = max;
			max = aux;
		}
		do {
			System.out.println("\n" + message);
			error = false;
			try {
				flotante = Float.parseFloat(introducirCadena());
			} catch (NumberFormatException e) {
				System.err.println("Error en la entrada de datos");
				error = true;
			}
			if (!error && (flotante < min || flotante > max)) {
				System.err.println("Error, el valor no se encuentra entre " + min + " y " + max + ".");
				error = true;
			}
		} while (error);

		return flotante;
	}

	public static int leerInt(String message, int min, int max) {
		int entero = 0;
		int aux;
		boolean error;
		if (min > max) {
			aux = min;
			min = max;
			max = aux;
		}
		do {
			System.out.println("\n" + message);
			error = false;
			try {
				entero = Integer.parseInt(introducirCadena());
			} catch (NumberFormatException e) {
				System.err.println("Error en la entrada de datos");
				error = true;
			}
			if (!error && (entero < min || entero > max)) {
				System.err.println("Error, el valor no se encuentra entre " + min + " y " + max + ".");
				error = true;
			}
		} while (error);

		return entero;
	}

	public static int leerInt(int min, int max) {
		int entero = 0;
		int aux;
		boolean error;
		if (min > max) {
			aux = min;
			min = max;
			max = aux;
		}
		do {
			error = false;
			try {
				entero = Integer.parseInt(introducirCadena());
			} catch (NumberFormatException e) {
				System.err.println("Error en la entrada de datos");
				error = true;
			}
			if (!error && (entero < min || entero > max)) {
				System.err.println("Error, el valor no se encuentra entre " + min + " y " + max + ".");
				error = true;
			}
		} while (error);

		return entero;
	}

	public static int leerInt() {
		int entero = 0;
		boolean error;
		do {
			error = false;
			try {
				entero = Integer.parseInt(introducirCadena());
			} catch (NumberFormatException e) {
				System.err.println("Error en la entrada de datos");
				error = true;
			}
		} while (error);

		return entero;
	}

	public static String introducirCadena() {
		String cadena = null;
		boolean error;
		InputStreamReader entrada = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(entrada);
		do {
			error = false;
			try {
				cadena = teclado.readLine();
			} catch (IOException e) {
				System.err.println("Error en la entrada de datos");
				error = true;
			}
		} while (error);

		return cadena;
	}
}
