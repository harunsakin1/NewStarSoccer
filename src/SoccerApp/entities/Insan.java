package SoccerApp.entities;

import SoccerApp.utility.enums.EUyruk;

import java.time.LocalDate;

public class Insan extends BaseEntity{
	private String ad;
	private String soyad;
	private LocalDate dogumTarihi;
	private EUyruk uyruk;
	private String maas;
	
	//CONSTRUCTORS
	
	
	public Insan(String ad, String soyad, LocalDate dogumTarihi, EUyruk uyruk, String maas) {
		this.ad = ad;
		this.soyad = soyad;
		this.dogumTarihi = dogumTarihi;
		this.uyruk = uyruk;
		this.maas = maas;
	}
	
	public Insan() {
	}
	
	//GETTERS
	
	public String getAd() {
		return ad;
	}
	
	public String getSoyad() {
		return soyad;
	}
	
	public LocalDate getDogumTarihi() {
		return dogumTarihi;
	}
	
	public EUyruk getUyruk() {
		return uyruk;
	}
	
	public String getMaas() {
		return maas;
	}
	//SETTERS
	
	public void setAd(String ad) {
		this.ad = ad;
	}
	
	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}
	
	public void setMaas(String maas) {
		this.maas = maas;
	}
	
	@Override
	public String toString() {
		return "Insan{" + "ad='" + getAd() + '\'' + ", soyad='" + getSoyad() + '\'' + ", dogumTarihi=" + getDogumTarihi() + ", uyruk=" + getUyruk() + ", maas='" + getMaas() + '\'' + ", id='" + getId() + '\'' + '}';
	}
	
	public void setDogumTarihi(LocalDate dogumTarihi) {
		this.dogumTarihi = dogumTarihi;
	}
	
	public void setUyruk(EUyruk uyruk) {
		this.uyruk = uyruk;
	}
}