package model;

public class TradeRequest {
	

	private int idPlayer;
	private int g_brick = 0;
	private int g_wool = 0;
	private int g_iron = 0;
	private int g_wheat = 0;
	private int g_wood = 0;
	private int w_brick = 0;
	private int w_wool = 0;
	private int w_iron = 0;
	private int w_wheat = 0;
	private int w_wood = 0;
	private int accepted;

	public TradeRequest(int idPlayer, int g_brick, int g_wool, int g_iron, int g_wheat, int g_wood, int w_brick,
			int w_wool, int w_iron, int w_wheat, int w_wood) {
		this.idPlayer = idPlayer;
		this.g_brick = g_brick;
		this.g_wool = g_wool;
		this.g_iron = g_iron;
		this.g_wheat = g_wheat;
		this.g_wood = g_wood;
		this.w_brick = w_brick;
		this.w_wool = w_wool;
		this.w_iron = w_iron;
		this.w_wheat = w_wheat;
		this.w_wood = w_wood;
		
		
	}

	public TradeRequest(int idPlayer, int g_brick, int g_wool, int g_iron, int g_wheat, int g_wood, int w_brick,
			int w_wool, int w_iron, int w_wheat, int w_wood, int accepted) {
		this.idPlayer = idPlayer;
		this.g_brick = g_brick;
		this.g_wool = g_wool;
		this.g_iron = g_iron;
		this.g_wheat = g_wheat;
		this.g_wood = g_wood;
		this.w_brick = w_brick;
		this.w_wool = w_wool;
		this.w_iron = w_iron;
		this.w_wheat = w_wheat;
		this.w_wood = w_wood;
		this.accepted = accepted;
	}
	
	
	public int getAccepted() {
		return accepted;
	}

	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}
	
	public int getIdPlayer() {
		return idPlayer;
	}

	public int getG_brick() {
		return g_brick;
	}

	public int getG_wool() {
		return g_wool;
	}

	public int getG_iron() {
		return g_iron;
	}

	public int getG_wheat() {
		return g_wheat;
	}

	public int getG_wood() {
		return g_wood;
	}

	public int getW_brick() {
		return w_brick;
	}

	public int getW_wool() {
		return w_wool;
	}

	public int getW_iron() {
		return w_iron;
	}

	public int getW_wheat() {
		return w_wheat;
	}

	public int getW_wood() {
		return w_wood;
	}

}
