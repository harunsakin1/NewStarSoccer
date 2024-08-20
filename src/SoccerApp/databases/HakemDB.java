package SoccerApp.databases;

import SoccerApp.entities.Futbolcu;
import SoccerApp.entities.Hakem;
import SoccerApp.utility.DatabaseManager;
import SoccerApp.utility.enums.EKokart;
import SoccerApp.utility.enums.EMevki;
import SoccerApp.utility.enums.EUyruk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class HakemDB extends DatabaseManager<Hakem> {
	public boolean yaratHakem(String tempAd, String tempSoyad, LocalDate tempDogumTarihi, EUyruk tempUyruk,
	                          String tempMaas, EKokart tempKokart) {
		Hakem newHakem = new Hakem(tempAd, tempSoyad, tempDogumTarihi, tempUyruk, tempMaas, tempKokart, getNextId());
		
		return true;
	}
}