package sakila.vo;

public class Inven {
	private int inventoryId;
	private int count;
	private String title;
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "Inven [inventoryId=" + inventoryId + ", count=" + count + ", title=" + title + "]";
	}
	
}
