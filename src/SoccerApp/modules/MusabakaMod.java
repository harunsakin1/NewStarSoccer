package SoccerApp.modules;

import SoccerApp.databases.IstatistikDB;
import SoccerApp.entities.Istatistik;
import SoccerApp.entities.Kulup;
import SoccerApp.entities.Lig;
import SoccerApp.entities.Musabaka;
import SoccerApp.models.DatabaseModel;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.Random;

public class MusabakaMod {
	private DatabaseModel databaseModel = DatabaseModel.getInstance();
	
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
			
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	private static void gol(String message,String kulupId, Musabaka musabaka) {
		System.out.println(message);
		playWavFile("src/SoccerApp/sounds/GoalEffect.wav");
		if (kulupId.equals(musabaka.getEvSahibiID())) {
			musabaka.setEvSahibiSkor(musabaka.getEvSahibiSkor()+1);
		}
		else {
			musabaka.setDeplasmanSkor(musabaka.getDeplasmanSkor()+1);
		}
	}
	
	private void macOyna(Musabaka musabaka,Lig lig) throws InterruptedException {
		Random random = new Random();
		
		
		playWavFile("src/SoccerApp/sounds/MacOnu.wav");
		
		String evSahibiID = musabaka.getEvSahibiID();
		Optional<Kulup> optEvSahibiKulup = databaseModel.kulupDataBase.findByID(evSahibiID);
		String evSahibiKulupAdi = optEvSahibiKulup.get().getAd();
		
		String deplasmanID = musabaka.getDeplasmanID();
		Optional<Kulup> optDeplasmanKulup = databaseModel.kulupDataBase.findByID(deplasmanID);
		String deplasmanKulupAdi = optDeplasmanKulup.get().getAd();
		
		
		
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
					gol(evSahibiKulupAdi + " harika bir gol attı!",evSahibiID,musabaka);
					break;
				case 1:
					gol(deplasmanKulupAdi + " müthiş bir gol attı!",deplasmanID,musabaka);
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
		
		istatistikGuncelle(musabaka,lig);
		
	}
	private void istatistikGuncelle(Musabaka musabaka, Lig lig){
		Istatistik istatistikEvSahibi = databaseModel.istatistikDataBase
		musabaka.setDeplasmanSkor(musabaka.getDeplasmanSkor());
	}
}