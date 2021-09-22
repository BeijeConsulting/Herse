package it.beije.herse.file;

import java.util.Scanner;

public class Contatto {
	
	private String nome;
	private String cognome;
	private String telefono;
	private String email;
	private String note;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setNome() {
		System.out.println("Insersci il nome del contatto");
		Scanner input = new Scanner(System.in);
		String nome = input.next();
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setCognome() {
		System.out.println("Insersci il cognome del contatto");
		Scanner input = new Scanner(System.in);
		String cognome = input.next();
		this.cognome = cognome;
	}
	
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setTelefono() {
		System.out.println("Insersci il telefono del contatto");
		Scanner input = new Scanner(System.in);
		String telefono = input.next();
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEmail() {
		System.out.println("Insersci l'email del contatto");
		Scanner input = new Scanner(System.in);
		String email = input.next();
		this.email = email;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public void setNote() {
		System.out.println("Insersci le note per il contatto");
		Scanner input = new Scanner(System.in);
		String note = input.next();
		this.note = note;
	}

}
