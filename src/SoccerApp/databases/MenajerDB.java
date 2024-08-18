package SoccerApp.databases;

import SoccerApp.entities.Menajer;
import SoccerApp.entities.Stadyum;
import SoccerApp.utility.DatabaseManager;
import SoccerApp.utility.enums.EUyruk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;

public class MenajerDB extends DatabaseManager<Menajer> {
	public boolean yaratMenajer(String tempAd, String tempSoyad, LocalDate tempDogumTarihi, EUyruk tempUyruk,
	                            String tempMaas, String tempKulupID,
	                            int tempYil, String tempSifre) {
		String nextId = getNextId();
		Menajer newMenajer = new Menajer(tempAd,tempSoyad,tempDogumTarihi,tempUyruk,tempMaas,tempKulupID, tempYil,
		                                nextId , tempSifre);
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("E:\\JavaDersleri\\NewStarSoccer" +
				                                                                          "\\src\\SoccerApp\\build" +
				                                                                          "\\menajerler.bin"))) {
			veriListesi.add(newMenajer);
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