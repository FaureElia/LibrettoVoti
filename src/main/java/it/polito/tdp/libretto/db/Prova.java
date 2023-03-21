package it.polito.tdp.libretto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import it.polito.tdp.libretto.model.Voto;

public class Prova {

	public static void main(String[] args) {
		String jdbcURL="jdbc:mariadb://localhost/libretto?user=root&password=eliaMaria23";
		try {
		Connection conn=DriverManager.getConnection(jdbcURL);
		Statement st=conn.createStatement();//restituisce oggetto di tipo statement
		
		String sql="SELECT corso,punti,data " 
				+"FROM tabellaVoti";
		
		ResultSet res=st.executeQuery(sql);
		
		List<Voto> listaVoti=new ArrayList<Voto>();
		// dobbiamo adesso iterare sulle righe della tabella!
		// nota. se la prima riga non ci fosse, res.next mi da subito un valore di falso e quindi non esco dal while.
		// il cursore è inizialmente posizionato prima della prima riga!
		while (res.next()) {
			String corso= res.getString("corso");
			int punti=res.getInt("punti");
			
			System.out.println(corso+" = "+ punti);
			Voto v=new Voto(corso,punti,null);
			listaVoti.add(v);	
		}
		conn.close();
		
		
		}catch(SQLException e) {
			e.printStackTrace();
			
		}
		
	}

}
