package sakila.vo;

public class Store {
	private int storeId;
	private int managerStaff;
	private int addressId;
	private String lastUpdate;
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getManagerStaff() {
		return managerStaff;
	}
	public void setManagerStaff(int managerStaff) {
		this.managerStaff = managerStaff;
	}
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@Override
	public String toString() {
		return "Store [storeId=" + storeId + ", managerStaff=" + managerStaff + ", addressId=" + addressId
				+ ", lastUpdate=" + lastUpdate + "]";
	}
	
	
	
}
