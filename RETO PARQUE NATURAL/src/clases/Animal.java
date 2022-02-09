package clases;

import java.util.ArrayList;
import java.util.List;

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
}
