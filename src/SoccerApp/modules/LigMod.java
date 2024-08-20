package SoccerApp.modules;

import SoccerApp.NewStarSoccerApp;
import SoccerApp.databases.*;
import SoccerApp.entities.Lig;
import SoccerApp.models.DatabaseModel;
import SoccerApp.utility.enums.EBolge;
import SoccerApp.utility.enums.EKume;

import java.time.LocalDate;
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
		return NewStarSoccerApp.yapSecim();
	}
	
	public static int menu() {
		int secim;
		do {
			System.out.println("""
					                    1. Olustur Lig
					                    2. Kulup Ekle Lige
					                    3. Goruntule Lig
					                    0. Geri Don
					                   -1. Kapa programi
					                    
					                    """);
			secim = yapSecim();
			secim = menuSecenekleri(secim);
			
		} while(secim != 0);
		return secim;
	}
	
	public static int menuSecenekleri(int secim){
		return -1; //TODO NAPTIN?
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
	}
	
	public static void goruntuleLig(){
		System.out.print("Lig ID giriniz : ");
		String ligID=scanner.nextLine();
		System.out.println(ligDB.listeleLigdekiKulupleri(ligID));
	}
	
	
}