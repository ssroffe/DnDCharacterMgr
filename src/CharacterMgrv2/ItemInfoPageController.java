package CharacterMgrv2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ItemInfoPageController implements Initializable {
	private Item i;
	@FXML private TextArea descriptionTa;
	@FXML private Label itemLbl;
	private Stage stage;
	
	public ItemInfoPageController(Item i,Stage stage) {
		this.i = i;
		this.stage = stage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		itemLbl.setText(i.getName());
		descriptionTa.setText(i.getDescription());
		
		descriptionTa.focusedProperty().addListener((ov,oldV,newV) -> {
			if (!newV) {
				i.setDescription(descriptionTa.getText());
			}
		});
	}
	
	@FXML
	public void handleDoneBtn(ActionEvent e) {
		stage.close();
	}
	
}
