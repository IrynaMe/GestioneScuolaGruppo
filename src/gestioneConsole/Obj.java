package gestioneConsole;

import java.time.LocalDate;

public class Obj {//per salvare valori return di inserimento
    Integer numero;
    String stringa;
    LocalDate data;
    String sesso;
    String lettera;
    String codiceFiscale;
    String mail;

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public void setStringa(String stringa) {
        this.stringa = stringa;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setSesso(String sesso) {
        this.sesso = sesso;
    }

    public void setLettera(String lettera) {
        this.lettera = lettera;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getNumero() {
        return numero;
    }

    public String getStringa() {
        return stringa;
    }

    public LocalDate getData() {
        return data;
    }

    public String getSesso() {
        return sesso;
    }

    public String getLettera() {
        return lettera;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public String getMail() {
        return mail;
    }
}