package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.Dragao;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Jogo;

public class TestMazeWithStaticDragon {

	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', 'H', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', 'E', ' ', 'D', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};

	Heroi h=new Heroi(3, 1, 'H');
	Dragao d=new Dragao(3,3,'D');
	Espada e=new Espada(1, 3, 'E');
	
	@Test
	public void testMoveHeroToFreeCell() {
		Jogo j= new Jogo(m1,h,d,e);
		assertEquals(3,j.getHeroi().get_x());
		assertEquals(1,j.getHeroi().get_y());
		assertEquals(true,j.moveLeft());
		assertEquals(2,j.getHeroi().get_x());
		assertEquals(1,j.getHeroi().get_y());
	}
	
	@Test
	public void testMoveHeroToWall() {
		Jogo j= new Jogo(m1,h,d,e);
		assertEquals(3,j.getHeroi().get_x());
		assertEquals(1,j.getHeroi().get_y());
		assertEquals(false,j.moveUp());
		assertEquals(3,j.getHeroi().get_x());
		assertEquals(1,j.getHeroi().get_y());
	}
	
	@Test
	public void testHeroDies() {
		Jogo j= new Jogo(m1,h,d,e);
		j.moveDown();
		j.checkDragon();
		assertEquals(true,j.getFimJogo());
	}
	
	@Test
	public void testHeroPickSword() {
		Heroi h1=new Heroi(1, 2, 'H');
		Jogo j= new Jogo(m1,h1,d,e);
		j.moveDown();
		assertEquals(true,j.getHeroi().getArmado());
	}
	
	
	

}
