package gestioneConsole;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GestioneConsole gestioneConsole = new GestioneConsole();
        gestioneConsole.popolaHashMapMenu();
        gestioneConsole.Obj obj;

        boolean isInserimento = true;
        while (isInserimento) {
            obj = gestioneConsole.stampaMenu();

            System.out.println("Numero: " + obj.getNumero());
            System.out.println("Stringa: " + obj.getStringa());
            System.out.println("Data: " + obj.getData());
            System.out.println("Sesso: " + obj.getSesso());
            System.out.println("Lettera: " + obj.getLettera());
            System.out.println("Codice Fiscale: " + obj.getCodiceFiscale());
            System.out.println("Mail: " + obj.getMail());

            Scanner scanner = new Scanner(System.in);
            System.out.println("Vuoi continuare? (s/n)");
            String scelta = scanner.nextLine();

            if (!scelta.equalsIgnoreCase("s")) {
                System.out.println("Arrivederci!");
                isInserimento = false;
                scanner.close();
            }
        }

        gestioneConsole.chiudiScanner();
    }
}