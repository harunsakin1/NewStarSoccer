package SoccerApp.databases;

import SoccerApp.entities.Futbolcu;
import SoccerApp.entities.Kulup;
import SoccerApp.entities.Menajer;
import SoccerApp.utility.DatabaseManager;
import SoccerApp.utility.enums.EUyruk;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class KulupDB extends DatabaseManager<Kulup> {
	public boolean yaratKulup(String tempAd, String tempKurulusTarihi, String stadyumId, String tempStadyumAdi,
	                            boolean tempVarMiMenajer, String tempBaskan,
	                            String tempButce,String tempMaasButceYillik) {
		Kulup newKulup = new Kulup(tempAd, tempKurulusTarihi, stadyumId, tempStadyumAdi, tempVarMiMenajer,
		                               tempBaskan, tempButce,tempMaasButceYillik, getNextId());
		
		return true;
	}
	
	public boolean ekleFutbolcu(String futbolcuId, String kulupId, DatabaseManager<Futbolcu> futbolcular){ //oyuncu kulupsuz mu, ekle/ hata
		Optional<Futbolcu> futbolcu = futbolcular.findByID(futbolcuId);
		if (futbolcu.isEmpty()) return false;
		
		Optional<Kulup> kulup = findByID(kulupId);
		if (kulup.isEmpty()) return false;
		
		boolean sahipMiFutbolcuKulube = futbolcu.get().getKulupId().isPresent();
		if (!sahipMiFutbolcuKulube){
			futbolcu.get().setKulupId(kulupId);
		}
		return !sahipMiFutbolcuKulube;
	}
	
	public List<Kulup> araKulupFiltreIsim(String filtre, List<Kulup> veriList){
		return veriList.stream()
				.filter(kulup -> kulup.getAd().toLowerCase().contains(filtre.toLowerCase()))
				.toList();
	}
}