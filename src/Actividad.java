import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/*Debido a que cambie la clase Actividad a una clase Abstracta, tengo que reestructurar el codigo y en el Main
 * corregir las partes donde el codigo toma a la clase como no abstracta (Yo me entiendo)*/

public abstract class Actividad {
    private int id;
    private String titulo;
    private int cupoMax;
    private final int cupoMin = 1;
    private List<Inscripcion> inscripciones = new ArrayList<>();

    public Actividad(int id, String titulo, int cupo) {
        this.id = id;
        this.titulo = titulo;
        this.cupoMax = Math.max(cupo, cupoMin);
        this.inscripciones = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getCupoMax() {
        return cupoMax;
    }

    public void setCupoMax(int cupoMax) {
        this.cupoMax = cupoMax;
    }

    public int getCupoMin() {
        return cupoMin;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public Inscripcion inscribir(Estudiante estudiante){
        Inscripcion inscripto = new Inscripcion();

        inscripto.setEstudiante(estudiante);
        inscripto.setEstado("Habilitado");
        inscripto.setFecha(LocalDate.now());

        inscripciones.add(inscripto);

        return inscripto;
    }

    public void mostrarInscripciones(){
        for (Inscripcion i : inscripciones){
            System.out.println("Estudiante: " +i.getEstudiante().getNombre());
            System.out.println("Legajo: " +i.getEstudiante().getLegajo());
            System.out.println("Fecha de Inscripcion: " +i.getFecha());
            System.out.println("Estado: " +i.getEstado());
            System.out.println();
        }
    }

    public final void MI(){
        //MI = Mostrar Identificacion
        System.out.println("- " + getTipo() + ": "+titulo+" (id="+id+") - Cupo máximo: "+cupoMax);
    }

    public abstract double calcularCM();

    public abstract String getTipo();
}
