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

    /**
     * Folgende Arts zeichnen das Stadt Menü so wie die angegeben Selectierung
     * @author HF Rode
     * @since 18.11.2023
     */
    public static void stadtHaendler() {
        String asciiArt =
                "                                                           |>>>\n" +
                        "                   _                      _                |\n" +
                        "    ____________ .' '.    "+Farbauswahl.YELLOW+"_____ /----/-\\"+Farbauswahl.RESET+".' './========\\   / \\\n" +
                        "   //// ////// /V_.-._\\  "+Farbauswahl.YELLOW+"|.-.-.|===| _ |"+Farbauswahl.RESET+"-----| u    u |  /___\\\n" +
                        "  // /// // ///==\\ u |.  "+Farbauswahl.YELLOW+"|| | ||===|||||"+Farbauswahl.RESET+" |T| |   ||   | .| u |_ _ _ _ _ _\n" +
                        " ///////-\\////====\\==|"+Farbauswahl.BROWN+":::::::::::::::::::::::::::::::::::"+Farbauswahl.RESET+"|u u| U U U U U\n" +
                        " |----/\\u |--|++++|..|"+Farbauswahl.BROWN+"'''''''''''::::::::::::::''''''''''"+Farbauswahl.RESET+"|+++|+-+-+-+-+-+\n" +
                        " |u u|u | |u ||||||..|              "+Farbauswahl.BROWN+"'::::::::'"+Farbauswahl.RESET+"           |===|>=== _ _ ==\n" +
                        " |===|  |u|==|++++|==|              "+Farbauswahl.BROWN+".::::::::."+Farbauswahl.RESET+"           | T |....| V |..\n" +
                        " |u u|u | |u ||HH||         "+Farbauswahl.GREEN+"\\|/    "+Farbauswahl.BROWN+".::::::::::."+Farbauswahl.RESET+"\n" +
                        " |===|_.|u|_.|+HH+|              "+Farbauswahl.BROWN+".::::::::::::."+Farbauswahl.RESET+"              \n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"                __"+Farbauswahl.BLUE+"("+Farbauswahl.BRICKSTONE_RED+"_"+Farbauswahl.BLUE+")"+Farbauswahl.BRICKSTONE_RED+"___         "+Farbauswahl.BROWN+".::::::::::::::."+Farbauswahl.RESET+"         "+Farbauswahl.BRICKSTONE_RED+"___"+Farbauswahl.BLUE+"("+Farbauswahl.BRICKSTONE_RED+"_"+Farbauswahl.BLUE+")"+Farbauswahl.BRICKSTONE_RED+"__\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"---------------/  / \\  /|       "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"       "+Farbauswahl.BRICKSTONE_RED+"|\\  / \\  \\-------\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"______________/_______/ |      "+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"      "+Farbauswahl.BRICKSTONE_RED+"| \\_______\\________\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"|       |     [===  =] /|     "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"     "+Farbauswahl.BRICKSTONE_RED+"|\\ [==  = ]   |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"|_______|_____[ = == ]/ |    "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::."+Farbauswahl.RESET+"    "+Farbauswahl.BRICKSTONE_RED+"| \\[ ===  ]___|____\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"     |       |[  === ] /|   "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::."+Farbauswahl.RESET+"   "+Farbauswahl.BRICKSTONE_RED+"|\\ [=  ===] |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"_____|_______|[== = =]/ |  "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::."+Farbauswahl.RESET+"  "+Farbauswahl.BRICKSTONE_RED+"| \\[ ==  =]_|______\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+" |       |    [ == = ] /| "+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::."+Farbauswahl.RESET+" "+Farbauswahl.BRICKSTONE_RED+"|\\ [== == ]      |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"_|_______|____[=  == ]/ |"+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::."+Farbauswahl.RESET+""+Farbauswahl.BRICKSTONE_RED+"| \\[  === ]______|_\n"+Farbauswahl.RESET+"";

        IntConsumer printChar = i -> System.out.print((char) i);
        asciiArt.chars().forEach(printChar);
    }

    public static void stadtSchmiede() {
        String asciiArt =
                "                                                           |>>>\n" +
                        "                   _                      "+Farbauswahl.YELLOW+"_"+Farbauswahl.RESET+"                |\n" +
                        "    ____________ .' '.    _____ /----/-\\"+Farbauswahl.YELLOW+".' './========\\"+Farbauswahl.RESET+"   / \\\n" +
                        "   //// ////// /V_.-._\\  |.-.-.|===| _ |"+Farbauswahl.YELLOW+"-----| u    u |"+Farbauswahl.RESET+"  /___\\\n" +
                        "  // /// // ///==\\ u |.  || | ||===|||||"+Farbauswahl.YELLOW+" |T| |   ||   |"+Farbauswahl.RESET+" .| u |_ _ _ _ _ _\n" +
                        " ///////-\\////====\\==|"+Farbauswahl.BROWN+":::::::::::::::::::::::::::::::::::"+Farbauswahl.RESET+"|u u| U U U U U\n" +
                        " |----/\\u |--|++++|..|"+Farbauswahl.BROWN+"'''''''''''::::::::::::::''''''''''"+Farbauswahl.RESET+"|+++|+-+-+-+-+-+\n" +
                        " |u u|u | |u ||||||..|              "+Farbauswahl.BROWN+"'::::::::'"+Farbauswahl.RESET+"           |===|>=== _ _ ==\n" +
                        " |===|  |u|==|++++|==|              "+Farbauswahl.BROWN+".::::::::."+Farbauswahl.RESET+"           | T |....| V |..\n" +
                        " |u u|u | |u ||HH||         "+Farbauswahl.GREEN+"\\|/    "+Farbauswahl.BROWN+".::::::::::."+Farbauswahl.RESET+"\n" +
                        " |===|_.|u|_.|+HH+|              "+Farbauswahl.BROWN+".::::::::::::."+Farbauswahl.RESET+"              \n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"                __"+Farbauswahl.BLUE+"("+Farbauswahl.BRICKSTONE_RED+"_"+Farbauswahl.BLUE+")"+Farbauswahl.BRICKSTONE_RED+"___         "+Farbauswahl.BROWN+".::::::::::::::."+Farbauswahl.RESET+"         "+Farbauswahl.BRICKSTONE_RED+"___"+Farbauswahl.BLUE+"("+Farbauswahl.BRICKSTONE_RED+"_"+Farbauswahl.BLUE+")"+Farbauswahl.BRICKSTONE_RED+"__\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"---------------/  / \\  /|       "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"       "+Farbauswahl.BRICKSTONE_RED+"|\\  / \\  \\-------\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"______________/_______/ |      "+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"      "+Farbauswahl.BRICKSTONE_RED+"| \\_______\\________\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"|       |     [===  =] /|     "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"     "+Farbauswahl.BRICKSTONE_RED+"|\\ [==  = ]   |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"|_______|_____[ = == ]/ |    "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::."+Farbauswahl.RESET+"    "+Farbauswahl.BRICKSTONE_RED+"| \\[ ===  ]___|____\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"     |       |[  === ] /|   "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::."+Farbauswahl.RESET+"   "+Farbauswahl.BRICKSTONE_RED+"|\\ [=  ===] |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"_____|_______|[== = =]/ |  "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::."+Farbauswahl.RESET+"  "+Farbauswahl.BRICKSTONE_RED+"| \\[ ==  =]_|______\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+" |       |    [ == = ] /| "+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::."+Farbauswahl.RESET+" "+Farbauswahl.BRICKSTONE_RED+"|\\ [== == ]      |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"_|_______|____[=  == ]/ |"+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::."+Farbauswahl.RESET+""+Farbauswahl.BRICKSTONE_RED+"| \\[  === ]______|_\n"+Farbauswahl.RESET+"";

        IntConsumer printChar = i -> System.out.print((char) i);
        asciiArt.chars().forEach(printChar);
    }

    public static void stadtTaverne() {
        String asciiArt =
                "                                                           |>>>\n" +
                        ""+Farbauswahl.YELLOW+"                   _"+Farbauswahl.RESET+"                      _                |\n" +
                        ""+Farbauswahl.YELLOW+"    ____________ .' '."+Farbauswahl.RESET+"    _____ /----/-\\.' './========\\   / \\\n" +
                        ""+Farbauswahl.YELLOW+"   //// ////// /V_.-._\\"+Farbauswahl.RESET+"  |.-.-.|===| _ |-----| u    u |  /___\\\n" +
                        ""+Farbauswahl.YELLOW+"  // /// // ///==\\ u |"+Farbauswahl.RESET+".  || | ||===||||| |T| |   ||   | .| u |_ _ _ _ _ _\n" +
                        ""+Farbauswahl.YELLOW+" ///////-\\////====\\==|"+Farbauswahl.RESET+":::::::::::::::::::::::::::::::::::|u u| U U U U U\n" +
                        ""+Farbauswahl.YELLOW+" |----/\\u |--|++++|..|"+Farbauswahl.RESET+"'''''''''''::::::::::::::''''''''''|+++|+-+-+-+-+-+\n" +
                        ""+Farbauswahl.YELLOW+" |u u|u | |u ||||||..|              "+Farbauswahl.BROWN+"'::::::::'"+Farbauswahl.RESET+"           |===|>=== _ _ ==\n" +
                        ""+Farbauswahl.YELLOW+" |===|  |u|==|++++|==|              "+Farbauswahl.BROWN+".::::::::."+Farbauswahl.RESET+"           | T |....| V |..\n" +
                        ""+Farbauswahl.YELLOW+" |u u|u | |u ||HH||         "+Farbauswahl.GREEN+"\\|/    "+Farbauswahl.BROWN+".::::::::::."+Farbauswahl.RESET+"\n" +
                        ""+Farbauswahl.YELLOW+" |===|_.|u|_.|+HH+|              "+Farbauswahl.BROWN+".::::::::::::."+Farbauswahl.RESET+"              \n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"                __"+Farbauswahl.BLUE+"("+Farbauswahl.BRICKSTONE_RED+"_"+Farbauswahl.BLUE+")"+Farbauswahl.BRICKSTONE_RED+"___         "+Farbauswahl.BROWN+".::::::::::::::."+Farbauswahl.RESET+"         "+Farbauswahl.BRICKSTONE_RED+"___"+Farbauswahl.BLUE+"("+Farbauswahl.BRICKSTONE_RED+"_"+Farbauswahl.BLUE+")"+Farbauswahl.BRICKSTONE_RED+"__\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"---------------/  / \\  /|       "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"       "+Farbauswahl.BRICKSTONE_RED+"|\\  / \\  \\-------\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"______________/_______/ |      "+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"      "+Farbauswahl.BRICKSTONE_RED+"| \\_______\\________\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"|       |     [===  =] /|     "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"     "+Farbauswahl.BRICKSTONE_RED+"|\\ [==  = ]   |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"|_______|_____[ = == ]/ |    "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::."+Farbauswahl.RESET+"    "+Farbauswahl.BRICKSTONE_RED+"| \\[ ===  ]___|____\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"     |       |[  === ] /|   "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::."+Farbauswahl.RESET+"   "+Farbauswahl.BRICKSTONE_RED+"|\\ [=  ===] |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"_____|_______|[== = =]/ |  "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::."+Farbauswahl.RESET+"  "+Farbauswahl.BRICKSTONE_RED+"| \\[ ==  =]_|______\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+" |       |    [ == = ] /| "+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::."+Farbauswahl.RESET+" "+Farbauswahl.BRICKSTONE_RED+"|\\ [== == ]      |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"_|_______|____[=  == ]/ |"+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::."+Farbauswahl.RESET+""+Farbauswahl.BRICKSTONE_RED+"| \\[  === ]______|_\n"+Farbauswahl.RESET+"";

        IntConsumer printChar = i -> System.out.print((char) i);
        asciiArt.chars().forEach(printChar);
    }

    public static void stadtTrainer() {
        String asciiArt =
                "                                                           "+Farbauswahl.YELLOW+"|>>>"+Farbauswahl.RESET+"\n" +
                        "                   _                      _                "+Farbauswahl.YELLOW+"|"+Farbauswahl.RESET+"\n" +
                        "    ____________ .' '.    _____ /----/-\\.' './========\\   "+Farbauswahl.YELLOW+"/ \\"+Farbauswahl.RESET+"\n" +
                        "   //// ////// /V_.-._\\  |.-.-.|===| _ |-----| u    u |  "+Farbauswahl.YELLOW+"/___\\"+Farbauswahl.RESET+"\n" +
                        "  // /// // ///==\\ u |.  || | ||===||||| |T| |   ||   | ."+Farbauswahl.YELLOW+"| u |_ _ _ _ _ _"+Farbauswahl.RESET+"\n" +
                        " ///////-\\////====\\==|"+Farbauswahl.BROWN+":::::::::::::::::::::::::::::::::::"+Farbauswahl.RESET+""+Farbauswahl.YELLOW+"|u u| U U U U U"+Farbauswahl.RESET+"\n" +
                        " |----/\\u |--|++++|..|"+Farbauswahl.BROWN+"'''''''''''::::::::::::::''''''''''"+Farbauswahl.RESET+""+Farbauswahl.YELLOW+"|+++|+-+-+-+-+-+"+Farbauswahl.RESET+"\n" +
                        " |u u|u | |u ||||||..|              "+Farbauswahl.BROWN+"'::::::::'"+Farbauswahl.RESET+"           "+Farbauswahl.YELLOW+"|===|>=== _ _ =="+Farbauswahl.RESET+"\n" +
                        " |===|  |u|==|++++|==|              "+Farbauswahl.BROWN+".::::::::."+Farbauswahl.RESET+"           "+Farbauswahl.YELLOW+"| T |....| V |.."+Farbauswahl.RESET+"\n" +
                        " |u u|u | |u ||HH||         "+Farbauswahl.GREEN+"\\|/    "+Farbauswahl.BROWN+".::::::::::."+Farbauswahl.RESET+"\n" +
                        " |===|_.|u|_.|+HH+|              "+Farbauswahl.BROWN+".::::::::::::."+Farbauswahl.RESET+"              \n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"                __"+Farbauswahl.BLUE+"("+Farbauswahl.BRICKSTONE_RED+"_"+Farbauswahl.BLUE+")"+Farbauswahl.BRICKSTONE_RED+"___         "+Farbauswahl.BROWN+".::::::::::::::."+Farbauswahl.RESET+"         "+Farbauswahl.BRICKSTONE_RED+"___"+Farbauswahl.BLUE+"("+Farbauswahl.BRICKSTONE_RED+"_"+Farbauswahl.BLUE+")"+Farbauswahl.BRICKSTONE_RED+"__\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"---------------/  / \\  /|       "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"       "+Farbauswahl.BRICKSTONE_RED+"|\\  / \\  \\-------\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"______________/_______/ |      "+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"      "+Farbauswahl.BRICKSTONE_RED+"| \\_______\\________\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"|       |     [===  =] /|     "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"     "+Farbauswahl.BRICKSTONE_RED+"|\\ [==  = ]   |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"|_______|_____[ = == ]/ |    "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::."+Farbauswahl.RESET+"    "+Farbauswahl.BRICKSTONE_RED+"| \\[ ===  ]___|____\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"     |       |[  === ] /|   "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::."+Farbauswahl.RESET+"   "+Farbauswahl.BRICKSTONE_RED+"|\\ [=  ===] |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"_____|_______|[== = =]/ |  "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::."+Farbauswahl.RESET+"  "+Farbauswahl.BRICKSTONE_RED+"| \\[ ==  =]_|______\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+" |       |    [ == = ] /| "+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::."+Farbauswahl.RESET+" "+Farbauswahl.BRICKSTONE_RED+"|\\ [== == ]      |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"_|_______|____[=  == ]/ |"+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::."+Farbauswahl.RESET+""+Farbauswahl.BRICKSTONE_RED+"| \\[  === ]______|_\n"+Farbauswahl.RESET+"";

        IntConsumer printChar = i -> System.out.print((char) i);
        asciiArt.chars().forEach(printChar);
    }

    public static void stadtPartyStatus() {
        String asciiArt =
                "                                                           |>>>\n" +
                        "                   _                      _                |\n" +
                        "    ____________ .' '.    _____ /----/-\\.' './========\\   / \\\n" +
                        "   //// ////// /V_.-._\\  |.-.-.|===| _ |-----| u    u |  /___\\\n" +
                        "  // /// // ///==\\ u |.  || | ||===||||| |T| |   ||   | .| u |_ _ _ _ _ _\n" +
                        " ///////-\\////====\\==|"+Farbauswahl.BROWN+":::::::::::::::::::::::::::::::::::"+Farbauswahl.RESET+"|u u| U U U U U\n" +
                        " |----/\\u |--|++++|..|"+Farbauswahl.BROWN+"'''''''''''::::::::::::::''''''''''"+Farbauswahl.RESET+"|+++|+-+-+-+-+-+\n" +
                        " |u u|u | |u ||||||..|              "+Farbauswahl.BROWN+"'::::::::'"+Farbauswahl.RESET+"           |===|>=== _ _ ==\n" +
                        " |===|  |u|==|++++|==|              "+Farbauswahl.BROWN+".::::::::."+Farbauswahl.RESET+"           | T |....| V |..\n" +
                        " |u u|u | |u ||HH||         "+Farbauswahl.GREEN+"\\|/    "+Farbauswahl.BROWN+".::::::::::."+Farbauswahl.RESET+"\n" +
                        " |===|_.|u|_.|+HH+|              "+Farbauswahl.BROWN+".::::::::::::."+Farbauswahl.RESET+"              \n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"                __"+Farbauswahl.BLUE+"("+Farbauswahl.BRICKSTONE_RED+"_"+Farbauswahl.BLUE+")"+Farbauswahl.BRICKSTONE_RED+"___         "+Farbauswahl.BROWN+".::::::::::::::."+Farbauswahl.RESET+"         "+Farbauswahl.BRICKSTONE_RED+"___"+Farbauswahl.BLUE+"("+Farbauswahl.BRICKSTONE_RED+"_"+Farbauswahl.BLUE+")"+Farbauswahl.BRICKSTONE_RED+"__\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"---------------/  / \\  /|       "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"       "+Farbauswahl.BRICKSTONE_RED+"|\\  / \\  \\-------\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"______________/_______/ |      "+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"      "+Farbauswahl.BRICKSTONE_RED+"| \\_______\\________\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"|       |     [===  =] /|     "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::."+Farbauswahl.RESET+"     "+Farbauswahl.BRICKSTONE_RED+"|\\ [==  = ]   |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"|_______|_____[ = == ]/ |    "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::."+Farbauswahl.RESET+"    "+Farbauswahl.BRICKSTONE_RED+"| \\[ ===  ]___|____\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"     |       |[  === ] /|   "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::."+Farbauswahl.RESET+"   "+Farbauswahl.BRICKSTONE_RED+"|\\ [=  ===] |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"_____|_______|[== = =]/ |  "+Farbauswahl.BROWN+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+":::::."+Farbauswahl.RESET+"  "+Farbauswahl.BRICKSTONE_RED+"| \\[ ==  =]_|______\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+" |       |    [ == = ] /| "+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::."+Farbauswahl.RESET+" "+Farbauswahl.BRICKSTONE_RED+"|\\ [== == ]      |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"_|_______|____[=  == ]/ |"+Farbauswahl.BROWN+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.BROWN+":::::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.BROWN+"::::::."+Farbauswahl.RESET+""+Farbauswahl.BRICKSTONE_RED+"| \\[  === ]______|_\n"+Farbauswahl.RESET+"";

        IntConsumer printChar = i -> System.out.print((char) i);
        asciiArt.chars().forEach(printChar);
    }

    public static void stadtKampf() {
        String asciiArt =
                "                                                           |>>>\n" +
                        "                   _                      _                |\n" +
                        "    ____________ .' '.    _____ /----/-\\.' './========\\   / \\\n" +
                        "   //// ////// /V_.-._\\  |.-.-.|===| _ |-----| u    u |  /___\\\n" +
                        "  // /// // ///==\\ u |.  || | ||===||||| |T| |   ||   | .| u |_ _ _ _ _ _\n" +
                        " ///////-\\////====\\==|"+Farbauswahl.RED+":::::::::::::::::::::::::::::::::::"+Farbauswahl.RESET+"|u u| U U U U U\n" +
                        " |----/\\u |--|++++|..|"+Farbauswahl.RED+"'''''''''''::::::::::::::''''''''''"+Farbauswahl.RESET+"|+++|+-+-+-+-+-+\n" +
                        " |u u|u | |u ||||||..|              "+Farbauswahl.RED+"'::::::::'"+Farbauswahl.RESET+"           |===|>=== _ _ ==\n" +
                        " |===|  |u|==|++++|==|              "+Farbauswahl.RED+".::::::::."+Farbauswahl.RESET+"           | T |....| V |..\n" +
                        " |u u|u | |u ||HH||         "+Farbauswahl.GREEN+"\\|/    "+Farbauswahl.RED+".::::::::::."+Farbauswahl.RESET+"\n" +
                        " |===|_.|u|_.|+HH+|              "+Farbauswahl.RED+".::::::::::::."+Farbauswahl.RESET+"              \n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"                __"+Farbauswahl.BLUE+"("+Farbauswahl.BRICKSTONE_RED+"_"+Farbauswahl.BLUE+")"+Farbauswahl.BRICKSTONE_RED+"___         "+Farbauswahl.RED+".::::::::::::::."+Farbauswahl.RESET+"         "+Farbauswahl.BRICKSTONE_RED+"___"+Farbauswahl.BLUE+"("+Farbauswahl.BRICKSTONE_RED+"_"+Farbauswahl.BLUE+")"+Farbauswahl.BRICKSTONE_RED+"__\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"---------------/  / \\  /|       "+Farbauswahl.RED+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.RED+":::"+Farbauswahl.GREY+";;"+Farbauswahl.RED+":::."+Farbauswahl.RESET+"       "+Farbauswahl.BRICKSTONE_RED+"|\\  / \\  \\-------\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"______________/_______/ |      "+Farbauswahl.RED+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.RED+":::::"+Farbauswahl.GREY+";;"+Farbauswahl.RED+":::."+Farbauswahl.RESET+"      "+Farbauswahl.BRICKSTONE_RED+"| \\_______\\________\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"|       |     [===  =] /|     "+Farbauswahl.RED+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.RED+"::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.RED+":::."+Farbauswahl.RESET+"     "+Farbauswahl.BRICKSTONE_RED+"|\\ [==  = ]   |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"|_______|_____[ = == ]/ |    "+Farbauswahl.RED+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.RED+":::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.RED+"::::."+Farbauswahl.RESET+"    "+Farbauswahl.BRICKSTONE_RED+"| \\[ ===  ]___|____\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"     |       |[  === ] /|   "+Farbauswahl.RED+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.RED+"::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.RED+":::::."+Farbauswahl.RESET+"   "+Farbauswahl.BRICKSTONE_RED+"|\\ [=  ===] |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"_____|_______|[== = =]/ |  "+Farbauswahl.RED+".:::::"+Farbauswahl.GREY+";;;"+Farbauswahl.RED+"::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.RED+":::::."+Farbauswahl.RESET+"  "+Farbauswahl.BRICKSTONE_RED+"| \\[ ==  =]_|______\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+" |       |    [ == = ] /| "+Farbauswahl.RED+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.RED+":::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.RED+"::::::."+Farbauswahl.RESET+" "+Farbauswahl.BRICKSTONE_RED+"|\\ [== == ]      |\n" +
                        ""+Farbauswahl.BRICKSTONE_RED+"_|_______|____[=  == ]/ |"+Farbauswahl.RED+".::::::"+Farbauswahl.GREY+";;"+Farbauswahl.RED+":::::::::::::"+Farbauswahl.GREY+";;;"+Farbauswahl.RED+"::::::."+Farbauswahl.RESET+""+Farbauswahl.BRICKSTONE_RED+"| \\[  === ]______|_\n"+Farbauswahl.RESET+"";

        IntConsumer printChar = i -> System.out.print((char) i);
        asciiArt.chars().forEach(printChar);
    }
//------------------------------------------------------Stadt Menü zuende
}
