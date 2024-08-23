package SoccerApp.models;

import SoccerApp.databases.KulupDB;
import SoccerApp.entities.Musabaka;

import java.time.LocalDateTime;

public class MusabakaModel {
	private static MusabakaModel musabakaModel = new MusabakaModel();
	private MusabakaModel(){}
	public static MusabakaModel getInstance(){
		return musabakaModel;
	}
	public void yazdirKulupFikstur(Musabaka musabaka, KulupDB kulupDB){
		String sonuc = (musabaka.getMusabakaTarihi().isAfter(LocalDateTime.now()))? "X - X": musabaka.getEvSahibiSkor() + " - " + musabaka.getDeplasmanSkor();
		String evSahibiAd = kulupDB.findByID(musabaka.getEvSahibiID()).get().getAd();
		String deplasmanAd = kulupDB.findByID(musabaka.getDeplasmanID()).get().getAd();
		System.out.println(evSahibiAd + " " + sonuc + " " + deplasmanAd);
	}
}