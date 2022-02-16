package clases;

import util.Util;

public class Planta extends SerVivo {

	private String tipoPlanta;
	private boolean flores;
	private String color;
	private String tipoReproduccion;

	// Constructor
	public Planta() {
		super();
	}

	// Getters y setters
	public String getTipoPlanta() {
		return tipoPlanta;
	}

	public void setTipoPlanta(String tipoPlanta) {
		this.tipoPlanta = tipoPlanta;
	}

	public boolean isFlores() {
		return flores;
	}

	public void setFlores(boolean flores) {
		this.flores = flores;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getTipoReproduccion() {
		return tipoReproduccion;
	}

	public void setTipoReproduccion(String tipoReproduccion) {
		this.tipoReproduccion = tipoReproduccion;
	}

	public void setDatos(int wCod) {
		super.setDatos(wCod);
		System.out.println("Introduce el tipo de planta: ");
		tipoPlanta = Util.introducirCadena();
		System.out.println("La planta tiene flores? (True/False)");
		flores = Util.leerBoolean();
		System.out.println("Introduce el color de la planta: ");
		color = Util.introducirCadena();
		System.out.println("Introduce el tipo de reproduccion: ");
		tipoReproduccion = Util.introducirCadena();
	}

	public void getDatos() {
		super.getDatos();
		String flor;
		if (flores == true) {
			flor = "tiene flores";
		} else {
			flor = "no tiene flores";
		}
		System.out.print("Ademas es una planta " + tipoPlanta + " que " + flor + ", tiene como color principal el "
				+ color + " y se reproduce de la siguiente manera: " + tipoReproduccion + "\n");
	}
}
