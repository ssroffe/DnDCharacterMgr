package CharacterMgrv2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class FeatureInfoPageController implements Initializable {
	private Feature f;
	@FXML private TextArea descriptionTa;
	@FXML private Label featureLbl;
	private Stage stage;
	
	public FeatureInfoPageController(Feature f,Stage stage) {
		this.f = f;
		this.stage = stage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		featureLbl.setText(f.getName());
		descriptionTa.setText(f.getDescription());
		
		descriptionTa.focusedProperty().addListener((ov,oldV,newV) -> {
			if (!newV) {
				f.setDescription(descriptionTa.getText());
			}
		});
	}
	
	@FXML
	public void handleDoneBtn(ActionEvent e) {
		stage.close();
	}
	
}
