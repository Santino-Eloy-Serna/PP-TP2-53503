import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EventoUniversitario {
    private final String Id;
    private String titulo;
    private double costoBase;
    private boolean gratuito;
    private static int cantidadEventos;
    private List<Actividad> actividades = new ArrayList<>();
    private Sala sala;

    static{
        cantidadEventos = 0;
    }

    public EventoUniversitario(String id, String titulo, double costoBase, boolean gratuito) {
        this.Id = id;
        this.titulo = titulo;
        this.gratuito = gratuito;
        this.costoBase = this.gratuito ? (double)0.0F : costoBase;
        cantidadEventos++;
    }

    public EventoUniversitario(String Id, EventoUniversitario otro) {
        this.Id = Id+"-CLON";
        this.titulo = otro.titulo+"-CLON";
        this.costoBase = otro.costoBase;
        this.gratuito = otro.gratuito;
    }

    /*La funcion del segundo constructor es para "copiar" un objeto ya existente.
     * Un explicacion resumida seria: Que este nuevo contructor toma todo un objeto ya creado
     * como el objeto Fiesta o conferencia, y lo copia de forma excata, creando un objeto identico
     * DEBO PROFUNDIZAR MAS EN ESTA "TECNICA"*/

    public String getId() {
        return Id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public double getCostoBase() {
        return costoBase;
    }

    public void setCostoBase(double costoBase) {
        this.costoBase = costoBase;
    }

    public boolean isGratuito() {
        return gratuito;
    }

    public void setGratuito(boolean gratuito) {
        this.gratuito = gratuito;
    }

    public static int getCantidadEventos() {
        return cantidadEventos;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public static void setCantidadEventos(int cantidadEventos) {
        EventoUniversitario.cantidadEventos = cantidadEventos;
    }

    public double CCE(){
        if (this.gratuito) {
            return 0.0;
        }

        double CT = costoBase;

        for (Actividad actividad : actividades) {
            CT += actividad.calcularCM();
        }

        return CT * 1.21;
    }
    // CCE = Calculo de Costo Estimado

    public void AS(Sala sala){
        setSala(sala);
        System.out.println("Se asigno la sala "+sala.getId()+" al evento "+titulo);
    }
    //AS = Asignar Sala

    public void CA(int id, String titulo, int cupo, String tipo){

        Scanner scanner = new Scanner(System.in);

        switch (tipo) {
            case "charla":
                System.out.println("Ingrese el nombre del Disertante: ");
                String D = scanner.nextLine();
                Actividad charla = new Charla(id,titulo,D,cupo);
                this.actividades.add(charla);
                System.out.println("Se creo una actividad de tipo "+tipo+" en el evento "+titulo);
                break;
            case "taller":
                System.out.println("El Taller "+titulo+" requiere uso de NoteBook? (S/N)");
                String respuesta = scanner.nextLine().trim().toLowerCase();
                boolean PNB = respuesta.equals("s") || respuesta.equals("si");
                Actividad taller = new Taller(id,titulo,PNB,cupo);
                this.actividades.add(taller);
                System.out.println("Se creo una actividad de tipo "+tipo+" en el evento "+titulo);
                break;
            default:
                System.out.println("Error: Actividad solicitada no encontrada");
        }
    }
    //CA = Crear Actividad

    public void mostrar(){
        System.out.println("Evento codigo=" + Id);
        System.out.println("TÍtulo=" + titulo);
        System.out.println("Costo=" + this.CCE());
        System.out.println("Sala asignada: " + (sala != null ? sala.getNombre() : "Sin sala")+"\n");
        System.out.println("Actividades:");
        for (Actividad actividad : actividades) {
            actividad.MI();
            actividad.mostrarInscripciones();
        }
    }
}

//Comentario de Prueba