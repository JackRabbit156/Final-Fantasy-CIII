package hauptmenu.speicherstand;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.VBox;

public class SpeicherstandLadenView extends VBox {

	public SpeicherstandLadenView() {

		this.setBackground(new Background(new BackgroundImage(new Image("hintergruende/hauptmenue.jpg"),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
				new BackgroundSize(1920, 1080, false, false, false, false))));

		this.getChildren().addAll();
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20.0);

	}
}