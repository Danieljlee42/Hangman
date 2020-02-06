import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Root{
	private Game game;
	private HiddenLetters hiddenLetters; 
	private HangmanBox hangmanBox = new HangmanBox();
	private Text guessRemained;
	private AlphabetButton[] alphabet = new AlphabetButton[26];
	private VBox rightCombine = new VBox();
	private BorderPane middleCombine = new BorderPane();
	
	public Root(ToolBar toolBar) {
		game = new Game(Game.randomWord("words.txt"));
		hiddenLetters = new HiddenLetters(game.answer); 
		guessRemained = new Text("Remaining Guesses: "+game.lives);
		guessRemained.setFont(Font.font("Alegreya",22));
		guessRemained.setFill(Color.BLACK);
		for(int i = 0; i < 26; i++) {
			AlphabetButton sub = new AlphabetButton(""+(char)('a'+i));
			sub.button().setOnMouseReleased(e->updateGame(game, sub, toolBar, hiddenLetters, hangmanBox, guessRemained));
			alphabet[i] = sub;
		}
		FlowPane alphabetFlow = new FlowPane(4,7);
		alphabetFlow.setPadding(new Insets(10, 10, 10, 10));
		alphabetFlow.setStyle("-fx-background-color: #4682b4;");
		for(int i = 0; i < 26; i++) {
			alphabetFlow.getChildren().add(alphabet[i].button());
		}
		rightCombine.getChildren().addAll(guessRemained,hiddenLetters.blackBoxes(),alphabetFlow);
		middleCombine.setLeft(hangmanBox.pane());
		middleCombine.setRight(rightCombine);
	}
	public Root(ToolBar toolBar, Game game) {
		this.game = game;
		hiddenLetters = new HiddenLetters(game.answer); 
		hiddenLetters.updateWord(game);
		guessRemained = new Text("Remaining Guesses: "+game.lives);
		guessRemained.setFont(Font.font("Alegreya",22));
		guessRemained.setFill(Color.BLACK);
		hangmanBox.updateLives(game);
		for(int i = 0; i < 26; i++) {
			AlphabetButton sub = new AlphabetButton(""+(char)('a'+i));
			sub.button().setOnMouseReleased(e->updateGame(game, sub, toolBar, hiddenLetters, hangmanBox, guessRemained));
			alphabet[i] = sub;
		}
		for(Character c: game.guessed) {
			alphabet[(int) (c)-97].button().setDisable(true);
		}
		
		FlowPane alphabetFlow = new FlowPane(4,7);
		alphabetFlow.setPadding(new Insets(10, 10, 10, 10));
		alphabetFlow.setStyle("-fx-background-color: #4682b4;");
		for(int i = 0; i < 26; i++) {
			alphabetFlow.getChildren().add(alphabet[i].button());
		}
		rightCombine.getChildren().addAll(guessRemained,hiddenLetters.blackBoxes(),alphabetFlow);
		middleCombine.setLeft(hangmanBox.pane());
		middleCombine.setRight(rightCombine);
	}
	public void updateGame(Game game, AlphabetButton sub, ToolBar toolBar, HiddenLetters hiddenLetters, HangmanBox hangmanBox, Text guessRemained) {
		toolBar.saveBtn().setDisable(false);
		toolBar.toolBar().requestFocus();
		game.gameState = 1;
		game.guessed.add(sub.getLetter().charAt(0));
		if(game.letterChecker(sub.getLetter().charAt(0))) {
			hiddenLetters.updateLetter(sub.getLetter().charAt(0),game);
			if(!game.checkPlaying()) {
				ExitWindow win = new ExitWindow("You Won","You Won");
				win.button().setOnAction(e2->{win.stage().close();});
				toolBar.saveBtn().setDisable(true);
				game.gameState = 2;
				disable();
				win.stage().showAndWait();
			}
		}
		else { 
			hangmanBox.updateWrong();
			guessRemained.setText("Remaining Guesses: "+(--game.lives));
			if (game.lives <= 0) {
				hiddenLetters.updateLost();
				ExitWindow lost = new ExitWindow("You LOST","You lost. The word was: "+game.answer);
				lost.button().setOnAction(e2->{lost.stage().close();});
				toolBar.saveBtn().setDisable(true);
				game.gameState = 2;
				disable();
				lost.stage().showAndWait();
			}
		}
	}
	public Game game() {
		return game;
	}
	public BorderPane middleCombine() {
		return middleCombine;
	}
	public AlphabetButton[] alphabet (){
		return alphabet;
		
	}
	public HiddenLetters hiddenLetters(){
		return hiddenLetters;
		
	}
	public HangmanBox hangmanBox(){
		return hangmanBox;
		
	}
	public Text guessRemained(){
		return guessRemained;
		
	}
	public void disable() {
		for(AlphabetButton btn: alphabet) {
			btn.button().setDisable(true);
		}
		game.guessed.addAll(game.selection);
	}
}