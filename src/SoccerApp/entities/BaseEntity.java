package SoccerApp.entities;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
	private String uuid;
	private static int nextId= 1;
	
	{
		//this.uuid = UUID.randomUUID().toString(); //TODO sistemi kolaylastir burayi etkinlestir
		this.uuid = String.valueOf(nextId++);
	}
	
	public String getId() {
		return uuid;
	}
}