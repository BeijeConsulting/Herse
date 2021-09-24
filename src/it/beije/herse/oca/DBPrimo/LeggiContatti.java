package it.beije.herse.oca.DBPrimo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LeggiContatti {
	public static void LeggiContatti(Connection connection){
		PreparedStatement preparedQuery=null;
		ResultSet rs=null;
		try {
		preparedQuery=connection.prepareStatement("SELECT * FROM rubrica");
		rs = preparedQuery.executeQuery();
		while (rs.next()) {
			System.out.println("id : " + rs.getInt("id"));
			System.out.println("nome : " + rs.getString("nome"));
			System.out.println("cognome : " + rs.getString("cognome"));
			System.out.println("telefono : " + rs.getString("telefono"));
			System.out.println("email : " + rs.getString("email"));
			System.out.println("note : " + rs.getString("note"));
			System.out.println();
		}
//		}catch(ClassNotFoundException cnfEx) {
//		cnfEx.printStackTrace();
	}catch (SQLException sqlEx) {
		sqlEx.printStackTrace();
	} finally {
		try {
			rs.close();
			preparedQuery.close();
			//connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


}