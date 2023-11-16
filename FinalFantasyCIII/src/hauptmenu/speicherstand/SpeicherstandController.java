package hauptmenu.speicherstand;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SpeicherstandController {

	/**
	 * Überführt den Speicherstand in das Speicherverwaltungssystem
	 * 
	 * @author Nick
	 * @throws SQLException
	 * @since 15.11.2023
	 */
	// Speicherstand speicherstand
	public static void speichern() throws SQLException {
		try (Connection connection = DriverManager.getConnection("jdbc:sqlite:spielstaende.db")) {
			System.out.println("Aktueller Spielstand wird gespeichert. Bitte warten...");
		}
	}

	/**
	 * lässt den Nutzer den Speicherstand auswählen und laden
	 * 
	 * @author Nick
	 * @since 15.11.2023
	 */
	public Speicherstand speicherstandAuswahl() {
		// TODO MELVIN
		return null;
	}
}
