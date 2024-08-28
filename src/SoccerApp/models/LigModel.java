package SoccerApp.models;

import SoccerApp.entities.Istatistik;
import SoccerApp.entities.Kulup;
import SoccerApp.entities.Lig;
import SoccerApp.entities.Musabaka;
import SoccerApp.utility.enums.ESkorTablosuElemani;

import java.text.Collator;
import java.time.*;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

public class LigModel {
	private DatabaseModel databaseModel = DatabaseModel.getInstance();
	public void yazdirKulupFikstur( Kulup kulup){
		Comparator<Musabaka> comp = (mus1, mus2) -> (int) Duration.between(mus2.getMusabakaTarihi(),
		                                                                   mus1.getMusabakaTarihi()).toDays();
		MusabakaModel musabakaModel = MusabakaModel.getInstance();
    String kulupId = kulup.getId();
    databaseModel.musabakaDataBase.findAll().stream().filter(mid -> mid.getDeplasman().getKulupId().equals(kulupId) || mid.getEvSahibi().getKulupId().equals(kulupId))
                                  .sorted(comp)
              .forEach(musabaka -> musabakaModel.yazdirKulupFikstur(musabaka,databaseModel.kulupDataBase));
}
	
	public List<Kulup> getirKulupleriLigdeYerAlan(Lig lig){
		Collator coll = Collator.getInstance(Locale.of("tr"));
		coll.setStrength(Collator.PRIMARY);
		
		/*return lig.getTakimlarIDList().stream().map(id -> databaseModel.kulupDataBase.findByID(id))
		   .filter(optklp -> optklp.isPresent()).map(klp -> klp.get().getAd()).sorted(coll::compare).toList();*/
		return lig.getTakimlarIDList().stream().map(id -> databaseModel.kulupDataBase.findByID(id))
		          .filter(optklp -> optklp.isPresent()).map(optKlp -> optKlp.get()).sorted((klp1, klp2) -> coll.compare(klp1.getAd(), klp2.getAd())).toList();
	}
	
public void yazdirFikstur(Lig lig) {
	String cizgi = "--------------------------------";
	int kulupGenislik = 25;
	int tarihGenislik = 20;
	
	/*Comparator<Musabaka> comp = (mus1, mus2) -> (int) Duration.between(mus2.getMusabakaTarihi(),
	                                                                   mus1.getMusabakaTarihi()).toDays();*/
	
	lig.getFikstur().forEach((k, v) -> {
		System.out.println(k + ". HAFTA\n" + cizgi + cizgi + "\n" + cizgi + cizgi);
		Map<Integer, List<Musabaka>> mapByDay = new TreeMap<>(
				v.stream().map(id -> databaseModel.musabakaDataBase.findByID(id))
				 .filter(Optional::isPresent).map(Optional::get)
				// .sorted(compNew.reversed())
				 .collect(Collectors.groupingBy(ms -> (ms.getMusabakaTarihi().getDayOfWeek().ordinal()+3)%7)));
		
		mapByDay.forEach((down, ml) -> {
			System.out.println(DayOfWeek.values()[(down+4)%7].getDisplayName(TextStyle.FULL,
			                                             Locale.forLanguageTag("tr")).toUpperCase());
			System.out.printf("\t%-" + kulupGenislik + "s %-5s %-" + kulupGenislik + "s %" + tarihGenislik + "s%n",
			                  "Ev Sahibi", "VS", "Deplasman", "Tarih");
			
			ml.forEach(m -> {
				String evSahibiKulupAdi = databaseModel.kulupDataBase.findByID(m.getEvSahibi().getKulupId()).map(Kulup::getAd).orElse("Bilinmeyen");
				String deplasmanKulupAdi = databaseModel.kulupDataBase.findByID(m.getDeplasman().getKulupId()).map(Kulup::getAd).orElse("Bilinmeyen");
				String musabakaTarihi = m.getMusabakaTarihi().toString();
				
				System.out.printf("\t%-" + kulupGenislik + "s %-5s %-" + kulupGenislik + "s %" + tarihGenislik + "s%n",
				                  evSahibiKulupAdi, "-", deplasmanKulupAdi, musabakaTarihi);
			});
			
			System.out.println(cizgi);
		});
	});
}
	public void goruntulePuanTablosu(Lig lig){
		Integer maksIsimUzunlugu = lig.getTakimlarIDList().stream()
		                           .map(kulup -> databaseModel.kulupDataBase.findByID(kulup).get().getAd().length())
		                           .max(Integer::compareTo).get();
		String format = "%-2s. %" + maksIsimUzunlugu + "s %4s %4s %4s %4s %4s %4s %4s \033[1m %4s \033[0m \n";
		System.out.printf(format, "", "Siralama","O", "G", "B", "M", "A", "Y", "AV", "P");
		System.out.println("  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -  -");
		lig.getPuanTablosu().forEach((siralama, istatistikId) -> {
			Istatistik istatistik = databaseModel.istatistikDataBase.findByID(istatistikId).get();
			
			String kulupAdi =
					databaseModel.kulupDataBase.findByID(String.valueOf(istatistik.getKulupId())).get().getAd();
			int galibiyet = istatistik.getGalibiyet();
			int maglubiyet = istatistik.getMaglubiyet();
			int beraberlik = istatistik.getBeraberlik();
			int oynanan = galibiyet + maglubiyet + beraberlik;
			int atilanGol = istatistik.getAtilanGol();
			int yenenGol = istatistik.getYenilenGol();
			int averaj = atilanGol - yenenGol;
			int puan = galibiyet*3 + beraberlik;
			
			System.out.printf(format, siralama, kulupAdi, oynanan, galibiyet, beraberlik, maglubiyet, atilanGol,
			                  yenenGol, averaj, puan);
		});
	}
	
	public int getirSiralamaVerIstatistikId(Lig lig, String istatistikId){
		for (Map.Entry<Integer, String> entry: lig.getPuanTablosu().entrySet()){
			if (entry.getValue().equals(istatistikId)) return entry.getKey();
		}
		return -1;
	}
}