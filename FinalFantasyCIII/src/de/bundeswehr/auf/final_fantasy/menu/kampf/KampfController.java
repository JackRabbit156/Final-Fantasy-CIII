package de.bundeswehr.auf.final_fantasy.menu.kampf;

import de.bundeswehr.auf.final_fantasy.Game;
import de.bundeswehr.auf.final_fantasy.charakter.controller.CharakterController;
import de.bundeswehr.auf.final_fantasy.charakter.controller.FeindController;
import de.bundeswehr.auf.final_fantasy.charakter.model.Charakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.Feind;
import de.bundeswehr.auf.final_fantasy.charakter.model.Klasse;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.controller.GegenstandController;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;
import de.bundeswehr.auf.final_fantasy.menu.hauptmenu.HauptmenuController;
import de.bundeswehr.auf.final_fantasy.menu.overlay.AnsichtsTyp;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.menu.speicherstand.SpeicherstandController;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.Faehigkeit;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.party.model.Party;
import de.bundeswehr.auf.final_fantasy.statistik.GameOverView;
import de.bundeswehr.auf.final_fantasy.statistik.StatistikController;

import java.util.*;

public class KampfController {

    final List<Charakter> aktuelleZugreihenfolge = new ArrayList<>();
    Charakter aktuellerCharakter;
    final List<Charakter> blockendeCharaktere = new ArrayList<>();
    final List<Feind> gegnerAnordnung = new ArrayList<>();
    final boolean[] istKampfVorbei = { false };
    final List<String> kampfWerteLog = new ArrayList<>();
    final Party party;
    final List<SpielerCharakter> partyAnordnung = new ArrayList<>();
    private final FeindController feindController;
    private Feind[] feinde;
    private final List<Feind> feindeDieGestorbenSind = new ArrayList<>();
    private final List<Feind> feindeDieNochActionHaben = new ArrayList<>();
    private final List<Feind> feindeDieNochLeben = new ArrayList<>();
    private final List<SpielerCharakter> freundeDieGestorbenSind = new ArrayList<>();
    private final List<SpielerCharakter> freundeDieNochActionHaben = new ArrayList<>();
    private final List<SpielerCharakter> freundeDieNochLeben = new ArrayList<>();
    private final Game gameController;
    private Faehigkeit gegnerFaehigkeit;
    private final SpielerCharakter hauptCharakterVorKampfbeginn;
    private final HauptmenuController hauptmenuController;
    private KampfView kampfView;
    private final List<SpielerCharakter> nebenCharaktereVorKampfbeginn = new ArrayList<>();
    private final PartyController partyController;
    private final Random random = new Random();
    private final List<Charakter> selbstBuffCharaktere = new ArrayList<>();
    private final SpeicherstandController speicherstandController;
    private final StatistikController statistikController;
    private final ViewController viewController;
    private final List<Charakter> zielGruppe = new ArrayList<>();

    public KampfController(FeindController feindController, PartyController partyController,
                           StatistikController statistikController, Game gameController,
                           HauptmenuController hauptmenuController, SpeicherstandController speicherstandController,
                           ViewController viewController) {
        this.feindController = feindController;
        this.partyController = partyController;
        this.statistikController = statistikController;
        this.gameController = gameController;
        this.hauptmenuController = hauptmenuController;

        hauptCharakterVorKampfbeginn = partyController.getParty().getHauptCharakter().clone();
        for (SpielerCharakter nebenCharakter : partyController.getParty().getNebenCharakter()) {
            if (nebenCharakter != null) {
                nebenCharaktereVorKampfbeginn.add(nebenCharakter.clone());
            }
        }
        this.party = partyController.getParty();
        this.speicherstandController = speicherstandController;
        this.viewController = viewController;
    }

    public static List<Faehigkeit> getAktiveFaehigkeiten(Charakter charakter) {
        List<Faehigkeit> moeglicheFaehigkeiten = new ArrayList<>();
        for (Faehigkeit faehigkeit : charakter.getFaehigkeiten()) {
            if (faehigkeit.getLevel() > 0) {
                moeglicheFaehigkeiten.add(faehigkeit);
            }
        }
        return moeglicheFaehigkeiten;
    }

    /**
     * Prüft, ob der Kampf vorbei ist
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    public void aktualisiereIstKampfVorbei() {
        int feindeCounter = 0;
        int partyCounter = 0;
        for (Charakter charakter : aktuelleZugreihenfolge) {
            if (charakter instanceof Feind) {
                feindeCounter++;
            }
            else {
                partyCounter++;
            }
        }
        istKampfVorbei[0] = feindeCounter == 0 || partyCounter == 0;
    }

    /**
     * Aktualisiert die Zugreihenfolge nach einer ausgeführten Aktion und entfernt
     * gegenbenfalls blockende Charaktere
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    public void aktualisiereZugreihenfolge() {
        Charakter charakterDerAktionAusgefuehrtHat = aktuelleZugreihenfolge.get(0);
        aktuelleZugreihenfolge.remove(0);
        aktuelleZugreihenfolge.add(charakterDerAktionAusgefuehrtHat);
        for (Charakter charakter : new ArrayList<>(aktuelleZugreihenfolge)) {
            if (charakter.getGesundheitsPunkte() < 1) {
                aktuelleZugreihenfolge.remove(charakter);
                if (charakter instanceof Feind) {
                    Feind feind = (Feind) charakter;
                    feindeDieNochLeben.remove(feind);
                    feindeDieGestorbenSind.add(feind);
                }
                else {
                    SpielerCharakter spielerCharakter = (SpielerCharakter) charakter;
                    freundeDieNochLeben.remove(spielerCharakter);
                    freundeDieGestorbenSind.add(spielerCharakter);
                }
            }
        }
        aktuellerCharakter = aktuelleZugreihenfolge.get(0);
        blockendeCharaktere.remove(aktuellerCharakter);
    }

    /**
     * Blocken wird durchgefuehrt bis der Charakter erneut dran ist oder stirbt.
     * Verteidigung und Magische Verteidigung wird um Angriff repektive Magischen
     * Angriff erhoeht.
     *
     * @author Melvin
     * @since 18.11.2023
     */
    public void blocken() {
        aktuellerCharakter
                .setVerteidigung(aktuellerCharakter.getVerteidigung() + aktuellerCharakter.getPhysischeAttacke());
        aktuellerCharakter.setMagischeVerteidigung(
                aktuellerCharakter.getMagischeVerteidigung() + aktuellerCharakter.getMagischeAttacke());
        blockendeCharaktere.add(aktuellerCharakter);
        aktualisiereZugreihenfolge();
    }

    /**
     * Resettet Statuswerte nach Kampf bevor es zurück ins GameGub geht
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    public void buffsUndDebuffsEntferne() {
        // Vor Kampfauswertung muessen alle Statuswerte (ausser aktuelle HP) wieder auf
        // ihren Wert von vor Kampfbeginn gesetzt werden.
        boolean hautpcharakterLebtNoch = false;

        // Hauptcharakter Statuswerte werden zurueckgesetzt (inklusive Leben)
        partyController.getParty().setHauptCharakter(hauptCharakterVorKampfbeginn);

        // Nebencharakter Statuspunkte werden zurueckgesetzt (inklusive Leben)
        SpielerCharakter[] partyUeberschreibung = new SpielerCharakter[3];
        int counter = 0;
        for (SpielerCharakter spielerCharakter :
                nebenCharaktereVorKampfbeginn) {
            partyUeberschreibung[counter] = spielerCharakter;
            counter++;
        }

        // Es gibt SpielerCharaktere die noch Leben
        if (!freundeDieNochLeben.isEmpty()) {
            for (SpielerCharakter spielerCharakter : new ArrayList<>(freundeDieNochLeben)) {
                // Wenn die Geschichte des Charakters zeigt, dass er der Hauptcharakter ist
                // werden die HP des Hauptcharakters auf die von diesem Spielercharakter
                // gesetzt
                if (spielerCharakter.getGeschichte()
                        .equals(partyController.getParty().getHauptCharakter().getGeschichte())) {
                    partyController.getParty().getHauptCharakter()
                            .setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte());
                    partyController.getParty().getHauptCharakter().setManaPunkte(spielerCharakter.getManaPunkte());
                    freundeDieNochLeben.remove(spielerCharakter);
                    // Das bedeutet, dass der hauptcharakter noch lebt
                    hautpcharakterLebtNoch = true;
                }
            }

            // Wenn der Hauptcharakter nicht mehr lebt werden seine HP auf 0 gesetzt
            if (!hautpcharakterLebtNoch) {
                partyController.getParty().getHauptCharakter().setGesundheitsPunkte(0);
                partyController.getParty().getHauptCharakter().setManaPunkte(0);
            }

            counter = 0;
            SpielerCharakter[] nebencharaktere = new SpielerCharakter[3];

            for (SpielerCharakter spielerCharakter : nebenCharaktereVorKampfbeginn) {
                for (SpielerCharakter nebenCharaktereDieUeberlebtHaben : freundeDieNochLeben) {
                    if (spielerCharakter.getName().equals(nebenCharaktereDieUeberlebtHaben.getName())) {
                        if (nebencharaktere[0] == null) {
                            nebencharaktere[0] = spielerCharakter;
                            spielerCharakter
                                    .setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
                            spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
                        }
                        else if (nebencharaktere[1] == null) {
                            nebencharaktere[1] = spielerCharakter;
                            spielerCharakter
                                    .setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
                            spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
                        }
                        else if (nebencharaktere[2] == null) {
                            nebencharaktere[2] = spielerCharakter;
                            spielerCharakter
                                    .setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
                            spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
                        }
                    }
                }
            }
            for (SpielerCharakter spielerCharakter : nebenCharaktereVorKampfbeginn) {
                for (SpielerCharakter nebenCharaktereDieUeberlebtHaben : freundeDieGestorbenSind) {

                    if (nebencharaktere[0] == null) {
                        nebencharaktere[0] = spielerCharakter;
                        spielerCharakter.setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
                        spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
                    }
                    else if (nebencharaktere[1] == null) {
                        nebencharaktere[1] = spielerCharakter;
                        spielerCharakter.setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
                        spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
                    }
                    else if (nebencharaktere[2] == null) {
                        nebencharaktere[2] = spielerCharakter;
                        spielerCharakter.setGesundheitsPunkte(nebenCharaktereDieUeberlebtHaben.getGesundheitsPunkte());
                        spielerCharakter.setManaPunkte(nebenCharaktereDieUeberlebtHaben.getManaPunkte());
                    }
                }
            }

            partyController.getParty().setNebenCharakter(nebencharaktere);

        }

        // Alles Partymitglieder sind tot und der Kampf wurde verloren. Alle am Anfang
        // des Kampfes erstellten Koepien koennen mit einem HP Wert von 0 an die Party
        // zurueckgegeben werden
        else {
            SpielerCharakter hauptCharakterVerloren = hauptCharakterVorKampfbeginn.clone();
            SpielerCharakter[] nebenCharaktereVerloren = new SpielerCharakter[3];
            hauptCharakterVerloren.setGesundheitsPunkte(0);
            counter = 0;
            for (SpielerCharakter nebenCharakter : nebenCharaktereVorKampfbeginn) {
                nebenCharaktereVerloren[counter] = nebenCharakter;
                nebenCharaktereVerloren[counter].setGesundheitsPunkte(0);
                counter++;
            }
            partyController.getParty().setHauptCharakter(hauptCharakterVerloren);
            partyController.getParty().setNebenCharakter(nebenCharaktereVerloren);
        }

        // Aktualisierter Nebencharakter-Array wird der Party uebergeben
        // partyController.getParty().setNebenCharakter(partyUeberschreibung);
        kampfAuswerten();
    }

    /**
     * Jeder SpielerCharakter hat die Moeglichkeit fliehen() als Action
     * auszuwaehlen. Die Party hat eine Grundchance von 20% zu fliehen. Die
     * Differenz von der Gesamtbeweglichkeit der Party und der Gegenrgruppe
     * entscheidet darüber, ob die Fluchtchance sich erhöht oder nicht. Ist das
     * Fliehen erfolgreich, flieht die gesamte Gruppe und der Kampf ist beendet.
     *
     * @author Melvin
     * @since 18.11.2023
     */
    public void fliehen() {
        int nettoBeweglichkeit = 0;
        double fluchtchance = 0.2;
        for (SpielerCharakter spielerCharakter : freundeDieNochLeben) {
            nettoBeweglichkeit += spielerCharakter.getBeweglichkeit();
        }
        for (Feind feind : feindeDieNochLeben) {
            nettoBeweglichkeit -= feind.getBeweglichkeit();
        }

        // 20% + 0.1125% pro Beweglichkeitsvorteil der Gruppe (positive Differenz)
        if (nettoBeweglichkeit > 0) {
            fluchtchance += (nettoBeweglichkeit / 800.0);
        }
        if (fluchtchance > 1.0) {
            fluchtchance = 1.0;
        }
//		fluchtchance = 0;
        if (random.nextDouble() < fluchtchance) {
            istKampfVorbei[0] = true;
        }
        else {
            // Flucht ist fehlgeschlagen, Aktion wird als ausgeführt betrachtet und
            aktualisiereZugreihenfolge();
        }
    }

    /**
     * Hier kann auf das Party-Verbrauchsgegenstandsinventar zugegriffen werden.
     * Methoden sind alles ausgelagert.
     *
     * @param benutzenAuf Zielcharakter für Verbrauchsgegenstand - SpielerCharakter
     * @param item        Verbrauchsgegenstand - Verbrauchsgegenstand
     * @author Melvin
     * @since 18.11.2023
     */

    public void gegenstand(Verbrauchsgegenstand item, SpielerCharakter benutzenAuf) {
        GegenstandController.verwendeVerbrauchsgegenstand(partyController.getParty().getVerbrauchsgegenstaende(), item,
                benutzenAuf);
        aktualisiereZugreihenfolge();
    }

    /**
     * Abhängig von der Klasse des Gegners wird hier seine Angriffs/Heal- Logik
     * bestimmt und entweder geblockt oder eine Fähigkeit genutzt
     *
     * @param gegner Gegner für den die Logik bestimmt werden soll - Feind
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    public void gegnerlogik(Feind gegner) {
        switch (gegner.getKlasse().getBezeichnung()) {
            case Klasse.TNK:
                // 65% Wahrscheinlichkeit, dass der Tank angreift (Selbstheilung oder Schaden am
                // SpielerCharaktere-Team)
                if (random.nextDouble() < 0.65) {
                    gegnerLogikFaehigkeitUndZielGruppe();
                    kampfView.setZielGruppe(zielGruppe);
                    kampfView.setFaehigkeit(gegnerFaehigkeit);
                    faehigkeitBenutzen(gegner, zielGruppe, gegnerFaehigkeit);
                }
                // 35% Chance, dass der Tank blockt
                else {
                    blocken();
                }
                break;
            // Alle anderen Klassen haben die gleichen Wahrscheinlichkeiten zu blocken (10%)
            // oder eine Faehigkeit zu benutzen (90%)
            case Klasse.HLR:
            case Klasse.MDD:
            case Klasse.PDD:
                if (random.nextDouble() < 0.9) {
                    gegnerLogikFaehigkeitUndZielGruppe();
                    kampfView.setZielGruppe(zielGruppe);
                    kampfView.setFaehigkeit(gegnerFaehigkeit);
                    faehigkeitBenutzen(gegner, zielGruppe, gegnerFaehigkeit);
                }
                else {
                    blocken();
                }
                break;
            default:
                throw new RuntimeException("Ungültige Klasse: " + gegner.getKlasse().getBezeichnung());
        }
    }

    /**
     * Kampfende wird ausgewertet - Exp wird verteilt Gold und Ressourcen werden
     * verteilt Statistik wird gepflegt GameOver wird geprueft Endet in Hub oder
     * GameOver
     *
     * @author Nick
     * @since 04.12.2023
     */
    public void kampfAuswerten() {
        kampfView.updateKampfBildschirm();
        resetKampfDaten();
        Party party = partyController.getParty();
        List<SpielerCharakter> ueberlebende = new ArrayList<>();
        List<SpielerCharakter> kaputte = new ArrayList<>();
        if (party.getHauptCharakter().getGesundheitsPunkte() > 0) {
            ueberlebende.add(party.getHauptCharakter());
        }
        else {
            kaputte.add(party.getHauptCharakter());
        }
        SpielerCharakter[] nebenCharakter = party.getNebenCharakter();
        for (SpielerCharakter charakter : nebenCharakter) {
            if (charakter != null && charakter.getGesundheitsPunkte() > 0) {
                ueberlebende.add(charakter);
            }
            else if (charakter != null) {
                kaputte.add(charakter);
            }
        }
        int ueberlebendeGegner = 0;
        for (Feind feind : feinde) {
            if (feind != null && feind.getGesundheitsPunkte() > 0) {
                ueberlebendeGegner++;
            }
        }
        if (ueberlebende.size() > 0 && ueberlebendeGegner <= 0) {
            // Sieg
            int gewonnenesGold = ((int) Math.floor(partyController.getPartyLevel()) * 10);
            partyController.goldHinzufuegen(gewonnenesGold);
            for (SpielerCharakter spielerCharakter : ueberlebende) {
                CharakterController.erfahrungHinzufuegen(spielerCharakter, 10);
                kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
                        .concat(spielerCharakter.getName() + " hat 10 Erfahrungspunkte erhalten!\n"));
            }
            statistikController.goldErhoehen(gewonnenesGold);
            statistikController.durchgefuehrteKaempfeErhoehen();
            statistikController.gewonneneKaempfeErhoehen();
            if (gameController.isHardcore()) {
                SpielerCharakter[] soeldner = party.getNebenCharakter() != null ? party.getNebenCharakter()
                        : new SpielerCharakter[0];
                for (int i = 0; i < soeldner.length; i++) {
                    if (soeldner[i] != null) {
                        if (soeldner[i].getGesundheitsPunkte() == 0) {
                            kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
                                    .concat(soeldner[i].getName() + " ist tot und hat die Party verlassen.\n"));
                            soeldner[i] = null;
                        }
                    }
                }
                party.setNebenCharakter(soeldner);
            }
            boolean ausruestungsloot = (ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(10) <= 1);
            if (ausruestungsloot) {
                int ausruestungsArt = ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(3);
                if (ausruestungsArt == 1) {
                    partyController.ausruestungsgegenstandHinzufuegen(feinde[0].getWaffe());
                    kampfView.kampfErgebnis.setText(
                            kampfView.kampfErgebnis.getText().concat(feinde[0].getWaffe().getName() + " erhalten!\n"));
                }
                if (ausruestungsArt == 2) {
                    partyController.ausruestungsgegenstandHinzufuegen(feinde[0].getRuestung());
                    kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
                            .concat(feinde[0].getRuestung().getName() + " erhalten!\n"));
                }
                if (ausruestungsArt == 3) {
                    if (feinde[0].getAccessoires()[0] != null) {
                        partyController.ausruestungsgegenstandHinzufuegen(feinde[0].getAccessoires()[0]);
                        kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
                                .concat(feinde[0].getAccessoires()[0].getName() + " erhalten!\n"));
                    }
                }
            }
            Material material = Material.zufaelligeMaterialArt();
            partyController.materialHinzufuegen(material, ((int) Math.floor(partyController.getPartyLevel())));
            kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
                    .concat(((int) Math.floor(partyController.getPartyLevel())) + "x "
                            + material.getClass().getSimpleName() + " erhalten.\n" + gewonnenesGold
                            + " Gold erhalten.\n"));
            kampfView.kampfErgebnisContainer.getChildren().add(0, kampfView.sieg);
        }
        if (ueberlebende.size() == 0) {
            // Niederlage
            statistikController.durchgefuehrteKaempfeErhoehen();
            statistikController.verloreneKaempfeErhoehen();
            if (partyController.getPartyGold() >= (Math.floor(partyController.getPartyLevel() * 2.5))
                    && !gameController.isHardcore()) {
                // Genug gold im Nicht-Hardcore zum wiederbeleben
                partyController.goldAbziehen((int) Math.floor(partyController.getPartyLevel() * 2.5));
                for (SpielerCharakter spielerCharakter : kaputte) {
                    spielerCharakter.setGesundheitsPunkte(1);
                }
                kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
                        .concat("Die ohnmächtigen Charaktere wurden für "
                                + ((int) (Math.floor(partyController.getPartyLevel() * 2.5)))
                                + " Gold wiederbelebt.\n"));
                kampfView.kampfErgebnisContainer.getChildren().add(0, kampfView.niederlage);
            }
            else {
                if (gameController.isHardcore()) {
                    // Verloren und ist Hardcore
                    try {
                        speicherstandController.entferneSpeicherstandHardcore(partyController);
                    } catch (Exception e) {
                    }
                }
                hauptmenuController.spielVorhandenProperty().set(false);
                GameOverView gameOverView = new GameOverView(statistikController.getStatistik(), partyController,
                        viewController);
                viewController.anmelden(gameOverView, null, AnsichtsTyp.OHNE_OVERLAY);
            }
        }
        if (ueberlebende.size() > 0 && ueberlebendeGegner > 0) {
            // Flucht
            if (gameController.isHardcore()) {
                // HardCore
                SpielerCharakter[] soeldner = party.getNebenCharakter() != null ? party.getNebenCharakter()
                        : new SpielerCharakter[0];
                for (int i = 0; i < soeldner.length; i++) {
                    if (soeldner[i] != null) {
                        if (soeldner[i].getGesundheitsPunkte() == 0) {
                            kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText()
                                    .concat(soeldner[i].getName() + " ist tot und hat die Party verlassen.\n"));
                            soeldner[i] = null;
                        }
                    }
                }
                party.setNebenCharakter(soeldner);
            }
            kampfView.kampfErgebnis.setText(kampfView.kampfErgebnis.getText().concat("Flucht erfolgreich.\nFeigling!"));
        }
        kampfView.aktionAusgefuehrtInfoAnzeige.toBack();
        kampfView.kampfErgebnisContainer.toFront();
    }

    /**
     * Startpunkt für kaempfe
     *
     * @author Maass
     * @since 19.11.2023
     */
    public void kampfStarten() {
        if (Game.debugModus) {
            kampfWerteLog.add("[DEBUG] Party:\n");
            kampfWerteLog.add(partyController.getParty().getHauptCharakter() + "\n");
            for (SpielerCharakter nebenCharakter : partyController.getParty().getNebenCharakter()) {
                if (nebenCharakter != null) {
                    kampfWerteLog.add(nebenCharakter + "\n");
                }
            }
        }
        this.feinde = feindController.gegnerGenerieren(partyController);

        if (Game.debugModus) {
            kampfWerteLog.add("[DEBUG] Feinde:\n");
            for (Feind feind : feinde) {
                kampfWerteLog.add(feind + "\n");
            }
        }

        List<Charakter> zugReihenfolge = new ArrayList<>();
        zugReihenfolge.add(partyController.getParty().getHauptCharakter());
        for (SpielerCharakter spielerCharakter : partyController.getParty().getNebenCharakter()) {
            if (spielerCharakter != null && spielerCharakter.getGesundheitsPunkte() > 0) {
                zugReihenfolge.add(spielerCharakter);
            }
        }
        zugReihenfolge.addAll(Arrays.asList(feinde));
        zugReihenfolge.sort(Comparator.comparingInt(Charakter::getBeweglichkeit));

        if (Game.debugModus) {
            StringBuilder sb = new StringBuilder();
            ListIterator<Charakter> iter = zugReihenfolge.listIterator(zugReihenfolge.size());
            while (iter.hasPrevious()) {
                Charakter charakter = iter.previous();
                if (sb.length() != 0) {
                    sb.append(" -> ");
                }
                sb.append(charakter.getName());
                sb.append(" (");
                sb.append(charakter.getBeweglichkeit());
                sb.append(")");
            }
            kampfWerteLog.add("[DEBUG] Zugreihenfolge: " + sb + "\n");
        }

        kampfBeginn(zugReihenfolge);
    }

    // Runde vorbei. Alle noch lebenden SpielerCharaktere und Feinde regenerieren HP
    // und MP
    public void regenerationNachRunde() {
        if (!istKampfVorbei[0]) {
            for (SpielerCharakter freund : freundeDieNochLeben) {
                freund.setGesundheitsPunkte(
                        freund.getGesundheitsPunkte() + (int) Math.round(freund.getGesundheitsRegeneration() / 8.0));
                freund.setManaPunkte(freund.getManaPunkte() + (int) Math.round(freund.getManaRegeneration() / 8.0));
                if (freund.getGesundheitsPunkte() > freund.getMaxGesundheitsPunkte()) {
                    freund.setGesundheitsPunkte(freund.getMaxGesundheitsPunkte());
                }
                if (freund.getManaPunkte() > freund.getMaxManaPunkte()) {
                    freund.setManaPunkte(freund.getMaxManaPunkte());
                }
            }
            for (Feind feind : feindeDieNochLeben) {
                feind.setGesundheitsPunkte(
                        feind.getGesundheitsPunkte() + (int) Math.round(feind.getGesundheitsRegeneration() / 8.0));
                feind.setManaPunkte(feind.getManaPunkte() + (int) Math.round(feind.getManaRegeneration() / 8.0));
                if (feind.getGesundheitsPunkte() > feind.getMaxGesundheitsPunkte()) {
                    feind.setGesundheitsPunkte(feind.getMaxGesundheitsPunkte());
                }
                if (feind.getManaPunkte() > feind.getMaxManaPunkte()) {
                    feind.setManaPunkte(feind.getMaxManaPunkte());
                }
            }
        }
        for (SpielerCharakter spielerCharakter : freundeDieNochLeben) {
            if (spielerCharakter.getGesundheitsPunkte() > 0) {
                freundeDieNochActionHaben.add(spielerCharakter);
            }
            else {
                freundeDieNochActionHaben.remove(spielerCharakter);
            }
        }
        for (Feind feind : feindeDieNochLeben) {
            if (feind.getGesundheitsPunkte() > 0) {
                feindeDieNochActionHaben.add(feind);
            }
            else {
                feindeDieNochActionHaben.remove(feind);
            }
        }
        if (!istKampfVorbei[0]) {
            // TODO ?
        }
        if (feindeDieNochLeben.isEmpty() || freundeDieNochLeben.isEmpty()) {
            istKampfVorbei[0] = true;
            if (feindeDieNochLeben.isEmpty()) {
                freundeDieNochActionHaben.clear();
            }
            else {
                feindeDieNochActionHaben.clear();
            }
        }
    }

    /**
     * Resettet alle für den Kampf wichtigen Daten nach Kampfabschluss für den
     * nächsten Kampf
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    public void resetKampfDaten() {
        for (Charakter charakter : blockendeCharaktere) {
            if (charakter instanceof SpielerCharakter) {
                charakter.setVerteidigung(charakter.getVerteidigung() - charakter.getPhysischeAttacke());
                charakter.setMagischeVerteidigung(charakter.getMagischeVerteidigung() - charakter.getMagischeAttacke());
            }
        }
        freundeDieGestorbenSind.clear();
        freundeDieNochLeben.clear();
        freundeDieNochActionHaben.clear();
        feindeDieNochLeben.clear();
        feindeDieNochActionHaben.clear();
        feindeDieGestorbenSind.clear();
        aktuelleZugreihenfolge.clear();
        gegnerAnordnung.clear();
        partyAnordnung.clear();
        istKampfVorbei[0] = false;
        blockendeCharaktere.clear();
        selbstBuffCharaktere.clear();
        nebenCharaktereVorKampfbeginn.clear();
        zielGruppe.clear();
    }

    public void zurueckZumHub() {
        viewController.aktuelleNachHinten();
    }

    /**
     * Ausgewähle Fähigkeit wird vom aktuellen Charakter auf die gewählten Ziele
     * benutzt
     *
     * @param charakterDerFaehigkeitBenutzt Charakter der die Fähigkeit benutzt -
     *                                      Charakter
     * @param ziele                         Ziele der Fähigkeit -
     *                                      ArrayList<Charakter>
     * @param faehigkeit                    Benutzte Fähigkeit - Fähigkeit
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */

    void faehigkeitBenutzen(Charakter charakterDerFaehigkeitBenutzt, List<Charakter> ziele,
                            Faehigkeit faehigkeit) {

        boolean hatCharakterGenugMana = true;
        List<Charakter> zielWahl = new ArrayList<>(ziele);
        kampfWerteLog.clear();

        // Faehigkeit von Freund oder Feind kann ab hier eingesetzt werden und wird
        // entsprechend durchgefuehrt
        try {
            aktuellerCharakter.setManaPunkte(aktuellerCharakter.getManaPunkte() - faehigkeit.getManaKosten());
        } catch (Exception e) {
            zielWahl.clear();
            faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
            if (faehigkeit.isIstFreundlich()) {
                if (aktuellerCharakter instanceof Feind) {
                    zielWahl.add(feindeDieNochLeben.get(0));
                }
                else {
                    zielWahl.add(freundeDieNochLeben.get(0));
                }
            }
            else {
                if (aktuellerCharakter instanceof Feind) {
                    zielWahl.add(freundeDieNochLeben.get(0));
                }
                else {
                    zielWahl.add(feindeDieNochLeben.get(0));
                }
            }
        }

        // Jeder Charakter hat eine Grundchance von 60% zu treffen. Jeder Punkt in
        // Genauigkeit, bis zum Wert '20', erhoeht die Trefferwahrscheinlichkeit um 2%.
        // Wenn das Genauigkeitsattribut den Wert '20' oder hoeher erreicht hat,
        // betraegt die Wahrscheinlichkeit zu treffen 100%. Jeder Attributpunkt
        // in Genauigkeit ueber 20 wird fuer die Berechnung der
        // kritischerTreffer-Wahrscheinlichkeit benutzt, wodurch eine 'Ueberskillung'
        // keine Verschwendung darstellt.
        double treffer = random.nextDouble();
        if (treffer < (0.65 + 0.02 * aktuellerCharakter.getGenauigkeit())) {
            int aktuellerCharakterMacht;
            int betroffenerCharakterAbwehr;
            double basisSchadensWert = 100.0;
            // Effekt einzeln auf jedes Ziel angewendet bis alle Ziele abgearbeitet wurden
            while (!zielWahl.isEmpty()) {
                Charakter betroffenerCharakter = zielWahl.get(0);
                String zielAttribut = faehigkeit.getZielAttribut();
                // Zuerst wird geguckt, ob es sich um eine physische oder magische Faehigkeit
                // handelt Abhaengig davon werden physische bzw. magische Angriffs und
                // Verteidigungswerte zur Berechnung verwendet
                if (faehigkeit.getFaehigkeitsTyp().equals("physisch")) {
                    aktuellerCharakterMacht = aktuellerCharakter.getPhysischeAttacke();
                    betroffenerCharakterAbwehr = betroffenerCharakter.getVerteidigung();
                }
                else {
                    aktuellerCharakterMacht = aktuellerCharakter.getMagischeAttacke();
                    betroffenerCharakterAbwehr = betroffenerCharakter.getMagischeVerteidigung();
                }

                // Es wird geguckt, ob die genutzte Faehigkeit kritisch trifft (Heal und
                // Schaden!) Jeder Charakter hat eine Grundchance von 30% kritisch zu treffen.
                // Bei einem kritischen Treffer ist die Faehigkeit grundsaetzlich 66% staerker.
                // Die Wahrscheinlichkeit kritisch zu treffen wird durch Beweglichkeit erhoeht.
                // Zusaetzlich kann eine Faehigkeit selbst eine erhoehte Wahrscheinlichkeit
                // haben kritisch zu treffen. Der Grundwert bei Faehigkeiten ist 1.0.
                // Jeder Attributpunkt des Charakters erhoeht die Wahrscheinlichkeit um 0.2%.
                // Spaetestens wenn man 350 Attributpunkte in Beweglichkeit hat (100%
                // Wahrscheinlichkeit kritisch zu treffen), erhoeht jeder weitere
                // Punkt den kritischen Schaden-Multiplikator um 1%. Wie bereits im Abschnitt
                // 'Genauigkeit' erklaert, wird jeder Punkt in Genauigkeit ueber einem Wert
                // von 20 in die kritische Treffer Berechnung einbezogen.
                // Hierbei wird der Genauigkeitsbonus 1:1 so behandelt wie ein
                // Geschwindigkeit-Attributspunkt. Sollte also die Wahrscheinlichkeit kritisch
                // zu treffen bereits bei 100% liegen, tragen die Bonus-Genauigkeitspunkte
                // ebenfalls zur Erhoehung des kritischen Schaden-Multiplikators bei.
                double kritMultiplikator = 1.0;
                int genauigkeitsBonus = 0;
                int ergebnisWert;
                boolean krit = false;
                if (aktuellerCharakter.getGenauigkeit() > 20) {
                    genauigkeitsBonus = aktuellerCharakter.getGenauigkeit() - 20;
                }
                double kritWahrscheinlichkeit = (0.3 + (faehigkeit.getWahrscheinlichkeit() - 1.0)
                        + 0.002 * (aktuellerCharakter.getBeweglichkeit() + genauigkeitsBonus));
                if (random.nextDouble() < kritWahrscheinlichkeit) {
                    if (kritWahrscheinlichkeit > 1.0) {
                        kritMultiplikator = 1.66 + ((kritWahrscheinlichkeit - 1) / 2);
                    }
                    else {
                        kritMultiplikator = 1.66;
                    }
                    krit = true;
                }

                ergebnisWert = (int) Math.floor((faehigkeit.getEffektStaerke() / basisSchadensWert)
                        * aktuellerCharakterMacht * kritMultiplikator);

                // Der Schwierigkeitsgrad beeinflusst den Basiswert VOR Abzug der
                // Verteidigungen.
                // =================================================================//
                // Leicht -> Spielerwert ist um 20% erhoeht.
                // -> Gegnerwert ist um 20% verringert.
                // Mittel -> Werte fuer alle unveraendert.
                // Schwer -> Spielerwerte 20% verringert.
                // -> Gegnerwerte 20% erhoeht.
                switch (gameController.getSchwierigkeitsgrad()) {
                    case "Leicht":
                        if (aktuellerCharakter instanceof SpielerCharakter) {
                            ergebnisWert *= 1.2;
                        }
                        if (aktuellerCharakter instanceof Feind) {
                            ergebnisWert *= 0.8;
                        }
                        break;
                    case "Schwer":
                        if (aktuellerCharakter instanceof SpielerCharakter) {
                            ergebnisWert *= 0.8;
                        }
                        if (aktuellerCharakter instanceof Feind) {
                            ergebnisWert *= 1.2;
                        }
                }

                // Es wird geguckt welches das ZielAttribut der Faehigkeit ist
                // Heal und Schaden gehen beide auf 'gesundheitsPunkte'
                switch (zielAttribut) {
                    case "gesundheitsPunkte":
                        if (faehigkeit.isIstFreundlich()) {
                            int healWert;
                            // gleiches Team -> Heal -> Verteidigung des Zieles spielt keine Rolle
                            if (ergebnisWert + betroffenerCharakter.getGesundheitsPunkte() > betroffenerCharakter
                                    .getMaxGesundheitsPunkte()) {
                                healWert = betroffenerCharakter.getMaxGesundheitsPunkte()
                                        - betroffenerCharakter.getGesundheitsPunkte();
                            }
                            else {
                                healWert = ergebnisWert;
                            }
                            betroffenerCharakter.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() + healWert);

                            // Wenn der Verbuendete durch den Heal mehr HP haette als durch seine maxHP
                            // moeglich, werden seine aktuellen HP gleich dem maxHP-Wert gesetzt
                            if (betroffenerCharakter.getGesundheitsPunkte() > betroffenerCharakter.getMaxGesundheitsPunkte()) {
                                betroffenerCharakter.setGesundheitsPunkte(betroffenerCharakter.getMaxGesundheitsPunkte());
                            }
                            if (krit) {
                                kampfWerteLog.add("KRITISCHER TREFFER!\n" + betroffenerCharakter.getName() + " wurde um "
                                        + healWert + " geheilt!\n");
                            }
                            else {
                                kampfWerteLog.add(betroffenerCharakter.getName() + " wurde um " + healWert + " geheilt!\n");
                            }
                        }
                        // feindliches Team -> Schaden -> Verteidigung des Zieles muss beachtet werden
                        // Wenn die Verteidigung des Zieles zu gross ist wird kein Schaden verursacht
                        else {
                            ergebnisWert -= betroffenerCharakterAbwehr;
                            if (ergebnisWert < 1) {
                                ergebnisWert = 1;
                            }
                            int hp = Math.min(ergebnisWert, betroffenerCharakter.getGesundheitsPunkte());
                            betroffenerCharakter.setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() - hp);
                            String istGestorbenString = "";

                            // Wenn der toedliche Schaden dazu fuehrt, dass ein Charakter UNTER 0 HP faellt
                            // werden die HP auf 0 gesetzt.

                            if (betroffenerCharakter.getGesundheitsPunkte() <= 0) {
                                istGestorbenString = (betroffenerCharakter.getName() + " ist gestorben.\n");
                                betroffenerCharakter.setGesundheitsPunkte(0);
                            }
                            if (krit) {
                                kampfWerteLog.add("KRITISCHER TREFFER!\n" + betroffenerCharakter.getName() + " hat "
                                        + hp + " Schaden erlitten!\n" + istGestorbenString);
                            }
                            else {
                                kampfWerteLog.add(betroffenerCharakter.getName() + " hat " + hp
                                        + " Schaden erlitten!\n" + istGestorbenString);
                            }
                        }
                        break;
                    case "maxGesundheitsPunkte":
                        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
                        if (faehigkeit.isIstFreundlich()) {
                            betroffenerCharakter
                                    .setMaxGesundheitsPunkte(betroffenerCharakter.getMaxGesundheitsPunkte() + ergebnisWert);

                            // Die aktuellen Gesundheitspunkte muessen um den gleichen Wert erhoeht werden
                            // da die Erhoehung der maxHP mit einem Heal einhergeht
                            betroffenerCharakter
                                    .setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() + ergebnisWert);
                            kampfWerteLog.add("Maximale Gesundheit von " + betroffenerCharakter.getName() + " wurde um "
                                    + ergebnisWert + " erhöht.\n");
                        }
                        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
                        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
                        else {
                            String istGestorben = "";
                            ergebnisWert -= betroffenerCharakter.getResistenz();
                            if (ergebnisWert < 1) {
                                ergebnisWert = 0;
                            }
                            int maxHP = Math.min(ergebnisWert, betroffenerCharakter.getMaxGesundheitsPunkte());
                            betroffenerCharakter
                                    .setMaxGesundheitsPunkte(betroffenerCharakter.getMaxGesundheitsPunkte() - maxHP);

                            // Wenn der Charakter aus dem Gegnerteam durch die Reduktion der maxHP nun
                            // mehr aktuelle als maxHP hat, muessen die aktuellen HP auf den neunen
                            // maxHP-Stand gesetzt werden (impliziert Schaden verursachen)
                            if (betroffenerCharakter.getMaxGesundheitsPunkte() < betroffenerCharakter
                                    .getGesundheitsPunkte()) {
                                betroffenerCharakter.setGesundheitsPunkte(betroffenerCharakter.getMaxGesundheitsPunkte());
                            }

                            // Wenn der Charakter dadurch stirbt und seine HP unter 0 fallen werden sie auf
                            // 0 gesetzt
                            if (betroffenerCharakter.getGesundheitsPunkte() < 0) {
                                betroffenerCharakter.setGesundheitsPunkte(0);
                                istGestorben = betroffenerCharakter.getName() + " ist gestorben.\n";
                            }
                            kampfWerteLog.add("Maximale Gesundheit von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + maxHP + " verringert.\n" + istGestorben);
                        }
                        break;
                    case "manaPunkte":
                        // gleiches Team -> Heal -> Resistenz des Zieles spielt keine Rolle
                        int mp;
                        if (faehigkeit.isIstFreundlich()) {
                            if (betroffenerCharakter.getManaPunkte() + ergebnisWert > betroffenerCharakter
                                    .getMaxManaPunkte()) {
                                mp = betroffenerCharakter.getMaxManaPunkte() - betroffenerCharakter.getManaPunkte();
                            }
                            else {
                                mp = ergebnisWert;
                            }
                            betroffenerCharakter.setManaPunkte(betroffenerCharakter.getManaPunkte() + mp);

                            // Wenn der Verbuendete durch den Buff mehr MP haette als durch seine maxMP
                            // moeglich, werden seine aktuellen MP gleich dem maxMP-Wert gesetzt
                            if (betroffenerCharakter.getManaPunkte() > betroffenerCharakter.getMaxManaPunkte()) {
                                betroffenerCharakter.setManaPunkte(betroffenerCharakter.getMaxManaPunkte());
                            }

                            kampfWerteLog.add(
                                    "Mana von " + betroffenerCharakter.getName() + "\nwurdeum " + mp + " aufgefuellt\n");
                        }
                        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
                        // Wenn die Resistenz des Zieles zu gross ist wird mindestwert angewendet
                        else {
                            ergebnisWert -= betroffenerCharakter.getResistenz();
                            if (ergebnisWert < 1) {
                                ergebnisWert = 1;
                            }
                            if (betroffenerCharakter.getManaPunkte() - ergebnisWert < 0) {
                                mp = betroffenerCharakter.getManaPunkte();
                            }
                            else {
                                mp = ergebnisWert;
                            }
                            betroffenerCharakter.setManaPunkte(betroffenerCharakter.getManaPunkte() - mp);

                            // Wenn MP dadurch UNTER 0 MP fallen werden MP auf 0 gesetzt.
                            if (betroffenerCharakter.getManaPunkte() < 0) {
                                betroffenerCharakter.setManaPunkte(0);
                            }
                            kampfWerteLog.add(
                                    "Mana von " + betroffenerCharakter.getName() + "\nwurde um " + mp + " verringert\n");
                        }
                        break;
                    case "maxManaPunkte":
                        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
                        if (faehigkeit.isIstFreundlich()) {
                            betroffenerCharakter.setMaxManaPunkte(betroffenerCharakter.getMaxManaPunkte() + ergebnisWert);

                            // Die aktuellen MP muessen um den gleichen Wert erhoeht werden
                            // da die Erhoehung der maxMP diese mit anhebt
                            betroffenerCharakter.setManaPunkte(betroffenerCharakter.getManaPunkte() + ergebnisWert);
                            kampfWerteLog.add("Maximales Mana von " + betroffenerCharakter.getName() + "\nwurde um " + ergebnisWert
                                    + " erhöht.\n");
                        }
                        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
                        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
                        else {
                            ergebnisWert -= betroffenerCharakter.getResistenz();
                            if (ergebnisWert < 1) {
                                ergebnisWert = 0;
                            }
                            int maxMP = Math.min(ergebnisWert, aktuellerCharakter.getMaxManaPunkte());
                            betroffenerCharakter.setMaxManaPunkte(betroffenerCharakter.getMaxManaPunkte() - maxMP);

                            // Wenn der Charakter aus dem Gegnerteam durch die Reduktion der maxMP nun
                            // mehr aktuelle als maxMP hat, muessen die aktuellen MP auf den neunen
                            // maxMP-Stand gesetzt werden (impliziert Reduktion der MP)
                            if (betroffenerCharakter.getMaxManaPunkte() < betroffenerCharakter.getManaPunkte()) {
                                betroffenerCharakter.setManaPunkte(betroffenerCharakter.getMaxManaPunkte());
                            }

                            // Wenn seine MP UNTER 0 fallen werden sie auf 0 gesetzt
                            if (betroffenerCharakter.getManaPunkte() < 0) {
                                betroffenerCharakter.setManaPunkte(0);
                            }
                            kampfWerteLog.add("Maximales Mana von " + betroffenerCharakter.getName() + "\nwurde um " + maxMP
                                    + " verringert.\n");
                        }
                        break;
                    case "physischeAttacke":
                        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
                        if (faehigkeit.isIstFreundlich()) {
                            betroffenerCharakter
                                    .setPhysischeAttacke(betroffenerCharakter.getPhysischeAttacke() + ergebnisWert);
                            kampfWerteLog.add("Physische Attacke von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + ergebnisWert + " erhöht.\n");
                        }
                        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
                        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
                        else {
                            ergebnisWert -= betroffenerCharakter.getResistenz();
                            if (ergebnisWert < 1) {
                                ergebnisWert = 0;
                            }
                            int physAtk = Math.min(ergebnisWert, betroffenerCharakter.getPhysischeAttacke());
                            betroffenerCharakter
                                    .setPhysischeAttacke(betroffenerCharakter.getPhysischeAttacke() - physAtk);

                            // Wenn seine PhysischeAttacke UNTER 0 faellt wird sie auf 0 gesetzt
                            if (betroffenerCharakter.getPhysischeAttacke() < 0) {
                                betroffenerCharakter.setPhysischeAttacke(0);
                            }
                            kampfWerteLog.add("Physische Attacke von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + physAtk + " verringert.\n");
                        }
                        break;
                    case "magischeAttacke":
                        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
                        if (faehigkeit.isIstFreundlich()) {
                            betroffenerCharakter
                                    .setMagischeAttacke(betroffenerCharakter.getMagischeAttacke() + ergebnisWert);
                            kampfWerteLog.add("Magische Attacke von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + ergebnisWert + " erhöht.\n");
                        }
                        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
                        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
                        else {
                            ergebnisWert -= betroffenerCharakter.getResistenz();
                            if (ergebnisWert < 1) {
                                ergebnisWert = 0;
                            }
                            int magAtk = Math.min(ergebnisWert, betroffenerCharakter.getMagischeAttacke());
                            betroffenerCharakter
                                    .setMagischeAttacke(betroffenerCharakter.getMagischeAttacke() - magAtk);

                            // Wenn seine MagischeAttacke UNTER 0 faellt wird sie auf 0 gesetzt
                            if (betroffenerCharakter.getMagischeAttacke() < 0) {
                                betroffenerCharakter.setMagischeAttacke(0);
                            }
                            kampfWerteLog.add("Magische Attacke von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + magAtk + " verringert\n");
                        }
                        break;
                    case "genauigkeit":
                        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
                        if (faehigkeit.isIstFreundlich()) {
                            betroffenerCharakter.setGenauigkeit(betroffenerCharakter.getGenauigkeit() + ergebnisWert);
                            kampfWerteLog.add("Genauigkeit von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + ergebnisWert + " erhöht.\n");
                        }
                        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
                        // Wenn die Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
                        else {
                            ergebnisWert -= betroffenerCharakter.getResistenz();
                            if (ergebnisWert < 1) {
                                ergebnisWert = 0;
                            }
                            int genauigkeit = Math.min(ergebnisWert, betroffenerCharakter.getGenauigkeit());
                            betroffenerCharakter.setGenauigkeit(betroffenerCharakter.getGenauigkeit() - genauigkeit);

                            // Wenn Genauigkeit UNTER 0 faellt wird sie auf 0 gesetzt
                            if (betroffenerCharakter.getGenauigkeit() < 0) {
                                betroffenerCharakter.setGenauigkeit(0);
                            }
                            kampfWerteLog.add("Genauigkeit von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + genauigkeit + " verringert.\n");
                        }
                        break;
                    case "verteidigung":
                        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
                        if (faehigkeit.isIstFreundlich()) {
                            betroffenerCharakter.setVerteidigung(betroffenerCharakter.getVerteidigung() + ergebnisWert);
                            kampfWerteLog.add("Verteidigung von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + ergebnisWert + " erhöht.\n");
                        }
                        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
                        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
                        else {
                            ergebnisWert -= betroffenerCharakter.getResistenz();
                            if (ergebnisWert < 1) {
                                ergebnisWert = 0;
                            }
                            int verteidigung = Math.min(ergebnisWert, betroffenerCharakter.getVerteidigung());
                            betroffenerCharakter.setVerteidigung(betroffenerCharakter.getVerteidigung() - verteidigung);

                            // Wenn Verteidigung UNTER 0 faellt wird sie auf 0 gesetzt
                            if (betroffenerCharakter.getVerteidigung() < 0) {
                                betroffenerCharakter.setVerteidigung(0);
                            }
                            kampfWerteLog.add("Verteidigung von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + verteidigung + " verringert.\n");
                        }
                        break;
                    case "magischeVerteidigung":
                        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
                        if (faehigkeit.isIstFreundlich()) {
                            betroffenerCharakter
                                    .setMagischeVerteidigung(betroffenerCharakter.getMagischeVerteidigung() + ergebnisWert);
                            kampfWerteLog.add("Magische Verteidigung von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + ergebnisWert + " erhöht.\n");
                        }
                        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
                        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
                        else {
                            ergebnisWert -= betroffenerCharakter.getResistenz();
                            if (ergebnisWert < 1) {
                                ergebnisWert = 0;
                            }
                            int magVer = Math.min(ergebnisWert, betroffenerCharakter.getMagischeVerteidigung());
                            betroffenerCharakter
                                    .setMagischeVerteidigung(betroffenerCharakter.getMagischeVerteidigung() - magVer);

                            // Wenn seine MagischeVerteidigung UNTER 0 faellt wird sie auf 0 gesetzt
                            if (betroffenerCharakter.getMagischeVerteidigung() < 0) {
                                betroffenerCharakter.setMagischeVerteidigung(0);
                            }
                            kampfWerteLog.add("Magische Verteidigung von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + magVer + " verringert.\n");
                        }
                        break;
                    case "resistenz":
                        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
                        if (faehigkeit.isIstFreundlich()) {
                            betroffenerCharakter.setResistenz(betroffenerCharakter.getResistenz() + ergebnisWert);
                            kampfWerteLog.add("Resistenz von " + betroffenerCharakter.getName() + "\nwurde um " + ergebnisWert
                                    + " erhöht.\n");
                        }
                        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
                        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
                        else {
                            ergebnisWert -= betroffenerCharakter.getResistenz();
                            if (ergebnisWert < 1) {
                                ergebnisWert = 0;
                            }
                            int resistenz = Math.min(ergebnisWert, betroffenerCharakter.getResistenz());
                            betroffenerCharakter.setResistenz(betroffenerCharakter.getResistenz() - resistenz);

                            // Wenn seine Resistenz UNTER 0 faellt wird sie auf 0 gesetzt
                            if (betroffenerCharakter.getResistenz() < 0) {
                                betroffenerCharakter.setResistenz(0);
                            }
                            kampfWerteLog.add("Resistenz von " + betroffenerCharakter.getName() + "\nwurde um " + resistenz
                                    + " verringert.\n");
                        }
                        break;
                    case "beweglichkeit":
                        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
                        if (faehigkeit.isIstFreundlich()) {
                            betroffenerCharakter.setBeweglichkeit(betroffenerCharakter.getBeweglichkeit() + ergebnisWert);
                            kampfWerteLog.add("Beweglichkeit von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + ergebnisWert + " erhöht.\n");
                        }
                        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
                        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
                        else {
                            ergebnisWert -= betroffenerCharakter.getResistenz();
                            if (ergebnisWert < 1) {
                                ergebnisWert = 0;
                            }
                            int beweglichkeit = Math.min(ergebnisWert, betroffenerCharakter.getBeweglichkeit());
                            betroffenerCharakter.setBeweglichkeit(betroffenerCharakter.getBeweglichkeit() - beweglichkeit);

                            // Wenn seine Beweglichkeit UNTER 0 faellt wird sie auf 0 gesetzt
                            if (betroffenerCharakter.getBeweglichkeit() < 0) {
                                betroffenerCharakter.setBeweglichkeit(0);
                            }
                            kampfWerteLog.add("Beweglichkeit von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + beweglichkeit + " verringert.\n");
                        }
                        break;
                    case "gesundheitsRegeneration":
                        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
                        if (faehigkeit.isIstFreundlich()) {
                            betroffenerCharakter.setGesundheitsRegeneration(
                                    betroffenerCharakter.getGesundheitsRegeneration() + ergebnisWert);
                            kampfWerteLog.add("Gesundheitsregeneration von " + betroffenerCharakter.getName()
                                    + "\nwurde um " + ergebnisWert + " erhöht.\n");
                        }
                        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
                        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
                        else {
                            ergebnisWert -= betroffenerCharakter.getResistenz();
                            if (ergebnisWert < 1) {
                                ergebnisWert = 0;
                            }
                            int gesReg = Math.min(ergebnisWert, betroffenerCharakter.getGesundheitsRegeneration());
                            betroffenerCharakter.setGesundheitsRegeneration(
                                    betroffenerCharakter.getGesundheitsRegeneration() - gesReg);

                            // Wenn seine GesundheitsRegeneration UNTER 0 faellt wird sie auf 0 gesetzt
                            if (betroffenerCharakter.getGesundheitsRegeneration() < 0) {
                                betroffenerCharakter.setGesundheitsRegeneration(0);
                            }
                            kampfWerteLog.add("Gesundheitsregeneration von " + betroffenerCharakter.getName()
                                    + "\nwurde um " + gesReg + " verringert.\n");
                        }
                        break;
                    case "manaRegeneration":
                        // gleiches Team -> Buff -> Resistenz des Zieles spielt keine Rolle
                        if (faehigkeit.isIstFreundlich()) {
                            betroffenerCharakter
                                    .setManaRegeneration(betroffenerCharakter.getManaRegeneration() + ergebnisWert);
                            kampfWerteLog.add("Manaregeneration von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + ergebnisWert + " erhöht.\n");
                        }
                        // feindliches Team -> DeBuff -> Resistenz des Zieles muss beachtet werden
                        // Wenn Resistenz des Zieles zu gross ist wird kein DeBuff verursacht
                        else {
                            ergebnisWert -= betroffenerCharakter.getResistenz();
                            if (ergebnisWert < 1) {
                                ergebnisWert = 0;
                            }
                            int manaReg = Math.min(ergebnisWert, betroffenerCharakter.getManaRegeneration());
                            betroffenerCharakter
                                    .setManaRegeneration(betroffenerCharakter.getManaRegeneration() - manaReg);

                            // Wenn seine ManaRegeneration UNTER 0 faellt wird sie auf 0 gesetzt
                            if (betroffenerCharakter.getManaRegeneration() < 0) {
                                betroffenerCharakter.setManaRegeneration(0);
                            }
                            kampfWerteLog.add("Manaregeneration von " + betroffenerCharakter.getName() + "\nwurde um "
                                    + manaReg + " verringert.\n");
                        }
                        break;
                    case "abwehr":
                        ergebnisWert -= betroffenerCharakter.getResistenz();
                        if (ergebnisWert < 1) {
                            ergebnisWert = 1;
                        }
                        int pDef = Math.min(ergebnisWert, betroffenerCharakter.getVerteidigung());
                        int mDef = Math.min(ergebnisWert, betroffenerCharakter.getMagischeVerteidigung());
                        betroffenerCharakter.setVerteidigung(betroffenerCharakter.getVerteidigung() - pDef);
                        betroffenerCharakter.setMagischeVerteidigung(betroffenerCharakter.getMagischeVerteidigung() - mDef);

                        // Wenn seine ManaRegeneration UNTER 0 faellt wird sie auf 0 gesetzt
                        if (betroffenerCharakter.getVerteidigung() < 0) {
                            betroffenerCharakter.setVerteidigung(0);
                        }
                        if (betroffenerCharakter.getMagischeVerteidigung() < 0) {
                            betroffenerCharakter.setMagischeVerteidigung(0);
                        }
                        kampfWerteLog.add(
                                "Abwehr von " + betroffenerCharakter.getName() + "\nwurde verringert." + "\n Verteidigung -"
                                        + pDef + " , Mag. Verteidigung -" + mDef + "\n");
                        break;
                    case "berserkerSpezial":
                        // Berserker Spezialfähigkeit
                        ergebnisWert -= betroffenerCharakterAbwehr;
                        if (ergebnisWert < 1) {
                            ergebnisWert = 1;
                        }
                        int hp = Math.min(ergebnisWert, betroffenerCharakter.getGesundheitsPunkte());
                        betroffenerCharakter
                                .setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() - ergebnisWert);

                        // Wenn seine HP UNTER 0 fallen werden sie auf 0 gesetzt
                        if (betroffenerCharakter.getGesundheitsPunkte() < 0) {
                            betroffenerCharakter.setGesundheitsPunkte(0);
                        }

                        aktuellerCharakter.setGesundheitsPunkte(aktuellerCharakter.getGesundheitsPunkte() - 10);
                        if (aktuellerCharakter.getGesundheitsPunkte() < 0) {
                            aktuellerCharakter.setGesundheitsPunkte(0);
                        }
                        kampfWerteLog.add(aktuellerCharakter.getName() + " hat die Berserker-Fähigkeit eingesetzt!\n"
                                + aktuellerCharakter.getName() + " hat sich einmalig selbst 10 HP Schaden zugefügt.\n"
                                + betroffenerCharakter.getName() + " hat " + hp + " Schaden erlitten.");
                        break;
                    case "feuermagierSpezial":
                        // Feuermagier Spezialfähigkeit
                        hp = 0;
                        ergebnisWert -= betroffenerCharakterAbwehr;
                        if (ergebnisWert < 1) {
                            ergebnisWert = 0;
                        }
                        betroffenerCharakter
                                .setGesundheitsPunkte(betroffenerCharakter.getGesundheitsPunkte() - ergebnisWert);

                        // Wenn seine HP UNTER 0 fallen werden sie auf 0 gesetzt
                        if (betroffenerCharakter.getGesundheitsPunkte() < 0) {
                            betroffenerCharakter.setGesundheitsPunkte(0);
                        }
                        if (betroffenerCharakter.getGesundheitsPunkte() < 0) {
                            betroffenerCharakter.setGesundheitsPunkte(0);
                            kampfWerteLog.add(aktuellerCharakter.getName() + " hat die Feuermagier-Fähigkeit eingesetzt!\n"
                                    + aktuellerCharakter.getName() + " SO EIN FEUERBALL, JUNGE!\n"
                                    + betroffenerCharakter.getName() + " hat" + hp + " Schaden erlitten.");
                        }
                        break;
                    case "eismagierSpezial":
                        // Eismagier Spezialfähigkeit
                        aktuelleZugreihenfolge.remove(betroffenerCharakter);
                        aktuelleZugreihenfolge.add(aktuelleZugreihenfolge.size() - 1, betroffenerCharakter);
                        kampfWerteLog.add(aktuellerCharakter.getName() + " hat die Eismagier-Fähigkeit eingesetzt!\n"
                                + "Oh Mai Oh Mai!\n" + betroffenerCharakter.getName()
                                + " ist diese Runde als letztes dran.");
                        break;
                    case "rabaukeSpezial":
                        // Rabauke Spezialfähigkeit
                        aktuellerCharakter.setVerteidigung(aktuellerCharakter.getVerteidigung() + 999999);
                        aktuellerCharakter.setMagischeVerteidigung(aktuellerCharakter.getMagischeVerteidigung() + 999999);
                        kampfWerteLog.add(aktuellerCharakter.getName() + " hat die Rabauken-Fähigkeit eingesetzt!\n"
                                + "Du bischt prutal...!\nAbwehr von " + betroffenerCharakter.getName()
                                + "wurde enorm erhöht.");
                        break;
                    case "paladinSpezial":
                        // Paladin Spezialfähigkeit
                        aktuellerCharakter.setMaxGesundheitsPunkte(aktuellerCharakter.getMaxGesundheitsPunkte() + 120);
                        aktuellerCharakter.setGesundheitsPunkte(aktuellerCharakter.getMaxGesundheitsPunkte());
                        kampfWerteLog.add(aktuellerCharakter.getName() + " hat die Paladin-Fähigkeit eingesetzt!\n"
                                + "Ja bist du Deppert?!\n" + "100% Heilung und Maximale Gesundheit\nwurde stark erhöht.");
                        break;
                    case "priesterSpezial":
                        // Priester Spezialfähigkeit
                        if (betroffenerCharakter instanceof Feind) {
                            for (Feind feind : feindeDieNochLeben) {
                                feind.setMaxGesundheitsPunkte(feind.getMaxGesundheitsPunkte() + 20);
                                feind.setMaxGesundheitsPunkte(feind.getMaxGesundheitsPunkte() + 20);
                                feind.setPhysischeAttacke(feind.getPhysischeAttacke() + 80);
                                feind.setMagischeAttacke(feind.getMagischeAttacke() + 80);
                                feind.setVerteidigung(feind.getVerteidigung() + 80);
                                feind.setMagischeVerteidigung(feind.getMagischeVerteidigung() + 80);
                                feind.setGesundheitsRegeneration(feind.getGesundheitsRegeneration() + 80);
                                feind.setManaRegeneration(feind.getManaRegeneration() + 80);
                                feind.setGenauigkeit(feind.getGenauigkeit() + 80);
                                feind.setBeweglichkeit(feind.getBeweglichkeit() + 80);
                            }
                        }
                        else if (betroffenerCharakter instanceof SpielerCharakter) {
                            for (SpielerCharakter spielerCharakter : freundeDieNochLeben) {
                                spielerCharakter.setMaxGesundheitsPunkte(spielerCharakter.getMaxGesundheitsPunkte() + 20);
                                spielerCharakter.setGesundheitsPunkte(spielerCharakter.getGesundheitsPunkte() + 20);
                                spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke() + 30);
                                spielerCharakter.setPhysischeAttacke(spielerCharakter.getPhysischeAttacke() + 30);
                                spielerCharakter.setMagischeAttacke(spielerCharakter.getMagischeAttacke() + 30);
                                spielerCharakter.setVerteidigung(spielerCharakter.getVerteidigung() + 30);
                                spielerCharakter.setMagischeVerteidigung(spielerCharakter.getMagischeVerteidigung() + 30);
                                spielerCharakter
                                        .setGesundheitsRegeneration(spielerCharakter.getGesundheitsRegeneration() + 30);
                                spielerCharakter.setManaRegeneration(spielerCharakter.getManaRegeneration() + 30);
                                spielerCharakter.setGenauigkeit(spielerCharakter.getGenauigkeit() + 30);
                                spielerCharakter.setBeweglichkeit(spielerCharakter.getBeweglichkeit() + 30);
                            }
                            kampfWerteLog.add(aktuellerCharakter.getName() + " hat die Paladin-Fähigkeit eingesetzt!\n"
                                    + "Da brat mir doch einer nen Storch...\n"
                                    + "Statuswerte des Teams wurden stark erhöht.");
                        }
                        break;
                    case "sanmausSpezial":
                        // Sanmaus Spezialfähigkeit
                        betroffenerCharakter.setGesundheitsPunkte(
                                (int) Math.floor((betroffenerCharakter.getMaxGesundheitsPunkte() * 0.7)));
                        betroffenerCharakter
                                .setManaPunkte((int) Math.floor((betroffenerCharakter.getMaxManaPunkte() * 0.5)));
                        kampfWerteLog.add(aktuellerCharakter.getName() + " hat die Sanmaus-Fähigkeit eingesetzt!\n"
                                + "Rettung in letzter Sekunde!\n" + "Gesundheitspunkte von "
                                + betroffenerCharakter.getName() + "\nwurden auf 70% gesetzt.\n"
                                + "Manapunkte wurden auf 70% gesetzt.\n");
                        break;
                }
                if (betroffenerCharakter.getManaPunkte() < 0) {
                    betroffenerCharakter.setManaPunkte(0);
                }
                if (betroffenerCharakter.getGesundheitsPunkte() < 0) {
                    betroffenerCharakter.setGesundheitsPunkte(0);
                }
                zielWahl.remove(0);
            }
        }
        // Die Fähigkeit hat nicht getroffen. Mana wurde trotzdem abgezogen und der Zug
        // des Charakters ist vorbei
        else {
            kampfWerteLog.add(faehigkeit.getName() + " ist daneben gegangen!\n");
            if (Game.debugModus) {
                kampfWerteLog.add("[DEBUG] Trefferchance liegt bei "
                        + (int) ((0.65 + 0.02 * aktuellerCharakter.getGenauigkeit()) * 100) + "%\n");
            }
        }
        aktualisiereZugreihenfolge();
        aktualisiereIstKampfVorbei();
    }

    /**
     * Lässt Gegner Fähigkeit und Ziele wählen, falls er angreift und nicht blockt
     *
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    private void gegnerLogikFaehigkeitUndZielGruppe() {
        String feindKlasse = aktuellerCharakter.getKlasse().getBezeichnung();
        List<Faehigkeit> moeglicheFaehigkeiten = new ArrayList<>();
		this.zielGruppe.clear();
		this.kampfWerteLog.clear();
        List<Charakter> zielGruppe = this.zielGruppe;
        Faehigkeit faehigkeit = null;

        // Befüllt Feind-Ziel-ArrayList (Feind-Team)
        List<Feind> moeglicheFeinde = new ArrayList<>(feindeDieNochLeben);
        // Befüllt SpielerCharakter-Ziel-ArrayList (SpielerCharakter-Team)
        List<SpielerCharakter> moeglicheSpielerCharaktere = new ArrayList<>(freundeDieNochLeben);

        // Nur Fähigkeiten sind möglich für die die Manapunkte auch reichen
        for (Faehigkeit eineFaehigkeit : getAktiveFaehigkeiten(aktuellerCharakter)) {
            if (eineFaehigkeit.getLevel() > 0 && eineFaehigkeit.getManaKosten() < aktuellerCharakter.getManaPunkte()) {
                moeglicheFaehigkeiten.add(eineFaehigkeit);
            }
        }

        // Gegnerlogik ist klassenabhängig!!!
        switch (feindKlasse) {
            // Healer versuchen immer zuerst ihre Teammitglieder (inklusive sich selbst) zu
            // heilen!
            case Klasse.HLR:
                zielGruppe.clear();
                // Zielgruppe ist immer zuerst das eigene Team, außer es wird im späteren
                // Logikverlauf anders entschieden
                zielGruppe.addAll(feindeDieNochLeben);

                // Entfernt alle Feinde aus dem eigenen Team als mögliche Ziele die die
                // maximale Gesundheit haben
                moeglicheFeinde.removeIf(feind -> feind.getGesundheitsPunkte() == feind.getMaxGesundheitsPunkte());

                // Entfernt alle Fähigkeiten die nicht aufs eigene Team genutzt werden können
                // und entfernt alle Fähigkeiten die mehr Ziele heilen können als es
                // Teammitglieder gibt die die Heilung benötigen.
                moeglicheFaehigkeiten.removeIf(eineFaehigkeit -> eineFaehigkeit.getZielAnzahl() > moeglicheFeinde.size() || !eineFaehigkeit.isIstFreundlich());

                // Wenn nach den ganzen Filter keine Fähigkeiten mehr übrig sind bedeutet das,
                // dass alle Feinde 100% ihrer Lebenspunkte haben. Erst jetzt will der Healer in
                // den Angriffsmodus gehen.
                if (moeglicheFaehigkeiten.isEmpty()) {
					// Nur Fähigkeiten sind möglich für die die Manapunkte auch reichen
					for (Faehigkeit eineFaehigkeit : getAktiveFaehigkeiten(aktuellerCharakter)) {
						if (eineFaehigkeit.getLevel() > 0 && eineFaehigkeit.getManaKosten() < aktuellerCharakter.getManaPunkte()) {
							moeglicheFaehigkeiten.add(eineFaehigkeit);
						}
					}
                    // Alle Fähigkeiten die aufs eigene Team angewendet werden können fliegen raus
                    // Alle Fähigkeiten die auf mehr Charaktere angewendet werden können als es
                    // Ziele gibt fliegen raus
                    moeglicheFaehigkeiten.removeIf(eineFaehigkeit -> eineFaehigkeit.getZielAnzahl() > zielGruppe.size() || eineFaehigkeit.isIstFreundlich());

					// Ziel-Gruppe ändert sich von eigener (Feind) zur SpielerCharakter-Gruppe
                    zielGruppe.clear();
                    if (!moeglicheFaehigkeiten.isEmpty()) {
                        faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
                        int nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();
                        while (nochZuWaehlendeZiele > 0) {
                            SpielerCharakter aktuellesZielSpielerCharakter = moeglicheSpielerCharaktere.get(0);
                            for (SpielerCharakter spielerCharakter : moeglicheSpielerCharaktere) {
                                if (spielerCharakter.getGesundheitsPunkte() < aktuellesZielSpielerCharakter
                                        .getGesundheitsPunkte()) {
                                    aktuellesZielSpielerCharakter = spielerCharakter;
                                }
                            }
                            moeglicheSpielerCharaktere.remove(aktuellesZielSpielerCharakter);
                            zielGruppe.add(aktuellesZielSpielerCharakter);
                            nochZuWaehlendeZiele--;
                            if (moeglicheSpielerCharaktere.isEmpty()) {
                                nochZuWaehlendeZiele = 0;
                            }
                        }
                    }
                    else {
                        faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
                        if (faehigkeit.isIstFreundlich()) {
                            if (aktuellerCharakter instanceof Feind) {
                                zielGruppe.add(feindeDieNochLeben.get(0));
                            }
                            else {
                                zielGruppe.add(freundeDieNochLeben.get(0));
                            }
                        }
                        else {
                            if (aktuellerCharakter instanceof Feind) {
                                zielGruppe.add(freundeDieNochLeben.get(0));
                            }
                            else {
                                zielGruppe.add(feindeDieNochLeben.get(0));
                            }
                        }
                    }
                }
                // Es gibt Feind-Charaktere (eigenes Team) die geheilt werden können.
                // Das Fähigkeitsset besteht aus den zu Anfang bestimmten Fähigkeiten
                else {
                    // Fähigkeit wird aus dem möglichen Pool zufällig gewählt
                    faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
                    int nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();

                    // Ziele werden auf Grundlage ihrer Lebenspunkte gewählt
                    // Beim heilen werden Feinde mit niedriger Gesundheit priorisiert
                    // Beim Schaden verursachen werden SpielerCharaktere mit niedriger Gesundheit
                    // priorisiert
                    while (nochZuWaehlendeZiele > 0) {
                        Feind aktuellesZielFeind = moeglicheFeinde.get(0);
                        for (Feind feind : moeglicheFeinde) {
                            if (feind.getGesundheitsPunkte() < aktuellesZielFeind.getGesundheitsPunkte()) {
                                aktuellesZielFeind = feind;
                            }
                        }
                        moeglicheFeinde.remove(aktuellesZielFeind);
                        zielGruppe.add(aktuellesZielFeind);
                        nochZuWaehlendeZiele--;
                        if (moeglicheFeinde.isEmpty()) {
                            nochZuWaehlendeZiele = 0;
                        }
                    }
                }
                break;

            // Tanks heilen sich entweder selbst, oder greifen die SpielerCharaktere-Gruppe
            // an, abhaengig von ihren eigenen Lebenspunkten
            case Klasse.TNK:
                zielGruppe.clear();
                // Wenn der Tank weniger als 50% seiner maximalen Lebenspunkte hat, will er sich
                // selbst heilen
                // In allen anderen Fällen will er die SpielerCharaktere-Gruppe angreifen
                boolean willIchMichHeilen = aktuellerCharakter.getGesundheitsPunkte() * 2 < aktuellerCharakter.getMaxGesundheitsPunkte();
                if (willIchMichHeilen) {

                    // Wenn sich der Tank heilen will ist die Zielgruppe der Fähigkeit die eigene
                    // Gruppe (Feind-Team)
                    zielGruppe.addAll(feindeDieNochLeben);

                    // Alle Fähigkeiten die nicht aufs eigene Team angewendet werden können fliegen raus.
                    // Alle Fähigkeiten die auf mehr als einen Charakter angewendet werden können fliegen raus.
                    for (Faehigkeit eineFaehigkeit : new ArrayList<>(moeglicheFaehigkeiten)) {
                        if (!eineFaehigkeit.isIstFreundlich()) {
                            moeglicheFaehigkeiten.remove(eineFaehigkeit);
                        }
                        if (eineFaehigkeit.getZielAnzahl() != 1) {
                            moeglicheFaehigkeiten.remove(eineFaehigkeit);
                        }
                    }
                    if (!moeglicheFaehigkeiten.isEmpty()) {
                        // Faehigkeit wird zufaellig aus dem moeglichen Pool bestimmt und auf sich
                        // selbst angewendet
                        faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
                        // zielWahl.add(zielGruppe.indexOf(aktuellerCharakter));
                    }
                    // Tank will sich zwar selber heilen, aber kann aus welchen Gründen auch immer
                    // keine Fähigkeit auf sich wirken. Also muss er wohl in den Angriff wechseln.
                    else {
                        willIchMichHeilen = false;
                    }
                }

                // Der Tank hat 50% oder mehr seiner maximalen Lebenspunkte oder kann keine
                // seiner Selbstheilungen benutzen. Daher will er nun Schaden an den
                // SpielerCharakteren verursachen
                if (!willIchMichHeilen) {
                    // Zielgruppe ist die SpielCharaktere-Gruppe
                    for (int counter = 0, len = freundeDieNochLeben.size(); counter < len; counter++) {
                        zielGruppe.add(moeglicheSpielerCharaktere.get(counter));
                    }

                    // Fähigkeiten die aufs eigene Team angewendet werden fliegen raus
                    moeglicheFaehigkeiten.removeIf(Faehigkeit::isIstFreundlich);

                    // Fähigkeiten die mehr Ziele haben als es noch auswählbare SpielerCharaktere
                    // gibt fliegen raus
                    moeglicheFaehigkeiten.removeIf(eineFaehigkeit -> eineFaehigkeit.getZielAnzahl() > moeglicheSpielerCharaktere.size());

                    // Fähigkeit wird zufällig aus dem möglichen Pool bestimmt
                    if (moeglicheFaehigkeiten.size() == 0) {
                        moeglicheFaehigkeiten.add(aktuellerCharakter.getFaehigkeiten().get(0));
                    }
                    faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
                    int nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();
                    zielGruppe.clear();
                    // Ziele werden bestimmt, wobei niedrige Lebenspunkte priorisiert werden
                    while (nochZuWaehlendeZiele > 0) {
                        SpielerCharakter aktuellesZielSpielerCharakter = moeglicheSpielerCharaktere.get(0);
                        for (SpielerCharakter spielerCharakter : moeglicheSpielerCharaktere) {
                            if ((spielerCharakter.getGesundheitsPunkte() > 0) && (spielerCharakter
                                    .getGesundheitsPunkte() < aktuellesZielSpielerCharakter.getGesundheitsPunkte())) {
                                aktuellesZielSpielerCharakter = spielerCharakter;
                            }
                        }
                        moeglicheSpielerCharaktere.remove(aktuellesZielSpielerCharakter);
                        zielGruppe.add(aktuellesZielSpielerCharakter);
                        nochZuWaehlendeZiele--;
                        if (moeglicheSpielerCharaktere.isEmpty()) {
                            nochZuWaehlendeZiele = 0;
                        }
                    }
                }
                if (faehigkeit == null) {
                    faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
                    kampfView.setFaehigkeit(faehigkeit);
                }
                break;

            // 'Physische DD' und 'Magische DD' haben beide die gleiche offensive Logik,
            // welche der Logik entspricht, die der Tank verfolgt, solange er 50% oder mehr
            // seiner maximalen Lebenspunkte hat.
            case Klasse.PDD:
            case Klasse.MDD:
                for (int counter = 0, len = freundeDieNochLeben.size(); counter < len; counter++) {
                    zielGruppe.add(moeglicheSpielerCharaktere.get(counter));
                }
                moeglicheFaehigkeiten.removeIf(Faehigkeit::isIstFreundlich);
                moeglicheFaehigkeiten.removeIf(eineFaehigkeit -> eineFaehigkeit.getZielAnzahl() > moeglicheSpielerCharaktere.size());
                zielGruppe.clear();
                faehigkeit = moeglicheFaehigkeiten.get(random.nextInt(moeglicheFaehigkeiten.size()));
                int nochZuWaehlendeZiele = faehigkeit.getZielAnzahl();
                while (nochZuWaehlendeZiele > 0) {
                    SpielerCharakter aktuellesZielSpielerCharakter = moeglicheSpielerCharaktere.get(0);
                    for (SpielerCharakter spielerCharakter : moeglicheSpielerCharaktere) {
                        if ((spielerCharakter.getGesundheitsPunkte() > 0) && (spielerCharakter
                                .getGesundheitsPunkte() < aktuellesZielSpielerCharakter.getGesundheitsPunkte())) {
                            aktuellesZielSpielerCharakter = spielerCharakter;
                        }
                    }
                    moeglicheSpielerCharaktere.remove(aktuellesZielSpielerCharakter);
                    zielGruppe.add(aktuellesZielSpielerCharakter);
                    nochZuWaehlendeZiele--;
                    if (moeglicheSpielerCharaktere.isEmpty()) {
                        nochZuWaehlendeZiele = 0;
                    }
                }
                break;
        }

        if (faehigkeit == null || faehigkeit.getManaKosten() > aktuellerCharakter.getManaPunkte()) {
            faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
        }

        if (faehigkeit.getZielAnzahl() > zielGruppe.size()) {
            faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
        }

        if (faehigkeit == null) {
            zielGruppe.clear();
            faehigkeit = aktuellerCharakter.getFaehigkeiten().get(0);
            if (faehigkeit.isIstFreundlich()) {
                if (aktuellerCharakter instanceof Feind) {
                    zielGruppe.add(feindeDieNochLeben.get(0));
                }
                else {
                    zielGruppe.add(freundeDieNochLeben.get(0));
                }
            }
            else {
                if (aktuellerCharakter instanceof Feind) {
                    zielGruppe.add(freundeDieNochLeben.get(0));
                }
                else {
                    zielGruppe.add(feindeDieNochLeben.get(0));
                }
            }
        }
        gegnerFaehigkeit = faehigkeit;
        kampfView.setFaehigkeit(gegnerFaehigkeit);
    }

    /**
     * Nach Initialisierung des Kampfes geht es hier los. Alle noch benötigten
     * ArrayLists und Werte werden befüllt, bevor an den KampfView-Controller
     * übergeben wird
     *
     * @author Melvin
     * @since 18.11.2023
     */
    private void kampfBeginn(List<Charakter> initialeZugreihenfolge) {
        partyAnordnung.add(partyController.getParty().getHauptCharakter());
        for (SpielerCharakter nebencharakter : partyController.getParty().getNebenCharakter()) {
            if (nebencharakter != null) {
                partyAnordnung.add(nebencharakter);
            }
        }

        // freundeDieNochLeben, feindeDieNochLeben, etc. wird alles befuellt
        if (partyController.getParty().getHauptCharakter().getGesundheitsPunkte() > 0) {
            freundeDieNochLeben.add(partyController.getParty().getHauptCharakter());
        }
        else {
            freundeDieGestorbenSind.add(partyController.getParty().getHauptCharakter());
        }
        int index = 0;
        for (SpielerCharakter nebenCharakter : partyController.getParty().getNebenCharakter()) {
            if (nebenCharakter != null) {
                if (nebenCharakter.getGesundheitsPunkte() > 0) {
                    freundeDieNochLeben.add(partyController.getParty().getNebenCarakter(index));
                }
                else {
                    freundeDieGestorbenSind.add(partyController.getParty().getNebenCarakter(index));
                }
            }
            index++;
        }
        for (Charakter value : initialeZugreihenfolge) {
            if (value instanceof Feind) {
                feindeDieNochLeben.add((Feind) value);
                gegnerAnordnung.add((Feind) value);
            }
        }
        freundeDieNochActionHaben.addAll(freundeDieNochLeben);
        feindeDieNochActionHaben.addAll(feindeDieNochLeben);
        aktuelleZugreihenfolge.addAll(initialeZugreihenfolge);
        Collections.reverse(aktuelleZugreihenfolge);
        aktuellerCharakter = aktuelleZugreihenfolge.get(0);

        kampfView = new KampfView(this);
        kampfView.updateKampfBildschirm();
        viewController.anmelden(this.kampfView, null, AnsichtsTyp.NICHT_CACHE);
    }
}
