import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class APP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Estudiante> estudiantes = new ArrayList<>();
        int id = 1;
        boolean TER = true;

        while(TER) {
            System.out.println("Ingrese que desea hacer: ");
            System.out.println("Registrar Estudiante (RE)");
            System.out.println("Crear Evento (CE)");
            //Crear un evento implica crear minimo una actividad, asignar una sala e inscribir un estudiante (Por ahora)
            String respuesta = scanner.nextLine().toLowerCase();

            switch (respuesta){
                case "re":
                    boolean CONT = true;

                    while (CONT){
                        System.out.println("Ingrese el nombre del Estudiante: ");
                        String N = scanner.nextLine();
                        System.out.println("Ingrese el legajo del Estudiante: ");
                        String L = scanner.nextLine();

                        estudiantes.add(new Estudiante(L,N));

                        System.out.println("Desea agregar otro estudiante? S/N");
                        String Res = scanner.nextLine().trim().toLowerCase();

                        if (Res.equals("n") || Res.equals("no")){
                            CONT = false;
                        }
                    }

                    System.out.println("Desea hacer algo mas? S/N o Si/No");
                    String Res = scanner.nextLine().trim().toLowerCase();

                    if (Res.equals("n") || Res.equals("no")){
                        TER = false;
                        System.out.println("Hasta luego");
                    }
                    break;
                case "ce":
                    System.out.println("Ingrese el nombre del evento:");
                    String T = scanner.nextLine();
                    System.out.println("Ingrese el costo base del evento:");
                    double costo = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.println("El evento tendra costo para los estudiantes?");
                    String R = scanner.nextLine().trim().toLowerCase();
                    boolean G;
                    G = R.equals("si") || R.equals("S") || R.equals("Si");
                    EventoUniversitario evento = new EventoUniversitario("EVT"+id,T,costo,G);
                    EventoUniversitario evento1 = new EventoUniversitario("EVT"+id,evento);

                    System.out.println("Ingrese el nombre de la sala que se asignara al evento: ");
                    String S = scanner.nextLine();

                    Sala sala = new Sala(id,S);
                    evento.AS(sala);
                    evento1.AS(sala);

                    int idA = 1;
                    boolean CAN = true;

                    while (CAN){
                        System.out.println("Ingrese el nombre de la Actividad: ");
                        String NA = scanner.nextLine();
                        System.out.println("Ingrese el cupo maximo de estudiantes para la Actividad: ");
                        int C = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("La actividad es una Charla o un Taller?");
                        String tipo = scanner.nextLine().trim().toLowerCase();
                        evento.CA(idA,NA,C,tipo);
                        CAN = false;

                        System.out.println("Desea crear otro Actividad para este evento? S/N");
                        String Re = scanner.nextLine().trim().toLowerCase();

                        if (Re.equals("s") || Re.equals("si")){
                            CAN = true;
                            ++idA;
                        }
                    }

                    if (estudiantes.isEmpty()){
                        System.out.println("No hay estudiantes para registrar");
                    } else {
                        CAN = true;

                        while (CAN){
                            System.out.println("Ingrese el legajo del estudiante que dese inscribir: ");
                            String legajo = scanner.nextLine();
                            System.out.println("Ingrese el id de la Actividad a inscribirse: ");
                            int NID = scanner.nextInt();
                            scanner.nextLine();

                            for (Estudiante estudiante : estudiantes){
                                if (estudiante.getLegajo().equals(legajo)){
                                    -- NID;
                                    Actividad actividad = evento.getActividades().get(NID);
                                    actividad.inscribir(estudiante);
                                }
                            }
                            CAN = false;

                            System.out.println("Desea inscribir a otro Estudiante? S/N");
                            String Re = scanner.nextLine().trim().toLowerCase();

                            if (Re.equals("s") || Re.equals("si")){
                                CAN = true;
                            }
                        }
                    }

                    evento.mostrar();
                    evento1.mostrar();

                    System.out.println("La cantidad de eventos son: "+EventoUniversitario.getCantidadEventos());

                    System.out.println("Desea hacer algo mas? S/N o Si/No");
                    Res = scanner.nextLine().trim().toLowerCase();

                    if (Res.equals("n") || Res.equals("no")){
                        TER = false;
                        System.out.println("Hasta luego");
                    }
                    break;
                default:
                    System.out.println("Valor irreconocible, ingrese un valor valido");
            }

            System.out.println("La cantidad de eventos son: "+EventoUniversitario.getCantidadEventos());
        }


    }

}

// CCE = Calculo de Costo Estimado
//AS = Asignar Sala
//CA = Crear Actividad
//Comentario de Prueba