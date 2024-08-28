package SoccerApp.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Takim implements Serializable {
	private String kulupId;
	private List<String> kadro;
	private Integer skor=0;
	
	{
		kadro = new ArrayList<>();
	}
	
	public String getKulupId() {
		return kulupId;
	}
	
	public void setKulupId(String kulupId) {
		this.kulupId = kulupId;
	}
	
	public List<String> getKadro() {
		return kadro;
	}
	
	public void setKadro(List<String> kadro) {
		this.kadro = kadro;
	}
	
	public Integer getSkor() {
		return skor;
	}
	
	public void arttirSkor() {
		this.skor++;
	}
	
	
	
	@Override
	public String toString() {
		return "Takim{" + "kulupId='" + getKulupId() + '\'' + ", kadro=" + getKadro() + ", skor=" + getSkor() + '}';
	}
}