package CharacterMgrv2;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class InventoryPageController implements Initializable {
	private Character c;
	private Stage stage;
	@FXML private VBox vbInventory;
	@FXML private TextField itemTf;
	
	public InventoryPageController(Character ch,Stage s) {
		this.c = ch;
		this.stage = s;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Item> inventoryList = c.getInventory();
		
		for (int i = 0; i < inventoryList.size(); i++) {
			final int j = i;
            Item nxtItem = inventoryList.get(i);
            
            Button info = new Button("info");
            Button rm = new Button("remove");
            Label inventoryLabel = new Label(nxtItem.getName());
            HBox hbInventoryList = new HBox(10);
            
            hbInventoryList.getChildren().addAll(inventoryLabel,info,rm);

            vbInventory.getChildren().add(hbInventoryList);

            ////// info button //////
            Stage infoStage = new Stage();
            info.setOnAction(new EventHandler<ActionEvent>() {
            	@Override
            	public void handle(ActionEvent e) {
            		try {
            			infoStage.close();
            			infoStage.setTitle(nxtItem.getName());
            			FXMLLoader loader = new FXMLLoader();
            			loader.setLocation(Main.class.getResource("ItemInfoPage.fxml"));
            			
            			ItemInfoPageController pc = new ItemInfoPageController(nxtItem,infoStage);
            			loader.setController(pc);
            			
            			AnchorPane root = (AnchorPane) loader.load();
            			
            			Scene scene = new Scene(root);
            			
            			infoStage.setScene(scene);
            			infoStage.show();
            		}
            		catch (Exception ex) {
            			ex.printStackTrace();
            		}
            	}
            });
            
            
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
                            vbInventory.getChildren().remove(hbInventoryList);
                            inventoryList.remove(j);
                            c.setInventory(inventoryList);
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
	public void handleAddItemBtn(ActionEvent e) {
		ArrayList<Item> inventoryList = c.getInventory();
		try {
			if (!itemTf.getText().isEmpty()) {		
				Item newItem = new Item(itemTf.getText());
				inventoryList.add(newItem);
				itemTf.setText(null);
			}
			vbInventory.getChildren().clear();
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
