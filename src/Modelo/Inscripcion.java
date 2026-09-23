package Modelo;

import java.io.Serializable;
import java.time.LocalDate;

public class Inscripcion implements Serializable {
    private LocalDate fecha;
    private String estado;
    private Estudiante estudiante;
    private TA ticket;
    //TA = Ticket de Acceso

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public TA getTicket() {
        return ticket;
    }

    public void confirmar() {
        this.estado = "Confirmado";
    }

    public void generarTicket(){
        if (estado.equals("Confirmado")){
            this.ticket =new TA("TICKET-"+estudiante.getLegajo(),LocalDate.now());
        }
    }
}

public class TA implements Serializable {
    private String idTicket;
    private LocalDate fechaEmision;

    public TA(String idTicket, LocalDate fechaEmision){
        this.idTicket = idTicket;
        this.fechaEmision = fechaEmision;
    }

    public void enviarTicket() {
        System.out.println("Enviado ticket "+idTicket+" al estudiante "+estudiante.getNombre()+" - fecha: "+fechaEmision);
    }
}
