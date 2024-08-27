package SoccerApp.databases;

import SoccerApp.entities.Musabaka;
import SoccerApp.utility.DatabaseManager;

import java.time.LocalDateTime;
import java.util.List;

public class MusabakaDB extends DatabaseManager<Musabaka> {
	private static MusabakaDB musabakaDB = new MusabakaDB();
	private MusabakaDB() {}
	public static MusabakaDB getInstance() {
		return musabakaDB;
	}
	public String yaratMusabaka(String kulupId1, String kulupId2, LocalDateTime macTarihi){
		Musabaka musabaka=new Musabaka();
		musabaka.getEvSahibi().setKulupId(kulupId1);
		musabaka.getDeplasman().setKulupId(kulupId2);
		musabaka.setMusabakaTarihi(macTarihi);
		save(musabaka);
		return musabaka.getId();
	}
}