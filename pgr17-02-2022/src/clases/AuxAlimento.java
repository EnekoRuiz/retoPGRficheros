package clases;

import java.util.ArrayList;

public class AuxAlimento implements Comparable<AuxAlimento> {
    private String alimento;
    private String nombreVulgar;
    private ArrayList<String> nombresVulgares = new ArrayList<>();

    public String getAlimento() {
        return this.alimento;
    }

    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    public String getNombreVulgar() {
        return this.nombreVulgar;
    }

    public void setNombreVulgar(String nombreVulgar) {
        this.nombreVulgar = nombreVulgar;
    }

    public ArrayList<String> getNombresVulgares() {
        return this.nombresVulgares;
    }

    public void setNombresVulgares(ArrayList<String> nombresVulgares) {
        this.nombresVulgares = nombresVulgares;
    }

    // Contructor
    public AuxAlimento(String alimento, String nombreVulgar) {
        this.alimento = alimento;
        this.nombreVulgar = nombreVulgar;
    }

    @Override
    public int compareTo(AuxAlimento o) {
        return this.nombreVulgar.compareTo(o.nombreVulgar);
    }

}
