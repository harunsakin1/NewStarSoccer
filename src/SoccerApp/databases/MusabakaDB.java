package SoccerApp.databases;

import SoccerApp.entities.Musabaka;
import SoccerApp.utility.DatabaseManager;

import java.time.LocalDateTime;
import java.util.List;

public class MusabakaDB extends DatabaseManager<Musabaka> {
	
	public String yaratMusabaka(String kulupId1, String kulupId2, LocalDateTime macTarihi){
		Musabaka musabaka=new Musabaka();
		musabaka.setEvSahibiID(kulupId1);
		musabaka.setDeplasmanID(kulupId2);
		musabaka.setMusabakaTarihi(macTarihi);
		save(musabaka);
		return musabaka.getId();
	}
}