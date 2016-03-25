package maze.gui;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import maze.logic.Direction;
import maze.logic.Dragao;
import maze.logic.Espada;
import maze.logic.GameMode;
import maze.logic.Heroi;
import maze.logic.Jogo;

public class MapWindow extends JPanel implements KeyListener,MouseListener{

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
	private BufferedImage dragonUp;
	private BufferedImage dragonDown;
	private BufferedImage dragonRight;
	private BufferedImage dragonLeft;
	private BufferedImage dragonSleep;
	private BufferedImage dragonSword;
	private BufferedImage dragonSleepSword;
	private BufferedImage win;
	private BufferedImage lose;
	private BufferedImage ground;
	private BufferedImage exit;
	//creating map
	private boolean createMap;
	private CreateGameElement currentElement;


	public MapWindow() {

		this.addKeyListener(this);
		this.addMouseListener(this);

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
			dragonUp =  ImageIO.read(new File("DragonUp.jpg"));
			dragonDown =  ImageIO.read(new File("DragonDown.jpg"));
			dragonRight =  ImageIO.read(new File("DragonRight.jpg"));
			dragonLeft =  ImageIO.read(new File("DragonLeft.jpg"));
			dragonSleep =  ImageIO.read(new File("DragonSleep.jpg"));
			dragonSleepSword =  ImageIO.read(new File("DragonSleepSword.jpg"));
			dragonSword =  ImageIO.read(new File("DragonSword.jpg"));
			dragon = dragonDown;
			win =  ImageIO.read(new File("win.jpg"));
			lose =  ImageIO.read(new File("lose.jpg"));
			ground =  ImageIO.read(new File("ground.jpg"));
			exit =  ImageIO.read(new File("exit.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		j=null;
		currentElement=CreateGameElement.Block;
		requestFocus();
	}

	public void setCurrentElement(CreateGameElement c){
		currentElement=c;
	}

	public void setJogo(Jogo j){
		this.j=j;
	}

	public void setCreateMap(boolean val){
		createMap=val;
	}


	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clears the backgorund ...
		char[][] maze=j.getLabirinto().getMaze();
		int frameDimension = 800;
		int width = frameDimension/maze.length ;
		int height = frameDimension/maze.length ;

		if(j.getFimJogo()){
			g.drawImage(lose, 0, 0, frameDimension, frameDimension, null);
		}
		else if(j.getSair()){
			g.drawImage(win, 0,0, frameDimension, frameDimension, null);

		}
		else{

			for (int i = 0; i < maze.length; i++) {
				for (int  k= 0; k < maze[i].length; k++) {
					switch (maze[i][k]) {
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

			//desenhar os dragoes
			for (int i = 0; i < j.getDragoes().size(); i++) {
				Dragao d=j.getDragoes().get(i);

				if(d.getAdormecido()){
					if(d.getGuardaEspada())
						dragon=dragonSleepSword;
					else
						dragon=dragonSleep;
				}
				else if(d.getGuardaEspada()){
					dragon=dragonSword;
				}
				else{
					switch (d.getDir()) {
					case UP://up
						dragon=dragonUp;
						break;
					case DOWN://down
						dragon=dragonDown;
						break;
					case RIGHT://right
						dragon=dragonRight;
						break;
					case LEFT://left
						dragon=dragonLeft;
						break;  
					}
				}
				g.drawImage(dragon, d.get_x()*width, d.get_y()*height, width, height , null);
			}
		}
	}

	public void update() {
		repaint();	
	}


	//TECLADO------------------------------------

	@Override
	public void keyPressed(KeyEvent e) {
		if(!createMap){
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
			repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	//RATO------------------------------------

	@Override
	public void mouseClicked(MouseEvent e) {
		if(createMap){
			int dim=j.getLabirinto().getMaze().length;
			int x=e.getX()/(800/dim);
			int y=e.getY()/(800/dim);

			if(x!=0 && y!= 0 && x!=(dim-1) && y!=(dim-1)){
				switch (currentElement) {
				case Dragon:

					break;
				case Hero:
					if(j.getHeroi()==null && (j.getLabirinto().getMaze()[y][x] == ' ' || j.getLabirinto().getMaze()[y][x] == 'X')){
						j.getLabirinto().setMaze(x, y, 'H');
						Heroi h=new Heroi(x, y, 'H');
						j.setHeroi(h);
					}
					else if(j.getLabirinto().getMaze()[y][x]=='H'){
						j.getLabirinto().setMaze(x, y, ' ');
						j.setHeroi(null);
					}
					break;
				case Sword:
					if(j.getEspada()==null && (j.getLabirinto().getMaze()[y][x] == ' ' || j.getLabirinto().getMaze()[y][x] == 'X')){
						j.getLabirinto().setMaze(x, y, 'E');
						Espada sword=new Espada(x, y, 'E');
						j.setEspada(sword);
					}
					else if(j.getLabirinto().getMaze()[y][x]=='E'){
						j.getLabirinto().setMaze(x, y, ' ');
						j.setEspada(null);
					}
					break;
				case Block:
					if(j.getLabirinto().getMaze()[y][x] == ' ')
						j.getLabirinto().setMaze(x, y, 'X');
					else if(j.getLabirinto().getMaze()[y][x] == 'X')
						j.getLabirinto().setMaze(x, y, ' ');
					break;
				default:
					break;

				}
			}else if(currentElement == CreateGameElement.Exit){
				if(j.getLabirinto().getMaze()[y][x] == 'S'){
					j.getLabirinto().setMaze(x, y, 'X');
					j.setHasExit(false);
				}else if(j.getHasExit()==false){
					j.getLabirinto().setMaze(x, y, 'S');
					j.setHasExit(true);
				}
			}










			repaint();
		}



	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}



}
