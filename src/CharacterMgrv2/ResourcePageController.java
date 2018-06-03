package CharacterMgrv2;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ResourcePageController implements Initializable {
	private Character c;
	private Stage stage;
	@FXML private VBox vbResource;
	@FXML private TextField resourceTf;
	
	public ResourcePageController(Character ch,Stage s) {
		this.c = ch;
		this.stage = s;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Resource> resourceList = c.getResources();
		
		for (int i = 0; i < resourceList.size(); i++) {
			final int j = i;
            Resource nxtItem = resourceList.get(i);
            
            Button rm = new Button("remove");
            TextField nameTf = new TextField(nxtItem.getName());
            Spinner<Integer> valueSn = new Spinner<Integer>();
            valueSn.setPrefWidth(75);
            
            nameTf.focusedProperty().addListener((ov,oldV,newV) -> {
    			if (!newV) {
    				nxtItem.setName(nameTf.getText());
    			}
    		});
            
    		SpinnerValueFactory<Integer> valuevf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, nxtItem.getValue()) {
    			@Override
    			public void increment(int steps) {
    				this.setValue(this.getValue() + 1);
    				nxtItem.setValue(this.getValue());
    			}
    			
    			@Override
    			public void decrement(int steps) {
    				this.setValue(this.getValue() - 1);
    				nxtItem.setValue(this.getValue());
    			}
    		};
    		
            valueSn.setValueFactory(valuevf);
    		
            HBox hbInventoryList = new HBox(10);
            
            hbInventoryList.getChildren().addAll(nameTf,valueSn,rm);

            vbResource.getChildren().add(hbInventoryList);
            
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

                    Label rmLabel = new Label("remove " + nxtItem.getName() + ". Are you sure?");
                    rmgrid.add(rmLabel,0,0);
                    Button yesRm = new Button("Yes");
                    Button noRm = new Button("Cancel");
                    HBox hbynrm = new HBox(10);
                    hbynrm.getChildren().addAll(yesRm,noRm);
                    rmgrid.add(hbynrm,0,1);

                    yesRm.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent e) {
                            vbResource.getChildren().remove(hbInventoryList);
                            resourceList.remove(j);
                            c.setResources(resourceList);
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
	public void handleAddResourceBtn(ActionEvent e) {
		ArrayList<Resource> resourceList = c.getResources();
		try {
			if (!resourceTf.getText().isEmpty()) {		
				Resource newResource = new Resource(resourceTf.getText());
				resourceList.add(newResource);
				resourceTf.setText(null);
			}
			vbResource.getChildren().clear();
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
