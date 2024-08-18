package SoccerApp.entities;

import SoccerApp.utility.enums.EKokart;
import SoccerApp.utility.enums.EUyruk;

import java.time.LocalDate;

public class Hakem extends Insan{
	private EKokart kokart;
	
	public Hakem() {
	}
	
	
	
	public Hakem(String ad, String soyad, LocalDate dogumTarihi, EUyruk uyruk, String maas, EKokart kokart,
	             String uuid) {
		super(ad, soyad, dogumTarihi, uyruk, maas);
		this.kokart = kokart;
		setId(uuid);
	}
	
	public EKokart getKokart() {
		return kokart;
	}
	
	public void setKokart(EKokart kokart) {
		this.kokart = kokart;
	}
	
}