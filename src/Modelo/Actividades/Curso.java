package Modelo.Actividades;

import Modelo.Certificacion.Certificable;
import Modelo.Estudiante;

public class Curso extends Actividad implements Certificable {

    private int nivel;

    public Curso(int id, String titulo, int nivel, int cupo) {
        super(id, titulo, cupo);
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public double calcularCM() {
        return 3000;
    }

    public String getTipo() {
        return "curso";
    }

    public String generarCertificado(Estudiante estudiante) {
        return "Certificado de asistencia - Curso: "+getTitulo()+" - Estudiante: "+estudiante.getNombre()+" - Entidad emisora: "+ENTIDAD_EMISORA;
    }
}