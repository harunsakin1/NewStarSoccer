package SoccerApp.databases;

import SoccerApp.entities.Futbolcu;
import SoccerApp.utility.DatabaseManager;

import java.util.List;

public class FutbolcuDB extends DatabaseManager<Futbolcu> {
	
	
	
	public List<Futbolcu> bulFutbolcularKulupId(String kulupId){
		return veriListesi.stream()
				.filter(futbolcu -> futbolcu.getKulupId().isPresent())
				.filter(futbolcu -> futbolcu.getKulupId().get().equals(kulupId))
				.toList();
	}
}