package game;

public class Position {
	
	private int x, y;

	Position(){}
	
	Position(int x, int y){
			this.setX(x);
			this.setY(y);
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	@Override
	public String toString() {
		return "(" + getX() + "," + getY() + ")";
	}
	
	public boolean equals(Position pos) {
		if(this.getX() == pos.getX() && this.getY() == pos.getY()){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		
		return (this.x * 0x1f1f1f1f) ^ this.y + 1;
	}
}
