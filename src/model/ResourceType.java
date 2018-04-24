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
	  
	  public static ResourceType fromString(String code) {
		  switch(code) {
		  case "W":
			  return WOL;
		  case "H":
			  return HOUT;
		  case "B":
			  return BAKSTEEN;
		  case "E":
			  return ERTS;
		  case "G":
			  return GRAAN;
		  case "X":
			  return WOESTIJN;			  
		  }
		  return null;
	  }

}
