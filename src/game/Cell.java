package game;

public class Cell extends Position{
	
	
	private CellType type;
	
	Cell(int x, int y, CellType type){
			this.setX(x);
			this.setY(y);
			this.type = type;
	}
	
	public CellType getType(){
		return this.type;
	}
	
	public void setType(CellType type){
		this.type = type;
	}
}
