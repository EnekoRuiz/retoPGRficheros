package clases;

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

}
