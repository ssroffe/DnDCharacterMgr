package CharacterMgrv2;

public class Item {
	private String name;
	private String description;
	
	public Item() {
		super();
	}
	public Item(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}
	public Item(String name) {
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
	
	public boolean equals(Item f) {
		if (this.name.equalsIgnoreCase(f.getName()) && this.description.equalsIgnoreCase(f.getDescription())) {
			return true;
		}
		else {
			return false;
		}
	}
}
