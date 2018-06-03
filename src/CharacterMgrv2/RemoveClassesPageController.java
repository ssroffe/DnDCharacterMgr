package CharacterMgrv2;

import java.net.URL;
import java.util.ArrayList;
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
import javafx.stage.Stage;

public class RemoveClassesPageController implements Initializable {
	private Character c;
	private Stage stage;
	private MainpageController mpc;
	@FXML private VBox vbClasses;
	private boolean[] selectedArr;
	
	public RemoveClassesPageController(Character ch,Stage s,MainpageController m) {
		this.c = ch;
		this.stage = s;
		this.mpc = m;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<CharClass> classList = c.getClss();
		selectedArr = new boolean[classList.size()];
		
		for (int i = 0; i < classList.size(); i++) {
			final int j = i;
			CharClass curr = classList.get(i);
			CheckBox select = new CheckBox();
			Label classLbl = new Label(curr.getClassName());
			Label lvlLbl = new Label("Level: " + Integer.toString(curr.getLevel()));
			
			HBox hb = new HBox(10);
			
			hb.getChildren().addAll(select,classLbl,lvlLbl);
			vbClasses.getChildren().add(hb);
			
			select.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                    selectedArr[j] = new_val;
                }
            });
			
		}
		
	}
	
	@FXML
	public void handleRemoveSelectedBtn(ActionEvent e) {
		//ArrayList<CharClass> classes = c.getClss();
		vbClasses.getChildren().clear();
		mpc.removeClasses(this);
		initialize(null,null);
	}
	
	public boolean[] getSelected() {
		return this.selectedArr;
	}
	
	@FXML
	public void handleDoneBtn(ActionEvent e) {
		stage.close();
	}
}
