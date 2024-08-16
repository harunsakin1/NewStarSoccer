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
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:\\JavaDersleri\\NewStarSoccer" +
				                                                                          "\\src\\SoccerApp\\build" +
				                                                                          "\\hakemler.bin"))) {
			veriListesi.add(newHakem);
			oos.writeObject(veriListesi);
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}