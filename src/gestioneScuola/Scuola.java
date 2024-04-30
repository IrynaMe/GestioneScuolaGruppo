package gestioneScuola;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Scuola {
	
	private String nomeScuola;
	private ArrayList<Allievo> allievi;
	private ArrayList<Insegnante> insegnanti;
	private ArrayList<Amministrativo> amministrativi;
	

	public Scuola(String nomeScuola) {
		this.nomeScuola = nomeScuola;
		this.allievi = new ArrayList<>();
		this.insegnanti = new ArrayList<>();
		this.amministrativi = new ArrayList<>();
	}
	
	// Metodi:
	
	public void aggiungiAllievo(Allievo allievo) {
		allievi.add(allievo);
		stampaFileAllievi();
	}
	
	public void aggiungiInsegnante(Insegnante insegnante) {
        insegnanti.add(insegnante);
    }

    public void aggiungiAmministrativo(Amministrativo amministrativo) {
        amministrativi.add(amministrativo);
    }
    
    public ArrayList<Allievo> getAllievi() {
		return allievi;
	}

	public void setAllievi(ArrayList<Allievo> allievi) {
		this.allievi = allievi;
	}

	public ArrayList<Insegnante> getInsegnanti() {
		return insegnanti;
	}

	public void setInsegnanti(ArrayList<Insegnante> insegnanti) {
		this.insegnanti = insegnanti;
	}

	public ArrayList<Amministrativo> getAmministrativi() {
		return amministrativi;
	}

	public void setAmministrativi(ArrayList<Amministrativo> amministrativi) {
		this.amministrativi = amministrativi;
	}



	public void stampaAllievi() {
    	for (Allievo allievo : getAllievi()) {
    		System.out.println("Nome: " + allievo.getNome() + ", Cognome: " + allievo.getCognome() + ", Classe: " + allievo.getClasse() + ", Sezione: " + allievo.getSezione() + "\n");
    	}
    }

    public void stampaFileAllievi(String nomeFile) {
    	try {
    		FileWriter fw = new FileWriter(nomeFile);

    		for (Allievo allievo : allievi) {
    			fw.write("Nome: " + allievo.getNome() + ", Cognome: " + allievo.getCognome() + ", Classe: " + allievo.getClasse() + ", Sezione: " + allievo.getSezione() + "\n");
    		}

    		fw.close();

    	} catch (IOException e) {
    		System.err.println("Errore durante la scrittura del file: " + e.getMessage());

    	}
    }

}
