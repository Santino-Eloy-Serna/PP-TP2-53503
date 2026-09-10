public class Estudiante {
    private String legajo;
    private String nombre;

    public Estudiante(String l, String n) {
        this.legajo = l;
        this.nombre = n;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
