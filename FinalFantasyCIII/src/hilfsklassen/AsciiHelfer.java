package hilfsklassen;

import java.util.function.IntConsumer;

public class AsciiHelfer {
    // TODO eintragen der ascii arts im verlauf des projekts als sammelstelle für alle ascii arts
    /*
        Banner generieren (Border "Columns v2")
        https://www.asciiart.eu/text-to-ascii-art

        Angepasste Schriftzuege in ASCII
        https://patorjk.com/software/taag/#p=display&f=Doh&t=tobias

        Bildchen
        https://www.asciiart.eu/people/occupations/wizards


        Magische Wesen und andere auf dieser seite:
        https://llizardakaejm.wordpress.com/2015/11/03/mythical-creatures/



        Ne linkliste mit ASCII zeugs
        https://curlie.org/en/Arts/Visual_Arts/ASCII_Art/Animation


        gebäude und co
        https://www.asciiart.eu/buildings-and-places/houses
     */
    // ------------------------------------
    //TODO {"Haendler", "Schmiede", "Taverne", "Trainer", "Party Status", "Kaempfen Gehen", "DEVELOP: Spiel beenden"}
    public static void stadtHaendler() {
        String asciiArt =
                "                                                           |>>>\n" +
                        "                   _                      _               |\n" +
                        "    ____________ .' '.    "+Farbauswahl.YELLOW+"_____ /----/-\\"+Farbauswahl.RESET+".' './========\\   / \\\n" +
                        "   //// ////// /V_.-._\\  "+Farbauswahl.YELLOW+"|.-.-.|===| _ |"+Farbauswahl.RESET+"-----| u    u |  /___\\\n" +
                        "  // /// // ///==\\ u |.  "+Farbauswahl.YELLOW+"|| | ||===|||||"+Farbauswahl.RESET+" |T| |   ||   | .| u |_ _ _ _ _ _\n" +
                        " ///////-\\////====\\==|"+Farbauswahl.BROWN+":::::::::::::::::::::::::::::::::::"+Farbauswahl.RESET+"|u u| U U U U U\n" +
                        " |----/\\u |--|++++|..|"+Farbauswahl.BROWN+"'''''''''''::::::::::::::''''''''''"+Farbauswahl.RESET+"|+++|+-+-+-+-+-+\n" +
                        " |u u|u | |u ||||||..|              "+Farbauswahl.BROWN+"'::::::::'"+Farbauswahl.RESET+"           |===|>=== _ _ ==\n" +
                        " |===|  |u|==|++++|==|              "+Farbauswahl.BROWN+".::::::::."+Farbauswahl.RESET+"           | T |....| V |..\n" +
                        " |u u|u | |u ||HH||         \\|/    "+Farbauswahl.BROWN+".::::::::::."+Farbauswahl.RESET+"\n" +
                        " |===|_.|u|_.|+HH+|              "+Farbauswahl.BROWN+".::::::::::::."+Farbauswahl.RESET+"              \n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"                __"+Farbauswahl.BLUE+"("+Farbauswahl.BRICKSTONE_RED+"_"+Farbauswahl.BLUE+")"+Farbauswahl.BRICKSTONE_RED+"___         "+Farbauswahl.BROWN+".::::::::::::::."+Farbauswahl.RESET+"         ___"+Farbauswahl.BLUE+"("+Farbauswahl.BRICKSTONE_RED+"_"+Farbauswahl.BLUE+")"+Farbauswahl.BRICKSTONE_RED+"_____\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"---------------/  / \\  /|       "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"       |\\  / \\  \\-------\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"______________/_______/ |      "+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"      | \\_______\\________\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"|       |     [===  =] /|     "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"     |\\ [==  = ]   |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"|_______|_____[ = == ]/ |    "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::."+Farbauswahl.RESET+"    | \\[ ===  ]___|____\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"     |       |[  === ] /|   "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::."+Farbauswahl.RESET+"   |\\ [=  ===] |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"_____|_______|[== = =]/ |  "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::."+Farbauswahl.RESET+"  | \\[ ==  =]_|______\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+" |       |    [ == = ] /| "+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::."+Farbauswahl.RESET+" |\\ [== == ]      |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"_|_______|____[=  == ]/ |"+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::."+Farbauswahl.RESET+"| \\[  === ]______|_\n";

        IntConsumer printChar = i -> System.out.print((char) i);
        asciiArt.chars().forEach(printChar);
    }

    public static void stadtSchmiede() {
        String asciiArt =
                "                                                           |>>>\n" +
                        "                   _                      "+Farbauswahl.GREEN+"_"+Farbauswahl.RESET+"               |\n" +
                        "    ____________ .' '.    _____ /----/-\\"+Farbauswahl.GREEN+".' './========\\"+Farbauswahl.RESET+"   / \\\n" +
                        "   //// ////// /V_.-._\\  |.-.-.|===| _ |"+Farbauswahl.GREEN+"-----| u    u |"+Farbauswahl.RESET+"  /___\\\n" +
                        "  // /// // ///==\\ u |.  || | ||===|||||"+Farbauswahl.GREEN+" |T| |   ||   |"+Farbauswahl.RESET+" .| u |_ _ _ _ _ _\n" +
                        " ///////-\\////====\\==|:::::::::::::::::::::::::::::::::::|u u| U U U U U\n" +
                        " |----/\\u |--|++++|..|'''''''''''::::::::::::::''''''''''|+++|+-+-+-+-+-+\n" +
                        " |u u|u | |u ||||||..|              '::::::::'           |===|>=== _ _ ==\n" +
                        " |===|  |u|==|++++|==|              .::::::::.           | T |....| V |..\n" +
                        " |u u|u | |u ||HH||         \\|/    .::::::::::.\n" +
                        " |===|_.|u|_.|+HH+|_              .::::::::::::.              _\n" +
                        "                __(_)___         .::::::::::::::.         ___(_)__\n" +
                        "---------------/  / \\  /|       .:::::;;;:::;;:::.       |\\  / \\  \\-------\n" +
                        "______________/_______/ |      .::::::;;:::::;;:::.      | \\_______\\________\n" +
                        "|       |     [===  =] /|     .:::::;;;::::::;;;:::.     |\\ [==  = ]   |\n" +
                        "|_______|_____[ = == ]/ |    .:::::;;;:::::::;;;::::.    | \\[ ===  ]___|____\n" +
                        "     |       |[  === ] /|   .:::::;;;::::::::;;;:::::.   |\\ [=  ===] |\n" +
                        "_____|_______|[== = =]/ |  .:::::;;;::::::::::;;;:::::.  | \\[ ==  =]_|______\n" +
                        " |       |    [ == = ] /| .::::::;;:::::::::::;;;::::::. |\\ [== == ]      |\n" +
                        "_|_______|____[=  == ]/ |.::::::;;:::::::::::::;;;::::::.| \\[  === ]______|_\n";

        IntConsumer printChar = i -> System.out.print((char) i);
        asciiArt.chars().forEach(printChar);
    }

    public static void stadtTaverne() {
        String asciiArt =
                "                                                           |>>>\n" +
                        "                   _                     "+Farbauswahl.GREEN+"_"+Farbauswahl.RESET+"               |\n" +
                        "    ____________ .' '.    _____ /----/-\\"+Farbauswahl.GREEN+".' './========\\"+Farbauswahl.RESET+"   / \\\n" +
                        "   //// ////// /V_.-._\\  |.-.-.|===| _ |"+Farbauswahl.GREEN+"-----| u    u |"+Farbauswahl.RESET+"  /___\\\n" +
                        "  // /// // ///==\\ u |.  || | ||===|||||"+Farbauswahl.GREEN+" |T| |   ||   |"+Farbauswahl.RESET+" .| u |_ _ _ _ _ _\n" +
                        " ///////-\\////====\\==|:::::::::::::::::::::::::::::::::::|u u| U U U U U\n" +
                        " |----/\\u |--|++++|..|'''''''''''::::::::::::::''''''''''|+++|+-+-+-+-+-+\n" +
                        " |u u|u | |u ||||||..|              '::::::::'           |===|>=== _ _ ==\n" +
                        " |===|  |u|==|++++|==|              .::::::::.           | T |....| V |..\n" +
                        " |u u|u | |u ||HH||         \\|/    .::::::::::.\n" +
                        " |===|_.|u|_.|+HH+|_              .::::::::::::.              _\n" +
                        "                __(_)___         .::::::::::::::.         ___(_)__\n" +
                        "---------------/  / \\  /|       .:::::;;;:::;;:::.       |\\  / \\  \\-------\n" +
                        "______________/_______/ |      .::::::;;:::::;;:::.      | \\_______\\________\n" +
                        "|       |     [===  =] /|     .:::::;;;::::::;;;:::.     |\\ [==  = ]   |\n" +
                        "|_______|_____[ = == ]/ |    .:::::;;;:::::::;;;::::.    | \\[ ===  ]___|____\n" +
                        "     |       |[  === ] /|   .:::::;;;::::::::;;;:::::.   |\\ [=  ===] |\n" +
                        "_____|_______|[== = =]/ |  .:::::;;;::::::::::;;;:::::.  | \\[ ==  =]_|______\n" +
                        " |       |    [ == = ] /| .::::::;;:::::::::::;;;::::::. |\\ [== == ]      |\n" +
                        "_|_______|____[=  == ]/ |.::::::;;:::::::::::::;;;::::::.| \\[  === ]______|_\n";

        IntConsumer printChar = i -> System.out.print((char) i);
        asciiArt.chars().forEach(printChar);
    }

    public static void stadtTrainer() {
        String asciiArt =
                "                                                           |>>>\n" +
                        "                   _                     "+Farbauswahl.GREEN+"_"+Farbauswahl.RESET+"               |\n" +
                        "    ____________ .' '.    _____ /----/-\\"+Farbauswahl.GREEN+".' './========\\"+Farbauswahl.RESET+"   / \\\n" +
                        "   //// ////// /V_.-._\\  |.-.-.|===| _ |"+Farbauswahl.GREEN+"-----| u    u |"+Farbauswahl.RESET+"  /___\\\n" +
                        "  // /// // ///==\\ u |.  || | ||===|||||"+Farbauswahl.GREEN+" |T| |   ||   |"+Farbauswahl.RESET+" .| u |_ _ _ _ _ _\n" +
                        " ///////-\\////====\\==|:::::::::::::::::::::::::::::::::::|u u| U U U U U\n" +
                        " |----/\\u |--|++++|..|'''''''''''::::::::::::::''''''''''|+++|+-+-+-+-+-+\n" +
                        " |u u|u | |u ||||||..|              '::::::::'           |===|>=== _ _ ==\n" +
                        " |===|  |u|==|++++|==|              .::::::::.           | T |....| V |..\n" +
                        " |u u|u | |u ||HH||         \\|/    .::::::::::.\n" +
                        " |===|_.|u|_.|+HH+|_              .::::::::::::.              _\n" +
                        "                __(_)___         .::::::::::::::.         ___(_)__\n" +
                        "---------------/  / \\  /|       .:::::;;;:::;;:::.       |\\  / \\  \\-------\n" +
                        "______________/_______/ |      .::::::;;:::::;;:::.      | \\_______\\________\n" +
                        "|       |     [===  =] /|     .:::::;;;::::::;;;:::.     |\\ [==  = ]   |\n" +
                        "|_______|_____[ = == ]/ |    .:::::;;;:::::::;;;::::.    | \\[ ===  ]___|____\n" +
                        "     |       |[  === ] /|   .:::::;;;::::::::;;;:::::.   |\\ [=  ===] |\n" +
                        "_____|_______|[== = =]/ |  .:::::;;;::::::::::;;;:::::.  | \\[ ==  =]_|______\n" +
                        " |       |    [ == = ] /| .::::::;;:::::::::::;;;::::::. |\\ [== == ]      |\n" +
                        "_|_______|____[=  == ]/ |.::::::;;:::::::::::::;;;::::::.| \\[  === ]______|_\n";

        IntConsumer printChar = i -> System.out.print((char) i);
        asciiArt.chars().forEach(printChar);
    }

    public static void stadtPartyStatus() {
        String asciiArt =
                "                                                           |>>>\n" +
                        "                   _                     "+Farbauswahl.GREEN+"_"+Farbauswahl.RESET+"               |\n" +
                        "    ____________ .' '.    _____ /----/-\\"+Farbauswahl.GREEN+".' './========\\"+Farbauswahl.RESET+"   / \\\n" +
                        "   //// ////// /V_.-._\\  |.-.-.|===| _ |"+Farbauswahl.GREEN+"-----| u    u |"+Farbauswahl.RESET+"  /___\\\n" +
                        "  // /// // ///==\\ u |.  || | ||===|||||"+Farbauswahl.GREEN+" |T| |   ||   |"+Farbauswahl.RESET+" .| u |_ _ _ _ _ _\n" +
                        " ///////-\\////====\\==|:::::::::::::::::::::::::::::::::::|u u| U U U U U\n" +
                        " |----/\\u |--|++++|..|'''''''''''::::::::::::::''''''''''|+++|+-+-+-+-+-+\n" +
                        " |u u|u | |u ||||||..|              '::::::::'           |===|>=== _ _ ==\n" +
                        " |===|  |u|==|++++|==|              .::::::::.           | T |....| V |..\n" +
                        " |u u|u | |u ||HH||         \\|/    .::::::::::.\n" +
                        " |===|_.|u|_.|+HH+|_              .::::::::::::.              _\n" +
                        "                __(_)___         .::::::::::::::.         ___(_)__\n" +
                        "---------------/  / \\  /|       .:::::;;;:::;;:::.       |\\  / \\  \\-------\n" +
                        "______________/_______/ |      .::::::;;:::::;;:::.      | \\_______\\________\n" +
                        "|       |     [===  =] /|     .:::::;;;::::::;;;:::.     |\\ [==  = ]   |\n" +
                        "|_______|_____[ = == ]/ |    .:::::;;;:::::::;;;::::.    | \\[ ===  ]___|____\n" +
                        "     |       |[  === ] /|   .:::::;;;::::::::;;;:::::.   |\\ [=  ===] |\n" +
                        "_____|_______|[== = =]/ |  .:::::;;;::::::::::;;;:::::.  | \\[ ==  =]_|______\n" +
                        " |       |    [ == = ] /| .::::::;;:::::::::::;;;::::::. |\\ [== == ]      |\n" +
                        "_|_______|____[=  == ]/ |.::::::;;:::::::::::::;;;::::::.| \\[  === ]______|_\n";

        IntConsumer printChar = i -> System.out.print((char) i);
        asciiArt.chars().forEach(printChar);
    }

    public static void stadtKampf() {
        String asciiArt =
                "                                                           |>>>\n" +
                        "                   _                     "+Farbauswahl.GREEN+"_"+Farbauswahl.RESET+"               |\n" +
                        "    ____________ .' '.    _____ /----/-\\"+Farbauswahl.GREEN+".' './========\\"+Farbauswahl.RESET+"   / \\\n" +
                        "   //// ////// /V_.-._\\  |.-.-.|===| _ |"+Farbauswahl.GREEN+"-----| u    u |"+Farbauswahl.RESET+"  /___\\\n" +
                        "  // /// // ///==\\ u |.  || | ||===|||||"+Farbauswahl.GREEN+" |T| |   ||   |"+Farbauswahl.RESET+" .| u |_ _ _ _ _ _\n" +
                        " ///////-\\////====\\==|:::::::::::::::::::::::::::::::::::|u u| U U U U U\n" +
                        " |----/\\u |--|++++|..|'''''''''''::::::::::::::''''''''''|+++|+-+-+-+-+-+\n" +
                        " |u u|u | |u ||||||..|              '::::::::'           |===|>=== _ _ ==\n" +
                        " |===|  |u|==|++++|==|              .::::::::.           | T |....| V |..\n" +
                        " |u u|u | |u ||HH||         \\|/    .::::::::::.\n" +
                        " |===|_.|u|_.|+HH+|_              .::::::::::::.              _\n" +
                        "                __(_)___         .::::::::::::::.         ___(_)__\n" +
                        "---------------/  / \\  /|       .:::::;;;:::;;:::.       |\\  / \\  \\-------\n" +
                        "______________/_______/ |      .::::::;;:::::;;:::.      | \\_______\\________\n" +
                        "|       |     [===  =] /|     .:::::;;;::::::;;;:::.     |\\ [==  = ]   |\n" +
                        "|_______|_____[ = == ]/ |    .:::::;;;:::::::;;;::::.    | \\[ ===  ]___|____\n" +
                        "     |       |[  === ] /|   .:::::;;;::::::::;;;:::::.   |\\ [=  ===] |\n" +
                        "_____|_______|[== = =]/ |  .:::::;;;::::::::::;;;:::::.  | \\[ ==  =]_|______\n" +
                        " |       |    [ == = ] /| .::::::;;:::::::::::;;;::::::. |\\ [== == ]      |\n" +
                        "_|_______|____[=  == ]/ |.::::::;;:::::::::::::;;;::::::.| \\[  === ]______|_\n";

        IntConsumer printChar = i -> System.out.print((char) i);
        asciiArt.chars().forEach(printChar);
    }

}
