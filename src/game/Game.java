package game;


public class Game implements Runnable{
	
	private Thread thread;
	private Panel game;
	private Position nextPosition;
	private static Player player;
	static boolean running = true;
	
	Game(){
		player = new Player(new Position(1,1));
		game = new Panel();
		thread = new Thread(this);
		nextPosition = new Position();
		thread.start();
	}
	
	public static Player getPlayer(){
		return player;
	}

	@Override
	public void run() {
		while(running){
			delay(70);
			move();
			checkAbbys();
			checkZPM();
			checkPlate();
			checkPortal();
			game.repaint();
		}
		
	}
	
	private void move(){
		if(player.getDirection() == Direction.UP){
			nextPosition = new Position(player.getPosition().getX(),player.getPosition().getY()-1);
			
		}else if(player.getDirection() == Direction.DOWN){
			nextPosition = new Position(player.getPosition().getX(),player.getPosition().getY()+1);
			
		}else if(player.getDirection() == Direction.LEFT){
			nextPosition = new Position(player.getPosition().getX()-1,player.getPosition().getY());
			
		}else if(player.getDirection() == Direction.RIGHT){
			nextPosition = new Position(player.getPosition().getX()+1,player.getPosition().getY());
			
		}
		if(!Panel.getPlayingField().getMapCellType(nextPosition).equals(CellType.WALL) && player.getDirection() != Direction.STAY){
			player.setPosition(nextPosition);
		}
	}
	
	private void delay(int time){
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void checkPortal(){
		if(player.getPortal(0).isActive() && player.getPortal(1).isActive()){
			if(player.getPosition().getX() == player.getPortal(0).getPlace().getX() && player.getPosition().getY() == player.getPortal(0).getPlace().getY()){
				player.setPosition(player.getPortal(1).getOuterPosition());
			}else if(player.getPosition().getX() == player.getPortal(1).getPlace().getX() && player.getPosition().getY() == player.getPortal(1).getPlace().getY()){
				player.setPosition(player.getPortal(0).getOuterPosition());
			}
		}
	}
	
	private void checkZPM(){
		if(Panel.getPlayingField().isZPM(player.getPosition())){
			Panel.getPlayingField().setMapCellType(player.getPosition(), CellType.PATH);
			player.incInventory();
			if(player.getInventory() == Map.ZPMCOUNT){
				//MOST ITT MI A SZAR TÖRTÉNJEN?
			}
		}
	}
	
	private void checkPlate(){
		if(Panel.getPlayingField().isPlate(player.getPosition())){
			Panel.getPlayingField().getDoorManager().openDoor(player.getPosition());
		}
	}
	
	private void checkAbbys(){
		if(Panel.getPlayingField().isAbyss(player.getPosition())){
			running = false;
			for(int j = 0; j < Map.LIMIT; j++){
				for(int i = 0; i < Map.LIMIT; i++){
					if(j%2 == 0)
						Panel.getPlayingField().getMap()[i][j].setType(CellType.TROLL);
					else
						Panel.getPlayingField().getMap()[Map.LIMIT-i-1][j].setType(CellType.TROLL);
					game.repaint();
					delay(5);
				}
			}
			System.exit(1);
		}
	}
}
