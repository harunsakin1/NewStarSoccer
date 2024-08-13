package SoccerApp.entities;

import java.util.HashSet;
import java.util.Set;

public class Kulup extends BaseEntity{
	private String ad;
	private String kurulusTarihi;
	private String stadyumId;
	private Set<String> futbolcuList;
	private String menajer;
	private String baskan;
	private String butce;
	
	{
		futbolcuList = new HashSet<>();
	}
	
	//GETTES
	public Kulup() {
	}
	
	public String getAd() {
		return ad;
	}
	
	public String getKurulusTarihi() {
		return kurulusTarihi;
	}
	
	public String getStadyumId() {
		return stadyumId;
	}
	
	
	public Set<String> getFutbolcuList() {
		return futbolcuList;
	}
	
	public String getButce() {
		return butce;
	}
	// SETTERS
	public void setAd(String ad) {
		this.ad = ad;
	}
	
	public void setStadyumId(String stadyumId) {
		this.stadyumId = stadyumId;
	}
	
	
	public void setButce(String butce) {
		this.butce = butce;
	}
	
	public void setKurulusTarihi(String kurulusTarihi) {
		this.kurulusTarihi = kurulusTarihi;
	}
	
	public void setFutbolcuList(Set<String> futbolcuList) {
		this.futbolcuList = futbolcuList;
	}
	
	public String getMenajer() {
		return menajer;
	}
	
	public void setMenajer(String menajer) {
		this.menajer = menajer;
	}
	
	public String getBaskan() {
		return baskan;
	}
	
	public void setBaskan(String baskan) {
		this.baskan = baskan;
	}
	
	@Override
	public String toString() {
		return "Kulup{" + "ad='" + getAd() + '\'' + ", kurulusTarihi='" + getKurulusTarihi() + '\'' + ", stadyum=" + getStadyumId() + ", futbolcuList=" + getFutbolcuList() + ", menajer='" + getMenajer() + '\'' + ", baskan='" + getBaskan() + '\'' + ", butce='" + getButce() + '\'' + ", id='" + getId() + '\'' + '}';
	}
}