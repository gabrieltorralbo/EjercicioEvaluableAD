package EjercicioEvaluable;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.IOException;
import java.util.List;
public class GestorFicheros {

	public static void guardarLibrosBinario(List<Libro> libros, String filename) {
	    // Método para guardar una lista de libros en un archivo binario

	    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
	        // Intenta abrir el ObjectOutputStream para escribir en el archivo
	        oos.writeObject(libros); // Escribe la lista de libros en el archivo
	        System.out.println("Libros guardados en el archivo binario con éxito.");
	    } catch (IOException e) {
	        // Captura cualquier excepción de E/S
	        e.printStackTrace();
	        System.out.println("Error al guardar los autores en el archivo binario: " + e.getMessage());
	    }
	}
	public static List<Libro> leerLibrosBinario(String filename) throws IOException, ClassNotFoundException {
	    // Método para leer una lista de libros desde un archivo binario

	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
	        // Intenta abrir el ObjectInputStream para leer desde el archivo
	        List<Libro> readObject = (List<Libro>) ois.readObject(); // Lee la lista de libros desde el archivo
	        return readObject; // Devuelve la lista leída
	    } catch (IOException | ClassNotFoundException e) {
	        // Captura excepciones de E/S y de la clase no encontrada
	        e.printStackTrace();
	        throw new IOException("Error al leer el archivo binario: " + e.getMessage());
	    }
	}


public static void guardarAutoresBinario(List<Autor> Autores, String filename) {
	// Método para guardar una lista de autores en un archivo binario
	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
		 // Intenta abrir el ObjectOutputStream para escribir en el archivo
		oos.writeObject(Autores);// Escribe la lista de autores en el archivo
		System.out.println("Autores guardados en el archivo binario con éxito.");
	} catch (IOException e) {
		// Captura cualquier excepción de E/S
		e.printStackTrace();
		System.out.println("Error al guardar los autores en el archivo binario: " + e.getMessage());
	}
}


public static List<Autor> leerAutoresBinario(String filename) throws IOException, ClassNotFoundException {
	  // Método para leer una lista de autores desde un archivo binario
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
    	// Intenta abrir el ObjectInputStream para leer desde el archivo
        List<Autor> readObject = (List<Autor>) ois.readObject();
		return readObject;
    } catch (IOException | ClassNotFoundException e) {
    	// Captura excepciones de E/S y de la clase no encontrada
        e.printStackTrace();
        throw new IOException("Error al leer el archivo binario: " + e.getMessage());
    }
}

public static void guardarPrestamosTexto(List<Prestamo> nuevosPrestamos, String filename) throws IOException {
    // Leer los prestamos actuales del archivo
    List<Prestamo> prestamosActuales = leerPrestamosTexto(filename);

    // Agregar los nuevos prestamos a la lista existente
    prestamosActuales.addAll(nuevosPrestamos);

    // Guardar toda la lista actualizada en el archivo
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
        for (Prestamo prestamo : prestamosActuales) {
            // Formatear la información del préstamo y escribir en el archivo
            String linea = String.format("%d;%s;%d;%d%n",
                    prestamo.getIdlibro(),
                    prestamo.getNombreusuario(),
                    prestamo.getFechaprestamo(),
                    prestamo.getFechadevolucion());

            writer.write(linea);
        }
    } catch (IOException e) {
        e.printStackTrace();
        throw new IOException("Error al escribir en el archivo de préstamos: " + e.getMessage());
    }
}
public static List<Prestamo> leerPrestamosTexto(String filename) {
    List<Prestamo> prestamos = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String linea;
        while ((linea = reader.readLine()) != null) {
            // Imprimir la línea antes de intentar convertirla
            System.out.println("Línea leída: " + linea);

            // Parsear la línea y agregar el Prestamo a la lista
            String[] partes = linea.split(";");
            int idlibro = Integer.parseInt(partes[0]);
            String nombreusuario = partes[1];
            int fechaprestamo = Integer.parseInt(partes[2]);
            int fechadevolucion = Integer.parseInt(partes[3]);

            Prestamo prestamo = new Prestamo(idlibro, nombreusuario, fechaprestamo, fechadevolucion);
            prestamos.add(prestamo);
        }
        reader.close();
    } catch (IOException | NumberFormatException e) {
        e.printStackTrace();
    }

    return prestamos;
}
public static List<Libro> importarLibrosDesdeXML(String rutaArchivo) {
    List<Libro> listaDeLibros = new ArrayList<>();

    try {
        // Configurar el analizador de documentos XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parsear el documento XML
        Document document = builder.parse(rutaArchivo);

        // Obtener la lista de elementos de libro
        NodeList nodeList = document.getElementsByTagName("libro");

        // Iterar a través de cada elemento de libro y crear objetos Libro
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element libroElement = (Element) nodeList.item(i);
            // Obtener atributos e información del elemento libro
            int id = Integer.parseInt(libroElement.getAttribute("id"));
            String titulo = libroElement.getElementsByTagName("titulo").item(0).getTextContent();
            String autor = libroElement.getElementsByTagName("autor").item(0).getTextContent();
            int anio = Integer.parseInt(libroElement.getElementsByTagName("anio").item(0).getTextContent());
            String genero = libroElement.getElementsByTagName("genero").item(0).getTextContent();

            // Crear un objeto Libro y agregarlo a la lista
            Libro libro = new Libro(id, titulo, autor, anio, genero);
            listaDeLibros.add(libro);
        }

        System.out.println("Libros importados exitosamente.");

    } catch (Exception e) {
        e.printStackTrace();
    }

    return listaDeLibros;
}
public static List<Autor> importarAutoresDesdeXML(String rutaArchivo) {
    List<Autor> listaDeAutores = new ArrayList<>();

    try {
        // Configurar el analizador de documentos XML
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Parsear el documento XML
        Document document = builder.parse(rutaArchivo);

        // Obtener la lista de elementos de autor
        NodeList nodeList = document.getElementsByTagName("autor");

        // Iterar a través de cada elemento de autor y crear objetos Autor
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element autorElement = (Element) nodeList.item(i);
            // Obtener atributos e información del elemento autor
            int id = Integer.parseInt(autorElement.getAttribute("id"));
            String nombre = autorElement.getElementsByTagName("nombre").item(0).getTextContent();
            String nacionalidad = autorElement.getElementsByTagName("nacionalidad").item(0).getTextContent();
            int anioNacimiento = Integer.parseInt(autorElement.getElementsByTagName("anioNacimiento").item(0).getTextContent());

            // Crear un objeto Autor y agregarlo a la lista
            Autor autor = new Autor(id, nombre, nacionalidad, anioNacimiento);
            listaDeAutores.add(autor);
        }

        System.out.println("Autores importados exitosamente.");

    } catch (Exception e) {
        e.printStackTrace();
    }

    return listaDeAutores;
}

public static void exportarLibrosAXML(List<Libro> listaDeLibros, String rutaArchivo) {
    try {
        // Configurar el generador de documentos XML
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Crear el elemento raíz del documento XML
        Element rootElement = doc.createElement("libros");
        doc.appendChild(rootElement);

        // Iterar a través de la lista de libros y agregar elementos al documento XML
        for (Libro libro : listaDeLibros) {
            Element libroElement = doc.createElement("libro");
            // Agregar atributo id al elemento libro
            libroElement.setAttribute("id", String.valueOf(libro.getId()));

            // Agregar elementos hijos al elemento libro (titulo, autor, anio, genero)
            Element tituloElement = doc.createElement("titulo");
            tituloElement.appendChild(doc.createTextNode(libro.getTitulo()));
            libroElement.appendChild(tituloElement);

            Element autorElement = doc.createElement("autor");
            autorElement.appendChild(doc.createTextNode(libro.getAutor()));
            libroElement.appendChild(autorElement);

            Element anioElement = doc.createElement("anio");
            anioElement.appendChild(doc.createTextNode(String.valueOf(libro.getAnioPublicacion())));
            libroElement.appendChild(anioElement);

            Element generoElement = doc.createElement("genero");
            generoElement.appendChild(doc.createTextNode(libro.getGenero()));
            libroElement.appendChild(generoElement);

            // Agregar el elemento libro al elemento raíz
            rootElement.appendChild(libroElement);
        }

        // Guardar el contenido en un archivo XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(rutaArchivo));

        transformer.transform(source, result);

        System.out.println("Libros exportados exitosamente a " + rutaArchivo);

    } catch (Exception e) {
        e.printStackTrace();
    }
}

public static void exportarAutoresAXML(List<Autor> listaDeAutores, String rutaArchivo) {
    try {
        // Configurar el generador de documentos XML
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        // Crear el elemento raíz del documento XML
        Element rootElement = doc.createElement("autores");
        doc.appendChild(rootElement);

        // Iterar a través de la lista de autores y agregar elementos al documento XML
        for (Autor autor : listaDeAutores) {
            Element autorElement = doc.createElement("autor");
            // Agregar atributo id al elemento autor
            autorElement.setAttribute("id", String.valueOf(autor.getId()));

            // Agregar elementos hijos al elemento autor (nombre, nacionalidad, anioNacimiento)
            Element nombreElement = doc.createElement("nombre");
            nombreElement.appendChild(doc.createTextNode(autor.getNombre()));
            autorElement.appendChild(nombreElement);

            Element nacionalidadElement = doc.createElement("nacionalidad");
            nacionalidadElement.appendChild(doc.createTextNode(autor.getNacionalidad()));
            autorElement.appendChild(nacionalidadElement);

            Element anioNacimientoElement = doc.createElement("anioNacimiento");
            anioNacimientoElement.appendChild(doc.createTextNode(String.valueOf(autor.getAnionacimiento())));
            autorElement.appendChild(anioNacimientoElement);

            // Agregar el elemento autor al elemento raíz
            rootElement.appendChild(autorElement);
        }

        // Guardar el contenido en un archivo XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new FileOutputStream(rutaArchivo));

        transformer.transform(source, result);

        System.out.println("Autores exportados exitosamente a " + rutaArchivo);

    } catch (Exception e) {
        e.printStackTrace();
    }
}
}