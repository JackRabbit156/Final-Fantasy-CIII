package de.bundeswehr.auf.final_fantasy.menu.trainer.view;

import de.bundeswehr.auf.final_fantasy.charakter.model.AttributCharakter;
import de.bundeswehr.auf.final_fantasy.charakter.model.SpielerCharakter;
import de.bundeswehr.auf.final_fantasy.hilfsklassen.Attribute;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import de.bundeswehr.auf.final_fantasy.menu.trainer.TrainerController;

/**
 * The type Trainer attribute aendern de.bundeswehr.auf.final_fantasy.menu.overlay.view.
 */
public class AttributeAendernView extends BorderPane {

    private final TrainerController trainerController;
    private final Label offeneAttributsPunkte;
    private final Label lblMaxGesundheit;
    private final Label lblMaxGesundheitWert;
    private final Button btnMaxGesundheitPlus;
    private final Button btnMaxGesundheitMinus;
    private final Label lblMaxMana;
    private final Label lblMaxManaWert;
    private final Button btnMaxManaPlus;
    private final Button btnMaxManaMinus;
    private final Label lblPhysischeAttacke;
    private final Label lblPhysischeAttackeWert;
    private final Button btnPhysischeAttackePlus;
    private final Button btnPhysischeAttackeMinus;
    private final Label lblMagischeAttacke;
    private final Label lblMagischeAttackeWert;
    private final Button btnMagischeAttackePlus;
    private final Button btnMagischeAttackeMinus;
    private final Label lblGenauigkeit;
    private final Label lblGenauigkeitWert;
    private final Button btnGenauigkeitPlus;
    private final Button btnGenauigkeitMinus;
    private final Label lblVerteidigung;
    private final Label lblVerteidigungWert;
    private final Button btnVerteidigungPlus;
    private final Button btnVerteidigungMinus;
    private final Label lblMagischeVerteidigung;
    private final Label lblMagischeVerteidigungWert;
    private final Button btnMagischeVerteidigungPlus;
    private final Button btnMagischeVerteidigungMinus;
    private final Label lblResistenz;
    private final Label lblResistenzWert;
    private final Button btnResistenzPlus;
    private final Button btnResistenzMinus;
    private final Label lblBeweglichkeit;
    private final Label lblBeweglichkeitWert;
    private final Button btnBeweglichkeitPlus;
    private final Button btnBeweglichkeitMinus;

    /**
     * Instantiates a new Trainer attribute aendern de.bundeswehr.auf.final_fantasy.menu.overlay.view.
     *
     * @param trainerController the de.bundeswehr.auf.final_fantasy.menu.trainer de.bundeswehr.auf.final_fantasy.controller
     */
    public AttributeAendernView(TrainerController trainerController) {
        this.trainerController = trainerController;

        Label lbltitel = new Label("Attribute verändern");
        lbltitel.setAlignment(Pos.CENTER);
        VBox titel = new VBox(lbltitel);
        titel.setAlignment(Pos.CENTER);
        titel.getStyleClass().add("trainerTitel");
        this.setTop(titel);

        offeneAttributsPunkte = new Label();
        offeneAttributsPunkte.setAlignment(Pos.CENTER);

        Image imgPlus = new Image("icons/plus.png");
        Image imgMinus = new Image("icons/minus.png");

        lblMaxGesundheit = new Label();
        lblMaxGesundheit.setAlignment(Pos.CENTER);
        lblMaxGesundheitWert = new Label();
        lblMaxGesundheitWert.setAlignment(Pos.CENTER);

        btnMaxGesundheitPlus = new Button();
        btnMaxGesundheitPlus.setGraphic(new ImageView(imgPlus));
        btnMaxGesundheitPlus.setOnAction(event -> trainerController.attributAendern(Attribute.MAX_GP, true));

        btnMaxGesundheitMinus = new Button();
        btnMaxGesundheitMinus.setGraphic(new ImageView(imgMinus));
        btnMaxGesundheitMinus.setOnAction(event -> trainerController.attributAendern(Attribute.MAX_GP, false));

        HBox containerMaxGesundheitspunktePlusMinus = new HBox(btnMaxGesundheitMinus, btnMaxGesundheitPlus);
        HBox containerMaxGesundheitspunkte = new HBox(lblMaxGesundheit, lblMaxGesundheitWert, containerMaxGesundheitspunktePlusMinus);
        containerMaxGesundheitspunkte.setSpacing(50.0);

        lblMaxMana = new Label();
        lblMaxMana.setAlignment(Pos.CENTER);
        lblMaxManaWert = new Label();
        lblMaxManaWert.setAlignment(Pos.CENTER);

        btnMaxManaPlus = new Button();
        btnMaxManaPlus.setGraphic(new ImageView(imgPlus));
        btnMaxManaPlus.setOnAction(event -> trainerController.attributAendern(Attribute.MAX_MP, true));

        btnMaxManaMinus = new Button();
        btnMaxManaMinus.setGraphic(new ImageView(imgMinus));
        btnMaxManaMinus.setOnAction(event -> trainerController.attributAendern(Attribute.MAX_MP, false));

        HBox containerMaxManaPunktePlusMinus = new HBox(btnMaxManaMinus, btnMaxManaPlus);
        HBox containerMaxManaPunkte = new HBox(lblMaxMana, lblMaxManaWert, containerMaxManaPunktePlusMinus);
        containerMaxManaPunkte.setSpacing(50);

        lblPhysischeAttacke = new Label();
        lblPhysischeAttacke.setAlignment(Pos.CENTER);

        lblPhysischeAttackeWert = new Label();
        lblPhysischeAttackeWert.setAlignment(Pos.CENTER);

        btnPhysischeAttackePlus = new Button();
        btnPhysischeAttackePlus.setGraphic(new ImageView(imgPlus));
        btnPhysischeAttackePlus.setOnAction(event -> trainerController.attributAendern(Attribute.A, true));

        btnPhysischeAttackeMinus = new Button();
        btnPhysischeAttackeMinus.setGraphic(new ImageView(imgMinus));
        btnPhysischeAttackeMinus.setOnAction(event -> trainerController.attributAendern(Attribute.A, false));

        HBox containerPhysischeAttackePlusMinus = new HBox(btnPhysischeAttackeMinus, btnPhysischeAttackePlus);
        HBox containerPhysischeAttacke = new HBox(lblPhysischeAttacke, lblPhysischeAttackeWert, containerPhysischeAttackePlusMinus);
        containerPhysischeAttacke.setSpacing(50);

        lblMagischeAttacke = new Label();
        lblMagischeAttackeWert = new Label();

        btnMagischeAttackePlus = new Button();
        btnMagischeAttackePlus.setGraphic(new ImageView(imgPlus));
        btnMagischeAttackePlus.setOnAction(event -> trainerController.attributAendern(Attribute.MA, true));

        btnMagischeAttackeMinus = new Button();
        btnMagischeAttackeMinus.setGraphic(new ImageView(imgMinus));
        btnMagischeAttackeMinus.setOnAction(event -> trainerController.attributAendern(Attribute.MA, false));

        HBox containerMagischeAttackePlusMinus = new HBox(btnMagischeAttackeMinus, btnMagischeAttackePlus);
        HBox containerMagischeAttacke = new HBox(lblMagischeAttacke, lblMagischeAttackeWert, containerMagischeAttackePlusMinus);
        containerMagischeAttacke.setSpacing(50);

        lblGenauigkeit = new Label();
        lblGenauigkeitWert = new Label();

        btnGenauigkeitPlus = new Button();
        btnGenauigkeitPlus.setGraphic(new ImageView(imgPlus));
        btnGenauigkeitPlus.setOnAction(event -> trainerController.attributAendern(Attribute.G, true));

        btnGenauigkeitMinus = new Button();
        btnGenauigkeitMinus.setGraphic(new ImageView(imgMinus));
        btnGenauigkeitMinus.setOnAction(event -> trainerController.attributAendern(Attribute.G, false));

        HBox containerGenauigkeitPlusMinus = new HBox(btnGenauigkeitMinus, btnGenauigkeitPlus);
        HBox containerGenauigkeit = new HBox(lblGenauigkeit, lblGenauigkeitWert, containerGenauigkeitPlusMinus);
        containerGenauigkeit.setSpacing(50);

        lblVerteidigung = new Label();
        lblVerteidigung.setAlignment(Pos.CENTER);

        lblVerteidigungWert = new Label();
        lblVerteidigungWert.setAlignment(Pos.CENTER);

        btnVerteidigungPlus = new Button();
        btnVerteidigungPlus.setGraphic(new ImageView(imgPlus));
        btnVerteidigungPlus.setOnAction(event -> trainerController.attributAendern(Attribute.V, true));

        btnVerteidigungMinus = new Button();
        btnVerteidigungMinus.setGraphic(new ImageView(imgMinus));
        btnVerteidigungMinus.setOnAction(event -> trainerController.attributAendern(Attribute.V, false));

        HBox containerVerteidigungPlusMinus = new HBox(btnVerteidigungMinus, btnVerteidigungPlus);
        HBox containerVerteidigung = new HBox(lblVerteidigung, lblVerteidigungWert, containerVerteidigungPlusMinus);
        containerVerteidigung.setSpacing(50);

        lblMagischeVerteidigung = new Label();
        lblMagischeVerteidigung.setAlignment(Pos.CENTER);

        lblMagischeVerteidigungWert = new Label();
        lblMagischeVerteidigungWert.setAlignment(Pos.CENTER);

        btnMagischeVerteidigungPlus = new Button();
        btnMagischeVerteidigungPlus.setGraphic(new ImageView(imgPlus));
        btnMagischeVerteidigungPlus.setOnAction(event -> trainerController.attributAendern(Attribute.MV, true));

        btnMagischeVerteidigungMinus = new Button();
        btnMagischeVerteidigungMinus.setGraphic(new ImageView(imgMinus));
        btnMagischeVerteidigungMinus.setOnAction(event -> trainerController.attributAendern(Attribute.MV, false));

        HBox containerMagischeVerteidigungPlusMinus = new HBox(btnMagischeVerteidigungMinus, btnMagischeVerteidigungPlus);
        HBox containerMagischeVerteidigung = new HBox(lblMagischeVerteidigung, lblMagischeVerteidigungWert, containerMagischeVerteidigungPlusMinus);
        containerMagischeVerteidigung.setSpacing(50);

        lblResistenz = new Label();
        lblResistenz.setAlignment(Pos.CENTER);

        lblResistenzWert = new Label();
        lblResistenzWert.setAlignment(Pos.CENTER);

        btnResistenzPlus = new Button();
        btnResistenzPlus.setGraphic(new ImageView(imgPlus));
        btnResistenzPlus.setOnAction(event -> trainerController.attributAendern(Attribute.R, true));

        btnResistenzMinus = new Button();
        btnResistenzMinus.setGraphic(new ImageView(imgMinus));
        btnResistenzMinus.setOnAction(event -> trainerController.attributAendern(Attribute.R, false));

        HBox containerResistenzPlusMinus = new HBox(btnResistenzMinus, btnResistenzPlus);
        HBox containerResistenz = new HBox(lblResistenz, lblResistenzWert, containerResistenzPlusMinus);
        containerResistenz.setSpacing(50);

        lblBeweglichkeit = new Label();
        lblBeweglichkeit.setAlignment(Pos.CENTER);

        lblBeweglichkeitWert = new Label();
        lblBeweglichkeitWert.setAlignment(Pos.CENTER);

        btnBeweglichkeitPlus = new Button();
        btnBeweglichkeitPlus.setGraphic(new ImageView(imgPlus));
        btnBeweglichkeitPlus.setOnAction(event -> trainerController.attributAendern(Attribute.B, true));

        btnBeweglichkeitMinus = new Button();
        btnBeweglichkeitMinus.setGraphic(new ImageView(imgMinus));
        btnBeweglichkeitMinus.setOnAction(event -> trainerController.attributAendern(Attribute.B, false));

        HBox containerBeweglichkeitPlusMinus = new HBox(btnBeweglichkeitMinus, btnBeweglichkeitPlus);
        HBox containerBeweglichkeit = new HBox(lblBeweglichkeit, lblBeweglichkeitWert, containerBeweglichkeitPlusMinus);
        containerBeweglichkeit.setSpacing(50);

        containerMaxGesundheitspunkte.getStyleClass().add("trainerAttributeContainer");
        containerMaxManaPunkte.getStyleClass().add("trainerAttributeContainer");
        containerPhysischeAttacke.getStyleClass().add("trainerAttributeContainer");
        containerMagischeAttacke.getStyleClass().add("trainerAttributeContainer");
        containerGenauigkeit.getStyleClass().add("trainerAttributeContainer");
        containerVerteidigung.getStyleClass().add("trainerAttributeContainer");
        containerMagischeVerteidigung.getStyleClass().add("trainerAttributeContainer");
        containerResistenz.getStyleClass().add("trainerAttributeContainer");
        containerBeweglichkeit.getStyleClass().add("trainerAttributeContainer");
        containerMaxGesundheitspunktePlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerMaxManaPunktePlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerPhysischeAttackePlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerMagischeAttackePlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerGenauigkeitPlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerVerteidigungPlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerMagischeVerteidigungPlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerResistenzPlusMinus.getStyleClass().add("trainerAttributeContainer");
        containerBeweglichkeitPlusMinus.getStyleClass().add("trainerAttributeContainer");

        btnMaxGesundheitPlus.getStyleClass().add("trainerAttributeButton");
        btnMaxManaPlus.getStyleClass().add("trainerAttributeButton");
        btnPhysischeAttackePlus.getStyleClass().add("trainerAttributeButton");
        btnMagischeAttackePlus.getStyleClass().add("trainerAttributeButton");
        btnMagischeAttackePlus.getStyleClass().add("trainerAttributeButton");
        btnGenauigkeitPlus.getStyleClass().add("trainerAttributeButton");
        btnVerteidigungPlus.getStyleClass().add("trainerAttributeButton");
        btnMagischeVerteidigungPlus.getStyleClass().add("trainerAttributeButton");
        btnResistenzPlus.getStyleClass().add("trainerAttributeButton");
        btnBeweglichkeitPlus.getStyleClass().add("trainerAttributeButton");
        btnMaxGesundheitMinus.getStyleClass().add("trainerAttributeButton");
        btnMaxManaMinus.getStyleClass().add("trainerAttributeButton");
        btnPhysischeAttackeMinus.getStyleClass().add("trainerAttributeButton");
        btnMagischeAttackeMinus.getStyleClass().add("trainerAttributeButton");
        btnGenauigkeitMinus.getStyleClass().add("trainerAttributeButton");
        btnVerteidigungMinus.getStyleClass().add("trainerAttributeButton");
        btnMagischeVerteidigungMinus.getStyleClass().add("trainerAttributeButton");
        btnResistenzMinus.getStyleClass().add("trainerAttributeButton");
        btnBeweglichkeitMinus.getStyleClass().add("trainerAttributeButton");

        offeneAttributsPunkte.getStyleClass().add("trainerAttributeLabel");
        lblMaxGesundheit.getStyleClass().add("trainerAttributeLabel");
        lblMaxGesundheitWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblMaxMana.getStyleClass().add("trainerAttributeLabel");
        lblMaxManaWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblPhysischeAttacke.getStyleClass().add("trainerAttributeLabel");
        lblPhysischeAttackeWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblMagischeAttacke.getStyleClass().add("trainerAttributeLabel");
        lblMagischeAttackeWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblGenauigkeit.getStyleClass().add("trainerAttributeLabel");
        lblGenauigkeitWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblVerteidigung.getStyleClass().add("trainerAttributeLabel");
        lblVerteidigungWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblMagischeVerteidigung.getStyleClass().add("trainerAttributeLabel");
        lblMagischeVerteidigungWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblResistenz.getStyleClass().add("trainerAttributeLabel");
        lblResistenzWert.getStyleClass().add("trainerAttributeLabelWerte");
        lblBeweglichkeit.getStyleClass().add("trainerAttributeLabel");
        lblBeweglichkeitWert.getStyleClass().add("trainerAttributeLabelWerte");

        VBox center = new VBox();
        center.getChildren().addAll(offeneAttributsPunkte, containerMaxGesundheitspunkte,
                containerMaxManaPunkte, containerPhysischeAttacke, containerMagischeAttacke, containerGenauigkeit,
                containerVerteidigung, containerMagischeVerteidigung, containerResistenz, containerBeweglichkeit);
        center.setAlignment(Pos.CENTER);
        center.setSpacing(10.0);
        this.setCenter(center);

        this.setMaxWidth(1536.0);
        this.getStyleClass().add("trainerStyle");
        this.setBackground(TrainerController.setzeTrainerHintergrund());
    }

    /**
     * @author Thomas Maass
     * @since 05.12.2023
     * Anzeige vorbereiten.
     * Wegen der Aktualisierung im menue
     */
    public void anzeigeVorbereiten() {
        SpielerCharakter aktuellerCharakter = trainerController.getAktuellerCharakter();
        AttributCharakter attribute = aktuellerCharakter.getAttribute();
        int offeneAttributpunkte = aktuellerCharakter.getOffeneAttributpunkte();

        offeneAttributsPunkte.setText("Name: " + aktuellerCharakter.getName() + "\n" +
                "Offene Attributspunkte: " + offeneAttributpunkte);

        lblMaxGesundheit.setText("Max. Gesundheitspunkte");
        lblMaxGesundheitWert.setText(String.valueOf(attribute.getMaxGesundheitsPunkte()));

        lblMaxMana.setText("Max. Manapunkte");
        lblMaxManaWert.setText(String.valueOf(attribute.getMaxManaPunkte()));

        lblPhysischeAttacke.setText("Physische Attacke");
        lblPhysischeAttackeWert.setText(String.valueOf(attribute.getPhysischeAttacke()));

        lblMagischeAttacke.setText("Magische Attacke");
        lblMagischeAttackeWert.setText(String.valueOf(attribute.getMagischeAttacke()));

        lblGenauigkeit.setText("Genauigkeit");
        lblGenauigkeitWert.setText(String.valueOf(attribute.getGenauigkeit()));

        lblVerteidigung.setText("Verteidigung");
        lblVerteidigungWert.setText(String.valueOf(attribute.getVerteidigung()));

        lblMagischeVerteidigung.setText("Magische Verteidigung");
        lblMagischeVerteidigungWert.setText(String.valueOf(attribute.getMagischeVerteidigung()));

        lblResistenz.setText("Resistenz");
        lblResistenzWert.setText(String.valueOf(attribute.getResistenz()));

        lblBeweglichkeit.setText("Beweglichkeit");
        lblBeweglichkeitWert.setText(String.valueOf(attribute.getBeweglichkeit()));

        btnMaxGesundheitPlus.setDisable(offeneAttributpunkte <= 0);
        btnMaxManaPlus.setDisable(offeneAttributpunkte <= 0);
        btnPhysischeAttackePlus.setDisable(offeneAttributpunkte <= 0);
        btnMagischeAttackePlus.setDisable(offeneAttributpunkte <= 0);
        btnMagischeAttackePlus.setDisable(offeneAttributpunkte <= 0);
        btnGenauigkeitPlus.setDisable(offeneAttributpunkte <= 0);
        btnVerteidigungPlus.setDisable(offeneAttributpunkte <= 0);
        btnMagischeVerteidigungPlus.setDisable(offeneAttributpunkte <= 0);
        btnResistenzPlus.setDisable(offeneAttributpunkte <= 0);
        btnBeweglichkeitPlus.setDisable(offeneAttributpunkte <= 0);

        btnMaxGesundheitMinus.setDisable(attribute.getMaxGesundheitsPunkte() <= 1);
        btnMaxManaMinus.setDisable(attribute.getMaxManaPunkte() <= 0);
        btnPhysischeAttackeMinus.setDisable(attribute.getPhysischeAttacke() <= 0);
        btnMagischeAttackeMinus.setDisable(attribute.getMagischeAttacke() <= 0);
        btnGenauigkeitMinus.setDisable(attribute.getGenauigkeit() <= 0);
        btnVerteidigungMinus.setDisable(attribute.getVerteidigung() <= 0);
        btnMagischeVerteidigungMinus.setDisable(attribute.getMagischeVerteidigung() <= 0);
        btnResistenzMinus.setDisable(attribute.getResistenz() <= 0);
        btnBeweglichkeitMinus.setDisable(attribute.getBeweglichkeit() <= 0);
    }

}
