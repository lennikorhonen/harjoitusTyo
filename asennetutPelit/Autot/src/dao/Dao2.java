package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Pelit;

public class Dao2 {
	
	private Connection con=null; //Yhteys olio, jolla luodaan yhteys tietokantaan
	private ResultSet rs = null; // Tähän olioon tulee tietokanta haun tulos eli taulusta löydetyt data rivit
	private PreparedStatement stmtPrep=null; // Tähän olioon tulee tietokannassa ajettava sql lause
	 										// Tietoturvallisempi, estää sql-injectionin
	private String sql;
	private String db ="asennetutPelit.db"; // Tietokannan nimi

	private Connection yhdista(){
		Connection con = null;    	
		String path = System.getProperty("catalina.base"); // Java projektin juuri, mistä tietokanta tiedosto löytyy  	
		path = path.substring(0, path.indexOf(".metadata")).replace("\\", "/"); //Korvataan Windowsin hakemistoerottimet "Internetin" hakemistoerottimilla
		//Eclipsessa
		//path += "/webapps/"; //Tuotannossa. Laita tietokanta webapps-kansioon
		String url = "jdbc:sqlite:"+path+db; // Tietokannan osoite 	
		try {	       
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(url); // Nyt avataan yhteys tietokantaan
			System.out.println("Yhteys avattu.");
		}catch (Exception e){	
			System.out.println("Yhteyden avaus epäonnistui.");
			e.printStackTrace();	         
		}
		return con;
	}
	
	public boolean lisaaPeli(Pelit peli){
		boolean paluuArvo=true;
		sql="INSERT INTO Pelit VALUES(?,?,?,?,?,?,?)";				  
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql);
			stmtPrep.setInt(1, peli.getPeliId());
			stmtPrep.setString(2, peli.getNimi());
			stmtPrep.setDouble(3, peli.getKoko());
			stmtPrep.setString(4, peli.getVersio());
			stmtPrep.setInt(5, peli.getJulkaisuVuosi());
			stmtPrep.setString(6, peli.getJakeluAlusta());
			stmtPrep.setString(7, peli.getPeliTyyppi());
			stmtPrep.executeUpdate();
	        con.close();
		} catch (SQLException e) {				
			e.printStackTrace();
			paluuArvo=false;
		}
		return paluuArvo;
	}

	public ArrayList<Pelit> listaaKaikki(String hakusana){
		ArrayList<Pelit> peli = new ArrayList<Pelit>();
		sql = "SELECT *" //PeliID, Nimi, Koko, Versio, Julkaisuvuosi, Jakelualusta, Pelityyppi
				+ "FROM Pelit WHERE Nimi LIKE ? or Jakelualusta LIKE ? or Pelityyppi LIKE ?";       
		try {
			con=yhdista();
			if(con!=null){ //jos yhteys onnistui
				stmtPrep = con.prepareStatement(sql);  //Luodaan komento olio, joka voidaan sitten ajaa tietokannassa
				stmtPrep.setString(1, "%" + hakusana + "%"); //Talletetaan sql lauseeseen ? merkkien tilalle arvot
				// setString(1, ykkönen viittaa sql lauseen 1. ? -merkkiin
				stmtPrep.setString(2, "%" + hakusana + "%");
				stmtPrep.setString(3, "%" + hakusana + "%");
				rs = stmtPrep.executeQuery(); // Ajetaan sql lause tietokannassa
				
				if(rs!=null){ //jos kysely onnistui							
					while(rs.next()){ // Kyselyssä tuli useita data rivejä, jotka kopioidaan ArrayList tietorakenteeseen
						Pelit pelit = new Pelit();
						//rs.getInt(1 niin ykkönen viittaa sql haun 1. kenttään
						pelit.setPeliId(rs.getInt(1));
						pelit.setNimi(rs.getString(2));
						pelit.setKoko(rs.getDouble(3));
						pelit.setVersio(rs.getString(4));
						pelit.setJulkaisuVuosi(rs.getInt(5));
						pelit.setJakeluAlusta(rs.getString(6));
						pelit.setPeliTyyppi(rs.getString(7));
						peli.add(pelit); // Lisätään data rivi ArrayList tietorakenteeseen
					}					
				}
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return peli; //Lähetetään data rivit kutsuneelle ohjelmalle, tässä Servletille
	}
	
	public boolean muutaPeli(Pelit peli){
		boolean paluuArvo=true;
		sql="UPDATE Pelit SET Nimi=?, Koko=?, Versio=?, Julkaisuvuosi=?, Jakelualusta=?, Pelityyppi=?  WHERE PeliID=?";						  
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setString(1, peli.getNimi());
			stmtPrep.setDouble(2, peli.getKoko());
			stmtPrep.setString(3, peli.getVersio());
			stmtPrep.setInt(4, peli.getJulkaisuVuosi());
			stmtPrep.setString(5, peli.getJakeluAlusta());
			stmtPrep.setString(6, peli.getPeliTyyppi());
			stmtPrep.setInt(7, peli.getPeliId());
			stmtPrep.executeUpdate();
	        con.close();
		} catch (SQLException e) {				
			e.printStackTrace();
			paluuArvo=false;
		}
		return paluuArvo;
	}
	
	public boolean poistaPeli(String peliId){ //Oikeassa elämässä tiedot ensisijaisesti merkitään poistetuksi.
		boolean paluuArvo=true;
		sql="DELETE FROM Pelit WHERE PeliID=?";						  
		try {
			con = yhdista();
			stmtPrep=con.prepareStatement(sql); 
			stmtPrep.setString(1, peliId);
			stmtPrep.executeUpdate();
	        con.close();
		} catch (SQLException e) {				
			e.printStackTrace();
			paluuArvo=false;
		}
		return paluuArvo;
	}

}
