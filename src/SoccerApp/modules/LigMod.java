package SoccerApp.modules;

import SoccerApp.NewStarSoccerApp;
import SoccerApp.databases.*;
import SoccerApp.entities.Lig;
import SoccerApp.models.DatabaseModel;
import SoccerApp.utility.enums.EBolge;
import SoccerApp.utility.enums.EKume;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LigMod {
	private static FutbolcuDB futbolcuDB= DatabaseModel.futbolcuDataBase;
	private static HakemDB hakemDB=DatabaseModel.hakemDataBase;
	private static KulupDB kulupDB=DatabaseModel.kulupDataBase;
	private static MenajerDB menajerDB=DatabaseModel.menajerDataBase;
	private static LigDB ligDB=DatabaseModel.ligDataBase;
	private static MusabakaDB musabakaDB=DatabaseModel.musabakaDataBase;
	private static StadyumDB stadyumDB=DatabaseModel.stadyumDataBase;
	private static Scanner scanner=new Scanner(System.in);
	
	public static int yapSecim(){
		return yapSecim("Secim yapiniz:" );
	}
	
	public static int yapSecim(String mesaj){
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
					                    0. Geri Don
					                   -1. Kapa programi
					                    """);
			secim = yapSecim();
			secim = menuSecenekleri(secim);
			
		} while(secim != 0);
		return secim;
	}
	
	public static int menuSecenekleri(int secim){
		switch (secim){
			case 1:
				yaratLig();
				break;
			case 2:
				if (!ekleLigeKulupMenu()){
					System.out.println("Ekleme islemi basarisizlikle sonuclandi :((");
				}
				break;
			case 3:
				goruntuleLig();
				break;
			case 4:
				olusturFikstur();
				break;
			default:
				System.out.println("Girdi gecersiz x_x");
		}
		
		return secim;
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
		else if ((lig = optLig.get()).getTakimlarIDList().size() != lig.MAKS_LIG_TAKIM_SAYISI){
			System.out.println("Henuz ligde yeterince katilimci kulup tanimlanmamistir");
			return;
		}
		
	}
	
	private static boolean ekleLigeKulupMenu(){
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
		do{
			System.out.print("Eklemek istediginiz kulubun id'sini giriniz\n(Cikis icin 0 veya negatif deger giriniz):" +
					                 " " +
					                 " ");
			kulupId = yapSecim();
			kulupIdler.add(String.valueOf(kulupId));
		}while (kulupId >= 1);
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
	
	public static void yaratLig(){
		Lig lig=new Lig(20);
		System.out.print("Lig adı giriniz : ");
		String ad= scanner.nextLine();
		System.out.println("Bolge giriniz : ");
		String bolge= scanner.nextLine();
		System.out.println("Küme giriniz : ");
		String kume= scanner.nextLine();
		System.out.println("Lig başlangıç tarihini giriniz : (yyyy-MM-dd) ");
		String baslangicTarihi= scanner.nextLine();
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
	
	public static void goruntuleLig(){
		System.out.print("Lig ID giriniz : ");
		String ligID=scanner.nextLine();
		ligDB.listeleLigdekiKulupleri(ligID).forEach(kulupId -> System.out.println(kulupDB.findByID(kulupId).get().getAd()));
	}
	
	
}