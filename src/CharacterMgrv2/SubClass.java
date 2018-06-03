package CharacterMgrv2;

import java.util.ArrayList;

public class SubClass {
	private String name;
	private ArrayList<Integer> lvlFeatures;
	private ArrayList<Feature> features;
	private String description;
	
	public SubClass() {
		super();
	}
	public SubClass(String name, ArrayList<Feature> features,ArrayList<Integer> lvlFeatures, String description) {
		super();
		this.name = name;
		this.features = features;
		this.description = description;
		this.lvlFeatures = lvlFeatures;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Feature> getFeatures() {
		return features;
	}
	public void setFeatures(ArrayList<Feature> features) {
		this.features = features;
	}
	public ArrayList<Integer> getLvlFeatures() {
		return lvlFeatures;
	}
	public void setLvlFeatures(ArrayList<Integer> lvlFeatures) {
		this.lvlFeatures = lvlFeatures;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String toString() {
		return name;
	}
	//public SubClass fromString() {
	//	return this;
	//}
}
