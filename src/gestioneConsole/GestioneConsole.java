package gestioneConsole;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class GestioneConsole {
    private Scanner sc = new Scanner(System.in);
  //  public static final String ANSI_RED = "\u001B[31m";
  //  public static final String ANSI_RESET = "\u001B[0m";
    private Map<Integer, VotoMenu> votiMenu = new HashMap<>();
    Obj obj=new Obj();//per salvare valori return di inserimento

    //crea menu da HashMap
    public Obj stampaMenu() {
        Integer key = null;
        VotoMenu votoMenu = null;
        for (Map.Entry<Integer, VotoMenu> entry : votiMenu.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());

        }
        System.out.println("Inserisci la scelta: ");
        int scelta = Integer.parseInt(sc.nextLine());

        for (Map.Entry<Integer, VotoMenu> entry : votiMenu.entrySet()) {
            if (scelta == entry.getKey()) {
                // Chiamo metodi partendo da tipi di dati
                switch (entry.getValue().getTipoDiData()) {
                    case NUMERO:
                        Integer numero = dammiIntero("Inserisci un numero:", "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3, entry.getValue().rangeNumeroMin, entry.getValue().rangeNumeroMax);
                        if (numero== null) {
                            System.out.println("Inserimento non andato con successo");
                        } else {
                            System.out.println("Inserimento andato con successo");
                            obj.setNumero(numero);
                        }
                        break;
                    case STRINGA:
                        String stringa = dammiStringa("Inserisci una stringa:", "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3, entry.getValue().rangeStringaMinLength, entry.getValue().rangeStringaMaxLength);
                        if (stringa == null) {
                            System.out.println("Inserimento non andato con successo");
                        } else {
                            System.out.println("Inserimento andato con successo");
                            obj.setStringa(stringa);
                        }
                        break;
                    case LETTERA:
                        String str = dammiLettera("Inserisci una lettera:", "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.",3,entry.getValue().rangeLetteraInizio,entry.getValue().rangeLetteraFine);
                        if (str != null) {
                            System.out.println("Inserimento andato con successo");
                            obj.setLettera(str);
                        } else {
                            System.out.println("Inserimento non andato con successo");
                        }
                        break;
                    // Add cases for other TipoDiData values here
                    case DATA:
                        LocalDate data = dammiData("Inserisci una data (formato dd-MM-yyyy):", "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3, entry.getValue().rangeMinYear,entry.getValue().rangeMaxYear);
                        if (data != null) {
                            System.out.println("Inserimento andato con successo");
                            obj.setData(data);
                        } else {
                            System.out.println("Inserimento non andato con successo");
                        }
                        break;
                    case SESSO:
                        String sesso = dammiSesso("Inserisci il sesso (m/f):", "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3);
                        if (sesso!=null) {
                            System.out.println("Inserimento andato con successo");
                            obj.setSesso(sesso);
                        } else {
                            System.out.println("Inserimento non andato con successo");
                        }
                        break;
                    case MAIL:
                        String mail = dammiMail("Inserisci un indirizzo email:", "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3);
                        if (mail!=null) {
                            System.out.println("Inserimento andato con successo");
                            obj.setMail(mail);
                        } else {
                            System.out.println("Inserimento non andato con successo");
                        }
                        break;
                    case CODICE_FISCALE:
                        String codiceFiscale = dammiCodiceFiscale("Inserisci un codice fiscale:", "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3);
                        if (codiceFiscale!=null) {
                            System.out.println("Inserimento andato con successo");
                            obj.setCodiceFiscale(codiceFiscale);
                        } else {
                            System.out.println("Inserimento non andato con successo");
                        }
                        break;

                    default:
                        System.out.println("Input di chiave menu non valido");
                        break;
                }
            }

        }
        return obj;
    }

    public void popolaHashMapMenu() {
        VotoMenu numeroIntero = new VotoMenu("Inserimento di un numero intero", TipoDiData.NUMERO);
        numeroIntero.setRangeNumeroMin(1);
        numeroIntero.setRangeNumeroMax(99);
        VotoMenu stringa = new VotoMenu("Inserimento di una stringa", TipoDiData.STRINGA);
        stringa.setRangeStringaMinLength(2);
        stringa.setRangeStringaMaxLength(20);
        VotoMenu lettera = new VotoMenu("Inserimento di una lettera", TipoDiData.LETTERA);
        lettera.setRangeLetteraInizio("A");
        lettera.setRangeLetteraFine("Z");
        VotoMenu data = new VotoMenu("Inserimento di data", TipoDiData.DATA);
        data.setRangeMinYear(1900);
        data.setRangeMaxYear(2024);
        VotoMenu sesso = new VotoMenu("Inserimento di sesso", TipoDiData.SESSO);
        VotoMenu mail = new VotoMenu("Inserimento di mail", TipoDiData.MAIL);
        VotoMenu codiceFiscale = new VotoMenu("Inserimento di mail", TipoDiData.CODICE_FISCALE);
        votiMenu.put(1, numeroIntero);
        votiMenu.put(2, stringa);
        votiMenu.put(3, lettera);
        votiMenu.put(4, data);
        votiMenu.put(5, sesso);
        votiMenu.put(6, mail);
        votiMenu.put(7, codiceFiscale);
    }


    //i metodi return null se input errato, valore se corretto
    public Integer dammiIntero(String msgShow, String msgRetry, String msgError,
                                 String msgSuccess, int tentativi, int rangeMin, int rangeMax) {
        String input=null;
        Integer result=null;
        if (rangeMin < 0 || rangeMax < 0 || rangeMin > rangeMax) {
            System.out.println("Range non valido");
        } else {
            String regexInt = "[" + rangeMin + "-" + rangeMax + "]";
            do {
                System.out.println(msgShow);
                input = sc.nextLine();
                if (!Pattern.matches(regexInt, input)) {
                    System.out.println(msgRetry);
                    tentativi--;
                }
            } while (!Pattern.matches(regexInt, input) && tentativi != 0);
            if (tentativi==0) {
                result = null;
                System.out.println(msgError);
            } else {
                result=Integer.parseInt(input);
                System.out.println(msgSuccess);
            }
        }
        return result;
    }

    public String dammiStringa(String msgShow, String msgRetry, String msgError,
                               String msgSuccess, int tentativi, int rangeMinLength, int rangeMaxLength) {
        String input = null;
        if (rangeMinLength < 0 || rangeMaxLength < 0 || rangeMinLength > rangeMaxLength) {
            System.out.println("Range di lunghezza non valido");
        } else {
            String regexString = "^[a-zA-Z]{" + rangeMinLength + "," + rangeMaxLength + "}$";

            do {
                System.out.println(msgShow);
                input = sc.nextLine();
                if (!Pattern.matches(regexString, input)) {
                    System.out.println(msgRetry);
                    input = null;
                    tentativi--;
                }
            } while (!Pattern.matches(regexString, input) && tentativi != 0);
            if (input == null) {
                System.out.println(msgError);
            } else {
                System.out.println(msgSuccess);
            }
        }
        return input;
    } //cognome, nome, LuogoNascita, materia, classe,sesso

    public String dammiSesso(String msgShow, String msgRetry, String msgError,
                                String msgSuccess, int tentativi) {
        String regexSesso = "^[mfMF]$";
        String input;
        Integer[] arrResult = new Integer[2];
        do {
            System.out.println(msgShow);
            input = sc.nextLine();
            if (!Pattern.matches(regexSesso, input)) {
                System.out.println(msgRetry);
                tentativi--;
            }
        } while (!Pattern.matches(regexSesso, input) && tentativi != 0);
        if (tentativi == 0) {
            System.out.println(msgError);
            input = null;
        } else {
            System.out.println(msgSuccess);
        }

        return input;
    }
    public String dammiLettera(String msgShow, String msgRetry, String msgError,
                                  String msgSuccess, int tentativi, String letteraInizio, String letteraFine) {
        int asciiInizio = (int) letteraInizio.charAt(0);
        int asciiFine = (int) letteraFine.charAt(0);
        String input=null;
        Integer[] arrResult = new Integer[2];
        if (asciiInizio > asciiFine || letteraInizio.length() > 1 || letteraFine.length() > 1) {
            System.out.println("Range di lettere non valido");
        } else {
            String regexSezioneClasse = "^[A-D]$";
            String regexString = "^[" + letteraInizio.toLowerCase() + "-" + letteraFine.toLowerCase() + letteraInizio.toUpperCase() + "-" + letteraFine.toUpperCase() + "]$";
            do {
                System.out.println(msgShow);
                input = sc.nextLine();
                if (!Pattern.matches(regexString, input)) {
                    System.out.println(msgRetry);
                    input = null;
                    tentativi--;
                }
            } while (!Pattern.matches(regexString, input) && tentativi != 0);
            if (input == null) {
                System.out.println(msgError);
            } else {
                System.out.println(msgSuccess);
            }

        }
        return input;
    } //classe

    public LocalDate dammiData(String msgShow, String msgRetry, String msgError,
                               String msgSuccess, int tentativi, int minYear, int maxYear) {

        String input;
        String minYearString = String.valueOf(minYear);
        String maxYearString = String.valueOf(maxYear);
        LocalDate data = null;
        if (minYear > maxYear || minYearString.length() < 4 || maxYearString.length() < 4) {
            System.out.println("Range di anni non valido");
        } else {
            String regexData = "^\\d{2}-\\d{2}-\\d{4}$";
            do {
                System.out.println(msgShow);
                input = sc.nextLine();
                if (!Pattern.matches(regexData, input)) {
                    System.out.println(msgRetry);
                    input = null;
                    tentativi--;
                }
            } while (!Pattern.matches(regexData, input)&& tentativi != 0);
            if (input == null) {
                System.out.println(msgError);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                data = LocalDate.parse(input, formatter);
                System.out.println(msgSuccess);
            }

        }
        return data;
    }

    public String dammiMail(String msgShow, String msgRetry, String msgError,
                            String msgSuccess, int tentativi) {
        String input;

        LocalDate data = null;
        String regexMail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        do {
            System.out.println(msgShow);
            input = sc.nextLine();
            if (!Pattern.matches(regexMail, input)) {
                System.out.println(msgRetry);
                input = null;
                tentativi--;
            }
        } while (!Pattern.matches(regexMail, input) &&tentativi != 0);
        if (input == null) {
            System.out.println(msgError);
        } else {
            System.out.println(msgSuccess);
        }

        return input;
    }

    public String dammiCodiceFiscale(String msgShow, String msgRetry, String msgError,
                                     String msgSuccess, int tentativi) {
        String input;
        String regexCodiceFiscale = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$";

        do {
            System.out.println(msgShow);
            input = sc.nextLine();
            if (!Pattern.matches(regexCodiceFiscale, input)) {
                System.out.println(msgRetry);
                input = null;
                tentativi--;
            }
        } while (!Pattern.matches(regexCodiceFiscale, input) && tentativi != 0);
        if (input == null) {
            System.out.println(msgError);
        } else {
            System.out.println(msgSuccess);
        }

        return input;

    }

    public void chiudiScanner() {
        sc.close();
    }


    private class VotoMenu {
        private String nomeVotoMenu;
        public TipoDiData tipoDiData;
        Integer rangeNumeroMin, rangeNumeroMax;// Da specificare range inserimento numero con setter
        Integer rangeStringaMinLength, rangeStringaMaxLength;//Da specificare lunghezza stringa con setter
        String rangeLetteraInizio, rangeLetteraFine;//da specificare range lettere con setter
        Integer rangeMinYear, rangeMaxYear;//da specificare range anni con setter


        public VotoMenu(String nomeVotoMenu, TipoDiData tipoDiData) {
            this.nomeVotoMenu = nomeVotoMenu;
            this.tipoDiData = tipoDiData;
        }

        public void setNomeVotoMenu(String nomeVotoMenu) {
            this.nomeVotoMenu = nomeVotoMenu;
        }

        public void setTipoDiData(TipoDiData tipoDiData) {
            this.tipoDiData = tipoDiData;
        }

        public void setRangeNumeroMin(Integer rangeNumeroMin) {
            this.rangeNumeroMin = rangeNumeroMin;
        }

        public void setRangeNumeroMax(Integer rangeNumeroMax) {
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

        public TipoDiData getTipoDiData() {
            return tipoDiData;
        }

        @Override
        public String toString() {
            return nomeVotoMenu +
                    ", tipoDiData=" + tipoDiData;
        }
    }
    public static class Obj {
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

}//


 /* public TipoDiData assegnaTipoData(int scelta) {
        TipoDiData tipoData;
        switch (scelta) {
            case 1:
                tipoData = TipoDiData.NUMERO;
                System.out.println("Specifica range per inserimento: Inserischi numero minimo: ");
                int numeroMin=Integer.parseInt(sc.nextLine());
                System.out.println("Specifica range per inserimento: Inserischi numero massimo: ");
                int numeroMax=Integer.parseInt(sc.nextLine());
                break;
            case 2:
                tipoData = TipoDiData.STRINGA;
                System.out.println("Specifica lunghezza della Stringa: Inserischi lunghezza minima (numero intero): ");
                int lunghezzaMin=Integer.parseInt(sc.nextLine());
                System.out.println("Specifica lunghezza della Stringa: Inserischi lunghezza massima (numero intero): ");
                int lunghezzaMax=Integer.parseInt(sc.nextLine());
                break;
            case 3:
                tipoData = TipoDiData.LETTERA;
                break;
            case 4:
                tipoData = TipoDiData.DATA;
                break;
            case 5:
                tipoData = TipoDiData.SESSO;
                break;
            case 6:
                tipoData = TipoDiData.MAIL;
                break;
            case 7:
                tipoData = TipoDiData.CODICE_FISCALE;
                break;
            default:
                System.out.println("Scelta per tipo di data non valida");
                return null;
        }
        return tipoData;

    }*/

 /*public void aggiungiVotiMenu() {

        // String[] tipiData = new String[] {"numero", "stringa", "data", "sesso", "mail", "codice fiscale", "classe", "sezione"};
        String votoMenu = null;
        Integer tipoDataInput = null;
        String tipoData;
        int key = 0;

        System.out.println("1->Creare nuovo voto di menu\n0->Esci");
        int scelta = -1;
        do {
            scelta = Integer.parseInt(sc.nextLine());
            if (scelta == 1) {
                System.out.println("Inserisci la stringa con il voto di menu: ");
                votoMenu = sc.nextLine();
                if (votoMenu == null) {
                    System.out.println("Non hai inserito il voto");
                    return;
                }
                System.out.println("Scegli tipo di input per il voto appena inserito:  " +
                        "1->numero\n2->stringa\n3->lettera\n4->data\n5->sesso\n6->mail\n7->codice fiscale\n altro numero-annulare");
                tipoDataInput = Integer.parseInt(sc.nextLine());
                if (tipoDataInput > 7 || tipoDataInput < 1) {
                    System.out.println("Non hai inserito il voto");
                    return;
                }
                if (votoMenu != null && tipoDataInput != null) {
                    TipoDiData tipoDiData = assegnaTipoData(tipoDataInput);
                   // Menu menu=new Menu(votoMenu, tipoDiData,);
                  //  votiMenu.put(++key, );
                }
            } else if (scelta > 1) {
                System.out.println("Input non valido, voto di menu non creato");
            }
        } while (scelta != 0);


    }*/