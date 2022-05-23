package prj3;

import java.time.LocalDate;

public class CursColectiu extends Curs {
    
    private int aforament;
    private double preuColectiu;

    public CursColectiu(){
        
    }
    
    public CursColectiu(int aforament, double preuColectiu, int id_curs, String dni_monitor, String nom_curs, LocalDate data_inici, String descripcio, int preu_hora) {
        super(id_curs, dni_monitor, nom_curs, data_inici, descripcio, preu_hora);
        this.aforament = aforament;
        this.preuColectiu = preuColectiu;
    }

    

    public int getAforament() {
        return aforament;
    }

    public void setAforament(int aforament) {
        this.aforament = aforament;
    }

    public double getPreuColectiu() {
        return preuColectiu;
    }

    public void setPreuColectiu(int preuColectiu) {
        this.preuColectiu = preuColectiu;
    }

    @Override
    public String toString() {
        return super.toString() + "aforament: " + aforament + ", preuColectiu=" + preuColectiu + '}';
    }
    
    
}
