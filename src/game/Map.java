package game;


public class Map {
	
	public static final int LIMIT = 11;
	public static final int ABYSSCOUNT = 1;
	public static final int ZPMCOUNT = 1;
	public static final int DOORCOUNT = 6;
	public static final int BOXCOUNT = 4;

	private Cell[][] map;
	private DoorManager doorManager;
	
	Map(){
		map = new Cell[LIMIT][LIMIT];
		doorManager = new DoorManager();
		initMap();
		generateLab(map, 1, 1);
		placeAbyss();
		placeZPM();
		placeBox();
		placeDoorPlatePairs();
	}
	
	public Cell[][] getMap(){
		return map;
	}
	
	public CellType getMapCellType(Position p){
		return map[p.getX()][p.getY()].getType();
	}
	
	public void setMapCellType(Position p, CellType c){
		if(p==null) System.out.println("no such cell"+c);
		else map[p.getX()][p.getY()].setType(c);
	}
	
	public DoorManager getDoorManager(){
		return doorManager;
	}
	
	private void initMap(){
		for(int i = 0; i < LIMIT; i++){
			for(int j = 0; j < LIMIT; j++){
				if(i == 0 || j == 0 || i == LIMIT-1 || j == LIMIT-1){
					map[i][j] = new Cell(i,j,CellType.WALL);
				}else{
					map[i][j] = new Cell(i,j,CellType.WALL);
				}
			}
		}
	}
	
	enum Dir {UP, DOWN, RIGHT, LEFT};
	private void generateLab(Cell[][] m, int x, int y){
		Dir dir[] = Dir.values();
		
		if(y != 0 && x != 0 && y != LIMIT-1 && x != LIMIT-1){
			map[y][x].setType(CellType.PATH);
		}else return;
		
		
		for(int i = 0; i < 4; i++){
			int r = (int)(Math.random() * 4);
			Dir temp = dir[i];
			dir[i] = dir[r];
			dir[r] = temp;
		}
		
		for(int i = 0; i < 4; i++){
			switch(dir[i]){
			case UP:
				if(y >= 2 && !map[y-2][x].getType().equals(CellType.PATH)){
					map[y-1][x].setType(CellType.PATH);
					generateLab(map, x, y-2);
				}
				break;
			case LEFT:
				if(x >= 2 && !map[y][x-2].getType().equals(CellType.PATH)){
					map[y][x-1].setType(CellType.PATH);
					generateLab(map, x-2, y);
				}
				break;
			case DOWN:
				if(y < LIMIT-2 && !map[y+2][x].getType().equals(CellType.PATH)){
					map[y+1][x].setType(CellType.PATH);
					generateLab(map, x, y+2);
				}
				break;
			case RIGHT:
				if(x < LIMIT-2 && !map[y][x+2].getType().equals(CellType.PATH)){
					map[y][x+1].setType(CellType.PATH);
					generateLab(map, x+2, y);
				}
				break;
			}
		}
	}
	
	private void placeAbyss() {
		int count = 0;
		while(count != ABYSSCOUNT){
			int r1 = 1 + (int)(Math.random() * (LIMIT-1));
			int r2 = 1 + (int)(Math.random() * (LIMIT-1));
			if(map[r1][r2].getType().equals(CellType.PATH) && !(r1 == 1 && r2 == 1) && !isEdge(r1,r2) && !map[r1][r2].getType().equals(CellType.ABYSS)){
				map[r1][r2].setType(CellType.ABYSS);
				count+=1;
			}
		}
	}
	
	private boolean isEdge(int i, int j){
		
		if(map[i-1][j].getType() == CellType.PATH &&
		   map[i+1][j].getType() == CellType.PATH ||
		   map[i][j-1].getType() == CellType.PATH &&
		   map[i][j+1].getType() == CellType.PATH){
			return false;
		}else{
			return true;
		}
		
	}
	
	
	
	private void placeZPM(){
		int count = 0;
		while(count != ZPMCOUNT){
			int r1 = 1 + (int)(Math.random() * (LIMIT-1));
			int r2 = 1 + (int)(Math.random() * (LIMIT-1));
			if(map[r1][r2].getType().equals(CellType.PATH) && !(r1 == 1 && r2 == 1) && !map[r1][r2].getType().equals(CellType.ABYSS) && !map[r1][r2].getType().equals(CellType.ZPM)){
				map[r1][r2].setType(CellType.ZPM);
				count+=1;
			}
		}
	}
	
	private void placeBox(){
		int count = 0;
		while(count != BOXCOUNT){
			int r1 = 1 + (int)(Math.random() * (LIMIT-1));
			int r2 = 1 + (int)(Math.random() * (LIMIT-1));
			if(map[r1][r2].getType().equals(CellType.PATH) && !(r1 == 1 && r2 == 1) && !map[r1][r2].getType().equals(CellType.ABYSS)
			   && !map[r1][r2].getType().equals(CellType.ZPM) && !map[r1][r2].getType().equals(CellType.BOX) && !isEdge(r1,r2)){
				map[r1][r2].setType(CellType.BOX);
				count+=1;
			}
		}
	}
	
	private void placeDoorPlatePairs() {
		int count = 0;
		while(count != Map.DOORCOUNT){
			int r1 = 1 + (int)(Math.random() * (Map.LIMIT-1));
			int r2 = 1 + (int)(Math.random() * (Map.LIMIT-1));
			int r3 = 1 + (int)(Math.random() * (Map.LIMIT-1));
			int r4 = 1 + (int)(Math.random() * (Map.LIMIT-1));
			
			Position plate = new Position(r1,r2);
			Position door = new Position(r3,r4);
			
			if(map[r1][r2].getType().equals(CellType.PATH) &&
			   map[r3][r4].getType().equals(CellType.PATH) &&
			   !(r1 == 1 && r2 == 1 && r3 == 1 && r4 == 1) && 
			   !plate.equals(door) && !isEdge(r3,r4)){
				
				map[r1][r2].setType(CellType.PLATE);
				map[r3][r4].setType(CellType.DOOR);
				
				System.out.println(door.hashCode());
				
				
				doorManager.getDoorSystem().put(plate, door);
				
				count+=1;
			}
		}
		
	}
	
	public boolean isZPM(Position p){
		if(map[p.getX()][p.getY()].getType().equals(CellType.ZPM)){
			return true;
		}else
		return false;
	}
	
	public boolean isAbyss(Position p){
		if(map[p.getX()][p.getY()].getType().equals(CellType.ABYSS)){
			return true;
		}else
		return false;
	}

	public boolean isPlate(Position p) {
		if(map[p.getX()][p.getY()].getType().equals(CellType.PLATE)){
			return true;
		}else
		return false;
	}
	
	
}
