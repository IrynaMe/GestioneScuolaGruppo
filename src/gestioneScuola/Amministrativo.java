package gestioneScuola;

import java.time.LocalDate;

public class Amministrativo extends Persona {
	private String ufficioCompetenza;
	private String orarioLavoro;

	public Amministrativo(String nome, String cognome, LocalDate dataDiNascita, String luogoDiNascita, String email, String orario, String UfficioCompetenza) {
		super(nome, cognome, dataDiNascita, luogoDiNascita, email);
		this.ufficioCompetenza = ufficioCompetenza;
		this.orarioLavoro = orario;
	}

	public String getUfficioDiCompetenza() {
		return ufficioCompetenza;
	}

	public void setUfficioDiCompetenza(String ufficioDiCompetenza) {
		this.ufficioCompetenza = ufficioDiCompetenza;
	}

	public String getOrario() {
		return orarioLavoro;
	}

	public void setOrario(String orario) {
		this.orarioLavoro = orario;
	}

}
