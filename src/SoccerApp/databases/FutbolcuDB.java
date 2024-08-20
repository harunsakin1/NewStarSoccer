package SoccerApp.databases;

import SoccerApp.entities.BaseEntity;
import SoccerApp.entities.Futbolcu;
import SoccerApp.utility.DatabaseManager;
import SoccerApp.utility.enums.EMevki;
import SoccerApp.utility.enums.EUyruk;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FutbolcuDB extends DatabaseManager<Futbolcu> {
	
	public boolean yaratFutbolcu(String tempAd, String tempSoyad, LocalDate tempDogumTarihi, EUyruk tempUyruk,
	                             String tempMaas, int tempFormaNumarasi, String tempBonservis, EMevki tempMevki,
	                             int tempYetenekPuani, String tempKulupId){
		Futbolcu newFutbolcu =
				new Futbolcu(tempAd, tempSoyad, tempDogumTarihi, tempUyruk, tempMaas, tempFormaNumarasi, tempBonservis, tempMevki, tempYetenekPuani, tempKulupId, getNextId());
		
		return true;
	}
	
	
	
	public List<Futbolcu> bulFutbolcularKulupId(String kulupId){
		return veriListesi.stream()
				.filter(futbolcu -> futbolcu.getKulupId().isPresent())
				.filter(futbolcu -> futbolcu.getKulupId().get().equals(kulupId))
				.toList();
	}
}