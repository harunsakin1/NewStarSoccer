package SoccerApp.models;

import SoccerApp.databases.KulupDB;
import SoccerApp.databases.MusabakaDB;
import SoccerApp.entities.Kulup;
import SoccerApp.entities.Lig;
import SoccerApp.entities.Musabaka;

import java.text.Collator;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    databaseModel.musabakaDataBase.findAll().stream().filter(mid -> mid.getDeplasmanID().equals(kulupId) || mid.getEvSahibiID().equals(kulupId))
                                  .sorted(comp.reversed())
              .forEach(musabaka -> musabakaModel.yazdirKulupFikstur(musabaka,databaseModel.kulupDataBase));
}
	
	public void yazdirKuluplerLigdeYerAlan(Lig lig){
		Collator coll = Collator.getInstance(Locale.of("tr"));
		coll.setStrength(Collator.PRIMARY);
		
		lig.getTakimlarIDList().stream().map(id -> databaseModel.kulupDataBase.findByID(id))
		   .filter(optklp -> optklp.isPresent()).map(klp -> klp.get().getAd()).sorted(coll::compare)
		   .forEach(System.out::println);
	}
	
public void yazdirFikstur(Lig lig) {
	String cizgi = "--------------------------------";
	int kulupGenislik = 25;
	int tarihGenislik = 20;
	
	Comparator<Musabaka> comp = (mus1, mus2) -> (int) Duration.between(mus2.getMusabakaTarihi(),
	                                                                   mus1.getMusabakaTarihi()).toDays();
	lig.getFikstur().forEach((k, v) -> {
		System.out.println(k + ". HAFTA\n" + cizgi + cizgi + "\n" + cizgi + cizgi);
		Map<DayOfWeek, List<Musabaka>> mapByDay =
				v.stream().map(id -> databaseModel.musabakaDataBase.findByID(id))
				 .filter(Optional::isPresent).map(Optional::get)
				 .sorted(comp.reversed())
				 .collect(Collectors.groupingBy(ms -> ms.getMusabakaTarihi().getDayOfWeek()));
		
		mapByDay.forEach((dow, ml) -> {
			System.out.println(dow.getDisplayName(TextStyle.FULL,
			                                             Locale.forLanguageTag("tr")).toUpperCase());
			System.out.printf("\t%-" + kulupGenislik + "s %-5s %-" + kulupGenislik + "s %" + tarihGenislik + "s%n",
			                  "Ev Sahibi", "VS", "Deplasman", "Tarih");
			
			ml.forEach(m -> {
				String evSahibiKulupAdi = databaseModel.kulupDataBase.findByID(m.getEvSahibiID()).map(Kulup::getAd).orElse("Bilinmeyen");
				String deplasmanKulupAdi = databaseModel.kulupDataBase.findByID(m.getDeplasmanID()).map(Kulup::getAd).orElse("Bilinmeyen");
				String musabakaTarihi = m.getMusabakaTarihi().toString();
				
				System.out.printf("\t%-" + kulupGenislik + "s %-5s %-" + kulupGenislik + "s %" + tarihGenislik + "s%n",
				                  evSahibiKulupAdi, "-", deplasmanKulupAdi, musabakaTarihi);
			});
			
			System.out.println(cizgi);
		});
	});
}
}