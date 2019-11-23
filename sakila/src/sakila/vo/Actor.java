package sakila.vo;

public class Actor {
	private String name;
	private int actorId;
	private String firstName;
	private String lastName;
	private String lastUpdate;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getActorId() {
		return actorId;
	}
	public void setActorId(int actorId) {
		this.actorId = actorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@Override
	public String toString() {
		return "Actor [name=" + name + ", actorId=" + actorId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", lastUpdate=" + lastUpdate + "]";
	}
	
	
}
