package SoccerApp.entities;

public class Stadyum extends BaseEntity{
	private Integer kapasite;
	private String ad;
	
	public Integer getKapasite() {
		return kapasite;
	}
	
	public void setKapasite(Integer kapasite) {
		this.kapasite = kapasite;
	}
	
	public String getAd() {
		return ad;
	}
	
	public void setAd(String ad) {
		this.ad = ad;
	}
	
	@Override
	public String toString() {
		return "Stadyum{" + "kapasite=" + getKapasite() + ", ad='" + getAd() + '\'' + '}';
	}
}