package SoccerApp.entities;

import SoccerApp.models.DatabaseModel;

import java.util.ArrayList;
import java.util.List;

public class Fikstur {
	
	private List<String> fikstur;
	
	public Fikstur() {
		fikstur=new ArrayList<>();
	}
	
	public void setFikstur(List<String> fikstur) {
		this.fikstur = fikstur;
	}
	
	public List<String> getFikstur() {
		return fikstur;
	}
	
	
	
}