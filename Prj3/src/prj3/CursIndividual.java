package prj3;

import java.time.LocalDate;

public class CursIndividual extends Curs {
    
    private int preuHora;

    public CursIndividual(int preuHora, int id_curs, String dni_monitor, String nom_curs, LocalDate data_inici, String descripcio, int preu_hora) {
        super(id_curs, dni_monitor, nom_curs, data_inici, descripcio, preu_hora);
        this.preuHora = preuHora;
    }


    public int getPreuHora() {
        return preuHora;
    }

    public void setPreuHora(int preuHora) {
        this.preuHora = preuHora;
    }

    @Override
    public String toString() {
        return super.toString() + "preuHora=" + preuHora + '}';
    }
}
