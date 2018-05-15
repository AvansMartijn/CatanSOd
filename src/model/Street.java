package model;

public class Street extends Building{

	private StreetLocation streetLocation;

	public Street() {

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
