package de.bundeswehr.auf.final_fantasy.menu.schmiede;

import de.bundeswehr.auf.final_fantasy.charakter.controller.CharakterController;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.AusruestungsGegenstand;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.Ruestung;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.Waffe;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Materialien;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.ZufallsZahlenGenerator;
import de.bundeswehr.auf.final_fantasy.menu.overlay.AnsichtsTyp;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.menu.schmiede.view.SchmiedeView;
import de.bundeswehr.auf.final_fantasy.menu.schmiede.view.VerbessernView;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.control.Button;

import java.util.*;

public class SchmiedeController {
    
    private final PartyController partyController;
    private final SchmiedeView schmiedeView;
    private ViewController viewController;
    private final List<Map<Material, Integer>> aufruestungskosten = new ArrayList<>();
    private List<Button> schmiedeMenuButtons;
    private final BooleanProperty verbessernGeklickt = new SimpleBooleanProperty();
    private final VerbessernView verbessernView;

    /**
     * Der SchmiedeController verwaltet die Interaktionen zwischen der SchmiedeView, der VerbessernView,
     * dem PartyController und dem ViewController in Bezug auf die Schmiede-Funktionen im Spiel.
     *
     * @param partyController Die Instanz des PartyControllers, die Informationen über die Spielcharaktere und Materialien enthält.
     * @param viewController  Die Instanz des ViewControllers, der für die Anzeige der Benutzeroberfläche verantwortlich ist.
     *                        <p>
     *                        Die Methode initialisiert die notwendigen Ansichten (SchmiedeView und VerbessernView),
     *                        erstellt die zugehörigen Menü-Buttons und definiert deren Aktionen.
     *                        <p>
     *                        Bei der Initialisierung wird auch eine BooleanProperty 'verbessernGeklickt' erstellt,
     *                        um den Zustand des Verbessern-Buttons zu überwachen und den entsprechenden Button zu deaktivieren,
     *                        nachdem er einmal geklickt wurde.
     *                        <p>
     *                        Der Verbessern-Button aktualisiert die VerbessernView und meldet sie dann mit einem Overlay an den ViewController an,
     *                        während der Zurück-Button den ViewController informiert, zur vorherigen Ansicht zu wechseln.
     */
    public SchmiedeController(PartyController partyController, ViewController viewController) {
        this.viewController = viewController;
        this.partyController = partyController;
        this.schmiedeView = new SchmiedeView(partyController, this);
        this.verbessernView = new VerbessernView(partyController, this, viewController);

        Button buttonVerbessern = new Button("Verbessern");
        buttonVerbessern.setOnAction(event -> {
            this.verbessernView.verbessernWaffenAnzeigeAktualisieren();
            this.verbessernView.verbessernRuestungAnzeigeAktualisieren();
            this.verbessernView.verbessernAccessoireAnzeigeAktualisieren();
            viewController.anmelden(verbessernView, schmiedeMenuButtons, AnsichtsTyp.MIT_OVERLAY);
            verbessernGeklickt.setValue(true);
        });
        buttonVerbessern.disableProperty().bind(Bindings.equal(new SimpleBooleanProperty(true), verbessernGeklickt));
        Button buttonGameHub = new Button("Zurück");
        buttonGameHub.setOnAction(event -> {
            viewController.aktuelleNachHinten();
            verbessernGeklickt.setValue(false);
        });
        this.schmiedeMenuButtons = new ArrayList<>(Arrays.asList(buttonVerbessern, buttonGameHub));
        this.viewController = viewController;

        materialKostenErzeugen();
    }

    /**
     * Wertet einen Ausruestungsgegenstand auf
     * Laesst betroffenen Charakter den Ausruestungsgegenstand ablegen und anlegen
     * Setzt Ausruestungsgegenstand-Level +1 und passt Attribute an
     *
     * @param ausruestungsgegenstand Der Ausrüstungsgegenstand, der aufgewertet werden soll.
     * @return boolean
     * @author OF Stetter
     * @since 05.12.2023
     */
    public boolean aufwerten(AusruestungsGegenstand ausruestungsgegenstand) {
        boolean ergebnis = false;
        int levelAnforderung = ausruestungsgegenstand.getLevelAnforderung();
        boolean genugGold = ((ausruestungsgegenstand.getLevelAnforderung() + 1) * 100) <= partyController.getPartyGold();
        boolean genugMaterial = true;
        for (Map.Entry<Material, Integer> entry : aufruestungskosten.get(levelAnforderung - 1).entrySet()) {
            boolean mengeVorhanden = partyController.getParty().getMaterialien().get(entry.getKey()).get() >= entry.getValue();
            if (!mengeVorhanden) {
                genugMaterial = false;
            }
        }

        if (genugGold && genugMaterial) {
            partyController.goldAbziehen((ausruestungsgegenstand.getLevelAnforderung() + 1) * 100);

            for (Map.Entry<Material, Integer> entry : aufruestungskosten.get(levelAnforderung - 1).entrySet()) {
                partyController.getParty().getMaterialien().get(entry.getKey()).set(partyController.getParty().getMaterialien().get(entry.getKey()).get() - entry.getValue());
            }
            List<SpielerCharakter> tmp = new ArrayList<>();
            tmp.add(partyController.getParty().getHauptCharakter());
            tmp.addAll(Arrays.asList(partyController.getParty().getNebenCharaktere()));
            boolean ausruestungAusgezogen = false;
            SpielerCharakter spielerCharakter = null;
            if ((ausruestungsgegenstand.getLevelAnforderung() + 1) <= partyController.getPartyLevel()) {
                for (SpielerCharakter charakter : tmp) {
                    if (charakter != null && CharakterController.ausruestungAnzeigen(charakter).contains(ausruestungsgegenstand)) {
                        CharakterController.ausruestungAusziehen(charakter, ausruestungsgegenstand, partyController.getParty().getAusruestungsgegenstandInventar());
                        ausruestungAusgezogen = true;
                        spielerCharakter = charakter;
                        break;
                    }
                }
                if (!ausruestungAusgezogen || spielerCharakter.getLevel() >= ausruestungsgegenstand.getLevelAnforderung() + 1) {
                    if (ausruestungsgegenstand instanceof Waffe) {
                        ((Waffe) ausruestungsgegenstand).setAttacke(((Waffe) ausruestungsgegenstand).getAttacke() + 1);
                        ((Waffe) ausruestungsgegenstand).setMagischeAttacke(((Waffe) ausruestungsgegenstand).getMagischeAttacke() + 1);
                    }
                    else if (ausruestungsgegenstand instanceof Ruestung) {
                        ((Ruestung) ausruestungsgegenstand).setVerteidigung(((Ruestung) ausruestungsgegenstand).getVerteidigung() + 1);
                        ((Ruestung) ausruestungsgegenstand).setMagischeVerteidigung(((Ruestung) ausruestungsgegenstand).getMagischeVerteidigung() + 1);
                    }
                    else if (ausruestungsgegenstand instanceof Accessoire) {
                        ((Accessoire) ausruestungsgegenstand).setMaxGesundheitsPunkte(((Accessoire) ausruestungsgegenstand).getMaxGesundheitsPunkte() + 1);
                        ((Accessoire) ausruestungsgegenstand).setMaxManaPunkte(((Accessoire) ausruestungsgegenstand).getMaxManaPunkte() + 1);
                    }
                    ausruestungsgegenstand.setLevelAnforderung((ausruestungsgegenstand).getLevelAnforderung() + 1);
                    if (ausruestungAusgezogen) {
                        CharakterController.ausruestungAnlegen(spielerCharakter, ausruestungsgegenstand, partyController.getParty().getAusruestungsgegenstandInventar());
                    }
                    ergebnis = true;
                }
            }
        }
        return ergebnis;
    }

    public List<Map<Material, Integer>> getAufruestungskosten() {
        return aufruestungskosten;
    }

    /**
     * Liefert die Overlaybuttons disabled zurück
     *
     * @return ArrayList
     * @author OF Stetter
     * @since 05.12.2023
     */
    public List<Button> getSchmiedeMenuButtonsDisabled() {
        List<Button> disabled = new ArrayList<>();
        Button verbessernDisabled = new Button("Verbessern");
        verbessernDisabled.setDisable(true);
        Button zurueck = new Button("Zurück");
        zurueck.setDisable(true);
        disabled.add(verbessernDisabled);
        disabled.add(zurueck);
        return disabled;
    }

    /**
     * Diese Methode generiert die Materialkosten für Ausrüstungsverbesserungen basierend auf dem aktuellen Party-Level.
     * Die erzeugten Materialkosten werden in der Liste aufruestungskosten gespeichert.
     * Die Methode verwendet Zufallszahlen, um die Menge der benötigten Materialien zu bestimmen.
     * Es werden verschiedene Materialien in unterschiedlichen Verhältnissen je nach Level der Verbesserung generiert.
     *
     * @author OF Stetter
     * @since 05.12.2023
     */

    public void materialKostenErzeugen() {
        int maxLvlVerbesserung = 999;
        aufruestungskosten.clear();
        for (int i = 0; i < maxLvlVerbesserung; i++) {
            aufruestungskosten.add(new HashMap<>());

            if ((i % 5) == 0) {
                aufruestungskosten.get(i).put(Materialien.MITHRIL, (int) Math.floor(partyController.getPartyLevel() * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(3)));
                aufruestungskosten.get(i).put(Materialien.POPEL, (int) Math.floor(partyController.getPartyLevel() * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(3)));
            }
            else if ((i % 3) == 0) {
                aufruestungskosten.get(i).put(Materialien.GOLDERZ, (int) Math.floor(partyController.getPartyLevel() * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(3)));
                aufruestungskosten.get(i).put(Materialien.EISENERZ, (int) Math.floor(partyController.getPartyLevel() * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(3)));
            }
            else if ((i % 2) == 0) {
                aufruestungskosten.get(i).put(Materialien.SILBERERZ, (int) Math.floor(partyController.getPartyLevel() * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(3)));
                aufruestungskosten.get(i).put(Materialien.SCHLEIM, (int) Math.floor(partyController.getPartyLevel() * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(3)));
            }
            else {
                aufruestungskosten.get(i).put(Materialien.SILBERERZ, (int) Math.floor(partyController.getPartyLevel() * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(3)));
                aufruestungskosten.get(i).put(Materialien.GOLDERZ, (int) Math.floor(partyController.getPartyLevel() * ZufallsZahlenGenerator.zufallsZahlAb1Inklusive(3)));
            }

        }
    }

    /**
     * Diese Methode wird verwendet, um die Schmiede-Ansicht anzuzeigen und mit der ViewController-Komponente zu verknüpfen.
     * Die Schmiede-Ansicht wird mit den entsprechenden Menü-Buttons angezeigt und mit einem Overlay versehen.
     * Diese Methode wird aufgerufen, wenn der Nutzer die Schmiede-Funktion betreten möchte.
     *
     * @author OF Stetter
     * @since 05.12.2023
     */
    public void schmiedeAnzeigen() {
        this.verbessernView.verbessernWaffenAnzeigeAktualisieren();
        this.verbessernView.verbessernRuestungAnzeigeAktualisieren();
        this.verbessernView.verbessernAccessoireAnzeigeAktualisieren();
        viewController.anmelden(verbessernView, schmiedeMenuButtons, AnsichtsTyp.MIT_OVERLAY);
        verbessernGeklickt.setValue(true);
//        viewController.anmelden(schmiedeView, schmiedeMenuButtons, AnsichtsTyp.MIT_OVERLAY);
    }

}

