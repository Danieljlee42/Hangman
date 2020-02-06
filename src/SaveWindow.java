import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SaveWindow{
	private Text text;
	private Button yesBtn;
	private Button noBtn;
	private Button cancelBtn;
	private HBox hbox;
	private VBox vbox;
	private Scene scene;
	private Stage stage;
		
	public SaveWindow(String titleName, String textName) {
		text = new Text(textName);
		yesBtn = new Button("Yes");
		yesBtn.setOnMouseReleased(e-> stage.close());
		noBtn = new Button("No");
		noBtn.setOnMouseReleased(e-> stage.close());
		cancelBtn = new Button("Cancel");
		cancelBtn.setOnMouseReleased(e-> stage.close());
		hbox = new HBox(4, yesBtn,noBtn,cancelBtn);
		hbox.setAlignment(Pos.CENTER);
		vbox = new VBox(text,hbox);
		vbox.setAlignment(Pos.CENTER);
		scene = new Scene(vbox,300,200);
		stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(titleName); 
		stage.setScene(scene);	
	}
	public Stage stage() {
		return stage;
	}
	public Button yesBtn() {
		return yesBtn;
	}
	public Button noBtn() {
		return noBtn;
	}
	public Button cancelBtn() {
		return cancelBtn;
	}
}