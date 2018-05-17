package model;

public class Resource {
	private ResourceType rsType;
	
	public Resource(ResourceType resourceType) {
		this.rsType = resourceType;
	}

	public ResourceType getRsType() {
		return rsType;
	}

}
