package clases;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import util.Util;

public class Cuidador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		LocalDate pruebaFechaNacimiento;
		LocalDate pruebaFechaAlta;
		boolean pasarseFecha = true;
		this.codCuidador = codCuidador;
		System.out.println("El codigo se ha generado automaticamente, tu codigo es el: " + codCuidador);
		System.out.println("\nIntroduce el nombre del cuidador: ");
		nombre = Util.introducirCadena();
		System.out.println("\nIntroduce los apellidos del cuidador: ");
		apellidos = Util.introducirCadena();
		while (pasarseFecha) {

			System.out.println("\nIntroduce la fecha de nacimiento del cuidador: (DD/MM/AAAA) ");
			pruebaFechaNacimiento = Util.leerFechaDMA();
			pasarseFecha = fechaNacimientoCorrecta(pruebaFechaNacimiento, pasarseFecha);

		}
		while (!pasarseFecha) {
			System.out.println("\nIntroduce la fecha de alta del cuidador: (DD/MM/AAAA) ");
			pruebaFechaAlta = Util.leerFechaDMA();
			pasarseFecha = comprobacionFechaAltaMayorAlActual(pruebaFechaAlta, pasarseFecha);
		}
		System.out.println("\nIntroduce la formacion del cuidador: ");
		formacion = Util.introducirCadena();

	}

	// get años Trabajados
	public int getCalculoAnnosTrabajados() {
		int calcularAnnos = 1;
		Period diferenciaEdad;
		LocalDate hoy = LocalDate.now();
		diferenciaEdad = Period.between(fechaAlta, hoy);
		calcularAnnos = diferenciaEdad.getYears();
		return calcularAnnos;
	}

	// METODOS PARA CONTROLAR LAS FECHAS

	private boolean comprobacionFechaAltaPosteriorNacimiento(LocalDate probarFecha) {
		boolean comprobacion;
		Period diferenciaEdad;
		diferenciaEdad = Period.between(fechaNac, probarFecha);
		comprobacion = diferenciaEdad.isNegative();
		return comprobacion;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	public boolean comprobacionNoPasarseDeFecha(LocalDate fechaIntroducida) {
		boolean comprobacion;
		Period diferenciaEdad;
		LocalDate hoy = LocalDate.now();

		diferenciaEdad = Period.between(fechaIntroducida, hoy);
		comprobacion = diferenciaEdad.isNegative();
		return comprobacion;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private boolean fechaNacimientoCorrecta(LocalDate pruebaFechaNacimiento, boolean pasarseFecha) {
		boolean comprobacion1 = comprobacionNoPasarseDeFecha(pruebaFechaNacimiento);
		if (!comprobacion1) {
			fechaNac = pruebaFechaNacimiento;
			pasarseFecha = false;
		} else {
			System.out.println(
					"No es posible introducir una fecha de nacimiento superior al dia actual.");
		}
		return pasarseFecha;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private boolean fechaAltaCorrecta(LocalDate pruebaFechaAlta, boolean pasarseFecha) {
		// Comprobar que la fecha de Alta sea posterior a la fecha de Nacimiento
		boolean comprobacion2 = comprobacionFechaAltaPosteriorNacimiento(pruebaFechaAlta);
		if (!comprobacion2) {
			fechaAlta = pruebaFechaAlta;
			pasarseFecha = true;
		} else {
			System.out.println(
					"No es posible introducir una fecha de alta anterior a la fecha de nacimiento introducida.");
		}
		return pasarseFecha;
	}

	// -----------------------------------------------------------------------------------------------------------------------------------------------------------------

	private boolean comprobacionFechaAltaMayorAlActual(LocalDate pruebaFechaAlta, boolean pasarseFecha) {
		pasarseFecha = comprobacionNoPasarseDeFecha(pruebaFechaAlta);
		if (!pasarseFecha) {
			pasarseFecha = fechaAltaCorrecta(pruebaFechaAlta, pasarseFecha);
		} else {
			System.out.println("No es posible introducir una fecha de alta superior al dia actual.");
			pasarseFecha = false;
		}
		return pasarseFecha;
	}
}
