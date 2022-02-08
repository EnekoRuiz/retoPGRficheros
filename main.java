package principal;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import clases.Categoria;
import clases.Empleado;
import util.Util;
import util.MyObjectOutputStream;

public class main {

	public static void main(String[] args) {
		
		File fichEmpleado = new File("fichEmpleado.dat");
		File fichCategoria = new File("fichCategoria.dat");
		
		int opc, cuantos = 0;

		do {
			System.out.println("1. Alta de empleado \r \n"
					+ "2. Alta de categoria \r \n"
					+ "3. Modificaci�n del departamento de un empleado a partir de su c�digo de empleado \r \n"
					+ "4. Listado de los departamentos con el n�mero de empleados que hay en cada departamento \r \n"
					+ "5. Listado ordenador por categoria \r \n"
					+ "6. Salir");
			opc = Util.leerInt();
			
			switch(opc) {
			case 1: 
			try {
				altaEmpleados(fichEmpleado, cuantos);
			} catch (IOException e) {
				System.out.println("Te ha dado fallo manin");
			}
				break;

			case 2:
			try{
				altacategoria(fichCategoria, cuantos);
			} catch (IOException e){
				System.out.println("Error");
			}
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			}
			
		}
		while (opc!=6);
	}

	private static void altacategoria(File fichCategoria, int cuantos) throws IOException {
		int codCategoria;
		String descripcion;
		float sueldo;
		int mas, confirmar;

		if (fichCategoria.exists()){
			System.out.println("Ya hay un fichero creado, se añadiran los datos al final");

			FileOutputStream fos = new FileOutputStream(fichCategoria, true);
			MyObjectOutputStream mos = new MyObjectOutputStream(fos);

			do{
				codCategoria = cuantos+1;
				System.out.println("Introduce la descripcion de la categoria "+codCategoria);
				descripcion= Util.introducirCadena();
				System.out.println("Introduce el sueldo");
				sueldo= Util.leerFLoat();
				Categoria categoria = new Categoria(codCategoria, descripcion, sueldo);

				System.out.println("Estos son los datos que vas a introducir: \r \n"
				+"Codigo de la categoria: "+codCategoria+" \r \n"
				+"Descripcion: "+descripcion+" \r \n"
				+"Suelo: "+sueldo+" \r \n");
				mos.writeObject(categoria);

				System.out.println("Quieres introducir mas categorias? (1 si/2 no)");
				mas=Util.leerInt();
			}
			while(mas==1);
			mos.close();
			fos.close();
		}
		else {

		}
	}

	private static void altaEmpleados(File fichEmpleado, int cuantos) throws IOException {
		String nombre, apellido, dni, departamento;
		int codEmpleado, codCategoria, mas;
		
		if (fichEmpleado.exists()) {
			System.out.println("Ya hay un fichero creado, se a�adiran los datos al final");
			
			FileOutputStream fos = new FileOutputStream(fichEmpleado, true);
			MyObjectOutputStream moos = new MyObjectOutputStream(fos);
			
			do {
				System.out.println("Introduce el nombre");
				nombre = Util.introducirCadena();
				System.out.println("Introduce el apellido");
				apellido = Util.introducirCadena();
				System.out.println("Introduce el dni");
				dni = Util.introducirCadena();
				System.out.println("Introduce el departamento");
				departamento = Util.introducirCadena();
				System.out.println("Introduce la categoria");
				codCategoria = Util.leerInt();
				codEmpleado = cuantos+100;
				Empleado empleado = new Empleado(nombre, apellido, dni, codEmpleado, departamento, codCategoria);
				moos.writeObject(empleado);
				
				System.out.println("¿Quieres introducir mas alumnos? (1 si)(2 no)");
				mas=Util.leerInt();
			} while (mas == 1);
			moos.close();
			fos.close();
		}

		else {
			System.out.println("Creamos un fichero nuevo");
			FileOutputStream fos = new FileOutputStream(fichEmpleado);
			MyObjectOutputStream moos = new MyObjectOutputStream(fos);

			do {
				System.out.println("Introduce el nombre");
				nombre = Util.introducirCadena();
				System.out.println("Introduce el apellido");
				apellido = Util.introducirCadena();
				System.out.println("Introduce el dni");
				dni = Util.introducirCadena();
				System.out.println("Introduce el departamento");
				departamento = Util.introducirCadena();
				System.out.println("Introduce la categoria");
				codCategoria = Util.leerInt();
				codEmpleado = cuantos+100;
				Empleado empleado = new Empleado(nombre, apellido, dni, codEmpleado, departamento, codCategoria);
				moos.writeObject(empleado);
				
				System.out.println("¿Quieres introducir mas alumnos? (1 si)(2 no)");
				mas=Util.leerInt();
			} while (mas == 1);
			moos.close();
			fos.close();
		}
		System.out.println("Felicidades, has introducido un empleado en el fichero");
		
	}

}
