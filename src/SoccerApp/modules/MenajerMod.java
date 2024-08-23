package SoccerApp.modules;

import SoccerApp.NewStarSoccerApp;
import SoccerApp.databases.*;
import SoccerApp.entities.Futbolcu;
import SoccerApp.entities.Kulup;
import SoccerApp.entities.Menajer;

import java.util.Optional;
import java.util.Scanner;

public class MenajerMod {
	private KulupDB kulupDatabase;
	private FutbolcuDB futbolcuDatabase;
	private StadyumDB stadyumDatabase;
	private MenajerDB menajerDatabase;
	private HakemDB hakemDatabase;
	private NewStarSoccerApp nssApp=NewStarSoccerApp.getInstance();
	private static MenajerMod menajerMod=new MenajerMod();
	
	public static MenajerMod getInstance(){
		return menajerMod;
	}
	
	private MenajerMod() {
	}
	
	
	public void setHakemDatabase(HakemDB hakemDB) {
		hakemDatabase = hakemDB;
	}
	
	private Scanner scanner = new Scanner(System.in);
	
	public void setMenajerDatabase(MenajerDB menajerDB) {
		menajerDatabase = menajerDB;
	}
	
	public void setKulupDatabase(KulupDB kulupDB) {
		kulupDatabase = kulupDB;
	}
	
	public void setFutbolcuDatabase(FutbolcuDB futbolcuDB) {
		futbolcuDatabase = futbolcuDB;
	}
	
	public void setStadyumDatabase(StadyumDB stadyumDB) {
		stadyumDatabase = stadyumDB;
	}
	
	private int yapSecim() {
		return nssApp.yapSecim();
		
	}
	
	public int girisYapMenajerMod() {
		int secim = 0;
		do { // TODO buradan main menu'ye donme islemi eklenebilir
			System.out.println("Menajer Modül'e giriş yapmak için menajer id'nizi ve şifrenizi girmeniz gerekmektedir" +
					                   ".");
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
		} while (secim != -1 && secim != 0);
		return secim;
	}
	
	
	public int menu(Menajer menajer) {
		int secim;
		do {
			System.out.println("""
					                   #### NewStarSoccer Menajer Arayüzüne Hoşgeldiniz ####
					                           1. Kendi kulübümü görüntüle
					                           2. Diğer kulüpleri görüntüle
					                           0. Hesaptan Çık
					                          -1. Çıkış
					                           """);
			
			secim = yapSecim();
			if (secim == 0) {
				System.out.println("Hesaptan çıkış yapılıyor");
				break;
			}
			secim = menuSecenekleri(secim, menajer);
		} while (secim != -1);
		return secim;
	}
	
	private int menuSecenekleri(int secim, Menajer menajer) {
		switch (secim) {
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
	
	private void futbolculariGoruntuleMenu() {
		System.out.println("Futbolcularini görüntülemek istediğiniz bir kulüp var mi?\n1. Evet\n2. Hayır");
		int secim = yapSecim();
		futbolculariGoruntuleMenuSecenekleri(secim);
	}
	
	private void futbolculariGoruntuleMenuSecenekleri(int secim) {
		switch (secim) {
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
	
	private void futbolcuGoruntuleTarafindanKulupId() {
		System.out.print("Futbolcularını görüntülemek istediğiniz kulübün id'sini giriniz: ");
		int secim = yapSecim();
		futbolcuDatabase.findAll().stream()
		                .filter(futbolcu -> futbolcu.getKulupId().get().equals(String.valueOf(secim)))
		                .forEach(System.out::println);
	}
	
	private void goruntuleDigerKulupler() {
		kulupDatabase.findAll().forEach(System.out::println);
	}
	
	private void goruntuleFutbolcularDetayli(String kulupId) {
		futbolcuDatabase.bulFutbolcularKulupId(kulupId)
		                .forEach(futbolcu -> System.out.println(futbolcu.goruntuleDetayli()));
	}
	
	private Kulup bulKulubumu(Menajer menajer) {
		return kulupDatabase.findByID(menajer.getKulupId()).get();
	}
	
	
}