package CharacterMgrv2;

import java.util.ArrayList;
import java.util.HashSet;

public class CharClass {
	private String className;
	private int level;
	private SubClass subclass;
	private HashSet<SubClass> subclassOptions;
	private String hitdice;
	private ArrayList<Integer> lvlFeatures;
	private ArrayList<Feature> features;
	
	public CharClass(String className, int level, SubClass subclass,String hitdice, ArrayList<Integer> lvlFeats,
			ArrayList<Feature> feats) {
		super();
		this.className = className;
		this.level = level;
		this.subclass = subclass;
		this.hitdice = hitdice;
		this.lvlFeatures = lvlFeats;
		this.features = feats;
	}
	
	public CharClass() {
		this.className = "";
		this.level = 0;
		this.subclass = new SubClass();
		this.hitdice = "";
		this.lvlFeatures = new ArrayList<Integer>();
		this.features = new ArrayList<Feature>();
		this.subclassOptions = new HashSet<SubClass>();
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public SubClass getSubclass() {
		return subclass;
	}
	public void setSubclass(SubClass subclass) {
		this.subclass = subclass;
	}

	public String getHitdice() {
		return hitdice;
	}

	public void setHitdice(String hitdice) {
		this.hitdice = hitdice;
	}

	public ArrayList<Integer> getLvlFeatures() {
		return lvlFeatures;
	}

	public void setLvlFeatures(ArrayList<Integer> lvlFeatures) {
		this.lvlFeatures = lvlFeatures;
	}

	public ArrayList<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(ArrayList<Feature> features) {
		this.features = features;
	}

	public HashSet<SubClass> getSubclassOptions() {
		return subclassOptions;
	}

	public void setSubclassOptions(HashSet<SubClass> subclassOptions) {
		this.subclassOptions = subclassOptions;
	}
	
	//public String toString() {
	//	return className;
	//}
}
