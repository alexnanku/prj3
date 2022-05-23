package prj3;

import java.time.LocalDate;

public class Curs {
    
    private int id_curs;
    private String dni_monitor;
    private String nom_curs;
    private LocalDate data_inici;
    private String descripcio;
    private int preu_hora;
    
    public Curs(){
        
    }
    
    public Curs(int id_curs, String dni_monitor, String nom_curs, LocalDate data_inici, String descripcio, int preu_hora) {
        this.id_curs = id_curs;
        this.dni_monitor = dni_monitor;
        this.nom_curs = nom_curs;
        this.data_inici = data_inici;
        this.descripcio = descripcio;
        this.preu_hora = preu_hora;
    }

    public int getId_curs() {
        return id_curs;
    }

    public void setId_curs(int id_curs) {
        this.id_curs = id_curs;
    }

    public String getDni_monitor() {
        return dni_monitor;
    }

    public void setDni_monitor(String dni_monitor) {
        this.dni_monitor = dni_monitor;
    }

    public String getNom_curs() {
        return nom_curs;
    }

    public void setNom_curs(String nom_curs) {
        this.nom_curs = nom_curs;
    }

    public LocalDate getData_inici() {
        return data_inici;
    }

    public void setData_inici(LocalDate data_inici) {
        this.data_inici = data_inici;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public int getPreu_hora() {
        return preu_hora;
    }

    public void setPreu_hora(int preu_hora) {
        this.preu_hora = preu_hora;
    }

    @Override
    public String toString() {
        return "Curs{" + "id_curs=" + id_curs + ", dni_monitor=" + dni_monitor + ", nom_curs=" + nom_curs + ", data_inici=" + data_inici + ", descripcio=" + descripcio + ", preu_hora=" + preu_hora + '}';
    }
    
}


