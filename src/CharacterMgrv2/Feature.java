package CharacterMgrv2;

public class Feature {
	private String name;
	private String description;
	
	public Feature() {
		super();
	}
	public Feature(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public Feature(String name) {
		this.name = name;
		this.description = "";
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
	
	public boolean equals(Feature f) {
		if (this.name.equalsIgnoreCase(f.getName()) && this.description.equalsIgnoreCase(f.getDescription())) {
			return true;
		}
		else {
			return false;
		}
	}
}
