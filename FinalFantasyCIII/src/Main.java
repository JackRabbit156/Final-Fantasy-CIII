import hauptmenu.HauptmenuController;
import hilfsklassen.KonsolenAssistent;
import hilfsklassen.ScannerHelfer;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {

		HauptmenuController hauptmenu = new HauptmenuController();

		System.out.println(" _____                                                                 _____ \r\n"
				+ "( ___ )                                                               ( ___ )\r\n"
				+ " |   |~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|   | \r\n"
				+ " |   |           _______ _________ _        _______  _                 |   | \r\n"
				+ " |   |          (  ____ \\\\__   __/( (    /|(  ___  )( \\                |   | \r\n"
				+ " |   |          | (    \\/   ) (   |  \\  ( || (   ) || (                |   | \r\n"
				+ " |   |          | (__       | |   |   \\ | || (___) || |                |   | \r\n"
				+ " |   |          |  __)      | |   | (\\ \\) ||  ___  || |                |   | \r\n"
				+ " |   |          | (         | |   | | \\   || (   ) || |                |   | \r\n"
				+ " |   |          | )      ___) (___| )  \\  || )   ( || (____/\\          |   | \r\n"
				+ " |   |          |/       \\_______/|/    )_)|/     \\|(_______/          |   | \r\n"
				+ " |   |                                                                 |   | \r\n"
				+ " |   |  _______  _______  _       _________ _______  _______           |   | \r\n"
				+ " |   | (  ____ \\(  ___  )( (    /|\\__   __/(  ___  )(  ____ \\|\\     /| |   | \r\n"
				+ " |   | | (    \\/| (   ) ||  \\  ( |   ) (   | (   ) || (    \\/( \\   / ) |   | \r\n"
				+ " |   | | (__    | (___) ||   \\ | |   | |   | (___) || (_____  \\ (_) /  |   | \r\n"
				+ " |   | |  __)   |  ___  || (\\ \\) |   | |   |  ___  |(_____  )  \\   /   |   | \r\n"
				+ " |   | | (      | (   ) || | \\   |   | |   | (   ) |      ) |   ) (    |   | \r\n"
				+ " |   | | )      | )   ( || )  \\  |   | |   | )   ( |/\\____) |   | |    |   | \r\n"
				+ " |   | |/       |/     \\||/    )_)   )_(   |/     \\|\\_______)   \\_/    |   | \r\n"
				+ " |   |                                                                 |   | \r\n"
				+ " |   |               _______ ___________________________               |   | \r\n"
				+ " |   |              (  ____ \\\\__   __/\\__   __/\\__   __/               |   | \r\n"
				+ " |   |              | (    \\/   ) (      ) (      ) (                  |   | \r\n"
				+ " |   |              | |         | |      | |      | |                  |   | \r\n"
				+ " |   |              | |         | |      | |      | |                  |   | \r\n"
				+ " |   |              | |         | |      | |      | |                  |   | \r\n"
				+ " |   |              | (____/\\___) (______) (______) (___               |   | \r\n"
				+ " |   |              (_______/\\_______/\\_______/\\_______/               |   | \r\n"
				+ " |___|~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|___| \r\n"
				+ "(_____)                                                               (_____)");

		System.out.printf("%53s", "Enter drücken um fortzufahren!");
		ScannerHelfer.nextLine();
		KonsolenAssistent.clear();
		hauptmenu.hauptmenuAnzeigen();
	}

}
