/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package todoagenda;

import contenidos.ElementoAgenda;
import usuarios.GestorUsuario;
import usuarios.Usuario;
import contenidos.Tarea;
import contenidos.Evento;
import enumeraciones.Tipo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


public class App implements AppInterface {
    private Usuario usuarioActivo;
    private GestorUsuario gestorUsuarios;
    private Map<String, Agenda> agendasPorUsuario;
    private Scanner scanner;

    public App() {
        this.gestorUsuarios = new GestorUsuario(BuilderUsuario.crearUsuarios());
        this.agendasPorUsuario = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        int resultadoLogin;
        do {
            resultadoLogin = this.verificarYRegistrarUsuario();
        } while (resultadoLogin != 1);

        this.menu();
    }

    private int verificarYRegistrarUsuario() {
        System.out.println("Ingrese su nombre:");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese su contraseña:");
        String contraseña = scanner.nextLine();

        if (!gestorUsuarios.usuarioExiste(nombre, contraseña)) {
            System.out.println("El usuario no existe. ¿Desea registrarse? (S/N)");
            String respuesta = scanner.nextLine();
            if (respuesta.equalsIgnoreCase("S")) {
                gestorUsuarios.registrarUsuario(nombre, contraseña);
                agendasPorUsuario.put(nombre, new Agenda());
            } else {
                return 0; 
            }
        }

        usuarioActivo = new Usuario(nombre, contraseña);
        return 1; 
    }

    private void menu() {
        Agenda miAgenda = agendasPorUsuario.get(usuarioActivo.getNombre());
        boolean salir = false;

        while (!salir) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Gestionar contactos");
            System.out.println("2. Agregar elemento");
            System.out.println("3. Eliminar elemento");
            System.out.println("4. Modificar elemento");
            System.out.println("5. Mostrar agenda");
            System.out.println("6. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer del scanner

            switch (opcion) {
                case 1:
                    miAgenda.getContactoAgenda().administrarContactos();
                    break;
                case 2:
                    agregarElemento(miAgenda);
                    break;
                case 3:
                    eliminarElemento(miAgenda);
                    break;
                case 4:
                    modificarElemento(miAgenda);
                    break;
                case 5:
                    mostrarAgenda(miAgenda);
                    break;
                case 6:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }


    private void agregarElemento(Agenda agenda) {
    System.out.println("¿Desea agregar un Evento (E) o una Tarea (T)?");
    String tipoElemento = scanner.nextLine();

    System.out.println("Ingrese el título:");
    String titulo = scanner.nextLine();

    System.out.println("Ingrese anotaciones:");
    String anotaciones = scanner.nextLine();

    System.out.println("Ingrese el tipo (TRABAJO, ESCUELA, OTROS):");
    Tipo tipo = Tipo.valueOf(scanner.nextLine().toUpperCase());

    
    
    //NOSE QUE HICE ME DABA ERROR Y LE HE DADO A CORREGIR DE LA BOMBILLITA AHORA NOSE SI ESTA BIEM
    if (tipoElemento.equalsIgnoreCase("E")) {
        System.out.println("Ingrese la fecha del evento (formato YYYY-MM-DD):");
        LocalDate fecha = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Evento evento = new Evento(titulo, anotaciones, tipo, fecha);
        agenda.agregarElemento(evento);
    } else if (tipoElemento.equalsIgnoreCase("T")) {
        LocalDate fechaVencimiento = null;
        boolean esPrioritaria = false;
        LocalDate fechaRecordatorio = null;

        System.out.println("¿Tiene fecha de vencimiento? (S/N)");
        if (scanner.nextLine().trim().equalsIgnoreCase("S")) {
            System.out.println("Ingrese la fecha de vencimiento (formato YYYY-MM-DD):");
            fechaVencimiento = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        System.out.println("¿Es una tarea prioritaria? (S/N)");
        esPrioritaria = scanner.nextLine().trim().equalsIgnoreCase("S");

        System.out.println("¿Tiene fecha de recordatorio? (S/N)");
        if (scanner.nextLine().trim().equalsIgnoreCase("S")) {
            System.out.println("Ingrese la fecha de recordatorio (formato 2000-02-01):");
            fechaRecordatorio = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }

        Tarea tarea = new Tarea(titulo, anotaciones, tipo, fechaVencimiento, esPrioritaria, fechaRecordatorio);
        agenda.agregarElemento(tarea);
    } else {
        System.out.println("Tipo de elemento no reconocido.");
    }
}


  
  //aqui no pregunto que tipo de evento porque como se elimina por titulo 
private void eliminarElemento(Agenda agenda) {
    System.out.println("Ingrese el título del elemento a eliminar:");
    String titulo = scanner.nextLine();
    boolean eliminado = agenda.eliminarElemento(titulo);
    if (eliminado) {
        System.out.println("Elemento eliminado con éxito.");
    } else {
        System.out.println("No se encontró un elemento con ese título.");
    }
}

     private  void modificarElemento(Agenda agenda) {
        System.out.println("Ingrese el título del elemento a modificar:");
        String titulo = scanner.nextLine();

        ElementoAgenda elemento = agenda.buscarElementoPorTitulo(titulo);
        if (elemento == null) {
            System.out.println("No se encontró un elemento con ese título.");
            return;
        }

        if (elemento instanceof Evento) {
            mostrarMenuModificarEvento((Evento) elemento);
        } else if (elemento instanceof Tarea) {
            mostrarMenuModificarTarea((Tarea) elemento);
        }
    }

     
    private  void mostrarMenuModificarEvento(Evento evento) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("Seleccione el atributo del evento a modificar:");
            System.out.println("1. Título");
            System.out.println("2. Anotaciones");
            System.out.println("3. Fecha");
            System.out.println("4. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer del scanner

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nuevo título:");
                    evento.setTitulo(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Ingrese nuevas anotaciones:");
                    evento.setAnotaciones(scanner.nextLine());
                    break;
                case 3:
                    System.out.println("Ingrese la nueva fecha (formato 2000-02-01):");
                    try {
                        evento.setFecha(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    } catch (DateTimeParseException e) {
                        System.out.println("no,valido,otra vez");
                    }
                    break;
                case 4:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void mostrarMenuModificarTarea(Tarea tarea) {
        boolean continuar = true;
        while (continuar) {
            System.out.println("Seleccione el atributo de la tarea a modificar:");
            System.out.println("1. Título");
            System.out.println("2. Anotaciones");
            System.out.println("3. Fecha de Vencimiento");
            System.out.println("4. Prioridad");
            System.out.println("5. Fecha de Recordatorio");
            System.out.println("6. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer del scanner

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el nuevo título:");
                    tarea.setTitulo(scanner.nextLine());
                    break;
                case 2:
                    System.out.println("Ingrese nuevas anotaciones:");
                    tarea.setAnotaciones(scanner.nextLine());
                    break;
                case 3:
                    System.out.println("Ingrese la nueva fecha de vencimiento (formato 2000-02-01):");
                    try {
                        tarea.setFechaVencimiento(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    } catch (DateTimeParseException e) {
                        System.out.println("Formato de fecha inválido. Intente de nuevo.");
                    }
                    break;
                case 4:
                    System.out.println("¿Es la tarea prioritaria? (S/N)");
                    tarea.setEsPrioritaria(scanner.nextLine().trim().equalsIgnoreCase("S"));
                    break;
                case 5:
                    System.out.println("Ingrese la nueva fecha de recordatorio (formato 2000-02-01):");
                    try {
                        tarea.setFechaRecordatorio(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    } catch (DateTimeParseException e) {
                        System.out.println("Opción no válida, intentalo otra vez");
                    }
                    break;
                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
    
    
    
    private void mostrarAgenda(Agenda agenda) {
    boolean continuar = true;
    while (continuar) {
        System.out.println("Seleccione cómo desea visualizar la agenda:");
        System.out.println("1. Visualizar todo (Eventos y Tareas)");
        System.out.println("2. Visualizar solo Eventos o Tareas");
        System.out.println("3. Visualizar tareas y eventos para hoy");
        System.out.println("4. Visualizar eventos y tareas para un día específico");
        System.out.println("5. Salir");

        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        switch (opcion) {
            case 1:
                agenda.mostrarElementos();
                break;
            case 2:
                mostrarSoloEventosOTareas(agenda);
                break;
            case 3:
                mostrarEventosTareasHoy(agenda);
                break;
            case 4:
                mostrarEventosTareasDiaEspecifico(agenda);
                break;
            case 5:
                continuar = false;
                break;
            default:
                System.out.println("Opción no válida.");
        }
              
    }
}

    private void mostrarSoloEventosOTareas(Agenda agenda) {
    System.out.println("¿Desea visualizar Eventos (E) o Tareas (T)?");
    String tipo = scanner.nextLine();

    if (tipo.equalsIgnoreCase("E")) {
        agenda.mostrarElementosTipo("Evento");
    } else if (tipo.equalsIgnoreCase("T")) {
        agenda.mostrarElementosTipo("Tarea");
    } else {
        System.out.println("Opción no válida.");
    }
}

    
    private static void mostrarEventosTareasHoy(Agenda agenda) {
    LocalDate hoy = LocalDate.now();
    agenda.mostrarElementosFecha(hoy);
}

    
    private  void mostrarEventosTareasDiaEspecifico(Agenda agenda) {
    System.out.println("Ingrese la fecha (formato 2000-01-12):");
    LocalDate fecha;
    try {
        fecha = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        agenda.mostrarElementosFecha(fecha);
    } catch (DateTimeParseException e) {
        System.out.println("no es correcto el formato");
    }
}

    
    
    
}
