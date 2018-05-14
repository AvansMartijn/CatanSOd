package model;

public class Settlement extends Building{
	private boolean isCity;
	
	public Settlement(boolean isCity) {
		this.isCity = isCity;
		if(isCity) {
			victoryPoint = 2;
		}else {
			victoryPoint = 1;
		}
	}

	public boolean isCity() {
		return isCity;
	}

	public void setCity(boolean isCity) {
		this.isCity = isCity;
	}
	
	
}
