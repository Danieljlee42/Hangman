import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

@SuppressWarnings("serial")
public class Game implements Serializable{
	String answer; 
	char[] shown;
	ArrayList<Character> selection = new ArrayList<>();
	ArrayList<Character> guessed = new ArrayList<>();
	int lives = 10;
	public int gameState = 0;
	
	public Game(String answer) {
		this.answer = answer;
		this.shown = new char[answer.length()];
		for (Character c = 'a'; c<='z'; c++) {
			selection.add(c);
		}
		for(int i = 0; i < shown.length ; i++) {
			shown[i] = '_';
		}
	}
	public boolean checkPlaying() {
		for(char c:shown) {
			if (c == '_') {
				return true;
			}
		}
		return false;
	}
	public boolean letterChecker(char guess) {
		if(answer.indexOf(guess)==-1) {
			return false;
		}
		return true;
	}
	public boolean inSelection(char guess) {
		for(char c: selection) {
			if(c==guess) {
				return true;
			}
		}
		return false;
	}
	public boolean inGuessed(char guess) {
		for(char c: guessed) {
			if(c==guess) {
				return true;
			}
		}
		return false;
	}
	static String randomWord(String fileName) {
		File wordFile = new File ("src/"+fileName);
		String word = "plane";
		int count = 0;
		try {
			@SuppressWarnings("resource")
			Scanner input = new Scanner (wordFile);
			while(input.hasNext()) {
				input.next();
				count++;
			}
			int randLine = (int) (Math.random()*count);
			int reached = 0;
			input = new Scanner (wordFile);
			while(reached != randLine) {
				word = input.next();
				reached++;
			}
		}catch(IOException ioe) {
			System.out.println("There is no words.txt");
		}
		return word;
	}
	public static Game loadGame(Game newGame, File fileName) {
		try {
		     FileInputStream file = new FileInputStream(fileName);
		     ObjectInputStream fin  = new ObjectInputStream(file);
		     newGame = (Game) fin.readObject(); 
		     fin.close();
		     System.out.println("Successfully loaded contents into: "+fileName.getName());
		     return newGame;
		} catch(IOException e){
			System.out.println(".hng file is not found. New game object will be created.");
			return newGame;
		} catch (Exception e) {
			System.out.println("A .hng file was not selected.");
		}
		return newGame;
	}
	public static void saveGame(Game game, File fileName) {
		try {
		      FileOutputStream file = new FileOutputStream(fileName);
		      ObjectOutputStream fout = new ObjectOutputStream(file);
		      fout.writeObject(game); 
		      fout.close();
		      System.out.println("Game saved into file: "+fileName.getName());
		} catch (Exception e){
		    System.out.println("Game was not able to be saved.");
		} 
	}
	public static File getHngFile(boolean dialogType) {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Obtain Hangman File");
			fileChooser.getExtensionFilters().add(new ExtensionFilter("Hangman Files (*.hng)","*.hng"));
			Stage inStage = new Stage();
			File hngFile;
			if(dialogType) {//true for load
				hngFile = fileChooser.showOpenDialog(inStage);
			}
			else {//false for save
				hngFile = fileChooser.showSaveDialog(inStage);
			}
			return hngFile;
		}catch(Exception e) {
			System.out.println("No file Selected.");
		}
		return null;
	}
}