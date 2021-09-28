package it.beije.herse.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionPool {

	private final static int MAX_CONNESSIONI = 5;
	private ConnectionPool() {}

	private static ConnectionPool instance;

	private  Connection connection;
	private Statement statement;
	private ResultSet rs;

	private static int numeroConnessioni;

	public static int getNumeroConnessioni() {
		return numeroConnessioni;
	}

	public static ConnectionPool getInstance() {
		if(numeroConnessioni == MAX_CONNESSIONI) {
			throw new RuntimeException("Limite connessioni");
		}
		else if(numeroConnessioni<MAX_CONNESSIONI) {
			instance = new ConnectionPool();
			numeroConnessioni++;
		}
		return instance;
	}

	public Connection getConnection(){
		return this.connection;
	}

	public Statement getStatement() {
		return this.statement;
	}

	public ResultSet getResultSet() {
		return this.rs;
	}

	public  void closeConnection() {
		instance = null;
		numeroConnessioni--;

	}




	public  void openConnection() throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/herse?serverTimezone=CET", "root", "borgese");
	}

	public static void main(String[] args) {
		ConnectionPool connectionPool = ConnectionPool.getInstance();
		//		ConnectionPool connectionPool1 = ConnectionPool.getInstance();
		System.out.println("connessioni : "+ ConnectionPool.getNumeroConnessioni());		
		connectionPool.closeConnection();
		System.out.println("connessioni : "+ ConnectionPool.getNumeroConnessioni());
		try {
			connectionPool.openConnection();

			Connection connection = connectionPool.getConnection();
			System.out.println("connection " + connection);

			Statement statement = connectionPool.getStatement();
			statement = connection.createStatement();
			System.out.println("statement " + statement);


			ResultSet rs = connectionPool.getResultSet();

			rs = statement.executeQuery("SELECT * FROM rubrica");


//			while(rs.next()) {
//				System.out.println(rs.getString("nome"));
//			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
