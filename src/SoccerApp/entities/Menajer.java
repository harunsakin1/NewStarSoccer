package SoccerApp.entities;

import SoccerApp.utility.IHesap;
import SoccerApp.utility.enums.EUyruk;

import java.time.LocalDate;

public class Menajer extends Insan implements IHesap {
	private String kulupId;
	private LocalDate sozlesmeBitisTarihi;
	private String sifre;
	
	
	public Menajer(){
		kulupId="-1";
		sifre = "";
	}
	
	public Menajer(String kulupId, int yil, String sifre){
		this.kulupId=kulupId;
		this.sozlesmeBitisTarihi=LocalDate.now().plusYears(yil);
		this.sifre = sifre;
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
		return "Menajer{" + "id='" + getId() + '\'' + ", kulupId='" + getKulupId() + '\'' + ", sifre='" + getSifre() + '\'' + ", ad='" + getAd() + '\'' + ", soyad='" + getSoyad() + '\'' + ", sozlesmeBitisTarihi=" + getSozlesmeBitisTarihi() + ", dogumTarihi=" + getDogumTarihi() + ", uyruk=" + getUyruk() + ", maas='" + getMaas() + '\'' + '}';
	}
	
	@Override
	public String getSifre() {
		return this.sifre;
	}
}