package clases;

public class AuxColor implements Comparable<AuxColor>{
	private String color;
	private int veces;
	
	public AuxColor(String color, int veces) {
		super();
		this.color = color;
		this.veces = veces;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getVeces() {
		return veces;
	}
	public void setVeces(int veces) {
		this.veces = veces;
	}

	@Override
	public int compareTo(AuxColor o) {
		return o.getVeces()-veces;
	}
}
