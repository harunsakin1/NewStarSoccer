package SoccerApp.entities;

import SoccerApp.utility.enums.EUyruk;

import java.time.LocalDate;

public class Menajer extends Insan {
	private String kulupId;
	private LocalDate sozlesmeBitisTarihi;
	
	//TODO ÖNCEDEN ÇALIŞTIRDIĞI KULÜP, ALDIĞI KUPALAR VE MENAJER PUANI
	
	public Menajer(){
		kulupId="";
	}
	
	public Menajer(String kulupId, int yil){
		this.kulupId=kulupId;
		this.sozlesmeBitisTarihi=LocalDate.now().plusYears(yil);
	}
	
	public String getKulupId() {
		return kulupId;
	}
	
	public void setKulupId(String kulupId) {
		this.kulupId = kulupId;
	}
	
	public LocalDate getSozlesmeBitisTarihi() {
		return sozlesmeBitisTarihi;
	}
	
	public void setSozlesmeBitisTarihi(LocalDate sozlesmeBitisTarihi) {
		this.sozlesmeBitisTarihi = sozlesmeBitisTarihi;
	}
	
	@Override
	public String toString() {
		return "Menajer{" + "id='" + getId() + '\'' + ", ad='" + getAd() + '\'' + ", soyad='" + getSoyad() + '\'' + ", kulupId='" + getKulupId() + '\'' + ", sozlesmeBitisTarihi=" + getSozlesmeBitisTarihi() + ", dogumTarihi=" + getDogumTarihi() + ", uyruk=" + getUyruk() + ", maas='" + getMaas() + '\'' + '}';
	}
}