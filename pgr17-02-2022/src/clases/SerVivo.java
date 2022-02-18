package clases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import util.Util;

public class SerVivo implements Serializable {

	private String nombreCientifico;
	private String habitat;
	private float tamannoMedio;
	private String nombreVulgar;
	private String descripcion;
	private int ejemplares;
	private List<Integer> codCuidador = new ArrayList<Integer>();

	// Constructor
	public SerVivo() {
		super();
	}

	// Getters y setters
	public String getNombreCientifico() {
		return nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	public String getHabitat() {
		return habitat;
	}

	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}

	public float getTamannoMedio() {
		return tamannoMedio;
	}

	public void setTamannoMedio(float tamannoMedio) {
		this.tamannoMedio = tamannoMedio;
	}

	public String getNombreVulgar() {
		return nombreVulgar;
	}

	public void setNombreVulgar(String nombreVulgar) {
		this.nombreVulgar = nombreVulgar;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getEjemplares() {
		return ejemplares;
	}

	public void setEjemplares(int ejemplares) {
		this.ejemplares = ejemplares;
	}

	public List<Integer> getCodCuidador() {
		return codCuidador;
	}

	public void setCodCuidador(List<Integer> codCuidador) {
		this.codCuidador = codCuidador;
	}

	public void borrarCuidador(int codUsuario) {
		for (int i = 0; i < codCuidador.size(); i++) {
			if (codCuidador.get(i) == codUsuario) {
				codCuidador.remove(i);
				i = codCuidador.size();
			}
		}
	}

	public boolean encontrarCuidador(int codUsuario) {
		boolean encontrado = false;
		for (int i = 0; i < codCuidador.size(); i++) {
			if (codCuidador.get(i) == codUsuario) {
				encontrado = true;
			}
		}
		return encontrado;
	}
	public void setCodigo(File Cuidadores, int wCod) {
		char seguir;
		boolean esta = false;
		
		for (int i = 0; i < codCuidador.size(); i++) {
			if (codCuidador.get(i).equals(wCod)) {
				esta = true;
				i = codCuidador.size();
			}
		}
		if (esta) {
			System.out.println("El cuidador ya esta introducido");
		} else {
			codCuidador.add(wCod);
		}	
		
	}
	
	// setDatos
	public void setDatos() {
		System.out.println("Introduce el nombre cientifico");
		nombreCientifico = Util.introducirCadena();
		System.out.println("Introduce el habitat");
		habitat = Util.introducirCadena();
		System.out.println("Introduce el tamano medio (cm)");
		tamannoMedio = Util.leerFLoat();
		System.out.println("Introduce el nombre vulgar");
		nombreVulgar = Util.introducirCadena();
		System.out.println("Introduce la descripcion");
		descripcion = Util.introducirCadena();
		System.out.println("Introduce el numero de ejemplares");
		ejemplares = Util.leerInt();
	}

	public void getDatos() {
		System.out.println("El nombre cientifico del ser vivo es " + nombreCientifico + ", su habitat es " + habitat
				+ ", el tamaño medio de la especie es " + tamannoMedio + "cm, su nombre vulgar es " + nombreVulgar
				+ ", tiene " + ejemplares + " ejemplares en el parque y su descripcion es la siguiente:"
				+ descripcion);
	}
}
