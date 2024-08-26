package SoccerApp.modules;

import SoccerApp.NewStarSoccerApp;
import SoccerApp.entities.Istatistik;
import SoccerApp.entities.Kulup;
import SoccerApp.entities.Lig;
import SoccerApp.models.DatabaseModel;
import SoccerApp.models.LigModel;
import SoccerApp.utility.enums.EBolge;
import SoccerApp.utility.enums.EKume;
import SoccerApp.utility.enums.ESkorTablosuElemani;

import java.time.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LigMod {
	private DatabaseModel databaseModel=DatabaseModel.getInstance();
	private LigModel ligModel = new LigModel();
	private Scanner scanner = new Scanner(System.in);
	private Random rnd = new Random();
	private static LigMod ligMod=null;
	
	public static LigMod getInstance(){
		if (ligMod==null) {
			ligMod=new LigMod();
		}
		return ligMod;
	}
	public LigMod() {
	}
	
	public int yapSecim() {
		return NewStarSoccerApp.yapSecim("Secim yapiniz:");
	}
	
	public int yapSecim(String mesaj) {
		return NewStarSoccerApp.yapSecim(mesaj);
	}
	
	public int menu() {
		int secim;
		do {
			System.out.println("""
					                    1. Olustur Lig
					                    2. Kulup Ekle Lige
					                    3. Goruntule Ligdeki Katilimci Kulupler
					                    4. Fikstur Olustur
					                    5. Goruntule Fikstur
					                    6. Goruntule Kulup Fikstur
					                    7. Goruntule Puan Tablosu
					                    8. tempEkleKulup (1-20)
					                    0. Geri Don
					                   -1. Kapa programi
					                    """);
			secim = yapSecim();
			if (secim==0){
				break;
			}
			secim = menuSecenekleri(secim);
		} while (secim != -1);
		return secim;
	}
	
	public int menuSecenekleri(int secim) {
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
				yazdirKuluplerLigdeYerAlan();
				break;
			case 4:
				olusturFikstur();
				break;
			case 5:
				goruntuleFikstur();
				break;
			case 6:
				yazdirKulupFikstur();
				break;
			case 7:
				goruntuleLigPuanTablosu();
				break;
			case 8:
				tempEkleKulup();
				break;
			case -1:
				break;
			default:
				System.out.println("Girdi gecersiz x_x");
		}
		
		return secim;
	}
	
	private void goruntuleLigPuanTablosu() {
		String ligId = String.valueOf(yapSecim("Lig id'si giriniz: "));
		Optional<Lig> optLig = databaseModel.ligDataBase.findByID(ligId);
		optLig.ifPresentOrElse(lig -> ligModel.goruntulePuanTablosu(lig), () -> System.out.println("Bulamadik o_o"));
	}
	
	private void tempEkleKulup() {
		String ligId = String.valueOf(yapSecim("LIG ID>>>"));
		for (int kulupId = 1; kulupId <= 20; kulupId++) {
			databaseModel.ligDataBase.ekleKulup(ligId, String.valueOf(kulupId));
		}
	}
	
	private void yazdirKulupFikstur() {
		
		int secim = yapSecim("Fiksturunu goruntulemek istediginiz kulup ID'sini giriniz : ");
		Optional<Kulup> optKulup = databaseModel.kulupDataBase.findByID(String.valueOf(secim));
		if (optKulup.isEmpty()) {
			System.out.println("Lig bulunamadı.");
		}
		ligModel.yazdirKulupFikstur(optKulup.get());
	}
	
	private void yazdirKuluplerLigdeYerAlan() {
		String secim = String.valueOf(yapSecim("Katilimcilarini gormek istediginiz lig'in id'sini giriniz: "));
		Optional<Lig> optLig = databaseModel.ligDataBase.findByID(secim);
		optLig.ifPresentOrElse(lig -> ligModel.getirKulupleriLigdeYerAlan(lig).forEach(klp -> System.out.println(klp.getAd())),
		                       () -> System.out.println(
				"Girdiginiz lig id'de hata var x_x"));
		
	}
	
	private void goruntuleFikstur() {
		System.out.print("Lig ID giriniz : ");
		String ligID = scanner.nextLine();
		Optional<Lig> optionalLig = databaseModel.ligDataBase.findByID(ligID);
		if (optionalLig.isEmpty()) {
			System.out.println("Lig bulunamadı.");
			return;
		}
		
		Lig lig = optionalLig.get();
		if (lig.getFikstur().isEmpty()) {
			System.out.println("Fikstur henuz olusturulmamis.");
			return;
		}
		ligModel.yazdirFikstur(lig);
	}
	
	
	private void olusturFikstur() {
		System.out.print("Lig id giriniz: ");
		String ligId = scanner.nextLine();
		Optional<Lig> optLig = databaseModel
				.ligDataBase.findByID(ligId);
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
	
	private boolean ekleLigeKulupler() {
		int kulupId;
		
		System.out.print("Lig id giriniz:");
		String ligId = scanner.nextLine();
		boolean ekleKulup = false;
		do {
			
			System.out.print("Eklemek istediginiz kulubun id'sini giriniz\n(Cikis icin 0 veya negatif deger giriniz):" + " " + " ");
			kulupId = yapSecim();
			Optional<Kulup> optionalKulup = databaseModel.kulupDataBase.findByID(String.valueOf(kulupId));
			if (kulupId < 1) {
				break;
			}
			else if (optionalKulup.isEmpty()){
				return false;
			}
			ekleKulup = databaseModel.ligDataBase.ekleKulup(ligId, String.valueOf(kulupId));
		} while (ekleKulup);
//		kulupIdler.removeLast();
		return ekleKulup;
	}
	
	public void yaratLig() {
		Lig lig = new Lig(20);
		AtomicInteger atomicInteger = new AtomicInteger(1);
		System.out.print("Lig adı giriniz : ");
		String ad = scanner.nextLine();
		Arrays.stream(EBolge.values()).forEach(b -> {
			System.out.println(atomicInteger.getAndIncrement() + "- " + b);
		});
		Integer bolge = yapSecim("Bölge giriniz");
		atomicInteger.set(1);
		Arrays.stream(EKume.values()).forEach(k -> {
			System.out.println(atomicInteger.getAndIncrement() + "- " + k);
		});
		Integer kume = yapSecim("Küme giriniz : ");
		System.out.println("Lig başlangıç tarihini giriniz : (yyyy-MM-dd) ");
		String baslangicTarihi = scanner.nextLine();
		System.out.println("Lig bitiş tarihini giriniz : (yyyy-MM-dd) ");
		String bitisTarihi = scanner.nextLine();
		lig.setAd(ad);
		lig.setBolge(EBolge.values()[bolge - 1]);
		lig.setKume(EKume.values()[kume - 1]);
		lig.setBaslangicTarihi(LocalDate.parse(baslangicTarihi));
		lig.setBitisTarihi(LocalDate.parse(bitisTarihi));
		lig.setId(String.valueOf(databaseModel.ligDataBase.findAll().size() + 1));
		databaseModel.ligDataBase.save(lig);
		System.out.println("Lig basariyla eklendi!");
	}
	
	public void goruntuleLig() {
		System.out.print("Lig ID giriniz : ");
		String ligID = scanner.nextLine();
		databaseModel.ligDataBase.listeleLigdekiKulupleri(ligID)
		     .forEach(kulupId -> System.out.println(databaseModel.kulupDataBase.findByID(kulupId).get().getAd()));
	}
	
	public List<List<LocalDateTime>> belirleMacVakitleri(String ligID) {
		Optional<Lig> optionalLig = databaseModel.ligDataBase.findByID(ligID);
		if (optionalLig.isEmpty()) {
			return null;
		}
		Lig lig = optionalLig.get();
		int toplamHaftaSayisi = lig.getMaksLigTakimSayisi() - 1;
		LocalDate ilkMacGunu = bulIlkCuma(lig.getBaslangicTarihi());
		
		List<List<LocalDateTime>> macTarihleri = new ArrayList<>();
		for (int i = 0; i < toplamHaftaSayisi; i++) {
			macTarihleri.add(haftaninVakitleri(ilkMacGunu, i));
			
		}
		return macTarihleri;
	}
	
	public List<LocalDateTime> haftaninVakitleri(LocalDate ilkMacGunu, long hafta) {
		List<LocalDateTime> macTarihleri = new ArrayList<>();
		List<Integer> rastgeleDakikalar = List.of(15, 30, 45, 0);
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                            rastgeleDakikalar.get(rnd.nextInt(0, 4)))));
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                            rastgeleDakikalar.get(rnd.nextInt(0, 4)))));
		
		
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(1)
		                                            .plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                            rastgeleDakikalar.get(rnd.nextInt(0, 4)))));
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(1)
		                                            .plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                            rastgeleDakikalar.get(rnd.nextInt(0, 4)))));
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(1)
		                                            .plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                            rastgeleDakikalar.get(rnd.nextInt(0, 4)))));
		
		
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(2)
		                                            .plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                            rastgeleDakikalar.get(rnd.nextInt(0, 4)))));
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(2)
		                                            .plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                            rastgeleDakikalar.get(rnd.nextInt(0, 4)))));
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(2)
		                                            .plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                            rastgeleDakikalar.get(rnd.nextInt(0, 4)))));
		
		
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(3)
		                                            .plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                            rastgeleDakikalar.get(rnd.nextInt(0, 4)))));
		macTarihleri.add(LocalDateTime.of(ilkMacGunu.plusDays(3)
		                                            .plusWeeks(hafta), LocalTime.of(rnd.nextInt(18, 22),
		                                                                            rastgeleDakikalar.get(rnd.nextInt(0, 4)))));
		return macTarihleri;
	}
	
	public LocalDate bulIlkCuma(LocalDate ligBaslangicTarihi) {
		for (int i = 0; i < 7; i++) {
			LocalDate siradakiGun = ligBaslangicTarihi.plusDays(i);
			if (siradakiGun.getDayOfWeek() == DayOfWeek.FRIDAY) {
				return siradakiGun;
			}
		}
		return null;
	}
	
	public List<List<LocalDateTime>> fiksturVakitleri(LocalDate ilkMacGunu) {
		List<List<LocalDateTime>> fiksturVakitleri = new ArrayList<>();
		for (int haftaNumarasi = 0; haftaNumarasi < 19; haftaNumarasi++) {
			List<LocalDateTime> haftaninVakitleri = haftaninVakitleri(ilkMacGunu, haftaNumarasi);
			fiksturVakitleri.add(haftaninVakitleri);
		}
		return fiksturVakitleri;
	}
	
	public List<List<List<String>>> yaratEslesme(Lig lig) {
		List<String> takimlarIDList = lig.getTakimlarIDList();
		List<String> takimlarIdListClone = new ArrayList<>(takimlarIDList);
		Collections.shuffle(takimlarIdListClone);
		List<List<List<String>>> eslesmeList = new ArrayList<>();
		for (int i = 0; i < takimlarIdListClone.size() - 1; i++) {
			List<List<String>> haftaListe = haftaninEslesmesi(takimlarIdListClone, i, 0);
			eslesmeList.add(haftaListe);
		}
		return eslesmeList;
	}
	
	private List<List<String>> haftaninEslesmesi(List<String> takimlarIdListClone, int haftaNumarasi, int sabitIndex) {
		List<List<String>> haftaninEslesmesi = new ArrayList<>();
		int listeUzunluk = takimlarIdListClone.size();
		for (int pointer = 0; pointer < takimlarIdListClone.size() / 2; pointer++) {
			Integer problematicIdx = listeUzunluk - 1 - haftaNumarasi - pointer;
			problematicIdx = (problematicIdx >= 1) ? problematicIdx : problematicIdx + listeUzunluk - 1;
			if (pointer == 0) {
				haftaninEslesmesi.add(List.of(takimlarIdListClone.get(0),
				                              takimlarIdListClone.get(listeUzunluk - haftaNumarasi - 1)));
			}
			else if (pointer <= haftaNumarasi) {
				haftaninEslesmesi.add(List.of(takimlarIdListClone.get(listeUzunluk - 1 - haftaNumarasi + pointer),
				                              takimlarIdListClone.get(problematicIdx)));
			}
			else {
				haftaninEslesmesi.add(List.of(takimlarIdListClone.get(pointer - haftaNumarasi),
				                              takimlarIdListClone.get(problematicIdx)));
			}
		}
		return haftaninEslesmesi;
	}
	
	public void yaratFiktur(String ligID) {
		
		Optional<Lig> optionalLig = databaseModel.ligDataBase.findByID(ligID);
		
		if (optionalLig.isEmpty()) {
			return;
		}
		Lig lig = optionalLig.get();
		List<List<List<String>>> eslesmeler = yaratEslesme(lig);
		Collections.shuffle(eslesmeler);
		List<List<LocalDateTime>> fiskturler = fiksturVakitleri(bulIlkCuma(lig.getBaslangicTarihi()));
		Map<Integer, List<String>> fikstur = new TreeMap<>();
		for (int haftaNumarasi = 0; haftaNumarasi < lig.getMaksLigTakimSayisi() - 1; haftaNumarasi++) {
			List<String> haftaFikturu = new ArrayList<>();
			List<String> haftaFikturuIkinciYari = new ArrayList<>();
			for (int eslesmeNo = 0; eslesmeNo < eslesmeler.get(haftaNumarasi).size(); eslesmeNo++) {
				List<String> musabakalar =
						yaratMusabaka(eslesmeler.get(haftaNumarasi).get(eslesmeNo).get(0),
						              eslesmeler.get(haftaNumarasi).get(eslesmeNo).get(1),
						              fiskturler
								              .get(haftaNumarasi).get(eslesmeNo), haftaNumarasi);
				haftaFikturu.add(musabakalar.get(0));
				haftaFikturuIkinciYari.add(musabakalar.get(1));
			}
			fikstur.put(haftaNumarasi + 1, haftaFikturu);
			fikstur.put(haftaNumarasi + 20, haftaFikturuIkinciYari);
		}
		lig.setFikstur(fikstur);
		
		baslatPuanTablosu(lig);
	}
	
	private void baslatPuanTablosu(Lig lig) {
		List<Kulup> kulups = ligModel.getirKulupleriLigdeYerAlan(lig);
		for (int i = 1; i <= kulups.size(); i++) {
			Istatistik istatistik = new Istatistik();
			istatistik.setKulupId(kulups.get(i).getId());
			istatistik.setLigId(lig.getId());
			lig.getPuanTablosu().put(i, istatistik);
		}
	}
	
	
	//TODO refactor islemi yap (sayfadaki butun kodlar)
	public List<String> yaratMusabaka(String kulupId1, String kulupId2, LocalDateTime macTarihi, int hafta) {
		List<String> musabakaList = new ArrayList<>();
		switch (hafta % 2) {
			case 0:
				musabakaList.add(databaseModel.musabakaDataBase.yaratMusabaka(kulupId1, kulupId2, macTarihi));
				musabakaList.add(databaseModel.musabakaDataBase.yaratMusabaka(kulupId2, kulupId1, macTarihi.plusWeeks(19)));
				break;
			case 1:
				musabakaList.add(databaseModel.musabakaDataBase.yaratMusabaka(kulupId2, kulupId1, macTarihi));
				musabakaList.add(databaseModel.musabakaDataBase.yaratMusabaka(kulupId1, kulupId2, macTarihi.plusWeeks(19)));
				break;
		}
		
		
		return musabakaList;
	}
	
	
}