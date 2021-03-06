package it.beije.herse.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "rubrica")
public class Contatto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="nome")
	private String nome;

	@Column(name="cognome")
	private String cognome;
	
	//@Column(name="telefono")
	private String telefono;

	//@Column(name="email")
	//@Column
	private String email;

	@Column(name="note")
	private String note;
//	private String annotazioni;
//	public String getAnnotazioni() {
//		return annotazioni;
//	}
//	public void setAnnotazioni(String annotazioni) {
//		this.annotazioni = annotazioni;
//	}

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
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
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
//		StringBuilder builder = new StringBuilder("{id: ").append(id);
//		builder.append(", nome: ").append(nome);
//		builder.append(", cognome: ").append(cognome);
//		builder.append(", telefono: ").append(telefono);
//		builder.append(", email: ").append(email);
//		builder.append(", note: ").append(note).append("}");

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
