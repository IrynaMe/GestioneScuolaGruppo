package gestioneScuola;

import java.time.*;
import java.util.Scanner;

public class GestioneScuola {
	
	public static void main(String[] args) {
		
		Scuola scuola = new Scuola("Scuola Media Statale Benedetto Licantropo");
		Scanner scanner = new Scanner(System.in);
		int scelta;
		
		while (true) {
			System.out.println("Cosa vuoi fare? \n 1. Aggiungi allievo \n 2. Stampa allievi \n 3. Stampa file allievi \n 4. Esci"); // \n 4. Inserisci docente \n 5. Inserisci amministrativo");
			 scelta = scanner.nextInt();
			 scanner.nextLine(); // Per il problema della riga mangiata
		
			switch (scelta) {
				case 1: // Aggiungi allievo
					
					System.out.println("Inserisci il nome dell'allievo: ");
					String nome = scanner.nextLine();
					
					System.out.println("Inserisci il cognome dell'allievo: ");
					String cognome = scanner.nextLine();
					
					System.out.println("Inserisci la data di nascita dell'allievo(gg/mm/aaaa): ");
					String dataDiNascita = scanner.nextLine(); // Problema: Data di nascita dovrebbe essere LocalDate e non String, ma non lo prende
					String[] DataNascita = dataDiNascita.split("/");
					LocalDate bd = null;
					if(DataNascita.length==3)
						bd = LocalDate.of(Integer.parseInt(DataNascita[2]),Integer.parseInt(DataNascita[1]),Integer.parseInt(DataNascita[0]));
					
					
					System.out.println("Inserisci il luogo di nascita dell'allievo: "); 
					String luogoDiNascita = scanner.nextLine();
					
					System.out.println("Inserisci l'indirizzo email dell'allievo: ");
					String email = scanner.nextLine();
					System.out.println("Inserisci la classe dell'allievo: ");
					String classe = scanner.nextLine();
					
					System.out.println("Inserisci la sezione dell'allievo: ");
					String sezione = scanner.nextLine();
					
					Allievo nuovoAllievo = new Allievo(nome, cognome, bd, luogoDiNascita, email, classe, sezione);
			
					scuola.aggiungiAllievo(nuovoAllievo);
					
					break;
					
				case 2: //Stampa allievi
					scuola.stampaAllievi();
					break;
					
				case 3: //Stampa file allievi
					scuola.stampaFileAllievi("elencoAllievi.txt");
					break;
					
				case 4: // Esci
					scanner.close();
					return;
					
				default:
	                System.out.println("Scelta non valida. Riprova.");
			}
							
		}

	}

}