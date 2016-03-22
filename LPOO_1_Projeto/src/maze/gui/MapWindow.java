package maze.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import maze.logic.Direction;
import maze.logic.Dragao;
import maze.logic.GameMode;
import maze.logic.Jogo;

public class MapWindow extends JPanel implements KeyListener{

	private Jogo j;
	private BufferedImage hero;
	private BufferedImage heroUp;
	private BufferedImage heroDown;
	private BufferedImage heroRight;
	private BufferedImage heroLeft;
	private BufferedImage heroArmedUp;
	private BufferedImage heroArmedDown;
	private BufferedImage heroArmedRight;
	private BufferedImage heroArmedLeft;
	private BufferedImage wall;
	private BufferedImage sword;
	private BufferedImage dragon;
	private BufferedImage win;
	private BufferedImage lose;
	private BufferedImage ground;
	private BufferedImage exit;
	private GameMode mode;

	public MapWindow(Jogo j1,GameMode m) {

		this.addKeyListener(this);

		try {
			heroArmedUp =  ImageIO.read(new File("heroArmedUp.jpg"));
			heroArmedDown =  ImageIO.read(new File("heroArmedDown.jpg"));
			heroArmedRight =  ImageIO.read(new File("heroArmedRight.jpg"));
			heroArmedLeft =  ImageIO.read(new File("heroArmedLeft.jpg"));
			heroUp =  ImageIO.read(new File("heroUp.jpg"));
			heroDown =  ImageIO.read(new File("heroDown.jpg"));
			heroRight =  ImageIO.read(new File("heroRight.jpg"));
			heroLeft =  ImageIO.read(new File("heroLeft.jpg"));
			hero=heroDown;
			wall =  ImageIO.read(new File("wall.jpg"));
			sword =  ImageIO.read(new File("sword.jpg"));
			dragon =  ImageIO.read(new File("dragonUp.jpg"));
			win =  ImageIO.read(new File("win.jpg"));
			lose =  ImageIO.read(new File("lose.jpg"));
			ground =  ImageIO.read(new File("ground.jpg"));
			exit =  ImageIO.read(new File("exit.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		j=j1;
		mode=m;
		requestFocus();
	}
	public int dimensionFrame(){
		int dim = j.getLabirinto().getMaze().length;
		if(dim == 5 || dim == 15)
			return 600;
		else if(dim == 11)
			return 605;
		else if(dim == 13)
			return 604;
		else if(dim == 17 )
			return 612;
		else if(dim == 21)
			return 609;
		else
			return 608;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clears the backgorund ...
		char[][] maze=j.getLabirinto().getMaze();
		int width = dimensionFrame()/maze.length ;
		int height = dimensionFrame()/maze.length ;
		if(j.getFimJogo()){
			g.drawImage(lose, 50, 50, 200, 200, null);
		}
		else if(j.getSair()){
			g.drawImage(win, 50, 50, 200, 200, null);
		}
		else{

			for (int i = 0; i < maze.length; i++) {
				for (int  k= 0; k < maze[i].length; k++) {
					switch (maze[i][k]) {
					case 'd':
						g.drawImage(dragon, k*width, i*height, width, height , null);
						break;
					case 'D':
						g.drawImage(dragon, k*width, i*height, width, height , null);
						break;
					case 'H': 
						g.drawImage(hero, k*width, i*height, width, height , null);
						break;
					case 'E':
						g.drawImage(sword, k*width, i*height, width, height , null);
						break;
					case 'A':
						g.drawImage(hero, k*width, i*height, width, height , null);
						break;
					case 'X':
						g.drawImage(wall, k*width, i*height, width, height , null);
						break;
					case ' ':
						g.drawImage(ground, k*width, i*height, width, height , null);
						break;
					case 'S':
						if(j.getDragoes().size()==0)
							g.drawImage(ground, k*width, i*height, width, height , null);
						else
							g.drawImage(exit, k*width, i*height, width, height , null);
						break;
					default:
						break;
					}
				}
			}
		}
	}

	public void update() {
		repaint();	
	}

	private void dragonAction(){

		switch (mode) {
		case StaticDragon://dragao parado
			break; 
		case MovingDragon://Dragao com Movimentacao 
			for (Dragao d : j.getDragoes()) {
				j.moveDragon(d);
			} 
			j.checkDragon();
			break;
		case ToogleSleepAndMoveDragon://Dragao com Movimentacao intercalada com Dormir 
			j.toggleAdormecerRandom();
			for (Dragao d : j.getDragoes()) {
				if(d.getAdormecido()==false){//se não estiver adormecido o dragao pode mover-se
					j.moveDragon(d);
				}
			}
			j.checkDragon();
			break;
		default:
			break;
		} 
	}


	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_LEFT: 
			j.move(Direction.LEFT); 
			if(j.getHeroi().getArmado())
				hero=heroArmedLeft;
			else
				hero=heroLeft;
			break;

		case KeyEvent.VK_RIGHT: 
			j.move(Direction.RIGHT);
			if(j.getHeroi().getArmado())
				hero=heroArmedRight;
			else
				hero=heroRight;
			break;

		case KeyEvent.VK_UP: 
			j.move(Direction.UP);
			if(j.getHeroi().getArmado())
				hero=heroArmedUp;
			else
				hero=heroUp;
			break;

		case KeyEvent.VK_DOWN: 
			j.move(Direction.DOWN);
			if(j.getHeroi().getArmado())
				hero=heroArmedDown;
			else
				hero=heroDown;
			break;

		}
		dragonAction();
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}



}
