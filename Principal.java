package EjercicioEvaluable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GuardedObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//Importar otras clases necesarias
public class Principal {
private static Scanner scanner = new Scanner(System.in);
File fichero = new File("C:/Libros.ser");
private static GestorFicheros gestorFicheros = new
GestorFicheros();
public static void main(String[] args) {
boolean salir = false;
while (!salir) {
mostrarMenu();
int opcion = scanner.nextInt();
switch (opcion) {
case 1:
//Gestionar libros
gestionarLibros();
break;
case 2:
//Gestionar autores
gestionarAutores();
break;
case 3:
//Gestionar préstamos
gestionarPrestamos();
break;
case 4:
//Exportar/Importar datos con XML
gestionarExportImportXML();
break;
case 5:
salir = true;
break;
default:
System.out.println("Opción no válida. Por favor, intente de nuevo.");
}
}
}
// metodos para mostrar los menus
private static void mostrarMenu() {
System.out.println("Bienvenido al Sistema de Gestión de Biblioteca");
System.out.println("1. Gestionar Libros");
System.out.println("2. Gestionar Autores");
System.out.println("3. Gestionar Préstamos");
System.out.println("4. Exportar/Importar Datos (XML)");
System.out.println("5. Salir");
System.out.print("Seleccione una opción: ");
}
private static void mostrarMenuLibros() {
System.out.println("Bienvenido al Sistema de Gestión de Libros");
System.out.println("1. Añadir Libros");
System.out.println("2. Leer Libros");
System.out.println("3. Actualizar Libros");
System.out.println("4. Eliminar Libros");
System.out.println("5. Salir");
System.out.print("Seleccione una opción: ");
}
private static void mostrarMenuAutores() {
System.out.println("Bienvenido al Sistema de Gestión de Autores");
System.out.println("1. Añadir Autores");
System.out.println("2. Leer Autores");
System.out.println("3. Actualizar Autores");
System.out.println("4. Eliminar Autores");
System.out.println("5. Salir");
System.out.print("Seleccione una opción: ");
}
private static void mostrarMenuPrestamos() {
System.out.println("Bienvenido al Sistema de Gestión de Prestamos");
System.out.println("1. Añadir Prestamo");
System.out.println("2. Leer Prestamos");
System.out.println("3. Salir");
System.out.print("Seleccione una opción: ");
}
private static void mostrarMenuXML() {
System.out.println("Bienvenido al Sistema de Gestión de importación o exportación de XML");
System.out.println("1. Exportar XML");
System.out.println("2. Importar XML");
System.out.println("3. Salir");
System.out.print("Seleccione una opción: ");
}

// metodo que se encarga de gestionar todo lo relacionado con los libros
private static void gestionarLibros() {
    boolean salir2 = false;
// 
    while (!salir2) {
        mostrarMenuLibros();
        int opcion2 = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después de la opción

        switch (opcion2) {
            case 1: {// opción para añadir libros
                List<Libro> librosUsuario = Libro.crearLibrosDesdeEntradaUsuario();
                // se muestra la lista para verificar su contenido antes de añadirla al archivo
                for (Libro libro : librosUsuario) {
                    System.out.println(libro);
                }// se añade al archivo
               gestorFicheros.guardarLibrosBinario(librosUsuario, "C:/Libros.ser");
                break; 
            }
            case 2: { // opción para leer libros
            	 System.out.println("Los libros dentro del archivo binario son: ");
            	
				List<Libro> LibrosLeido = null;
				try {// se recupera la lista de libros del archivo
					LibrosLeido = gestorFicheros.leerLibrosBinario("C:/Libros.ser");
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				// se lee la lista 
            	 for (Libro libro : LibrosLeido) {
                     System.out.println(libro);
                 }
				
                break;
            }
            case 3: { //opción para actualizar libros
            	  System.out.println("Seleciona el id del libro que quieres cambiar:");
            	  int idseleccionar = scanner.nextInt();
			try { // se llama al metodo que actualiza los libros
				Libro.actualizarLibroPorId("C:/Libros.ser", idseleccionar );
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
            }
                break;
            
            case 4: {// opción para eliminar libros 
            		
            	 System.out.println("Seleciona el id del libro que quieres eliminar:");
           	  int idseleccionar2 = scanner.nextInt();
            	try {	// se llama al metodo que elimina los libros
					Libro.eliminarLibroPorId("C:/Libros.ser", idseleccionar2);
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	break;
            }
            case 5: {
                salir2 = true;
                break;
            }
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
    }

		
	
}
//metodo que se encarga de gestionar todo lo relacionado con los autores
private static void gestionarAutores() { 
    boolean salir3 = false;

    while (!salir3) {
        mostrarMenuAutores();
        int opcion3 = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después de la opción

        switch (opcion3) {
            case 1: { // opcion para añadir autores 
                List<Autor> autoresUsuario = Autor.crearAutoresDesdeEntradaUsuario();
                // se muestra la lista para verificar el contenido
                for (Autor autor : autoresUsuario) {
                    System.out.println(autor);
                }
                // se añade al archivo
                gestorFicheros.guardarAutoresBinario(autoresUsuario, "C:/Autores.ser");
                break;
            }
            case 2: { // opcion para leer autores
            	 System.out.println("Los Autores dentro del archivo binario son: ");
             	
 				List<Autor> AutoresLeido = null;
 				try {// se obtienen los datos del archivo y se meten en una lista 
 					AutoresLeido = gestorFicheros.leerAutoresBinario("C:/Autores.ser");
 				} catch (ClassNotFoundException | IOException e) {
 					// TODO Auto-generated catch block
 					e.printStackTrace();
 				}
 				// se muestran los datos por pantalla
             	 for (Autor autor : AutoresLeido) {
                      System.out.println(autor);
                  }
 				
                 break;               
            }
            case 3: { // opcion para actualizar autores
            	System.out.println("Seleciona el id del autor que quieres cambiar:");
          	  int idseleccionar = scanner.nextInt();
			try { // se llama al metodo que actualiza los autores
				Autor.actualizarAutorPorId("C:/Autores.ser", idseleccionar );
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
              break;                
            }
            case 4: {// opcion para eliminar autores
           	 System.out.println("Seleciona el id del libro que quieres eliminar:");
          	  int idseleccionar2 = scanner.nextInt();
           	try { // se llama al metodo que elimina los autores 
					Autor.eliminarAutorPorId("C:/Autores.ser", idseleccionar2);
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
           	break;                
            }
            case 5: {
                salir3 = true;
                break;
            }
            default:
                System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
    }

}
//metodo que se encarga de gestionar todo lo relacionado con los prestamos 
private static void gestionarPrestamos() {
	    boolean salir4 = false;

	    while (!salir4) {
	        mostrarMenuPrestamos();
	        int opcion4 = scanner.nextInt();
	        scanner.nextLine(); // Consumir la nueva línea después de la opción
	        
	        switch (opcion4) {
	            case 1: {// opción para añadir prestamos 
	            	List<Prestamo> prestamosUsuario = Prestamo.crearPrestamosDesdeEntradaUsuario();
	            	// se muestra la lista para comprobar que todo sta correcto
                    for (Prestamo prestamo : prestamosUsuario) {
                        System.out.println(prestamo);
                    }
                    try {// se añade al archivo
						gestorFicheros.guardarPrestamosTexto(prestamosUsuario, "C:/Prestamos.txt");
					} catch (IOException e) {
						e.printStackTrace();
					}
                    break;

	            }
	            case 2: {// opción para leer los prestamos 
	            	  System.out.println("Los préstamos dentro del archivo de texto son: ");
	            	  	// se obtiene la lista del archivo
	                    List<Prestamo> prestamosLeidos = gestorFicheros.leerPrestamosTexto("C:/Prestamos.txt");
	                    // se muestra por pantalla
	                    for (Prestamo prestamo : prestamosLeidos) {
	                        System.out.println(prestamo);
	                    }
	                 break;               
	            }
	            
	            case 3: {
	                salir4 = true;
	                break;
	            }
	            default:
	                System.out.println("Opción no válida. Inténtalo de nuevo.");
	        }
	    }

	}
//metodo que se encarga de gestionar todo lo relacionado con la exportación e importación sobre XML
private static void gestionarExportImportXML() {
	boolean salir5 = false;

    while (!salir5) {
        mostrarMenuXML();
        int opcion5 = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea después de la opción

        switch (opcion5) {
        // exportar a xml
        case 1:{
        	List<Libro> LibrosLeido = null;
        	List<Autor> AutoresLeido = null;
        	// se leen las listas de autores y libros que estan en los archivos binarios y se muestran por pantalla para 
        	// ver que todo esta en orden 
				try {
					AutoresLeido = gestorFicheros.leerAutoresBinario("C:/Autores.ser");
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				
         	 for (Autor autor : AutoresLeido) {
                  System.out.println(autor);
              }
         	 
         	
			try {
				LibrosLeido = gestorFicheros.leerLibrosBinario("C:/Libros.ser");
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
        	 for (Libro libro : LibrosLeido) {
                 System.out.println(libro);
             }
        	 // se exportan a sus respectivos xml 
         	 GestorFicheros.exportarAutoresAXML(AutoresLeido, "C:/Autores.xml");
         	 GestorFicheros.exportarLibrosAXML(LibrosLeido, "C:/Libros.xml");
        }
        	break;
        	
        case 2:{

           
        	// se recuperan los datos del xml y se almacenan en listas
            List<Libro> libros = GestorFicheros.importarLibrosDesdeXML("C:/Libros.xml");
            System.out.println("Libros importados del XML:");
            // se muestra la lista de libros
            for (Libro libro : libros) {
                System.out.println(libro);
            }
            
            List<Autor> autores = GestorFicheros.importarAutoresDesdeXML("C:/Autores.xml");

            // se muestra la lista de autores
            for (Autor autor : autores) {
                System.out.println(autor);
            }
            
            GestorFicheros.guardarLibrosBinario(libros, "C:/Libros.ser");
            GestorFicheros.guardarAutoresBinario(autores, "C:/Autores.ser");
            
        	break;}
        	
        case 3 :{
        	 salir5 = true;
        }
        	break;
        
        default:
        	System.out.println("Opción no valida. Inténtalo de nuevo.");
        	
}


}

}
}
