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

    @Override
    public double calcularCM(){
        if (PNB){
            return 5000;
        } else {
            return 2000;
        }
    }

    @Override
    public String getTipo(){
        return "taller";
    }

    @Override
    public String generarCertificado(Estudiante estudiante) {
        return "Certificado de asistencia - Taller: "+getTitulo()+" - Estudiante: "+estudiante.getNombre()+" - Entidad emisora: "+ENTIDAD_EMISORA;
    }
}
