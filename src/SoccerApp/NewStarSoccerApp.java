package SoccerApp;

import SoccerApp.databases.*;
import SoccerApp.entities.Futbolcu;
import SoccerApp.modules.KulupMod;
import SoccerApp.modules.MenajerMod;
import SoccerApp.utility.GeneratorRex;
import SoccerApp.utility.enums.EKokart;
import SoccerApp.utility.enums.EMevki;
import SoccerApp.utility.enums.EUyruk;

import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;

public class NewStarSoccerApp {
	private static KulupDB kulupDB = new KulupDB();
	private static FutbolcuDB futbolcuDB = new FutbolcuDB();
	private static StadyumDB stadyumDB=new StadyumDB();
	private static MenajerDB menajerDB=new MenajerDB();
	private static HakemDB hakemDB=new HakemDB();
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		ataModlaraDatabaseleri();
		
		//yukleIO();
		
		System.out.println("Program başlatılıyor");
		getirBinarydenJavaya();
		
		//kulupDB.findAll().forEach(System.out::println);
		//stadyumDB.findAll().forEach(System.out::println);
		//futbolcuDB.findAll().forEach(System.out::println);
		//menajerDB.findAll().forEach(System.out::println);
		//nssMenu();
		//TODO yaratIO bazen serialID'den dolayı hata veriyor, bazen vermiyor, neden ?
		nssMenu();
	}
	
	private static void yukleIO() {
		GeneratorRex.yaratStadyumIO();
		GeneratorRex.yaratMenajerlerIO();
		GeneratorRex.yaratKulupIO();
		GeneratorRex.yaratHakemlerIO();
		GeneratorRex.yaratFutbolcularIO();
	}
	
	private static void getirBinarydenJavaya() {
		getirKulupler();
		getirFutbolcular();
		getirMenajerler();
		getirHakemler();
		getirStadyumlar();
	}
	
	private static void ataModlaraDatabaseleri() {
		ataKulupModaDatabaseleri();
		ataMenajerModaDatabaseleri();
	}
	
	private static void ataMenajerModaDatabaseleri() {
		MenajerMod.setKulupDatabase(kulupDB);
		MenajerMod.setFutbolcuDatabase(futbolcuDB);
		MenajerMod.setStadyumDatabase(stadyumDB);
		MenajerMod.setHakemDatabase(hakemDB);
		MenajerMod.setMenajerDatabase(menajerDB);
	}
	
	private static void ataKulupModaDatabaseleri() {
		KulupMod.setKulupDatabase(kulupDB);
		KulupMod.setFutbolcuDatabase(futbolcuDB);
		KulupMod.setStadyumDatabase(stadyumDB);
		KulupMod.setHakemDatabase(hakemDB);
		KulupMod.setMenajerDatabase(menajerDB);
	}
	
	private static void getirHakemler(){
		GeneratorRex.setHakemDB(hakemDB);
		GeneratorRex.getirHakemler();
	}
	private static void getirMenajerler(){
		GeneratorRex.setMenajerDB(menajerDB);
		GeneratorRex.getirMenajerler();
	}
	private static void getirKulupler(){
		GeneratorRex.setKulupDb(kulupDB);
		GeneratorRex.getirKulupler();
	}
	private static void getirStadyumlar(){
		GeneratorRex.setStadyumDB(stadyumDB);
		GeneratorRex.getirStadyumlar();
	}
	private static void getirFutbolcular(){
		GeneratorRex.setFutbolcuDB(futbolcuDB);
		GeneratorRex.getirFutbolcular();
	}
	
	private static int nssMenu() {
		int secim;
		do {
			System.out.println("""
					                   #### NewStarSoccer Uygulamasına Hoşgeldiniz ####
					                           1. Kulüp Modül
					                           2. Menajer Modül
					                           0. Geri Dön
					                          -1. Çıkış
					                           """);
			secim = yapSecim();
			if (secim == 0) {
				System.out.println("Geri dönülüyor");
				break;
			}
			secim = nssMenuSecenekleri(secim);
		} while (secim != -1);
		return secim;
		
		
	}
	
	private static int nssMenuSecenekleri(int secim) {
		switch (secim) {
			case 1:
				return KulupMod.menu();
			case 2:
				MenajerMod.girisYapMenajerMod();
				return secim;
			case -1:
				System.out.println("Uygulama sonlandırılıyor....");
				return secim;
		}
		return secim;
	}
	
	public static int yapSecim(String message){
		int secim;
		while (true) {
			System.out.print(message);
			try {
				secim = scanner.nextInt();
				break;
			}
			catch (Exception e) {
				System.out.println("Gecersiz girdi, lütfen integer giriniz");
			}
			finally {
				scanner.nextLine();
			}
		}
		return secim;
	}
	
	public static int yapSecim(){
		return yapSecim("Secim yapiniz: ");
	}
	
}