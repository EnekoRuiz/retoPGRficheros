package clases;

public class AuxHabitat implements Comparable<AuxHabitat> {
	private String habitat;
	private int cuantos;
	
	
	
	//Constructor
	public AuxHabitat(String habitat, int cuantos) {
		super();
		this.habitat = habitat;
		this.cuantos = cuantos;
	}
	
	//GetDatos
	public String getHabitat() {
		return habitat;
	}
	public void setHabitat(String habitat) {
		this.habitat = habitat;
	}
	public int getCuantos() {
		return cuantos;
	}
	public void setCuantos(int cuantos) {
		this.cuantos = cuantos;
	}

	@Override
	public int compareTo(AuxHabitat o) {
		return o.getCuantos()-cuantos;
	}
	
	

}
