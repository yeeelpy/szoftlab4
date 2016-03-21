package game;

public class Portal {
	
	private boolean active;
	private Position outerPosition, place;
	
	Portal(){
		active = false;
		outerPosition = new Position();
		place = new Position();
	}

	public void openPortal(boolean blue){
		place = new Position(Game.getPlayer().getPosition().getX(),Game.getPlayer().getPosition().getY());
		placeEnds();
		if(blue){
			Panel.getPlayingField().getMap()[place.getX()][place.getY()].setType(CellType.BLUEPORTAL);
		}else{
			Panel.getPlayingField().getMap()[place.getX()][place.getY()].setType(CellType.ORANGEPORTAL);
		}
		
		setActive(true);
	}
	
	private void placeEnds(){
		if(Game.getPlayer().getLastDirection() == Direction.UP){
			while(notPlace()){
				place.setY(place.getY()-1);
			}
			outerPosition = new Position(place.getX(), place.getY()+1);
		}else if(Game.getPlayer().getLastDirection() == Direction.DOWN){
			while(notPlace()){
				place.setY(place.getY()+1);
			}
			outerPosition = new Position(place.getX(), place.getY()-1);
		}else if(Game.getPlayer().getLastDirection() == Direction.LEFT){
			while(notPlace()){
				place.setX(place.getX()-1);
			}
			outerPosition = new Position(place.getX()+1, place.getY());
		}else if(Game.getPlayer().getLastDirection() == Direction.RIGHT){
			while(notPlace()){
				place.setX(place.getX()+1);
			}
			outerPosition = new Position(place.getX()-1, place.getY());
		}
	}
	
	public Position getFuturePlace(){
		Position futurePlace = new Position();
		if(Game.getPlayer().getLastDirection() == Direction.UP){
			while(notFuturePlace(futurePlace)){
				futurePlace.setY(futurePlace.getY()-1);
			}
		}else if(Game.getPlayer().getLastDirection() == Direction.DOWN){
			while(notFuturePlace(futurePlace)){
				futurePlace.setY(futurePlace.getY()+1);
			}
		}else if(Game.getPlayer().getLastDirection() == Direction.LEFT){
			while(notFuturePlace(futurePlace)){
				futurePlace.setX(futurePlace.getX()-1);
			}
		}else if(Game.getPlayer().getLastDirection() == Direction.RIGHT){
			while(notFuturePlace(futurePlace)){
				futurePlace.setX(futurePlace.getX()+1);
			}
		}
		return futurePlace;
	}
	
	private boolean notPlace(){
		return !Panel.getPlayingField().getMap()[place.getX()][place.getY()].getType().equals(CellType.WALL) && !Panel.getPlayingField().getMap()[place.getX()][place.getY()].getType().equals(CellType.BLUEPORTAL) && !Panel.getPlayingField().getMap()[place.getX()][place.getY()].getType().equals(CellType.ORANGEPORTAL);
	}
	
	private boolean notFuturePlace(Position futurePlace){
		return !Panel.getPlayingField().getMap()[futurePlace.getX()][futurePlace.getY()].getType().equals(CellType.WALL) && !Panel.getPlayingField().getMap()[futurePlace.getX()][futurePlace.getY()].getType().equals(CellType.BLUEPORTAL) && !Panel.getPlayingField().getMap()[futurePlace.getX()][futurePlace.getY()].getType().equals(CellType.ORANGEPORTAL);
	}
	
	public void closePortal(){
		Panel.getPlayingField().getMap()[place.getX()][place.getY()].setType(CellType.WALL);
		setActive(false);
	}

	public Position getOuterPosition() {
		return outerPosition;
	}
	
	public Position getPlace() {
		return place;
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean b){
		this.active = b;
	}
}
