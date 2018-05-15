package model;

public class Street extends Building{

	private StreetLocation streetLocation;

	public Street(String idstreet, int db_xfrom, int db_yfrom, int db_xto, int db_yto) {

	}
	
	public StreetLocation getStreetLocation() {
		return streetLocation;
	}

	public void setStreetLocation(StreetLocation streetLocation) {
		this.streetLocation = streetLocation;
	}
	
	public boolean isBuild() {
		if(streetLocation == null) {
			return true;
		}
		return false;
	}
}
