package gestioneScuola;

import java.time.LocalDate;

public class ProvaAllievo {
	
	private static LocalDate data;
	private static String materia;
	private static int voto;
	
	public ProvaAllievo(LocalDate data, String materia, int voto) {
		this.data = data;
		this.materia = materia;
		this.voto = voto;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public static int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

}
