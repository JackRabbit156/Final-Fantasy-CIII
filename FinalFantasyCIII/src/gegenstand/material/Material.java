package gegenstand.material;

import gegenstand.Gegenstand;
import hilfsklassen.ZufallsZahlenGenerator;

public abstract class Material extends Gegenstand {

    /**
     * @return MaterialArt
     * @author Nick
     * @since 20.11.2023
     */
    public static Material zufaelligeMaterialArt() {
        int nummer = ZufallsZahlenGenerator.zufallsZahlIntAb1(6);
        Material erg = new Eisenerz();
        switch (nummer) {
            case 1:
                erg = new Eisenerz();
                break;
            case 2:
                erg = new Golderz();
                break;
            case 3:
                erg = new Mitrhil();
                break;
            case 4:
                erg = new Popel();
                break;
            case 5:
                erg = new Schleim();
                break;
            case 6:
                erg = new Silbererz();
                break;
        }
        return erg;
    }
}
