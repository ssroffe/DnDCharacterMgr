package CharacterMgrv2;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;

import com.google.gson.Gson;

public class Character {
	//basics
	private String name;
	private String race;
	private String subrace;
	private ArrayList<CharClass> clss;
	private char gender;
	private ArrayList<Weapon> weapons;
	private String background;
	private String alignment;
	
	// health
	private int maxHP;
	private int currHP;
	private int tempHP;
	
	private int level;
	private int exp;
	
	// stats
	private int armor;
	private int initiative;
	private int speed;
	private int proficiencyBonus;
	private int inspiration;
	private String hitdice;
	
	private int[] attributes; //str, cons, dex, intel, wis, char
	
	//money
	private int[] currency; //cp,sp,ep,gp,pp


	// death
	private int deathRoll;
	private int saveRoll;
	
	// spells
	private int spellSaveDC;
	private int spellAttack;
	

	private HashSet<Spell> spells;
	private int spellSlots1;
	private int spellSlots2;
	private int spellSlots3;
	private int spellSlots4;
	private int spellSlots5;
	private int spellSlots6;
	private int spellSlots7;
	private int spellSlots8;
	private int spellSlots9;
	
	//flavor
	private String ideals;
	private String bonds;
	private String flaws;
	private HashSet<String> languages;
	
	private String description;
	private String  notes;
	
	private ArrayList<Feature> features;
	
	private ArrayList<Item> inventory;
	private boolean[] proficiencies;
	private boolean[] savingThrows;
	
	private ArrayList<Resource> resources; //miscellaneous pools

	
	public Character() {
		name = "";
		race = "";
		subrace = "";
		clss = new ArrayList<CharClass>();
		gender = 'n';
		weapons = new ArrayList<Weapon>();
		background = "";
		alignment = "";
		maxHP = 1;
		currHP = 1;
		tempHP = 0;
		level = 1;
		exp = 0;
		armor = 0;
		initiative = 0;
		speed = 0;
		proficiencyBonus = 2;
		inspiration = 0;
		hitdice = "";
		
		attributes = new int[]{0,0,0,0,0,0};
		currency = new int[]{0,0,0,0,0};
		deathRoll = 0;
		saveRoll = 0;
		spellSaveDC = 0;
		spellAttack = 0;
		spells = new HashSet<Spell>();
		spellSlots1 = 0;
		spellSlots2 = 0;
		spellSlots3 = 0;
		spellSlots4 = 0;
		spellSlots5 = 0;
		spellSlots6 = 0;
		spellSlots7 = 0;
		spellSlots8 = 0;
		spellSlots9 = 0;
		ideals = "";
		bonds = "";
		flaws = "";
		languages = new HashSet<String>();
		description = "";
		notes = "";
		features = new ArrayList<Feature>();
		inventory = new ArrayList<Item>();
		proficiencies = new boolean[18];
		savingThrows = new boolean[6];
		resources = new ArrayList<Resource>();
		
	}

	public String getAlignment() {
		return alignment;
	}

	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getSubrace() {
		return subrace;
	}
	public void setSubrace(String subrace) {
		this.subrace = subrace;
	}
	public ArrayList<CharClass> getClss() {
		return clss;
	}
	public void setClss(ArrayList<CharClass> clss) {
		this.clss = clss;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public ArrayList<Weapon> getWeapons() {
		return weapons;
	}
	public void setWeapons(ArrayList<Weapon> weapons) {
		this.weapons = weapons;
	}
	public String getBackground() {
		return background;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public int getMaxHP() {
		return maxHP;
	}
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	public int getCurrHP() {
		return currHP;
	}
	public void setCurrHP(int currHP) {
		this.currHP = currHP;
	}
	public int getTempHP() {
		return tempHP;
	}
	public void setTempHP(int tempHP) {
		this.tempHP = tempHP;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public int getArmor() {
		return armor;
	}
	public void setArmor(int armor) {
		this.armor = armor;
	}
	public int getInitiative() {
		return initiative;
	}
	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getProficiencyBonus() {
		return proficiencyBonus;
	}
	public void setProficiencyBonus(int proficiencyBonus) {
		this.proficiencyBonus = proficiencyBonus;
	}
	public int getInspiration() {
		return inspiration;
	}
	public void setInspiration(int inspiration) {
		this.inspiration = inspiration;
	}
	public String getHitdice() {
		return hitdice;
	}
	public void setHitdice(String hitdice) {
		this.hitdice = hitdice;
	}
	public int[] getCurrency() {
		return currency;
	}
	public void setCurrency(int[] currency) {
		this.currency = currency;
	}
	public void setCp(int c) {
		this.currency[0] = c;
	}
	public int getCp() {
		return currency[0];
	}
	public void setSp(int s) {
		this.currency[1] = s;
	}
	public int getSp() {
		return currency[1];
	}
	public void setEp(int e) {
		this.currency[2] = e;
	}
	public int getEp() {
		return currency[2];
	}
	public void setGp(int g) {
		this.currency[3] = g;
	}
	public int getGp() {
		return currency[3];
	}
	public void setPp(int p) {
		this.currency[4] = p;
	}
	public int getPp() {
		return currency[4];
	}
	public int getDeathRoll() {
		return deathRoll;
	}
	public void setDeathRoll(int deathRoll) {
		this.deathRoll = deathRoll;
	}
	public int getSaveRoll() {
		return saveRoll;
	}
	public void setSaveRoll(int saveRoll) {
		this.saveRoll = saveRoll;
	}
	public int getSpellSaveDC() {
		return spellSaveDC;
	}
	public void setSpellSaveDC(int spellSaveDC) {
		this.spellSaveDC = spellSaveDC;
	}
	public int getSpellAttack() {
		return spellAttack;
	}
	public void setSpellAttack(int spellAttack) {
		this.spellAttack = spellAttack;
	}
	public HashSet<Spell> getSpells() {
		return spells;
	}

	public void setSpells(HashSet<Spell> spells) {
		this.spells = spells;
	}

	public int getSpellSlots1() {
		return spellSlots1;
	}
	public void setSpellSlots1(int spellSlots1) {
		this.spellSlots1 = spellSlots1;
	}
	
	public int getSpellSlots2() {
		return spellSlots2;
	}
	public void setSpellSlots2(int spellSlots2) {
		this.spellSlots2 = spellSlots2;
	}

	public int getSpellSlots3() {
		return spellSlots3;
	}
	public void setSpellSlots3(int spellSlots3) {
		this.spellSlots3 = spellSlots3;
	}

	public int getSpellSlots4() {
		return spellSlots4;
	}
	public void setSpellSlots4(int spellSlots4) {
		this.spellSlots4 = spellSlots4;
	}

	public int getSpellSlots5() {
		return spellSlots5;
	}
	public void setSpellSlots5(int spellSlots5) {
		this.spellSlots5 = spellSlots5;
	}

	public int getSpellSlots6() {
		return spellSlots6;
	}
	public void setSpellSlots6(int spellSlots6) {
		this.spellSlots6 = spellSlots6;
	}

	public int getSpellSlots7() {
		return spellSlots7;
	}
	public void setSpellSlots7(int spellSlots7) {
		this.spellSlots7 = spellSlots7;
	}

	public int getSpellSlots8() {
		return spellSlots8;
	}
	public void setSpellSlots8(int spellSlots8) {
		this.spellSlots8 = spellSlots8;
	}
	
	public int getSpellSlots9() {
		return spellSlots9;
	}
	public void setSpellSlots9(int spellSlots9) {
		this.spellSlots9 = spellSlots9;
	}
	public String getIdeals() {
		return ideals;
	}
	public void setIdeals(String ideals) {
		this.ideals = ideals;
	}
	public String getBonds() {
		return bonds;
	}
	public void setBonds(String bonds) {
		this.bonds = bonds;
	}
	public String getFlaws() {
		return flaws;
	}
	public void setFlaws(String flaws) {
		this.flaws = flaws;
	}
	public HashSet<String> getLanguages() {
		return languages;
	}
	public void setLanguages(HashSet<String> languages) {
		this.languages = languages;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public ArrayList<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(ArrayList<Feature> features) {
		this.features = features;
	}
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	public boolean[] getProficiencies() {
		return proficiencies;
	}
	public void setProficiencies(boolean[] proficiencies) {
		this.proficiencies = proficiencies;
	}
	public void setProficiencyVal(boolean b,int index) {
        this.proficiencies[index] = b;
	}
	public boolean[] getSavingThrows() {
		return savingThrows;
	}
	public void setSavingThrows(boolean[] savingThrows) {
		this.savingThrows = savingThrows;
	}	
	public void setSavingThrowsVal(boolean b, int index) {
		this.savingThrows[index] = b;
	}
	public ArrayList<Resource> getResources() {
		return resources;
	}
	public void setResources(ArrayList<Resource> resources) {
		this.resources = resources;
	}
	
	public int[] getAttributes() {
		return attributes;
	}

	public void setAttributes(int[] attributes) {
		this.attributes = attributes;
	}
	
	public void setStr(int s) {
		this.attributes[0] = s;
	}
	public int getStr() {
		return attributes[0];
	}
	public void setCons(int c) {
		this.attributes[1] = c;
	}
	public int getCons() {
		return attributes[1];
	}
	public void setDex(int d) {
		this.attributes[2] = d;
	}
	public int getDex() {
		return attributes[2];
	}
	public void setInt(int i) {
		this.attributes[3] = i;
	}
	public int getInt() {
		return attributes[3];
	}
	public void setWis(int w) {
		this.attributes[4] = w;
	}
	public int getWis() {
		return attributes[4];
	}
	public void setChar(int c) {
		this.attributes[5] = c;
	}
	public int getChar() {
		return attributes[5];
	}
	
	///////////////////
	//// Save/Load ////
	///////////////////
	
	public boolean saveCharacter(String fileName) {
		Gson gson = new Gson();
		
		try (FileWriter writer = new FileWriter(fileName)) {
			gson.toJson(this,writer);
			return true;
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return false;
	
	}
	
	public static Character loadCharacter(String fileName) {
		Gson gson = new Gson();
		
		Character c = new Character();
		
		try (Reader reader = new FileReader(fileName)) {
			c = gson.fromJson(reader,Character.class);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return c;
	}
	
}
