package CharacterMgrv2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class FeatureNotificationController implements Initializable {
	private Feature f;
	@FXML private TextArea descriptionTa;
	@FXML private Label featureLbl;
	private Stage stage;
	
	public FeatureNotificationController(Feature f,Stage stage) {
		this.f = f;
		this.stage = stage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		featureLbl.setText(f.getName());
		descriptionTa.setText(f.getDescription());
	}
	
	@FXML
	public void handleOkBtn(ActionEvent e) {
		stage.close();
	}
	
}
