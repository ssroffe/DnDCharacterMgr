package CharacterMgrv2;

public class Weapon {
	private String name;
	private String damage;
	private boolean proficient;
	private boolean finesse;
	private String damageType;
	
	public Weapon(String n) {
		this.name = n;
		this.damage = "";
		this.proficient = false;
		this.finesse = false;
		this.damageType = "";
	}
	
	public Weapon(String name, String damage, boolean proficient, boolean finesse, String damageType) {
		this.name = name;
		this.damage = damage;
		this.proficient = proficient;
		this.finesse = finesse;
		this.damageType = damageType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	public boolean isProficient() {
		return proficient;
	}

	public void setProficient(boolean proficient) {
		this.proficient = proficient;
	}

	public boolean isFinesse() {
		return finesse;
	}

	public void setFinesse(boolean finesse) {
		this.finesse = finesse;
	}

	public String getDamageType() {
		return damageType;
	}

	public void setDamageType(String damageType) {
		this.damageType = damageType;
	}
	
	
}
