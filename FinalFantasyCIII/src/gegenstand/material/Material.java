package gegenstand.material;

import gegenstand.Gegenstand;
import hilfsklassen.ZufallsZahlenGenerator;

public abstract class Material extends Gegenstand {

    public static final Eisenerz EISENERZ = new Eisenerz();
    public static final Golderz GOLDERZ = new Golderz();
    public static final Mithril MITHRIL = new Mithril();
    public static final Popel POPEL = new Popel();
    public static final Schleim SCHLEIM = new Schleim();
    public static final Silbererz SILBERERZ = new Silbererz();

    /**
     * @return MaterialArt
     * @author Nick
     * @since 20.11.2023
     */
    public static Material zufaelligeMaterialArt() {
        int nummer = ZufallsZahlenGenerator.zufallsZahlIntAb1(6);
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
