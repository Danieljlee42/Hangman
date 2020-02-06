import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class HangmanBox{
	private int hangmanCounter = 0;
	private ImageView hangmanImg;
	private FlowPane hangman;
	
	public HangmanBox() {
		hangman= new FlowPane();
		hangman.setPrefWrapLength(300);
		hangmanImg = new ImageView(new Image("/hangman0.png"));	
		hangman.getChildren().add(hangmanImg);
	}
	public void updateWrong() {
		hangmanImg.setImage(new Image("/hangman"+ (++hangmanCounter) +".png"));
	}
	public void updateLives(Game game) {
		hangmanCounter = 9 - game.lives;
		hangmanImg.setImage(new Image("/hangman"+ (++hangmanCounter) +".png"));
	}
	public FlowPane pane() {
		return hangman;
	}
}
