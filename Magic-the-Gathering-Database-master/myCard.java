package CardAnalyzer.CA;

import java.util.Arrays;
import java.util.List;

public class myCard {
	private String name;
	private String[] types;
	private String[] subtypes;
	private String cost;
	private int cmc;
	private int power = -2;
	private int toughness = -2;
	private String condition;
	private boolean foil;
	private int loyalty = -1;
	
	public myCard() {
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public void setSuperTypes(String[] n) {
		this.types = n;
	}
	
	public void setSubTypes(String[] n) {
		this.subtypes = n;
	}
	
	public void setTypes(String[] n, String[] t) {
		this.types = n;
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
	
	public void printMyCard() {
		//print name and supertypes
		System.out.print("name: " + name + "; types: ");
		for (int i = 0; i < this.types.length; i++) {
			System.out.print(types[i]);
		}
		//print subtypes
		System.out.print("; subtypes: ");
		for (int i = 0; i < this.subtypes.length; i++) {
			System.out.print(subtypes[i]);
		}
		//print mana costs
		System.out.print("; cost: " + this.cost + "; cmc: " + this.cmc);
		//convert to list
		List<String> sptp = Arrays.asList(types);
		//print extra info for creature or planeswalker
		if (sptp.contains("Creatue")) {
			System.out.print("; power: " + this.power + "; toughness: " + this.toughness);
		} else if (sptp.contains("Planeswalker")) {
			System.out.print("; loyalty: " + this.loyalty);
		}
		//condition (including foil)
		System.out.println("; condition: " + this.condition + (foil ? " (foil)" : ""));
	}
}