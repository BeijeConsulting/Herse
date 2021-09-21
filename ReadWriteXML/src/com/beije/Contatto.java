package com.beije;

public class Contatto {

    private String nome;
    private String cognome;
    private String telefono;
    private String email;
    private String note;

    public Contatto(){}
    public Contatto(String nome, String cognome, String telefono, String email, String note){
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.email = email;
        this.note = note;
    }

    public void show(){
        System.out.println(this.nome);
        System.out.println(this.cognome);
        System.out.println(this.telefono);
        System.out.println(this.email);
        System.out.println(this.note);
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
}
