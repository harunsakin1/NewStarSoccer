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
	public void yazdirKuluplerLigdeYerAlan(Lig lig, KulupDB kulupDB){
		Collator coll = Collator.getInstance(Locale.of("tr"));
		coll.setStrength(Collator.PRIMARY);
		
		lig.getTakimlarIDList().stream().map(id -> kulupDB.findByID(id))
		   .filter(optklp -> optklp.isPresent()).map(klp -> klp.get().getAd()).sorted(coll::compare)
		   .forEach(System.out::println);
	}
	
public void yazdirFikstur(Lig lig, MusabakaDB musabakaDB, KulupDB kulupDB) {
	String cizgi = "--------------------------------";
	int kulupGenislik = 25;
	int tarihGenislik = 20;
	
	Comparator<Musabaka> comp = (mus1, mus2) -> (int) Duration.between(mus2.getMusabakaTarihi(),
	                                                                   mus1.getMusabakaTarihi()).toDays();
	
	lig.getFikstur().forEach((k, v) -> {
		System.out.println(k + ". HAFTA\n" + cizgi + cizgi + "\n" + cizgi + cizgi);
		Map<DayOfWeek, List<Musabaka>> mapByDay =
				v.stream().map(id -> musabakaDB.findByID(id))
				 .filter(Optional::isPresent).map(Optional::get)
				 .sorted(comp.reversed())
				 .collect(Collectors.groupingBy(ms -> ms.getMusabakaTarihi().getDayOfWeek()));
		
		mapByDay.forEach((dow, ml) -> {
			System.out.println(dow.getDisplayName(TextStyle.FULL,
			                                             Locale.forLanguageTag("tr")).toUpperCase());
			System.out.printf("\t%-" + kulupGenislik + "s %-5s %-" + kulupGenislik + "s %" + tarihGenislik + "s%n",
			                  "Ev Sahibi", "VS", "Deplasman", "Tarih");
			
			ml.forEach(m -> {
				String evSahibiKulupAdi = kulupDB.findByID(m.getEvSahibiID()).map(Kulup::getAd).orElse("Bilinmeyen");
				String deplasmanKulupAdi = kulupDB.findByID(m.getDeplasmanID()).map(Kulup::getAd).orElse("Bilinmeyen");
				String musabakaTarihi = m.getMusabakaTarihi().toString();
				
				System.out.printf("\t%-" + kulupGenislik + "s %-5s %-" + kulupGenislik + "s %" + tarihGenislik + "s%n",
				                  evSahibiKulupAdi, "-", deplasmanKulupAdi, musabakaTarihi);
			});
			
			System.out.println(cizgi);
		});
	});
}
	/*
	hafta1:
		cuma(localdatetime):
			Ev sahibi   VS     Deplasman
			    A        -         B
			    C        -         D
	hafta2:
		fdslfds
	 */
}