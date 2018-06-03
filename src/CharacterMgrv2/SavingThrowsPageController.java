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

public class SavingThrowsPageController implements Initializable {
	private Character c;
	private Stage stage;
	private DecimalFormat fmt = new DecimalFormat("+0;-0");

	@FXML VBox vbSavingThrows;
	
	public SavingThrowsPageController(Character ch, Stage s) {
		this.c = ch;
		this.stage = s;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		boolean[] savingThrowsList = c.getSavingThrows();
        String[] savingThrows = { "Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma" };

        int[] savingThrowsMod = { calcMod(c.getStr()), calcMod(c.getDex()), calcMod(c.getCons()), calcMod(c.getInt()),
            calcMod(c.getWis()), calcMod(c.getChar()) };

        for (int i = 0; i < savingThrowsList.length; i++) {

            String nxtItem = savingThrows[i];
            Label savingThrowsLabel = new Label(nxtItem);
            Label modLabel = new Label();
            modLabel.setFont(Font.font("System",FontWeight.BOLD,14));
            if (c.getSavingThrows()[i]) {
                modLabel.setText(fmt.format(savingThrowsMod[i] + c.getProficiencyBonus()));
            }
            else {
                modLabel.setText(fmt.format(savingThrowsMod[i]));
            }
            HBox hbSavingThrowsList = new HBox(10);
            CheckBox isProficient = new CheckBox();
            isProficient.setSelected(c.getSavingThrows()[i]);
            isProficient.setAllowIndeterminate(false);
            final int j = i;
            isProficient.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                    c.setSavingThrowsVal(new_val,j);
                    if (new_val) {
                        modLabel.setText(fmt.format(savingThrowsMod[j] + c.getProficiencyBonus()));
                    }
                    else if (!new_val) {
                        modLabel.setText(fmt.format(savingThrowsMod[j]));
                    }
                }
            });

            hbSavingThrowsList.getChildren().addAll(isProficient,modLabel, savingThrowsLabel);

            vbSavingThrows.getChildren().add(hbSavingThrowsList);

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
