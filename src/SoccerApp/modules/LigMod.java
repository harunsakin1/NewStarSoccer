package SoccerApp.modules;

import SoccerApp.NewStarSoccerApp;
import SoccerApp.databases.*;
import SoccerApp.entities.Fikstur;
import SoccerApp.entities.Kulup;
import SoccerApp.entities.Lig;
import SoccerApp.entities.Musabaka;
import SoccerApp.models.DatabaseModel;
import SoccerApp.utility.enums.EBolge;
import SoccerApp.utility.enums.EKume;

import java.time.*;
import java.util.*;
import java.util.stream.IntStream;

public class LigMod {
	private static FutbolcuDB futbolcuDB = DatabaseModel.futbolcuDataBase;
	private static HakemDB hakemDB = DatabaseModel.hakemDataBase;
	private static KulupDB kulupDB = DatabaseModel.kulupDataBase;
	private static MenajerDB menajerDB = DatabaseModel.menajerDataBase;
	private static LigDB ligDB = DatabaseModel.ligDataBase;
	private static MusabakaDB musabakaDB = DatabaseModel.musabakaDataBase;
	private static StadyumDB stadyumDB = DatabaseModel.stadyumDataBase;
	private static Scanner scanner = new Scanner(System.in);
	private static Random rnd = new Random();
	
	public static int yapSecim() {
		return yapSecim("Secim yapiniz:");
	}
	
	public static int yapSecim(String mesaj) {
		return NewStarSoccerApp.yapSecim(mesaj);
	}
	
	public static int menu() {
		int secim;
		do {
			System.out.println("""
					                    1. Olustur Lig
					                    2. Kulup Ekle Lige
					                    3. Goruntule Lig
					                    4. Fikstur Olustur
					                    5. Goruntule Fikstur
					                    0. Geri Don
					                   -1. Kapa programi
					                    """);
			secim = yapSecim();
			secim = menuSecenekleri(secim);
			
		} while (secim != 0);
		return secim;
	}
	
	public static int menuSecenekleri(int secim) {
		switch (secim) {
			case 1:
				yaratLig();
				break;
			case 2:
				if (!ekleLigeKulupMenu()) {
					System.out.println("Ekleme islemi basarisizlikle sonuclandi :((");
				}
				break;
			case 3:
				goruntuleLig();
				break;
			case 4:
				olusturFikstur();
				break;
			case 5:
				goruntuleFikstur();
				break;
			default:
				System.out.println("Girdi gecersiz x_x");
		}
		
		return secim;
	}
	
	private static void goruntuleFikstur() {
		System.out.print("Lig ID giriniz : ");
		String ligID = scanner.nextLine();
		Optional<Lig> optionalLig = ligDB.findByID(ligID);
		if (optionalLig.isEmpty()){
			return;
		}
		Fikstur fikstur = optionalLig.get().getFikstur();
		fikstur.getFikstur().stream().sorted(Comparator.comparing(id -> musabakaDB.findByID(id).get()
		                      .getMusabakaTarihi())).forEach(musabaka->{
			String evSahibiID = musabakaDB.findByID(musabaka).get().getEvSahibiID();
			String deplasmanID = musabakaDB.findByID(musabaka).get().getDeplasmanID();
			LocalDateTime musabakaTarihi = musabakaDB.findByID(musabaka).get().getMusabakaTarihi();
			String evSahibiAd = kulupDB.findByID(evSahibiID).get().getAd();
			String deplasmanAd = kulupDB.findByID(deplasmanID).get().getAd();
			System.out.println(evSahibiAd+" - "+deplasmanAd+" Tarih : "+musabakaTarihi);
		});
	}
	
	
	
	
	private static void olusturFikstur() {
		System.out.print("Lig id giriniz: ");
		String ligId = scanner.nextLine();
		Optional<Lig> optLig = ligDB.findByID(ligId);
		Lig lig;
		if (optLig.isEmpty()) {
			System.out.println("Girdiginiz lig bulunamadi");
			return;
		}
		else if ((lig = optLig.get()).getTakimlarIDList().size() != lig.MAKS_LIG_TAKIM_SAYISI) {
			System.out.println("Henuz ligde yeterince katilimci kulup tanimlanmamistir");
			return;
		}
		fiksturOlustur(ligId);
	}
	
	private static boolean ekleLigeKulupMenu() {
		int secim = yapSecim("Kac adet kulup ekleyeceksiniz?");
		if (secim < 1) return false;
		else if (secim == 1) return ekleLigeKulup();
		else return ekleLigeKulupler();
	}
	
	private static boolean ekleLigeKulupler() {
		int kulupId;
		List<String> kulupIdler = new ArrayList<>();
		System.out.print("Lig id giriniz:");
		String ligId = scanner.nextLine();
		do {
			System.out.print("Eklemek istediginiz kulubun id'sini giriniz\n(Cikis icin 0 veya negatif deger giriniz):" + " " + " ");
			kulupId = yapSecim();
			kulupIdler.add(String.valueOf(kulupId));
		} while (kulupId >= 1);
		kulupIdler.removeLast();
		return ligDB.ekleKulupler(kulupIdler, ligId);
	}
	
	private static boolean ekleLigeKulup() {
		System.out.print("Kulup eklenecek ligin id'sini giriniz: ");
		String ligId = scanner.nextLine();
		System.out.print("Kulup id'sini giriniz: ");
		String kulupId = scanner.nextLine();
		return ligDB.ekleKulup(ligId, kulupId);
	}
	
	public static void yaratLig() {
		Lig lig = new Lig(20);
		System.out.print("Lig adı giriniz : ");
		String ad = scanner.nextLine();
		System.out.println("Bolge giriniz : ");
		String bolge = scanner.nextLine();
		System.out.println("Küme giriniz : ");
		String kume = scanner.nextLine();
		System.out.println("Lig başlangıç tarihini giriniz : (yyyy-MM-dd) ");
		String baslangicTarihi = scanner.nextLine();
		System.out.println("Lig bitiş tarihini giriniz : (yyyy-MM-dd) ");
		String bitisTarihi = scanner.nextLine();
		lig.setAd(ad);
		lig.setBolge(EBolge.valueOf(bolge));
		lig.setKume(EKume.valueOf(kume));
		lig.setBaslangicTarihi(LocalDate.parse(baslangicTarihi));
		lig.setBitisTarihi(LocalDate.parse(bitisTarihi));
		lig.setId("1");
		ligDB.save(lig);
		System.out.println("Lig basariyla eklendi!");
	}
	
	public static void goruntuleLig() {
		System.out.print("Lig ID giriniz : ");
		String ligID = scanner.nextLine();
		ligDB.listeleLigdekiKulupleri(ligID)
		     .forEach(kulupId -> System.out.println(kulupDB.findByID(kulupId).get().getAd()));
	}
	
	public static List<List<LocalDateTime>> belirleMacVakitleri(String ligID) {
		Optional<Lig> optionalLig = ligDB.findByID(ligID);
		if (optionalLig.isEmpty()) {
			return null;
		}
		Lig lig = optionalLig.get();
		int toplamHaftaSayisi = lig.getMaksLigTakimSayisi() - 1;
		LocalDate ilkMacGunu = bulIlkCuma(lig.getBaslangicTarihi());
		
		List<List<LocalDateTime>> macTarihleri = new ArrayList<>();
		for (int i = 0; i < toplamHaftaSayisi; i++) {
			macTarihleri.add(haftaninVakitleri(ilkMacGunu,i));
			
		}
		return macTarihleri;
	}
	
	public static List<LocalDateTime> haftaninVakitleri(LocalDate ilkMacGunu, long hafta) {
		List<LocalDateTime> macTarihleri = new ArrayList<>();
		
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22), rnd.nextInt(0, 60))));
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22), rnd.nextInt(0, 60))));
		
		
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(1).plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                       rnd.nextInt(0, 60))));
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(1).plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                       rnd.nextInt(0, 60))));
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(1).plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                       rnd.nextInt(0, 60))));
		
		
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(2).plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                       rnd.nextInt(0, 60))));
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(2).plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                       rnd.nextInt(0, 60))));
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(2).plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                       rnd.nextInt(0, 60))));
		
		
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(3).plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                       rnd.nextInt(0, 60))));
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(3).plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                       rnd.nextInt(0, 60))));
		return macTarihleri;
	}
	
	public static LocalDate bulIlkCuma(LocalDate ligBaslangicTarihi) {
		for (int i = 0; i < 7; i++) {
			LocalDate siradakiGun = ligBaslangicTarihi.plusDays(i);
			if (siradakiGun.getDayOfWeek() == DayOfWeek.FRIDAY) {
				return siradakiGun;
			}
		}
		return null;
	}
	
	public static void fiksturOlustur(String ligID) {
		Fikstur fikstur=new Fikstur();
		Optional<Lig> optionalLig = ligDB.findByID(ligID);
		
		if (optionalLig.isEmpty()) {
			return;
		}
		Lig lig = optionalLig.get();
		List<List<LocalDateTime>> macVakitleri = belirleMacVakitleri(ligID);
		List<String> kuluplerList = lig.getTakimlarIDList();
		List<String> musabakaIdlerList=new ArrayList<>();
		List<Integer> haftaNumaralari;
		for (int i = 0; i < kuluplerList.size() - 1; i++) {
			haftaNumaralari = new ArrayList<>(IntStream.iterate(0, sayi -> sayi + 1).limit(19).boxed().toList());
			haftaNumaralari.removeAll(bulHangiHaftaMaciVar(kuluplerList.get(i),musabakaIdlerList,i,kuluplerList));
			List<String> rakipKuluplerID=kuluplerList.subList(i+1,kuluplerList.size());
			for (int j = i + 1; j < kuluplerList.size(); j++) {
				String rastgeleRakipID = rakipKuluplerID.get(rnd.nextInt(rakipKuluplerID.size()));
				rakipKuluplerID.remove(rastgeleRakipID);
				Integer rastgeleHafta = haftaNumaralari.get(rnd.nextInt(haftaNumaralari.size()));
				haftaNumaralari.remove(rastgeleHafta);
				LocalDateTime belirlenenVakit = macVakitleri.get(rastgeleHafta).get(rnd.nextInt(0, 10));
				macVakitleri.get(rastgeleHafta).remove(belirlenenVakit);
				musabakaIdlerList.addAll(yaratMusabaka(kuluplerList.get(i),rastgeleRakipID,belirlenenVakit));
				
			}
		}
		fikstur.setFikstur(musabakaIdlerList);
		lig.setFikstur(fikstur);
	}
	public static List<Integer> bulHangiHaftaMaciVar(String kulupID,List<String> musabakaIDlerList, int i,
	                                           List<String> kuluplerList){
		List<Integer> macOlanHaftalarList=new ArrayList<>(musabakaIDlerList.stream()
		                                                                   .filter(musabaka -> musabakaDB.findByID(musabaka).get().getEvSahibiID().equals(kuluplerList.get(i))
				                                                                   || musabakaDB.findByID(musabaka).get().getDeplasmanID().equals(kuluplerList.get(i)))
		                                                                   .map(Integer::parseInt)
		                                                                   .toList());
		return macOlanHaftalarList;
	}
	//TODO refactor islemi yap (sayfadaki butun kodlar)
	public static List<String> yaratMusabaka(String kulupId1, String kulupId2, LocalDateTime macTarihi) {
		List<String> musabakaList = new ArrayList<>();
		switch (rnd.nextInt(1, 3)) {
			case 1:
				musabakaList.add(musabakaDB.yaratMusabaka(kulupId1, kulupId2, macTarihi));
				musabakaList.add(musabakaDB.yaratMusabaka(kulupId2, kulupId1, macTarihi.plusWeeks(19)));
				break;
			case 2:
				musabakaList.add(musabakaDB.yaratMusabaka(kulupId2, kulupId1, macTarihi));
				musabakaList.add(musabakaDB.yaratMusabaka(kulupId1, kulupId2, macTarihi.plusWeeks(19)));
				break;
		}
		return musabakaList;
	}
	
	
}