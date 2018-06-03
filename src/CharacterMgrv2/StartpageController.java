package CharacterMgrv2;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class StartpageController {
	@FXML private Stage stage;
	@FXML private Character c;
	private String fileName;
	
	public void handleLoadBtn(ActionEvent e) {
		try {
			FileChooser fc = new FileChooser();
			fc.setTitle("Load Character");
			File initDir = new File(".");
			fc.setInitialDirectory(initDir);
			
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("D&D Files (*.dnd)","*.dnd");
			fc.getExtensionFilters().add(extFilter);
			
			File file = fc.showOpenDialog(stage);
			String filePath = file.toString();
			
			if (!filePath.isEmpty()) {
				c = Character.loadCharacter(filePath);
				this.fileName = filePath;
				stage.close();
			}
		}
		catch (Exception ex) {
		}
	}
	
	public void handleNewBtn(ActionEvent e) {
		c = new Character();
		this.fileName = "";
		stage.close();
		
	}
	
	public Stage getStage() {
		return this.stage;
	}
	public void setStage(Stage s) {
		this.stage = s;
	}
	
	public Character getCharacter() {
		return this.c;
	}
	public String getFileName() {
		return this.fileName;
	}
}
