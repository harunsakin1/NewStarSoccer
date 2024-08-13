package SoccerApp;

import SoccerApp.databases.KulupDB;
import SoccerApp.entities.*;
import SoccerApp.utility.DatabaseManager;
import SoccerApp.utility.GeneratorRex;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;

public class Main {
	static DatabaseManager<Futbolcu> futbolcular  = new DatabaseManager<>();
	static KulupDB kulupler = new KulupDB();
	static DatabaseManager<Stadyum> stadlar = new DatabaseManager<>();
	static DatabaseManager<Musabaka> fikstur = new DatabaseManager<>();
	static DatabaseManager<Hakem> hakemler = new DatabaseManager<>();
	
	public static void main(String[] args) throws InterruptedException{
		yaratHepsi();
		Hakem hakem = new Hakem();
		hakemler.save(hakem);
		System.out.println(futbolcular.findAll());
		System.out.println(kulupler.findAll());
		Scanner scanner = new Scanner(System.in);
		
		//System.out.println(kulupler.ekleFutbolcu(scanner.nextLine(), scanner.nextLine(), futbolcular));
		//System.out.println(futbolcular.findAll());
		
		System.out.println(macPlanla("103", "101", "107", hakem.getId()));
		System.out.println(fikstur.findAll());
		macOyna(fikstur.findAll().get(0));
		
		
		
	}
	
	private static void playWavFile(String filePath) {
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
			
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private static void macOyna(Musabaka musabaka) throws InterruptedException{
		String kulupAd1 = kulupler.findByID(musabaka.getKulup1Id()).get().getAd();
		String kulupAd2 = kulupler.findByID(musabaka.getKulup2Id()).get().getAd();
		playWavFile("src/SoccerApp/sounds/MacOnu.wav");
		Thread.sleep(3000);
		System.out.println("Evet sevgili izleyenler, cekismeli bir mac ile karsinizdayiz!");
		Thread.sleep(2000);
		System.out.println( kulupAd1+ " den muthis bir hucum");
		Thread.sleep(2000);
		System.out.println("Eee, ııı, sey, yani, yüzde 1500");
		Thread.sleep(2000);
		System.out.println(kulupAd2 + " mort oldu");
		Thread.sleep(4000);
		playWavFile("src/SoccerApp/sounds/GoalEffect.wav");
		System.out.println("TOP AGLARDA!!! " + kulupAd1 + " muthis bir gol atıyor");
		Thread.sleep(2000);
		playWavFile("src/SoccerApp/sounds/GoalEffect.wav");
		System.out.println("VE GOOOOL!!! " + kulupAd1 + " efsane bir gol atıyor");
		Thread.sleep(2000);
		playWavFile("src/SoccerApp/sounds/GoalEffect.wav");
		playWavFile("src/SoccerApp/sounds/InanilmazBirMac.wav");
		System.out.println("SIMSEK GIBI!!! " + kulupAd1 + " enfes bir gol atıyor");
		playWavFile("src/SoccerApp/sounds/MacBitisDudugu.wav");
		musabaka.setSkorTablosu(new Integer[]{3, 0});
		System.out.println("macin sonucu: " + kulupAd1 + Arrays.toString(musabaka.getSkorTablosu()) + kulupAd2 +
				                   "\nElinize " +
				                   "saglik " +
				                   "arkadaslar");
	}
	
	private static boolean macPlanla(String kulup1Id, String kulup2Id, String stadyumId, String hakemId) {
		Musabaka musabaka = new Musabaka();
		Optional<Hakem> hakem = hakemler.findByID(hakemId);
		if (hakem.isEmpty()) return false;
		
		Optional<Kulup> kulup1 = kulupler.findByID(kulup1Id);
		if (kulup1.isEmpty()) return false;
		
		Optional<Kulup> kulup2 = kulupler.findByID(kulup2Id);
		if (kulup2.isEmpty()) return false;
		
		Optional<Stadyum> stadyum = stadlar.findByID(stadyumId);
		if (stadyum.isEmpty()) return false;
		
		musabaka.setHakemIds(Set.of(hakemId));
		musabaka.setKulup1Id(kulup1Id);
		musabaka.setKulup2Id(kulup2Id);
		musabaka.setStadyumId(stadyumId);
		fikstur.save(musabaka);
		return true;
	}
	
	public static void yaratHepsi(){
		GeneratorRex.yaratVeKaydetFutbolcular(futbolcular);
		GeneratorRex.kulupleriOlusturVeKaydet(kulupler);
		GeneratorRex.stadyumlariOlusturVeKaydet(stadlar);
	}
}