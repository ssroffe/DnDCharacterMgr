package CharacterMgrv2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import com.google.gson.Gson;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;


public class SpellsPageController implements Initializable{
	private Character c;
	private Stage stage;
	private Spell[] spellArr;
	
	@FXML private ComboBox<Spell> spells0Cb;
	@FXML private ComboBox<Spell> spells1Cb;
	@FXML private ComboBox<Spell> spells2Cb;
	@FXML private ComboBox<Spell> spells3Cb;
	@FXML private ComboBox<Spell> spells4Cb;
	@FXML private ComboBox<Spell> spells5Cb;
	@FXML private ComboBox<Spell> spells6Cb;
	@FXML private ComboBox<Spell> spells7Cb;
	@FXML private ComboBox<Spell> spells8Cb;
	@FXML private ComboBox<Spell> spells9Cb;
	@FXML private ComboBox<Spell> allSpellsCb;
	
	@FXML private GridPane spells0Gp;
	@FXML private GridPane spells1Gp;
	@FXML private GridPane spells2Gp;
	@FXML private GridPane spells3Gp;
	@FXML private GridPane spells4Gp;
	@FXML private GridPane spells5Gp;
	@FXML private GridPane spells6Gp;
	@FXML private GridPane spells7Gp;
	@FXML private GridPane spells8Gp;
	@FXML private GridPane spells9Gp;
	
	@FXML private Spinner<Integer> ss1;
	@FXML private Spinner<Integer> ss2;
	@FXML private Spinner<Integer> ss3;
	@FXML private Spinner<Integer> ss4;
	@FXML private Spinner<Integer> ss5;
	@FXML private Spinner<Integer> ss6;
	@FXML private Spinner<Integer> ss7;
	@FXML private Spinner<Integer> ss8;
	@FXML private Spinner<Integer> ss9;
	
	@FXML private TextField spellAttackTf;
	@FXML private TextField spellSaveTf;
	
	public SpellsPageController(Character ch, Stage s) {
		this.c = ch;
		this.stage = s;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		fillSpellsArray();
		fillLevelComboboxes();
		reloadSpells();
		SpinnerSetup();
		
		//Spell save and attack
		spellAttackTf.setText(Integer.toString(c.getSpellAttack()));
		spellAttackTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getSpellAttack();
			if (newV) {
				oldVal = Integer.parseInt(spellAttackTf.getText());
			}
			try {
				if (!newV) {
					c.setSpellAttack(Integer.parseInt(spellAttackTf.getText()));
				}
			}
			catch (Exception e) {
				spellAttackTf.setText(Integer.toString(oldVal));
			}
		});
		
		spellSaveTf.setText(Integer.toString(c.getSpellSaveDC()));
		spellSaveTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getSpellSaveDC();
			if (newV) {
				oldVal = Integer.parseInt(spellSaveTf.getText());
			}
			try {
				if (!newV) {
					c.setSpellSaveDC(Integer.parseInt(spellSaveTf.getText()));
				}
			}
			catch (Exception e) {
				spellSaveTf.setText(Integer.toString(oldVal));
			}
		});
		
		//Display names
		spells0Cb.setConverter(new StringConverter<Spell>() {
			@Override
			public String toString(Spell ss) {
				try {
					return ss.getName();
				}
				catch (NullPointerException np) {
					return "";
				}
			}
			
			@Override
			public Spell fromString(String s) {
				return spells0Cb.getItems().stream().filter(ap -> 
						ap.getName().equals(s)).findFirst().orElse(null);
			}
		});
		spells1Cb.setConverter(new StringConverter<Spell>() {
			@Override
			public String toString(Spell ss) {
				try {
					return ss.getName();
				}
				catch (NullPointerException np) {
					return "";
				}
			}
			
			@Override
			public Spell fromString(String s) {
				return spells1Cb.getItems().stream().filter(ap -> 
						ap.getName().equals(s)).findFirst().orElse(null);
			}
		});
		spells2Cb.setConverter(new StringConverter<Spell>() {
			@Override
			public String toString(Spell ss) {
				try {
					return ss.getName();
				}
				catch (NullPointerException np) {
					return "";
				}
			}
			
			@Override
			public Spell fromString(String s) {
				return spells2Cb.getItems().stream().filter(ap -> 
						ap.getName().equals(s)).findFirst().orElse(null);
			}
		});
		spells3Cb.setConverter(new StringConverter<Spell>() {
			@Override
			public String toString(Spell ss) {
				try {
					return ss.getName();
				}
				catch (NullPointerException np) {
					return "";
				}
			}
			
			@Override
			public Spell fromString(String s) {
				return spells3Cb.getItems().stream().filter(ap -> 
						ap.getName().equals(s)).findFirst().orElse(null);
			}
		});
		spells4Cb.setConverter(new StringConverter<Spell>() {
			@Override
			public String toString(Spell ss) {
				try {
					return ss.getName();
				}
				catch (NullPointerException np) {
					return "";
				}
			}
			
			@Override
			public Spell fromString(String s) {
				return spells4Cb.getItems().stream().filter(ap -> 
						ap.getName().equals(s)).findFirst().orElse(null);
			}
		});
		spells5Cb.setConverter(new StringConverter<Spell>() {
			@Override
			public String toString(Spell ss) {
				try {
					return ss.getName();
				}
				catch (NullPointerException np) {
					return "";
				}
			}
			
			@Override
			public Spell fromString(String s) {
				return spells5Cb.getItems().stream().filter(ap -> 
						ap.getName().equals(s)).findFirst().orElse(null);
			}
		});
		spells6Cb.setConverter(new StringConverter<Spell>() {
			@Override
			public String toString(Spell ss) {
				try {
					return ss.getName();
				}
				catch (NullPointerException np) {
					return "";
				}
			}
			
			@Override
			public Spell fromString(String s) {
				return spells6Cb.getItems().stream().filter(ap -> 
						ap.getName().equals(s)).findFirst().orElse(null);
			}
		});
		spells7Cb.setConverter(new StringConverter<Spell>() {
			@Override
			public String toString(Spell ss) {
				try {
					return ss.getName();
				}
				catch (NullPointerException np) {
					return "";
				}
			}
			
			@Override
			public Spell fromString(String s) {
				return spells7Cb.getItems().stream().filter(ap -> 
						ap.getName().equals(s)).findFirst().orElse(null);
			}
		});
		spells8Cb.setConverter(new StringConverter<Spell>() {
			@Override
			public String toString(Spell ss) {
				try {
					return ss.getName();
				}
				catch (NullPointerException np) {
					return "";
				}
			}
			
			@Override
			public Spell fromString(String s) {
				return spells8Cb.getItems().stream().filter(ap -> 
						ap.getName().equals(s)).findFirst().orElse(null);
			}
		});
		spells9Cb.setConverter(new StringConverter<Spell>() {
			@Override
			public String toString(Spell ss) {
				try {
					return ss.getName();
				}
				catch (NullPointerException np) {
					return "";
				}
			}
			
			@Override
			public Spell fromString(String s) {
				return spells9Cb.getItems().stream().filter(ap -> 
						ap.getName().equals(s)).findFirst().orElse(null);
			}
		});
		allSpellsCb.setConverter(new StringConverter<Spell>() {
			@Override
			public String toString(Spell ss) {
				try {
					return ss.getName();
				}
				catch (NullPointerException np) {
					return "";
				}
			}
			
			@Override
			public Spell fromString(String s) {
				return allSpellsCb.getItems().stream().filter(ap -> 
						ap.getName().equals(s)).findFirst().orElse(null);
			}
		});
		
	}

	public void reloadSpells() {
		HashSet<Spell> spells = c.getSpells();
		
		for (Spell s : spells) {
			int lvl = s.getLevel();
			switch (lvl) {
			case 0: addSpell(s,spells0Gp); break;
			case 1: addSpell(s,spells1Gp); break;
			case 2: addSpell(s,spells2Gp); break;
			case 3: addSpell(s,spells3Gp); break;
			case 4: addSpell(s,spells4Gp); break;
			case 5: addSpell(s,spells5Gp); break;
			case 6: addSpell(s,spells6Gp); break;
			case 7: addSpell(s,spells7Gp); break;
			case 8: addSpell(s,spells8Gp); break;
			case 9: addSpell(s,spells9Gp); break;
			}
		}
	}
	
	public void fillSpellsArray() {
		Gson gson = new Gson();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("spells.json");
		
		try (Reader reader = new BufferedReader(new InputStreamReader(in))) {
			spellArr = gson.fromJson(reader, Spell[].class);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			spellArr = new Spell[0];
		}
	}
	
	public void fillLevelComboboxes() {
		ArrayList<CharClass> classList = c.getClss();
		
		ArrayList<String> caster = new ArrayList<String>();
		for (int i = 0; i < classList.size(); i++) {
			try {
				String subclssName = classList.get(i).getSubclass().getName();
				if (subclssName.equalsIgnoreCase("Eldritch Knight") || subclssName.equalsIgnoreCase("Arcane Trickster")) {
					caster.add("wizard");
				}
			}
			catch (NullPointerException npe) {
				
			}
		}
		for (int i = 0; i < classList.size(); i++) {
			String className = classList.get(i).getClassName();
			caster.add(className.toLowerCase());
		}
		
		for (Spell s : spellArr) {
			
			int lvl = s.getLevel();
			HashSet<String> spellClasses = s.getClss();
			
			allSpellsCb.getItems().add(s);
			
			for (int i = 0; i < caster.size(); i++) {
				
				if (spellClasses.contains(caster.get(i))) {
					try {
						switch (lvl) {
						case 0: spells0Cb.getItems().add(s); break;
						case 1: spells1Cb.getItems().add(s); break;
						case 2: spells2Cb.getItems().add(s); break;
						case 3: spells3Cb.getItems().add(s); break;
						case 4: spells4Cb.getItems().add(s); break;
						case 5: spells5Cb.getItems().add(s); break;
						case 6: spells6Cb.getItems().add(s); break;
						case 7: spells7Cb.getItems().add(s); break;
						case 8: spells8Cb.getItems().add(s); break;
						case 9: spells9Cb.getItems().add(s); break;
						}
					}
					catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
			
		}
		
	}
	
	@FXML
	public void handleAddSpell0Btn(ActionEvent e) {
		if (spells0Cb.getValue() != null) {
			Spell s = spells0Cb.getValue();
			addSpell(s,spells0Gp);
			spells0Cb.setValue(null);
		}
	}
	@FXML
	public void handleAddSpell1Btn(ActionEvent e) {
		if (spells1Cb.getValue() != null) {
			Spell s = spells1Cb.getValue();
			addSpell(s,spells1Gp);
			spells1Cb.setValue(null);
		}
	}
	@FXML
	public void handleAddSpell2Btn(ActionEvent e) {
		if (spells2Cb.getValue() != null) {
			Spell s = spells2Cb.getValue();
			addSpell(s,spells2Gp);
			spells2Cb.setValue(null);
		}
	}
	@FXML
	public void handleAddSpell3Btn(ActionEvent e) {
		if (spells3Cb.getValue() != null) {
			Spell s = spells3Cb.getValue();
			addSpell(s,spells3Gp);
			spells3Cb.setValue(null);
		}
	}
	@FXML
	public void handleAddSpell4Btn(ActionEvent e) {
		if (spells4Cb.getValue() != null) {
			Spell s = spells4Cb.getValue();
			addSpell(s,spells4Gp);
			spells4Cb.setValue(null);
		}
	}
	@FXML
	public void handleAddSpell5Btn(ActionEvent e) {
		if (spells5Cb.getValue() != null) {
			Spell s = spells5Cb.getValue();
			addSpell(s,spells5Gp);
			spells5Cb.setValue(null);
		}
	}
	@FXML
	public void handleAddSpell6Btn(ActionEvent e) {
		if (spells6Cb.getValue() != null) {
			Spell s = spells6Cb.getValue();
			addSpell(s,spells6Gp);
			spells6Cb.setValue(null);
		}
	}
	@FXML
	public void handleAddSpell7Btn(ActionEvent e) {
		if (spells7Cb.getValue() != null) {
			Spell s = spells7Cb.getValue();
			addSpell(s,spells7Gp);
			spells7Cb.setValue(null);
		}
	}
	@FXML
	public void handleAddSpell8Btn(ActionEvent e) {
		if (spells8Cb.getValue() != null) {
			Spell s = spells8Cb.getValue();
			addSpell(s,spells8Gp);
			spells8Cb.setValue(null);
		}
	}
	@FXML
	public void handleAddSpell9Btn(ActionEvent e) {
		if (spells9Cb.getValue() != null) {
			Spell s = spells9Cb.getValue();
			addSpell(s,spells9Gp);
			spells9Cb.setValue(null);
		}
	}
	@FXML
	public void handleAddSpellAllBtn(ActionEvent e) {
		if (allSpellsCb.getValue() != null) {
			Spell spell = allSpellsCb.getValue();
			int lvl = spell.getLevel();
			switch (lvl) {
			case 0: addSpell(spell,spells0Gp); break;
			case 1: addSpell(spell,spells1Gp); break;
			case 2: addSpell(spell,spells2Gp); break;
			case 3: addSpell(spell,spells3Gp); break;
			case 4: addSpell(spell,spells4Gp); break;
			case 5: addSpell(spell,spells5Gp); break;
			case 6: addSpell(spell,spells6Gp); break;
			case 7: addSpell(spell,spells7Gp); break;
			case 8: addSpell(spell,spells8Gp); break;
			case 9: addSpell(spell,spells9Gp); break;
			}
			allSpellsCb.setValue(null);
		}
	}
	
	@FXML
	public void handleAddCustomSpell(ActionEvent e) {
		Stage customStage = new Stage();
		try {
			customStage.close();
			customStage.setTitle("New Custom Spell");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("CustomSpellPage.fxml"));
			
			CustomSpellPageController pc = new CustomSpellPageController(c,customStage);
			loader.setController(pc);
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			
			customStage.setScene(scene);
			customStage.showAndWait();
			
			//reload the page
			initialize(null,null);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void addSpell(Spell sp, GridPane gp) {
		HashSet<Spell> spellsList = c.getSpells();
		spellsList.add(sp);
		String spellName = sp.getName();
		Label spellLbl = new Label(sp.getName());
		Button info = new Button("Info");
		Button rm = new Button("Remove");
		int numRows = getRowCount(gp);
		HBox hb = new HBox(10);
		hb.getChildren().addAll(info,rm);
		
		gp.add(spellLbl, 0, numRows);
		gp.add(hb,1,numRows);
		final int currRow = numRows;
		
		////// Remove Button ///////
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

                Label rmLabel = new Label("remove " + spellName + ". Are you sure?");
                rmgrid.add(rmLabel,0,0);
                Button yesRm = new Button("Yes");
                Button noRm = new Button("Cancel");
                HBox hbynrm = new HBox(10);
                hbynrm.getChildren().addAll(yesRm,noRm);
                rmgrid.add(hbynrm,0,1);

                yesRm.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                    	deleteRow(gp,currRow);
                        spellsList.remove(sp);
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
        
        ///// INFO BUTTON /////
        Stage infoStage = new Stage();
        info.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		try {
        			infoStage.close();
        			infoStage.setTitle(sp.getName() + " Information");
        			FXMLLoader loader = new FXMLLoader();
        			loader.setLocation(Main.class.getResource("SpellInfoPage.fxml"));
        			
        			SpellInfoPageController pc = new SpellInfoPageController(sp,infoStage);
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
		
	}
	
	public void SpinnerSetup() {
		SpinnerValueFactory<Integer> vf1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, c.getSpellSlots1()) {
			@Override
			public void increment(int steps) {
				this.setValue(this.getValue() + 1);
				c.setSpellSlots1(this.getValue());
			}
			@Override
			public void decrement(int steps) {
				this.setValue(this.getValue() - 1);
				c.setSpellSlots1(this.getValue());
			}
		};
		ss1.setValueFactory(vf1);
		
		SpinnerValueFactory<Integer> vf2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, c.getSpellSlots2()) {
			@Override
			public void increment(int steps) {
				this.setValue(this.getValue() + 1);
				c.setSpellSlots2(this.getValue());
			}
			@Override
			public void decrement(int steps) {
				this.setValue(this.getValue() - 1);
				c.setSpellSlots2(this.getValue());
			}
		};
		ss2.setValueFactory(vf2);
		
		SpinnerValueFactory<Integer> vf3 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, c.getSpellSlots3()) {
			@Override
			public void increment(int steps) {
				this.setValue(this.getValue() + 1);
				c.setSpellSlots3(this.getValue());
			}
			@Override
			public void decrement(int steps) {
				this.setValue(this.getValue() - 1);
				c.setSpellSlots3(this.getValue());
			}
		};
		ss3.setValueFactory(vf3);
		
		SpinnerValueFactory<Integer> vf4 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, c.getSpellSlots4()) {
			@Override
			public void increment(int steps) {
				this.setValue(this.getValue() + 1);
				c.setSpellSlots4(this.getValue());
			}
			@Override
			public void decrement(int steps) {
				this.setValue(this.getValue() - 1);
				c.setSpellSlots4(this.getValue());
			}
		};
		ss4.setValueFactory(vf4);
		
		SpinnerValueFactory<Integer> vf5 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, c.getSpellSlots5()) {
			@Override
			public void increment(int steps) {
				this.setValue(this.getValue() + 1);
				c.setSpellSlots5(this.getValue());
			}
			@Override
			public void decrement(int steps) {
				this.setValue(this.getValue() - 1);
				c.setSpellSlots5(this.getValue());
			}
		};
		ss5.setValueFactory(vf5);
		
		SpinnerValueFactory<Integer> vf6 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, c.getSpellSlots6()) {
			@Override
			public void increment(int steps) {
				this.setValue(this.getValue() + 1);
				c.setSpellSlots6(this.getValue());
			}
			@Override
			public void decrement(int steps) {
				this.setValue(this.getValue() - 1);
				c.setSpellSlots6(this.getValue());
			}
		};
		ss6.setValueFactory(vf6);
		
		SpinnerValueFactory<Integer> vf7 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, c.getSpellSlots7()) {
			@Override
			public void increment(int steps) {
				this.setValue(this.getValue() + 1);
				c.setSpellSlots7(this.getValue());
			}
			@Override
			public void decrement(int steps) {
				this.setValue(this.getValue() - 1);
				c.setSpellSlots7(this.getValue());
			}
		};
		ss7.setValueFactory(vf7);
		
		SpinnerValueFactory<Integer> vf8 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, c.getSpellSlots8()) {
			@Override
			public void increment(int steps) {
				this.setValue(this.getValue() + 1);
				c.setSpellSlots8(this.getValue());
			}
			@Override
			public void decrement(int steps) {
				this.setValue(this.getValue() - 1);
				c.setSpellSlots8(this.getValue());
			}
		};
		ss8.setValueFactory(vf8);
		
		SpinnerValueFactory<Integer> vf9 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, c.getSpellSlots9()) {
			@Override
			public void increment(int steps) {
				this.setValue(this.getValue() + 1);
				c.setSpellSlots9(this.getValue());
			}
			@Override
			public void decrement(int steps) {
				this.setValue(this.getValue() - 1);
				c.setSpellSlots9(this.getValue());
			}
		};
		ss9.setValueFactory(vf9);
	}
	
	private int getRowCount(GridPane pane) {
        int numRows = pane.getRowConstraints().size();
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Node child = pane.getChildren().get(i);
            if (child.isManaged()) {
                Integer rowIndex = GridPane.getRowIndex(child);
                if(rowIndex != null){
                    numRows = Math.max(numRows,rowIndex+1);
                }
            }
        }
        return numRows;
    }
	
	static void deleteRow(GridPane grid, final int row) {
	    Set<Node> deleteNodes = new HashSet<>();
	    for (Node child : grid.getChildren()) {
	        // get index from child
	        Integer rowIndex = GridPane.getRowIndex(child);

	        // handle null values for index=0
	        int r = rowIndex == null ? 0 : rowIndex;

	        if (r > row) {
	            // decrement rows for rows after the deleted row
	            GridPane.setRowIndex(child, r-1);
	        } else if (r == row) {
	            // collect matching rows for deletion
	            deleteNodes.add(child);
	        }
	    }

	    // remove nodes from row
	    grid.getChildren().removeAll(deleteNodes);
	}
	
	@FXML
	public void handleDoneBtn(ActionEvent e) {
		stage.close();
	}
}
