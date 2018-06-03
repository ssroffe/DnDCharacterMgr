package CharacterMgrv2;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomSpellPageController implements Initializable {
	private Character c;
	private Stage stage;
	
	@FXML private ComboBox<String> lvlCb;
	@FXML private ComboBox<String> ritualCb;
	
	@FXML private TextField nameTf;
	@FXML private TextField schoolTf;
	@FXML private TextField castingTimeTf;
	@FXML private TextField rangeTf;
	@FXML private TextField componentsTf;
	@FXML private TextField durationTf;
	@FXML private TextArea descriptionTa;
	
	@FXML private VBox bottomVb;
	@FXML private HBox buttonHb;
	
	@FXML private Text errLbl;
	
	public CustomSpellPageController(Character ch, Stage st) {
		this.c = ch;
		this.stage = st;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lvlCb.setValue("0");
		ritualCb.setValue("No");
		errLbl = new Text("The spell needs at least a name!");
		errLbl.setFill(Color.FIREBRICK);
	}

	@FXML
	public void handleAddBtn(ActionEvent e) {
		if (nameTf.getText().isEmpty()) {
			bottomVb.getChildren().add(errLbl);
			Timer errTimer = new Timer();
			errTimer.schedule(new TimerTask() {
				@Override
				public void run() {
					Platform.runLater(() -> {
						bottomVb.getChildren().remove(errLbl);	
					});
				}
			}, 3000);
		}
		else {
			Spell newSpell = new Spell();
			newSpell.setName(nameTf.getText());
			newSpell.setSchool(schoolTf.getText());
			newSpell.setCasting_time(castingTimeTf.getText());
			newSpell.setRange(rangeTf.getText());
			newSpell.setComponents(componentsTf.getText());
			newSpell.setDuration(durationTf.getText());
			newSpell.setDescription(descriptionTa.getText());
			
			newSpell.setLevel(Integer.parseInt(lvlCb.getValue()));
			
			if (ritualCb.getValue().equalsIgnoreCase("No")) {
				newSpell.setRitual(false);
			}
			else {
				newSpell.setRitual(true);
			}
			
			c.getSpells().add(newSpell);
			stage.close();
		}
	}
	
	@FXML
	public void handleCancelBtn(ActionEvent e) {
		stage.close();
	}
}
