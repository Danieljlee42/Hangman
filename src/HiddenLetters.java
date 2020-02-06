import javafx.geometry.Insets;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class HiddenLetters{
	private Text[] letters; 
	private FlowPane blackBoxes;
	private String answer;
	
	public HiddenLetters(String answer) {
		this.answer = answer;
		letters = new Text[answer.length()];
		for(int i = 0;i<answer.length();i++) {
			Text tsub = new Text(answer.charAt(i)+"");
			tsub.setFont(Font.font("Alegreya",19));
			tsub.setFill(Color.BLACK);
			letters[i]=tsub;
		}
		blackBoxes = new FlowPane(2,10);
		blackBoxes.setPadding(new Insets(15, 12, 15, 12));
		for(int i = 0; i < answer.length();i++) {
			Rectangle r = new Rectangle();
			r.setWidth(30);
			r.setHeight(30);
			r.setFill(Color.BLACK);
			StackPane a = new StackPane();
			a.getChildren().addAll(r,letters[i]);
			blackBoxes.getChildren().addAll(a); 
		}
	}
	public void updateLost() {
		for (Text t:letters) {
			if(t.getFill() == Color.BLACK) {
				t.setFill(Color.ORANGERED);
			}
		}
	}
	public void updateLetter(char guess, Game game) {
		int i=0;
		while(answer.indexOf(guess, i)!=-1) {
			i = answer.indexOf(guess, i);
			letters[i].setFill(Color.WHITE);
			game.shown[i++]=guess;
		}
	}
	public void updateWord(Game game) {
		for (Character guess: game.guessed) {
			updateLetter(guess,game);
		}
	}
	public FlowPane blackBoxes() {
		return blackBoxes;
	}
}