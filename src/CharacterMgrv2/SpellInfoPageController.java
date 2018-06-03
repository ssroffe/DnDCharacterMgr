package CharacterMgrv2;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class SpellInfoPageController implements Initializable {
	private Spell s;
	private Stage stage;
	
	@FXML private Label nameLbl;
	@FXML private Label levelLbl;
	@FXML private Label ritualLbl;
	@FXML private Label castingTimeLbl;
	@FXML private Label rangeLbl;
	@FXML private Label componentsLbl;
	@FXML private Label durationLbl;
	@FXML private TextArea descriptionTa;
	
	public SpellInfoPageController(Spell spell, Stage st) {
		this.s = spell;
		this.stage = st;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		nameLbl.setText(s.getName());
		levelLbl.setText("Level " + Integer.toString(s.getLevel()) + " " + s.getSchool());
		
		boolean isRitual = s.getRitual();
		if (isRitual) {
			ritualLbl.setVisible(true);
		}
		
		castingTimeLbl.setText(s.getCasting_time());
		rangeLbl.setText(s.getRange());
		componentsLbl.setText(s.getComponents());
		durationLbl.setText(s.getDuration());
		descriptionTa.setText(s.getDescription());
		
	}

	@FXML
	public void handleOkBtn(ActionEvent e) {
		stage.close();
	}
}
