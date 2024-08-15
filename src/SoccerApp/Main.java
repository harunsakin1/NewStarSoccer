package SoccerApp;

import SoccerApp.databases.KulupDB;
import SoccerApp.entities.*;
import SoccerApp.utility.DatabaseManager;
import SoccerApp.utility.GeneratorRex;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;

public class Main {
	static DatabaseManager<Futbolcu> futbolcular  = new DatabaseManager<>();
	static KulupDB kulupler = new KulupDB();
	static DatabaseManager<Stadyum> stadlar = new DatabaseManager<>();
	static DatabaseManager<Musabaka> fikstur = new DatabaseManager<>();
	static DatabaseManager<Hakem> hakemler = new DatabaseManager<>();
	static Random random = new Random();
	
	public static void main(String[] args) throws InterruptedException{
		
		
		/*yaratHepsi();
		Hakem hakem = new Hakem();
		hakemler.save(hakem);
		System.out.println(futbolcular.findAll());
		System.out.println(kulupler.findAll());
		Scanner scanner = new Scanner(System.in);
		
		//System.out.println(kulupler.ekleFutbolcu(scanner.nextLine(), scanner.nextLine(), futbolcular));
		//System.out.println(futbolcular.findAll());
		
		System.out.println(macPlanla("103", "101", "107", hakem.getId()));
		System.out.println(fikstur.findAll());
		macOyna(fikstur.findAll().get(0));*/
		
		
		
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
	/*private static void gol(String message) {
		System.out.println(message);
		playWavFile("src/SoccerApp/sounds/GoalEffect.wav");
	}*/
	
	/*private static void macOyna(Musabaka musabaka) throws InterruptedException{
		
		*//*playWavFile("src/SoccerApp/sounds/MacOnu.wav");
		
		String kulupAd1 = kulupler.findByID(musabaka.getKulup1Id()).get().getAd();
		String kulupAd2 = kulupler.findByID(musabaka.getKulup2Id()).get().getAd();
		
		playWavFile("src/SoccerApp/sounds/MacBaslangicDudugu.wav");
		
		boolean oyunDevam = true;
		int dakika = 0;
		
		while (oyunDevam) {
			Thread.sleep(1500);
			dakika += 12;
			System.out.println(dakika + ". dakika");
			
			int olay = random.nextInt(5); // 0 ile 4 arasında rastgele bir tam sayı döner
			switch (olay) {
				case 0:
					gol(kulupAd1 + " harika bir gol attı!");
					break;
				case 1:
					gol(kulupAd2 + " müthiş bir gol attı!");
					break;
				case 2:
					System.out.println(kulupAd1 + " sarı kart gördü.");
					break;
				case 3:
					System.out.println(kulupAd2 + " sarı kart gördü.");
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
		
		musabaka.setSkorTablosu(new Integer[]{random.nextInt(4), random.nextInt(4)});
		System.out.println("Maç sonucu: " + kulupAd1 + " " + musabaka.getSkorTablosu()[0] + " - " + musabaka.getSkorTablosu()[1] + " " + kulupAd2);*//*
		playWavFile("src/SoccerApp/sounds/MacOnu.wav");
		String kulupAd1 = kulupler.findByID(musabaka.getEvSahibiID()).get().getAd();
		String kulupAd2 = kulupler.findByID(musabaka.getDeplasmanID()).get().getAd();
		playWavFile("src/SoccerApp/sounds/MacBaslangicDudugu.wav");
		Thread.sleep(1000);
		System.out.println("Evet sevgili izleyenler, cekismeli bir mac ile karsinizdayiz!");
		Thread.sleep(2000);
		System.out.println( kulupAd1+ " den muthis bir hucum");
		Thread.sleep(2000);
		System.out.println("Eee, ııı, sey, yani, yüzde 1500");
		Thread.sleep(2000);
		System.out.println(kulupAd2 + " mort oldu");
		Thread.sleep(2000);
		System.out.println("TOP AGLARDA!!! " + kulupAd1 + " muthis bir gol atıyor");
		playWavFile("src/SoccerApp/sounds/GoalEffect.wav");
		Thread.sleep(2000);
		System.out.println("VE GOOOOL!!! " + kulupAd1 + " efsane bir gol atıyor");
		playWavFile("src/SoccerApp/sounds/GoalEffect.wav");
		Thread.sleep(2000);
		System.out.println("SIMSEK GIBI!!! " + kulupAd1 + " enfes bir gol atıyor");
		playWavFile("src/SoccerApp/sounds/GoalEffect.wav");
		playWavFile("src/SoccerApp/sounds/InanilmazBirMac.wav");
		playWavFile("src/SoccerApp/sounds/MacBitisDudugu.wav");
		System.out.println("macin sonucu: " + kulupAd1 + Arrays.toString(musabaka.getSkorTablosu()) + kulupAd2 +
				                   "\nElinize " +
				                   "saglik " +
				                   "arkadaslar");
	}*/
	
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
		musabaka.setEvSahibiID(kulup1Id);
		musabaka.setDeplasmanID(kulup2Id);
		musabaka.setStadyumId(stadyumId);
		fikstur.save(musabaka);
		return true;
	}
	
	
}