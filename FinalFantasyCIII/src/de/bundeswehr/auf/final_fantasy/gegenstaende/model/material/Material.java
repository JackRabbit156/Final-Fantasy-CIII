package de.bundeswehr.auf.final_fantasy.gegenstaende.model.material;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.Gegenstand;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;

public abstract class Material extends Gegenstand {

    /**
     * Singleton zur Nutzung der Materialien in der Map
     * @author Nick
     * @since 06.12.2023
     */
    public static final Eisenerz EISENERZ = new Eisenerz();
    /**
     * Singleton zur Nutzung der Materialien in der Map
     * @author Nick
     * @since 06.12.2023
     */
    public static final Golderz GOLDERZ = new Golderz();
    /**
     * Singleton zur Nutzung der Materialien in der Map
     * @author Nick
     * @since 06.12.2023
     */
    public static final Mithril MITHRIL = new Mithril();
    /**
     * Singleton zur Nutzung der Materialien in der Map
     * @author Nick
     * @since 06.12.2023
     */
    public static final Popel POPEL = new Popel();
    /**
     * Singleton zur Nutzung der Materialien in der Map
     * @author Nick
     * @since 06.12.2023
     */
    public static final Schleim SCHLEIM = new Schleim();
    /**
     * Singleton zur Nutzung der Materialien in der Map
     * @author Nick
     * @since 06.12.2023
     */
    public static final Silbererz SILBERERZ = new Silbererz();

    /**
     * @return MaterialArt
     * @author Nick
     * @since 20.11.2023
     */
    public static Material zufaelligeMaterialArt() {
        int nummer = ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(6);
        Material erg = EISENERZ;
        switch (nummer) {
            case 1:
                erg = EISENERZ;
                break;
            case 2:
                erg = GOLDERZ;
                break;
            case 3:
                erg = MITHRIL;
                break;
            case 4:
                erg = POPEL;
                break;
            case 5:
                erg = SCHLEIM;
                break;
            case 6:
                erg = SILBERERZ;
                break;
            default:
                break;

        }
        return erg;
    }


}
