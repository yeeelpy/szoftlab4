package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final int WINDOWSIZE = 900;
	public static final int CELLSIZE = WINDOWSIZE / Map.LIMIT;
	
	private static Map playingField;
	private JFrame frame;
	
	Panel(){
		frame = new JFrame();
		frame.setTitle("#SZOFTVERPROJEKTLABORHÁZI");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		playingField = new Map();
		this.setPreferredSize(new Dimension(WINDOWSIZE, WINDOWSIZE));
		frame.setResizable(false);
		frame.add(this);
		frame.addKeyListener(Game.getPlayer());
		frame.pack();
		frame.setVisible(true);
		this.setBackground(Color.DARK_GRAY);
	}
	
	public static Map getPlayingField(){
		return playingField;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintCanvas(g);
		paintLab(g);
		paintPlayer(g);
		if(!Game.running){
			paintLose(g);
		}
	}

	private void paintCanvas(Graphics g){
		for(int i = 0; i < WINDOWSIZE; i += CELLSIZE){
			g.drawLine(i, 0, i, WINDOWSIZE);
			g.drawLine(0, i, WINDOWSIZE, i);
		}
	}
	
	private void paintLab(Graphics g){
		for(int i = 0; i < Map.LIMIT; i++){
			for(int j = 0; j < Map.LIMIT; j++){
				if(playingField.getMap()[i][j].getType().equals(CellType.PATH)){
					g.setColor(Color.DARK_GRAY);
					g.fillRect(i*CELLSIZE, j*CELLSIZE, CELLSIZE, CELLSIZE);
					g.setColor(Color.BLACK);
				}else if(playingField.getMap()[i][j].getType().equals(CellType.WALL)){
					g.setColor(Color.BLACK);
					g.fillRect(i*CELLSIZE, j*CELLSIZE, CELLSIZE, CELLSIZE);
					g.setColor(Color.BLACK);
				}else if(playingField.getMap()[i][j].getType().equals(CellType.ABYSS)){
					g.setColor(Color.GREEN);
					g.fillRect(i*CELLSIZE, j*CELLSIZE, CELLSIZE, CELLSIZE);
					g.setColor(Color.BLACK);
				}else if(playingField.getMap()[i][j].getType().equals(CellType.ZPM)){
					g.setColor(Color.YELLOW);
					g.fillOval(i*CELLSIZE, j*CELLSIZE, CELLSIZE, CELLSIZE);
					g.setColor(Color.BLACK);
				}else if(playingField.getMap()[i][j].getType().equals(CellType.DOOR)){
					g.setColor(Color.GRAY);
					g.fillRect(i*CELLSIZE, j*CELLSIZE, CELLSIZE, CELLSIZE);
					g.setColor(Color.BLACK);
				}else if(playingField.getMap()[i][j].getType().equals(CellType.PLATE)){
					g.setColor(Color.WHITE);
					g.fillOval(i*CELLSIZE, j*CELLSIZE, CELLSIZE, CELLSIZE);
					g.setColor(Color.BLACK);
				}else if(playingField.getMap()[i][j].getType().equals(CellType.BLUEPORTAL)){
					g.setColor(Color.BLUE);
					g.fillRect(i*CELLSIZE, j*CELLSIZE, CELLSIZE, CELLSIZE);
					g.setColor(Color.BLACK);
				}else if(playingField.getMap()[i][j].getType().equals(CellType.ORANGEPORTAL)){
					g.setColor(Color.ORANGE);
					g.fillRect(i*CELLSIZE, j*CELLSIZE, CELLSIZE, CELLSIZE);
					g.setColor(Color.BLACK);
				}else if(playingField.getMap()[i][j].getType().equals(CellType.BOX)){
					g.setColor(Color.PINK);
					g.fillRect(i*CELLSIZE, j*CELLSIZE, CELLSIZE, CELLSIZE);
					g.setColor(Color.BLACK);
				}else if(playingField.getMap()[i][j].getType().equals(CellType.PRESSUREDPLATE)){
					g.setColor(Color.PINK);
					g.fillOval(i*CELLSIZE, j*CELLSIZE, CELLSIZE, CELLSIZE);
					g.setColor(Color.BLACK);
				}
			}
		}
		
	}
	
	private void paintPlayer(Graphics g){
		g.setColor(Color.RED);
		g.fillOval(Game.getPlayer().getPosition().getX()*CELLSIZE, Game.getPlayer().getPosition().getY()*CELLSIZE, CELLSIZE, CELLSIZE);
		g.setColor(Color.BLACK);
	}
	
	private void paintLose(Graphics g){
		for(int i = 0; i < Map.LIMIT; i++){
			for(int j = 0; j < Map.LIMIT; j++){
				if(playingField.getMap()[i][j].getType().equals(CellType.TROLL)){
					g.fillRect(i*CELLSIZE, j*CELLSIZE, CELLSIZE, CELLSIZE);
				}
			}
		}
	}
	
	
}
