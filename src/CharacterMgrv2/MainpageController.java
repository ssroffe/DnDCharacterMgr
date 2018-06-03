package CharacterMgrv2;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import com.google.gson.Gson;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.StringConverter;

//TODO: Death saving throws
public class MainpageController implements Initializable{
	private Stage stage;
	private String savedFile;
	
	private DecimalFormat fmt = new DecimalFormat("+0;-0");
	
	@FXML private Button saveAsBtn;
	
	@FXML private TextField nameTf;
	@FXML private TextField inspirationTf;
	@FXML private TextField proficiencyTf;
	@FXML private TextField strTf;
	@FXML private TextField consTf;
	@FXML private TextField dexTf;
	@FXML private TextField intTf;
	@FXML private TextField wisTf;
	@FXML private TextField charTf;
	@FXML private TextField expTf;
	@FXML private TextField maxHPTf;
	@FXML private TextField currHPTf;
	@FXML private TextField addSubHPTf;
	@FXML private TextField tempHPTf;
	@FXML private TextField acTf;
	@FXML private TextField initTf;
	@FXML private TextField speedTf;
	@FXML private TextField cpTf;
	@FXML private TextField spTf;
	@FXML private TextField epTf;
	@FXML private TextField gpTf;
	@FXML private TextField ppTf;
	@FXML private TextField addSpendTf;
	
	@FXML private Label strModLbl;
	@FXML private Label consModLbl;
	@FXML private Label dexModLbl;
	@FXML private Label intModLbl;
	@FXML private Label wisModLbl;
	@FXML private Label charModLbl;
	@FXML private Label totalLvlLbl;
	
	@FXML private Label savedLbl;
	
	@FXML private CheckBox deathRollSuccessBox1;
	@FXML private CheckBox deathRollSuccessBox2;
	@FXML private CheckBox deathRollSuccessBox3;
	@FXML private CheckBox deathRollFailBox1;
	@FXML private CheckBox deathRollFailBox2;
	@FXML private CheckBox deathRollFailBox3;
	
	@FXML private ComboBox<String> currencyCb;
	@FXML private ComboBox<String> alignmentCb;
	@FXML private ComboBox<String> raceCb;
	@FXML private ComboBox<String> subRaceCb;
	@FXML private ComboBox<String> backgroundCb;
	//@FXML private ComboBox<CharClass> classCb;
	
	@FXML private VBox rightVb;
	
	//Roller stuff
	@FXML private ComboBox<String> rollerCb;
	@FXML private Spinner<Integer> rollerSp;
	@FXML private Label rollerLbl;
	
	//Stages
	private Stage skillsStage;
	private Stage languageStage;
	private Stage savingThrowsStage;
	private Stage descriptionStage;
	private Stage notesStage;
	private Stage personalityStage;
	private Stage featuresStage;
	private Stage inventoryStage;
	private Stage weaponsStage;
	private Stage resourcesStage;
	private Stage spellsStage;
	private Stage removeClassesStage;
	
	private CharClass[] classes;
	private Character c;
	
	public MainpageController(Character character) {
		this.c = character;
	}
	public MainpageController(Character character,String fileName,Stage s) {
		this.c = character;
		this.savedFile = fileName;
		this.stage = s;
	}
	
	public void setCharacter(Character character) {
		this.c = character;
	}
	
	@FXML
	public void nameClick(MouseEvent e) {
		nameTf.setEditable(true);
	}
	
	public void stageInits() {
		languageStage = new Stage();
		savingThrowsStage = new Stage();
		descriptionStage = new Stage();
		notesStage = new Stage();
		skillsStage = new Stage();
		personalityStage = new Stage();
		featuresStage = new Stage();
		inventoryStage = new Stage();
		weaponsStage = new Stage();
		resourcesStage = new Stage();
		spellsStage = new Stage();
		removeClassesStage = new Stage();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		stageInits();
		fillRoller();
		fillClasses();
		fillCharacter();
		fillDeathRolls();
		SaveOnExit();
		//Set name on unfocus
		nameTf.focusedProperty().addListener((ov,oldV,newV) -> {
			if (!newV) {
				c.setName(nameTf.getText());
				nameTf.setEditable(false);
			}
		});
		
		// Death Rolls
		deathRollSuccessBox1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                if (new_val) {
                    c.setSaveRoll(c.getSaveRoll() + 1);
                }
                else if (!new_val) {
                	c.setSaveRoll(c.getSaveRoll() - 1);
                }
            }
        });
		deathRollSuccessBox2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                if (new_val) {
                    c.setSaveRoll(c.getSaveRoll() + 1);
                }
                else if (!new_val) {
                	c.setSaveRoll(c.getSaveRoll() - 1);
                }
            }
        });
		deathRollSuccessBox3.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                if (new_val) {
                    c.setSaveRoll(c.getSaveRoll() + 1);
                }
                else if (!new_val) {
                	c.setSaveRoll(c.getSaveRoll() - 1);
                }
            }
        });
		deathRollFailBox1.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                if (new_val) {
                    c.setDeathRoll(c.getDeathRoll() + 1);
                }
                else if (!new_val) {
                	c.setDeathRoll(c.getDeathRoll() - 1);
                }
            }
        });
		deathRollFailBox2.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                if (new_val) {
                    c.setDeathRoll(c.getDeathRoll() + 1);
                }
                else if (!new_val) {
                	c.setDeathRoll(c.getDeathRoll() - 1);
                }
            }
        });
		deathRollFailBox3.selectedProperty().addListener(new ChangeListener<Boolean>() {
            public void changed(ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) {
                if (new_val) {
                    c.setDeathRoll(c.getDeathRoll() + 1);
                }
                else if (!new_val) {
                	c.setDeathRoll(c.getDeathRoll() - 1);
                }
            }
        });
		
		//races
        ObservableList<String> subRaceOptions = FXCollections.observableArrayList();
        if (c.getRace().equalsIgnoreCase("Dwarf")) {
            subRaceOptions.addAll("Hill Dwarf", "Mountain Dwarf");
        }
        else if (c.getRace().equalsIgnoreCase("Elf")) {
            subRaceOptions.addAll("High Elf", "Wood Elf", "Dark Elf");
        }
        else if (c.getRace().equalsIgnoreCase("Halfling")) {
            subRaceOptions.addAll("Lightfoot","Stout");
        }
        else if (c.getRace().equalsIgnoreCase("Dragonborn")) {
            subRaceOptions.addAll("Black (Acid)", "Blue (Lightning)","Brass (Fire)","Bronze (Lightning)","Copper (Acid)","Gold (Fire)","Green (Poison)", "Red (Fire)", "Silver (Cold)", "White (Cold)");
        }
        else if (c.getRace().equalsIgnoreCase("Gnome")) {
            subRaceOptions.addAll("Forest Gnome", "Rock Gnome");
        }
        else {
            subRaceOptions.addAll("");
        }
		raceCb.valueProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> val, String o, String n) {
				c.setRace(n);
				//Add Subrace stuff here
                if (c.getRace().equalsIgnoreCase("Dwarf")) {
                    subRaceOptions.clear();
                    subRaceOptions.addAll("Hill Dwarf", "Mountain Dwarf");
                }
                else if (c.getRace().equalsIgnoreCase("Elf")) {
                    subRaceOptions.clear();
                    subRaceOptions.addAll("High Elf", "Wood Elf", "Dark Elf");
                }
                else if (c.getRace().equalsIgnoreCase("Halfling")) {
                    subRaceOptions.clear();
                    subRaceOptions.addAll("Lightfoot","Stout");
                }
                else if (c.getRace().equalsIgnoreCase("Dragonborn")) {
                    subRaceOptions.clear();
                    subRaceOptions.addAll("Black (Acid)", "Blue (Lightning)","Brass (Fire)","Bronze (Lightning)","Copper (Acid)","Gold (Fire)","Green (Poison)", "Red (Fire)", "Silver (Cold)", "White (Cold)");
                }
                else if (c.getRace().equalsIgnoreCase("Gnome")) {
                    subRaceOptions.clear();
                    subRaceOptions.addAll("Forest Gnome", "Rock Gnome");
                }
                else {
                    subRaceOptions.clear();
                }

                subRaceCb.setValue("");
                c.setSubrace("");
                subRaceCb.setItems(subRaceOptions);
			}
		});
		
		alignmentCb.valueProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldval, String newval) {
                c.setAlignment(newval);
            }
        });
        
		subRaceCb.valueProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldval, String newval) {
                c.setSubrace(newval);
            }
        });
		
		backgroundCb.valueProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> ov, String oldval, String newval) {
                handleBackgroundChange(oldval,newval);
            }
        });
		
		inspirationTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getInspiration();
			if (newV) {
				oldVal = Integer.parseInt(inspirationTf.getText());
			}
			try {
				if (!newV) {
					c.setInspiration(Integer.parseInt(inspirationTf.getText()));
				}
			}
			catch (Exception e) {
				inspirationTf.setText(Integer.toString(oldVal));
			}
		});
		
		maxHPTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getMaxHP();
			if (newV) {
				oldVal = Integer.parseInt(maxHPTf.getText());
			}
			try {
				if (!newV) {
					c.setMaxHP(Integer.parseInt(maxHPTf.getText()));
				}
			}
			catch (Exception e) {
				maxHPTf.setText(Integer.toString(oldVal));
			}
		});
		
		currHPTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getCurrHP();
			if (newV) {
				oldVal = Integer.parseInt(currHPTf.getText());
			}
			try {
				if (!newV) {
					int val = Integer.parseInt(currHPTf.getText());
					if (val > c.getMaxHP()) {
						c.setCurrHP(c.getMaxHP());
						currHPTf.setText(Integer.toString(c.getCurrHP()));
					}
					else if (val <= 0) {
						c.setCurrHP(0);
						currHPTf.setText(Integer.toString(c.getCurrHP()));
					}
					else {
						c.setCurrHP(val);
					}
				}
			}
			catch (Exception e) {
				maxHPTf.setText(Integer.toString(oldVal));
			}
		});
		
		strTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getAttributes()[0];
			if (newV) {
				oldVal = Integer.parseInt(strTf.getText());
			}
			try {
				if (!newV) {
					c.setStr(Integer.parseInt(strTf.getText()));
					strModLbl.setText(fmt.format(calcMod(c.getAttributes()[0])));
				}
			}
			catch (Exception e) {
				strTf.setText(Integer.toString(oldVal));
			}
		});
		
		consTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getAttributes()[1];
			if (newV) {
				oldVal = Integer.parseInt(consTf.getText());
			}
			try {
				if (!newV) {
					c.setCons(Integer.parseInt(consTf.getText()));
					consModLbl.setText(fmt.format(calcMod(c.getAttributes()[1])));

				}
			}
			catch (Exception e) {
				consTf.setText(Integer.toString(oldVal));
			}
		});
		
		dexTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getAttributes()[2];
			if (newV) {
				oldVal = Integer.parseInt(dexTf.getText());
			}
			try {
				if (!newV) {
					c.setDex(Integer.parseInt(dexTf.getText()));
					dexModLbl.setText(fmt.format(calcMod(c.getAttributes()[2])));

				}
			}
			catch (Exception e) {
				dexTf.setText(Integer.toString(oldVal));
			}
		});
		
		intTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getAttributes()[3];
			if (newV) {
				oldVal = Integer.parseInt(intTf.getText());
			}
			try {
				if (!newV) {
					c.setInt(Integer.parseInt(intTf.getText()));
					intModLbl.setText(fmt.format(calcMod(c.getAttributes()[3])));

				}
			}
			catch (Exception e) {
				intTf.setText(Integer.toString(oldVal));
			}
		});
		
		wisTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getAttributes()[4];
			if (newV) {
				oldVal = Integer.parseInt(wisTf.getText());
			}
			try {
				if (!newV) {
					c.setWis(Integer.parseInt(wisTf.getText()));
					wisModLbl.setText(fmt.format(calcMod(c.getAttributes()[4])));

				}
			}
			catch (Exception e) {
				wisTf.setText(Integer.toString(oldVal));
			}
		});
		
		charTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getAttributes()[5];
			if (newV) {
				oldVal = Integer.parseInt(charTf.getText());
			}
			try {
				if (!newV) {
					c.setChar(Integer.parseInt(charTf.getText()));
					charModLbl.setText(fmt.format(calcMod(c.getAttributes()[5])));

				}
			}
			catch (Exception e) {
				charTf.setText(Integer.toString(oldVal));
			}
		});
		
		acTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getArmor();
			if (newV) {
				oldVal = Integer.parseInt(acTf.getText());
			}
			try {
				if (!newV) {
					c.setArmor(Integer.parseInt(acTf.getText()));
				}
			}
			catch (Exception e) {
				acTf.setText(Integer.toString(oldVal));
			}
		});
		
		initTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getArmor();
			if (newV) {
				oldVal = Integer.parseInt(initTf.getText());
			}
			try {
				if (!newV) {
					c.setInitiative(Integer.parseInt(initTf.getText()));
				}
			}
			catch (Exception e) {
				initTf.setText(Integer.toString(oldVal));
			}
		});
		
		speedTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getSpeed();
			if (newV) {
				oldVal = Integer.parseInt(speedTf.getText());
			}
			try {
				if (!newV) {
					c.setSpeed(Integer.parseInt(speedTf.getText()));
				}
			}
			catch (Exception e) {
				speedTf.setText(Integer.toString(oldVal));
			}
		});
		
		proficiencyTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getProficiencyBonus();
			if (newV) {
				oldVal = Integer.parseInt(proficiencyTf.getText());
			}
			try {
				if (!newV) {
					c.setProficiencyBonus(Integer.parseInt(proficiencyTf.getText()));
					proficiencyTf.setText(fmt.format(c.getProficiencyBonus()));
				}
			}
			catch (Exception e) {
				proficiencyTf.setText(fmt.format(Integer.toString(oldVal)));
			}
		});
		
		cpTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getCp();
			if (newV) {
				oldVal = Integer.parseInt(cpTf.getText());
			}
			try {
				if (!newV) {
					c.setCp(Integer.parseInt(cpTf.getText()));
				}
			}
			catch (Exception e) {
				cpTf.setText(Integer.toString(oldVal));
			}
		});
		
		spTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getSp();
			if (newV) {
				oldVal = Integer.parseInt(spTf.getText());
			}
			try {
				if (!newV) {
					c.setSp(Integer.parseInt(spTf.getText()));
				}
			}
			catch (Exception e) {
				spTf.setText(Integer.toString(oldVal));
			}
		});
		
		epTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getEp();
			if (newV) {
				oldVal = Integer.parseInt(epTf.getText());
			}
			try {
				if (!newV) {
					c.setEp(Integer.parseInt(epTf.getText()));
				}
			}
			catch (Exception e) {
				epTf.setText(Integer.toString(oldVal));
			}
		});
		
		gpTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getGp();
			if (newV) {
				oldVal = Integer.parseInt(gpTf.getText());
			}
			try {
				if (!newV) {
					c.setGp(Integer.parseInt(gpTf.getText()));
				}
			}
			catch (Exception e) {
				gpTf.setText(Integer.toString(oldVal));
			}
		});
		
		ppTf.focusedProperty().addListener((ov,oldV,newV) -> {
			int oldVal = c.getGp();
			if (newV) {
				oldVal = Integer.parseInt(ppTf.getText());
			}
			try {
				if (!newV) {
					c.setPp(Integer.parseInt(ppTf.getText()));
				}
			}
			catch (Exception e) {
				ppTf.setText(Integer.toString(oldVal));
			}
		});
		
	}
	
	@FXML
	public void fillCharacter() {
		nameTf.setText(c.getName());
		inspirationTf.setText(Integer.toString(c.getInspiration()));
		proficiencyTf.setText(fmt.format(c.getProficiencyBonus()));
		strTf.setText(Integer.toString(c.getAttributes()[0]));
		consTf.setText(Integer.toString(c.getAttributes()[1]));
		dexTf.setText(Integer.toString(c.getAttributes()[2]));
		intTf.setText(Integer.toString(c.getAttributes()[3]));
		wisTf.setText(Integer.toString(c.getAttributes()[4]));
		charTf.setText(Integer.toString(c.getAttributes()[5]));
		
		expTf.setText(Integer.toString(c.getExp()));
		maxHPTf.setText(Integer.toString(c.getMaxHP()));
		currHPTf.setText(Integer.toString(c.getCurrHP()));
		tempHPTf.setText(Integer.toString(c.getTempHP()));
		acTf.setText(Integer.toString(c.getArmor()));
		initTf.setText(Integer.toString(c.getInitiative()));
		speedTf.setText(Integer.toString(c.getSpeed()));
		
		cpTf.setText(Integer.toString(c.getCurrency()[0]));
		spTf.setText(Integer.toString(c.getCurrency()[1]));
		epTf.setText(Integer.toString(c.getCurrency()[2]));
		gpTf.setText(Integer.toString(c.getCurrency()[3]));
		ppTf.setText(Integer.toString(c.getCurrency()[4]));
		
		strModLbl.setText(fmt.format(calcMod(c.getAttributes()[0])));
		consModLbl.setText(fmt.format(calcMod(c.getAttributes()[1])));
		dexModLbl.setText(fmt.format(calcMod(c.getAttributes()[2])));
		intModLbl.setText(fmt.format(calcMod(c.getAttributes()[3])));
		wisModLbl.setText(fmt.format(calcMod(c.getAttributes()[4])));
		charModLbl.setText(fmt.format(calcMod(c.getAttributes()[5])));
		
		alignmentCb.setValue(c.getAlignment());
		raceCb.setValue(c.getRace());
		subRaceCb.setValue(c.getSubrace());
		backgroundCb.setValue(c.getBackground());
		
		//Classes
		reloadClasses();
		
		try {
			totalLvlLbl.setText(Integer.toString(calcTotalLvl()));
		}
		catch (NullPointerException ne) {
			totalLvlLbl.setText("0");
		}
	}
	
	
	///////////////////////
	/////// ROLLER ////////
	///////////////////////
	@FXML
	public void fillRoller() {
		SpinnerValueFactory<Integer> rollervf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 200, 1);
		rollerSp.setValueFactory(rollervf);
		rollerSp.focusedProperty().addListener((obs,oldVal,newVal) -> {
			int rollDefault = 0;
			if (!newVal) {
				try {
					rollerSp.increment(0);
				}
				catch (Exception e) {
					rollerSp.getValueFactory().setValue(rollDefault);
				}
			}
		});
	}
	
	@FXML
	public void handleRollerBtn(ActionEvent e) {
		try {
			Random rn = new Random();
			int finalRoll = 0;
			
			int diceNum = rollerSp.getValue();
			String diceType = rollerCb.getValue();
			
			int minRoll = 1;
			int maxRoll = 1;
			if (diceType.equalsIgnoreCase("d4")) {
				maxRoll = 4;
			}
			else if (diceType.equalsIgnoreCase("d6")) {
				maxRoll = 6;
			}
			else if (diceType.equalsIgnoreCase("d8")) {
				maxRoll = 8;
			}
			else if (diceType.equalsIgnoreCase("d10") || diceType.equalsIgnoreCase("d%")) {
				maxRoll = 10;
			}
			else if (diceType.equalsIgnoreCase("d12")) {
				maxRoll = 12;
			}
			else if (diceType.equalsIgnoreCase("d20")) {
				maxRoll = 20;
			}
			else if (diceType.equalsIgnoreCase("d100")) {
				maxRoll = 100;
			}
			
			for (int i = 0; i < diceNum; i++) {
				finalRoll = finalRoll + rn.nextInt(maxRoll - minRoll + 1) + minRoll;
			}
			if (diceType.equalsIgnoreCase("d%")) {
				finalRoll = finalRoll * 10;
			}
			
			rollerLbl.setText(Integer.toString(finalRoll));
			if (finalRoll == 0) {
				rollerLbl.setVisible(false);
			}
			else {
				rollerLbl.setVisible(true);
			}
			
		}
		catch (Exception ex) {
			rollerLbl.setVisible(false);
		}
	}
	
	
	/////////////////////////
	////// CLASS INIT ///////
	/////////////////////////
	@FXML
	public void fillClasses() {
		Gson gson = new Gson();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("classes.json");
		
		try (Reader reader = new BufferedReader(new InputStreamReader(in))) {
			classes = gson.fromJson(reader, CharClass[].class);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
			classes = new CharClass[0];
		}

	}
	
	
	//////////////////
	/////// HP ///////
	//////////////////
	@FXML
	public void handleRefillBtn(ActionEvent e) {
		c.setCurrHP(c.getMaxHP());
		currHPTf.setText(Integer.toString(c.getCurrHP()));
	}
	
	@FXML
	public void handleAddHP(ActionEvent e) {
		try {
			int addHP = c.getCurrHP() + Integer.parseInt(addSubHPTf.getText());
			if (addHP > c.getMaxHP()) {
				c.setCurrHP(c.getMaxHP());
				currHPTf.setText(Integer.toString(c.getCurrHP()));
			}
			else {
				c.setCurrHP(addHP);
				currHPTf.setText(Integer.toString(c.getCurrHP()));
			}
			addSubHPTf.clear();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@FXML
	public void handleSubHP(ActionEvent e) {
		try {
			int subHP = c.getCurrHP() - Integer.parseInt(addSubHPTf.getText());
			if (subHP <= 0) {
				c.setCurrHP(0);
				currHPTf.setText(Integer.toString(c.getCurrHP()));
			}
			else {
				c.setCurrHP(subHP);
				currHPTf.setText(Integer.toString(c.getCurrHP()));
			}
			addSubHPTf.clear();
		}
		catch (Exception ex) {
		}
	}
	
	////////////////////
	///// CURRENCY /////
	////////////////////
	
	@FXML
	public void handleAddCurr(ActionEvent e) {
		try {
			int val = Integer.parseInt(addSpendTf.getText());
			String currencyType = currencyCb.getValue();
			
			if (!currencyType.isEmpty()) {
				if (currencyType.equalsIgnoreCase("CP")) {
					c.setCp(c.getCp() + val);
					cpTf.setText(Integer.toString(c.getCp()));
					addSpendTf.clear();
					currencyCb.setValue("");
				}
				else if (currencyType.equalsIgnoreCase("SP")) {
					c.setSp(c.getSp() + val);
					spTf.setText(Integer.toString(c.getSp()));
					addSpendTf.clear();
					currencyCb.setValue("");
				}
				else if (currencyType.equalsIgnoreCase("EP")) {
					c.setEp(c.getEp() + val);
					epTf.setText(Integer.toString(c.getEp()));
					addSpendTf.clear();
					currencyCb.setValue("");
				}
				else if (currencyType.equalsIgnoreCase("GP")) {
					c.setGp(c.getGp() + val);
					gpTf.setText(Integer.toString(c.getGp()));
					addSpendTf.clear();
					currencyCb.setValue("");
				}
				else if (currencyType.equalsIgnoreCase("PP")) {
					c.setPp(c.getPp() + val);
					ppTf.setText(Integer.toString(c.getPp()));
					addSpendTf.clear();
					currencyCb.setValue("");
				}
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@FXML
	public void handleSpendCurr(ActionEvent e) {
		try {
			int val = Integer.parseInt(addSpendTf.getText());
			String currencyType = currencyCb.getValue();
			
			if (!currencyType.isEmpty()) {
				if (currencyType.equalsIgnoreCase("CP")) {
					int subVal = c.getCp() - val;
					if (subVal < 0) {
						c.setCp(0);
						cpTf.setText(Integer.toString(c.getCp()));
					}
					else {
						c.setCp(c.getCp() - val);
						cpTf.setText(Integer.toString(c.getCp()));
					}
					addSpendTf.clear();
					currencyCb.setValue("");
				}
				else if (currencyType.equalsIgnoreCase("SP")) {
					int subVal = c.getSp() - val;
					if (subVal < 0) {
						c.setSp(0);
						spTf.setText(Integer.toString(c.getSp()));
					}
					else {
						c.setSp(c.getSp() - val);
						spTf.setText(Integer.toString(c.getSp()));
					}
					addSpendTf.clear();
					currencyCb.setValue("");
				}
				else if (currencyType.equalsIgnoreCase("EP")) {
					int subVal = c.getEp() - val;
					if (subVal < 0) {
						c.setEp(0);
						epTf.setText(Integer.toString(c.getEp()));
					}
					else {
						c.setEp(c.getEp() - val);
						epTf.setText(Integer.toString(c.getEp()));
					}
					addSpendTf.clear();
					currencyCb.setValue("");
				}
				else if (currencyType.equalsIgnoreCase("GP")) {
					int subVal = c.getGp() - val;
					if (subVal < 0) {
						c.setGp(0);
						gpTf.setText(Integer.toString(c.getGp()));
					}
					else {
						c.setGp(c.getGp() - val);
						gpTf.setText(Integer.toString(c.getGp()));
					}
					addSpendTf.clear();
					currencyCb.setValue("");
				}
				else if (currencyType.equalsIgnoreCase("PP")) {
					int subVal = c.getPp() - val;
					if (subVal < 0) {
						c.setPp(0);
						ppTf.setText(Integer.toString(c.getPp()));
					}
					else {
						c.setPp(c.getPp() - val);
						ppTf.setText(Integer.toString(c.getPp()));
					}
					addSpendTf.clear();
					currencyCb.setValue("");
				}
			}
			
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	
	//////////////////////
	///// BACKGROUND /////
	//////////////////////
	
	public void handleBackgroundChange(String old, String newVal) {
		String oldVal = "";
		if (old != null) {
			oldVal = old;
		}
		else {
			oldVal = "";
		}
		if (oldVal.equalsIgnoreCase("Acolyte")) {
			c.getProficiencies()[6] = false;
			c.getProficiencies()[14] = false;
		}
		else if (oldVal.equalsIgnoreCase("Charlatan")){
			c.getProficiencies()[4] = false;
			c.getProficiencies()[15] = false;
		}
		else if (oldVal.equalsIgnoreCase("Criminal")) {
			c.getProficiencies()[4] = false;
			c.getProficiencies()[16] = false;
		}
		else if (oldVal.equalsIgnoreCase("Entertainer")) {
			c.getProficiencies()[0] = false;
			c.getProficiencies()[12] = false;
		}
		else if (oldVal.equalsIgnoreCase("Folk Hero")) {
			c.getProficiencies()[1] = false;
			c.getProficiencies()[17] = false;
		}
		else if (oldVal.equalsIgnoreCase("Guild Artisan")) {
			c.getProficiencies()[6] = false;
			c.getProficiencies()[13] = false;
		}
		else if (oldVal.equalsIgnoreCase("Hermit")) {
			c.getProficiencies()[9] = false;
			c.getProficiencies()[14] = false;
		}
		else if (oldVal.equalsIgnoreCase("Noble")) {
			c.getProficiencies()[5] = false;
			c.getProficiencies()[13] = false;
		}
		else if (oldVal.equalsIgnoreCase("Outlander")) {
			c.getProficiencies()[3] = false;
			c.getProficiencies()[17] = false;
		}
		else if (oldVal.equalsIgnoreCase("Sage")) {
			c.getProficiencies()[2] = false;
			c.getProficiencies()[5] = false;
		}
		else if (oldVal.equalsIgnoreCase("Sailor")) {
			c.getProficiencies()[3] = false;
			c.getProficiencies()[11] = false;
		}
		else if (oldVal.equalsIgnoreCase("Soldier")) {
			c.getProficiencies()[3] = false;
			c.getProficiencies()[7] = false;
		}
		else if (oldVal.equalsIgnoreCase("Urchin")) {
			c.getProficiencies()[15] = false;
			c.getProficiencies()[16] = false;
		}
		
		//new stuff
		if (newVal.equalsIgnoreCase("Acolyte")) {
			c.getProficiencies()[6] = true;
			c.getProficiencies()[14] = true;
		}
		else if (newVal.equalsIgnoreCase("Charlatan")){
			c.getProficiencies()[4] = true;
			c.getProficiencies()[15] = true;
		}
		else if (newVal.equalsIgnoreCase("Criminal")) {
			c.getProficiencies()[4] = true;
			c.getProficiencies()[16] = true;
		}
		else if (newVal.equalsIgnoreCase("Entertainer")) {
			c.getProficiencies()[0] = true;
			c.getProficiencies()[12] = true;
		}
		else if (newVal.equalsIgnoreCase("Folk Hero")) {
			c.getProficiencies()[1] = true;
			c.getProficiencies()[17] = true;
		}
		else if (newVal.equalsIgnoreCase("Guild Artisan")) {
			c.getProficiencies()[6] = true;
			c.getProficiencies()[13] = true;
		}
		else if (newVal.equalsIgnoreCase("Hermit")) {
			c.getProficiencies()[9] = true;
			c.getProficiencies()[14] = true;
		}
		else if (newVal.equalsIgnoreCase("Noble")) {
			c.getProficiencies()[5] = true;
			c.getProficiencies()[13] = true;
		}
		else if (newVal.equalsIgnoreCase("Outlander")) {
			c.getProficiencies()[3] = true;
			c.getProficiencies()[17] = true;
		}
		else if (newVal.equalsIgnoreCase("Sage")) {
			c.getProficiencies()[2] = true;
			c.getProficiencies()[5] = true;
		}
		else if (newVal.equalsIgnoreCase("Sailor")) {
			c.getProficiencies()[3] = true;
			c.getProficiencies()[11] = true;
		}
		else if (newVal.equalsIgnoreCase("Soldier")) {
			c.getProficiencies()[3] = true;
			c.getProficiencies()[7] = true;
		}
		else if (newVal.equalsIgnoreCase("Urchin")) {
			c.getProficiencies()[15] = true;
			c.getProficiencies()[16] = true;
		}
		
		c.setBackground(newVal);
	}
	
	/////////////////////
	//// DEATH ROLLS ////
	/////////////////////
	
	public void fillDeathRolls() {
		int deaths =  c.getDeathRoll();
		int saves = c.getSaveRoll();
		
		switch (deaths) {
		case 1: 
			deathRollFailBox1.setSelected(true);
			break;
		case 2:
			deathRollFailBox1.setSelected(true);
			deathRollFailBox2.setSelected(true);
			break;
		case 3:
			deathRollFailBox1.setSelected(true);
			deathRollFailBox2.setSelected(true);
			deathRollFailBox3.setSelected(true);
			break;
		default: break;
		}
		
		switch (saves) {
		case 1:
			deathRollSuccessBox1.setSelected(true);
			break;
		case 2:
			deathRollSuccessBox1.setSelected(true);
			deathRollSuccessBox2.setSelected(true);
			break;
		case 3:
			deathRollSuccessBox1.setSelected(true);
			deathRollSuccessBox2.setSelected(true);
			deathRollSuccessBox3.setSelected(true);
			break;
		default: break;
		}
	}
	
	//////////////////////
	////// CLASSES ///////
	//////////////////////
	public void reloadClasses() {
		ArrayList<CharClass> currentClasses = c.getClss();
		ArrayList<CharClass> tmp = new ArrayList<CharClass>();

		//Make a deep copy of the class array to avoid listener issues
		for (CharClass cl : currentClasses) {
			tmp.add(cl);
		}
		
		for (int i = 0; i < tmp.size(); i++) {
			final int j = i;
			CharClass clss = tmp.get(i);
			ComboBox<CharClass> classCb = new ComboBox<>();
			classCb.setPromptText("Class...");
			classCb.setEditable(true);
			classCb.setPrefWidth(115);
			
			HBox hb = new HBox(10);
			ComboBox<SubClass> subClassCb = new ComboBox<SubClass>();
			subClassCb.setPromptText("Subclass...");
			subClassCb.setEditable(true);
			subClassCb.setPrefWidth(200);
			
			Label lvlLbl = new Label("Lvl:");
			Spinner<Integer> lvlSp = new Spinner<Integer>();
			
			HBox hblvl = new HBox(10);
			hblvl.getChildren().addAll(lvlLbl,lvlSp);
			rightVb.getChildren().add(hblvl);
			
			TextField hitdiceTf = new TextField();
			hitdiceTf.setPrefWidth(55);
			
			hitdiceTf.focusedProperty().addListener((ov,oldV,newV) -> {
				if (!newV) {
	                clss.setHitdice(hitdiceTf.getText());
	                
	            	c.setClss(tmp);
				}
			});
			
			HBox hbsub = new HBox(10);
			
			ObservableList<CharClass> classOptions = FXCollections.observableArrayList();
			for (CharClass c : this.classes) {
				classOptions.add(c);
			}
			classCb.setItems(classOptions);
			hb.getChildren().addAll(classCb,hitdiceTf);
			
			//Display names
			classCb.setConverter(new StringConverter<CharClass>() {
				@Override
				public String toString(CharClass cc) {
					try {
						return cc.getClassName();
					}
					catch (NullPointerException np) {
						return "";
					}
				}
				
				@Override
				public CharClass fromString(String s) {
					return classCb.getItems().stream().filter(ap -> 
							ap.getClassName().equals(s)).findFirst().orElse(null);
				}
			});
		
			hbsub.getChildren().addAll(subClassCb);
			
			ObservableList<SubClass> subClassOptions = FXCollections.observableArrayList();
			
			classCb.valueProperty().addListener(new ChangeListener<CharClass>() {
	            public void changed(ObservableValue<? extends CharClass> ov, CharClass oldval, CharClass newval) {
	            	HashSet<SubClass> subClasses = newval.getSubclassOptions();
	                subClassOptions.clear();
	                for (SubClass sc : subClasses) {
	                	subClassOptions.add(sc);
	                }
	                
	                subClassCb.setItems(subClassOptions);
	                if (!classCb.getValue().getClassName().equals(clss.getClassName())) {
		                subClassCb.setValue(null);
		                newval.setSubclass(null);
	                }
	                
	                currentClasses.set(j,newval);
	        		totalLvlLbl.setText(Integer.toString(calcTotalLvl()));
	        		
	        		
	            }
	        });
			
			
			//Display names
			
			subClassCb.setConverter(new StringConverter<SubClass>() {
				@Override
				public String toString(SubClass sc) {
					try {
						return sc.getName();
					}
					catch (NullPointerException np) {
						return "";
					}
				}
				
				@Override
				public SubClass fromString(String s) {
					return subClassCb.getItems().stream().filter(ap -> 
							ap.getName().equals(s)).findFirst().orElse(null);
				}
			});
			
			subClassCb.valueProperty().addListener(new ChangeListener<SubClass>() {
	            public void changed(ObservableValue<? extends SubClass> ov, SubClass oldval, SubClass newval) {
	            	if (newval != null) {
		                try {
			            	clss.setSubclass(newval);
			            	currentClasses.set(j, clss);
			            	if (clss.getLevel() != lvlSp.getValue()) {
			            		checkSubFeatures(newval, lvlSp.getValue());
			            	}
		                }
		                catch (Exception e) {
		                	e.printStackTrace();
		                	subClassCb.setValue(null);
		                }
	            	}
	            }
	        });
			
			// Handle Spinner increments
			
			SpinnerValueFactory<Integer> lvlvf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, clss.getLevel()) {
				@Override
				public void increment(int steps) {
					this.setValue(this.getValue() + 1);
					try {
						checkFeatures(classCb.getValue(),this.getValue());
					}
					catch (NullPointerException npe) {
						this.setValue(0);
					}
					
					try {
						checkSubFeatures(subClassCb.getValue(), this.getValue());
					}
					catch (NullPointerException npe) {
						
					}
					
	                ArrayList<CharClass> currClssList = c.getClss();
	                try {
	                	int currInd = currClssList.indexOf(classCb.getValue());
	                
		                CharClass currClss = currClssList.get(currInd);
		                currClss.setLevel(this.getValue());
		            	
		            	currClssList.set(currInd,currClss);
		            	c.setClss(currClssList);
		            	
	                }
	                catch (Exception exc) {
	                	exc.printStackTrace();
	                }
					totalLvlLbl.setText(Integer.toString(calcTotalLvl()));
					checkProficiencyLevelUp();
					
				}
				
				@Override
				public void decrement(int steps) {
					this.setValue(this.getValue() - 1);
					ArrayList<CharClass> currClssList = c.getClss();
	                try {
	                	int currInd = currClssList.indexOf(classCb.getValue());
	                
		                CharClass currClss = currClssList.get(currInd);
		                currClss.setLevel(this.getValue());
		            	clss.setLevel(this.getValue());
		            	
		            	currClssList.set(currInd,currClss);
		            	c.setClss(currClssList);
		            	
	                }
	                catch (Exception exc) {
	                	exc.printStackTrace();
	                }
					totalLvlLbl.setText(Integer.toString(calcTotalLvl()));
					checkProficiencyLevelDown();
				}
			};
			
			lvlSp.setValueFactory(lvlvf);
			lvlSp.setPrefWidth(75);
			
			classCb.setValue(clss);
			try {
				subClassCb.setValue(clss.getSubclass());
			}
			catch (NullPointerException npe) {
				subClassCb.setValue(null);
			}
			hitdiceTf.setText(clss.getHitdice());
			
			
			rightVb.getChildren().add(hb);
			rightVb.getChildren().add(hbsub);
		}
		
	}

	@FXML public void handleAddClassBtn() {
		CharClass clss = new CharClass();
		clss.setClassName("N/A");
		clss.setLevel(1);
		
		HBox hb = new HBox(10);
		ComboBox<CharClass> classCb = new ComboBox<>();
		classCb.setPromptText("Class...");
		classCb.setEditable(true);
		classCb.setPrefWidth(115);
		
		ComboBox<SubClass> subClassCb = new ComboBox<SubClass>();
		subClassCb.setPromptText("Subclass...");
		subClassCb.setEditable(true);
		subClassCb.setPrefWidth(200);
		
		Label lvlLbl = new Label("Lvl:");
		Spinner<Integer> lvlSp = new Spinner<Integer>();
		
		HBox hblvl = new HBox(10);
		hblvl.getChildren().addAll(lvlLbl,lvlSp);
		rightVb.getChildren().add(hblvl);
		
		TextField hitdiceTf = new TextField();
		hitdiceTf.setPrefWidth(55);
		
		hitdiceTf.focusedProperty().addListener((ov,oldV,newV) -> {
			if (!newV) {
				ArrayList<CharClass> currClssList = c.getClss();
            	int currInd = currClssList.indexOf(classCb.getValue());
                
                CharClass currClss = currClssList.get(currInd);
                currClss.setHitdice(hitdiceTf.getText());
            	clss.setHitdice(hitdiceTf.getText());
            	
            	currClssList.set(currInd,currClss);
            	c.setClss(currClssList);
			}
		});
		
		HBox hbsub = new HBox(10);
		
		ObservableList<CharClass> classOptions = FXCollections.observableArrayList();
		for (CharClass c : classes) {
			classOptions.add(c);
		}
		classCb.setItems(classOptions);
		hb.getChildren().addAll(classCb,hitdiceTf);
		
		
		//Display names
		classCb.setConverter(new StringConverter<CharClass>() {
			@Override
			public String toString(CharClass cc) {
				try {
					return cc.getClassName();
				}
				catch (NullPointerException np) {
					return "";
				}
			}
			
			@Override
			public CharClass fromString(String s) {
				return classCb.getItems().stream().filter(ap -> 
						ap.getClassName().equals(s)).findFirst().orElse(null);
			}
		});

		
		hbsub.getChildren().addAll(subClassCb);
		
		ObservableList<SubClass> subClassOptions = FXCollections.observableArrayList();
		classCb.valueProperty().addListener(new ChangeListener<CharClass>() {
            public void changed(ObservableValue<? extends CharClass> ov, CharClass oldval, CharClass newval) {
            	HashSet<SubClass> subClasses = newval.getSubclassOptions();
                subClassOptions.clear();
                for (SubClass sc : subClasses) {
                	subClassOptions.add(sc);
                }
                subClassCb.setItems(subClassOptions);
                ArrayList<CharClass> currClss = c.getClss();
                
                currClss.add(newval);
                hitdiceTf.setText(newval.getHitdice());
        		totalLvlLbl.setText(Integer.toString(calcTotalLvl()));
        		
            }
        });
		
		
		//Display names
		
		subClassCb.setConverter(new StringConverter<SubClass>() {
			@Override
			public String toString(SubClass sc) {
				try {
					return sc.getName();
				}
				catch (NullPointerException np) {
					return "";
				}
			}
			
			@Override
			public SubClass fromString(String s) {
				return subClassCb.getItems().stream().filter(ap -> 
						ap.getName().equals(s)).findFirst().orElse(null);
			}
		});
		
		subClassCb.valueProperty().addListener(new ChangeListener<SubClass>() {
            public void changed(ObservableValue<? extends SubClass> ov, SubClass oldval, SubClass newval) {
            	if (newval != null) {
	                ArrayList<CharClass> currClssList = c.getClss();
	                try {
	                	int currInd = currClssList.indexOf(classCb.getValue());
	                
		                CharClass currClss = currClssList.get(currInd);
		                currClss.setSubclass(newval);
		            	clss.setSubclass(newval);
		            	
		            	currClssList.set(currInd,currClss);
		            	c.setClss(currClssList);
		            	checkSubFeatures(newval, lvlSp.getValue());
		            	
	                }
	                catch (Exception e) {
	                	e.printStackTrace();
	                	subClassCb.setValue(null);
	                }
            	}
            }
        });
		
		// Handle Spinner increments
		SpinnerValueFactory<Integer> lvlvf = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 0) {
			@Override
			public void increment(int steps) {
				this.setValue(this.getValue() + 1);
				
				try {
					checkFeatures(classCb.getValue(),this.getValue());
				}
				catch (NullPointerException npe) {
					this.setValue(0);
				}
				
				try {
					checkSubFeatures(subClassCb.getValue(), this.getValue());
				}
				catch (NullPointerException npe) {
					
				}
				
                ArrayList<CharClass> currClssList = c.getClss();
                try {
                	int currInd = currClssList.indexOf(classCb.getValue());
                
	                CharClass currClss = currClssList.get(currInd);
	                currClss.setLevel(this.getValue());
	            	clss.setLevel(this.getValue());
	            	
	            	currClssList.set(currInd,currClss);
	            	c.setClss(currClssList);
	            	
                }
                catch (Exception exc) {
                	exc.printStackTrace();
                }
				totalLvlLbl.setText(Integer.toString(calcTotalLvl()));
				checkProficiencyLevelUp();

			}
			
			@Override
			public void decrement(int steps) {
				this.setValue(this.getValue() - 1);
				ArrayList<CharClass> currClssList = c.getClss();
                try {
                	int currInd = currClssList.indexOf(classCb.getValue());
                
	                CharClass currClss = currClssList.get(currInd);
	                currClss.setLevel(this.getValue());
	            	clss.setLevel(this.getValue());
	            	
	            	currClssList.set(currInd,currClss);
	            	c.setClss(currClssList);
	            	
                }
                catch (Exception exc) {
                	exc.printStackTrace();
                }
				totalLvlLbl.setText(Integer.toString(calcTotalLvl()));
				checkProficiencyLevelDown();
			}
		};
		lvlSp.setValueFactory(lvlvf);
		lvlSp.setPrefWidth(75);
		
		rightVb.getChildren().add(hb);
		rightVb.getChildren().add(hbsub);
	}
	
	@FXML
	public void handleRemoveClassesBtn(ActionEvent e) {
		try {
			removeClassesStage.close();
			removeClassesStage.setTitle("Remove Classes?");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("RemoveClassesPage.fxml"));
			
			RemoveClassesPageController pc = new RemoveClassesPageController(c,removeClassesStage,this);
			loader.setController(pc);
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			
			removeClassesStage.setScene(scene);
			removeClassesStage.show();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void removeClasses(RemoveClassesPageController pc) {
		boolean[] selectedArr = pc.getSelected();
		ArrayList<CharClass> classes = c.getClss();
		for (int i = selectedArr.length - 1; i >= 0; i--) {
			boolean isSelected = selectedArr[i];
			if (isSelected) {
				classes.remove(i);
			}
			rightVb.getChildren().clear();	
			reloadClasses();
		}
	}
	
	///////////////////////
	//// SAVING THROWS ////
	///////////////////////
	
	@FXML
	public void handleSavingThrowsBtn(ActionEvent e) {
		try {
			savingThrowsStage.close();
			savingThrowsStage.setTitle("Saving Throws");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("SavingThrowsPage.fxml"));
			
			SavingThrowsPageController pc = new SavingThrowsPageController(c,savingThrowsStage);
			loader.setController(pc);
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			
			savingThrowsStage.setScene(scene);
			savingThrowsStage.show();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	////////////////
	//// SKILLS ////
	////////////////
	
	@FXML
	public void handleSkillsBtn(ActionEvent e) {
		try {
			skillsStage.close();
			skillsStage.setTitle("Skills");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("SkillsPage.fxml"));
			
			SkillsPageController pc = new SkillsPageController(c,skillsStage);
			loader.setController(pc);
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			
			skillsStage.setScene(scene);
			skillsStage.show();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//////////////////
	//// FEATURES ////
	//////////////////
	
	@FXML
	public void handleFeaturesBtn(ActionEvent e) {
		try {
			featuresStage.close();
			featuresStage.setTitle("Features");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("FeaturesPage.fxml"));
			
			FeaturesPageController pc = new FeaturesPageController(c,featuresStage);
			loader.setController(pc);
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			
			featuresStage.setScene(scene);
			featuresStage.show();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	///////////////////
	//// INVENTORY ////
	///////////////////
	
	@FXML
	public void handleInventoryBtn(ActionEvent e) {
		try {
			inventoryStage.close();
			inventoryStage.setTitle("Inventory");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("InventoryPage.fxml"));
			
			InventoryPageController pc = new InventoryPageController(c,inventoryStage);
			loader.setController(pc);
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			
			inventoryStage.setScene(scene);
			inventoryStage.show();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/////////////////////
	//// PERSONALITY ////
	/////////////////////
	
	@FXML
	public void handlePersonalityBtn(ActionEvent e) {
		try {
			personalityStage.close();
			personalityStage.setTitle("Personality");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("PersonalityPage.fxml"));
			
			PersonalityPageController pc = new PersonalityPageController(c,personalityStage);
			loader.setController(pc);
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			
			personalityStage.setScene(scene);
			personalityStage.show();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	///////////////////
	//// RESOURCES ////
	///////////////////
	
	@FXML
	public void handleResourcesBtn(ActionEvent e) {
		try {
			resourcesStage.close();
			resourcesStage.setTitle("Resources");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("ResourcesPage.fxml"));
			
			ResourcePageController pc = new ResourcePageController(c,resourcesStage);
			loader.setController(pc);
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			
			resourcesStage.setScene(scene);
			resourcesStage.show();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	////////////////
	//// SPELLS ////
	////////////////
	
	@FXML
	public void handleSpellsBtn(ActionEvent e) {
		try {
			spellsStage.close();
			spellsStage.setTitle("Spells");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("SpellsPage.fxml"));
			
			SpellsPageController pc = new SpellsPageController(c,spellsStage);
			loader.setController(pc);
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			
			spellsStage.setScene(scene);
			spellsStage.show();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/////////////////////
	//// DESCRIPTION ////
	/////////////////////
	
	@FXML
	public void handleDescriptionBtn(ActionEvent e) {
		try {
			descriptionStage.close();
			descriptionStage.setTitle("Description");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("DescriptionPage.fxml"));
			
			DescriptionPageController dpc = new DescriptionPageController(c,descriptionStage);
			loader.setController(dpc);
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			
			descriptionStage.setScene(scene);
			descriptionStage.show();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	///////////////
	//// NOTES ////
	///////////////
	
	@FXML
	public void handleNotesBtn(ActionEvent e) {
		try {
			notesStage.close();
			notesStage.setTitle("Notes");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("NotesPage.fxml"));
			
			NotesPageController npc = new NotesPageController(c,notesStage);
			loader.setController(npc);
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			
			notesStage.setScene(scene);
			notesStage.show();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	///////////////
	//// NOTES ////
	///////////////
	
	@FXML
	public void handleWeaponsBtn(ActionEvent e) {
		try {
			weaponsStage.close();
			weaponsStage.setTitle("Weapons");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("WeaponsPage.fxml"));
			
			WeaponsPageController npc = new WeaponsPageController(c,weaponsStage);
			loader.setController(npc);
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			
			weaponsStage.setScene(scene);
			weaponsStage.show();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	///////////////////
	//// LANGUAGES ////
	///////////////////
	
	@FXML
	public void handleLanguagesBtn(ActionEvent e) {
		try {
			languageStage.close();
			languageStage.setTitle("Languages");
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("LanguagesPage.fxml"));
			
			LanguagesPageController lpc = new LanguagesPageController(c,languageStage);
			loader.setController(lpc);
			
			AnchorPane root = (AnchorPane) loader.load();
			
			Scene scene = new Scene(root);
			
			languageStage.setScene(scene);
			languageStage.show();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//////////////////
	////// SAVE //////
	//////////////////
	
	@FXML
	public void handleSaveBtn(ActionEvent e) {
		if (!savedFile.isEmpty()) {
			c.saveCharacter(savedFile);
			savedLbl.setText("Character saved to " + savedFile);
			savedLbl.setVisible(true);
			Timer timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					savedLbl.setVisible(false);
				}
			}, 3000);
		}
		else {
			saveAsBtn.fire();
		}
		
	}
	
	@FXML
	public void handleSaveAsBtn(ActionEvent e) {
		try {
			Stage stage = new Stage();
			Timer timer = new Timer();
			FileChooser fc = new FileChooser();
			fc.setTitle("Save Character");
			File initDir = new File(".");
			fc.setInitialDirectory(initDir);
			
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("D&D Files (*.dnd)","*.dnd");
			fc.getExtensionFilters().add(extFilter);
			
			File file = fc.showSaveDialog(stage);
			String filePath = file.toString();
			
			this.savedFile = filePath;
			
			if (!filePath.isEmpty()) {
				c.saveCharacter(filePath);
				savedLbl.setText("Character Saved to " + savedFile);
				savedLbl.setVisible(true);
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						savedLbl.setVisible(false);
					}
				}, 3000);
			}
		}
		catch (NullPointerException npe) {
			
		}
	}
	
	////////////////
	///// MISC /////
	////////////////
	public void checkFeatures(CharClass cc, int lvl) {
		ArrayList<Integer> lvlFeats = cc.getLvlFeatures();
		ArrayList<Feature> features = cc.getFeatures();
		for (int i = 0; i < lvlFeats.size(); i++) {
			int currLvlFeat = lvlFeats.get(i);
			if (lvl == currLvlFeat) {
				Feature f = features.get(i);
				ArrayList<Feature> currFeats = c.getFeatures();
				if (!currFeats.contains(f)) {
					currFeats.add(f);
					c.setFeatures(currFeats);
					featureNotification(f);
				}
			}
		}
	}
	
	public void checkSubFeatures(SubClass sc, int lvl) {
		ArrayList<Integer> lvlFeats = sc.getLvlFeatures();
		ArrayList<Feature> features = sc.getFeatures();
		for (int i = 0; i < lvlFeats.size(); i++) {
			int currLvlFeat = lvlFeats.get(i);
			if (lvl == currLvlFeat) {
				Feature f = features.get(i);
				ArrayList<Feature> currFeats = c.getFeatures();
				if (!currFeats.contains(f)) {
					currFeats.add(f);
					c.setFeatures(currFeats);
					featureNotification(f);
				}
			}
		}
	}
	
	public void featureNotification(Feature f) {
		try {
			Stage featStage = new Stage();
			featStage.setTitle("New Feature!");
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("FeatureNotification.fxml"));
			
			FeatureNotificationController fnc = new FeatureNotificationController(f,featStage);
			loader.setController(fnc);
			
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
	
			featStage.setScene(scene);
					
			featStage.show();
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	public void checkProficiencyLevelUp() {
		int lvl = calcTotalLvl();
		if (lvl == 5 || lvl == 9 || lvl == 13 || lvl == 17) {
			c.setProficiencyBonus(c.getProficiencyBonus() + 1);
			proficiencyTf.setText(fmt.format(c.getProficiencyBonus()));
		}
	}
	
	public void checkProficiencyLevelDown() {
		int lvl = calcTotalLvl();
		if (lvl == 4 || lvl == 9 || lvl == 12 || lvl == 16) {
			c.setProficiencyBonus(c.getProficiencyBonus() - 1);
			proficiencyTf.setText(fmt.format(c.getProficiencyBonus()));
		}
	}
	
	public int calcTotalLvl() {
		int totLvl = 0;
		ArrayList<CharClass> classList = c.getClss();
		for (int i = 0; i < classList.size(); i++) {
			totLvl = totLvl + classList.get(i).getLevel();
		}
		return totLvl;
	}
	
    public static int calcMod(int stat) {
        double weightedVal = (stat - 10.0) / 2.0;
        int returnVal = (int) Math.floor(weightedVal);
        return returnVal;
    }
    
    
	////////////////////////////////////////
    ///////////// SAVE ON EXIT /////////////
    ////////////////////////////////////////  
	public void SaveOnExit() {    
	    Stage saveExitStage = new Stage();
	    this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	        public void handle(WindowEvent we) {
	            we.consume();
	            saveExitStage.close();
	            saveExitStage.setTitle("Save Character?");
	            GridPane segrid = new GridPane();
	            segrid.setAlignment(Pos.CENTER);
	            segrid.setHgap(10);
	            segrid.setVgap(10);
	            Scene seScene = new Scene(segrid,400,150);
	            saveExitStage.setScene(seScene);
	            saveExitStage.show();

	            Label seLabel = new Label("Save before closing?");
	            seLabel.setFont(Font.font("System",FontWeight.BOLD,20));
	            segrid.add(seLabel,0,0);
	            HBox sebtns = new HBox(15);
	            Button yesSE = new Button("Yes");
	            Button noSE = new Button("No");
	            Button cancelSE = new Button("Cancel");
	            sebtns.getChildren().addAll(yesSE,noSE,cancelSE);
	            segrid.add(sebtns,0,1);

	            yesSE.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent e) {
	                    if (savedFile.isEmpty()) {
	                        saveAsBtn.fire();
	                    }
	                    else {
	                        c.saveCharacter(savedFile);
	                    }
	                    saveExitStage.close();
	                    stage.close();
	                    System.exit(0);
	                }
	            });

	            noSE.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent e) {
	                    //saveExitStage.close();
	                    //stage.close();
	                    System.exit(0);
	                }
	            });
	            cancelSE.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent e) {
	                    saveExitStage.close();
	                }
	            });
	    	}
	    });
    }
}

