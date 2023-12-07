package EjercicioEvaluable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Prestamo implements Serializable {

	//ATRIBUTOS 
	
	private int idlibro;
	private String nombreusuario;
	private int fechaprestamo;
	private int fechadevolucion;
	
	
	
	
	//CONSTRUCTOR 
	
	public Prestamo(int idlibro, String nombreusuario, int fechaprestamo, int fechadevolucion) {
		super();
		this.idlibro = idlibro;
		this.nombreusuario = nombreusuario;
		this.fechaprestamo = fechaprestamo;
		this.fechadevolucion = fechadevolucion;
	}
	
	public Prestamo(){
		
		
	}


// GETTERS Y SETTERS
	


	public int getIdlibro() {
		return idlibro;
	}




	public void setIdlibro(int idlibro) {
		this.idlibro = idlibro;
	}




	public String getNombreusuario() {
		return nombreusuario;
	}




	public void setNombreusuario(String nombreusuario) {
		this.nombreusuario = nombreusuario;
	}




	public int getFechaprestamo() {
		return fechaprestamo;
	}




	public void setFechaprestamo(int fechaprestamo) {
		this.fechaprestamo = fechaprestamo;
	}




	public int getFechadevolucion() {
		return fechadevolucion;
	}




	public void setFechadevolucion(int fechadevolucion) {
		this.fechadevolucion = fechadevolucion;
	}
	
	
	// TO STRING 
    public String toString() {
        return "Prestamo{" +
                "idlibro=" + idlibro +
                ", nombreusuario='" + nombreusuario + '\'' +
                ", fechaprestamo=" + fechaprestamo +
                ", fechadevolucion=" + fechadevolucion +
                '}';
    }
	
    public static List<Prestamo> crearPrestamosDesdeEntradaUsuario() {
        List<Prestamo> prestamos = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese la información de los préstamos:");

        boolean agregarPrestamo = true;
        // Se almacenan los datos del usuario
        while (agregarPrestamo) {
            System.out.print("ID del libro: ");
            int idLibro = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después del número

            System.out.print("Nombre del usuario: ");
            String nombreUsuario = scanner.nextLine();

            System.out.print("Fecha de préstamo: ");
            int fechaPrestamo = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después del número

            System.out.print("Fecha de devolución: ");
            int fechaDevolucion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después del número

            // Crear el préstamo con los datos proporcionados por el usuario
            Prestamo nuevoPrestamo = new Prestamo(idLibro, nombreUsuario, fechaPrestamo, fechaDevolucion);

            // Agregar el préstamo a la lista
            prestamos.add(nuevoPrestamo);

            System.out.print("¿Desea agregar otro préstamo? (Si/No): ");
            String respuesta = scanner.nextLine();

            agregarPrestamo = respuesta.equalsIgnoreCase("Si");
        }

        return prestamos;
    }
}
	

