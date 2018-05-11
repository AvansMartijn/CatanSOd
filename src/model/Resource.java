package model;

public class Resource {

	ResourceType resourceType;

	public Resource(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	
	public boolean hasResourceType(ResourceType resourceType) {
		if (this.resourceType == resourceType) {
			return true;
		}
		return false;
	}
	
	public ResourceType getResourceType() {
		return resourceType;
	}
}