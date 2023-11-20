package gegenstand;

import charakter.model.SpielerCharakter;
import gegenstand.material.*;
import gegenstand.verbrauchsgegenstand.Manatränke.GrosserManatrank;
import gegenstand.verbrauchsgegenstand.Manatränke.KleinerManatrank;
import gegenstand.verbrauchsgegenstand.Manatränke.MittlererManatrank;
import gegenstand.verbrauchsgegenstand.Verbrauchsgegenstand;
import gegenstand.verbrauchsgegenstand.heiltraenke.GrosserHeiltrank;
import gegenstand.verbrauchsgegenstand.heiltraenke.KleinerHeiltrank;
import gegenstand.verbrauchsgegenstand.heiltraenke.MittlererHeiltrank;

import java.util.HashMap;

public class GegenstandController{

    private static final Verbrauchsgegenstand[] VERBRAUCHSGEGENSTAENDE = {new KleinerHeiltrank(), new MittlererHeiltrank(),
            new GrosserHeiltrank(), new KleinerManatrank(), new MittlererManatrank(),
            new GrosserManatrank()};

    public static Verbrauchsgegenstand[] rueckgabeAllerVerbrauchsgegenstaende(){return VERBRAUCHSGEGENSTAENDE;}

    public static Verbrauchsgegenstand rueckgabeSpezifischerVerbrauchsgegenstand(String name){
        Verbrauchsgegenstand rueckgabe = null;
        for (Verbrauchsgegenstand verbrauchsgegenstand : VERBRAUCHSGEGENSTAENDE) {
            if (verbrauchsgegenstand.getName().equals(name)){
                rueckgabe = verbrauchsgegenstand;
            }
        }
        return rueckgabe;
    }

    public static HashMap<Verbrauchsgegenstand, Integer> verwendeVerbrauchsgegenstand(HashMap<Verbrauchsgegenstand, Integer> verbrauchsgegenstaende,
                                                                               Verbrauchsgegenstand verwendeterVerbGgst,
                                                                               SpielerCharakter spielerCharakter){
        if (verbrauchsgegenstaende.get(verwendeterVerbGgst) > 0){
            verwendeterVerbGgst.gegenstandVerwenden(spielerCharakter);
            verbrauchsgegenstaende.put(verwendeterVerbGgst, verbrauchsgegenstaende.get(verwendeterVerbGgst) - 1);
        }
        if (verbrauchsgegenstaende.get(verwendeterVerbGgst) == 0){
            verbrauchsgegenstaende.remove(verwendeterVerbGgst);
        }
        return verbrauchsgegenstaende;
    }

    private static final Material[] MATERIALIEN = {new Eisenerz(), new Golderz(),
            new Silbererz(), new Mitrhil(), new Schleim(), new Popel()};

    public static Material[] rueckgabeAllerMaterialien(){return MATERIALIEN;}

    public static Material rueckgabeSpezifischerMaterialien(String name){
        Material rueckgabe = null;
        for (Material material : MATERIALIEN) {
            if (material.getName().equals(name)){
                rueckgabe = material;
            }
        }
        return rueckgabe;
    }
}
