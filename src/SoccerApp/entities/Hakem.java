package SoccerApp.entities;

import SoccerApp.utility.enums.EKokart;

public class Hakem extends Insan{
	private EKokart kokart;
	
	public EKokart getKokart() {
		return kokart;
	}
	
	public void setKokart(EKokart kokart) {
		this.kokart = kokart;
	}
	
}