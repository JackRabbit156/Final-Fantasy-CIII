package hauptmenu.speicherstand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import charakter.model.SpielerCharakter;
import gamehub.trainer.faehigkeiten.Faehigkeit;
import gegenstand.Ausruestungsgegenstand.Accessoire;
import gegenstand.Ausruestungsgegenstand.Ruestungen.Ruestung;
import gegenstand.Ausruestungsgegenstand.Waffen.Waffe;

//import gegenstand.Ausruestungsgegenstand.Ruestung;
//import gegenstand.Ausruestungsgegenstand.Waffe;

import gegenstand.material.Material;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import hilfsklassen.ScannerHelfer;
import party.Party;
import statistik.Statistik;

public class SpeicherstandController {

	/**
	 * Schreibt alle Daten die gespeichert werden muessen in den entsprechenden
	 * Spielstand (SQLite Datenbank). Wenn es noch ueberhaupt gar keinen Spielstand
	 * gibt, werden alle Tabellen erstellt, die zum Speichern der Daten benoetigt
	 * werden.
	 * 
	 * @author Melvin
	 * @throws SQLException
	 * @since 16.11.2023
	 */
	public void speichern(Speicherstand speicherstand) throws SQLException {
		try (Connection connection = DriverManager.getConnection("jdbc:sqlite:spielstaende.db")) {
			System.out.println("Aktueller Spielstand wird gespeichert. Bitte warten...");
			try (Statement statement = connection.createStatement()) {
				// @formatter:off
				statement.execute("CREATE TABLE IF NOT EXISTS  Charakter ("
						+ "  charakter_ID	         INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "  party_ID    	         INTEGER REFERENCES Party(party_ID),"
						+ "  istHauptCharakter    	 BOOLEAN             ,"
						+ "  klasseBezeichnung	     TEXT REFERENCES Klasse(bezeichnung),"
						+ "  name	                 TEXT        		 ,"
						+ "  grafischeDarstellung    TEXT        		 ,"
						+ "  level	                 INTEGER     		 ,"
						+ "  gesundheitsPunkte       INTEGER     		 ,"
						+ "  maxGesundheitsPunkte    INTEGER    		 ,"
						+ "  manaPunkte              INTEGER     		 ,"
						+ "  maxManaPunkte           INTEGER     		 ,"
						+ "  physischeAttacke        INTEGER     		 ,"
						+ "  magischeAttacke         INTEGER     		 ,"
						+ "  genauigkeit             INTEGER     		 ,"
						+ "  verteidigung            INTEGER     		 ,"
						+ "  magischeVerteidigung    INTEGER     		 ,"
						+ "  resitenz                INTEGER     		 ,"
						+ "  beweglichkeit           INTEGER     		 ,"
						+ "  gesundheitsregeneration INTEGER     		 ,"
						+ "  manaRegeneration        	INTEGER     	 ," 
						+ "  geschichte				 	TEXT	     	 ,"
						+ "  erfahrungsPunkte		 	INTEGER	         ,"
						+ "  offeneFaehigkeitspunkte 	INTEGER	     	 ,"
						+ "  verteilteFaehigkeitspunkte INTEGER	     	 ,"
						+ "  offeneAttributpunkte       INTEGER	     	  " + ");");

				statement.execute("CREATE TABLE IF NOT EXISTS   Waffe   ("
						+ "  charakter_ID     	     INTEGER REFERENCES Charakter(charakter_ID),"
						+ "  waffe_ID     	         INTEGER PRIMARY KEY AUTOINCREMENT,"
						+ "  name     	             TEXT        ,"
						+ "  kaufswert     	         INTEGER     ,"
						+ "  verkaufswert     	     INTEGER     ,"
						+ "  kaufbar     	         INTEGER     ,"
						+ "  bonus	                 TEXT        ,"
						+ "  bonusUmfang        	 INTEGER 	 ,"
						+ "  levelAnforderung        INTEGER 	 ,"
						+ "  soeldnerItem     	     INTEGER     ,"
						+ "  attacke        	     INTEGER 	 ,"
						+ "  magischeAttacke         INTEGER 	 ,"
						+ "  waffenTyp	             TEXT         " + ");");

				statement.execute("CREATE TABLE IF NOT EXISTS   Ruestung ("
						+ "  charakter_ID     	     INTEGER REFERENCES Charakter(charakter_ID),"
						+ "  name     	             TEXT        ,"
						+ "  kaufswert     	         INTEGER     ,"
						+ "  verkaufswert     	     INTEGER     ,"
						+ "  kaufbar     	         BOOLEAN     ,"
						+ "  bonus	                 TEXT        ,"
						+ "  bonusUmfang        	 INTEGER 	 ,"
						+ "  levelAnforderung        INTEGER 	 ,"
						+ "  soeldnerItem     	     INTEGER     ,"
						+ "  magischeVerteidigung    INTEGER     ,"
						+ "  verteidigung	         INTEGER     ,"
						+ "  resitenz        	     INTEGER 	 ,"
						+ "  ruestungsTyp            TEXT 	      " + ");");
				
				statement.execute("CREATE TABLE IF NOT EXISTS   Faehigkeit ("
						+ "  charakter_ID     	     INTEGER REFERENCES Charakter(charakter_ID),"
						+ "  name     	             TEXT        ,"
						+ "  beschreibung     	     TEXT        ,"
						+ "  manaKosten     	     INTEGER     ,"
						+ "  level     	 			 INTEGER     ,"
						+ "  levelAnforderung	     INTEGER     ,"
						+ "  istFreundlich           BOOLEAN 	 ,"
						+ "  effektStaerke           INTEGER 	 ,"
						+ "  zielAnzahl     	     INTEGER     ,"
						+ "  dauer                   INTEGER     ,"
						+ "  wahrscheinlichkeit  	 DOUBLE      ,"
						+ "  zielAttribut            TEXT      	  " + ");");
				
				
				statement.execute("CREATE TABLE IF NOT EXISTS   WaffenInventar ("
						+ "  party_ID     	         INTEGER REFERENCES Party(party_ID),"
						+ "  name     	             TEXT        ,"
						+ "  kaufswert     	         INTEGER     ,"
						+ "  verkaufswert     	     INTEGER     ,"
						+ "  kaufbar     	         BOOLEAN     ,"
						+ "  bonus	                 TEXT        ,"
						+ "  bonusUmfang        	 INTEGER 	 ,"
						+ "  levelAnforderung        INTEGER 	 ,"
						+ "  soeldnerItem     	     INTEGER     ,"
						+ "  attacke            	 INTEGER 	 ,"
						+ "  magischeAttacke         INTEGER 	 ,"
						+ "  waffenTyp	             TEXT         " + ");");
				
				statement.execute("CREATE TABLE IF NOT EXISTS   RuestungsInventar ("
						+ "  party_ID     	         INTEGER REFERENCES Party(party_ID),"
						+ "  name     	             TEXT        ,"
						+ "  kaufswert     	         INTEGER     ,"
						+ "  verkaufswert     	     INTEGER     ,"
						+ "  kaufbar     	         BOOLEAN     ,"
						+ "  bonus	                 TEXT        ,"
						+ "  bonusUmfang        	 INTEGER 	 ,"
						+ "  levelAnforderung        INTEGER 	 ,"
						+ "  soeldnerItem     	     INTEGER     ,"
						+ "  magischeVerteidigung    INTEGER     ,"
						+ "  verteidigung	         INTEGER     ,"
						+ "  resitenz        	     INTEGER 	 ,"
						+ "  ruestungsTyp            TEXT 	      " + ");");
				
				statement.execute("CREATE TABLE IF NOT EXISTS   AccessoireInventar ("
						+ "  party_ID     	     INTEGER REFERENCES Charakter(charakter_ID),"
						+ "  name     	             TEXT        ,"
						+ "  kaufswert     	         INTEGER     ,"
						+ "  verkaufswert     	     INTEGER     ,"
						+ "  kaufbar     	         BOOLEAN	 ,"
						+ "  bonus	                 TEXT        ,"
						+ "  bonusUmfang        	 INTEGER 	 ,"
						+ "  levelAnforderung        INTEGER 	 ,"
						+ "  soeldnerItem     	     INTEGER      " + ");");

				statement.execute("CREATE TABLE IF NOT EXISTS   Accessoire ("
						+ "  charakter_ID     	     INTEGER REFERENCES Charakter(charakter_ID),"
						+ "  name     	             TEXT        ,"
						+ "  kaufswert     	         INTEGER     ,"
						+ "  verkaufswert     	     INTEGER     ,"
						+ "  kaufbar     	         BOOLEAN	 ,"
						+ "  bonus	                 TEXT        ,"
						+ "  bonusUmfang        	 INTEGER 	 ,"
						+ "  levelAnforderung        INTEGER 	 ,"
						+ "  soeldnerItem     	     INTEGER      " + ");");

				statement.execute("CREATE TABLE IF NOT EXISTS    Party   ("
						+ "  party_ID    	 		 INTEGER PRIMARY KEY REFERENCES Speicherstand(speicherstand_ID)        ,"
						+ "  hauptCharakter     	 TEXT         ,"
						+ "  nebenCharakter1	     TEXT         ,"
						+ "  nebenCharakter2	     TEXT         ,"
						+ "  nebenCharakter3	     TEXT         ,"
						+ "  gold	     			 INTEGER       " + ");");
				
				
				statement.execute("CREATE TABLE IF NOT EXISTS    Statistik   ("
						+ "  statistik_ID    	         INTEGER PRIMARY KEY REFERENCES Speicherstand(speicherstand_ID)        ,"
						+ "  gesamtErwirtschaftetesGold  INTEGER         													   ,"
						+ "  durchgefuehrteKaempfe	     INTEGER         													   ,"
						+ "  gewonneneKaempfe	     	 INTEGER     														   ,"
						+ "  verloreneKaempfe	     	 INTEGER      														    " + ");");
				
				statement.execute("CREATE TABLE IF NOT EXISTS    Verbrauchsgegenstand             ("
						+ "  party_ID    	 	 INTEGER REFERENCES Party(party_ID)		   		  ,"
						+ "  name	             TEXT         									  ,"
						+ "  kaufswert	         INTEGER         								  ,"
						+ "  verkaufswert	     INTEGER         								  ,"
						+ "  anzahl	             INTEGER" 										 + ");");
				
				statement.execute("CREATE TABLE IF NOT EXISTS    Material			              ("
						+ "  party_ID    	 	 INTEGER REFERENCES Party(party_ID)		   		  ,"
						+ "  name	             TEXT         									  ,"
						+ "  kaufswert	         INTEGER         								  ,"
						+ "  verkaufswert	     INTEGER         								  ,"
						+ "  anzahl	             INTEGER" + ");");
				
				statement.execute("CREATE TABLE IF NOT EXISTS    Speicherstand   ("
						+ "  speicherstand_ID   	 INTEGER PRIMARY KEY AUTOINCREMENT        ,"
						+ "  speicherstand_name   	 TEXT       							  ,"
						+ "  schwierigkeitsgrad   	 TEXT        							  ,"
						+ "  hardcore  	 			 BOOLEAN        						  ,"
						+ "  datum                   DATE 	                                   " + ");");
				// @formatter:on
			}

			// Erstelle einen neuen Spielstand zum Abspeichern mit Schwierigkeit, Hardcore
			// und Datum
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Speicherstand (speicherstand_name, schwierigkeitsgrad, hardcore, datum) VALUES (?, ?, ?, ?);")) {
				preparedStatement.setString(1, speicherstand.getParty().getHauptCharakter().getName());
				preparedStatement.setString(2, speicherstand.getSchwierigkeitsgrad());
				preparedStatement.setBoolean(3, speicherstand.isHardcore());
				preparedStatement.setObject(4, LocalDateTime.now());
				preparedStatement.execute();
			}
			int speicherstand_ID;
			try (Statement statement = connection.createStatement()) {
				ResultSet resultset = statement.executeQuery("SELECT MAX(speicherstand_ID) FROM Speicherstand;");
				resultset.next();
				speicherstand_ID = resultset.getInt(1);

			}
			SpielerCharakter hauptCharakter = speicherstand.getParty().getHauptCharakter();

			// Save Party ohne Charaktere
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Party (party_ID, hauptCharakter, klasseBezeichnung, nebenCharakter2, nebenCharakter3, gold) VALUES (?, ?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, speicherstand_ID);
				preparedStatement.setString(2, hauptCharakter.getName());
				for (int counter = 0; counter < 3; counter++) {
					if (speicherstand.getParty().getNebenCharakter()[counter] != null) {
						preparedStatement.setString(3 + counter,
								speicherstand.getParty().getNebenCharakter()[counter].getName());
					}
					else {
						preparedStatement.setString(3 + counter, null);
					}
				}
				preparedStatement.setInt(6, speicherstand.getParty().getGold());
				preparedStatement.execute();
			}

			// Save Hauptcharakter der Party
			saveSpielerCharakter(speicherstand_ID, hauptCharakter, connection, true);

			// Save Nebencharaktere der Party
			for (int counter = 0, len = speicherstand.getParty().getNebenCharakter().length; counter < len; counter++) {
				saveSpielerCharakter(speicherstand_ID, speicherstand.getParty().getNebenCharakter()[counter],
						connection, false);
			}

			// Speichern der aktuellen Statistik
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Statistik (statistik_ID, gesamtErwirtschaftetesGold, durchgefuehrteKaempfe, gewonneneKaempfe, verloreneKaempfe) VALUES (?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, speicherstand_ID);
				preparedStatement.setInt(2, speicherstand.getStatistik().getGesamtErwirtschaftetesGold());
				preparedStatement.setInt(3, speicherstand.getStatistik().getDurchgefuehrteKaempfe());
				preparedStatement.setInt(4, speicherstand.getStatistik().getGewonneneKaempfe());
				preparedStatement.setInt(5, speicherstand.getStatistik().getVerloreneKaempfe());
				preparedStatement.execute();
			}

			// Speichern aller Gegenstaende im Inventar (Verbrauchsgegenstaende und
			// Materialien)

			for (Entry<Verbrauchsgegenstand, Integer> entry : speicherstand.getParty().getVerbrauchsgegenstaende()
					.entrySet()) {
				Verbrauchsgegenstand item = entry.getKey();
				int itemAnzahl = entry.getValue();
				try (final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO Verbrauchsgegenstand (party_ID, name, kaufswert, verkaufswert, anzahl) VALUES (?, ?, ?, ?, ?);")) {
					preparedStatement.setInt(1, speicherstand_ID);
					preparedStatement.setString(2, item.getName());
					preparedStatement.setInt(3, item.getKaufwert());
					preparedStatement.setInt(4, item.getVerkaufswert());
					preparedStatement.setInt(5, itemAnzahl);
					preparedStatement.execute();
				}
			}

			for (Entry<Material, Integer> entry : speicherstand.getParty().getMaterialien().entrySet()) {
				Material material = entry.getKey();
				int materialAnzahl = entry.getValue();
				try (final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO Material (party_ID, name, kaufswert, verkaufswert, anzahl) VALUES (?, ?, ?, ?, ?);")) {
					preparedStatement.setInt(1, speicherstand_ID);
					preparedStatement.setString(2, material.getName());
					preparedStatement.setInt(3, material.getKaufswert());
					preparedStatement.setInt(4, material.getVerkaufswert());
					preparedStatement.setInt(5, materialAnzahl);
					preparedStatement.execute();
				}
			}

			for (int counter = 0, len = speicherstand.getParty().getWaffen().length; counter < len; counter++) {
				try (final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO WaffenInventar (party_ID, name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem, attacke, magischeAttacke, waffenTyp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
					preparedStatement.setInt(1, speicherstand_ID);
					preparedStatement.setString(2, speicherstand.getParty().getWaffen()[counter].getName());
					preparedStatement.setInt(3, speicherstand.getParty().getWaffen()[counter].getKaufswert());
					preparedStatement.setInt(4, speicherstand.getParty().getWaffen()[counter].getVerkaufswert());
					preparedStatement.setBoolean(5, speicherstand.getParty().getWaffen()[counter].istKaufbar());
					preparedStatement.setString(6,
							speicherstand.getParty().getWaffen()[counter].getBonus().getBezeichnung());
					preparedStatement.setInt(7, speicherstand.getParty().getWaffen()[counter].getBonusUmfang());
					preparedStatement.setInt(8, speicherstand.getParty().getWaffen()[counter].getLevelAnforderung());
					preparedStatement.setBoolean(9, speicherstand.getParty().getWaffen()[counter].istSoeldnerItem());
					preparedStatement.setInt(10, speicherstand.getParty().getWaffen()[counter].getAttacke());
					preparedStatement.setInt(11, speicherstand.getParty().getWaffen()[counter].getMagischeAttacke());
					preparedStatement.setString(12, speicherstand.getParty().getWaffen()[counter].getWaffenTyp());
					preparedStatement.execute();
				}
			}

			for (int counter = 0, len = speicherstand.getParty().getRuestungen().length; counter < len; counter++) {
				try (final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO RuestungsInventar (party_ID, name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem, magischeAttacke, attacke, resistenz, ruestungsTyp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
					preparedStatement.setInt(1, speicherstand_ID);
					preparedStatement.setString(2, speicherstand.getParty().getRuestungen()[counter].getName());
					preparedStatement.setInt(3, speicherstand.getParty().getRuestungen()[counter].getKaufswert());
					preparedStatement.setInt(4, speicherstand.getParty().getRuestungen()[counter].getVerkaufswert());
					preparedStatement.setBoolean(5, speicherstand.getParty().getRuestungen()[counter].istKaufbar());
					preparedStatement.setString(6,
							speicherstand.getParty().getRuestungen()[counter].getBonus().getBezeichnung());
					preparedStatement.setInt(7, speicherstand.getParty().getRuestungen()[counter].getBonusUmfang());
					preparedStatement.setInt(8,
							speicherstand.getParty().getRuestungen()[counter].getLevelAnforderung());
					preparedStatement.setBoolean(9,
							speicherstand.getParty().getRuestungen()[counter].istSoeldnerItem());
					preparedStatement.setInt(10,
							speicherstand.getParty().getRuestungen()[counter].getMagischeVerteidigung());
					preparedStatement.setInt(11, speicherstand.getParty().getRuestungen()[counter].getVerteidigung());
					preparedStatement.setInt(12, speicherstand.getParty().getRuestungen()[counter].getResistenz());
					preparedStatement.setString(13,
							speicherstand.getParty().getRuestungen()[counter].getRuestungsTyp());
					preparedStatement.execute();
				}
			}

			for (int counter = 0, len = speicherstand.getParty().getAccessoires().length; counter < len; counter++) {
				try (final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO AccessoirInventar (party_ID, name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
					preparedStatement.setInt(1, speicherstand_ID);
					preparedStatement.setString(2, speicherstand.getParty().getAccessoires()[counter].getName());
					preparedStatement.setInt(3, speicherstand.getParty().getAccessoires()[counter].getKaufswert());
					preparedStatement.setInt(4, speicherstand.getParty().getAccessoires()[counter].getVerkaufswert());
					preparedStatement.setBoolean(5, speicherstand.getParty().getAccessoires()[counter].istKaufbar());
					preparedStatement.setString(6,
							speicherstand.getParty().getAccessoires()[counter].getBonus().getBezeichnung());
					preparedStatement.setInt(7, speicherstand.getParty().getAccessoires()[counter].getBonusUmfang());
					preparedStatement.setInt(8,
							speicherstand.getParty().getAccessoires()[counter].getLevelAnforderung());
					preparedStatement.setBoolean(9,
							speicherstand.getParty().getAccessoires()[counter].istSoeldnerItem());
					preparedStatement.execute();
				}
			}

		}
		System.out.println("Speichern erfolgreich");
	}

	private void saveSpielerCharakter(int speicherstand_ID, SpielerCharakter charakter, final Connection connection,
			boolean istHauptCharakter) throws SQLException {
		// Gibt es den Charakter? (Wichtig beim Nebencharakter-Array!)
		if (charakter != null) {
			// Speicher die Charakterdaten vom aktuellen Charakter
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Charakter (party_ID, istHauptCharakter, klasseBezeichnung, name, grafischeDarstellung, level, gesundheitsPunkte, maxGesundheitsPunkte, manaPunkte, maxManaPunkte, physischeAttacke, magischeAttacke, genauigkeit, verteidigung, magischeVerteidigung, resitenz, beweglichkeit, gesundheitsregeneration, manaRegeneration, geschichte, erfahrungsPunkte, offeneFaehigkeitspunkte, verteilteFaehigkeitspunkte, offeneAttributpunkte) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, speicherstand_ID);
				preparedStatement.setBoolean(2, istHauptCharakter);
				preparedStatement.setString(3, charakter.getKlasse().getBezeichnung());
				preparedStatement.setString(4, charakter.getName());
				preparedStatement.setString(5, charakter.getGrafischeDarstellung());
				preparedStatement.setInt(6, charakter.getLevel());
				preparedStatement.setInt(7, charakter.getGesundheitsPunkte());
				preparedStatement.setInt(8, charakter.getMaxGesundheitsPunkte());
				preparedStatement.setInt(9, charakter.getManaPunkte());
				preparedStatement.setInt(10, charakter.getMaxManaPunkte());
				preparedStatement.setInt(11, charakter.getPhysischeAttacke());
				preparedStatement.setInt(12, charakter.getMagischeAttacke());
				preparedStatement.setInt(13, charakter.getGenauigkeit());
				preparedStatement.setInt(14, charakter.getVerteidigung());
				preparedStatement.setInt(15, charakter.getMagischeVerteidigung());
				preparedStatement.setInt(16, charakter.getResistenz());
				preparedStatement.setInt(17, charakter.getBeweglichkeit());
				preparedStatement.setInt(18, charakter.getGesundheitsRegeneration());
				preparedStatement.setInt(19, charakter.getManaRegeneration());
				preparedStatement.execute();
			}
			int aktuelleCharakter_ID;
			try (Statement statement = connection.createStatement()) {
				ResultSet resultset = statement.executeQuery("SELECT MAX(charakter_ID) FROM Charakter;");
				resultset.next();
				aktuelleCharakter_ID = resultset.getInt(1);
			}

			// Speicher die ausgeruestete Waffe des aktuellen Charakters
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Waffe (charakter_ID, name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem, attacke, magischeAttacke, waffenTyp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, aktuelleCharakter_ID);
				preparedStatement.setString(2, charakter.getWaffe().getName());
				preparedStatement.setInt(3, charakter.getWaffe().getKaufwert());
				preparedStatement.setInt(4, charakter.getWaffe().getVerkaufswert());
				preparedStatement.setBoolean(5, charakter.getWaffe().isIstNichtKaufbar());
				preparedStatement.setString(6, charakter.getWaffe().getBonus().getBezeichnung());
				preparedStatement.setInt(7, charakter.getWaffe().getBonusUmfang());
				preparedStatement.setInt(8, charakter.getWaffe().getLevelAnforderung());
				preparedStatement.setBoolean(9, charakter.getWaffe().isIstSoeldnerItem());
				preparedStatement.setInt(10, charakter.getWaffe().getAttacke());
				preparedStatement.setInt(11, charakter.getWaffe().getMagischeAttacke());
				preparedStatement.setString(12, charakter.getWaffe().getWaffenTyp());
				preparedStatement.execute();
			}

			// Speichert die Faehigkeiten des aktuellen Charakters
			for (int counter = 0, len = charakter.getFaehigkeiten().size(); counter < len; counter++) {
				try (final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO Faehigkeit (charakter_ID, name, beschreibung, manaKosten, level, levelAnforderung, istFreundlich, effektStaerke, zielAnzahl, dauer, wahrscheinlichkeit, zielAttribut) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
					preparedStatement.setInt(1, aktuelleCharakter_ID);
					preparedStatement.setString(2, charakter.getFaehigkeiten().get(counter).getName());
					preparedStatement.setString(3, charakter.getFaehigkeiten().get(counter).getBeschreibung());
					preparedStatement.setInt(4, charakter.getFaehigkeiten().get(counter).getManaKosten());
					preparedStatement.setInt(5, charakter.getFaehigkeiten().get(counter).getLevel());
					preparedStatement.setInt(6, charakter.getFaehigkeiten().get(counter).getLevelAnforderung());
					preparedStatement.setBoolean(7, charakter.getFaehigkeiten().get(counter).getIstFreundlich());
					preparedStatement.setInt(8, charakter.getFaehigkeiten().get(counter).getEffektStaerke());
					preparedStatement.setInt(9, charakter.getFaehigkeiten().get(counter).getZielAnzahl());
					preparedStatement.setInt(10, charakter.getFaehigkeiten().get(counter).getDauer());
					preparedStatement.setDouble(11, charakter.getFaehigkeiten().get(counter).getWahrscheinlichkeit());
					preparedStatement.setString(12, charakter.getFaehigkeiten().get(counter).getZielAttribut());
					preparedStatement.execute();
				}
			}

			// Speicher die ausgeruestete Ruestung des aktuellen Charakters
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Ruestung (charakter_ID, name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem, magischeVerteidigung, verteidigung, resitenz, ruestungsTyp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, aktuelleCharakter_ID);
				preparedStatement.setString(2, charakter.getRuestung().getName());
				preparedStatement.setInt(3, charakter.getRuestung().getKaufwert());
				preparedStatement.setInt(4, charakter.getRuestung().getVerkaufswert());
				preparedStatement.setBoolean(5, charakter.getRuestung().isIstNichtKaufbar());
				preparedStatement.setString(6, charakter.getRuestung().getBonus().getBezeichnung());
				preparedStatement.setInt(7, charakter.getRuestung().getBonusUmfang());
				preparedStatement.setInt(8, charakter.getRuestung().getLevelAnforderung());
				preparedStatement.setBoolean(9, charakter.getRuestung().isIstSoeldnerItem());
				preparedStatement.setInt(10, charakter.getRuestung().getmVtg());
				preparedStatement.setInt(11, charakter.getRuestung().getpVtg());
				preparedStatement.setInt(12, charakter.getRuestung().getResistenz());
				preparedStatement.setString(13, charakter.getRuestung().getRuestungsTyp());
				preparedStatement.execute();
			}

			// Speicher die ausgeruesteten Accessoires des aktuellen Charakters
			for (int counter = 0; counter < 3; counter++) {
				// Der aktulelle Accessoire-Slot vom aktuellen Charakter ist befuellt und
				// Attribute werden gespeichert
				if (charakter.getAccessoires()[counter] != null) {
					try (final PreparedStatement preparedStatement = connection.prepareStatement(
							"INSERT INTO Accessoire (charakter_ID, name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
						preparedStatement.setInt(1, aktuelleCharakter_ID);
						preparedStatement.setString(2, charakter.getAccessoires()[counter].getName());
						preparedStatement.setInt(3, charakter.getAccessoires()[counter].getKaufwert());
						preparedStatement.setInt(4, charakter.getAccessoires()[counter].getVerkaufswert());
						preparedStatement.setBoolean(5, charakter.getAccessoires()[counter].isIstNichtKaufbar());
						preparedStatement.setString(6, charakter.getAccessoires()[counter].getBonus().getBezeichnung());
						preparedStatement.setInt(7, charakter.getAccesssoires()[counter].getBonusUmfang());
						preparedStatement.setInt(8, charakter.getAccessoires()[counter].getLevelAnforderung());
						preparedStatement.setBoolean(9, charakter.getAccessoires()[counter].isIstSoeldnerItem());
						preparedStatement.execute();
					}
				}
				// Der aktulelle Accessoire-Slot vom aktuellen Charakter ist nicht befuellt und
				// ein leer-Accessoire wird gespeichert
				else {
					try (final PreparedStatement preparedStatement = connection.prepareStatement(
							"INSERT INTO Accessoire (charakter_ID, name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
						preparedStatement.setInt(1, aktuelleCharakter_ID);
						preparedStatement.setString(2, null);
						preparedStatement.setInt(3, -1);
						preparedStatement.setInt(4, -1);
						preparedStatement.setBoolean(5, false);
						preparedStatement.setString(6, null);
						preparedStatement.setInt(7, -1);
						preparedStatement.setInt(8, -1);
						preparedStatement.setBoolean(9, false);
						preparedStatement.execute();
					}
				}
			}
		}
		// Der Nebencharakterslot ist leer
		else {
			// Leerer Charakter wird erstellt
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Charakter (party_ID, istHauptCharakter, klasseBezeichnung, name, grafischeDarstellung, level, gesundheitsPunkte, maxGesundheitsPunkte, manaPunkte, maxManaPunkte, physischeAttacke, magischeAttacke, genauigkeit, verteidigung, magischeVerteidigung, resitenz, beweglichkeit, gesundheitsregeneration, manaRegeneration) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, speicherstand_ID);
				preparedStatement.setBoolean(2, istHauptCharakter);
				preparedStatement.setString(3, null);
				preparedStatement.setString(4, null);
				preparedStatement.setString(5, null);
				preparedStatement.setInt(6, -1);
				preparedStatement.setInt(7, -1);
				preparedStatement.setInt(8, -1);
				preparedStatement.setInt(9, -1);
				preparedStatement.setInt(10, -1);
				preparedStatement.setInt(11, -1);
				preparedStatement.setInt(12, -1);
				preparedStatement.setInt(13, -1);
				preparedStatement.setInt(14, -1);
				preparedStatement.setInt(15, -1);
				preparedStatement.setInt(16, -1);
				preparedStatement.setInt(17, -1);
				preparedStatement.setInt(18, -1);
				preparedStatement.setInt(19, -1);
				preparedStatement.execute();
			}
			int aktuelleCharakter_ID;
			try (Statement statement = connection.createStatement()) {
				ResultSet resultset = statement.executeQuery("SELECT MAX(charakter_ID) FROM Charakter;");
				resultset.next();
				aktuelleCharakter_ID = resultset.getInt(1);
			}
			// Leere Waffe wird erstellt
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Waffe (charakter_ID, name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem, attacke, magischeAttacke, waffenTyp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, aktuelleCharakter_ID);
				preparedStatement.setString(2, null);
				preparedStatement.setInt(3, -1);
				preparedStatement.setInt(4, -1);
				preparedStatement.setBoolean(5, false);
				preparedStatement.setString(6, null);
				preparedStatement.setInt(7, -1);
				preparedStatement.setInt(8, -1);
				preparedStatement.setBoolean(9, false);
				preparedStatement.setInt(10, -1);
				preparedStatement.setInt(11, -1);
				preparedStatement.setString(12, null);
				preparedStatement.execute();
			}
			// Leere Ruestung wird erstellt
			try (final PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO Ruestung (charakter_ID, name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem, magischeVerteidigung, verteidigung, resitenz, ruestungsTyp) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
				preparedStatement.setInt(1, aktuelleCharakter_ID);
				preparedStatement.setString(2, null);
				preparedStatement.setInt(3, -1);
				preparedStatement.setInt(4, -1);
				preparedStatement.setBoolean(5, false);
				preparedStatement.setString(6, null);
				preparedStatement.setInt(7, -1);
				preparedStatement.setInt(8, -1);
				preparedStatement.setBoolean(9, false);
				preparedStatement.setInt(10, -1);
				preparedStatement.setInt(11, -1);
				preparedStatement.setInt(12, -1);
				preparedStatement.setString(13, null);
				preparedStatement.execute();
			}
			// Drei Leere Accessoire-Slots werden erstellt fuer den leeren Charakter
			for (int counter = 0; counter < 3; counter++) {
				try (final PreparedStatement preparedStatement = connection.prepareStatement(
						"INSERT INTO Accessoire (charakter_ID, name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
					preparedStatement.setInt(1, aktuelleCharakter_ID);
					preparedStatement.setString(2, null);
					preparedStatement.setInt(3, -1);
					preparedStatement.setInt(4, -1);
					preparedStatement.setBoolean(5, false);
					preparedStatement.setString(6, null);
					preparedStatement.setInt(7, -1);
					preparedStatement.setInt(8, -1);
					preparedStatement.setBoolean(9, false);
					preparedStatement.execute();
				}
			}

		}
	}

	/**
	 * lässt den Nutzer den Speicherstand auswählen und laden
	 * 
	 * @author Melvin
	 * @throws SQLException
	 * @since 16.11.2023
	 */
	public Speicherstand speicherstandAuswahl() throws SQLException {
		int zuLadenderSpeicherstand_ID = 0;
		int aktuelleCharakter_ID = 0;
		try (Connection connection = DriverManager.getConnection("jdbc:sqlite:spielstaende.db")) {
			System.out.println("Vorhandene Spielstaende: ");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement
					.executeQuery("SELECT speicherstand_name, datum, speicherstand_ID FROM Speicherstand;");
			if (!resultSet.next()) {
				System.out.println("Keine gespeicherten Daten vorhanden :(");
			}
			else {
				while (resultSet.next()) {
					int counter = 1;
					System.out.println(counter + ". " + resultSet.getString(1) + " | " + resultSet.getString(2));
					counter++;
				}
				int poitionAuswahlSpielstand = 0;
				boolean istEingabeValide = true;
				System.out.println("Welcher Spielstand soll geladen werden? (Zahl vor Spielstand eingeben): ");
				do {
					try {
						poitionAuswahlSpielstand = ScannerHelfer.nextInt();
					} catch (Exception e) {
						System.out.println(
								"Eingabe fehlerhaft. Bitte die Zahl vor dem Spielstand eingeben, der geladen werden soll.");
						istEingabeValide = false;
					}
				} while (!istEingabeValide);
				resultSet.first();
				int aktuelleZeile = 1;
				while ((aktuelleZeile < poitionAuswahlSpielstand) && resultSet.next()) {
					aktuelleZeile++;
				}
				zuLadenderSpeicherstand_ID = resultSet.getInt(3);
				System.out.println("Spielstand " + resultSet.getString(1) + "(ID: " + resultSet.getInt(3) + ")"
						+ "wird geladen... Bitte warten");
			}
			ResultSet resultset = statement.executeQuery(
					"SELECT name, klasseBezeichnung, grafischeDarstellung, level, gesundheitsPunkte, maxGesundheitsPunkte, manaPunkte, maxManaPunkte, physischeAttacke, magischeAttacke, genauigkeit, verteidigung, magischeVerteidigung, resitenz, beweglichkeit, gesundheitsregeneration, manaRegeneration, geschichte, erfahrungsPunkte, offeneFaehigkeitspunkte, verteilteFaehigkeitspunkte, offeneAttributpunkte, charakter_ID FROM Charakter WHERE party_ID ="
							+ zuLadenderSpeicherstand_ID + " AND istHauptCharakter =" + true + ";");
			resultset.next();
			aktuelleCharakter_ID = resultSet.getInt("charakter_ID");
			SpielerCharakter hauptCharakter = new SpielerCharakter(resultSet.getString("name"),
					resultSet.getString("klasseBezeichnung"), resultSet.getString("geschichte"));
			hauptCharakter.setGrafischeDarstellung(resultSet.getString("grafischeDarstellung"));
			hauptCharakter.setLevel(resultSet.getInt("level"));
			hauptCharakter.setGesundheitsPunkte(resultSet.getInt("gesundheitsPunkte"));
			hauptCharakter.setMaxGesundheitsPunkte(resultSet.getInt("maxGesundheitsPunkte"));
			hauptCharakter.setManaPunkte(resultSet.getInt("manaPunkte"));
			hauptCharakter.setMaxManaPunkte(resultSet.getInt("maxManaPunkte"));
			hauptCharakter.setPhysischeAttacke(resultSet.getInt("physischeAttacke"));
			hauptCharakter.setMagischeAttacke(resultSet.getInt("magischeAttacke"));
			hauptCharakter.setGenauigkeit(resultSet.getInt("genauigkeit"));
			hauptCharakter.setVerteidigung(resultSet.getInt("verteidigung"));
			hauptCharakter.setMagischeVerteidigung(resultSet.getInt("magischeVerteidigung"));
			hauptCharakter.setResistenz(resultSet.getInt("resistenz"));
			hauptCharakter.setBeweglichkeit(resultSet.getInt("beweglichkeit"));
			hauptCharakter.setGesundheitsRegeneration(resultSet.getInt("gesundheitsregeneration"));
			hauptCharakter.setManaRegeneration(resultSet.getInt("manaRegeneration"));
			hauptCharakter.setErfahrungsPunkte(resultSet.getInt("erfahrungspunkte"));
			hauptCharakter.setOffeneFaehigkeitspunkte(resultSet.getInt("offeneFaehigkeitspunkte"));
			hauptCharakter.setVerteilteFaehigkeitspunkte(resultSet.getInt("verteilteFaehigkeitspunkte"));
			hauptCharakter.setOffeneAttributpunkte(resultSet.getInt("offeneAttributpunkte"));

			resultset = statement.executeQuery(
					"SELECT name, beschreibung, manaKosten, faehigkeitsTyp, magischeAttacke, attacke, heilung, freundlich, goldKosten, faehigkeitspunktKosten, klasseBezeichnung, levelAnforderung, charakter_ID FROM Faehigkeit WHERE party_ID ="
							+ zuLadenderSpeicherstand_ID + " AND charakter_ID =" + aktuelleCharakter_ID + ";");

			// TODO Faehigkeiten Hauptcharakter
			List<Faehigkeit> hauptCharakterFaehigkeiten = new ArrayList<>();
			while (resultSet.next()) {
				Faehigkeit faehigkeit = new Faehigkeit();
				hauptCharakterFaehigkeiten.add(faehigkeit);
			}

			// TODO Waffe Hauptcharakter
			resultset = statement.executeQuery(
					"SELECT charakter_ID, name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem, attacke, magischeAttacke, waffenTyp FROM Waffe WHERE charakter_ID ="
							+ aktuelleCharakter_ID + ";");
			Waffe waffe = new Waffe(null, 0);
			hauptCharakter.setWaffe(waffe);

			// TODO Ruestung Hauptcharakter
			resultset = statement.executeQuery(
					"SELECT charakter_ID, name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem, magischeVerteidigung, verteidigung, resistenz, ruestungsTyp FROM Ruestung WHERE charakter_ID ="
							+ aktuelleCharakter_ID + ";");
			Ruestung ruestung = new Ruestung(null, 0);
			hauptCharakter.setRuestung(ruestung);

			// TODO Accessoires Hauptcharakter
			resultset = statement.executeQuery(
					"SELECT charakter_ID, name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem FROM Accessoire WHERE charakter_ID ="
							+ aktuelleCharakter_ID + ";");
			Accessoire[] hauptcharakterAccessoires = new Accessoire[3];
			int accessoireCounter = 0;
			while (resultSet.next()) {
				if (resultSet.getString("name") != null) {
					Accessoire accessoire = new Accessoire(0);
				}
				else {
					hauptcharakterAccessoires[accessoireCounter] = null;
				}
				accessoireCounter++;
			}
			hauptCharakter.setAccessoires(hauptcharakterAccessoires);

			SpielerCharakter[] nebenCharaktere = new SpielerCharakter[3];
			ResultSet resultsetNebencharaktere = statement.executeQuery(
					"SELECT name, klasseBezeichnung, grafischeDarstellung, level, gesundheitsPunkte, maxGesundheitsPunkte, manaPunkte, maxManaPunkte, physischeAttacke, magischeAttacke, genauigkeit, verteidigung, magischeVerteidigung, resitenz, beweglichkeit, gesundheitsregeneration, manaRegeneration, geschichte, erfahrungsPunkte, offeneFaehigkeitspunkte, verteilteFaehigkeitspunkte, offeneAttributpunkte, charakter_ID FROM Charakter WHERE party_ID ="
							+ zuLadenderSpeicherstand_ID + " AND istHauptCharakter =" + false + ";");
			int nebenCharaktereCounter = 0;
			while (resultsetNebencharaktere.next()) {
				aktuelleCharakter_ID = resultsetNebencharaktere.getInt("charakter_ID");
				SpielerCharakter nebenCharakter = new SpielerCharakter(resultsetNebencharaktere.getString("name"),
						resultsetNebencharaktere.getString("klasseBezeichnung"),
						resultsetNebencharaktere.getString("geschichte"));
				nebenCharakter.setGrafischeDarstellung(resultsetNebencharaktere.getString("grafischeDarstellung"));
				nebenCharakter.setLevel(resultsetNebencharaktere.getInt("level"));
				nebenCharakter.setGesundheitsPunkte(resultsetNebencharaktere.getInt("gesundheitsPunkte"));
				nebenCharakter.setMaxGesundheitsPunkte(resultsetNebencharaktere.getInt("maxGesundheitsPunkte"));
				nebenCharakter.setManaPunkte(resultsetNebencharaktere.getInt("manaPunkte"));
				nebenCharakter.setMaxManaPunkte(resultsetNebencharaktere.getInt("maxManaPunkte"));
				nebenCharakter.setPhysischeAttacke(resultsetNebencharaktere.getInt("physischeAttacke"));
				nebenCharakter.setMagischeAttacke(resultsetNebencharaktere.getInt("magischeAttacke"));
				nebenCharakter.setGenauigkeit(resultsetNebencharaktere.getInt("genauigkeit"));
				nebenCharakter.setVerteidigung(resultsetNebencharaktere.getInt("verteidigung"));
				nebenCharakter.setMagischeVerteidigung(resultsetNebencharaktere.getInt("magischeVerteidigung"));
				nebenCharakter.setResistenz(resultsetNebencharaktere.getInt("resistenz"));
				nebenCharakter.setBeweglichkeit(resultsetNebencharaktere.getInt("beweglichkeit"));
				nebenCharakter.setGesundheitsRegeneration(resultsetNebencharaktere.getInt("gesundheitsregeneration"));
				nebenCharakter.setManaRegeneration(resultsetNebencharaktere.getInt("manaRegeneration"));
				nebenCharakter.setErfahrungsPunkte(resultsetNebencharaktere.getInt("erfahrungspunkte"));
				nebenCharakter.setOffeneFaehigkeitspunkte(resultsetNebencharaktere.getInt("offeneFaehigkeitspunkte"));
				nebenCharakter
						.setVerteilteFaehigkeitspunkte(resultsetNebencharaktere.getInt("verteilteFaehigkeitspunkte"));
				nebenCharakter.setOffeneAttributpunkte(resultsetNebencharaktere.getInt("offeneAttributpunkte"));

				// TODO Faehigkeiten Hauptcharakter
				resultset = statement.executeQuery(
						"SELECT name, beschreibung, manaKosten, faehigkeitsTyp, magischeAttacke, attacke, heilung, freundlich, goldKosten, faehigkeitspunktKosten, klasseBezeichnung, levelAnforderung, charakter_ID FROM Faehigkeit WHERE party_ID ="
								+ zuLadenderSpeicherstand_ID + " AND charakter_ID =" + aktuelleCharakter_ID + ";");
				List<Faehigkeit> nebenCharakterFaehigkeiten = new ArrayList<>();
				while (resultSet.next()) {
					Faehigkeit faehigkeit = new Faehigkeit();
					nebenCharakterFaehigkeiten.add(faehigkeit);
				}

				// TODO Waffe Nebencharaktere
				resultset = statement.executeQuery(
						"SELECT name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem, attacke, magischeAttacke, waffenTyp FROM Waffe WHERE charakter_ID ="
								+ aktuelleCharakter_ID + ";");
				nebenCharakter.setWaffe(new Waffe(null, 0));

				// TODO Ruestung Nebencharaktere
				resultset = statement.executeQuery(
						"SELECT name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem, magischeVerteidigung, verteidigung, resistenz, ruestungsTyp FROM Ruestung WHERE charakter_ID ="
								+ aktuelleCharakter_ID + ";");
				nebenCharakter.setRuestung(new Ruestung());

				// TODO Accessoires Hauptcharakter
				resultset = statement.executeQuery(
						"SELECT name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem FROM Accessoire WHERE charakter_ID ="
								+ aktuelleCharakter_ID + ";");
				Accessoire[] nebenCharakterAccessoires = new Accessoire[3];
				accessoireCounter = 0;
				while (resultSet.next()) {
					if (resultSet.getString("name") != null) {
						Accessoire accessoire = new Accessoire(0);
					}
					else {
						nebenCharakterAccessoires[accessoireCounter] = null;
					}
					accessoireCounter++;
				}
				nebenCharakter.setAccessoires(nebenCharakterAccessoires);
				nebenCharaktere[nebenCharaktereCounter] = nebenCharakter;
				nebenCharaktereCounter++;
			}
			int zuLadendePartyGold = 0;
			List<Accessoire> zuLadendePartyAccessoireInventar = new ArrayList<>();
			List<Waffe> zuLadendePartyWaffenInventar = new ArrayList<>();
			List<Ruestung> zuLadendePartyRuestungsInventar = new ArrayList<>();
			Map<Material, Integer> zuLadendePartyMaterialien = new HashMap<Material, Integer>();
			Map<Verbrauchsgegenstand, Integer> zuLadendePartyVerbrauchsgegenstaende = new HashMap<Verbrauchsgegenstand, Integer>();

			// TODO AccessoireInventar laden sobald Accessoire-Constructor vorhanden
			resultset = statement.executeQuery(
					"SELECT name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem FROM AccessoireInventar WHERE party_ID ="
							+ zuLadenderSpeicherstand_ID + ";");
			while (resultSet.next()) {
				zuLadendePartyAccessoireInventar.add(new Accessoire(0));
			}
			// TODO WaffenInventar laden sobald Waffen-Constructor vorhanden
			resultset = statement.executeQuery(
					"SELECT name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem, attacke, magischeAttacke, waffenTyp FROM WaffenInventar WHERE party_ID ="
							+ zuLadenderSpeicherstand_ID + ";");
			while (resultSet.next()) {
				zuLadendePartyWaffenInventar.add(new Waffe(null, 0));
			}
			// TODO RuestungsInventar laden sobald Ruestung-Constructor vorhanden
			resultset = statement.executeQuery(
					"SELECT name, kaufswert, verkaufswert, kaufbar, bonus, bonusUmfang, levelAnforderung, soeldnerItem, magischeVerteidigung, verteidigung, resitenz, ruestungsTyp FROM WaffenInventar WHERE party_ID ="
							+ zuLadenderSpeicherstand_ID + ";");
			while (resultSet.next()) {
				zuLadendePartyRuestungsInventar.add(new Ruestung(null, 0));
			}

			// TODO Party-Materialien laden sobald Material-Constructor vorhanden
			resultset = statement
					.executeQuery("SELECT name, kaufswert, verkaufswert, kaufbar, anzahl FROM Material WHERE party_ID ="
							+ zuLadenderSpeicherstand_ID + ";");
			while (resultSet.next()) {
				zuLadendePartyMaterialien.put(new Material(), resultSet.getInt("anzahl"));
			}

			// TODO Party-Verbrauchsgegenstaende laden sobald
			// Verbrauchsgegenstand-Constructor vorhanden
			resultset = statement.executeQuery(
					"SELECT name, kaufswert, verkaufswert, kaufbar, anzahl FROM Verbrauchsgegenstand WHERE party_ID ="
							+ zuLadenderSpeicherstand_ID + ";");
			while (resultSet.next()) {
				zuLadendePartyVerbrauchsgegenstaende.put(new Verbrauchsgegenstand(), resultSet.getInt("anzahl"));
			}

			resultset = statement
					.executeQuery("SELECT gold FROM Party WHERE party_ID =" + zuLadenderSpeicherstand_ID + ";");
			zuLadendePartyGold = resultSet.getInt("gold");
			Party zuLadendeParty = new Party(hauptCharakter.getName(), hauptCharakter.getKlasse().getBezeichnung());
			zuLadendeParty.setHauptCharakter(hauptCharakter);
			zuLadendeParty.setNebenCharakter(nebenCharaktere);
			zuLadendeParty.setGold(zuLadendePartyGold);
			zuLadendeParty.setAccessoires(zuLadendePartyAccessoireInventar);
			zuLadendeParty.setWaffen(zuLadendePartyWaffenInventar);
			zuLadendeParty.setRuestungen(zuLadendePartyRuestungsInventar);
			zuLadendeParty.setMaterialien(zuLadendePartyMaterialien);
			zuLadendeParty.setVerbrauchsgegenstaende(zuLadendePartyVerbrauchsgegenstaende);

			Statistik zuLadendeStatistik = new Statistik();
			String schwierigkeitsgrad = null;
			boolean istHardcore = false;
			resultset = statement
					.executeQuery("SELECT schwierigkeitsgrad, hardcore FROM Speicherstand WHERE speicherstand_ID ="
							+ zuLadenderSpeicherstand_ID + ";");
			schwierigkeitsgrad = resultSet.getString("schwierigkeitsgrad");
			istHardcore = resultSet.getBoolean("hardcore");
			resultset = statement.executeQuery(
					"SELECT gesamtErwirtschaftetesGold, durchgefuehrteKaempfe, gewonneneKaempfe, verloreneKaempfe FROM Statistik WHERE speicherstand_ID ="
							+ zuLadenderSpeicherstand_ID + ";");
			zuLadendeStatistik.setGesamtErwirtschaftetesGold(resultSet.getInt("gesamtErwirtschaftetesGold"));
			zuLadendeStatistik.setDurchgefuehrteKaempfe(resultset.getInt("durchgefuehrteKaempfe"));
			zuLadendeStatistik.setGewonneneKaempfe(resultset.getInt("gewonneneKaempfe"));
			zuLadendeStatistik.setVerloreneKaempfe(resultset.getInt("verloreneKaempfe"));

			Speicherstand zuLadenderSpeicherstand = new Speicherstand(zuLadendeParty, schwierigkeitsgrad, istHardcore,
					zuLadendeStatistik);
			return zuLadenderSpeicherstand;

		}
	}
}
