package model;

public class Street extends Building{

	private StreetLocation streetLocation;
	public final static ResourceType[] cost = new ResourceType[] {ResourceType.BAKSTEEN, ResourceType.HOUT};
	
	private int db_xfrom;
	private int db_yfrom;
	private int db_xto;
	private int db_yto;
	
	public Street(String idstreet, int db_xfrom, int db_yfrom, int db_xto, int db_yto) {
		this.idBuilding = idstreet;
		this.db_xfrom = db_xfrom;
		this.db_yfrom = db_yfrom;
		this.db_xto = db_xto;
		this.db_yto = db_yto;
	}
	
	public int getDb_xfrom() {
		return db_xfrom;
	}

	public void setDb_xfrom(int db_xfrom) {
		this.db_xfrom = db_xfrom;
	}

	public int getDb_yfrom() {
		return db_yfrom;
	}

	public void setDb_yfrom(int db_yfrom) {
		this.db_yfrom = db_yfrom;
	}

	public int getDb_xto() {
		return db_xto;
	}

	public void setDb_xto(int db_xto) {
		this.db_xto = db_xto;
	}

	public int getDb_yto() {
		return db_yto;
	}

	public void setDb_yto(int db_yto) {
		this.db_yto = db_yto;
	}

	public StreetLocation getStreetLocation() {
		return streetLocation;
	}

	public void setStreetLocation(StreetLocation streetLocation) {
		this.streetLocation = streetLocation;
	}
	
	public boolean isBuild() {
		if(streetLocation == null) {
			return false;
		}
		return true;
	}
}
