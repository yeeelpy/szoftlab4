package game;

import java.util.HashMap;

public class DoorManager {
	private HashMap<Position, Position> doorSystem;
	//               KEY         VALUE
	//               PLATE       DOOR
	
	DoorManager(){
		doorSystem = new HashMap<Position, Position>();
	}

	public HashMap<Position, Position> getDoorSystem() {
		return doorSystem;
	}

	public void setDoorSystem(HashMap<Position, Position> doorSystem) {
		this.doorSystem = doorSystem;
	}

	public void openDoor(Position platePos) {
		
		System.out.println(doorSystem);
		
		Position doorPos = doorSystem.get(platePos);
		
		System.out.println(platePos.hashCode());
		
		Panel.getPlayingField().setMapCellType(doorSystem.get(platePos), CellType.PATH);
		
	}

	
	
	
}
