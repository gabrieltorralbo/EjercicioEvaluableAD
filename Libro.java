package EjercicioEvaluable;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Libro implements Serializable {

//ATRIBUTOS	
	
private int id;
private String titulo;
private String autor;
private int anioPublicacion;
private String genero;

//CONSTRUCTORES
public Libro(int id, String titulo, String autor, int anioPublicacion, String genero) {
	this.id = id;
	this.titulo = titulo;
	this.autor = autor;
	this.anioPublicacion = anioPublicacion;
	this.genero = genero;
}

public Libro() {
}

// GETTERS Y SETTERS
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public String getAutor() {
	return autor;
}
public void setAutor(String autor) {
	this.autor = autor;
}
public int getAnioPublicacion() {
	return anioPublicacion;
}
public void setAnioPublicacion(int anioPublicacion) {
	this.anioPublicacion = anioPublicacion;
}
public String getGenero() {
	return genero;
}
public void setGenero(String genero) {
	this.genero = genero;
}

// TO STRING
public String toString() {
    return "Libro{" +
            "id=" + id +
            ", titulo='" + titulo + '\'' +
            ", autor='" + autor + '\'' +
            ", anioPublicacion=" + anioPublicacion +
            ", genero='" + genero + '\'' +
            '}';
}

public static List<Libro> crearLibrosDesdeEntradaUsuario() {
    List<Libro> libros = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    System.out.println("Ingrese la información de los libros:");

    boolean agregarLibro = true;
    // Se almacenan los datos de usuario
    while (agregarLibro) {
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después del número

        System.out.print("Título: ");
        String titulo = scanner.nextLine();

        System.out.print("Autor: ");
        String autor = scanner.nextLine();

        System.out.print("Año de Publicación: ");
        int anioPublicacion = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después del número

        System.out.print("Género: ");
        String genero = scanner.nextLine();

        // Crear el libro con los datos proporcionados por el usuario
        Libro nuevoLibro = new Libro(id, titulo, autor, anioPublicacion, genero);

        // Agregar el libro a la lista
        libros.add(nuevoLibro);

        System.out.print("¿Desea agregar otro libro? (Si/No): ");
        String respuesta = scanner.nextLine();

        agregarLibro = respuesta.equalsIgnoreCase("Si");
    }

    return libros;
}

public static void actualizarLibroPorId(String filename, int id) throws IOException, ClassNotFoundException {
    // Leer la lista de libros desde el archivo binario
    List<Libro> libros = GestorFicheros.leerLibrosBinario("C:/Libros.ser");

    // Buscar el libro por ID
    Libro libroParaActualizar = buscarLibroPorId(libros, id);

    if (libroParaActualizar != null) {
        // Se actualizan los valores 
        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese los nuevos datos para el libro (o presione Enter para dejarlos sin cambios):");

        System.out.print("Título (" + libroParaActualizar.getTitulo() + "): ");
        String nuevoTitulo = scanner.nextLine();
        if (!nuevoTitulo.isEmpty()) {
            libroParaActualizar.setTitulo(nuevoTitulo);
        }

        System.out.print("Autor (" + libroParaActualizar.getAutor() + "): ");
        String nuevoAutor = scanner.nextLine();
        if (!nuevoAutor.isEmpty()) {
            libroParaActualizar.setAutor(nuevoAutor);
        }

        System.out.print("Año de Publicación (" + libroParaActualizar.getAnioPublicacion() + "): ");
        String nuevoAnioPublicacionStr = scanner.nextLine();
        if (!nuevoAnioPublicacionStr.isEmpty()) {
            int nuevoAnioPublicacion = Integer.parseInt(nuevoAnioPublicacionStr);
            libroParaActualizar.setAnioPublicacion(nuevoAnioPublicacion);
        }

        System.out.print("Género (" + libroParaActualizar.getGenero() + "): ");
        String nuevoGenero = scanner.nextLine();
        if (!nuevoGenero.isEmpty()) {
            libroParaActualizar.setGenero(nuevoGenero);
        }

        // Guardar la lista actualizada en el archivo binario
        GestorFicheros.guardarLibrosBinario(libros, filename);
        System.out.println("Libro actualizado con éxito.");
    } else {
        System.out.println("No se encontró un libro con el ID proporcionado.");
    }
}


public static void eliminarLibroPorId(String filename, int id) throws IOException, ClassNotFoundException {
    // Leer la lista de libros desde el archivo binario
    List<Libro> libros = GestorFicheros.leerLibrosBinario(filename);

    // Buscar el libro por ID
    Libro libroAEliminar = buscarLibroPorId(libros, id);

    if (libroAEliminar != null) {
        // Eliminar el libro de la lista
        libros.remove(libroAEliminar);

        // Guardar la lista actualizada en el archivo binario
        GestorFicheros.guardarLibrosBinario(libros, filename);
        System.out.println("Libro eliminado con éxito.");
    } else {
        System.out.println("No se encontró un libro con el ID proporcionado.");
    }
}

// Este metodo es que realiza la busqueda por ID
private static Libro buscarLibroPorId(List<Libro> libros, int id) {
    for (Libro libro : libros) {
        if (libro.getId() == id) {
            return libro;
        }
    }
    return null; // No se encontró un libro con el ID proporcionado
}
    


	
    
}








