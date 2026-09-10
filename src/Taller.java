public class Taller extends Actividad{
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
}
