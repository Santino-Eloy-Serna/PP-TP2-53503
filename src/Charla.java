public class Charla extends Actividad{
    private String disertante;

    public Charla(int id, String titulo, String disertante, int cupo) {
        super(id, titulo, cupo);
        this.disertante = disertante;
    }

    public String getDisertante() {
        return disertante;
    }

    public void setDisertante(String disertante) {
        this.disertante = disertante;
    }

    @Override
    public double calcularCM(){
        //CM = Costo Materiales
        return (double)0.0F;
    }

    @Override
    public String getTipo(){
        return "charla";
    }
}
