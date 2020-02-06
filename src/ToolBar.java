import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class ToolBar{
	
	private int newBtnPressed = 0;
	private Button newBtn;
	private Button loadBtn;
	private Button saveBtn;
	private Button exitBtn;
	private HBox toolBar;
	
	public ToolBar() {
		ImageView newView = new ImageView(new Image("/start.jpg"));		
		newView.setFitHeight(25);
		newView.setFitWidth(25);
		ImageView loadView = new ImageView(new Image("/load.jpg"));		
		loadView.setFitHeight(25);
		loadView.setFitWidth(25);
		ImageView saveView= new ImageView(new Image("/save.png"));
		saveView.setFitHeight(25);
		saveView.setFitWidth(25);
		ImageView exitView = new ImageView(new Image("/exit.jpg"));		
		exitView.setFitHeight(25);
		exitView.setFitWidth(25);
		newBtn = new Button("New",newView);
		loadBtn = new Button("Load",loadView);
		saveBtn = new Button("Save",saveView);
		exitBtn = new Button("Exit",exitView);
		saveBtn.setDisable(true);
		toolBar = new HBox();
		toolBar.setPadding(new Insets(15, 12, 15, 12));
		toolBar.setSpacing(10);
		toolBar.setStyle("-fx-background-color: #336699;");
		toolBar.getChildren().add(newBtn);
		toolBar.getChildren().add(loadBtn);
		toolBar.getChildren().add(saveBtn);
		toolBar.getChildren().add(exitBtn);
	}
	public Button newBtn() {
		return newBtn;
	}
	public Button loadBtn() {
		return loadBtn;
	}
	public Button saveBtn() {
		return saveBtn;
	}
	public Button exitBtn() {
		return exitBtn;
	}
	public HBox toolBar() {
		return toolBar;
	}
	public int getNewBtnPressed() {
		return newBtnPressed;
	}
	public void setNewBtnPressed(int newBtnPressed) {
		this.newBtnPressed = newBtnPressed;
	}
}