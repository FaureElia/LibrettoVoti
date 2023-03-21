package it.polito.tdp.libretto.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import it.polito.tdp.libretto.model.Voto;

public class VotoDAO {
	//chiamiamo DAO le classi che hanno l'unico scopo di accedere ai dati!
	// operazioni del tipo CRUD (create read update delete list Search)
	public  List<Voto> listaDiVoti() {
		try {
			Connection conn=BDConnect.getConnection();
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
				LocalDate data=res.getDate("data").toLocalDate();
				
				System.out.println(corso+" = "+ punti);
				Voto v=new Voto(corso,punti, data);
				listaVoti.add(v);	
			}
			
			conn.close();
			return listaVoti; }catch(SQLException e) {
							e.printStackTrace();}
		
		return null;
	}
	
	public void createVoto(Voto v) {
		/**
		String sql="INSERT INTO `tabellavoti` (`corso`, `punti`, `data`) "+
					"VALUES ('"+v.getCorso()+"', "+v.getPunti()+", '"+v.getDataEsame()+"');";
		/**
		 * ATTENZIONE!! SE FACESSI COSI SAREBBE UN GROSSO ERRORE! NON COSTRUIRE MAI UNO STATEMENT CONCATENANDO STRINGHE!
		 * 
		 * E' NECESSARIO PREPARARE UN TEMPLATE STATEMENT.
		 */
		
		String sql="INSERT INTO `tabellavoti` (`corso`, `punti`, `data`) VALUES (?, ?, ?);";
		try {
			Connection conn=BDConnect.getConnection();
			PreparedStatement st=conn.prepareStatement(sql);
			
			st.setString(1, v.getCorso());
			st.setInt(2, v.getPunti());
			st.setDate(3, Date.valueOf(v.getDataEsame()));
			
			//st.setDate(3, new Date(v.getDataEsame()));
			
			st.executeUpdate();
			
			}catch(SQLException e) {
				e.printStackTrace();
		}
		
	}
	
	public Voto readVoto(String corso) {
		return null;
	}
	
	public List<Voto> searchVotoConPuntiMaggiori(int punti){
		return null;
	}

}
