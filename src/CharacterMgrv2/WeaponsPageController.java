package CharacterMgrv2;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class WeaponsPageController implements Initializable {
	private Character c;
	private Stage stage;
	private DecimalFormat fmt = new DecimalFormat("+0;-0");
	
	@FXML private VBox vbWeapons;
	@FXML private TextField weaponTf;
	
	public WeaponsPageController(Character ch,Stage s) {
		this.c = ch;
		this.stage = s;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ArrayList<Weapon> weaponsList = c.getWeapons();
		
		for (int i = 0; i < weaponsList.size(); i++) {
			final int currIndex = i;
			Weapon nxtItem = weaponsList.get(i);
			
			TextField nameTf = new TextField(nxtItem.getName());
			nameTf.setPromptText("Weapon Name");
			TextField damageTf = new TextField(nxtItem.getDamage());
			damageTf.setPromptText("Damage");
			damageTf.setPrefWidth(80);
			TextField damageTypeTf = new TextField(nxtItem.getDamageType());
			damageTypeTf.setPromptText("Damage Type");
			
			CheckBox isProficient = new CheckBox();
			isProficient.setAllowIndeterminate(false);
			isProficient.setSelected(nxtItem.isProficient());
			
			CheckBox isFinesse = new CheckBox();
			isFinesse.setAllowIndeterminate(false);
			isFinesse.setSelected(nxtItem.isFinesse());
			
			Label hitMod = new Label();
            hitMod.setFont(Font.font("System",FontWeight.BOLD,18));
			Label damageMod = new Label();
            damageMod.setFont(Font.font("System",FontWeight.BOLD,18));

			Button rm = new Button("Remove");
			
            if (isProficient.isSelected() && isFinesse.isSelected()) {
                hitMod.setText(fmt.format(calcMod(c.getDex()) + c.getProficiencyBonus()));
            }
            else if (!isProficient.isSelected() && isFinesse.isSelected()) {
                hitMod.setText(fmt.format(calcMod(c.getDex())));
            }
            else if (isProficient.isSelected() && !isFinesse.isSelected()) {
                hitMod.setText(fmt.format(calcMod(c.getStr()) + c.getProficiencyBonus()));
            }
            else {
                hitMod.setText(fmt.format(calcMod(c.getStr())));

            }
            
            if (isFinesse.isSelected()) {
                damageMod.setText(fmt.format(calcMod(c.getDex())));
            }
            else {
                damageMod.setText(fmt.format(calcMod(c.getStr())));
            }
            
            isProficient.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) {

                    nxtItem.setProficient(newVal);
                    weaponsList.set(currIndex, nxtItem);

                    if (newVal && isFinesse.isSelected()) {
                        hitMod.setText(fmt.format(calcMod(c.getDex()) + c.getProficiencyBonus()));
                    }
                    else if (newVal && !isFinesse.isSelected()) {
                        hitMod.setText(fmt.format(calcMod(c.getStr()) + c.getProficiencyBonus()));
                    }
                    else if (!newVal && isFinesse.isSelected()) {
                        hitMod.setText(fmt.format(calcMod(c.getDex())));
                    }
                    else {
                        hitMod.setText(fmt.format(calcMod(c.getStr())));
                    }

                }
            });

            isFinesse.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> ov, Boolean oldVal, Boolean newVal) {
                	
                    nxtItem.setFinesse(newVal);
                    weaponsList.set(currIndex, nxtItem);
                    
                    if (newVal) {
                        damageMod.setText(fmt.format(calcMod(c.getDex())));
                    }
                    else {
                        damageMod.setText(fmt.format(calcMod(c.getStr())));
                    }
                    
                    if (newVal && isProficient.isSelected()) {
                        hitMod.setText(fmt.format(calcMod(c.getDex()) + c.getProficiencyBonus()));
                    }
                    else if (newVal && !isProficient.isSelected()) {
                        hitMod.setText(fmt.format(calcMod(c.getDex())));
                    }
                    else if (!newVal && isProficient.isSelected()) {
                        hitMod.setText(fmt.format(calcMod(c.getStr()) + c.getProficiencyBonus()));
                    }
                    else {
                        hitMod.setText(fmt.format(calcMod(c.getStr())));
                    }
                }
            });
			
            nameTf.focusedProperty().addListener((ov,oldV,newV) -> {
    			if (!newV) {
    				nxtItem.setName(nameTf.getText());
    			}
    		});
            
            damageTf.focusedProperty().addListener((ov,oldV,newV) -> {
    			if (!newV) {
    				nxtItem.setDamage(damageTf.getText());
    			}
    		});
            
            damageTypeTf.focusedProperty().addListener((ov,oldV,newV) -> {
    			if (!newV) {
    				nxtItem.setDamageType(damageTypeTf.getText());
    			}
    		});
            
            HBox hbweapons = new HBox(10);
            hbweapons.getChildren().addAll(isProficient, hitMod, nameTf, damageTf,damageMod, damageTypeTf,
            		isFinesse,rm);
            
            vbWeapons.getChildren().add(hbweapons);
            
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
                            vbWeapons.getChildren().remove(hbweapons);
                            weaponsList.remove(currIndex);
                            c.setWeapons(weaponsList);
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
	public void handleAddWeaponsBtn(ActionEvent e) {
		ArrayList<Weapon> weaponsList = c.getWeapons();
		try {
			if (!weaponTf.getText().isEmpty() ) {
				Weapon newWeapon = new Weapon(weaponTf.getText());
				weaponsList.add(newWeapon);
				weaponTf.setText(null);
				
			}
			vbWeapons.getChildren().clear();
			initialize(null,null);
		}
		catch (Exception ex) {
			
		}
	}
	
	@FXML
	public void handleDoneBtn(ActionEvent e) {
		stage.close();
	}
	
    public static int calcMod(int stat) {
        double weightedVal = (stat - 10.0) / 2.0;
        int returnVal = (int) Math.floor(weightedVal);
        return returnVal;
    }
}
