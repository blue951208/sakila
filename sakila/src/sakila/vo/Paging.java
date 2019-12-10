package sakila.vo;

public class Paging {
	private int count;
	private int lastPage;
	private int beginRow;
	private int rowPerPage;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}
	public int getBeginRow() {
		return beginRow;
	}
	public void setBeginRow(int beginRow) {
		this.beginRow = beginRow;
	}
	public int getRowPerPage() {
		return rowPerPage;
	}
	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}
	@Override
	public String toString() {
		return "Paging [count=" + count + ", lastPage=" + lastPage + ", beginRow=" + beginRow + ", rowPerPage="
				+ rowPerPage + "]";
	}
	
}	
