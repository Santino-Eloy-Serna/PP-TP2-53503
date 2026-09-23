package Hilos;

import Modelo.Actividades.Actividad;
import Modelo.EventoUniversitario;
import Modelo.Inscripcion;

public class EnvioTicketsThread extends Thread{
    private EventoUniversitario evento;

    public EnvioTicketsThread(EventoUniversitario evento){
        this.evento = evento;
    }

    @Override
    public void run(){
        System.out.println("[Hilo Envio] Iniciando envio de tickets...");

        for (Actividad actividad : evento.getActividades()){
            for (Inscripcion inscripcion : actividad.getInscripciones()){
                if (inscripcion.getEstado().equals("Confirmado") && inscripcion.getTicket() != null){
                    System.out.println("[Hilo Envio] Enviando ticket...");
                    inscripcion.getTicket().enviarTicket();

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e){
                        System.out.println("[Hilo Envio] El envio fue interrumpido");
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        System.out.println("[Hilo Envio] Finalizo el envio de tickets");
    }
}
