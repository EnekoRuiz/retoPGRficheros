package clases;

import java.util.ArrayList;
import java.util.List;

import util.Util;

public class SerVivo {

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
	
	//setDatos
		public void setDatos() {
			char seguir;
			
			System.out.println("Introduce el nombre cientifico");
			nombreCientifico=Util.introducirCadena();
			System.out.println("Introduce el habitat");
			habitat=Util.introducirCadena();
			System.out.println("Introduce el tamano medio");
			tamannoMedio=Util.leerFLoat();
			System.out.println("Introduce el nombre vulgar");
			nombreVulgar=Util.introducirCadena();
			System.out.println("Introduce la descripcion");
			descripcion=Util.introducirCadena();
			System.out.println("Introduce el numero de ejemplares");
			ejemplares=Util.leerInt();
			do {
				System.out.println("Introduce el codigo del cuidador a su cargo");
				int aux=Util.leerInt();
				codCuidador.add(aux);
				System.out.println("Quieres introducir mas cuidadores? (S/N)");
				seguir=Util.leerChar('S', 'N');
			}while(seguir!='N');
		}
}
