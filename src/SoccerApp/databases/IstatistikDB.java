package SoccerApp.databases;

import SoccerApp.entities.Istatistik;
import SoccerApp.entities.Kulup;
import SoccerApp.utility.DatabaseManager;

public class IstatistikDB extends DatabaseManager<Istatistik> {
	private static IstatistikDB istatistikDB = new IstatistikDB();
	
	public static IstatistikDB getInstance() {
		return istatistikDB;
	}
	
	private IstatistikDB() {
	}
	
	public Istatistik alKulupAlLigGetirIstatistik(String kulupId, String ligId) {
		
		for (Istatistik istatistik : veriListesi) {
			if (istatistik.getKulupId().equals(kulupId) && istatistik.getLigId().equals(ligId)){
				return istatistik;
			}
		}
		
		return null;
	}
	
	
}