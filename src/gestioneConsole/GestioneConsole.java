package gestioneConsole;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class GestioneConsole {
    private static int index=0;
    private Scanner sc = new Scanner(System.in);
  //  public static final String ANSI_RED = "\u001B[31m";
  //  public static final String ANSI_RESET = "\u001B[0m";
    private Map<Integer, VotoMenu> votiMenu = new HashMap<>();
    Obj obj=new Obj();//per salvare valori return di inserimento

    //crea menu da HashMap+gestisce risultati di return dai metodi
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
                        Integer numero = dammiIntero("Inserisci un numero da "+entry.getValue().getRangeNumeroMin()+" a "+entry.getValue().getRangeNumeroMax(), "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3, entry.getValue().getRangeNumeroMin(), entry.getValue().getRangeNumeroMax());
                        if (numero== null) {
                            System.out.println("Inserimento non andato con successo");
                        } else {
                            System.out.println("Inserimento andato con successo");
                            obj.setNumero(numero);
                        }
                        break;
                    case STRINGA:
                        String stringa = dammiStringa("Inserisci una stringa con lunghezza da "+entry.getValue().getRangeStringaMinLength()+" a "+ entry.getValue().getRangeStringaMaxLength() +" caratteri", "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3, entry.getValue().getRangeStringaMinLength(), entry.getValue().getRangeStringaMaxLength());
                        if (stringa == null) {
                            System.out.println("Inserimento non andato con successo");
                        } else {
                            System.out.println("Inserimento andato con successo");
                            obj.setStringa(stringa);
                        }
                        break;
                    case LETTERA:
                        String str = dammiLettera("Inserisci una lettera da "+entry.getValue().getRangeLetteraInizio()+" a "+entry.getValue().getRangeLetteraFine(), "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.",3,entry.getValue().getRangeLetteraInizio(),entry.getValue().getRangeLetteraFine());
                        if (str != null) {
                            System.out.println("Inserimento andato con successo");
                            obj.setLettera(str);
                        } else {
                            System.out.println("Inserimento non andato con successo");
                        }
                        break;
                    case DATA:
                        LocalDate data = dammiData("Inserisci una data (formato dd-MM-yyyy), dove anno è da "+entry.getValue().getRangeMinYear()+" a "+entry.getValue().getRangeMaxYear(), "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3, entry.getValue().getRangeMinYear(),entry.getValue().getRangeMaxYear());
                        if (data != null) {
                            System.out.println("Inserimento andato con successo");
                            obj.setData(data);
                        } else {
                            System.out.println("Inserimento non andato con successo");
                        }
                        break;
                    case ORARIO:
                        LocalTime ore = dammiOra("Inserisci ore (formato hh:mm), dove hour è da "+entry.getValue().getMinHour()+" a "+entry.getValue().getMaxHour(), "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3, entry.getValue().getMinHour(),entry.getValue().getMaxHour());
                        if (ore != null) {
                            System.out.println("Inserimento andato con successo");
                            obj.setOreMin(ore);
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

    //creare voti di menu + !! specificare con setter limiti di inserimento per intero, stringa, lettera, data
    //da commentare voti non usate
    public void popolaHashMapMenu() {
        //intero
        VotoMenu numeroIntero = new VotoMenu("Inserimento di un numero intero", TipoDiData.NUMERO);
        numeroIntero.setRangeNumeroMin(1);
        numeroIntero.setRangeNumeroMax(99);
        votiMenu.put(++index, numeroIntero);
        //stringa
        VotoMenu stringa = new VotoMenu("Inserimento di una stringa", TipoDiData.STRINGA);
        stringa.setRangeStringaMinLength(2);
        stringa.setRangeStringaMaxLength(20);
        votiMenu.put(++index, stringa);
        //lettera
/*        VotoMenu lettera = new VotoMenu("Inserimento di una lettera", TipoDiData.LETTERA);
        lettera.setRangeLetteraInizio("A");
        lettera.setRangeLetteraFine("Z");
        votiMenu.put(++index, lettera);*/
        //data
        VotoMenu data = new VotoMenu("Inserimento di data", TipoDiData.DATA);
        data.setRangeMinYear(1900);
        data.setRangeMaxYear(2024);
        votiMenu.put(++index, data);
        //ora
        VotoMenu orario = new VotoMenu("Inserimento di orario", TipoDiData.ORARIO);
        orario.setMinHour(9);
        orario.setMaxHour(18);
        votiMenu.put(++index, orario);
        //sesso
        VotoMenu sesso = new VotoMenu("Inserimento di sesso", TipoDiData.SESSO);
        votiMenu.put(++index, sesso);
        //mail
        VotoMenu mail = new VotoMenu("Inserimento di mail", TipoDiData.MAIL);
        votiMenu.put(++index, mail);
        //cf
        VotoMenu codiceFiscale = new VotoMenu("Inserimento di codiceFiscale", TipoDiData.CODICE_FISCALE);
        votiMenu.put(++index, codiceFiscale);
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
        //    String regexData = "^\\d{2}-\\d{2}-("+m"|"+200[0-9]|2010+")$";
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

    public LocalTime dammiOra(String msgShow, String msgRetry, String msgError,
                               String msgSuccess, int tentativi, int minHour, int maxHour) {

        String input;
        String minYearString = String.valueOf(minHour);
        String maxYearString = String.valueOf(maxHour);
        LocalTime localTime = null;
        if (minHour > maxHour || minYearString.length() < 4 ) {
            System.out.println("Range di ore non valido");
        } else {
            String regexTime = "^\\d{2}:\\d{2}$";

            do {
                System.out.println(msgShow);
                input = sc.nextLine();
                if (!Pattern.matches(regexTime, input)) {
                    System.out.println(msgRetry);
                    input = null;
                    tentativi--;
                }
            } while (!Pattern.matches(regexTime, input)&& tentativi != 0);
            if (input == null) {
                System.out.println(msgError);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                localTime = LocalTime.parse(input, formatter);
                System.out.println(msgSuccess);
            }

        }
        return localTime;
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





}//
