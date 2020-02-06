import java.io.File;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Hangman extends Application{
	public static void main(String[] args) { launch(args); }
	public static boolean start = false; //start button has been pressed false = no, true = yes
	public static Root root;
	public void start(Stage stage) throws Exception {
		ToolBar toolBar = new ToolBar(); 
		root = new Root(toolBar);
		Text hangManText = new Text("Hangman");
		hangManText.setFont(Font.font("Verdana",40));
		hangManText.setFill(Color.WHITE);
		Button startBtn = new Button("Start Playing");
		HBox startBox = new HBox();
		startBox.setAlignment(Pos.CENTER);
		startBox.setStyle("-fx-background-color: #fff8dc;");
		startBox.setPadding(new Insets(10, 10, 10, 10));
		startBox.getChildren().add(startBtn);
		VBox topCombine = new VBox();
		topCombine.setAlignment(Pos.CENTER);
		topCombine.getChildren().add(toolBar.toolBar()); 
		BorderPane rootPane = new BorderPane();
		rootPane.setStyle("-fx-background-color: #b0e0e6;");
		rootPane.setTop(topCombine);
		toolBar.newBtn().setOnAction(e-> 
		{
			if(root.game().gameState == 0  && toolBar.getNewBtnPressed() == 0) {
				topCombine.getChildren().add(hangManText);
				rootPane.setBottom(startBox);
				toolBar.setNewBtnPressed(1);
			}
			else if ((root.game().gameState == 0  && toolBar.getNewBtnPressed() == 1 )|| root.game().gameState == 2 || toolBar.saveBtn().isDisable()) {
				startBtn.setDisable(false);
				rootPane.getChildren().remove(root.middleCombine());
				root = new Root(toolBar);
				start = false;
			}
			else if (root.game().gameState == 1) {
				SaveWindow w = new SaveWindow("Alert","Would you like to save your progress?");
				w.yesBtn().setOnAction(e2->{
					Game.saveGame(root.game(), Game.getHngFile(false));
					startBtn.setDisable(false);
					rootPane.getChildren().remove(root.middleCombine());
					root = new Root(toolBar);
					start = false;
					toolBar.saveBtn().setDisable(true);
				});
				w.noBtn().setOnAction(e2->{
					startBtn.setDisable(false);
					rootPane.getChildren().remove(root.middleCombine());
					root = new Root(toolBar);
					start = false;
				});
				w.stage().showAndWait();
			}
		});
		toolBar.loadBtn().setOnAction(e-> {
			if(root.game().gameState == 0 && toolBar.getNewBtnPressed() == 0) {
				topCombine.getChildren().add(hangManText);
				rootPane.setBottom(startBox);
				toolBar.setNewBtnPressed(1);
			} 
			File hngFile = Game.getHngFile(true);
			if (toolBar.getNewBtnPressed() == 1 && hngFile != null) {
				startBtn.setDisable(false);
				toolBar.saveBtn().setDisable(true);
				rootPane.getChildren().remove(root.middleCombine());
				start = false;
				root = new Root(toolBar, Game.loadGame(root.game(), hngFile));
			}
			
		});
		toolBar.saveBtn().setOnAction(e -> {
			File hngFile = Game.getHngFile(false);
			Game.saveGame(root.game(), hngFile);
			if (hngFile != null) {
				toolBar.saveBtn().setDisable(true);
			}
		});
		toolBar.exitBtn().setOnAction(e-> 
		{
			if(root.game().gameState == 0 || root.game().gameState == 2 || toolBar.saveBtn().isDisable()) {
				stage.close();
			}
			else {
				SaveWindow w = new SaveWindow("Alert","Would you like to save your progress?");
				w.yesBtn().setOnAction(e2->{
					File hngFile = Game.getHngFile(false);
					Game.saveGame(root.game(), hngFile);
					if (hngFile != null) {
						stage.close(); 
					}
				});
				w.stage().show();
				w.noBtn().setOnAction(e2->stage.close());
			}
		});
		startBtn.setOnAction(e->{rootPane.setCenter(root.middleCombine()); startBtn.setDisable(true); start = true; toolBar.toolBar().requestFocus();});
		Scene scene = new Scene(rootPane, 800, 600);
		scene.setOnKeyPressed(e-> {
			try{
				String text = e.getText();
				if(!root.game().inGuessed(text.charAt(0))&&root.game().inSelection(text.charAt(0))&&start){
					AlphabetButton sub = root.alphabet()[(int) (text.charAt(0))-97];
					sub.button().setDisable(true);
					root.updateGame(root.game(), sub, toolBar, root.hiddenLetters(), root.hangmanBox(), root.guessRemained());
				}
			}catch(Exception ex) {
				System.out.println("Illegal character inputs.");
			}
		});
		stage.setTitle("Hangman");
		stage.getIcons().add(new Image("file:hangmanIcon.png"));
		stage.setScene(scene);
		//stage.setResizable(false);;
		stage.sizeToScene();
		stage.show();
	}
}