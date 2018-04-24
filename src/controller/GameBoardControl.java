package controller;

import model.BuildingLocation;
import model.Harbour;
import model.ResourceType;

public class GameBoardControl {
	
	public void createBoard() {
		createBuildingLocations();

	}
	
	private void createBuildingLocations() {
		BuildingLocation bl1 = new BuildingLocation(1, 3, null);
		BuildingLocation bl2 = new BuildingLocation(1, 4, null);
		BuildingLocation bl3 = new BuildingLocation(2, 2, new Harbour(ResourceType.BAKSTEEN));
		BuildingLocation bl4 = new BuildingLocation(2, 3, new Harbour(ResourceType.BAKSTEEN));
		BuildingLocation bl5 = new BuildingLocation(2, 5, new Harbour(ResourceType.HOUT));
		BuildingLocation bl6 = new BuildingLocation(2, 6, new Harbour(ResourceType.HOUT));
		BuildingLocation bl7 = new BuildingLocation(3, 1, new Harbour(null));
		BuildingLocation bl8 = new BuildingLocation(3, 2, null);
		BuildingLocation bl9 = new BuildingLocation(3, 4, null);
		BuildingLocation bl10 = new BuildingLocation(3, 5, null);
		BuildingLocation bl11 = new BuildingLocation(3, 7, null);
		BuildingLocation bl12 = new BuildingLocation(3, 8, new Harbour(null));
		BuildingLocation bl13 = new BuildingLocation(4, 1, new Harbour(null));
		BuildingLocation bl14 = new BuildingLocation(4, 3, null);
		BuildingLocation bl15 = new BuildingLocation(4, 4, null);
		BuildingLocation bl16 = new BuildingLocation(4, 6, null);
		BuildingLocation bl17 = new BuildingLocation(4, 7, null);
		BuildingLocation bl18 = new BuildingLocation(4, 9, new Harbour(null));
		BuildingLocation bl19 = new BuildingLocation(5, 2, null);
		BuildingLocation bl20 = new BuildingLocation(5, 3, null);
		BuildingLocation bl21 = new BuildingLocation(5, 5, null);
		BuildingLocation bl22 = new BuildingLocation(5, 6, null);
		BuildingLocation bl23 = new BuildingLocation(5, 8, null);
		BuildingLocation bl24 = new BuildingLocation(5, 9, null);
		BuildingLocation bl25 = new BuildingLocation(6, 2, new Harbour(null));
		BuildingLocation bl26 = new BuildingLocation(6, 4, null);
		BuildingLocation bl27 = new BuildingLocation(6, 5, null);
		BuildingLocation bl28 = new BuildingLocation(6, 7, null);
		BuildingLocation bl29 = new BuildingLocation(6, 8, null);
		BuildingLocation bl30 = new BuildingLocation(6, 10, new Harbour(ResourceType.GRAAN));
		BuildingLocation bl31 = new BuildingLocation(7, 3, new Harbour(null));
		BuildingLocation bl32 = new BuildingLocation(7, 4, null);
		BuildingLocation bl33 = new BuildingLocation(7, 6, null);
		BuildingLocation bl34 = new BuildingLocation(7, 7, null);
		BuildingLocation bl35 = new BuildingLocation(7, 9, null);
		BuildingLocation bl36 = new BuildingLocation(7, 10, new Harbour(ResourceType.GRAAN));
		BuildingLocation bl37 = new BuildingLocation(8, 3, null);
		BuildingLocation bl38 = new BuildingLocation(8, 5, null);
		BuildingLocation bl39 = new BuildingLocation(8, 6, null);
		BuildingLocation bl40 = new BuildingLocation(8, 8, null);
		BuildingLocation bl41 = new BuildingLocation(8, 9, null);
		BuildingLocation bl42 = new BuildingLocation(8, 11, null);
		BuildingLocation bl43 = new BuildingLocation(9, 4, null);
		BuildingLocation bl44 = new BuildingLocation(9, 5, new Harbour(ResourceType.WOL));
		BuildingLocation bl45 = new BuildingLocation(9, 7, null);
		BuildingLocation bl46 = new BuildingLocation(9, 8, null);
		BuildingLocation bl47 = new BuildingLocation(9, 10, new Harbour(ResourceType.ERTS));
		BuildingLocation bl48 = new BuildingLocation(9, 11, null);
		BuildingLocation bl49 = new BuildingLocation(10, 6, new Harbour(ResourceType.WOL));
		BuildingLocation bl50 = new BuildingLocation(10, 7, null);
		BuildingLocation bl51 = new BuildingLocation(10, 9, null);
		BuildingLocation bl52 = new BuildingLocation(10, 10, new Harbour(ResourceType.ERTS));
		BuildingLocation bl53 = new BuildingLocation(11, 8, new Harbour(null));
		BuildingLocation bl54 = new BuildingLocation(11, 9, new Harbour(null));
	}

}
