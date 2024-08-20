package SoccerApp.modules;

import SoccerApp.NewStarSoccerApp;
import SoccerApp.databases.*;
import SoccerApp.entities.Futbolcu;
import SoccerApp.entities.Kulup;
import SoccerApp.entities.Menajer;

import java.util.Optional;
import java.util.Scanner;

public class MenajerMod {
	private static KulupDB kulupDatabase;
	private static FutbolcuDB futbolcuDatabase;
	private static StadyumDB stadyumDatabase;
	private static MenajerDB menajerDatabase;
	private static HakemDB hakemDatabase;
	
	public static void setHakemDatabase(HakemDB hakemDB) {
		hakemDatabase = hakemDB;
	}
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void setMenajerDatabase(MenajerDB menajerDB) {
		menajerDatabase = menajerDB;
	}
	
	public static void setKulupDatabase(KulupDB kulupDB) {
		kulupDatabase = kulupDB;
	}
	
	public static void setFutbolcuDatabase(FutbolcuDB futbolcuDB) {
		futbolcuDatabase = futbolcuDB;
	}
	
	public static void setStadyumDatabase(StadyumDB stadyumDB) {
		stadyumDatabase = stadyumDB;
	}
	
	private static int yapSecim(){
		return NewStarSoccerApp.yapSecim();
		
	}
	
	public static void girisYapMenajerMod(){
		int secim = 0;
		do { // TODO buradan main menu'ye donme islemi eklenebilir
			System.out.println("Menajer Modül'e giriş yapmak için menajer id'nizi ve şifrenizi girmeniz gerekmektedir.");
			System.out.print("Menajer id: ");
			String tempId = scanner.nextLine();
			System.out.print("Şifre: ");
			String tempSifre = scanner.nextLine();
			Optional<Menajer> OptionalMenajer = menajerDatabase.findByID(tempId);
			Menajer menajer;
			if (OptionalMenajer.isEmpty()) {
				System.out.println("Gecersiz id");
				break;
			}
			else if ((menajer = OptionalMenajer.get()).getSifre().equals(tempSifre)) {
				System.out.println("Giriş başarılı.");
				secim = menu(menajer);
			}
			else {
				System.out.println("Şifre hatalı");
				break;
			}
		} while(secim != -1);
	}
	
	
	public static int menu(Menajer menajer){
		int secim;
		do{
			System.out.println("""
				           #### NewStarSoccer Menajer Arayüzüne Hoşgeldiniz ####
				                   1. Kendi kulübümü görüntüle
				                   2. Diğer kulüpleri görüntüle
				                   0. Hesaptan Çık
				                  -1. Çıkış
				                   """);
			
			secim = yapSecim();
			if (secim == 0){
				System.out.println("Hesaptan çıkış yapılıyor");
				break;
			}
			secim =menuSecenekleri(secim, menajer);
		} while (secim != -1);
		return secim;
	}
	
	private static int menuSecenekleri(int secim, Menajer menajer) {
		switch (secim){
			case 1:
				if (!menajer.getKulupId().equals("-1")) {
					Kulup kulup = bulKulubumu(menajer);
					System.out.println(kulup.goruntuleDetayKulup());
					goruntuleFutbolcularDetayli(kulup.getId());
				}
				else System.out.println("Kulubunuz bulunmamaktadır.");
				break;
			case 2:
				goruntuleDigerKulupler();
				futbolculariGoruntuleMenu();
				break;
		}
		
		return secim;
	}
	
	private static void futbolculariGoruntuleMenu() {
		System.out.println("Futbolcularini görüntülemek istediğiniz bir kulüp var mi?\n1. Evet\n2. Hayır");
		int secim = yapSecim();
		futbolculariGoruntuleMenuSecenekleri(secim);
	}
	
	private static void futbolculariGoruntuleMenuSecenekleri(int secim) {
		switch (secim){
			case 1:
				futbolcuGoruntuleTarafindanKulupId();
				break;
			case 2:
				System.out.println("Geri donuluyor...");
				break;
			default:
				System.out.println("Girdi tanımlanamadı. x_x");
			
		}
	}
	
	private static void futbolcuGoruntuleTarafindanKulupId() {
		System.out.print("Futbolcularını görüntülemek istediğiniz kulübün id'sini giriniz: ");
		int secim = yapSecim();
		futbolcuDatabase.findAll().stream().filter(futbolcu -> futbolcu.getKulupId().get().equals(String.valueOf(secim))).forEach(System.out::println);
	}
	
	private static void goruntuleDigerKulupler() {
		kulupDatabase.findAll().forEach(System.out::println);
	}
	
	private static void goruntuleFutbolcularDetayli(String kulupId) {
		futbolcuDatabase.bulFutbolcularKulupId(kulupId).forEach(futbolcu -> System.out.println(futbolcu.goruntuleDetayli()));
	}
	
	private static Kulup bulKulubumu(Menajer menajer) {
		return kulupDatabase.findByID(menajer.getKulupId()).get();
	}
	
	
}