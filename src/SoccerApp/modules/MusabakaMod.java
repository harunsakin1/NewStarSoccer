package SoccerApp.modules;

import SoccerApp.NewStarSoccerApp;
import SoccerApp.entities.*;
import SoccerApp.models.DatabaseModel;
import SoccerApp.models.LigModel;
import SoccerApp.utility.enums.*;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class MusabakaMod {
	private DatabaseModel databaseModel = DatabaseModel.getInstance();
	private LigModel ligModel = new LigModel();
	private static MusabakaMod musabakaMod = new MusabakaMod();
	private Scanner scanner = new Scanner(System.in);
	private Random random = new Random();
	
	public static MusabakaMod getInstance(){
		return musabakaMod;
	}
	
	private MusabakaMod(){
	
	}
	
	public int menu(){
		int secim = -1;
		do {
			System.out.println("""
				                  ### Musabaka Mod ###
				                  1. Mac Oynat
				                  0. Geri
				                  -1. Cikis
				                  """);
			secim = NewStarSoccerApp.yapSecim("Seciminiz: ");
			if (secim == 0) break;
			
			secim = menuSecenekleri(secim);
		} while(secim != -1);
		
		return secim;
	}
	
	private int menuSecenekleri(int secim) {
		switch (secim){
			case 1:
				macOynat();
		}
		return secim;
	}
	
	private void macOynat() {
		// TODO ligID almak yerine database'den arat.
		System.out.print("Lig id gir : ");
		String ligId = scanner.nextLine();
		Optional<Lig> optLig = databaseModel.ligDataBase.findByID(ligId);
		if (optLig.isEmpty()) return;
		System.out.print("Musabaka id gir: ");
		var lig=optLig.get();
		String musabakaId = scanner.nextLine();
		Optional<Musabaka> optMus = databaseModel.musabakaDataBase.findByID(musabakaId);
		if (optMus.isEmpty()) return;
		var musabaka = optMus.get();
		
		try {
			macOyna(musabaka, lig);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void playWavFile(String filePath) {
		try {
			File soundFile = new File(filePath);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
			AudioFormat format = audioStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip audioClip = (Clip) AudioSystem.getLine(info);
			audioClip.open(audioStream);
			audioClip.start();
			
			audioClip.addLineListener(event -> {
				if (event.getType() == LineEvent.Type.STOP) {
					audioClip.close();
				}
			});
			
			// Klip çalınırken programın kapanmaması için bekleme ekledim
			while (!audioClip.isRunning()) {
				Thread.sleep(10);
			}
			while (audioClip.isRunning()) {
				Thread.sleep(10);
			}
			
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private void gol(String message, EGolAtan golAtan, Musabaka musabaka, Lig lig) {
		System.out.println(message);
		playWavFile("src/SoccerApp/sounds/GoalEffect.wav");
		Takim golAtanTakim = null;
		Takim golYiyenTakim = null;
		switch (golAtan){
			case EV_SAHIBI -> {
				golAtanTakim = musabaka.getEvSahibi();
				golYiyenTakim = musabaka.getDeplasman();
			}
			case DEPLASMAN -> {
				golAtanTakim = musabaka.getDeplasman();
				golYiyenTakim = musabaka.getEvSahibi();
			}
		}
		var atanIst = databaseModel.istatistikDataBase.alKulupAlLigGetirIstatistik(golAtanTakim.getKulupId(),
		                                                                           lig.getId());
		var yiyenIst = databaseModel.istatistikDataBase.alKulupAlLigGetirIstatistik(golYiyenTakim.getKulupId(),
		                                                                            lig.getId());
		switch (golYiyenTakim.getSkor() - golAtanTakim.getSkor()){
			case 1:
				atanIst.artirBeraberlik();
				atanIst.azaltMaglubiyet();
				
				yiyenIst.artirBeraberlik();
				yiyenIst.azaltGalibiyet();
				break;
			case 0:
				atanIst.artirGalibiyet();
				atanIst.azaltBeraberlik();
				
				yiyenIst.artirMaglubiyet();
				yiyenIst.azaltBeraberlik();
				break;
		}
		atanIst.artirAtilanGol();
		yiyenIst.artirYenilenGol();
		golAtanTakim.arttirSkor();
		guncellePuanTablosu(lig, atanIst, ESkorTablosuDegisimYonu.YUKARI);
		guncellePuanTablosu(lig, yiyenIst, ESkorTablosuDegisimYonu.ASAGI);
	}
	
	public void kadroBelirle(Musabaka musabaka,Lig lig){
		String evSahibiKulupId = musabaka.getEvSahibi().getKulupId();
		String deplasmanKulupId = musabaka.getDeplasman().getKulupId();
		
		List<Futbolcu> futbolcularEvSahibiKulup = databaseModel.futbolcuDataBase.bulFutbolcularKulupId(evSahibiKulupId);
		List<Futbolcu> futbolcularDeplasmanKulup = databaseModel.futbolcuDataBase.bulFutbolcularKulupId(deplasmanKulupId);
		Map<EMevki,List<Futbolcu>> evSahibiKadroIlkOnBir = new HashMap<>();
		Map<EMevki,List<Futbolcu>> deplasmanIlkOnBir = new HashMap<>();
		
		List<Futbolcu> kalecilerList =
				futbolcularEvSahibiKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.KALECI).toList();
		evSahibiKadroIlkOnBir.put(EMevki.KALECI,new ArrayList<>(List.of(kalecilerList.get(random.nextInt(0, kalecilerList.size())))));
		List<Futbolcu> deplasmanKalecilerlist =
				futbolcularDeplasmanKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.KALECI).toList();
		deplasmanIlkOnBir.put(EMevki.KALECI,new ArrayList<>(List.of(deplasmanKalecilerlist.get(random.nextInt(0, kalecilerList.size())))));
		
		List<Futbolcu> evSahibiDefanslistesi =
				new ArrayList<>(futbolcularEvSahibiKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.DEFANS).toList());
		Collections.shuffle(evSahibiDefanslistesi);
		evSahibiKadroIlkOnBir.put(EMevki.DEFANS,evSahibiDefanslistesi.subList(0,3));
		List<Futbolcu> deplasmanDefansListesi =
				new ArrayList<>(futbolcularDeplasmanKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.DEFANS).toList());
		Collections.shuffle(deplasmanDefansListesi);
		deplasmanIlkOnBir.put(EMevki.DEFANS,deplasmanDefansListesi.subList(0,3));
		
		List<Futbolcu> evSahibiOrtaSahaListesi =
				new ArrayList<>(futbolcularEvSahibiKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.ORTASAHA).toList());
		Collections.shuffle(evSahibiOrtaSahaListesi);
		evSahibiKadroIlkOnBir.put(EMevki.ORTASAHA, evSahibiOrtaSahaListesi.subList(0,4));
		
		List<Futbolcu> deplasmanOrtaSahaListesi =
				new ArrayList<>(futbolcularDeplasmanKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.ORTASAHA).toList());
		Collections.shuffle(deplasmanOrtaSahaListesi);
		deplasmanIlkOnBir.put(EMevki.ORTASAHA, deplasmanOrtaSahaListesi.subList(0,4));
		
		List<Futbolcu> evSahibiForvetListesi =
				new ArrayList<>(futbolcularEvSahibiKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.FORVET).toList());
		Collections.shuffle(evSahibiForvetListesi);
		evSahibiKadroIlkOnBir.put(EMevki.FORVET, evSahibiForvetListesi.subList(0,3));
		
		List<Futbolcu> deplasmanForvetListesi =
				new ArrayList<>(futbolcularDeplasmanKulup.stream().filter(futbolcu -> futbolcu.getMevki() == EMevki.FORVET).toList());
		Collections.shuffle(deplasmanForvetListesi);
		deplasmanIlkOnBir.put(EMevki.FORVET, deplasmanForvetListesi.subList(0,3));
	
		evSahibiKadroIlkOnBir.forEach((k, v) -> v.forEach(futbolcu -> musabaka.getEvSahibi().getKadro().add(futbolcu.getId())));
		deplasmanIlkOnBir.forEach((k, v) -> v.forEach(futbolcu -> musabaka.getDeplasman().getKadro().add(futbolcu.getId())));
		
		macaBasla(evSahibiKadroIlkOnBir, deplasmanIlkOnBir, musabaka, lig);
	}
	
	private void macaBasla(Map<EMevki, List<Futbolcu>> evSahibiKadroIlkOnBir, Map<EMevki, List<Futbolcu>> deplasmanIlkOnBir, Musabaka musabaka, Lig lig) {
		var evSahibiMevkiPuanlari = new HashMap<EMevki, Integer>();
		var deplasmanMevkiPuanlari = new HashMap<EMevki, Integer>();
		playWavFile("src/SoccerApp/sounds/MacOnu.wav");
		
		String evSahibiID = musabaka.getEvSahibi().getKulupId();
		Optional<Kulup> optEvSahibiKulup = databaseModel.kulupDataBase.findByID(evSahibiID);
		String evSahibiKulupAdi = optEvSahibiKulup.get().getAd();
		
		String deplasmanID = musabaka.getDeplasman().getKulupId();
		Optional<Kulup> optDeplasmanKulup = databaseModel.kulupDataBase.findByID(deplasmanID);
		String deplasmanKulupAdi = optDeplasmanKulup.get().getAd();
		Istatistik evSahibiIst = databaseModel.istatistikDataBase.alKulupAlLigGetirIstatistik(evSahibiID, lig.getId());
		Istatistik deplasmanIst = databaseModel.istatistikDataBase.alKulupAlLigGetirIstatistik(deplasmanID, lig.getId());
		
		deplasmanIst.artirBeraberlik();
		evSahibiIst.artirBeraberlik();
		
		evSahibiKadroIlkOnBir.forEach((k, v) -> evSahibiMevkiPuanlari.put(k, v.stream().map(fut -> fut.getYetenekPuani()).reduce(0, (p1, p2) -> p1 + p2)));
		deplasmanIlkOnBir.forEach((k, v) -> deplasmanMevkiPuanlari.put(k, v.stream().map(fut -> fut.getYetenekPuani()).reduce(0, (p1, p2) -> p1 + p2)));
		
		kadroYazdir(evSahibiKulupAdi, deplasmanKulupAdi, evSahibiKadroIlkOnBir, deplasmanIlkOnBir);
		
		int maxSure = musabaka.getSure();
		int sure;
		EMacStatu status; // ilkStatus
		for (int i = 0; i < 2; i++) {
			sure = 0;
			status = EMacStatu.values()[((random.nextInt(2) + i) % 2) + 4];
			while (sure < maxSure/2){
				try {
					Thread.sleep(100);
					sure += random.nextInt(3, 6);
					status = nextStatus(status, evSahibiMevkiPuanlari, deplasmanMevkiPuanlari, evSahibiKadroIlkOnBir, deplasmanIlkOnBir, musabaka, lig);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	private void kadroYazdir(String evSahibiKulupAdi, String deplasmanKulupAdi, Map<EMevki, List<Futbolcu>> evSahibiKadroIlkOnBir, Map<EMevki, List<Futbolcu>> deplasmanIlkOnBir) {
		System.out.println(evSahibiKulupAdi);
		for (Map.Entry<EMevki, List<Futbolcu>> futList: evSahibiKadroIlkOnBir.entrySet()){
			System.out.println(futList.getKey());
			for (Futbolcu fut: futList.getValue()){
				System.out.println(fut.getAd() + " " + fut.getSoyad() + " " + fut.getFormaNumarasi());
			}
		}
		
		System.out.println(deplasmanKulupAdi);
		for (Map.Entry<EMevki, List<Futbolcu>> futList: deplasmanIlkOnBir.entrySet()){
			System.out.println(futList.getKey());
			for (Futbolcu fut: futList.getValue()){
				System.out.println(fut.getAd() + " " + fut.getSoyad() + " " + fut.getFormaNumarasi());
			}
		}
	}
	
	private EMacStatu nextStatus(EMacStatu status, HashMap<EMevki, Integer> evSahibiMevkiPuanlari, HashMap<EMevki, Integer> deplasmanMevkiPuanlari, Map<EMevki, List<Futbolcu>> evSahibiKadroIlkOnBir, Map<EMevki, List<Futbolcu>> deplasmanIlkOnBir, Musabaka musabaka, Lig lig) {
		Integer ATILAN_GOL_GUC_ORANI = 250;
		Double EV_SAHIPLIGI_GUC_ORANI = 1.5;
		Double TOP_SAHIPLIGI_GUC_ORANI = 1.5;
		Double KALECI_ISABET_KATSAYISI = 3.;
		Double RAKIP_FORVET_GUC_KATSAYISI = 0.1;
		Integer toplamGolGuc = musabaka.getEvSahibi().getSkor() + musabaka.getDeplasman().getSkor()*ATILAN_GOL_GUC_ORANI;
		
		return switch (status){
			case KALE_DEPLASMAN -> {
				Integer evSahibiGuc = (int)((evSahibiMevkiPuanlari.get(EMevki.KALECI)*EV_SAHIPLIGI_GUC_ORANI+toplamGolGuc)*3);
				Integer deplasmanGuc = (int)(deplasmanMevkiPuanlari.get(EMevki.FORVET)*TOP_SAHIPLIGI_GUC_ORANI);
				int nextStatus = random.nextInt(evSahibiGuc + deplasmanGuc + toplamGolGuc + 50);
				
				System.out.println(deplasmanIlkOnBir.get(EMevki.FORVET).get(random.nextInt(deplasmanIlkOnBir.get(EMevki.FORVET).size())).getSoyad() + " 'den şut");
				
				
				if (nextStatus < 50) yield EMacStatu.DEFANS_DEPLASMAN;
				else if (nextStatus < 50 + toplamGolGuc) yield status;
				else if (nextStatus < 50 + toplamGolGuc + evSahibiGuc) {
					gol("nefis bir gol!", EGolAtan.DEPLASMAN, musabaka, lig);
					yield EMacStatu.ORTASAHA_EVSAHIBI;
				}
				else {
					System.out.println(deplasmanIlkOnBir.get(EMevki.KALECI).get(0).getSoyad()  + " kurtardı.");
					yield EMacStatu.KALE_EVSAHIBI;
				}
			}
			
			case KALE_EVSAHIBI -> {
				Integer evSahibiDefGuc =  (int)(evSahibiMevkiPuanlari.get(EMevki.DEFANS)*EV_SAHIPLIGI_GUC_ORANI*TOP_SAHIPLIGI_GUC_ORANI*KALECI_ISABET_KATSAYISI);
				Integer evSahibiOrtGuc =  (int)(evSahibiMevkiPuanlari.get(EMevki.ORTASAHA)*EV_SAHIPLIGI_GUC_ORANI*TOP_SAHIPLIGI_GUC_ORANI*KALECI_ISABET_KATSAYISI);
				Integer deplasmanForvetGuc = (int)(deplasmanMevkiPuanlari.get(EMevki.FORVET)*RAKIP_FORVET_GUC_KATSAYISI);
				Integer deplasmanOrtGuc = (int)(deplasmanMevkiPuanlari.get(EMevki.ORTASAHA));
				int nextStatus = random.nextInt(evSahibiDefGuc + evSahibiOrtGuc + deplasmanOrtGuc + deplasmanForvetGuc);
				if (nextStatus < evSahibiDefGuc) yield EMacStatu.DEFANS_EVSAHIBI;
				else if (nextStatus < evSahibiDefGuc + evSahibiOrtGuc) yield EMacStatu.ORTASAHA_EVSAHIBI;
				else if (nextStatus < evSahibiDefGuc + evSahibiOrtGuc + deplasmanForvetGuc) yield EMacStatu.DEFANS_DEPLASMAN;
				else yield EMacStatu.ORTASAHA_DEPLASMAN;
			}
			
			case DEFANS_DEPLASMAN -> {Integer evSahibiGuc = (int)(evSahibiMevkiPuanlari.get(EMevki.DEFANS)*EV_SAHIPLIGI_GUC_ORANI);
				Integer deplasmanGuc = (int)(deplasmanMevkiPuanlari.get(EMevki.FORVET)*TOP_SAHIPLIGI_GUC_ORANI);
				int nextStatus = random.nextInt(evSahibiGuc + deplasmanGuc + toplamGolGuc + 50);
				
				if (nextStatus < 50) yield EMacStatu.ORTASAHA_DEPLASMAN;
				else if (nextStatus < 50 + toplamGolGuc) yield status;
				else if (nextStatus < 50 + toplamGolGuc + evSahibiGuc) yield EMacStatu.KALE_DEPLASMAN;
				else yield EMacStatu.DEFANS_EVSAHIBI;}
			
			case DEFANS_EVSAHIBI -> {Integer evSahibiGuc = (int)(evSahibiMevkiPuanlari.get(EMevki.DEFANS)*EV_SAHIPLIGI_GUC_ORANI*TOP_SAHIPLIGI_GUC_ORANI);
				Integer deplasmanGuc = (int)(deplasmanMevkiPuanlari.get(EMevki.FORVET));
				int nextStatus = random.nextInt(evSahibiGuc + deplasmanGuc + toplamGolGuc + 50);
				
				if (nextStatus < 50) yield EMacStatu.KALE_EVSAHIBI;
				else if (nextStatus < 50 + toplamGolGuc) yield status;
				else if (nextStatus < 50 + toplamGolGuc + evSahibiGuc) yield EMacStatu.ORTASAHA_EVSAHIBI;
				else yield EMacStatu.DEFANS_DEPLASMAN;}
			
			case ORTASAHA_DEPLASMAN -> {Integer evSahibiGuc = (int)(evSahibiMevkiPuanlari.get(EMevki.ORTASAHA)*EV_SAHIPLIGI_GUC_ORANI);
				Integer deplasmanGuc = (int)(deplasmanMevkiPuanlari.get(EMevki.ORTASAHA)*TOP_SAHIPLIGI_GUC_ORANI);
				int nextStatus = random.nextInt(evSahibiGuc + deplasmanGuc + toplamGolGuc + 50);
				
				if (nextStatus < 50) yield EMacStatu.FORVET_DEPLASMAN;
				else if (nextStatus < 50 + toplamGolGuc) yield status;
				else if (nextStatus < 50 + toplamGolGuc + evSahibiGuc) yield EMacStatu.DEFANS_DEPLASMAN;
				else yield EMacStatu.ORTASAHA_EVSAHIBI;}
			
			case ORTASAHA_EVSAHIBI -> {
				Integer evSahibiGuc = (int)(evSahibiMevkiPuanlari.get(EMevki.ORTASAHA)*EV_SAHIPLIGI_GUC_ORANI*TOP_SAHIPLIGI_GUC_ORANI);
				Integer deplasmanGuc = deplasmanMevkiPuanlari.get(EMevki.ORTASAHA);
				int nextStatus = random.nextInt(evSahibiGuc + deplasmanGuc + toplamGolGuc + 50);
				
				if (nextStatus < 50) yield EMacStatu.DEFANS_EVSAHIBI;
				else if (nextStatus < 50 + toplamGolGuc) yield status;
				else if (nextStatus < 50 + toplamGolGuc + evSahibiGuc) yield EMacStatu.FORVET_EVSAHIBI;
				else yield EMacStatu.ORTASAHA_DEPLASMAN;
			}
			case FORVET_DEPLASMAN -> {Integer evSahibiGuc = (int)(evSahibiMevkiPuanlari.get(EMevki.FORVET)*EV_SAHIPLIGI_GUC_ORANI);
				Integer deplasmanGuc = (int)(deplasmanMevkiPuanlari.get(EMevki.DEFANS)*TOP_SAHIPLIGI_GUC_ORANI);
				int nextStatus = random.nextInt(evSahibiGuc + deplasmanGuc + toplamGolGuc + 50);
				
				if (nextStatus < 50) yield EMacStatu.DEPKALE_DEPLASMAN;
				else if (nextStatus < 50 + toplamGolGuc) yield status;
				else if (nextStatus < 50 + toplamGolGuc + evSahibiGuc) yield EMacStatu.ORTASAHA_DEPLASMAN;
				else yield EMacStatu.FORVET_EVSAHIBI;}
			
			case FORVET_EVSAHIBI -> {Integer evSahibiGuc = (int)(evSahibiMevkiPuanlari.get(EMevki.FORVET)*EV_SAHIPLIGI_GUC_ORANI*TOP_SAHIPLIGI_GUC_ORANI);
				Integer deplasmanGuc = (int)(deplasmanMevkiPuanlari.get(EMevki.DEFANS));
				int nextStatus = random.nextInt(evSahibiGuc + deplasmanGuc + toplamGolGuc + 50);
				
				if (nextStatus < 50) yield EMacStatu.ORTASAHA_EVSAHIBI;
				else if (nextStatus < 50 + toplamGolGuc) yield status;
				else if (nextStatus < 50 + toplamGolGuc + evSahibiGuc) yield EMacStatu.DEPKALE_EVSAHIBI;
				else yield EMacStatu.FORVET_DEPLASMAN;}
			
			case DEPKALE_DEPLASMAN -> {
				Integer deplasmanDefGuc =  (int)(deplasmanMevkiPuanlari.get(EMevki.DEFANS)*TOP_SAHIPLIGI_GUC_ORANI*KALECI_ISABET_KATSAYISI);
				Integer deplasmanOrtGuc =  (int)(deplasmanMevkiPuanlari.get(EMevki.ORTASAHA)*TOP_SAHIPLIGI_GUC_ORANI*KALECI_ISABET_KATSAYISI);
				Integer evSahibiForvetGuc = (int)(evSahibiMevkiPuanlari.get(EMevki.FORVET)*RAKIP_FORVET_GUC_KATSAYISI*EV_SAHIPLIGI_GUC_ORANI);
				Integer evSahibiOrtGuc = (int)(evSahibiMevkiPuanlari.get(EMevki.ORTASAHA)*EV_SAHIPLIGI_GUC_ORANI);
				int nextStatus = random.nextInt(deplasmanDefGuc + evSahibiOrtGuc + deplasmanOrtGuc + evSahibiForvetGuc);
				if (nextStatus < deplasmanDefGuc) yield EMacStatu.DEFANS_EVSAHIBI;
				else if (nextStatus < deplasmanDefGuc + evSahibiOrtGuc) yield EMacStatu.ORTASAHA_EVSAHIBI;
				else if (nextStatus < deplasmanDefGuc + evSahibiOrtGuc + evSahibiForvetGuc) yield EMacStatu.DEFANS_DEPLASMAN;
				else yield EMacStatu.ORTASAHA_DEPLASMAN;
			}
			
			case DEPKALE_EVSAHIBI -> {
				Integer evSahibiGuc = (int)(evSahibiMevkiPuanlari.get(EMevki.FORVET)*EV_SAHIPLIGI_GUC_ORANI*TOP_SAHIPLIGI_GUC_ORANI);
				Integer deplasmanGuc = (deplasmanMevkiPuanlari.get(EMevki.KALECI)+toplamGolGuc)*3;
				int nextStatus = random.nextInt(evSahibiGuc + deplasmanGuc + toplamGolGuc + 50);
				
				System.out.println(evSahibiKadroIlkOnBir.get(EMevki.FORVET).get(random.nextInt(evSahibiKadroIlkOnBir.get(EMevki.FORVET).size())).getSoyad()  + " 'den şut");
				
				if (nextStatus < 50) yield EMacStatu.FORVET_EVSAHIBI;
				else if (nextStatus < 50 + toplamGolGuc) yield status;
				else if (nextStatus < 50 + toplamGolGuc + evSahibiGuc) {
					gol("nefis bir gol!", EGolAtan.EV_SAHIBI, musabaka, lig);
					yield EMacStatu.ORTASAHA_DEPLASMAN;
				}
				else{
					System.out.println(evSahibiKadroIlkOnBir.get(EMevki.KALECI).get(0).getSoyad()  + " kurtardı.");
					yield EMacStatu.DEPKALE_DEPLASMAN;
				}
			}
		};
	}
	
	
	@Deprecated
	public void macOyna(Musabaka musabaka, Lig lig) throws InterruptedException {
		Random random = new Random();
		
		
		playWavFile("src/SoccerApp/sounds/MacOnu.wav");
		
		String evSahibiID = musabaka.getEvSahibi().getKulupId();
		Optional<Kulup> optEvSahibiKulup = databaseModel.kulupDataBase.findByID(evSahibiID);
		String evSahibiKulupAdi = optEvSahibiKulup.get().getAd();
		
		String deplasmanID = musabaka.getDeplasman().getKulupId();
		Optional<Kulup> optDeplasmanKulup = databaseModel.kulupDataBase.findByID(deplasmanID);
		String deplasmanKulupAdi = optDeplasmanKulup.get().getAd();
		
		
		Istatistik evSahibiIst = databaseModel.istatistikDataBase.alKulupAlLigGetirIstatistik(evSahibiID, lig.getId());
		Istatistik deplasmanIst = databaseModel.istatistikDataBase.alKulupAlLigGetirIstatistik(deplasmanID, lig.getId());
		
		deplasmanIst.artirBeraberlik();
		evSahibiIst.artirBeraberlik();
		playWavFile("src/SoccerApp/sounds/MacBaslangicDudugu.wav");
		
		boolean oyunDevam = true;
		int dakika = 0;
		
		while (oyunDevam) {
			//Thread.sleep(1500);
			dakika += 12;
			System.out.println(dakika + ". dakika");
			
			int olay = random.nextInt(5); // 0 ile 4 arasında rastgele bir tam sayı döner
			switch (olay) {
				case 0:
					gol(evSahibiKulupAdi + " harika bir gol attı!", EGolAtan.EV_SAHIBI, musabaka, lig);
					break;
				case 1:
					gol(deplasmanKulupAdi + " müthiş bir gol attı!", EGolAtan.DEPLASMAN, musabaka, lig);
					break;
				case 2:
					System.out.println(evSahibiKulupAdi + " sarı kart gördü.");
					break;
				case 3:
					System.out.println(deplasmanKulupAdi + " sarı kart gördü.");
					break;
				case 4:
					System.out.println("Top orta sahada gidip geliyor.");
					break;
			}
			
			if (dakika >= 90) {
				playWavFile("src/SoccerApp/sounds/MacBitisDudugu.wav");
				oyunDevam = false;
			}
		}
		
	}
	private void guncellePuanTablosu(Lig lig, Istatistik istatistik, ESkorTablosuDegisimYonu degisim){
		String yukariId;
		String istId = istatistik.getId();
		int yukariSiralama = -1;
		switch(degisim){
			case YUKARI -> {
				yukariSiralama = ligModel.getirSiralamaVerIstatistikId(lig, istId) - 1;
			}
			case ASAGI -> {
				yukariSiralama = ligModel.getirSiralamaVerIstatistikId(lig, istId);
			}
		}
		if (yukariSiralama < 1) return;
		else if(yukariSiralama == lig.getMaksLigTakimSayisi()) return;
		
		
		
		yukariId = lig.getPuanTablosu().get(yukariSiralama);
		String asagiId = lig.getPuanTablosu().get(yukariSiralama + 1);
		
		Istatistik yukariIst = databaseModel.istatistikDataBase.findByID(yukariId).get();
		Istatistik asagiIst = databaseModel.istatistikDataBase.findByID(asagiId).get();
		
		if (yukariIst.getPuan() > asagiIst.getPuan()) return;
		else if (yukariIst.getPuan() == asagiIst.getPuan() && yukariIst.getAveraj() > asagiIst.getAveraj()) return;
		else {
			lig.getPuanTablosu().replace(yukariSiralama, asagiId);
			lig.getPuanTablosu().replace(yukariSiralama + 1, yukariId);
			guncellePuanTablosu(lig,istatistik,degisim);
		}
	}
}

/*
istId1, istId2
k1    ,   k2
p1 p2  -> p2 > p1
tempId = tempId1
replace(k1, istId2)
replace(k2, tempId)


 */