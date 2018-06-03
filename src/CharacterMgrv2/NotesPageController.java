package CharacterMgrv2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class NotesPageController implements Initializable {
	private Character c;
	private Stage stage;
	@FXML TextArea notesTa;
	
	public NotesPageController(Character ch, Stage s) {
		this.c = ch;
		this.stage = s;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		notesTa.setText(c.getNotes());
		notesTa.focusedProperty().addListener((ov,oldV,newV) -> {
			if (!newV) {
				c.setNotes(notesTa.getText());
			}
		});
	}
	
	@FXML
	public void handleDoneBtn(ActionEvent e) {
		stage.close();
	}
}
