import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExitWindow{
	private Text text;
	private Button button;
	private VBox vbox;
	private Scene scene;
	private Stage stage;
	
	public ExitWindow(String titleName, String textName) {
		text = new Text(textName);
		button = new Button("CLOSE");
		button.setOnMouseReleased(e-> stage.close());
		vbox = new VBox(text,button);
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
	public Button button() {
		return button;
	}
}
