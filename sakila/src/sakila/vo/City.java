package sakila.vo;

public class City {
	private int cityId;
	private String city;
	private Country country;
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", city=" + city + ", country=" + country + "]";
	}
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
}
