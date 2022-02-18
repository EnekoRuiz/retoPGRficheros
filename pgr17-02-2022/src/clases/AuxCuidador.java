package clases;

import java.util.ArrayList;

public class AuxCuidador implements Comparable<AuxCuidador> {
    private String formacion;
    private ArrayList<Integer> codigos = new ArrayList<>();

    public AuxCuidador(String formacion) {
        this.formacion = formacion;
    }

    public String getFormacion() {
        return this.formacion;
    }

    public void setFormacion(String formacion) {
        this.formacion = formacion;
    }

    public ArrayList<Integer> getCodigos() {
        return this.codigos;
    }

    public void setCodigos(ArrayList<Integer> codigos) {
        this.codigos = codigos;
    }

    @Override
    public int compareTo(AuxCuidador o) {
        return this.formacion.compareTo(o.formacion);
    }

}