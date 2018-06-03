package CharacterMgrv2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class PersonalityPageController implements Initializable {
	private Character c;
	private Stage stage;
	@FXML TextArea flawsTa;
	@FXML TextArea bondsTa;
	@FXML TextArea idealsTa;
	
	public PersonalityPageController(Character ch, Stage s) {
		this.c = ch;
		this.stage = s;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		flawsTa.setText(c.getFlaws());
		bondsTa.setText(c.getBonds());
		idealsTa.setText(c.getIdeals());
		
		flawsTa.focusedProperty().addListener((ov,oldV,newV) -> {
			if (!newV) {
				c.setFlaws(flawsTa.getText());
			}
		});
		
		bondsTa.focusedProperty().addListener((ov,oldV,newV) -> {
			if (!newV) {
				c.setBonds(bondsTa.getText());
			}
		});
		
		idealsTa.focusedProperty().addListener((ov,oldV,newV) -> {
			if (!newV) {
				c.setIdeals(idealsTa.getText());
			}
		});
		
	}
	
	@FXML
	public void handleDoneBtn(ActionEvent e) {
		stage.close();
	}
}
