package SoccerApp.entities;

public class Kulup extends BaseEntity{
	private String ad;
	private String kurulusTarihi;
	private String stadyumId;
	private String stadyumAdi;
	private boolean varMiMenajer;
	// TODO TAM TERSİ DE YAPILACAK -- MENAJER DB DE YAPILACAK
	private String baskan;
	private String butce;
	private String maasButceYillik;
	
	{
		varMiMenajer = false;
	}
	public void setStadyumAdi(String stadyumAdi) {
		this.stadyumAdi = stadyumAdi;
	}
	
	public boolean isVarMiMenajer() {
		return varMiMenajer;
	}
	
	public String getMaasButceYillik() {
		return maasButceYillik;
	}
	
	public void setMaasButceYillik(String maasButceYillik) {
		this.maasButceYillik = maasButceYillik;
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
		return varMiMenajer;
	}
	
	public void setVarMiMenajer(boolean varMiMenajer) {
		this.varMiMenajer = varMiMenajer;
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
	
	public String goruntuleDetayKulup() {
		return "Kulup{" + "id='" + getId() + '\'' +
				", ad='" + getAd() + '\'' +
				", kurulusTarihi='" + getKurulusTarihi() + '\'' +
				", stadyumId='" + getStadyumId() + '\'' +
				", stadyumAdi='" + getStadyumAdi() + '\'' +
				", menajer='" + getVarMiMenajerId() + '\'' +
				", baskan='" + getBaskan() + '\'' +
				", butce='" + getButce() + '\'' + '}';
	}
	
	
	@Override
	public String toString() {
		return "Kulup{" + "ad='" + getAd() + '\'' + ", kurulusTarihi='" + getKurulusTarihi() + '\'' + ", stadyumId='" + getStadyumId() + '\'' + ", stadyumAdi='" + getStadyumAdi() + '\'' + ", varMiMenajerId=" + isVarMiMenajer() + ", baskan='" + getBaskan() + '\'' + ", butce='" + getButce() + '\'' + ", maasButceYillik='" + getMaasButceYillik() + '\'' + ", id='" + getId() + '\'' + '}';
	}
}