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
		Istatistik istatistik = null;
		for (Istatistik istatistik1 : veriListesi) {
			if (istatistik1.getKulupId().equals(kulupId) && istatistik1.getLigId().equals(ligId)){
				return istatistik;
			}
		}
		
		return istatistik;
	}
	
	
}