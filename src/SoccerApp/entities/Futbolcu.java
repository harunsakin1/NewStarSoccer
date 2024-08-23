package SoccerApp.entities;

import SoccerApp.utility.enums.EMevki;
import SoccerApp.utility.enums.EUyruk;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Futbolcu extends Insan{
	private int formaNumarasi;
	private String bonservis;
	private EMevki mevki;
	private int yetenekPuani;
	private String kulupId;
	private LocalDate sozlesmeBitisTarihi;
	//TODO Sözleşme bitiş zamanı eklenebilir
	//CONSTRUCTORS
	
	
	public Optional<String> getKulupId() {
		return Optional.ofNullable(kulupId);
	}
	
	public void setKulupId(String kulupId) {
		this.kulupId = kulupId;
	}
	
	public Futbolcu() {
	}
	
	
	{
		kulupId = "-1";
	}
	
	public Futbolcu(String ad, String soyad, LocalDate dogumTarihi, EUyruk uyruk, String maas, int formaNumarasi,
	                String bonservis, EMevki mevki, int yetenekPuani, String kulupId, String uuid) {
		super(ad, soyad, dogumTarihi, uyruk, maas);
		this.formaNumarasi = formaNumarasi;
		this.bonservis = bonservis;
		this.mevki = mevki;
		this.yetenekPuani = yetenekPuani;
		this.kulupId = kulupId;
		setId(uuid);
	}
	
		public int getFormaNumarasi() {
		return formaNumarasi;
	}
	
	public String getBonservis() {
		return bonservis;
	}
	
	public void setBonservis(String bonservis) {
		this.bonservis = bonservis;
	}
	
	public void setFormaNumarasi(int formaNumarasi) {
		this.formaNumarasi = formaNumarasi;
	}
	
	public EMevki getMevki() {
		return mevki;
	}
	
	public void setMevki(EMevki mevki) {
		this.mevki = mevki;
	}
	
	public int getYetenekPuani() {
		return yetenekPuani;
	}
	
	public boolean setYetenekPuani(int yetenekPuani) {
		if(yetenekPuani >= 0 && yetenekPuani <= 100 ){
			this.yetenekPuani = yetenekPuani;
			return true;
		} else
		return false;
		
	}
	
	public String goruntuleDetayli(){
		return "Futbolcu{" + "formaNumarasi=" + getFormaNumarasi() + ", bonservis='" + getBonservis() + '\'' + ", mevki=" + getMevki() + ", yetenekPuani=" + getYetenekPuani() + ", kulupId='" + getKulupId().get() + '\'' + ", ad='" + getAd() + '\'' + ", soyad='" + getSoyad() + '\'' + ", dogumTarihi=" + getDogumTarihi() + ", uyruk=" + getUyruk() + ", maas='" + getMaas() + '\'' + ", id='" + getId() + '\'' + '}';
	}
	
	@Override
	public String toString() {
		return "Futbolcu{" + "ad='" + getAd() + '\'' + ", soyad='" + getSoyad() + '\'' + ", dogumTarihi=" + getDogumTarihi() + ", mevki=" + getMevki() + ", yetenekPuani=" + getYetenekPuani() + ", formaNumarasi=" + getFormaNumarasi() + '}';
	}
}