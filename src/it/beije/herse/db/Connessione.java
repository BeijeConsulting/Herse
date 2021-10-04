package it.beije.herse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connessione {

	private Connection connection;
	private int nome;


	public int getNome() {
		return this.nome;
	}
	public Connection getConn() {
		return this.connection;
	}
	
	private Connessione(int nome){

		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "abcabcde");

			this.nome=nome;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("QUALCOSA è ANDATO STORTO");
		}
	}

	public static Connessione nuovaConnessione(int nome){
		return new Connessione(nome);
	}

	public static void main(String[] args) {



	}

}
