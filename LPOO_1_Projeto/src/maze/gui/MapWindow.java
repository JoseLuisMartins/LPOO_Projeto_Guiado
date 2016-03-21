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

import maze.logic.Jogo;

public class MapWindow extends JPanel implements KeyListener{

	private Jogo j;
	private BufferedImage hero;
	private BufferedImage wall;
	private BufferedImage sword;
	private BufferedImage dragon;

	public MapWindow(Jogo j1) {

		this.addKeyListener(this);

		try {
			hero =  ImageIO.read(new File("hero.jpg"));
			wall =  ImageIO.read(new File("wall.jpg"));
			sword =  ImageIO.read(new File("sword.jpg"));
			dragon =  ImageIO.read(new File("dragon.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		j=j1;
		requestFocus();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clears the backgorund ...
		char[][] maze=j.getLabirinto().getMaze();
		for (int i = 0; i < maze.length; i++) {
			for (int j = 0; j < maze[i].length; j++) {
				switch (maze[i][j]) {
				case 'd':
					g.drawImage(dragon, j*50, i*50, 50, 50, null);
					break;
				case 'D':
					g.drawImage(dragon, j*50, i*50, 50, 50, null);
					break;
				case 'H':
					g.drawImage(hero, j*50, i*50, 50, 50, null);
					break;
				case 'E':
					g.drawImage(sword, j*50, i*50, 50, 50, null);
					break;
				case 'A':
					g.drawImage(hero, j*50, i*50, 50, 50, null);
					break;
				case 'x':
					g.drawImage(wall, j*50, i*50, 50, 50, null);
					break;
				default:
					break;
				}
			}
		}

	}

	public void update() {
		repaint();	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

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
