package SoccerApp.entities;

import java.io.Serializable;

public class Istatistik extends BaseEntity implements Serializable {
	private String kulupId;
	private int galibiyet;
	private int beraberlik;
	private int maglubiyet;
	private int atilanGol;
	private int yenilenGol;
	private String ligId;
	
	{
		this.galibiyet=0;
		this.beraberlik=0;
		this.maglubiyet=0;
		this.atilanGol=0;
		this.yenilenGol=0;
	}
	
	public String getKulupId() {
		return kulupId;
	}
	
	public void setKulupId(String kulupId) {
		this.kulupId = kulupId;
	}
	
	public int getGalibiyet() {
		return galibiyet;
	}
	
	public void artirGalibiyet(){
		galibiyet++;
	}
	
	public void azaltGalibiyet(){
		galibiyet--;
	}
	
	public int getBeraberlik() {
		return beraberlik;
	}
	
	public void artirBeraberlik(){
		beraberlik++;
	}
	
	public void azaltBeraberlik(){
		beraberlik--;
	}
	
	public int getMaglubiyet() {
		return maglubiyet;
	}
	
	public void artirMaglubiyet(){
		maglubiyet++;
	}
	
	public void azaltMaglubiyet(){
		maglubiyet--;
	}
	
	public int getAtilanGol() {
		return atilanGol;
	}
	
	public void artirAtilanGol() {
		atilanGol++;
	}
	
	public int getYenilenGol() {
		return yenilenGol;
	}
	
	public void artirYenilenGol() {
		yenilenGol++;
	}
	public int getAveraj(){
		return atilanGol - yenilenGol;
	}
	
	public int getPuan(){
		return galibiyet*3 + beraberlik;
	}
	public String getLigId() {
		return ligId;
	}
	
	public void setLigId(String ligId) {
		this.ligId = ligId;
	}
}