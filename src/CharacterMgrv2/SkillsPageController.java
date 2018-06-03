package CharacterMgrv2;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SkillsPageController implements Initializable{
	private Character c;
	private Stage stage;
	private DecimalFormat fmt = new DecimalFormat("+0;-0");

	@FXML private VBox vbSkills;
	
	public SkillsPageController(Character ch, Stage s) {
		this.c = ch;
		this.stage = s;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
        boolean[] skillsList = c.getProficiencies();
        String[] skills = {"Acrobatics (Dex)", "Animal Handling (Wis)", "Arcana (Int)",
            "Athletics (Str)", "Deception (Cha)", "History (Int)", "Insight (Wis)",
            "Intimidation (Cha)", "Investigation (Int)", "Medicine (Wis)", "Nature (Int)",
            "Perception (Wis)", "Performance (Cha)", "Persuasion (Cha)", "Religion (Int)",
            "Sleight of Hand (Dex)", "Stealth (Dex)", "Survival (Wis)"};

        int[] skillsMod = {calcMod(c.getDex()), calcMod(c.getWis()), calcMod(c.getInt()),
            calcMod(c.getStr()), calcMod(c.getChar()), calcMod(c.getInt()), calcMod(c.getWis()),
            calcMod(c.getChar()), calcMod(c.getInt()), calcMod(c.getWis()), calcMod(c.getInt()),
            calcMod(c.getWis()), calcMod(c.getChar()), calcMod(c.getChar()), calcMod(c.getInt()),
            calcMod(c.getDex()), calcMod(c.getDex()), calcMod(c.getWis())};

        for (int i = 0; i < skillsList.length; i++) {

            String nxtItem = skills[i];
            Label skillsLabel = new Label(nxtItem);
            Label modLabel = new Label();
            modLabel.setFont(Font.font("System",FontWeight.BOLD,14));

            if (c.getProficiencies()[i]) {
                modLabel.setText(fmt.format(skillsMod[i] + c.getProficiencyBonus()));
            }
            else {
                modLabel.setText(fmt.format(skillsMod[i]));
            }
            HBox hbSkillsList = new HBox(10);
            CheckBox isProficient = new CheckBox();
            isProficient.setSelected(c.getProficiencies()[i]);
            isProficient.setAllowIndeterminate(false);
            final int j = i;
            isProficient.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                    c.setProficiencyVal(new_val,j);
                    if (new_val) {
                        modLabel.setText(fmt.format(skillsMod[j] + c.getProficiencyBonus()));
                    }
                    else if (!new_val) {
                        modLabel.setText(fmt.format(skillsMod[j]));
                    }
                }
            });

            hbSkillsList.getChildren().addAll(isProficient,modLabel, skillsLabel);

            vbSkills.getChildren().add(hbSkillsList);


        }
		
	}
	
    public static int calcMod(int stat) {
        double weightedVal = (stat - 10.0) / 2.0;
        int returnVal = (int) Math.floor(weightedVal);
        return returnVal;
    }
	
    @FXML
    public void handleDoneBtn(ActionEvent e) {
    	stage.close();
    }
	
}
