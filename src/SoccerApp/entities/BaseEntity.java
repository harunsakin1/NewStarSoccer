package SoccerApp.entities;

public abstract class BaseEntity {
	private String uuid;
	private static int nextId;
	
	{
		//this.uuid = UUID.randomUUID().toString(); //TODO sistemi kolaylastir burayi etkinlestir
		this.uuid = String.valueOf(nextId++);
	}
	
	public String getId() {
		return uuid;
	}
}