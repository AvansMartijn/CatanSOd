package model;

public class Resource {
	private ResourceType rsType;
	private String resourceID;
	
	public Resource(String resourceID) {
		this.resourceID = resourceID;
		
		switch(resourceID.substring(0, 1)) {
		case "b":
			this.rsType = ResourceType.BAKSTEEN;
			break;
		case "g":
			this.rsType = ResourceType.GRAAN;
			break;
		case "h":
			this.rsType = ResourceType.HOUT;
			break;
		case "e":
			this.rsType = ResourceType.ERTS;
			break;
		case "w":
			this.rsType = ResourceType.WOL;
		}
	}

	public ResourceType getRsType() {
		return rsType;
	}

	public String getResourceID() {
		return resourceID;
	}
	

}
