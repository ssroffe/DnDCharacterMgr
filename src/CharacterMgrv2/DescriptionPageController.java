package CharacterMgrv2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class DescriptionPageController implements Initializable {
	private Character c;
	private Stage stage;
	@FXML TextArea descriptionTa;
	
	public DescriptionPageController(Character ch, Stage s) {
		this.c = ch;
		this.stage = s;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		descriptionTa.setText(c.getDescription());
		descriptionTa.focusedProperty().addListener((ov,oldV,newV) -> {
			if (!newV) {
				c.setDescription(descriptionTa.getText());
			}
		});
	}
	
	@FXML
	public void handleDoneBtn(ActionEvent e) {
		stage.close();
	}
}
