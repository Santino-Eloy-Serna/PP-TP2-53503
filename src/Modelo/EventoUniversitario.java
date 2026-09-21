package Modelo;

import Modelo.Actividades.Actividad;
import Modelo.Actividades.Charla;
import Modelo.Actividades.Taller;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.Serializable;

public class EventoUniversitario implements Serializable{
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
    //AS = Asignar Modelo.Sala

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
                System.out.println("El Actividades.Taller "+titulo+" requiere uso de NoteBook? (S/N)");
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
    //CA = Crear Modelo.Actividades.Actividad

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

    public boolean persistirEvento(){
        String NA = "evento-"+Id+".dat";
        //NA = Nombre Archivo

        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(NA))){

            salida.writeObject(this);

            System.out.println("Evento Persistido");

            return true;
        } catch (FileNotFoundException e){
            System.out.println("Error: No se pudo crear/abrir el archivo");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida al persistir");
        }

        return false;
    }

    public EventoUniversitario recuperarEvento(String id) {
        String NA = "evento-" + id + ".dat";

        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(NA))) {

            EventoUniversitario evento = (EventoUniversitario) entrada.readObject();

            System.out.println("Evento recuperado correctamente.");
            return evento;

        } catch (FileNotFoundException e) {
            System.out.println("Error: no existe el archivo del evento");
        } catch (IOException e) {
            System.out.println("Error de entrada/salida al recuperar el event");
        } catch (java.lang.ClassNotFoundException e) {
            System.out.println("Error: No se encontró la clase del objeto");
        }

        return null;
    }
}

//Comentario de Prueba