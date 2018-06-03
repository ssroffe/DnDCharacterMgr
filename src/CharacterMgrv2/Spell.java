package CharacterMgrv2;

import java.util.HashSet;

public class Spell {
	private String name;
	private String description;
	private String range;
	private String components;
	private boolean ritual; //maybe a boolean?
	private String duration;
	private boolean concentration; //maybe a boolean?
	private String casting_time;
	private int level;
	private String school;
	private HashSet<String> clss;
	private HashSet<String> subclass;
	
	
	public Spell() {
		name = "";
		description = "";
		range = "";
		components = "";
		ritual = false;
		duration = "";
		concentration = false;
		casting_time = "";
		level = 0;
		school = "";
		clss = new HashSet<String>();
		subclass = new HashSet<String>();
	}
	
	public Spell(String name, String description, String range, String components, boolean ritual,
			String duration, boolean concentration, String casting_time, int level, String school, HashSet<String> clss,
			HashSet<String> subclass) {
		super();
		this.name = name;
		this.description = description;
		this.range = range;
		this.components = components;
		this.ritual = ritual;
		this.duration = duration;
		this.concentration = concentration;
		this.casting_time = casting_time;
		this.level = level;
		this.school = school;
		this.clss = clss;
		this.subclass = subclass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getComponents() {
		return components;
	}
	public void setComponents(String components) {
		this.components = components;
	}
	public boolean getRitual() {
		return ritual;
	}
	public void setRitual(boolean ritual) {
		this.ritual = ritual;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public boolean getConcentration() {
		return concentration;
	}
	public void setConcentration(boolean concentration) {
		this.concentration = concentration;
	}
	public String getCasting_time() {
		return casting_time;
	}
	public void setCasting_time(String casting_time) {
		this.casting_time = casting_time;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public HashSet<String> getClss() {
		return clss;
	}
	public void setClss(HashSet<String> clss) {
		this.clss = clss;
	}
	public HashSet<String> getSubclass() {
		return subclass;
	}
	public void setSubclass(HashSet<String> subclass) {
		this.subclass = subclass;
	}
	
	
}
