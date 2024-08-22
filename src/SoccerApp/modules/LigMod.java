package SoccerApp.modules;

import SoccerApp.NewStarSoccerApp;
import SoccerApp.databases.*;
import SoccerApp.entities.Lig;
import SoccerApp.entities.Musabaka;
import SoccerApp.models.DatabaseModel;
import SoccerApp.utility.enums.EBolge;
import SoccerApp.utility.enums.EKume;

import java.time.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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
				if (!ekleLigeKulupler()) {
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
			System.out.println("Lig bulunamadı.");
			return;
		}
		
		Map<Integer,List<String>> fikstur = optionalLig.get().getFikstur();
		
		if (fikstur == null){
			System.out.println("Fiktür oluşturulmamış☺");
			return;
		}
		for (List<String> musabakaIdleri:fikstur.values()){
			for (String musabakaId:musabakaIdleri){
				Musabaka musabaka = musabakaDB.findByID(musabakaId).get();
				System.out.println(musabaka.getEvSahibiID()+" vs "+musabaka.getDeplasmanID()+" Zaman: "+musabaka.getMusabakaTarihi());
			}
		}
		//A takımı vs B takımı Zaman:LocalDateTime
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
		yaratFiktur(ligId);
	}
	@Deprecated
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
		boolean ekleKulup;
		do {
			System.out.print("Eklemek istediginiz kulubun id'sini giriniz\n(Cikis icin 0 veya negatif deger giriniz):" + " " + " ");
			kulupId = yapSecim();
			if (kulupId<1){
				break;
			}
			ekleKulup = ligDB.ekleKulup(ligId, String.valueOf(kulupId));
		} while (ekleKulup);
//		kulupIdler.removeLast();
		return ligDB.ekleKulupler(kulupIdler, ligId);
	}
	@Deprecated
	private static boolean ekleLigeKulup() {
		System.out.print("Kulup eklenecek ligin id'sini giriniz: ");
		String ligId = scanner.nextLine();
		System.out.print("Kulup id'sini giriniz: ");
		String kulupId = scanner.nextLine();
		return ligDB.ekleKulup(ligId, kulupId);
	}
	
	public static void yaratLig() {
		Lig lig = new Lig(20);
		AtomicInteger atomicInteger = new AtomicInteger(1);
		System.out.print("Lig adı giriniz : ");
		String ad = scanner.nextLine();
		Arrays.stream(EBolge.values()).forEach(b -> {
			System.out.println(atomicInteger.getAndIncrement()+"- "+b);
		});
		Integer bolge = yapSecim("Bölge giriniz");
		atomicInteger.set(1);
		Arrays.stream(EKume.values()).forEach(k -> {
			System.out.println(atomicInteger.getAndIncrement()+"- "+k);
		});
		Integer kume = yapSecim("Küme giriniz : ");
		System.out.println("Lig başlangıç tarihini giriniz : (yyyy-MM-dd) ");
		String baslangicTarihi = scanner.nextLine();
		System.out.println("Lig bitiş tarihini giriniz : (yyyy-MM-dd) ");
		String bitisTarihi = scanner.nextLine();
		lig.setAd(ad);
		lig.setBolge(EBolge.values()[bolge-1]);
		lig.setKume(EKume.values()[kume-1]);
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
	
	public static List<List<LocalDateTime>> fiksturVakitleri(LocalDate ilkMacGunu){
		List<List<LocalDateTime>> fiksturVakitleri = new ArrayList<>();
		for (int haftaNumarasi = 0; haftaNumarasi < 19; haftaNumarasi++) {
			List<LocalDateTime> haftaninVakitleri = haftaninVakitleri(ilkMacGunu, haftaNumarasi);
			fiksturVakitleri.add(haftaninVakitleri);
		}
		return fiksturVakitleri;
	}
	
	public static List<List<List<String>>> yaratEslesme(Lig lig){
		List<String> takimlarIDList = lig.getTakimlarIDList();
		List<String> takimlarIdListClone = new ArrayList<>(takimlarIDList);
		Collections.shuffle(takimlarIdListClone);
		List<List<List<String>>> eslesmeList = new ArrayList<>();
		for (int i = 0; i < takimlarIdListClone.size()-1; i++) {
			List<List<String>> haftaListe = haftaninEslesmesi(takimlarIdListClone, i, 0);
			eslesmeList.add(haftaListe);
		}
		return eslesmeList;
	}
	
	private static List<List<String>> haftaninEslesmesi(List<String> takimlarIdListClone, int haftaNumarasi,
	                                                    int sabitIndex) {
		List<List<String>> haftaninEslesmesi = new ArrayList<>();
		int listeUzunluk = takimlarIdListClone.size();
		for (int pointer = 0; pointer < takimlarIdListClone.size()/2; pointer++) {
			if (pointer==0){
				haftaninEslesmesi.add(List.of(takimlarIdListClone.get(0),
				                              takimlarIdListClone.get(listeUzunluk-haftaNumarasi-1)));
			}
			else if (pointer<=haftaNumarasi) {
				haftaninEslesmesi.add(List.of(takimlarIdListClone.get(listeUzunluk-1-haftaNumarasi+pointer),
				                              takimlarIdListClone.get(listeUzunluk-1-haftaNumarasi-pointer)));
			}
			else {
				haftaninEslesmesi.add(List.of(takimlarIdListClone.get(haftaNumarasi+pointer),
				                              takimlarIdListClone.get(listeUzunluk-1-haftaNumarasi-pointer)));
			}
		}
		return haftaninEslesmesi;
	}
	
	public static void yaratFiktur(String ligID){
		
		Optional<Lig> optionalLig = ligDB.findByID(ligID);
		
		if (optionalLig.isEmpty()) {
			return;
		}
		Lig lig = optionalLig.get();
		List<List<List<String>>> eslesmeler = yaratEslesme(lig);
		List<List<LocalDateTime>> fiskturler = fiksturVakitleri(bulIlkCuma(lig.getBaslangicTarihi()));
		Map<Integer,List<String>> fikstur = new TreeMap<>();
		for (int haftaNumarasi = 0; haftaNumarasi < lig.getMaksLigTakimSayisi()-1; haftaNumarasi++) {
			List<String>haftaFikturu = new ArrayList<>();
			List<String>haftaFikturuIkinciYari=new ArrayList<>();
			for (int eslesmeNo = 0; eslesmeNo < eslesmeler.get(haftaNumarasi).size(); eslesmeNo++) {
				List<String> musabakalar =
						yaratMusabaka(eslesmeler.get(haftaNumarasi).get(eslesmeNo).get(0), eslesmeler
								.get(haftaNumarasi)
								.get(eslesmeNo)
								.get(1), fiskturler
								.get(haftaNumarasi).get(eslesmeNo));
				haftaFikturu.add(musabakalar.get(0));
				haftaFikturuIkinciYari.add(musabakalar.get(1));
			}
			fikstur.put(haftaNumarasi+1,haftaFikturu);
			fikstur.put(haftaNumarasi+20,haftaFikturuIkinciYari);
		}
		lig.setFikstur(fikstur);
	}
	
	/*@Deprecated
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
		List<String> rakipKuluplerID = new ArrayList<>();
		for (int i = 0; i < kuluplerList.size() - 1; i++) {
			haftaNumaralari = new ArrayList<>(IntStream.iterate(0, sayi -> sayi + 1).limit(19).boxed().toList());
			haftaNumaralari.removeAll(bulHangiHaftaMaciVar(kuluplerList.get(i),musabakaIdlerList,lig));
			haftaNumaralari =
					new ArrayList<>(haftaNumaralari.stream().filter(n -> !macVakitleri.get(n).isEmpty()).toList());
			//rakipKuluplerID = kuluplerList.subList(i+1,kuluplerList.size());
			for (int j = i + 1; j < kuluplerList.size(); j++) {
				rakipKuluplerID.add(kuluplerList.get(j));
			}
			System.out.println("--------------rakipKuluplerID------------");
			System.out.println(rakipKuluplerID);
			System.out.println("--------------haftaNumaralari------------");
			System.out.println(haftaNumaralari);
			for (int j = i + 1; j < kuluplerList.size(); j++) {
				String rastgeleRakipID = rakipKuluplerID.get(rnd.nextInt(rakipKuluplerID.size()));
				rakipKuluplerID.remove(rastgeleRakipID);
				Integer rastgeleHafta = haftaNumaralari.get(rnd.nextInt(haftaNumaralari.size()));
				haftaNumaralari.remove(rastgeleHafta);
				LocalDateTime belirlenenVakit =
						macVakitleri.get(rastgeleHafta).get(rnd.nextInt(0,macVakitleri.get(rastgeleHafta).size()));
				macVakitleri.get(rastgeleHafta).remove(belirlenenVakit);
				musabakaIdlerList.addAll(yaratMusabaka(kuluplerList.get(i),rastgeleRakipID,belirlenenVakit));
				*/
	/*if (!haftaNumaralari.isEmpty()) {
					Integer rastgeleHafta = haftaNumaralari.get(rnd.nextInt(haftaNumaralari.size()));
					haftaNumaralari.remove(rastgeleHafta);
					
					if (!macVakitleri.get(rastgeleHafta).isEmpty()) {
						LocalDateTime belirlenenVakit = macVakitleri.get(rastgeleHafta).get(rnd.nextInt(macVakitleri.get(rastgeleHafta).size()));
						macVakitleri.get(rastgeleHafta).remove(belirlenenVakit);
						
						musabakaIdlerList.addAll(yaratMusabaka(kuluplerList.get(i), rastgeleRakipID, belirlenenVakit));
					} else {
						System.out.println("Hata: Seçilen hafta için uygun zaman dilimi yok.");
					}
				} else {
					System.out.println("Hata: Uygun hafta yok.");
				}*/
	/*
			}
			rakipKuluplerID.clear();
		}
		fikstur.setFikstur(musabakaIdlerList);
		lig.setFikstur(fikstur);
	}*/
	
	public static List<Integer> bulHangiHaftaMaciVar(String kulupID,List<String> musabakaIDlerList, Lig lig){
		/*List<Integer> macOlanHaftalarList=new ArrayList<>(musabakaIDlerList.stream()
		                                                                   .filter(musabaka -> musabakaDB.findByID(musabaka).get().getEvSahibiID().equals(kuluplerList.get(i))
				                                                                   || musabakaDB.findByID(musabaka).get().getDeplasmanID().equals(kuluplerList.get(i)))
		                                                                   .map(Integer::parseInt)
		                                                                   .toList());*/
		return musabakaIDlerList.stream().map(mId -> musabakaDB.findByID(mId).get())
		                        .filter(mus -> mus.getDeplasmanID().equals(kulupID) || mus.getEvSahibiID().equals(kulupID))
		                        .map(musabaka -> (int)(Duration.between(LocalDateTime.of(lig.getBaslangicTarihi(),
		                                                                                 LocalTime.of(18,15)),
		                                                               musabaka.getMusabakaTarihi()).toDays()/7)).toList();
		//return macOlanHaftalarList;
	}
	//TODO refactor islemi yap (sayfadaki butun kodlar)
	public static List<String> yaratMusabaka(String kulupId1, String kulupId2, LocalDateTime macTarihi) {
		List<String> musabakaList = new ArrayList<>();
				musabakaList.add(musabakaDB.yaratMusabaka(kulupId1, kulupId2, macTarihi));
				musabakaList.add(musabakaDB.yaratMusabaka(kulupId2, kulupId1, macTarihi.plusWeeks(19)));
				
		return musabakaList;
	}
	
	
}