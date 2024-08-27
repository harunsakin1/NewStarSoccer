package SoccerApp.models;

import SoccerApp.databases.KulupDB;
import SoccerApp.databases.MusabakaDB;
import SoccerApp.entities.Musabaka;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class MusabakaModel {
	private static MusabakaModel musabakaModel = new MusabakaModel();
	private MusabakaModel() {
	}
	
	public static MusabakaModel getInstance(){
		return musabakaModel;
	}
	
	public void yazdirKulupFikstur(Musabaka musabaka, KulupDB kulupDB){
		String sonuc = (musabaka.getMusabakaTarihi().isAfter(LocalDateTime.now()))? "X - X": musabaka.getEvSahibi().getSkor() + " - " + musabaka.getDeplasman().getSkor();
		String evSahibiAd = kulupDB.findByID(musabaka.getEvSahibi().getKulupId()).get().getAd();
		String deplasmanAd = kulupDB.findByID(musabaka.getDeplasman().getKulupId()).get().getAd();
		LocalDateTime musTarih = musabaka.getMusabakaTarihi();
		String localDateTimeFormat = "%tb %<2td, %<ta %<tH:%<tM, %<tY %n";
		String ayrac = "     -     -     -     -     -     -     -     -     -     -     -     -";
		//System.out.println(evSahibiAd + " " + sonuc + " " + deplasmanAd);
		System.out.printf("\t\t%15s %3s %-15s \t" + localDateTimeFormat, evSahibiAd, sonuc, deplasmanAd,
		                  musTarih);
		System.out.println(ayrac);
	}
}