package Modelo.Actividades;

import Modelo.Certificacion.Certificable;
import Modelo.Estudiante;

public class Taller extends Actividad implements Certificable {
    private  boolean PNB; //PNB = Pide NoteBook

    public Taller(int id, String titulo, boolean PNB, int cupo) {
        super(id, titulo, cupo);
        this.PNB = PNB;
    }

    public boolean isPNB() {
        return PNB;
    }

    public void setPNB(boolean PNB) {
        this.PNB = PNB;
    }

    public double calcularCM(){
        if (PNB){
            return 5000;
        } else {
            return 2000;
        }
    }

    public String getTipo(){
        return "taller";
    }

    public String generarCertificado(Estudiante estudiante) {
        return "Certificado de asistencia - Curso: "+getTitulo()+" - Estudiante: "+estudiante.getNombre()+" - Entidad emisora: "+ENTIDAD_EMISORA;
    }
}
