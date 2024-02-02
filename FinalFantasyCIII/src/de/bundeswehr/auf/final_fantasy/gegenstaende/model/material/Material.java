package de.bundeswehr.auf.final_fantasy.gegenstaende.model.material;

import de.bundeswehr.auf.final_fantasy.gegenstaende.model.Gegenstand;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Materialien;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;

public abstract class Material extends Gegenstand {

    /**
     * @return MaterialArt
     * @author Nick
     * @since 20.11.2023
     */
    public static Material zufaelligeMaterialArt() {
        Material result;
        switch (ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(6)) {
            case 2:
                result = Materialien.GOLDERZ;
                break;
            case 3:
                result = Materialien.MITHRIL;
                break;
            case 4:
                result = Materialien.POPEL;
                break;
            case 5:
                result = Materialien.SCHLEIM;
                break;
            case 6:
                result = Materialien.SILBERERZ;
                break;
            case 1:
            default:
                result = Materialien.EISENERZ;
                break;
        }
        return result;
    }

}
