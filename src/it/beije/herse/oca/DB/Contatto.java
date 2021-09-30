package it.beije.herse.oca.DB;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
public class Contatto {
	//Dichiaro le colonne della tabella
	@a
	private int id;
	private String nome;
	private String cognome;
	private int telefono;
	private String email;
	private String note;
	
	//Metodi set e get 
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("{id: ").append(id)
				.append(", nome: ").append(nome)
				.append(", cognome: ").append(cognome)
				.append(", telefono: ").append(telefono)
				.append(", email: ").append(email)
				.append(", note: ").append(note)
				.append("}");
		return builder.toString();
	}

}

