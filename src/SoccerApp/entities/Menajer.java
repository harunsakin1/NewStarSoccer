package SoccerApp.entities;

import SoccerApp.utility.enums.EUyruk;

import java.time.LocalDate;

public class Menajer extends Insan {
	private String kulupId;
	private LocalDate sozlesmeBitisTarihi;
	
	
	
	public Menajer(){
		kulupId="-1";
	}
	
	public Menajer(String ad, String soyad, LocalDate dogumTarihi, EUyruk uyruk, String maas, String kulupId,
	               int yil,String uuid) {
		super(ad, soyad, dogumTarihi, uyruk, maas);
		this.kulupId = kulupId;
		this.sozlesmeBitisTarihi=LocalDate.now().plusYears(yil);
		setId(uuid);
	}
	
	public Menajer(String kulupId, int yil, String uuid){
		this.kulupId=kulupId;
		this.sozlesmeBitisTarihi=LocalDate.now().plusYears(yil);
		setId(uuid);
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