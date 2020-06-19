package CardAnalyzer.CA;

import org.apache.commons.io.FilenameUtils;

public class myCard {
	private String name;
	private String[] supertypes;
	private String[] subtypes;
	private String cost;
	private String[] color;
	private String set;
	private int cmc;
	private int power = -2;
	private int toughness = -2;
	private String condition;
	private boolean foil;
	private int loyalty = -1;
	private String path;
	
	public void setSet(String s) {
		this.set = s;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public void setSuperTypes(String[] n) {
		this.supertypes = n;
	}
	
	public void setSubTypes(String[] n) {
		this.subtypes = n;
	}
	
	public void setTypes(String[] n, String[] t) {
		this.supertypes = n;
		this.subtypes = t;
	}
	
	public void setCost(String n) {
		this.cost = n;
	}
	
	public void setPower(int n) {
		this.power = n;
	}
	
	public void setToughness(int n) {
		this.toughness = n;
	}
	
	public void setPT(int n, int t) {
		this.power = n;
		this.toughness = t;
	}
	
	public void setColor(String[] c) {
		this.color = c;
	}
	
	public void setCMC(int n) {
		this.cmc = n;
	}
	
	public void setCondition(String n) {
		this.condition = n;
	}
	
	public void setFoil(boolean n) {
		this.foil = n;
	}
	
	public void setLoyalty(int n) {
		this.loyalty = n;
	}
	
	public void setPath(String p) {
		this.path = p;
	}
	
	public String getSet() {
		return this.set;
	}
	
	public String setName() {
		return this.name;
	}
	
	public String[] getSuperTypes() {
		return this.supertypes;
	}
	
	public String[] getSubTypes() {
		return this.subtypes;
	}
	
	public String setCost() {
		return this.cost;
	}
	
	public int getPower() {
		return this.power;
	}
	
	public int getToughness() {
		return this.toughness;
	}
	
	public String[] getColor() {
		return this.color;
	}
	
	public int getCMC() {
		return this.cmc;
	}
	
	public String getCondition() {
		return this.condition;
	}
	
	public boolean getFoil() {
		return this.foil;
	}
	
	public int getLoyalty() {
		return this.loyalty;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public int getID() {
		return Integer.parseInt(FilenameUtils.getName(FilenameUtils.removeExtension(path)));
	}
	
	public void printMyCard() {
		//print name and supertypes
		System.out.print("name: " + name + "; supertypes: ");
		for (int i = 0; i < this.supertypes.length; i++) {
			System.out.print(this.supertypes[i] + ((i != this.supertypes.length - 1)? " " : ""));
		}
		//print subtypes
		System.out.print("; subtypes: ");
		for (int i = 0; i < this.subtypes.length; i++) {
			System.out.print(this.subtypes[i]);
		}
		System.out.print("; set: " + this.set);
		//print mana costs
		System.out.print("; cost: " + this.cost + "; cmc: " + this.cmc);
		//print color
		System.out.print("; color: ");
		for (int i = 0; i < this.color.length; i++) {
			System.out.print(this.color[i] + ((i != this.color.length - 1)? " " : "")); 
		}
		//print extra info for creature or planeswalker
		System.out.print("; power: " + this.power + "; toughness: " + this.toughness);
		System.out.print("; loyalty: " + this.loyalty);
		//condition (including foil)
		System.out.print("; condition: " + this.condition + (foil ? " (foil)" : ""));
		//print path
		System.out.println("; path: " + this.getID());
	}
}