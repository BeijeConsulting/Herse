package it.beije.herse.file;

public class Contatto {
	
	private int id;
	private String nome;
	private String cognome;
	private String telefono;
	private String email;
	private String note;
	
		
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

//	@Override
//	public String toString() {
//		return "Contatto [nome=" + nome + ", cognome=" + cognome + ", telefono=" + telefono + ", email=" + email
//				+ ", note=" + note + "]";
//	}
	
	public Contatto() {}
	
	public Contatto(String nome, String cognome, String telefono, String email, String note) {
		this.nome = nome;
		this.cognome = cognome;
		this.telefono = telefono;
		this.email = email;
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
				.append(", note: ").append(note).append("}");
		
		return builder.toString();
	}


}
