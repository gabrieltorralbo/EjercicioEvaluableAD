package EjercicioEvaluable;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Autor implements Serializable {

	//ATRIBUTOS

	
	private int id;
	private String nombre;
	private String nacionalidad;
	private int anionacimiento;
	

	
	// CONSTRUCTOR 
	
public Autor(int id, String nombre, String nacionalidad, int anionacimiento) {
	this.id = id;
	this.nombre = nombre;
	this.nacionalidad = nacionalidad;
	this.anionacimiento = anionacimiento;	}

public Autor(){
	
}

// GETTERS Y SETTERS

public int getId() {
	return id;
}



public void setId(int id) {
	this.id = id;
}



public String getNombre() {
	return nombre;
}



public void setNombre(String nombre) {
	this.nombre = nombre;
}



public String getNacionalidad() {
	return nacionalidad;
}



public void setNacionalidad(String nacionalidad) {
	this.nacionalidad = nacionalidad;
}



public int getAnionacimiento() {
	return anionacimiento;
}



public void setAnionacimiento(int anionacimiento) {
	this.anionacimiento = anionacimiento;
}

public String toString() {
    return "Autor{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", nacionalidad='" + nacionalidad + '\'' +
            ", anioNacimiento=" + anionacimiento +
            '}';
}
	
public static List<Autor> crearAutoresDesdeEntradaUsuario() {
    List<Autor> Autores = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    System.out.println("Ingrese la información de los Autores:");

    boolean agregarAutor = true;

    while (agregarAutor) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después del número

        System.out.print("Nombre: ");
        String Nombre = scanner.nextLine();

        System.out.print("Nacionalidad: ");
        String Nacionalidad = scanner.nextLine();

        System.out.print("Año de Nacimiento: ");
        int anionacimiento = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después del número

       

        // Crear el libro con los datos proporcionados por el usuario
        Autor nuevoAutor = new Autor(id, Nombre, Nacionalidad, anionacimiento);

        // Agregar el libro a la lista
        Autores.add(nuevoAutor);

        System.out.print("¿Desea agregar otro autor? (Si/No): ");
        String respuesta = scanner.nextLine();

        agregarAutor = respuesta.equalsIgnoreCase("si");
    }

    return Autores;
}

public static void actualizarAutorPorId(String filename, int id) throws IOException, ClassNotFoundException {
    // Leer la lista de libros desde el archivo binario
    List<Autor> autores = GestorFicheros.leerAutoresBinario("C:/Autores.ser");

    // Buscar el libro por ID
    Autor AutorParaActualizar = buscarAutorPorId(autores, id);

    if (AutorParaActualizar != null) {
        // Se actualizan los valores
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los nuevos datos para el libro (o presione Enter para dejarlos sin cambios):");

        System.out.print("Nombre (" + AutorParaActualizar.getNombre() + "): ");
        String nuevoNombre = scanner.nextLine();
        if (!nuevoNombre.isEmpty()) {
            AutorParaActualizar.setNombre(nuevoNombre);
        }

        System.out.print("Nacionalidad (" + AutorParaActualizar.getNacionalidad() + "): ");
        String nuevaNacionalidad = scanner.nextLine();
        if (!nuevaNacionalidad.isEmpty()) {
            AutorParaActualizar.setNacionalidad(nuevaNacionalidad);
        }

        System.out.print("Año de Nacimiento (" + AutorParaActualizar.getAnionacimiento() + "): ");
        String nuevoAnionacimientoSt = scanner.nextLine();
        if (!nuevoAnionacimientoSt.isEmpty()) {
            int nuevoAnionacimiento = Integer.parseInt(nuevoAnionacimientoSt);
            AutorParaActualizar.setAnionacimiento(nuevoAnionacimiento);
        }

        

        // Guardar la lista actualizada en el archivo binario
       GestorFicheros.guardarAutoresBinario(autores, filename);
        System.out.println("Autor actualizado con éxito.");
    } else {
        System.out.println("No se encontró un Autor con el ID proporcionado.");
    }
}

public static void eliminarAutorPorId(String filename, int id) throws IOException, ClassNotFoundException {
    // Leer la lista de libros desde el archivo binario
    List<Autor> autores = GestorFicheros.leerAutoresBinario(filename);

    // Buscar el libro por ID
    Autor AutorAEliminar = buscarAutorPorId(autores, id);

    if (AutorAEliminar != null) {
        // Eliminar el libro de la lista
        autores.remove(AutorAEliminar);

        // Guardar la lista actualizada en el archivo binario
        GestorFicheros.guardarAutoresBinario(autores, filename);
        System.out.println("Autor eliminado con éxito.");
    } else {
        System.out.println("No se encontró un Autor con el ID proporcionado.");
    }
}
private static Autor buscarAutorPorId(List<Autor> autores, int id) {
    for (Autor autor : autores) {
        if (autor.getId() == id) {
            return autor;
        }
    }
    return null; // No se encontró un libro con el ID proporcionado
}

}
