package prj3;

import java.time.LocalDate;
import java.util.Objects;

public class Client {
    
    private String dni;
    private String nom;
    private String cognom;
    private String cognom2;
    private LocalDate datanaixemnt;
    private String email;
    private String sexe;
    private String usuari; 
    private String contrasenya;
    
 
    // Els local date li passo Client cli = new Client("", "", LocalDate.parse("aaaa-mm-dd"));

    public Client(){
        
    }
    
    public Client(String dni, String nom, String cognom, String cognom2, LocalDate datanaixemnt, String email, String sexe, String usuari, String contrasenya) {
        this.dni = dni;
        this.nom = nom;
        this.cognom = cognom;
        this.cognom2 = cognom2;
        this.datanaixemnt = datanaixemnt;
        this.email = email;
        this.sexe = sexe;
        this.usuari = usuari;
        this.contrasenya = contrasenya;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        this.cognom2 = cognom2;
    }

    public LocalDate getDatanaixemnt() {
        return datanaixemnt;
    }

    public void setDatanaixemnt(LocalDate datanaixemnt) {
        this.datanaixemnt = datanaixemnt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getUsuari() {
        return usuari;
    }

    public void setUsuari(String usuari) {
        this.usuari = usuari;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.dni);
        hash = 23 * hash + Objects.hashCode(this.nom);
        hash = 23 * hash + Objects.hashCode(this.cognom);
        hash = 23 * hash + Objects.hashCode(this.cognom2);
        hash = 23 * hash + Objects.hashCode(this.datanaixemnt);
        hash = 23 * hash + Objects.hashCode(this.email);
        hash = 23 * hash + Objects.hashCode(this.sexe);
        hash = 23 * hash + Objects.hashCode(this.usuari);
        hash = 23 * hash + Objects.hashCode(this.contrasenya);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Client other = (Client) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.cognom, other.cognom)) {
            return false;
        }
        if (!Objects.equals(this.cognom2, other.cognom2)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.sexe, other.sexe)) {
            return false;
        }
        if (!Objects.equals(this.usuari, other.usuari)) {
            return false;
        }
        if (!Objects.equals(this.contrasenya, other.contrasenya)) {
            return false;
        }
        return Objects.equals(this.datanaixemnt, other.datanaixemnt);
    }

    @Override
    public String toString() {
        return "Client{" + "dni=" + dni + ", nom=" + nom + ", cognom=" + cognom + ", cognom2=" + cognom2 + ", datanaixemnt=" + datanaixemnt + ", email=" + email + ", sexe=" + sexe + ", usuari=" + usuari + ", contrasenya=" + contrasenya + '}';
    }
    

    
}
