package CharacterMgrv2;

public class Resource {
	private String name;
	private int value;
	
	public Resource() {
		super();
	}
	
	public Resource(String name, int value) {
		super();
		this.name = name;
		this.value = value;
	}
	
	public Resource(String name) {
		this.name = name;
		this.value = 0;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
}
