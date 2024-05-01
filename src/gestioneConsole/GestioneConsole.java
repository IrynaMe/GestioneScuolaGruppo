package gestioneConsole;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

public class GestioneConsole {
    private static int index = 0;
    private Scanner sc = new Scanner(System.in);
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_ORANGE = "\u001B[38;5;208m";
    public static final String ANSI_RESET = "\u001B[0m";
    private Map<Integer, VotoMenu> votiMenu = new HashMap<>();
    Obj obj = new Obj();//per salvare valori return di inserimento

    //crea menu da HashMap+gestisce risultati di return dai metodi
    public Obj stampaMenu() {
        Integer key = null;
        VotoMenu votoMenu = null;
        for (Map.Entry<Integer, VotoMenu> entry : votiMenu.entrySet()) {
            System.out.print(ANSI_BLUE);
            System.out.println(entry.getKey() + " -> " + entry.getValue());
            System.out.print(ANSI_RESET);
        }

        System.out.println("Inserisci la scelta: ");

        int scelta = Integer.parseInt(sc.nextLine());


        for (Map.Entry<Integer, VotoMenu> entry : votiMenu.entrySet()) {
            if (scelta == entry.getKey()) {
                // Chiamo metodi partendo da tipi di dati
                switch (entry.getValue().getTipoDiData()) {
                    case NUMERO:
                        Integer numero = dammiIntero("Inserisci un numero da " + entry.getValue().getRangeNumeroMin() + " a " + entry.getValue().getRangeNumeroMax(), "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3, entry.getValue().getRangeNumeroMin(), entry.getValue().getRangeNumeroMax());
                        if (numero == null) {
                            System.out.println("Inserimento non andato con successo");
                        } else {
                            System.out.println("Inserimento andato con successo");
                            obj.setNumero(numero);
                        }
                        break;
                    case STRINGA:
                        String stringa = dammiStringa("Inserisci una stringa con lunghezza da " + entry.getValue().getRangeStringaMinLength() + " a " + entry.getValue().getRangeStringaMaxLength() + " caratteri", "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3, entry.getValue().getRangeStringaMinLength(), entry.getValue().getRangeStringaMaxLength());
                        if (stringa == null) {
                            System.out.println("Inserimento non andato con successo");
                        } else {
                            System.out.println("Inserimento andato con successo");
                            obj.setStringa(stringa);
                        }
                        break;
                    case LETTERA:
                        String str = dammiLettera("Inserisci una lettera da " + entry.getValue().getRangeLetteraInizio() + " a " + entry.getValue().getRangeLetteraFine(), "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3, entry.getValue().getRangeLetteraInizio(), entry.getValue().getRangeLetteraFine());
                        if (str != null) {
                            System.out.println("Inserimento andato con successo");
                            obj.setLettera(str);
                        } else {
                            System.out.println("Inserimento non andato con successo");
                        }
                        break;
                    case DATA:
                        LocalDate data = dammiData("Inserisci una data (formato dd-MM-yyyy), dove anno è da " + entry.getValue().getRangeMinYear() + " a " + entry.getValue().getRangeMaxYear(), "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3, entry.getValue().getRangeMinYear(), entry.getValue().getRangeMaxYear());
                        if (data != null) {
                            System.out.println("Inserimento andato con successo");
                            obj.setData(data);
                        } else {
                            System.out.println("Inserimento non andato con successo");
                        }
                        break;
                    case ORARIO:
                        LocalTime ore = dammiOra("Inserisci ore (formato hh:mm), dove hour è da " + entry.getValue().getMinHour() + " a " + entry.getValue().getMaxHour(), "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3, entry.getValue().getMinHour(), entry.getValue().getMaxHour());
                        if (ore != null) {
                            System.out.println("Inserimento andato con successo");
                            obj.setOreMin(ore);
                        } else {
                            System.out.println("Inserimento non andato con successo");
                        }
                        break;
                    case SESSO:
                        String sesso = dammiSesso("Inserisci il sesso (m/f):", "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3);
                        if (sesso != null) {
                            System.out.println("Inserimento andato con successo");
                            obj.setSesso(sesso);
                        } else {
                            System.out.println("Inserimento non andato con successo");
                        }
                        break;
                    case MAIL:
                        String mail = dammiMail("Inserisci un indirizzo email:", "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3);
                        if (mail != null) {
                            System.out.println("Inserimento andato con successo");
                            obj.setMail(mail);
                        } else {
                            System.out.println("Inserimento non andato con successo");
                        }
                        break;
                    case CODICE_FISCALE:
                        String codiceFiscale = dammiCodiceFiscale("Inserisci un codice fiscale:", "Input non valido. Riprova.", "Errore: input non valido.", "Input valido.", 3);
                        if (codiceFiscale != null) {
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
        numeroIntero.setRangeNumeroMin("1");
        numeroIntero.setRangeNumeroMax("100000");
        votiMenu.put(++index, numeroIntero);
        //stringa
        VotoMenu stringa = new VotoMenu("Inserimento di una stringa", TipoDiData.STRINGA);
        stringa.setRangeStringaMinLength(2);
        stringa.setRangeStringaMaxLength(20);
        votiMenu.put(++index, stringa);
        //lettera
       VotoMenu lettera = new VotoMenu("Inserimento di una lettera", TipoDiData.LETTERA);
        lettera.setRangeLetteraInizio("A");
        lettera.setRangeLetteraFine("D");
        votiMenu.put(++index, lettera);
        //data
        VotoMenu data = new VotoMenu("Inserimento di data", TipoDiData.DATA);
        data.setRangeMinYear(1900);
        data.setRangeMaxYear(2024);
        votiMenu.put(++index, data);
        //ora
        VotoMenu orario = new VotoMenu("Inserimento di orario", TipoDiData.ORARIO);
        orario.setMinHour("09");
        orario.setMaxHour("18");
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
                               String msgSuccess, int tentativi, String rangeMin, String rangeMax) {
        String input;
        Integer result = null;
        Integer numMin = Integer.parseInt(rangeMin);
        Integer numMax = Integer.parseInt(rangeMax);
        if (numMin < 0 || numMin > numMax) {
            System.out.println("Range non valido");
        } else {
            StringBuilder regexBuilder = new StringBuilder("\\b(").append(rangeMin).append("|").append(rangeMax).append("|");
            for (int i = numMin + 1; i < numMax; i++) {
                regexBuilder.append(i).append("|");
            }
            regexBuilder.append(")\\b");
            String regexInt = regexBuilder.toString();
            do {
                System.out.println(msgShow);
                input = sc.nextLine().trim();
                if (!input.isEmpty() && !Pattern.matches(regexInt, input)) {
                    System.out.print(ANSI_ORANGE);
                    System.out.println(msgRetry);
                    System.out.print(ANSI_RESET);
                    tentativi--;
                }
            } while ((input.isEmpty() || !Pattern.matches(regexInt, input)) && tentativi != 0);
            if (tentativi == 0) {
                System.out.print(ANSI_RED);
                System.out.println(msgError);
                System.out.print(ANSI_RESET);
            } else {
                result = Integer.parseInt(input);
                System.out.println(ANSI_GREEN);
                System.out.println(msgSuccess);
                System.out.println(ANSI_RESET);
            }
        }
        return result;
    }



    public String dammiStringa(String msgShow, String msgRetry, String msgError,
                               String msgSuccess, int tentativi, int rangeMinLength, int rangeMaxLength) {
        String input=null;
        if (rangeMinLength < 0 || rangeMaxLength < 0 || rangeMinLength > rangeMaxLength) {
            System.out.println("Range di lunghezza non valido");
        } else {
            String regexString = "^[a-zA-Z0-9]{" + rangeMinLength + "," + rangeMaxLength + "}$";
            do {
                System.out.println(msgShow);
                input = sc.nextLine().trim();
                if (!input.isEmpty() && !Pattern.matches(regexString, input)) {
                    System.out.println(ANSI_ORANGE);
                    System.out.println(msgRetry);
                    System.out.println(ANSI_RESET);
                    tentativi--;
                }
            } while (input.isEmpty() || (!input.isEmpty() && !Pattern.matches(regexString, input)) && tentativi != 0);
            if (tentativi == 0) {
                System.out.println(ANSI_RED);
                System.out.println(msgError);
                System.out.println(ANSI_RESET);
                input = null;
            } else {
                System.out.println(ANSI_GREEN);
                System.out.println(msgSuccess);
                System.out.println(ANSI_RESET);
            }
        }
        return input;
    }


    public String dammiSesso(String msgShow, String msgRetry, String msgError,
                             String msgSuccess, int tentativi) {
        String input=null;
        String regexSesso = "^[mfMF]$";
        do {
            System.out.println(msgShow);
            input = sc.nextLine().trim();
            if (!Pattern.matches(regexSesso, input)) {
                System.out.println(ANSI_ORANGE);
                System.out.println(msgRetry);
                System.out.println(ANSI_RESET);
                tentativi--;
            }
        } while (input == null || !Pattern.matches(regexSesso, input) && tentativi != 0);
        if (tentativi == 0) {
            System.out.println(ANSI_RED);
            System.out.println(msgError);
            System.out.println(ANSI_RESET);
            input = null;
        } else {
            System.out.println(ANSI_GREEN);
            System.out.println(msgSuccess);
            System.out.println(ANSI_RESET);
        }

        return input;
    }

    public String dammiLettera(String msgShow, String msgRetry, String msgError,
                               String msgSuccess, int tentativi, String letteraInizio, String letteraFine) {
        int asciiInizio = (int) letteraInizio.charAt(0);
        int asciiFine = (int) letteraFine.charAt(0);
        String input = null;
        if (asciiInizio > asciiFine || letteraInizio.length() > 1 || letteraFine.length() > 1) {
            System.out.println(ANSI_RED);
            System.out.println("Range di lettere non valido");
            System.out.println(ANSI_RESET);
        } else {
            String regexString = "^([" + letteraInizio.toLowerCase() + "-" + letteraFine.toLowerCase() + letteraInizio.toUpperCase() + "-" + letteraFine.toUpperCase() + "])$";
            do {
                System.out.println(msgShow);
                input = sc.nextLine().trim();
                if (input != null && !Pattern.matches(regexString, input)) {
                    System.out.println(ANSI_ORANGE);
                    System.out.println(msgRetry);
                    System.out.println(ANSI_RESET);
                    input = null;
                    tentativi--;
                }
            } while (input == null && tentativi != 0);
            if (input == null) {
                System.out.println(ANSI_RED);
                System.out.println(msgError);
                System.out.println(ANSI_RESET);
            } else {
                System.out.println(ANSI_GREEN);
                System.out.println(msgSuccess);
                System.out.println(ANSI_RESET);
            }
        }
        return input;
    }


    public LocalDate dammiData(String msgShow, String msgRetry, String msgError,
                               String msgSuccess, int tentativi, int minYear, int maxYear) {
        String input = null;
        LocalDate data = null;
        String minYearString = String.valueOf(minYear);
        String maxYearString = String.valueOf(maxYear);

        if (minYear > maxYear || minYearString.length() != 4 || maxYearString.length() != 4) {
            System.out.println("Range di anni non valido");
        } else {
            String regexData = "^(0[1-9]|1\\d|2[0-8])-(0[1-9]|1[0-2])-(19\\d{2}|20[0-2]\\d|202[0-4])$";
            do {
                System.out.println(msgShow);
                input = sc.nextLine().trim();
                if (input != null && !Pattern.matches(regexData, input)) {
                    System.out.println(ANSI_ORANGE);
                    System.out.println(msgRetry);
                    System.out.println(ANSI_RESET);
                    input = null;
                    tentativi--;
                }
            } while (input == null && tentativi != 0);
            if (input == null) {
                System.out.println(ANSI_RED);
                System.out.println(msgError);
                System.out.println(ANSI_RESET);
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                data = LocalDate.parse(input, formatter);
                System.out.println(ANSI_GREEN);
                System.out.println(msgSuccess);
                System.out.println(ANSI_RESET);
            }
        }
        return data;
    }

    public LocalTime dammiOra(String msgShow, String msgRetry, String msgError,
                              String msgSuccess, int tentativi, String minHour, String maxHour) {

        if(minHour.length()!=2||maxHour.length()!=2){
            System.out.println("Range di ore non valido, deve essere formsto hh (2 caratteri)");
        }
        String input=null;
        LocalTime localTime = null;
        String regexTime = "^("+minHour+"|"+maxHour+"]):[0-5][0-9]$";
        do {
            System.out.println(msgShow);
            input = sc.nextLine().trim();
            if (input != null&&!Pattern.matches(regexTime, input)) {
                System.out.println(ANSI_ORANGE);
                System.out.println(msgRetry);
                System.out.println(ANSI_RESET);
                input = null;
                tentativi--;
            }
        } while (input == null && tentativi != 0);
        if (input == null) {
            System.out.println(ANSI_RED);
            System.out.println(msgError);
            System.out.println(ANSI_RESET);
        } else {
            localTime = LocalTime.parse(input);
            System.out.println(ANSI_GREEN);
            System.out.println(msgSuccess);
            System.out.println(ANSI_RESET);
        }
        return localTime;
    }

    public String dammiMail(String msgShow, String msgRetry, String msgError,
                            String msgSuccess, int tentativi) {
        String input = null;
        String regexMail = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        do {
            System.out.println(msgShow);
            input = sc.nextLine().trim();
            if (!Pattern.matches(regexMail, input)) {
                System.out.println(ANSI_ORANGE);
                System.out.println(msgRetry);
                System.out.println(ANSI_RESET);
                tentativi--;
            }
        } while (input == null || !Pattern.matches(regexMail, input) && tentativi != 0);

        if (input == null) {
            System.out.println(ANSI_RED);
            System.out.println(msgError);
            System.out.println(ANSI_RESET);
        } else {
            System.out.println(ANSI_GREEN);
            System.out.println(msgSuccess);
            System.out.println(ANSI_RESET);
        }

        return input;
    }


    public String dammiCodiceFiscale(String msgShow, String msgRetry, String msgError,
                                     String msgSuccess, int tentativi) {
        String input = null;
        String regexCodiceFiscale = "^[A-Z]{6}\\d{2}[A-Z]\\d{2}[A-Z]\\d{3}[A-Z]$";

        do {
            System.out.println(msgShow);
            input = sc.nextLine().trim();
            if (!Pattern.matches(regexCodiceFiscale, input)) {
                System.out.println(ANSI_ORANGE);
                System.out.println(msgRetry);
                System.out.println(ANSI_RESET);
                tentativi--;
            }
        } while (input == null || !Pattern.matches(regexCodiceFiscale, input) && tentativi != 0);


        if (input == null) {
            System.out.println(ANSI_RED);
            System.out.println(msgError);
            System.out.println(ANSI_RESET);
        } else {
            System.out.println(ANSI_GREEN);
            System.out.println(msgSuccess);
            System.out.println(ANSI_RESET);
        }

        return input;
    }

    public void chiudiScanner() {
        sc.close();
    }


}//
