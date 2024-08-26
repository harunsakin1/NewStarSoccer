package SoccerApp.entities;

public class Istatistik extends BaseEntity{
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
	
	public void setGalibiyet(int galibiyet) {
		this.galibiyet = galibiyet;
	}
	
	public int getBeraberlik() {
		return beraberlik;
	}
	
	public void setBeraberlik(int beraberlik) {
		this.beraberlik = beraberlik;
	}
	
	public int getMaglubiyet() {
		return maglubiyet;
	}
	
	public void setMaglubiyet(int maglubiyet) {
		this.maglubiyet = maglubiyet;
	}
	
	public int getAtilanGol() {
		return atilanGol;
	}
	
	public void setAtilanGol(int atilanGol) {
		this.atilanGol = atilanGol;
	}
	
	public int getYenilenGol() {
		return yenilenGol;
	}
	
	public void setYenilenGol(int yenilenGol) {
		this.yenilenGol = yenilenGol;
	}
	
	public String getLigId() {
		return ligId;
	}
	
	public void setLigId(String ligId) {
		this.ligId = ligId;
	}
}