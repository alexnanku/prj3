package prj3;

import java.time.LocalDate;

public class CursCompetitiu extends Curs {

    private String nivell;
    private int preuCompeticio;

    public CursCompetitiu(String nivell, int preuCompeticio, int id_curs, String dni_monitor, String nom_curs, LocalDate data_inici, String descripcio, int preu_hora) {
        super(id_curs, dni_monitor, nom_curs, data_inici, descripcio, preu_hora);
        this.nivell = nivell;
        this.preuCompeticio = preuCompeticio;
    }   

    public String getNivell() {
        return nivell;
    }

    public void setNivell(String nivell) {
        this.nivell = nivell;
    }

    public int getPreuCompeticio() {
        return preuCompeticio;
    }

    public void setPreuCompeticio(int preuCompeticio) {
        this.preuCompeticio = preuCompeticio;
    }

    @Override
    public String toString() {
        return super.toString() + "nivell=" + nivell + ", preuCompeticio=" + preuCompeticio + '}';
    }
    
    

}
