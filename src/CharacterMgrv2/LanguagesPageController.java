package CharacterMgrv2;

import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LanguagesPageController implements Initializable {
	private Character c;
	private Stage stage;
	@FXML private VBox vbLanguages;
	@FXML private ComboBox<String> languageCb;
	
	public LanguagesPageController(Character ch,Stage s) {
		this.c = ch;
		this.stage = s;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		HashSet<String> languagesList = c.getLanguages();
		Iterator<String> itr = languagesList.iterator();
		
		while (itr.hasNext()) {
            String nxtItem = itr.next();
            Label languageslabel = new Label(nxtItem);
            HBox hbLanguagesList = new HBox(10);

            Button rm = new Button("remove");
            hbLanguagesList.getChildren().addAll(languageslabel,rm);

            vbLanguages.getChildren().add(hbLanguagesList);

            ////// Remove button ///////
            Stage confirmRm = new Stage();
            rm.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    confirmRm.close();
                    confirmRm.setTitle("Are you sure?");
                    GridPane rmgrid = new GridPane();
                    rmgrid.setAlignment(Pos.CENTER);
                    rmgrid.setHgap(10);
                    rmgrid.setVgap(10);
                    Scene rmscene = new Scene(rmgrid,400,150);
                    confirmRm.setScene(rmscene);
                    confirmRm.show();

                    Label rmLabel = new Label("remove " + nxtItem + ". Are you sure?");
                    rmgrid.add(rmLabel,0,0);
                    Button yesRm = new Button("Yes");
                    Button noRm = new Button("Cancel");
                    HBox hbynrm = new HBox(10);
                    hbynrm.getChildren().addAll(yesRm,noRm);
                    rmgrid.add(hbynrm,0,1);

                    yesRm.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            vbLanguages.getChildren().remove(hbLanguagesList);
                            languagesList.remove(languageslabel.getText());
                            c.setLanguages(languagesList);
                            confirmRm.close();
                        }
                    });
                    noRm.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            confirmRm.close();
                        }
                    });
                }
            });
		}
		
	}
	
	@FXML
	public void handleAddLanguageBtn(ActionEvent e) {
		HashSet<String> languageList = c.getLanguages();
		try {
			if (!languageCb.getValue().toString().isEmpty()) {
				languageList.add(languageCb.getValue().toString());
				languageCb.setValue(null);
			}
			vbLanguages.getChildren().clear();
			initialize(null, null);
		}
		catch (NullPointerException ex) {
		}
		
		
	}
	
	@FXML
	public void handleDoneBtn(ActionEvent e) {
		stage.close();
	}
}
