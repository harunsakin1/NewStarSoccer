package SoccerApp.entities;

import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class BaseEntity implements Serializable {
	private String uuid;
	private static int nextId= 1;
	
	{
		this.uuid = String.valueOf(nextId++);
	}
	
	public String getId() {
		return uuid;
	}
	
	public void setId(String uuid){
		this.uuid = uuid;
	}
}