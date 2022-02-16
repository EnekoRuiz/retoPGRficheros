 package clases;

import java.io.Serializable;
import java.time.LocalDate;

import util.Util;

public class Cuidador implements Serializable {

	private int codCuidador;
	private String nombre;
	private String apellidos;
	private LocalDate fechaAlta;
	private LocalDate fechaNac;
	private String formacion;

	// Constructor
	public Cuidador() {
		super();
	}

	// Getters y setters
	public int getCodCuidador() {
		return codCuidador;
	}

	public void setCodCuidador(int codCuidador) {
		this.codCuidador = codCuidador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public LocalDate getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(LocalDate fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public LocalDate getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(LocalDate fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getFormacion() {
		return formacion;
	}

	public void setFormacion(String formacion) {
		this.formacion = formacion;
	}

	public void getDatos() {
		System.out.println("El cuidador tiene el codigo " + codCuidador + ", se llama " + nombre + " " + apellidos
				+ ", empezo a trabajar el " + fechaAlta + ", nacio el " + fechaNac + " y tiene la formacion de "
				+ formacion);
	}

	// setDatos
	public void setDatos(int codCuidador) {
		this.codCuidador = codCuidador;
		System.out.println("El codigo se ha generado automaticamente, tu codigo es el: " + codCuidador);
		System.out.println("\nIntroduce el nombre del cuidador: ");
		nombre = Util.introducirCadena();
		System.out.println("\nIntroduce los apellidos del cuidador: ");
		apellidos = Util.introducirCadena();
		System.out.println("\nIntroduce la fecha de alta del cuidador: (DD/MM/AAAA) ");
		fechaAlta = Util.leerFechaDMA();
		System.out.println("\nIntroduce la fecha de nacimiento del cuidador: ");
		fechaNac = Util.leerFechaDMA();
		System.out.println("\nIntroduce la formacion del cuidador: ");
		formacion = Util.introducirCadena();

	}

}
