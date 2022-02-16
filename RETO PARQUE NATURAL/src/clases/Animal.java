package clases;

import java.util.ArrayList;
import java.util.List;

import util.Util;

public class Animal extends SerVivo {
	private boolean vertebrado;
	private String alimento;
	private String desplazamiento;
	private String clase;
	private List<String> vacunas = new ArrayList<String>();

	// Constructor
	public Animal() {
		super();
	}

	// Getters y Setters
	public boolean isVertebrado() {
		return vertebrado;
	}

	public void setVertebrado(boolean vertebrado) {
		this.vertebrado = vertebrado;
	}

	public String getAlimento() {
		return alimento;
	}

	public void setAlimento(String alimento) {
		this.alimento = alimento;
	}

	public String getDesplazamiento() {
		return desplazamiento;
	}

	public void setDesplazamiento(String desplazamiento) {
		this.desplazamiento = desplazamiento;
	}

	public String getClase() {
		return clase;
	}

	public void setClase(String clase) {
		this.clase = clase;
	}

	public List<String> getVacunas() {
		return vacunas;
	}

	public void setVacunas(List<String> vacunas) {
		this.vacunas = vacunas;
	}

	public void setDatos(int wCod) {
		boolean esta = false;
		char seguir;
		super.setDatos(wCod);
		System.out.println("El animal es vertebrado?");
		vertebrado = Util.leerBoolean();
		System.out.println("Introduce el alimento del animal: ");
		alimento = Util.introducirCadena();
		System.out.println("Introduce el metodo de desplazamiento: ");
		desplazamiento = Util.introducirCadena();
		System.out.println("Introduce la clase de animal: ");
		clase = Util.introducirCadena();

		do {
			System.out.println("Introduce la vacuna: ");
			String wVacuna = Util.introducirCadena();
			for (int i = 0; i < vacunas.size(); i++) {
				if (vacunas.get(i).equals(wVacuna)) {
					esta = true;
					i = vacunas.size();
				}
			}
			if (esta) {
				System.out.println("La vacuna ya esta introducida");
			} else {
				vacunas.add(wVacuna);
			}
			System.out.println("Quieres introducir mas vacunas? (S/N)");
			seguir = Util.leerChar('S', 'N');
		} while (seguir != 'N');
	}

	public void getDatos() {
		super.getDatos();
		String hueso;
		if (vertebrado == true) {
			hueso = "vertebrado";
		} else {
			hueso = "invertebrado";
		}
		System.out.print("Ademas es un " + hueso + " que tiene una dieta " + alimento
				+ ", se desplaza de la siguiente manera: " + desplazamiento + " y es un " + clase + "\n");
	}
}
