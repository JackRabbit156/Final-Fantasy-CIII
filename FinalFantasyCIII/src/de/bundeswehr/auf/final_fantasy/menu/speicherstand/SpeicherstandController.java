package de.bundeswehr.auf.final_fantasy.menu.speicherstand;

import de.bundeswehr.auf.final_fantasy.Game;
import de.bundeswehr.auf.final_fantasy.charakter.controller.CharakterController;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.Accessoire;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.ruestungen.*;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.ausruestung.waffen.*;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.material.Material;
import de.bundeswehr.auf.final_fantasy.gegenstaende.model.verbrauchsgegenstaende.Verbrauchsgegenstand;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Materialien;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Verbrauchsgegenstaende;
import de.bundeswehr.auf.final_fantasy.menu.overlay.ViewController;
import de.bundeswehr.auf.final_fantasy.menu.trainer.faehigkeiten.model.Faehigkeit;
import de.bundeswehr.auf.final_fantasy.party.PartyController;
import de.bundeswehr.auf.final_fantasy.party.model.Party;
import de.bundeswehr.auf.final_fantasy.statistik.Statistik;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class SpeicherstandController {

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss.SSS");

    private final ViewController viewController;

    public SpeicherstandController(ViewController viewController) {
        this.viewController = viewController;
    }

    /**
     * Entfernt mit demNamen des Hauptcharakters verknüpften Spielstände die im
     * Hardcore-Modus abgespeichert wurden
     *
     * @param speicherstandZeit Zeitstempel des abgespeicherten Spielstandes -
     *                          String
     * @author OFR Rieger
     * @since 23.01.24
     */
    public void entferneSpeicherstand(String speicherstandZeit) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:spielstaende.db");
             Statement statement = connection.createStatement()) {
            statement.execute("PRAGMA foreign_keys=ON");
            int id = loadSpeicherstandId(speicherstandZeit, statement);
            statement.execute("DELETE FROM Speicherstand " +
                    "WHERE speicherstand_ID = " + id);
        }
    }

    /**
     * Entfernt alle mit dem Namen des Hauptcharakters verknüpften Spielstände die im
     * Hardcore-Modus abgespeichert wurden.
     *
     * @param partyController partyController vom Spielstand - PartyController
     * @author OL Schiffer-Schmidl
     * @since 06.12.23
     */
    public void entferneSpeicherstandHardcore(PartyController partyController) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:spielstaende.db")) {
            try (Statement statement = connection.createStatement()) {
                statement.execute("PRAGMA foreign_keys=ON");
                ResultSet resultSet = statement.executeQuery(
                        "SELECT speicherstand_ID " +
                                "FROM Speicherstand " +
                                "WHERE hardcore = " + true + " AND " +
                                "speicherstand_name = '" + partyController.getParty().getHauptCharakter().getName() + "'");
                while (resultSet.next()) {
                    Statement statement2 = connection.createStatement();
                    statement2.execute(
                            "DELETE FROM Speicherstand " +
                                    "WHERE speicherstand_ID = " + resultSet.getInt("speicherstand_ID"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ViewController getViewController() {
        return viewController;
    }

    /**
     * Überprüft, ob ein Speicherstand vorhanden ist
     *
     * @return boolean hatSpeicherstand
     * @author OL Schiffer-Schmidl
     * @since 06.12.2023
     */
    public boolean istSpeicherstandVorhanden() {
        boolean istVorhanden = false;
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:spielstaende.db")) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT speicherstand_ID FROM Speicherstand;");
            if (resultSet.next()) {
                istVorhanden = true;
            }
        } catch (Exception e) {
            return false;
        }
        return istVorhanden;
    }

    /**
     * Schreibt alle Daten die gespeichert werden müssen in den entsprechenden
     * Spielstand (SQLite Datenbank). Wenn es noch überhaupt gar keinen Spielstand
     * gibt, werden alle Tabellen erstellt, die zum Speichern der Daten benötigt
     * werden.
     *
     * @param speicherstand zu speichernder Speicherstand - Speicherstand
     * @author Melvin
     * @since 06.12.2023
     */
    public void speichern(Speicherstand speicherstand) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:spielstaende.db")) {
            if (Game.debugModus) {
                System.out.println("[DEBUG] Aktueller Spielstand wird gespeichert. Bitte warten...");
            }
            tabellenErstellen(connection);
            int id = saveSpeicherstand(speicherstand, connection);
            saveParty(speicherstand.getParty(), connection, id);

            saveSpielerCharakter(speicherstand.getParty().getHauptCharakter(), true, connection, id);
            for (SpielerCharakter charakter : speicherstand.getParty().getNebenCharaktere()) {
                saveSpielerCharakter(charakter, false, connection, id);
            }

            saveWaffenInventar(speicherstand.getParty().getAusruestungsgegenstandInventar().getInventarWaffen(), connection, id);
            saveRuestungsInventar(speicherstand.getParty().getAusruestungsgegenstandInventar().getInventarRuestung(), connection, id);
            saveAccessoiresInventar(speicherstand.getParty().getAusruestungsgegenstandInventar().getInventarAccessoire(), connection, id);
            saveVerbrauchsgegenstaende(speicherstand.getParty().getVerbrauchsgegenstaende(), connection, id);
            saveMaterial(speicherstand.getParty().getMaterialien(), connection, id);

            saveStatistik(speicherstand.getStatistik(), connection, id);
            if (Game.debugModus) {
                System.out.println("[DEBUG] Speichern erfolgreich");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Generiert alle abrufbaren Speicherstände für die Anzeige
     *
     * @return eine ObservableList mit Strings der Speicherstände
     * @author OL Schiffer-Schmidl
     * @since 06.12.2023
     */
    public ObservableList<String> speicherstaendeAbrufen() {
        ObservableList<String> speicherstaende = FXCollections.observableArrayList();
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:spielstaende.db");
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT datum, speicherstand_name, Speicherstand.speicherstand_ID, durchgefuehrteKaempfe, gold, level " +
                            "FROM Speicherstand " +
                            "INNER JOIN Statistik ON Speicherstand.speicherstand_ID = Statistik.statistik_ID " +
                            "INNER JOIN Party ON Speicherstand.speicherstand_ID = Party.party_ID " +
                            "INNER JOIN Charakter ON Speicherstand.speicherstand_ID = Charakter.party_ID " +
                            "WHERE istHauptCharakter = " + true + ";");
            while (resultSet.next()) {
                speicherstaende.add(String.format("[%s] %s [%d]: %d Gold, %d Kämpfe",
                        LocalDateTime.parse(resultSet.getString("datum")).format(FORMATTER),
                        resultSet.getString("speicherstand_name"),
                        resultSet.getInt("level"),
                        resultSet.getInt("gold"),
                        resultSet.getInt("durchgefuehrteKaempfe")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return speicherstaende;

    }

    /**
     * Lädt den ausgewählten Speicherstand anhand des Zeitstempels im gespeicherten
     * Spielstand
     *
     * @param speicherstandZeit Zeitstempel des abgespeicherten Spielstandes -
     *                          String
     * @return Speicherstand speicherstand
     * @author OL Schiffer-Schmidl
     * @since 06.12.2023
     */
    public Speicherstand speicherstandLaden(String speicherstandZeit) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:spielstaende.db");
             Statement statement = connection.createStatement()) {
            int id = loadSpeicherstandId(speicherstandZeit, statement);

            Party party = new Party();
            party.setHauptCharakter(loadHauptCharakter(id, connection));
            party.setNebenCharaktere(loadNebenCharaktere(id, connection));
            party.setGold(loadPartyGold(id, statement));
            party.getAusruestungsgegenstandInventar().setInventarAccessoire(loadAccessoiresInventar(id, statement));
            party.getAusruestungsgegenstandInventar().setInventarWaffen(loadWaffenInventar(id, statement));
            party.getAusruestungsgegenstandInventar().setInventarRuestung(loadRuestungsInventar(id, statement));
            party.setMaterialien(loadMaterial(id, statement));
            party.setVerbrauchsgegenstaende(loadVerbrauchsgegenstaende(id, statement));

            ResultSet resultSet = statement.executeQuery("SELECT schwierigkeitsgrad, hardcore " +
                    "FROM Speicherstand " +
                    "WHERE speicherstand_ID = " + id + ";");
            String schwierigkeitsgrad = resultSet.getString("schwierigkeitsgrad");
            boolean hardcore = resultSet.getBoolean("hardcore");

            Statistik statistik = loadStatistik(id, statement);

            return new Speicherstand(party, schwierigkeitsgrad, hardcore, statistik);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;

    }

    private Accessoire createAccessoire(ResultSet resultSet) throws SQLException {
        Accessoire accessoire = new Accessoire(0);
        accessoire.setName(resultSet.getString("name"));
        accessoire.setKaufwert(resultSet.getInt("kaufwert"));
        accessoire.setVerkaufswert(resultSet.getInt("verkaufswert"));
        accessoire.setIstNichtKaufbar(resultSet.getBoolean("istNichtKaufbar"));
        accessoire.setLevelAnforderung(resultSet.getInt("levelAnforderung"));
        accessoire.setIstSoeldnerItem(resultSet.getBoolean("istSoeldnerItem"));
        accessoire.setMaxGesundheitsPunkte(resultSet.getInt("maxGesundheitsPunkte"));
        accessoire.setMaxManaPunkte(resultSet.getInt("maxManaPunkte"));
        accessoire.setGesundheitsRegeneration(resultSet.getInt("gesundheitsRegeneration"));
        accessoire.setManaRegeneration(resultSet.getInt("manaRegeneration"));
        accessoire.setBeweglichkeit(resultSet.getInt("beweglichkeit"));
        accessoire.setIcon(resultSet.getString("icon"));
        return accessoire;
    }

    private Ruestung createRuestung(ResultSet resultSet) throws SQLException {
        Ruestung ruestung;
        switch (resultSet.getString("ruestungsTyp")) {
            case "leichteruestung":
                ruestung = new LeichteRuestung(1);
                break;
            case "mittlereruestung":
                ruestung = new MittlereRuestung(1);
                break;
            case "schwereruestung":
                ruestung = new SchwereRuestung(1);
                break;
            case "sehrschwereruestung":
                ruestung = new SehrSchwereRuestung(1);
                break;
            default:
                throw new RuntimeException("Unbekannter Rüstungstyp: " + resultSet.getString("ruestungsTyp"));
        }
        ruestung.setName(resultSet.getString("name"));
        ruestung.setKaufwert(resultSet.getInt("kaufwert"));
        ruestung.setVerkaufswert(resultSet.getInt("verkaufswert"));
        ruestung.setIstNichtKaufbar(resultSet.getBoolean("istNichtKaufbar"));
        ruestung.setLevelAnforderung(resultSet.getInt("levelAnforderung"));
        ruestung.setIstSoeldnerItem(resultSet.getBoolean("istSoeldnerItem"));
        ruestung.setVerteidigung(resultSet.getInt("verteidigung"));
        ruestung.setMagischeVerteidigung(resultSet.getInt("magischeVerteidigung"));
        ruestung.setResistenz(resultSet.getInt("resistenz"));
        ruestung.setMaxManaPunkte(resultSet.getInt("maxManaPunkte"));
        ruestung.setMaxGesundheitsPunkte(resultSet.getInt("maxGesundheitsPunkte"));
        ruestung.setIcon(resultSet.getString("icon"));
        return ruestung;
    }

    private SpielerCharakter createSpielerCharakter(int charakterId, Connection connection, ResultSet resultSet) throws SQLException {
        SpielerCharakter charakter = new SpielerCharakter(
                resultSet.getString("name"),
                resultSet.getString("klasseBezeichnung"),
                resultSet.getString("geschichte"));
        charakter.setGrafischeDarstellung(resultSet.getString("grafischeDarstellung"));
        charakter.setLevel(resultSet.getInt("level"));

        charakter.getAttribute().setMaxGesundheitsPunkte(resultSet.getInt("maxGesundheitsPunkte"));
        charakter.getAttribute().setMaxManaPunkte(resultSet.getInt("maxManaPunkte"));
        charakter.getAttribute().setPhysischeAttacke(resultSet.getInt("physischeAttacke"));
        charakter.getAttribute().setMagischeAttacke(resultSet.getInt("magischeAttacke"));
        charakter.getAttribute().setGenauigkeit(resultSet.getInt("genauigkeit"));
        charakter.getAttribute().setVerteidigung(resultSet.getInt("verteidigung"));
        charakter.getAttribute().setMagischeVerteidigung(resultSet.getInt("magischeVerteidigung"));
        charakter.getAttribute().setResistenz(resultSet.getInt("resistenz"));
        charakter.getAttribute().setBeweglichkeit(resultSet.getInt("beweglichkeit"));
        charakter.getAttribute().setGesundheitsRegeneration(resultSet.getInt("gesundheitsregeneration"));
        charakter.getAttribute().setManaRegeneration(resultSet.getInt("manaRegeneration"));

        charakter.setErfahrungsPunkte(resultSet.getInt("erfahrungspunkte"));
        charakter.setOffeneFaehigkeitspunkte(resultSet.getInt("offeneFaehigkeitspunkte"));
        charakter.setVerteilteFaehigkeitspunkte(resultSet.getInt("verteilteFaehigkeitspunkte"));
        charakter.setOffeneAttributpunkte(resultSet.getInt("offeneAttributpunkte"));

        CharakterController.ausruestungAusziehen(charakter, charakter.getWaffe());
        CharakterController.ausruestungAusziehen(charakter, charakter.getRuestung());
        for (Accessoire accessoire : charakter.getAccessoires()) {
            if (accessoire != null) {
                CharakterController.ausruestungAusziehen(charakter, accessoire);
            }
        }
        try (Statement statement = connection.createStatement()) {
            charakter.setFaehigkeiten(loadFaehigkeiten(charakterId, statement));

            CharakterController.ausruestungAnlegen(charakter, loadWaffe(charakterId, statement));
            CharakterController.ausruestungAnlegen(charakter, loadRuestung(charakterId, statement));
            for (Accessoire accessoire : loadAccessoires(charakterId, statement)) {
                if (accessoire != null) {
                    CharakterController.ausruestungAnlegen(charakter, accessoire);
                }
            }
        }

        charakter.setGesundheitsPunkte(resultSet.getInt("gesundheitsPunkte"));
        charakter.setManaPunkte(resultSet.getInt("manaPunkte"));
        return charakter;
    }

    private Waffe createWaffe(ResultSet resultSet) throws SQLException {
        Waffe waffe;
        switch (resultSet.getString("waffenTyp")) {
            case "bogen":
                waffe = new Bogen(1);
                break;
            case "einhandwaffe":
                waffe = new EinhandWaffe(1);
                break;
            case "heilerstab":
                waffe = new HeilerStab(1);
                break;
            case "magierstab":
                waffe = new MagierStab(1);
                break;
            case "schild":
                waffe = new Schild(1);
                break;
            case "zweihandwaffe":
                waffe = new ZweihandWaffe(1);
                break;
            default:
                throw new RuntimeException("Unbekannter Waffentyp: " + resultSet.getString("waffenTyp"));
        }
        waffe.setName(resultSet.getString("name"));
        waffe.setKaufwert(resultSet.getInt("kaufwert"));
        waffe.setVerkaufswert(resultSet.getInt("verkaufswert"));
        waffe.setIstNichtKaufbar(resultSet.getBoolean("istNichtKaufbar"));
        waffe.setLevelAnforderung(resultSet.getInt("levelAnforderung"));
        waffe.setIstSoeldnerItem(resultSet.getBoolean("istSoeldnerItem"));
        waffe.setAttacke(resultSet.getInt("attacke"));
        waffe.setMagischeAttacke(resultSet.getInt("magischeAttacke"));
        waffe.setBeweglichkeit(resultSet.getInt("beweglichkeit"));
        waffe.setGenauigkeit(resultSet.getInt("genauigkeit"));
        waffe.setIcon(resultSet.getString("icon"));
        return waffe;
    }

    private Accessoire[] loadAccessoires(int charakterId, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(
                "SELECT name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, gesundheitsRegeneration, manaRegeneration, beweglichkeit, maxGesundheitsPunkte, maxManaPunkte, icon " +
                        "FROM Accessoire " +
                        "WHERE charakter_ID = " + charakterId + ";");
        Accessoire[] nebenCharakterAccessoires = new Accessoire[3];
        int counter = 0;
        while (resultSet.next()) {
            if (resultSet.getString("name") != null) {
                nebenCharakterAccessoires[counter] = createAccessoire(resultSet);
            }
            counter++;
        }
        return nebenCharakterAccessoires;
    }

    private List<Accessoire> loadAccessoiresInventar(int id, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(
                "SELECT name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, maxGesundheitsPunkte, maxManaPunkte, gesundheitsRegeneration, manaRegeneration, beweglichkeit, icon " +
                        "FROM Accessoire " +
                        "WHERE party_ID =" + id + ";");
        List<Accessoire> zuLadendePartyAccessoireInventar = new ArrayList<>();
        while (resultSet.next()) {
            zuLadendePartyAccessoireInventar.add(createAccessoire(resultSet));
        }
        return zuLadendePartyAccessoireInventar;
    }

    private List<Faehigkeit> loadFaehigkeiten(int charakterId, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(
                "SELECT name, beschreibung, manaKosten, faehigkeitsTyp, level, effektStaerke, istFreundlich, icon, levelAnforderung, zielAttribut, zielAnzahl, wahrscheinlichkeit, charakter_ID "
                        + "FROM Faehigkeit " + "WHERE charakter_ID =" + charakterId + ";");
        List<Faehigkeit> faehigkeiten = new ArrayList<>();
        while (resultSet.next()) {
            Faehigkeit faehigkeit = new Faehigkeit(resultSet.getString("name"),
                    resultSet.getString("beschreibung"),
                    resultSet.getString("icon"),
                    resultSet.getInt("manaKosten"),
                    resultSet.getInt("level"),
                    resultSet.getInt("levelAnforderung"),
                    resultSet.getBoolean("istFreundlich"),
                    resultSet.getInt("effektStaerke"),
                    resultSet.getInt("zielAnzahl"),
                    resultSet.getDouble("wahrscheinlichkeit"),
                    resultSet.getString("zielAttribut"),
                    resultSet.getString("faehigkeitsTyp"));
            faehigkeiten.add(faehigkeit);
        }
        return faehigkeiten;
    }

    private SpielerCharakter loadHauptCharakter(int id, Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT name, klasseBezeichnung, grafischeDarstellung, level, gesundheitsPunkte, maxGesundheitsPunkte, manaPunkte, maxManaPunkte, physischeAttacke, magischeAttacke, genauigkeit, verteidigung, magischeVerteidigung, resistenz, beweglichkeit, gesundheitsregeneration, manaRegeneration, geschichte, erfahrungsPunkte, offeneFaehigkeitspunkte, verteilteFaehigkeitspunkte, offeneAttributpunkte, charakter_ID " +
                            "FROM Charakter " +
                            "WHERE party_ID =" + id + " " +
                            "AND istHauptCharakter =" + true + ";");
            resultSet.next();
            return createSpielerCharakter(resultSet.getInt("charakter_ID"), connection, resultSet);
        }
    }

    private Map<Material, IntegerProperty> loadMaterial(int id, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT name, anzahl " +
                "FROM Material " +
                "WHERE party_ID =" + id + ";");
        Map<Material, IntegerProperty> materialien = new HashMap<>();
        while (resultSet.next()) {
            SimpleIntegerProperty anzahl = new SimpleIntegerProperty(resultSet.getInt("anzahl"));
            switch (resultSet.getString("name")) {
                case "Eisenerz":
                    materialien.put(Materialien.EISENERZ, anzahl);
                    break;
                case "Silbererz":
                    materialien.put(Materialien.SILBERERZ, anzahl);
                    break;
                case "Golderz":
                    materialien.put(Materialien.GOLDERZ, anzahl);
                    break;
                case "Mithril":
                    materialien.put(Materialien.MITHRIL, anzahl);
                    break;
                case "Popel":
                    materialien.put(Materialien.POPEL, anzahl);
                    break;
                case "Schleim":
                    materialien.put(Materialien.SCHLEIM, anzahl);
                    break;
                default:
                    throw new RuntimeException("Unbekanntes Material: " + resultSet.getString("name"));
            }
        }
        return materialien;
    }

    private SpielerCharakter[] loadNebenCharaktere(int id, Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT name, klasseBezeichnung, grafischeDarstellung, level, gesundheitsPunkte, maxGesundheitsPunkte, manaPunkte, maxManaPunkte, physischeAttacke, magischeAttacke, genauigkeit, verteidigung, magischeVerteidigung, resistenz, beweglichkeit, gesundheitsregeneration, manaRegeneration, geschichte, erfahrungsPunkte, offeneFaehigkeitspunkte, verteilteFaehigkeitspunkte, offeneAttributpunkte, charakter_ID, party_ID " +
                            "FROM Charakter " +
                            "WHERE party_ID = " + id +
                            " AND istHauptCharakter = " + false + ";");
            SpielerCharakter[] nebenCharaktere = new SpielerCharakter[3];
            int counter = 0;
            while (resultSet.next()) {
                if (resultSet.getString("name") != null) {
                    nebenCharaktere[counter] = createSpielerCharakter(resultSet.getInt("charakter_ID"), connection, resultSet);
                }
                counter++;
            }
            return nebenCharaktere;
        }
    }

    private int loadPartyGold(int id, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT gold " +
                "FROM Party " +
                "WHERE party_ID = " + id + ";");
        return resultSet.getInt("gold");
    }

    private Ruestung loadRuestung(int charakterId, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(
                "SELECT name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, magischeVerteidigung, verteidigung, ruestungsTyp, resistenz, maxManaPunkte, maxGesundheitsPunkte, icon " +
                        "FROM Ruestung " +
                        "WHERE charakter_ID = " + charakterId + ";");
        resultSet.next();
        return createRuestung(resultSet);
    }

    private List<Ruestung> loadRuestungsInventar(int id, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(
                "SELECT name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, magischeVerteidigung, verteidigung, ruestungsTyp, icon, resistenz, maxGesundheitsPunkte, maxManaPunkte " +
                        "FROM Ruestung " +
                        "WHERE party_ID =" + id + ";");
        List<Ruestung> inventar = new ArrayList<>();
        while (resultSet.next()) {
            inventar.add(createRuestung(resultSet));
        }
        return inventar;
    }

    private int loadSpeicherstandId(String speicherstandZeit, Statement statement) throws SQLException {
        String timestamp = speicherstandZeit.substring(6, 10) + "-" + speicherstandZeit.substring(3, 5) + "-" + speicherstandZeit.substring(0, 2) + "T" + speicherstandZeit.substring(11);
        ResultSet resultSet = statement.executeQuery("SELECT speicherstand_ID FROM Speicherstand WHERE datum ='" + timestamp + "';");
        return resultSet.getInt("speicherstand_ID");
    }

    private Statistik loadStatistik(int id, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT gesamtErwirtschaftetesGold, durchgefuehrteKaempfe, gewonneneKaempfe, verloreneKaempfe " +
                "FROM Statistik " +
                "WHERE statistik_ID = " + id + ";");
        Statistik statistik = new Statistik();
        statistik.setGesamtErwirtschaftetesGold(resultSet.getInt("gesamtErwirtschaftetesGold"));
        statistik.setDurchgefuehrteKaempfe(resultSet.getInt("durchgefuehrteKaempfe"));
        statistik.setGewonneneKaempfe(resultSet.getInt("gewonneneKaempfe"));
        statistik.setVerloreneKaempfe(resultSet.getInt("verloreneKaempfe"));
        return statistik;
    }

    private Map<Verbrauchsgegenstand, IntegerProperty> loadVerbrauchsgegenstaende(int id, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT name, kaufwert, verkaufswert, anzahl " +
                "FROM Verbrauchsgegenstand " +
                "WHERE party_ID = " + id + ";");
        Map<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstaende = new HashMap<>();
        while (resultSet.next()) {
            SimpleIntegerProperty anzahl = new SimpleIntegerProperty(resultSet.getInt("anzahl"));
            switch (resultSet.getString("name")) {
                case "Grosser Heiltrank":
                    verbrauchsgegenstaende.put(Verbrauchsgegenstaende.GROSSER_HEILTRANK, anzahl);
                    break;
                case "Mittlerer Heiltrank":
                    verbrauchsgegenstaende.put(Verbrauchsgegenstaende.MITTLERER_HEILTRANK, anzahl);
                    break;
                case "Kleiner Heiltrank":
                    verbrauchsgegenstaende.put(Verbrauchsgegenstaende.KLEINER_HEILTRANK, anzahl);
                    break;
                case "Grosser Manatrank":
                    verbrauchsgegenstaende.put(Verbrauchsgegenstaende.GROSSER_MANATRANK, anzahl);
                    break;
                case "Mittlerer Manatrank":
                    verbrauchsgegenstaende.put(Verbrauchsgegenstaende.MITTLERER_MANATRANK, anzahl);
                    break;
                case "Kleiner Manatrank":
                    verbrauchsgegenstaende.put(Verbrauchsgegenstaende.KLEINER_MANATRANK, anzahl);
                    break;
                default:
                    throw new RuntimeException("Unbekannter Verbrauchsgegenstand: " + resultSet.getString("name"));
            }
        }
        return verbrauchsgegenstaende;
    }

    private Waffe loadWaffe(int charakterId, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(
                "SELECT name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, attacke, magischeAttacke, waffenTyp, genauigkeit, beweglichkeit, icon " +
                        "FROM Waffe " +
                        "WHERE charakter_ID =" + charakterId + ";");
        resultSet.next();
        return createWaffe(resultSet);
    }

    private List<Waffe> loadWaffenInventar(int id, Statement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery(
                "SELECT name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, attacke, magischeAttacke, waffenTyp, genauigkeit, beweglichkeit, icon " +
                        "FROM Waffe " +
                        "WHERE party_ID =" + id + ";");
        List<Waffe> zuLadendePartyWaffenInventar = new ArrayList<>();
        while (resultSet.next()) {
            zuLadendePartyWaffenInventar.add(createWaffe(resultSet));
        }
        return zuLadendePartyWaffenInventar;
    }

    private void saveAccessoires(SpielerCharakter charakter, Connection connection, int charakterId) throws SQLException {
        // Speicher die ausgerüsteten Accessoires des aktuellen Charakters
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Accessoire (charakter_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, gesundheitsRegeneration, manaRegeneration, beweglichkeit, maxGesundheitsPunkte, maxManaPunkte, icon) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            for (int counter = 0; counter < 3; counter++) {
                // Der aktuelle Accessoire-Slot vom aktuellen Charakter ist befüllt und Attribute werden gespeichert
                if (charakter.getAccessoires()[counter] != null) {
                    preparedStatement.setInt(1, charakterId);
                    preparedStatement.setString(2, charakter.getAccessoires()[counter].getName());
                    preparedStatement.setInt(3, charakter.getAccessoires()[counter].getKaufwert());
                    preparedStatement.setInt(4, charakter.getAccessoires()[counter].getVerkaufswert());
                    preparedStatement.setBoolean(5, charakter.getAccessoires()[counter].isIstNichtKaufbar());
                    preparedStatement.setInt(6, charakter.getAccessoires()[counter].getLevelAnforderung());
                    preparedStatement.setBoolean(7, charakter.getAccessoires()[counter].isIstSoeldnerItem());
                    preparedStatement.setInt(8, charakter.getAccessoires()[counter].getGesundheitsRegeneration());
                    preparedStatement.setInt(9, charakter.getAccessoires()[counter].getManaRegeneration());
                    preparedStatement.setInt(10, charakter.getAccessoires()[counter].getBeweglichkeit());
                    preparedStatement.setInt(11, charakter.getAccessoires()[counter].getMaxGesundheitsPunkte());
                    preparedStatement.setInt(12, charakter.getAccessoires()[counter].getMaxManaPunkte());
                    preparedStatement.setString(13, charakter.getAccessoires()[counter].getIcon());
                }
                // Der aktuelle Accessoire-Slot vom aktuellen Charakter ist nicht befüllt und ein leerer Accessoire wird gespeichert
                else {
                    preparedStatement.setInt(1, charakterId);
                    preparedStatement.setString(2, null);
                    preparedStatement.setInt(3, -1);
                    preparedStatement.setInt(4, -1);
                    preparedStatement.setBoolean(5, false);
                    preparedStatement.setInt(6, -1);
                    preparedStatement.setBoolean(7, false);
                    preparedStatement.setInt(8, -1);
                    preparedStatement.setInt(9, -1);
                    preparedStatement.setInt(10, -1);
                    preparedStatement.setInt(11, -1);
                    preparedStatement.setInt(12, -1);
                    preparedStatement.setString(13, null);
                }
                preparedStatement.execute();
            }
        }
    }

    private void saveAccessoiresInventar(List<Accessoire> accessoires, Connection connection, int id) throws SQLException {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Accessoire (party_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, maxGesundheitsPunkte, maxManaPunkte, gesundheitsRegeneration, manaRegeneration, beweglichkeit, icon) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            for (Accessoire accessoire : accessoires) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, accessoire.getName());
                preparedStatement.setInt(3, accessoire.getKaufwert());
                preparedStatement.setInt(4, accessoire.getVerkaufswert());
                preparedStatement.setBoolean(5, accessoire.isIstNichtKaufbar());
                preparedStatement.setInt(6, accessoire.getLevelAnforderung());
                preparedStatement.setBoolean(7, accessoire.isIstSoeldnerItem());
                preparedStatement.setInt(8, accessoire.getMaxGesundheitsPunkte());
                preparedStatement.setInt(9, accessoire.getMaxManaPunkte());
                preparedStatement.setInt(10, accessoire.getGesundheitsRegeneration());
                preparedStatement.setInt(11, accessoire.getManaRegeneration());
                preparedStatement.setInt(12, accessoire.getBeweglichkeit());
                preparedStatement.setString(13, accessoire.getIcon());
                preparedStatement.execute();
            }
        }
    }

    private int saveCharakterDaten(int id, SpielerCharakter charakter, Connection connection, boolean hauptCharakter) throws SQLException {
        // Speicher die Charakterdaten vom aktuellen Charakter
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Charakter (party_ID, istHauptCharakter, klasseBezeichnung, name, grafischeDarstellung, level, gesundheitsPunkte, maxGesundheitsPunkte, manaPunkte, maxManaPunkte, physischeAttacke, magischeAttacke, genauigkeit, verteidigung, magischeVerteidigung, resistenz, beweglichkeit, gesundheitsregeneration, manaRegeneration, geschichte, erfahrungsPunkte, offeneFaehigkeitspunkte, verteilteFaehigkeitspunkte, offeneAttributpunkte) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setBoolean(2, hauptCharakter);
            preparedStatement.setString(3, charakter.getKlasse().getBezeichnung());
            preparedStatement.setString(4, charakter.getName());
            preparedStatement.setString(5, charakter.getGrafischeDarstellung());
            preparedStatement.setInt(6, charakter.getLevel());
            preparedStatement.setInt(7, charakter.getGesundheitsPunkte());
            preparedStatement.setInt(8, charakter.getAttribute().getMaxGesundheitsPunkte());
            preparedStatement.setInt(9, charakter.getManaPunkte());
            preparedStatement.setInt(10, charakter.getAttribute().getMaxManaPunkte());
            preparedStatement.setInt(11, charakter.getAttribute().getPhysischeAttacke());
            preparedStatement.setInt(12, charakter.getAttribute().getMagischeAttacke());
            preparedStatement.setInt(13, charakter.getAttribute().getGenauigkeit());
            preparedStatement.setInt(14, charakter.getAttribute().getVerteidigung());
            preparedStatement.setInt(15, charakter.getAttribute().getMagischeVerteidigung());
            preparedStatement.setInt(16, charakter.getAttribute().getResistenz());
            preparedStatement.setInt(17, charakter.getAttribute().getBeweglichkeit());
            preparedStatement.setInt(18, charakter.getAttribute().getGesundheitsRegeneration());
            preparedStatement.setInt(19, charakter.getAttribute().getManaRegeneration());
            preparedStatement.setString(20, charakter.getGeschichte());
            preparedStatement.setInt(21, charakter.getErfahrungsPunkte());
            preparedStatement.setInt(22, charakter.getOffeneFaehigkeitspunkte());
            preparedStatement.setInt(23, charakter.getVerteilteFaehigkeitspunkte());
            preparedStatement.setInt(24, charakter.getOffeneAttributpunkte());
            preparedStatement.executeUpdate();
        }
        try (Statement statement = connection.createStatement()) {
            ResultSet resultset = statement.executeQuery("SELECT MAX(charakter_ID) FROM Charakter;");
            resultset.next();
            return resultset.getInt(1);
        }
    }

    private void saveFaehigkeiten(SpielerCharakter charakter, Connection connection, int charakterId) throws SQLException {
        // Speichert die Fähigkeiten des aktuellen Charakters
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Faehigkeit (charakter_ID, name, beschreibung, manaKosten, level, levelAnforderung, istFreundlich, effektStaerke, zielAnzahl, wahrscheinlichkeit, zielAttribut, faehigkeitsTyp, icon) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            for (int counter = 0, len = charakter.getFaehigkeiten().size(); counter < len; counter++) {
                preparedStatement.setInt(1, charakterId);
                preparedStatement.setString(2, charakter.getFaehigkeiten().get(counter).getName());
                preparedStatement.setString(3, charakter.getFaehigkeiten().get(counter).getBeschreibung());
                preparedStatement.setInt(4, charakter.getFaehigkeiten().get(counter).getManaKosten());
                preparedStatement.setInt(5, charakter.getFaehigkeiten().get(counter).getLevel());
                preparedStatement.setInt(6, charakter.getFaehigkeiten().get(counter).getLevelAnforderung());
                preparedStatement.setBoolean(7, charakter.getFaehigkeiten().get(counter).isIstFreundlich());
                preparedStatement.setInt(8, charakter.getFaehigkeiten().get(counter).getEffektStaerke());
                preparedStatement.setInt(9, charakter.getFaehigkeiten().get(counter).getZielAnzahl());
                preparedStatement.setDouble(10, charakter.getFaehigkeiten().get(counter).getWahrscheinlichkeit());
                preparedStatement.setString(11, charakter.getFaehigkeiten().get(counter).getZielAttribut());
                preparedStatement.setString(12, charakter.getFaehigkeiten().get(counter).getFaehigkeitsTyp());
                preparedStatement.setString(13, charakter.getFaehigkeiten().get(counter).getIcon());
                preparedStatement.execute();
            }
        }
    }

    private void saveLeereCharakterDaten(int id, Connection connection, boolean hauptCharakter) throws SQLException {
        // Leerer Charakter wird erstellt
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Charakter (party_ID, istHauptCharakter, klasseBezeichnung, name, grafischeDarstellung, level, gesundheitsPunkte, maxGesundheitsPunkte, manaPunkte, maxManaPunkte, physischeAttacke, magischeAttacke, genauigkeit, verteidigung, magischeVerteidigung, resistenz, beweglichkeit, gesundheitsregeneration, manaRegeneration, geschichte, erfahrungsPunkte, offeneFaehigkeitspunkte, verteilteFaehigkeitspunkte, offeneAttributpunkte) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setBoolean(2, hauptCharakter);
            preparedStatement.setString(3, null);
            preparedStatement.setString(4, null);
            preparedStatement.setString(5, null);
            preparedStatement.setInt(6, -1);
            preparedStatement.setInt(7, -1);
            preparedStatement.setInt(8, -1);
            preparedStatement.setInt(9, -1);
            preparedStatement.setInt(10, -1);
            preparedStatement.setInt(11, -1);
            preparedStatement.setInt(12, -1);
            preparedStatement.setInt(13, -1);
            preparedStatement.setInt(14, -1);
            preparedStatement.setInt(15, -1);
            preparedStatement.setInt(16, -1);
            preparedStatement.setInt(17, -1);
            preparedStatement.setInt(18, -1);
            preparedStatement.setInt(19, -1);
            preparedStatement.setString(20, null);
            preparedStatement.setInt(21, -1);
            preparedStatement.setInt(22, -1);
            preparedStatement.setInt(23, -1);
            preparedStatement.setInt(24, -1);
            preparedStatement.execute();
        }
    }

    private void saveMaterial(Map<Material, IntegerProperty> materialien, Connection connection, int id) throws SQLException {
        for (Entry<Material, IntegerProperty> entry : materialien.entrySet()) {
            String materialName = entry.getKey().getName();
            int materialAnzahl = entry.getValue().get();
            try (final PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO Material (party_ID, name, anzahl) VALUES (?, ?, ?);")) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, materialName);
                preparedStatement.setInt(3, materialAnzahl);
                preparedStatement.execute();
            }
        }
    }

    private void saveParty(Party party, Connection connection, int id) throws SQLException {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Party (party_ID, hauptCharakter, nebenCharakter1, nebenCharakter2, nebenCharakter3, gold) " +
                        "VALUES (?, ?, ?, ?, ?, ?);")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, party.getHauptCharakter().getName());
            for (int counter = 0; counter < 3; counter++) {
                if (party.getNebenCharaktere()[counter] != null) {
                    preparedStatement.setString(3 + counter, party.getNebenCharaktere()[counter].getName());
                }
                else {
                    preparedStatement.setString(3 + counter, null);
                }
            }
            preparedStatement.setInt(6, party.getGold());
            preparedStatement.execute();
        }
    }

    private void saveRuestung(SpielerCharakter charakter, Connection connection, int charakterId) throws SQLException {
        // Speicher die ausgerüstete Rüstung des aktuellen Charakters
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Ruestung (charakter_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, magischeVerteidigung, verteidigung, ruestungsTyp, resistenz, maxGesundheitsPunkte, maxManaPunkte, icon) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            preparedStatement.setInt(1, charakterId);
            preparedStatement.setString(2, charakter.getRuestung().getName());
            preparedStatement.setInt(3, charakter.getRuestung().getKaufwert());
            preparedStatement.setInt(4, charakter.getRuestung().getVerkaufswert());
            preparedStatement.setBoolean(5, charakter.getRuestung().isIstNichtKaufbar());
            preparedStatement.setInt(6, charakter.getRuestung().getLevelAnforderung());
            preparedStatement.setBoolean(7, charakter.getRuestung().isIstSoeldnerItem());
            preparedStatement.setInt(8, charakter.getRuestung().getMagischeVerteidigung());
            preparedStatement.setInt(9, charakter.getRuestung().getVerteidigung());
            preparedStatement.setString(10, charakter.getRuestung().getClass().getSimpleName().toLowerCase());
            preparedStatement.setInt(11, charakter.getRuestung().getResistenz());
            preparedStatement.setInt(12, charakter.getRuestung().getMaxGesundheitsPunkte());
            preparedStatement.setInt(13, charakter.getRuestung().getMaxManaPunkte());
            preparedStatement.setString(14, charakter.getRuestung().getIcon());
            preparedStatement.execute();
        }
    }

    private void saveRuestungsInventar(List<Ruestung> ruestungen, Connection connection, int id) throws SQLException {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Ruestung (party_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, magischeVerteidigung, verteidigung, ruestungsTyp, icon, resistenz, maxGesundheitsPunkte, maxManaPunkte) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            for (Ruestung ruestung : ruestungen) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, ruestung.getName());
                preparedStatement.setInt(3, ruestung.getKaufwert());
                preparedStatement.setInt(4, ruestung.getVerkaufswert());
                preparedStatement.setBoolean(5, ruestung.isIstNichtKaufbar());
                preparedStatement.setInt(6, ruestung.getLevelAnforderung());
                preparedStatement.setBoolean(7, ruestung.isIstSoeldnerItem());
                preparedStatement.setInt(8, ruestung.getMagischeVerteidigung());
                preparedStatement.setInt(9, ruestung.getVerteidigung());
                preparedStatement.setString(10, ruestung.getClass().getSimpleName().toLowerCase());
                preparedStatement.setString(11, ruestung.getIcon());
                preparedStatement.setInt(12, ruestung.getResistenz());
                preparedStatement.setInt(13, ruestung.getMaxGesundheitsPunkte());
                preparedStatement.setInt(14, ruestung.getMaxManaPunkte());
                preparedStatement.execute();
            }
        }
    }

    private int saveSpeicherstand(Speicherstand speicherstand, Connection connection) throws SQLException {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Speicherstand (speicherstand_name, schwierigkeitsgrad, hardcore, datum) " +
                        "VALUES (?, ?, ?, ?);")) {
            preparedStatement.setString(1, speicherstand.getParty().getHauptCharakter().getName());
            preparedStatement.setString(2, speicherstand.getSchwierigkeitsgrad());
            preparedStatement.setBoolean(3, speicherstand.isHardcore());
            preparedStatement.setObject(4, LocalDateTime.now());
            preparedStatement.execute();
        }
        int id;
        try (Statement statement = connection.createStatement()) {
            ResultSet resultset = statement.executeQuery("SELECT MAX(speicherstand_ID) " +
                    "FROM Speicherstand;");
            resultset.next();
            id = resultset.getInt(1);

        }
        return id;
    }

    private void saveSpielerCharakter(SpielerCharakter charakter, boolean hauptCharakter, final Connection connection, int id) throws SQLException {
        if (charakter != null) {
            int charakterId = saveCharakterDaten(id, charakter, connection, hauptCharakter);
            saveFaehigkeiten(charakter, connection, charakterId);
            saveWaffe(charakter, connection, charakterId);
            saveRuestung(charakter, connection, charakterId);
            saveAccessoires(charakter, connection, charakterId);
        }
        // Der Nebencharakterslot ist leer
        else {
            saveLeereCharakterDaten(id, connection, hauptCharakter);
        }
    }

    private void saveStatistik(Statistik statistik, Connection connection, int id) throws SQLException {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Statistik (statistik_ID, gesamtErwirtschaftetesGold, durchgefuehrteKaempfe, gewonneneKaempfe, verloreneKaempfe) " +
                        "VALUES (?, ?, ?, ?, ?);")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, statistik.getGesamtErwirtschaftetesGold());
            preparedStatement.setInt(3, statistik.getDurchgefuehrteKaempfe());
            preparedStatement.setInt(4, statistik.getGewonneneKaempfe());
            preparedStatement.setInt(5, statistik.getVerloreneKaempfe());
            preparedStatement.execute();
        }
    }

    private void saveVerbrauchsgegenstaende(Map<Verbrauchsgegenstand, IntegerProperty> verbrauchsgegenstaende, Connection connection, int id) throws SQLException {
        for (Entry<Verbrauchsgegenstand, IntegerProperty> entry : verbrauchsgegenstaende.entrySet()) {
            Verbrauchsgegenstand item = entry.getKey();
            int itemAnzahl = entry.getValue().get();
            try (final PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Verbrauchsgegenstand (party_ID, name, kaufwert, verkaufswert, anzahl) " +
                            "VALUES (?, ?, ?, ?, ?);")) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, item.getName());

                preparedStatement.setInt(3, item.getKaufwert());
                preparedStatement.setInt(4, item.getVerkaufswert());
                preparedStatement.setInt(5, itemAnzahl);
                preparedStatement.execute();
            }
        }
    }

    private void saveWaffe(SpielerCharakter charakter, Connection connection, int charakterId) throws SQLException {
        // Speicher die ausgerüstete Waffe des aktuellen Charakters
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Waffe (charakter_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, attacke, magischeAttacke, waffenTyp, icon, genauigkeit, beweglichkeit) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            preparedStatement.setInt(1, charakterId);
            preparedStatement.setString(2, charakter.getWaffe().getName());
            preparedStatement.setInt(3, charakter.getWaffe().getKaufwert());
            preparedStatement.setInt(4, charakter.getWaffe().getVerkaufswert());
            preparedStatement.setBoolean(5, charakter.getWaffe().isIstNichtKaufbar());
            preparedStatement.setInt(6, charakter.getWaffe().getLevelAnforderung());
            preparedStatement.setBoolean(7, charakter.getWaffe().isIstSoeldnerItem());
            preparedStatement.setInt(8, charakter.getWaffe().getAttacke());
            preparedStatement.setInt(9, charakter.getWaffe().getMagischeAttacke());
            preparedStatement.setString(10, charakter.getWaffe().getClass().getSimpleName().toLowerCase());
            preparedStatement.setString(11, charakter.getWaffe().getIcon());
            preparedStatement.setInt(12, charakter.getWaffe().getGenauigkeit());
            preparedStatement.setInt(13, charakter.getWaffe().getBeweglichkeit());
            preparedStatement.execute();
        }
    }

    private void saveWaffenInventar(List<Waffe> waffen, Connection connection, int id) throws SQLException {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO Waffe (party_ID, name, kaufwert, verkaufswert, istNichtKaufbar, levelAnforderung, istSoeldnerItem, attacke, magischeAttacke, waffenTyp, icon, genauigkeit, beweglichkeit) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);")) {
            for (Waffe waffe : waffen) {
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, waffe.getName());
                preparedStatement.setInt(3, waffe.getKaufwert());
                preparedStatement.setInt(4, waffe.getVerkaufswert());
                preparedStatement.setBoolean(5, waffe.isIstNichtKaufbar());
                preparedStatement.setInt(6, waffe.getLevelAnforderung());
                preparedStatement.setBoolean(7, waffe.isIstSoeldnerItem());
                preparedStatement.setInt(8, waffe.getAttacke());
                preparedStatement.setInt(9, waffe.getMagischeAttacke());
                preparedStatement.setString(10, waffe.getClass().getSimpleName().toLowerCase());
                preparedStatement.setString(11, waffe.getIcon());
                preparedStatement.setInt(12, waffe.getGenauigkeit());
                preparedStatement.setInt(13, waffe.getBeweglichkeit());
                preparedStatement.execute();
            }
        }
    }

    private void tabellenErstellen(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
//				statement.execute("DROP TABLE IF EXISTS Charakter;");
//				statement.execute("DROP TABLE IF EXISTS Waffe;");
//				statement.execute("DROP TABLE IF EXISTS Ruestung;");
//				statement.execute("DROP TABLE IF EXISTS Faehigkeit;");
//				statement.execute("DROP TABLE IF EXISTS Accessoire;");
//				statement.execute("DROP TABLE IF EXISTS Party;");
//				statement.execute("DROP TABLE IF EXISTS Statistik;");
//				statement.execute("DROP TABLE IF EXISTS Verbrauchsgegenstand;");
//				statement.execute("DROP TABLE IF EXISTS Material;");
//				statement.execute("DROP TABLE IF EXISTS Speicherstand;");
            // @formatter:off
            statement.execute("CREATE TABLE IF NOT EXISTS  Charakter   ("
                    + "  charakter_ID	            INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "  party_ID    	            INTEGER REFERENCES Party(party_ID) ON DELETE CASCADE,"
                    + "  istHauptCharakter    	    BOOLEAN,"
                    + "  klasseBezeichnung	        TEXT,"
                    + "  name	                    TEXT,"
                    + "  grafischeDarstellung       TEXT,"
                    + "  level	                    INTEGER,"
                    + "  gesundheitsPunkte          INTEGER,"
                    + "  maxGesundheitsPunkte       INTEGER,"
                    + "  manaPunkte                 INTEGER,"
                    + "  maxManaPunkte              INTEGER,"
                    + "  physischeAttacke           INTEGER,"
                    + "  magischeAttacke            INTEGER,"
                    + "  genauigkeit                INTEGER,"
                    + "  verteidigung               INTEGER,"
                    + "  magischeVerteidigung       INTEGER,"
                    + "  resistenz                  INTEGER,"
                    + "  beweglichkeit              INTEGER,"
                    + "  gesundheitsregeneration    INTEGER,"
                    + "  manaRegeneration        	INTEGER,"
                    + "  geschichte				 	TEXT,"
                    + "  erfahrungsPunkte		 	INTEGER,"
                    + "  offeneFaehigkeitspunkte 	INTEGER,"
                    + "  verteilteFaehigkeitspunkte INTEGER,"
                    + "  offeneAttributpunkte       INTEGER"
                    + ");");
            statement.execute("CREATE TABLE IF NOT EXISTS   Waffe   ("
                    + "  charakter_ID     	     INTEGER REFERENCES Charakter(charakter_ID) ON DELETE CASCADE,"
                    + "  party_ID     	         INTEGER REFERENCES Party(party_ID) ON DELETE CASCADE,"
                    + "  waffe_ID     	         INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "  name     	             TEXT,"
                    + "  kaufwert     	         INTEGER,"
                    + "  icon     	     		 TEXT,"
                    + "  verkaufswert     	     INTEGER,"
                    + "  istNichtKaufbar     	 BOOLEAN,"
                    + "  levelAnforderung        INTEGER,"
                    + "  istSoeldnerItem     	 BOOLEAN,"
                    + "  attacke	        	 INTEGER,"
                    + "  magischeAttacke		 INTEGER,"
                    + "  genauigkeit	         INTEGER,"
                    + "  beweglichkeit	         INTEGER,"
                    + "  waffenTyp	             TEXT"
                    + ");");
            statement.execute("CREATE TABLE IF NOT EXISTS   Ruestung   ("
                    + "  charakter_ID     	     INTEGER REFERENCES Charakter(charakter_ID) ON DELETE CASCADE,"
                    + "  party_ID     	         INTEGER REFERENCES Party(party_ID) ON DELETE CASCADE,"
                    + "  ruestung_ID     	     INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "  name     	             TEXT,"
                    + "  icon     	             TEXT,"
                    + "  kaufwert     	         INTEGER,"
                    + "  verkaufswert     	     INTEGER,"
                    + "  istNichtKaufbar     	 BOOLEAN,"
                    + "  levelAnforderung        INTEGER,"
                    + "  istSoeldnerItem     	 BOOLEAN,"
                    + "  magischeVerteidigung	 INTEGER,"
                    + "  verteidigung			 INTEGER,"
                    + "  resistenz				 INTEGER,"
                    + "  maxGesundheitsPunkte	 INTEGER,"
                    + "  maxManaPunkte	         INTEGER,"
                    + "  ruestungsTyp            TEXT"
                    + ");");
            statement.execute("CREATE TABLE IF NOT EXISTS   Faehigkeit   ("
                    + "  charakter_ID     	     INTEGER REFERENCES Charakter(charakter_ID) ON DELETE CASCADE,"
                    + "  faehigkeit_ID     	     INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "  icon     	             TEXT,"
                    + "  name     	             TEXT,"
                    + "  beschreibung     	     TEXT,"
                    + "  manaKosten     	     INTEGER,"
                    + "  level     	 			 INTEGER,"
                    + "  levelAnforderung	     INTEGER,"
                    + "  istFreundlich           BOOLEAN,"
                    + "  effektStaerke           INTEGER,"
                    + "  zielAnzahl     	     INTEGER,"
                    + "  wahrscheinlichkeit  	 DOUBLE,"
                    + "  zielAttribut            TEXT,"
                    + "  faehigkeitsTyp  	     TEXT"
                    + ");");
            statement.execute("CREATE TABLE IF NOT EXISTS   Accessoire   ("
                    + "  charakter_ID     	     INTEGER REFERENCES Charakter(charakter_ID) ON DELETE CASCADE,"
                    + "  party_ID     	         INTEGER REFERENCES Party(party_ID) ON DELETE CASCADE,"
                    + "  accessoire_ID     	     INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "  icon     	             TEXT,"
                    + "  name     	             TEXT,"
                    + "  kaufwert     	         INTEGER,"
                    + "  verkaufswert     	     INTEGER,"
                    + "  istNichtKaufbar     	 BOOLEAN,"
                    + "  maxManaPunkte           INTEGER,"
                    + "  maxGesundheitsPunkte    INTEGER,"
                    + "  levelAnforderung        INTEGER,"
                    + "  istSoeldnerItem     	 BOOLEAN,"
                    + "  gesundheitsRegeneration INTEGER,"
                    + "  manaRegeneration        INTEGER,"
                    + "  beweglichkeit           INTEGER"
                    + ");");
            statement.execute("CREATE TABLE IF NOT EXISTS    Party   ("
                    + "  party_ID    	 		 INTEGER PRIMARY KEY REFERENCES Speicherstand(speicherstand_ID) ON DELETE CASCADE,"
                    + "  hauptCharakter     	 TEXT,"
                    + "  nebenCharakter1	     TEXT,"
                    + "  nebenCharakter2	     TEXT,"
                    + "  nebenCharakter3	     TEXT,"
                    + "  gold	     			 INTEGER"
                    + ");");
            statement.execute("CREATE TABLE IF NOT EXISTS    Statistik   ("
                    + "  statistik_ID    	         INTEGER PRIMARY KEY REFERENCES Speicherstand(speicherstand_ID) ON DELETE CASCADE,"
                    + "  gesamtErwirtschaftetesGold  INTEGER,"
                    + "  durchgefuehrteKaempfe	     INTEGER,"
                    + "  gewonneneKaempfe	     	 INTEGER,"
                    + "  verloreneKaempfe	     	 INTEGER"
                    + ");");
            statement.execute("CREATE TABLE IF NOT EXISTS    Verbrauchsgegenstand   ("
                    + "  party_ID    	 	 INTEGER REFERENCES Party(party_ID)	ON DELETE CASCADE,"
                    + "  name	             TEXT,"
                    + "  kaufwert	         INTEGER,"
                    + "  verkaufswert	     INTEGER,"
                    + "  anzahl	             INTEGER"
                    + ");");
            statement.execute("CREATE TABLE IF NOT EXISTS    Material   ("
                    + "  party_ID    	 	 INTEGER REFERENCES Party(party_ID) ON DELETE CASCADE,"
                    + "  name	             TEXT,"
                    + "  anzahl	             INTEGER"
                    + ");");
            statement.execute("CREATE TABLE IF NOT EXISTS    Speicherstand   ("
                    + "  speicherstand_ID   	 INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "  speicherstand_name   	 TEXT,"
                    + "  schwierigkeitsgrad   	 TEXT,"
                    + "  hardcore  	 			 BOOLEAN,"
                    + "  datum                   DATE"
                    + ");");
            // @formatter:on
        }
    }

}
