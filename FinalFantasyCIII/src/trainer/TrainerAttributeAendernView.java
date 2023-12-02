package trainer;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import view.ViewController;

public class TrainerAttributeAendernView extends VBox {
    private TrainerController trainerController;
    private Label offeneAttributsPunkte;
    private Label lblMaxGesundheit;
    private Button btnMaxGesundheitPlus;
    private Button btnMaxGesundheitMinus;
    private Label lblMaxMana;
    private Button btnMaxManaPlus;
    private Button btnMaxManaMinus;
    private Label lblPhysischeAttacke;
    private Button btnPhysischeAttackePlus;
    private Button btnPhysischeAttackeMinus;
    private Label lblMagischeAttacke;
    private Button btnMagischeAttackePlus;
    private Button btnMagischeAttackeMinus;
    private Label lblGenauigkeit;
    private Button btnGenauigkeitPlus;
    private Button btnGenauigkeitMinus;
    private Label lblVerteidigung;
    private Button btnVerteidigungPlus;
    private Button btnVerteidigungMinus;
    private Label lblMagischeVerteidigung;
    private Button btnMagischeVerteidigungPlus;
    private Button btnMagischeVerteidigungMinus;
    private Label lblResistenz;
    private Button btnResistenzPlus;
    private Button btnResistenzMinus;
    private Label lblBeweglichkeit;
    private Button btnBeweglichkeitPlus;
    private Button btnBeweglichkeitMinus;


    public TrainerAttributeAendernView(TrainerController trainerController) {


        this.trainerController = trainerController;
        Label titel = new Label("Attribute verändern");
        offeneAttributsPunkte = new Label(" Name :" + trainerController.getAktuellerCharakter().getName() + "\nOffene Attributspunkte : " + trainerController.getAktuellerCharakter().getOffeneAttributpunkte());
        //Container MaxGesundheit
        HBox containerMaxGesundheitspunkte = new HBox();
        lblMaxGesundheit = new Label("Max. Gesundheitspunkte\n" + trainerController.getAktuellerCharakter().getMaxGesundheitsPunkte());
        btnMaxGesundheitPlus = new Button("+");
        btnMaxGesundheitPlus.setOnAction(event ->trainerController.attributAendern("maxGesundheit",true));
        btnMaxGesundheitMinus = new Button("-");
        btnMaxGesundheitMinus.setOnAction(event ->trainerController.attributAendern("maxGesundheit",false));
        containerMaxGesundheitspunkte.getChildren().addAll(lblMaxGesundheit, btnMaxGesundheitPlus, btnMaxGesundheitMinus);
        //Container Max ManaPunkte
        HBox containerMaxManaPunkte = new HBox();
        lblMaxMana = new Label("Max. Manapunkte\n" + trainerController.getAktuellerCharakter().getMaxManaPunkte());
        btnMaxManaPlus = new Button("+");
        btnMaxManaPlus.setOnAction(event ->trainerController.attributAendern("maxMana",true));
        btnMaxManaMinus = new Button("-");
        btnMaxManaMinus.setOnAction(event ->trainerController.attributAendern("maxMana",false));
        containerMaxManaPunkte.getChildren().addAll(lblMaxMana, btnMaxManaPlus, btnMaxManaMinus);
        //Container Physische Attacke
        HBox containerPhysischeAttacke = new HBox();
        lblPhysischeAttacke = new Label("Physische Attacke\n" + trainerController.getAktuellerCharakter().getPhysischeAttacke());
        btnPhysischeAttackePlus = new Button("+");
        btnPhysischeAttackePlus.setOnAction(event ->trainerController.attributAendern("physischeAttacke",true));
        btnPhysischeAttackeMinus = new Button("-");
        btnPhysischeAttackeMinus.setOnAction(event ->trainerController.attributAendern("physischeAttacke",false));
        containerPhysischeAttacke.getChildren().addAll(lblPhysischeAttacke, btnPhysischeAttackePlus, btnPhysischeAttackeMinus);
        //Container Magische Attacke
        HBox containerMagischeAttacke = new HBox();
        lblMagischeAttacke = new Label("Magische Attacke\n" + trainerController.getAktuellerCharakter().getMagischeAttacke());
        btnMagischeAttackePlus = new Button("+");
        btnMagischeAttackePlus.setOnAction(event ->trainerController.attributAendern("MagischeAttacke",true));
        btnMagischeAttackeMinus = new Button("-");
        btnMagischeAttackeMinus.setOnAction(event ->trainerController.attributAendern("MagischeAttacke",false));
        containerMagischeAttacke.getChildren().addAll(lblMagischeAttacke, btnMagischeAttackePlus, btnMagischeAttackeMinus);
        //Container Genauigkeit
        HBox containerGenauigkeit = new HBox();
        lblGenauigkeit = new Label("Genauigkeit\n" + trainerController.getAktuellerCharakter().getGenauigkeit());
        btnGenauigkeitPlus = new Button("+");
        btnGenauigkeitPlus.setOnAction(event ->trainerController.attributAendern("genauigkeit",true));
        btnGenauigkeitMinus = new Button("-");
        btnGenauigkeitMinus.setOnAction(event ->trainerController.attributAendern("genauigkeit",false));
        containerGenauigkeit.getChildren().addAll(lblGenauigkeit, btnGenauigkeitPlus, btnGenauigkeitMinus);
        //Container Verteidigung
        HBox containerVerteidigung = new HBox();
        lblVerteidigung = new Label("Verteidigung\n" + trainerController.getAktuellerCharakter().getVerteidigung());
        btnVerteidigungPlus = new Button("+");
        btnVerteidigungPlus.setOnAction(event ->trainerController.attributAendern("verteidigung",true));
        btnVerteidigungMinus = new Button("-");
        btnVerteidigungMinus.setOnAction(event ->trainerController.attributAendern("verteidigung",false));
        containerVerteidigung.getChildren().addAll(lblVerteidigung, btnVerteidigungPlus, btnVerteidigungMinus);
        //Container Magische Verteidigung
        HBox containerMagischeVerteidigung = new HBox();
        lblMagischeVerteidigung = new Label("Magische Verteidigung\n" + trainerController.getAktuellerCharakter().getMagischeVerteidigung());
        btnMagischeVerteidigungPlus = new Button("+");
        btnMagischeVerteidigungPlus.setOnAction(event ->trainerController.attributAendern("magischeVerteidigung",true));
        btnMagischeVerteidigungMinus = new Button("-");
        btnMagischeVerteidigungMinus.setOnAction(event ->trainerController.attributAendern("magischeVerteidigung",false));
        containerMagischeVerteidigung.getChildren().addAll(lblMagischeVerteidigung, btnMagischeVerteidigungPlus, btnMagischeVerteidigungMinus);
        //Container Resistenz
        HBox containerResistenz = new HBox();
        lblResistenz = new Label("Resistenz\n" + trainerController.getAktuellerCharakter().getResistenz());
        btnResistenzPlus = new Button("+");
        btnResistenzPlus.setOnAction(event ->trainerController.attributAendern("resistenz",true));
        btnResistenzMinus = new Button("-");
        btnResistenzMinus.setOnAction(event ->trainerController.attributAendern("resistenz",false));
        containerResistenz.getChildren().addAll(lblResistenz, btnResistenzPlus, btnResistenzMinus);
        //Container Beweglichkeit
        HBox containerBeweglichkeit = new HBox();
        lblBeweglichkeit = new Label("Beweglichkeit\n" + trainerController.getAktuellerCharakter().getBeweglichkeit());
        btnBeweglichkeitPlus = new Button("+");
        btnBeweglichkeitPlus.setOnAction(event ->trainerController.attributAendern("beweglichkeit",true));
        btnBeweglichkeitMinus = new Button("-");
        btnBeweglichkeitMinus.setOnAction(event ->trainerController.attributAendern("beweglichkeit",false));
        containerBeweglichkeit.getChildren().addAll(lblBeweglichkeit, btnBeweglichkeitPlus, btnBeweglichkeitMinus);

        //styleKlassenZuweisen
        titel.getStyleClass().add("trainerAttributeTitel");
        //Container
        containerMaxGesundheitspunkte.getStyleClass().add("trainerAttributeContainer");
        containerMaxManaPunkte.getStyleClass().add("trainerAttributeContainer");
        containerPhysischeAttacke.getStyleClass().add("trainerAttributeContainer");
        containerMagischeAttacke.getStyleClass().add("trainerAttributeContainer");
        containerGenauigkeit.getStyleClass().add("trainerAttributeContainer");
        containerVerteidigung.getStyleClass().add("trainerAttributeContainer");
        containerMagischeVerteidigung.getStyleClass().add("trainerAttributeContainer");
        containerResistenz.getStyleClass().add("trainerAttributeContainer");
        containerBeweglichkeit.getStyleClass().add("trainerAttributeContainer");
        //Buttons
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
        //Label
        offeneAttributsPunkte.getStyleClass().add("trainerAttributeLabel");
        lblMaxGesundheit.getStyleClass().add("trainerAttributeLabel");
        lblMaxMana.getStyleClass().add("trainerAttributeLabel");
        lblPhysischeAttacke.getStyleClass().add("trainerAttributeLabel");
        lblMagischeAttacke.getStyleClass().add("trainerAttributeLabel");
        lblGenauigkeit.getStyleClass().add("trainerAttributeLabel");
        lblVerteidigung.getStyleClass().add("trainerAttributeLabel");
        lblMagischeVerteidigung.getStyleClass().add("trainerAttributeLabel");
        lblResistenz.getStyleClass().add("trainerAttributeLabel");
        lblBeweglichkeit.getStyleClass().add("trainerAttributeLabel");
        //Die Komponenten der VBox hinzufügen
        this.getChildren().addAll(titel, offeneAttributsPunkte, containerMaxGesundheitspunkte, containerMaxManaPunkte, containerPhysischeAttacke, containerMagischeAttacke, containerGenauigkeit, containerVerteidigung, containerMagischeVerteidigung, containerResistenz, containerBeweglichkeit);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(15.0);
        this.setMaxWidth(1536.0);
        this.setBackground(TrainerController.setzeTrainerHintergrund());


    }
    public void anzeigeVorbereiten() {
        int offeneAttributpunkte = trainerController.getAktuellerCharakter().getOffeneAttributpunkte();
        offeneAttributsPunkte.setText(" Name :" + trainerController.getAktuellerCharakter().getName() + "\nOffene Attributspunkte : " + offeneAttributpunkte);
        lblMaxGesundheit.setText("Max. Gesundheitspunkte\n" + trainerController.getAktuellerCharakter().getMaxGesundheitsPunkte());
        lblMaxMana.setText("Max. Manapunkte\n" + trainerController.getAktuellerCharakter().getMaxManaPunkte());
        lblPhysischeAttacke.setText("Physische Attacke\n" + trainerController.getAktuellerCharakter().getPhysischeAttacke());
        lblMagischeAttacke.setText("Magische Attacke\n" + trainerController.getAktuellerCharakter().getMagischeAttacke());
        lblGenauigkeit.setText("Genauigkeit\n" + trainerController.getAktuellerCharakter().getGenauigkeit());
        lblVerteidigung.setText("Verteidigung\n" + trainerController.getAktuellerCharakter().getVerteidigung());
        lblMagischeVerteidigung.setText("Magische Verteidigung\n" + trainerController.getAktuellerCharakter().getMagischeVerteidigung());
        lblResistenz.setText("Resistenz\n" + trainerController.getAktuellerCharakter().getResistenz());
        lblBeweglichkeit.setText("Beweglichkeit\n" + trainerController.getAktuellerCharakter().getBeweglichkeit());
        // Plus Knöpfe
        btnMaxGesundheitPlus.setDisable(offeneAttributpunkte <=0);
        btnMaxManaPlus.setDisable(offeneAttributpunkte <=0);
        btnPhysischeAttackePlus.setDisable(offeneAttributpunkte <=0);
        btnMagischeAttackePlus.setDisable(offeneAttributpunkte <=0);
        btnMagischeAttackePlus.setDisable(offeneAttributpunkte <=0);
        btnGenauigkeitPlus.setDisable(offeneAttributpunkte <=0);
        btnVerteidigungPlus.setDisable(offeneAttributpunkte <=0);
        btnMagischeVerteidigungPlus.setDisable(offeneAttributpunkte <=0);
        btnResistenzPlus.setDisable(offeneAttributpunkte <=0);
        btnBeweglichkeitPlus.setDisable(offeneAttributpunkte <=0);
        //Minus Knöpfe
        btnMaxGesundheitMinus.setDisable(trainerController.getAktuellerCharakter().getMaxGesundheitsPunkte() <= 1);
        btnMaxManaMinus.setDisable(trainerController.getAktuellerCharakter().getMaxManaPunkte() <= 0);
        btnPhysischeAttackeMinus.setDisable(trainerController.getAktuellerCharakter().getPhysischeAttacke() <= 0);
        btnMagischeAttackeMinus.setDisable(trainerController.getAktuellerCharakter().getMagischeAttacke() <= 0);
        btnGenauigkeitMinus.setDisable(trainerController.getAktuellerCharakter().getGenauigkeit() <= 0);
        btnVerteidigungMinus.setDisable(trainerController.getAktuellerCharakter().getVerteidigung() <= 0);
        btnMagischeVerteidigungMinus.setDisable(trainerController.getAktuellerCharakter().getMagischeVerteidigung() <= 0);
        btnResistenzMinus.setDisable(trainerController.getAktuellerCharakter().getResistenz() <= 0);
        btnBeweglichkeitMinus.setDisable(trainerController.getAktuellerCharakter().getBeweglichkeit() <= 0);

    }

}
