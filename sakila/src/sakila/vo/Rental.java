package sakila.vo;

public class Rental {
	private int inventoryId;
	private int customerId;
	private int staffId;
	public int getInventoryId() {
		return inventoryId;
	}
	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	@Override
	public String toString() {
		return "Rental [inventoryId=" + inventoryId + ", customerId=" + customerId + ", staffId=" + staffId + "]";
	}
	
}
