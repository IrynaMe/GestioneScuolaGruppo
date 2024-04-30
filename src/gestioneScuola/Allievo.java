package gestioneScuola;

import java.time.LocalDate;
import java.util.ArrayList;

public class Allievo extends Persona {
	
	//Variabili d'istanza:
	private String classe;
	private String sezione;
	private ArrayList<ProvaAllievo> andamento;

	// Costruttore: 
	public Allievo(String nome, String cognome, LocalDate dataDiNascita, String luogoDiNascita, String email, String classe, String sezione) {
		super(nome, cognome, dataDiNascita, luogoDiNascita, email);
		this.classe = classe;
		this.sezione = sezione;
		this.andamento = new ArrayList<>();
	}

	// Metodi:
	
	public double mediaVoti() {
		double somma = 0;
		for (ProvaAllievo risultato : andamento) {
			somma+= risultato.getVoto();
		}
		if (andamento.isEmpty()) {
			return -1;
		} else {
			return somma / andamento.size();
		}
	}
	
	public double mediaVotiMateria(String sMateria) {
		double somma = 0;
		int iNumVoti = 0;
		for (ProvaAllievo risultato : andamento) {
			if(risultato.getMateria().equals(sMateria))
			{
				somma+= risultato.getVoto();
				iNumVoti++;
			}
		}
		if (iNumVoti==0) {
			return -1;
		} else {
			return somma / iNumVoti;
		}
	}

	public String getClasse() {
		return classe;
	}
	
	public void setClasse(String classe) {
		this.classe = classe;
	}
	
	public String getSezione() {
		return sezione;
	}
	
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}
	
	public ArrayList getAndamento() {
		return andamento;
	}
	
	public void setAndamento(ArrayList andamento) {
		this.andamento = andamento;
	}
}