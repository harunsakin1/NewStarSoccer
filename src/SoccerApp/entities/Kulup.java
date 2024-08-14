package SoccerApp.entities;

public class Kulup extends BaseEntity{
	private String ad;
	private String kurulusTarihi;
	private String stadyumId;
	private String stadyumAdi;
	private boolean varMiMenajerId;//TODO Kulübe menajer atandığında true yapılacak
	// TODO TAM TERSİ DE YAPILACAK
	private String baskan;
	private String butce;
	
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
	
	public boolean getVarMiMenajerId() {
		return varMiMenajerId;
	}
	
	public void setVarMiMenajerId(boolean varMiMenajerId) {
		this.varMiMenajerId = varMiMenajerId;
	}
	
	public String getBaskan() {
		return baskan;
	}
	
	public void setBaskan(String baskan) {
		this.baskan = baskan;
	}
	
	public String getStadyumAdi() {
		return stadyumAdi;
	}
	
	@Override
	public String toString() {
		return "Kulup{" + "id='" + getId() + '\'' +
				", ad='" + getAd() + '\'' +
				", kurulusTarihi='" + getKurulusTarihi() + '\'' +
				", stadyumId='" + getStadyumId() + '\'' +
				", stadyumAdi='" + getStadyumAdi() + '\'' +
				", menajer='" + getVarMiMenajerId() + '\'' +
				", baskan='" + getBaskan() + '\'' +
				", butce='" + getButce() + '\'' + '}';
	}
}