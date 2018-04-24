package model;

public enum ResourceType {

	WOL("W"),
	HOUT("H"),
	BAKSTEEN("B"),
	ERTS("E"),
	GRAAN("G"),
	WOESTIJN("X");
	
	private final String shortCode;
	  
	  ResourceType(String code) {
	      this.shortCode = code;
	  }
		  
	  public String getResourceTypeCode() {
	      return this.shortCode;
	  }

}
