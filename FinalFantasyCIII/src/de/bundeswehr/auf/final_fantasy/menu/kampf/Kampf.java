package de.bundeswehr.auf.final_fantasy.menu.kampf;

import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;

import java.util.ArrayList;
import java.util.List;

public class Kampf {

    private Charakter aktuellerCharakter;
    private final List<Feind> feindeDieGestorbenSind = new ArrayList<>();
    private final List<Feind> feindeDieNochActionHaben = new ArrayList<>();
    private final List<Feind> feindeDieNochLeben = new ArrayList<>();
    private final List<SpielerCharakter> freundeDieGestorbenSind = new ArrayList<>();
    private final List<SpielerCharakter> freundeDieNochActionHaben = new ArrayList<>();
    private final List<SpielerCharakter> freundeDieNochLeben = new ArrayList<>();
    private boolean kampfVorbei;

    public Charakter getAktuellerCharakter() {
        return aktuellerCharakter;
    }

    public List<Feind> getFeindeDieGestorbenSind() {
        return feindeDieGestorbenSind;
    }

    public List<Feind> getFeindeDieNochActionHaben() {
        return feindeDieNochActionHaben;
    }

    public List<Feind> getFeindeDieNochLeben() {
        return feindeDieNochLeben;
    }

    public List<SpielerCharakter> getFreundeDieGestorbenSind() {
        return freundeDieGestorbenSind;
    }

    public List<SpielerCharakter> getFreundeDieNochActionHaben() {
        return freundeDieNochActionHaben;
    }

    public List<SpielerCharakter> getFreundeDieNochLeben() {
        return freundeDieNochLeben;
    }

    public boolean isKampfVorbei() {
        return kampfVorbei;
    }

    public void setAktuellerCharakter(Charakter aktuellerCharakter) {
        this.aktuellerCharakter = aktuellerCharakter;
    }

    public void setKampfVorbei(boolean kampfVorbei) {
        this.kampfVorbei = kampfVorbei;
    }

}
