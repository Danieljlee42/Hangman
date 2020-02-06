import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class AlphabetButton implements EventHandler<ActionEvent>{
	private Button button;
	private String letter;
	
	public AlphabetButton(String letter) {
		this.letter = letter;
		button = new Button(letter);
		button.setPrefSize(50,50);
		button.setOnAction(this);
		button.setStyle("-fx-background-color: #ffd700;");
	}
	public String getLetter() {
		return letter;
	}
	public void handle(ActionEvent e) {
		button.setDisable(true);
	}
	public void enable() {
		button.setDisable(false);
	}
	public Button button() {
		return button;
	}
}
