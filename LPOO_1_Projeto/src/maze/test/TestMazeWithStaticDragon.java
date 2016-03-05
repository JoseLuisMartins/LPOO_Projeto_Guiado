package maze.test;

import static org.junit.Assert.*;

import org.junit.Test;

import maze.logic.Direction;
import maze.logic.Dragao;
import maze.logic.Espada;
import maze.logic.Heroi;
import maze.logic.Jogo;
import maze.logic.Labirinto;

public class TestMazeWithStaticDragon {

	char [][] m1 = {{'X', 'X', 'X', 'X', 'X'},
					{'X', ' ', ' ', ' ', 'S'},
					{'X', ' ', 'X', ' ', 'X'},
					{'X', ' ', ' ', ' ', 'X'},
					{'X', 'X', 'X', 'X', 'X'}};

	Heroi h=new Heroi(3, 1, 'H');
	Dragao d=new Dragao(3,3,'D');
	Espada e=new Espada(1, 3, 'E'); 

	@Test
	public void testMoveHeroToFreeCell() {
		Jogo j= new Jogo(m1,h,d,e); 
		assertEquals(3,j.getHeroi().get_x());
		assertEquals(1,j.getHeroi().get_y());
		assertEquals(true,j.move(Direction.LEFT));
		assertEquals(2,j.getHeroi().get_x());
		assertEquals(1,j.getHeroi().get_y());
	}
	
	@Test
	public void TestDefaultGameConstructor() {
		Jogo game=new Jogo();
		System.out.println(game);
	}
	
	@Test
	public void testMoveHeroToWall() {
		Jogo j= new Jogo(m1,h,d,e);
		assertEquals(3,j.getHeroi().get_x());
		assertEquals(1,j.getHeroi().get_y());
		assertEquals(false,j.move(Direction.UP));
		assertEquals(3,j.getHeroi().get_x());
		assertEquals(1,j.getHeroi().get_y());
	}
	
	@Test
	public void testHeroDies() {
		Jogo j= new Jogo(m1,h,d,e);
		j.move(Direction.DOWN);
		assertEquals(true,j.getFimJogo());
	}
	
	@Test
	public void testHeroPickSword() {
		Heroi h1=new Heroi(1, 2, 'H');
		Jogo j= new Jogo(m1,h1,d,e);
		j.move(Direction.DOWN);
		assertEquals(true,j.getHeroi().getArmado());
	}
	
	@Test
	public void testDragonDies() {
		Jogo j= new Jogo(m1,h,d,e);
		j.getHeroi().setArmado();
		j.move(Direction.DOWN);
		assertEquals(true,j.getDragao().getMorto());
	}
	
	@Test
	public void testHeroWins() {
		Jogo j= new Jogo(m1,h,d,e);
		j.move(Direction.LEFT);
		j.move(Direction.LEFT);
		j.move(Direction.DOWN);
		j.move(Direction.DOWN);
		j.move(Direction.RIGHT);
		j.move(Direction.RIGHT);
		j.move(Direction.UP);
		j.move(Direction.UP);
		assertEquals(true,j.move(Direction.RIGHT));
		assertEquals(true,j.getSair());
	}
	
	@Test
	public void testHeroGettingOutWithoutSword() {
		Jogo j= new Jogo(m1,h,d,e);
		assertEquals(false,j.move(Direction.RIGHT));
	}
	
	
	@Test
	public void testHeroGettingOutWithoutKillingTheDragon() {
		Jogo j= new Jogo(m1,h,d,e);
		
		j.getHeroi().setArmado();
		assertEquals(true, j.getHeroi().getArmado());
		assertEquals(false,j.move(Direction.RIGHT));
	}
	
	
	
	/**/
	
	
	

}
