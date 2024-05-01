package gestioneConsole;

public class VotoMenu {
    private String nomeVotoMenu;
    public TipoDiData tipoDiData;
    String rangeNumeroMin, rangeNumeroMax;// Da specificare range inserimento numero con setter
    Integer rangeStringaMinLength, rangeStringaMaxLength;//Da specificare lunghezza stringa con setter
    String rangeLetteraInizio, rangeLetteraFine;//da specificare range lettere con setter
    Integer rangeMinYear, rangeMaxYear;//da specificare range anni con setter
    String minHour, maxHour; //da specificare range anni con setter


    public VotoMenu(String nomeVotoMenu, TipoDiData tipoDiData) {
        this.nomeVotoMenu = nomeVotoMenu;
        this.tipoDiData = tipoDiData;
    }

    public String getRangeNumeroMin() {
        return rangeNumeroMin;
    }

    public String getRangeNumeroMax() {
        return rangeNumeroMax;
    }

    public Integer getRangeStringaMinLength() {
        return rangeStringaMinLength;
    }

    public Integer getRangeStringaMaxLength() {
        return rangeStringaMaxLength;
    }

    public String getRangeLetteraInizio() {
        return rangeLetteraInizio;
    }

    public String getRangeLetteraFine() {
        return rangeLetteraFine;
    }

    public Integer getRangeMinYear() {
        return rangeMinYear;
    }

    public Integer getRangeMaxYear() {
        return rangeMaxYear;
    }

    public String getMinHour() {return minHour;}

    public String getMaxHour() {return maxHour;}

    public void setRangeNumeroMin(String rangeNumeroMin) {
        this.rangeNumeroMin = rangeNumeroMin;
    }

    public void setRangeNumeroMax(String rangeNumeroMax) {
        this.rangeNumeroMax = rangeNumeroMax;
    }

    public void setRangeStringaMinLength(Integer rangeStringaMinLength) {
        this.rangeStringaMinLength = rangeStringaMinLength;
    }

    public void setRangeStringaMaxLength(Integer rangeStringaMaxLength) {
        this.rangeStringaMaxLength = rangeStringaMaxLength;
    }

    public void setRangeLetteraInizio(String rangeLetteraInizio) {
        this.rangeLetteraInizio = rangeLetteraInizio;
    }

    public void setRangeLetteraFine(String rangeLetteraFine) {
        this.rangeLetteraFine = rangeLetteraFine;
    }

    public void setRangeMinYear(Integer rangeMinYear) {
        this.rangeMinYear = rangeMinYear;
    }

    public void setRangeMaxYear(Integer rangeMaxYear) {
        this.rangeMaxYear = rangeMaxYear;
    }

    public void setMinHour(String minHour) {this.minHour = minHour;}

    public void setMaxHour(String maxHour) {this.maxHour = maxHour;}

    public TipoDiData getTipoDiData() {
        return tipoDiData;
    }

    @Override
    public String toString() {
        return nomeVotoMenu +
                ", tipoDiData=" + tipoDiData;
    }
}