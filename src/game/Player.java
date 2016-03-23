package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player implements KeyListener{
	
	private Position position, lastPosition;
	private int direction, lastDirection;
	private Portal bluePortal, orangePortal;
	private int inventory;
	private boolean boxAvailable;
	
	Player(Position c){
		position = new Position(c.getX(),c.getY());
		setLastPosition(new Position());
		direction = Direction.STAY;
		setLastDirection(Direction.LEFT);
		bluePortal = new Portal();
		orangePortal = new Portal();
		inventory = 0;
		setBoxAvailable(false);

	}
	
	public int getDirection(){
		return direction;
	}
	
	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	public Portal getPortal(int num){
		if(num == 0){
			return bluePortal;
		}else{
			return orangePortal;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_W:
			direction = Direction.UP;
			Game.move();
			break;
		case KeyEvent.VK_S:
			direction = Direction.DOWN;
			Game.move();
			break;
		case KeyEvent.VK_D:
			direction = Direction.RIGHT;
			Game.move();
			break;
		case KeyEvent.VK_A:
			direction = Direction.LEFT;
			Game.move();
			break;
		case KeyEvent.VK_B:
			if(bluePortal.isActive()){
				bluePortal.closePortal();
			}else if(orangePortal.isActive() && bluePortal.getFuturePlace().equals(orangePortal.getPlace())){
				orangePortal.closePortal();
			}
			bluePortal.openPortal(true);
			break;
		case KeyEvent.VK_N:
			if(orangePortal.isActive()){
				orangePortal.closePortal();
			}else if(bluePortal.isActive() && orangePortal.getFuturePlace().equals(bluePortal.getPlace())){
				bluePortal.closePortal();
			}
			orangePortal.openPortal(false);
			break;
		case KeyEvent.VK_E:
			if(Panel.getPlayingField().getMapCellType(position) == CellType.BOX){
				if(!boxAvailable){
					Panel.getPlayingField().setMapCellType(position, CellType.PATH);
					boxAvailable = true;
				}
			}else if(Panel.getPlayingField().getMapCellType(position) == CellType.PRESSUREDPLATE){
				if(!boxAvailable){
					Panel.getPlayingField().setMapCellType(position, CellType.PLATE);
					boxAvailable = true;
				}
			}else{
				if(boxAvailable){
					if(Panel.getPlayingField().getMapCellType(position) == CellType.PLATE){
						Panel.getPlayingField().setMapCellType(position, CellType.PRESSUREDPLATE);
						boxAvailable = false;
					}else{
						Panel.getPlayingField().setMapCellType(position, CellType.BOX);
						boxAvailable = false;
					}
					
				}
			}
			
		}
			
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() != KeyEvent.VK_B && e.getKeyCode() != KeyEvent.VK_N && e.getKeyCode() != KeyEvent.VK_E){
			setLastDirection(direction);
			direction = Direction.STAY;
		}
		
	}

	public int getLastDirection() {
		return lastDirection;
	}

	public void setLastDirection(int lastDirection) {
		this.lastDirection = lastDirection;
	}

	public int getInventory() {
		return inventory;
	}

	public void incInventory() {
		this.inventory+=1;
	}

	public boolean isBoxAvailable() {
		return boxAvailable;
	}

	public void setBoxAvailable(boolean boxAvailable) {
		this.boxAvailable = boxAvailable;
	}

	public Position getLastPosition() {
		return lastPosition;
	}

	public void setLastPosition(Position lastPosition) {
		this.lastPosition = lastPosition;
	}
}
